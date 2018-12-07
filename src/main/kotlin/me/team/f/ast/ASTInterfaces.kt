package me.team.f.ast

import java.util.*
import kotlin.collections.HashMap
import kotlin.reflect.KParameter
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor

var globalSymbolTable = HashMap<String, Node>()
var errors = LinkedList<Error>()
var localSymbolTable = hashMapOf<String, Node>()


interface Node {
    val position: Position?
}

data class LineCol(val line: Int, val col: Int)

data class Position(val start: LineCol, val end: LineCol)

fun Node.isBefore(other: Node): Boolean =
    position!!.start.isBefore(other.position!!.start)

fun LineCol.isBefore(other: LineCol): Boolean =
    line < other.line || (line == other.line && col < other.col)

fun getPos(startLine: Int, startCol: Int, endLine: Int, endCol: Int): Position {
    val start = LineCol(startLine, startCol)
    val end = LineCol(endLine, endCol)
    return Position(start, end)
}

fun Node.process(operation: (Node) -> Unit) {
    operation(this)
    this.javaClass.kotlin.memberProperties.forEach { p ->
        val v = p.get(this)
        when (v) {
            is Node -> v.process(operation)
            is Collection<*> -> v.forEach { if (it is Node) it.process(operation) }
        }
    }
}

@Suppress("UNCHECKED_CAST")
fun <T : Node> Node.specificProcess(classF: Class<T>, operation: (T) -> Unit) {
    process {
        if (classF.isInstance(it)) {
            operation(it as T)
        }
    }
}

fun Node.transform(operation: (Node) -> Node): Node {
    operation(this)
    val changes = HashMap<String, Any>()
    this.javaClass.kotlin.memberProperties.forEach { p ->
        val v = p.get(this)
        when (v) {
            is Node -> {
                val newValue = v.transform(operation)
                if (newValue != v) changes[p.name] = newValue
            }
            is Collection<*> -> {
                val newValue = v.map { if (it is Node) it.transform(operation) else it }
                if (newValue != v) changes[p.name] = newValue
            }
        }
    }
    var instanceToTransform = this
    if (!changes.isEmpty()) {
        val constructor = this.javaClass.kotlin.primaryConstructor!!
        val params = HashMap<KParameter, Any?>()
        constructor.parameters.forEach { param ->
            if (changes.containsKey(param.name)) {
                params[param] = changes[param.name]
            } else {
                params[param] = this.javaClass.kotlin.memberProperties.find { param.name == it.name }!!.get(this)
            }
        }
        instanceToTransform = constructor.callBy(params)
    }
    return operation(instanceToTransform)
}

/**
 * Grammar part
 */

data class Program(
    val declarations: List<Declaration>,
    override val position: Position? = null
) : Node {

//    fun validate(): List<Error> {
//        localSymbolTable.putAll(globalSymbolTable)
//        declarations.forEach { it.validate() }
//        return errors
//    }
}

interface Declaration : Node {
    fun type(): Type?
    fun validate()
}

interface Expression : Node {
    fun type(): Type?
    fun validate()
}

interface Statement : Node {
    fun validate()
}

interface Type : Node {
    override fun toString(): String
    fun typeName(): String {
        return toString()
    }
}


/**
 * Declaration
 */

data class VarDeclaration(
    val varName: String,
    var type: Type? = null,
    val value: Expression,
    override val position: Position? = null
) : Declaration, Statement {
    override fun validate() {
        if (globalSymbolTable.containsKey(varName)) {
            errors.add(
                Error(
                    "A variable named '$varName' has been already declared at ${globalSymbolTable[varName]!!.position!!.start}",
                    position!!.start
                )
            )
        } else {
            if (type == null) {
                type = value.type()
            } else {
                if (type?.typeName() != value.type()?.typeName()) {
                    errors.add(
                        Error(
                            "Declared type \"$type\" doesn't match with value type \"${value.type()}\"",
                            position?.start!!
                        )
                    )
                }
            }
            globalSymbolTable[varName] = this
        }
        value.validate()
    }


    override fun type(): Type? {
        return type
    }
}

/**
 * Secondary
 */

data class Call(
    val secondary: Expression,
    val expressions: List<Expression>,
    override val position: Position? = null
) : Expression {
    override fun validate() {
        secondary as VarReference
        if (!globalSymbolTable.containsKey(secondary.name)) {
            errors.add(
                Error(
                    "Function ${secondary.name} is not declared yet.",
                    secondary.position?.start!!
                )
            )
        } else {

            val expression = globalSymbolTable[secondary.name] as Function

            if (expression.type != null) {
                if (expressions.size != expression.parameters.size) {
                    errors.add(
                        Error(
                            "Wrong number of parameters when call function ${secondary.name}",
                            secondary.position?.start!!
                        )
                    )
                } else {
                    for (i in 0..expression.parameters.lastIndex) {
//                            val l = expression.parameters[i].type
                        if (expression.parameters[i].type() != expressions[i].type()) {
                            val function = expressions[i]
                            val nameOfVarInCall = when (function) {
                                is VarReference -> function.name
                                is BoolLit -> function.value
                                is StrLit -> function.value
                                is RealLit -> function.value
                                is RatLit -> function.value
                                is CompLit -> function.value
                                is IntLit -> function.value
                                else -> function.toString()
                            }
                            errors.add(
                                Error(
                                    "Non-compatible type of parameters. Parameter in call with typeName $nameOfVarInCall of type ${
                                    function.type()
                                    } is " +
                                            "not same as in function declaration parameter with typeName ${expression.parameters[i].parName} of type ${
                                            expression.parameters[i].type()
                                            }",
                                    expressions[i].position?.start!!
                                )
                            )
                        }
                    }
                }

            }
        }
    }


    override fun type(): Type? {
        return secondary.type()
    }
}

data class ElementOf(
    val varName: Expression,
    val index: Expression,
    override val position: Position? = null
) : Expression {
    override fun validate() {
        varName.validate()
        index.validate()
    }

    override fun type(): Type? {
        return varName.type()
    }
}

data class NamedTupleElement(
    val secondary: Expression,
    val fieldName: String,
    override val position: Position? = null
) : Expression {
    override fun validate() {
        secondary.validate()
    }

    override fun type(): Type? {
        return secondary.type()
    }
}

data class UnnamedTupleElement(
    val secondary: Expression,
    val fieldNum: String,
    override val position: Position? = null
) : Expression {
    override fun validate() {
        secondary.validate()
    }

    override fun type(): Type? {
        return secondary.type()
    }
}

/**
 * Primary
 */
data class Conditional(
    val predicate: Expression,
    val thenExpr: Expression,
    val elseExpr: Expression,
    override val position: Position? = null
) : Expression {
    override fun validate() {
        predicate.validate()
        thenExpr.validate()
        elseExpr.validate()
    }

    override fun type(): Type? {
        return null
    }
}

/**
 * Types
 */

data class BooleanType(override val position: Position? = null) : Type {
    override fun toString(): String {
        return "boolean"
    }

}

data class IntegerType(override val position: Position? = null) : Type {
    override fun toString(): String {
        return "integer"
    }
}

data class RealType(override val position: Position? = null) : Type {
    override fun toString(): String {
        return "real"
    }
}

data class RationalType(override val position: Position? = null) : Type {
    override fun toString(): String {
        return "rational"
    }
}

data class ComplexType(override val position: Position? = null) : Type {
    override fun toString(): String {
        return "complex"
    }
}

data class StringType(override val position: Position? = null) : Type {
    override fun toString(): String {
        return "string"
    }
}

data class AnyType(override val position: Position? = null) : Type {
    override fun toString(): String {
        return "any"
    }
}

/**
 * Expressions
 */

data class VarReference(
    val name: String,
    override val position: Position? = null
) : Expression {
    override fun validate() {
        if (!localSymbolTable.containsKey(name) && !globalSymbolTable.containsKey(name)) {
            errors.add(Error("There is no variable named '$name'", position!!.start))
        } else if(localSymbolTable[name] != null && localSymbolTable[name] is VarDeclaration){
            if(isBefore(localSymbolTable[name] as VarDeclaration))errors.add(
                Error(
                    "You cannot refer to variable '$name' before its declaration",
                    position!!.start
                )
            )
        } else if(isBefore((globalSymbolTable[name] as VarDeclaration))){
            errors.add(
                Error(
                    "You cannot refer to variable '$name' before its declaration",
                    position!!.start
                )
            )
        }
    }

    override fun type(): Type? {
        val node = globalSymbolTable[name]
        return when (node) {
            is VarDeclaration -> {
                node.type
            }
            is Parameter -> {
                node.type
            }
            else -> AnyType()
        }
    }
}

data class BoolLit(
    val value: String,
    override val position: Position? = null
) : Expression {
    override fun validate() {
    }

    override fun type(): Type? {
        return BooleanType()
    }
}

data class IntLit(
    val value: String,
    override val position: Position? = null
) : Expression {
    override fun validate() {
    }

    override fun type(): Type? {
        return IntegerType()
    }
}

data class RealLit(
    val value: String,
    override val position: Position? = null
) : Expression {
    override fun validate() {
    }

    override fun type(): Type? {
        return RealType()
    }
}

data class RatLit(
    val value: String,
    override val position: Position? = null
) : Expression {
    override fun validate() {

    }

    override fun type(): Type? {
        return RationalType()
    }
}

data class CompLit(
    val value: String,
    override val position: Position? = null
) : Expression {
    override fun validate() {

    }

    override fun type(): Type? {
        return ComplexType()
    }
}

data class StrLit(
    val value: String,
    override val position: Position? = null
) : Expression {
    override fun validate() {

    }
    override fun type(): Type? {
        return StringType()
    }
}

interface BinaryExpression : Expression {
    val left: Expression
    val right: Expression
    fun leftType(): Type? {
        return left.type()
    }

    fun rightType(): Type? {
        return right.type()
    }

    override fun validate() {
        left.validate()
        right.validate()
    }
}

data class SumExpression(
    override val left: Expression,
    override val right: Expression,
    override val position: Position? = null
) : BinaryExpression {
    override fun validate() {

    }

    override fun type(): Type? {
        val left = leftType()
        val right = rightType()
        val typePair = Pair(left, right)
        return when {
            typePair.first is IntegerType && typePair.second is IntegerType -> IntegerType()
            typePair.first is IntegerType && typePair.second is RealType -> RealType()
            typePair.first is IntegerType && typePair.second is RationalType -> RationalType()
            typePair.first is IntegerType && typePair.second is ComplexType -> ComplexType()
            typePair.first is RealType && typePair.second is IntegerType -> RealType()
            typePair.first is RealType && typePair.second is RealType -> RealType()
            typePair.first is RealType && typePair.second is ComplexType -> ComplexType()
            typePair.first is RationalType && typePair.second is IntegerType -> RationalType()
            typePair.first is RationalType && typePair.second is RationalType -> RationalType()
            typePair.first is ComplexType && typePair.second is IntegerType -> ComplexType()
            typePair.first is ComplexType && typePair.second is RealType -> ComplexType()
            typePair.first is ComplexType && typePair.second is ComplexType -> ComplexType()
            typePair.first is MapType && typePair.second is MapType -> {
                left as MapType
                right as MapType
                if (left.types != right.types) {
                    errors.add(
                        Error(
                            "Cannot concat maps with elements of types (${left.types}, ${right.types})",
                            position?.start!!
                        )
                    )
                    AnyType()
                } else MapType(left.types)
            }
            typePair.first is ArrayType && typePair.second is ArrayType -> {
                left as ArrayType
                right as ArrayType
                if (left.type.typeName() != right.type.typeName()) {
                    errors.add(
                        Error(
                            "Cannot concat arrays with elements of types (${left.typeName()}, ${right.typeName()})",
                            position?.start!!
                        )
                    )
                    AnyType()
                } else ArrayType(left.type)
            }
            typePair.first is StringType && typePair.second is StringType -> {
                return StringType()
            }
            else -> {
                errors.add(
                    Error(
                        "Operator + cannot be applied on operands of types ($left, $right)",
                        position?.start!!
                    )
                )
                null
            }
        }
    }
}

data class SubExpression(
    override val left: Expression,
    override val right: Expression,
    override val position: Position? = null
) : BinaryExpression {

    override fun type(): Type? {
        val left = leftType()
        val right = rightType()
        val typePair = Pair(left, right)
        return when {
            typePair.first is IntegerType && typePair.second is IntegerType -> IntegerType()
            typePair.first is IntegerType && typePair.second is RealType -> RealType()
            typePair.first is IntegerType && typePair.second is RationalType -> RationalType()
            typePair.first is IntegerType && typePair.second is ComplexType -> ComplexType()
            typePair.first is RealType && typePair.second is IntegerType -> RealType()
            typePair.first is RealType && typePair.second is RealType -> RealType()
            typePair.first is RealType && typePair.second is ComplexType -> ComplexType()
            typePair.first is RationalType && typePair.second is IntegerType -> RationalType()
            typePair.first is RationalType && typePair.second is RationalType -> RationalType()
            typePair.first is ComplexType && typePair.second is IntegerType -> ComplexType()
            typePair.first is ComplexType && typePair.second is RealType -> ComplexType()
            typePair.first is ComplexType && typePair.second is ComplexType -> ComplexType()
            typePair.first is MapType && typePair.second is MapType -> {
                errors.add(
                    Error(
                        "Unsupported operator - for expressions of type map.",
                        position?.start!!
                    )
                )
                null
            }
            typePair.first is ArrayType && typePair.second is ArrayType -> {
                errors.add(
                    Error(
                        "Unsupported operator - for expressions of type array.",
                        position?.start!!
                    )
                )
                null
            }
            else -> {
                errors.add(
                    Error(
                        "Operator - cannot be applied on operands of types ($left, $right)",
                        position?.start!!
                    )
                )
                null
            }
        }
    }
}

data class MultExpression(
    override val left: Expression,
    override val right: Expression,
    override val position: Position? = null
) : BinaryExpression {
    override fun type(): Type? {
        val left = leftType()
        val right = rightType()
        val typePair = Pair(left, right)
        return when {
            typePair.first is IntegerType && typePair.second is IntegerType -> IntegerType()
            typePair.first is IntegerType && typePair.second is RealType -> RealType()
            typePair.first is IntegerType && typePair.second is RationalType -> RationalType()
            typePair.first is IntegerType && typePair.second is ComplexType -> ComplexType()
            typePair.first is RealType && typePair.second is IntegerType -> RealType()
            typePair.first is RealType && typePair.second is RealType -> RealType()
            typePair.first is RealType && typePair.second is ComplexType -> ComplexType()
            typePair.first is RationalType && typePair.second is IntegerType -> RationalType()
            typePair.first is RationalType && typePair.second is RationalType -> RationalType()
            typePair.first is ComplexType && typePair.second is IntegerType -> ComplexType()
            typePair.first is ComplexType && typePair.second is RealType -> ComplexType()
            typePair.first is ComplexType && typePair.second is ComplexType -> ComplexType()
            typePair.first is MapType && typePair.second is MapType -> {
                errors.add(
                    Error(
                        "Unsupported operator * for expressions of type map.",
                        position?.start!!
                    )
                )
                AnyType()
            }
            typePair.first is ArrayType && typePair.second is ArrayType -> {
                errors.add(
                    Error(
                        "Unsupported operator * for expressions of type array.",
                        position?.start!!
                    )
                )
                AnyType()
            }
            else -> {
                errors.add(
                    Error(
                        "Operator * cannot be applied on operands of types ($left, $right)",
                        position?.start!!
                    )
                )
                AnyType()
            }
        }
    }
}

data class DivExpression(
    override val left: Expression,
    override val right: Expression,
    override val position: Position? = null
) : BinaryExpression {
    override fun type(): Type? {
        val left = leftType()
        val right = rightType()
        val typePair = Pair(left, right)
        return when {
            typePair.first is IntegerType && typePair.second is IntegerType -> IntegerType()
            typePair.first is IntegerType && typePair.second is RealType -> RealType()
            typePair.first is IntegerType && typePair.second is RationalType -> RationalType()
            typePair.first is IntegerType && typePair.second is ComplexType -> ComplexType()
            typePair.first is RealType && typePair.second is IntegerType -> RealType()
            typePair.first is RealType && typePair.second is RealType -> RealType()
            typePair.first is RealType && typePair.second is ComplexType -> ComplexType()
            typePair.first is RationalType && typePair.second is IntegerType -> RationalType()
            typePair.first is RationalType && typePair.second is RationalType -> RationalType()
            typePair.first is ComplexType && typePair.second is IntegerType -> ComplexType()
            typePair.first is ComplexType && typePair.second is RealType -> ComplexType()
            typePair.first is ComplexType && typePair.second is ComplexType -> ComplexType()
            typePair.first is MapType && typePair.second is MapType -> {
                errors.add(
                    Error(
                        "Unsupported operator / for expressions of type map.",
                        position?.start!!
                    )
                )
                AnyType()
            }
            typePair.first is ArrayType && typePair.second is ArrayType -> {
                errors.add(
                    Error(
                        "Unsupported operator / for expressions of type array.",
                        position?.start!!
                    )
                )
                AnyType()
            }
            else -> {
                errors.add(
                    Error(
                        "Operator / cannot be applied on operands of types ($left, $right)",
                        position?.start!!
                    )
                )
                AnyType()
            }
        }
    }
}


data class LessExpression(
    override val left: Expression,
    override val right: Expression,
    override val position: Position? = null
) : BinaryExpression {
    override fun type(): Type? {
        val left = leftType()
        val right = rightType()
        val typePair = Pair(left, right)
        return when {
            typePair.first is IntegerType && typePair.second is IntegerType ||
                    typePair.first is IntegerType && typePair.second is RealType ||
                    typePair.first is IntegerType && typePair.second is RationalType ||
                    typePair.first is RealType && typePair.second is IntegerType ||
                    typePair.first is RealType && typePair.second is RealType ||
                    typePair.first is RationalType && typePair.second is IntegerType ||
                    typePair.first is RationalType && typePair.second is RationalType ||
                    typePair.first is ComplexType && typePair.second is ComplexType -> BooleanType()
            else -> {
                errors.add(
                    Error(
                        "Cannot use comparison operator to operands of types ($left, $right)",
                        position?.start!!
                    )
                )
                AnyType()
            }
        }
    }
}

data class GreaterExpression(
    override val left: Expression,
    override val right: Expression,
    override val position: Position? = null
) : BinaryExpression {
    override fun type(): Type? {
        val left = leftType()
        val right = rightType()
        val typePair = Pair(left, right)
        return when {
            typePair.first is IntegerType && typePair.second is IntegerType ||
                    typePair.first is IntegerType && typePair.second is RealType ||
                    typePair.first is IntegerType && typePair.second is RationalType ||
                    typePair.first is RealType && typePair.second is IntegerType ||
                    typePair.first is RealType && typePair.second is RealType ||
                    typePair.first is RationalType && typePair.second is IntegerType ||
                    typePair.first is RationalType && typePair.second is RationalType ||
                    typePair.first is ComplexType && typePair.second is ComplexType -> BooleanType()
            else -> {
                errors.add(
                    Error(
                        "Cannot use comparison operator to operands of types ($left, $right)",
                        position?.start!!
                    )
                )
                AnyType()
            }
        }
    }
}

data class LessEqExpression(
    override val left: Expression,
    override val right: Expression,
    override val position: Position? = null
) : BinaryExpression {
    override fun type(): Type? {
        val left = leftType()
        val right = rightType()
        val typePair = Pair(left, right)
        return when {
            typePair.first is IntegerType && typePair.second is IntegerType ||
                    typePair.first is IntegerType && typePair.second is RealType ||
                    typePair.first is IntegerType && typePair.second is RationalType ||
                    typePair.first is RealType && typePair.second is IntegerType ||
                    typePair.first is RealType && typePair.second is RealType ||
                    typePair.first is RationalType && typePair.second is IntegerType ||
                    typePair.first is RationalType && typePair.second is RationalType ||
                    typePair.first is ComplexType && typePair.second is ComplexType -> BooleanType()
            else -> {
                errors.add(
                    Error(
                        "Cannot use comparison operator to operands of types ($left, $right)",
                        position?.start!!
                    )
                )
                AnyType()
            }
        }
    }
}

data class GreaterEqExpression(
    override val left: Expression,
    override val right: Expression,
    override val position: Position? = null
) : BinaryExpression {
    override fun type(): Type? {
        val left = leftType()
        val right = rightType()
        val typePair = Pair(left, right)
        return when {
            typePair.first is IntegerType && typePair.second is IntegerType ||
                    typePair.first is IntegerType && typePair.second is RealType ||
                    typePair.first is IntegerType && typePair.second is RationalType ||
                    typePair.first is RealType && typePair.second is IntegerType ||
                    typePair.first is RealType && typePair.second is RealType ||
                    typePair.first is RationalType && typePair.second is IntegerType ||
                    typePair.first is RationalType && typePair.second is RationalType ||
                    typePair.first is ComplexType && typePair.second is ComplexType -> BooleanType()
            else -> {
                errors.add(
                    Error(
                        "Cannot use comparison operator to operands of types ($left, $right)",
                        position?.start!!
                    )
                )
                AnyType()
            }
        }
    }
}

data class EqualExpression(
    override val left: Expression,
    override val right: Expression,
    override val position: Position? = null
) : BinaryExpression {
    override fun type(): Type? {
        val left = leftType()
        val right = rightType()
        val typePair = Pair(left, right)
        return when {
            left is IntegerType && right is IntegerType ||
                    typePair.first is IntegerType && typePair.second is RealType ||
                    typePair.first is IntegerType && typePair.second is RationalType ||
                    typePair.first is RealType && typePair.second is IntegerType ||
                    typePair.first is RealType && typePair.second is RealType ||
                    typePair.first is RationalType && typePair.second is IntegerType ||
                    typePair.first is RationalType && typePair.second is RationalType ||
                    typePair.first is ComplexType && typePair.second is ComplexType -> BooleanType()
            else -> {
                errors.add(
                    Error(
                        "Cannot use comparison operator to operands of types ($left, $right)",
                        position?.start!!
                    )
                )
                AnyType()
            }
        }
    }
}

data class NotEqExpression(
    override val left: Expression,
    override val right: Expression,
    override val position: Position? = null
) : BinaryExpression {
    override fun type(): Type? {
        val left = leftType()
        val right = rightType()
        val typePair = Pair(left, right)
        return when {
            left is IntegerType && right is IntegerType ||
                    typePair.first is IntegerType && typePair.second is RealType ||
                    typePair.first is IntegerType && typePair.second is RationalType ||
                    typePair.first is RealType && typePair.second is IntegerType ||
                    typePair.first is RealType && typePair.second is RealType ||
                    typePair.first is RationalType && typePair.second is IntegerType ||
                    typePair.first is RationalType && typePair.second is RationalType ||
                    typePair.first is ComplexType && typePair.second is ComplexType -> BooleanType()
            else -> {
                errors.add(
                    Error(
                        "Cannot use comparison operator to operands of types ($left, $right)",
                        position?.start!!
                    )
                )
                AnyType()
            }
        }
    }
}

data class AndExpression(
    override val left: Expression,
    override val right: Expression,
    override val position: Position? = null
) : BinaryExpression {
    override fun type(): Type? {
        val left = leftType()
        val right = rightType()
        return if (left !is BooleanType || right !is BooleanType) {
            errors.add(
                Error(
                    "Cannot use logical operator on values of types ($left, $right)",
                    position?.start!!
                )
            )
            AnyType()
        } else BooleanType()
    }
}

data class OrExpression(
    override val left: Expression,
    override val right: Expression,
    override val position: Position? = null
) : BinaryExpression {
    override fun type(): Type? {
        val left = leftType()
        val right = rightType()
        return if (left !is BooleanType || right !is BooleanType) {
            errors.add(
                Error(
                    "Cannot use logical operator on values of types ($left, $right)",
                    position?.start!!
                )
            )
            AnyType()
        } else BooleanType()
    }
}

data class XorExpression(
    override val left: Expression,
    override val right: Expression,
    override val position: Position? = null
) : BinaryExpression {
    override fun type(): Type? {
        val left = leftType()
        val right = rightType()
        return if (left !is BooleanType || right !is BooleanType) {
            errors.add(
                Error(
                    "Cannot use logical operator on values of types ($left, $right)",
                    position?.start!!
                )
            )
            AnyType()
        } else BooleanType()
    }
}

/**
 * Statements
 */

data class Assignment(
    val secondary: Expression,
    val expression: Expression,
    override val position: Position? = null
) : Statement {
    override fun validate() {
        secondary as VarReference
        if (!globalSymbolTable.containsKey(secondary.name)) {
            errors.add(
                Error(
                    "There is no variable named '${secondary.name}'",
                    position!!.start
                )
            )
        } else if (isBefore(globalSymbolTable[secondary.name]!!)) {
            errors.add(
                Error(
                    "You cannot refer to variable '${secondary.name}' before its declaration",
                    position!!.start
                )
            )
        }
        val left = secondary.type()
        val right = expression.type()
        if (left?.typeName() != right?.typeName()) {
            errors.add(
                Error(
                    "Connot assign expression of $right type to variable of $left type",
                    position?.start!!
                )
            )
        }
    }
}

data class FunctionCall(
    val secondary: Expression,
    val expressions: List<Expression>,
    override val position: Position? = null
) : Statement {
    override fun validate() {
        if (!globalSymbolTable.containsKey((secondary as VarReference).name)) {
            errors.add(
                Error(
                    "Function ${secondary.name} is not declared yet.",
                    secondary.position?.start!!
                )
            )
            return
        }
        secondary.validate()

        val expression = globalSymbolTable[secondary.name] as VarDeclaration
        val functionType = expression.type()
        if (functionType != null && functionType is FunctionType) {

            if (expressions.size != functionType.types.size - 1) {
                errors.add(
                    Error(
                        "Wrong number of parameters when call function ${secondary.name}. ${expressions.size}!=${functionType.types.size - 1}",
                        secondary.position?.start!!
                    )
                )
            } else {
                for (i in 0 until expressions.size) {
                    val paramType = expressions[i].type()
                    if (functionType.types[i].typeName() != paramType?.typeName()) {
                        val function = expressions[i]
                        val nameOfVarInCall = when (function) {
                            is VarReference -> function.name
                            is BoolLit -> function.value
                            is StrLit -> function.value
                            is RealLit -> function.value
                            is RatLit -> function.value
                            is CompLit -> function.value
                            is IntLit -> function.value
                            else -> function.toString()
                        }
                        errors.add(
                            Error(
                                "Non-compatible type of parameters. Parameter in call $nameOfVarInCall:$paramType is " +
                                        "not same type ${functionType.types[i]} as in function declaration ",
                                expressions[i].position?.start!!
                            )
                        )
                    }
                }
            }

        }
    }
}

data class IfStatement(
    val predicate: Expression,
    val thenStatements: List<Statement>,
    val elseStatements: List<Statement>,
    override val position: Position? = null
) : Statement {
    override fun validate() {
        if (predicate.type() != BooleanType()) {
            errors.add(Error("Predicate expression should return boolean value.", position?.start!!))
        }
        predicate.validate()
        if (thenStatements.size == elseStatements.size && elseStatements.size == 1) {

        }
        thenStatements.forEach { it.validate() }
        elseStatements.forEach { it.validate() }
    }
}

data class LoopStatement(
    val loopHeader: LoopHeader,
    val statements: List<Statement>,
    override val position: Position? = null
) : Statement {
    override fun validate() {
        loopHeader.validate()
        statements.forEach { statement -> statement.validate() }
    }
}

interface LoopHeader : Statement

data class ForLoopHeader(
    val expressions: List<Expression>,
    val id: String? = null,
    val needRange: Boolean,
    override val position: Position? = null
) : LoopHeader {
    override fun validate() {

    }
}

class WhileLoopHeader(
    val expressions: List<Expression>,
    override val position: Position? = null
) : LoopHeader {
    override fun validate() {

    }
}

//data class LoopHeader(val expressions: List<Expression>,
//                      override val position: Position? = null): Statement

data class ReturnStatement(
    val expression: Expression,
    override val position: Position? = null
) : Statement {
    override fun validate() {

    }
}

data class BreakStatement(override val position: Position? = null) : Statement {
    override fun validate() {

    }
}

data class PrintStatement(
    val expressions: List<Expression>,
    override val position: Position? = null
) : Statement {
    override fun validate() {

    }
}


/**
 * Functions and function types
 */
data class FunctionType(
    val types: List<Type>,
    override val position: Position? = null
) : Type {
    override fun toString(): String {
        val paramList = types.subList(0, types.size - 2)
        var params = ""
        val returnT = types.last()
        paramList.forEach { params += it.typeName() + ", " }
        return "(${params.substring(0, params.length - 1)})->$returnT)"
    }
}

data class Function(
    val body: Body,
    val parameters: List<Parameter>,
    var type: Type? = null,
    override val position: Position? = null
) : Expression {
    init {
        globalSymbolTable.putAll(localSymbolTable)
    }
    override fun validate() {
        @Suppress("UNCHECKED_CAST")
        val copy = globalSymbolTable.clone() as HashMap<String, Node>
        parameters.forEach {
            localSymbolTable[it.parName] = it
        }

        body.validate()
        if (body.expression != null) {
            val bodyExpressionType = body.expression.type()
            if (type == null) type = bodyExpressionType
            else if (bodyExpressionType?.typeName() != type?.typeName()) {
                errors.add(
                    Error(
                        "Function return type $bodyExpressionType doesn't match with declared type $type",
                        position?.start!!
                    )
                )
            }
        }

        if (type == null) type = AnyType()
        localSymbolTable.clear()
        globalSymbolTable.clear()
        globalSymbolTable = copy

    }

    override fun type(): Type? {
        validate()
        val bodyExpressionType = body.expression?.type()
        if (bodyExpressionType != null && type == null) {
            type = bodyExpressionType
        }
        val types = LinkedList<Type>()
        parameters.forEach { parameter -> types.add(parameter.type) }
        if(type != null)types.add(type as Type)
        else types.add(AnyType())
        return FunctionType(types)
    }
}

data class Parameter(
    val parName: String,
    val type: Type,
    override val position: Position? = null
) : Expression {
    override fun validate() {

    }

    override fun type(): Type? {
        return type
    }
}

data class Body(
    val statements: List<Statement>? = null,
    val expression: Expression? = null,
    override val position: Position? = null
) : Expression {
    override fun validate() {
        statements?.forEach { statement -> statement.validate() }
    }

    override fun type(): Type? {
        return expression?.type()
    }
}


/**
 * Arrays
 */
data class ArrayType(
    val type: Type,
    override val position: Position? = null
) : Type {
    override fun toString(): String {
        return "array[$type]"
    }
}


data class Array(
    val expressions: List<Expression>,
    override val position: Position? = null
) : Expression {
    override fun validate() {
        expressions.forEach { it.validate() }
    }

    override fun type(): Type? {
        return expressions[0].type()
    }
}


/**
 * Maps
 */
data class MapType(
    val types: List<Type>,
    override val position: Position? = null
) : Type {
    override fun toString(): String {
        return "map{${types[0]},${types[1]}}"
    }
}

data class Map(
    val pairs: List<Pair>,
    override val position: Position? = null
) : Expression {
    override fun validate() {
        pairs.forEach { it.validate() }
    }

    override fun type(): Type? {
        return pairs[0].type()
    }
}

data class Pair(
    val expressions: List<Expression>,
    override val position: Position? = null
) : Expression {
    override fun validate() {
        expressions.forEach { it.validate() }
    }

    override fun type(): Type? {
        return TupleType(listOf(expressions[0].type() as Type, expressions[1].type() as Type))
    }
}


/**
 * Tuples
 */
data class TupleType(
    val types: List<Type>,
    override val position: Position? = null
) : Type {
    override fun toString(): String {
        var tupleType = ""
        types.forEach { type ->
            tupleType += type.toString() + ","
        }
        return "tuple(${tupleType.substring(0, tupleType.length - 2)})"
    }
}

data class Tuple(
    val elements: List<TupleElement>,
    override val position: Position? = null
) : Expression {
    override fun validate() {
        elements.forEach { it.validate() }
    }

    override fun type(): Type? {
        val types = LinkedList<Type>()
        elements.forEach { element -> types.add(element.type() as Type) }
        return TupleType(types)
    }
}

data class TupleElement(
    val name: String, val expression: Expression,
    override val position: Position? = null
) : Expression {
    override fun validate() {
        expression.validate()
    }

    override fun type(): Type? {
        return expression.type()
    }
}