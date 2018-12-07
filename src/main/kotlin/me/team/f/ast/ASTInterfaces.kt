package me.team.f.ast

import java.util.*
import kotlin.collections.HashMap
import kotlin.reflect.KParameter
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor

//data class Error(val message: String, val position: LineCol)

var errors = LinkedList<Error>()
var globalSymbolTable = HashMap<String, Node>()

interface Node {
    fun validate(globalSymbolTable: HashMap<String, Node>)
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
    override fun validate(globalSymbolTable: HashMap<String, Node>) {
        declarations.forEach { it.validate(globalSymbolTable) }
    }

//    val globalSymbolTable = HashMap<String, Node>()
    fun validate(): List<Error> {
        validate(globalSymbolTable)
        return errors
    }
}

interface Declaration : Node {
    fun type(globalSymbolTable: HashMap<String, Node>): Type
    override fun validate(globalSymbolTable: HashMap<String, Node>)
}

interface Expression : Node {
    fun type(globalSymbolTable: HashMap<String, Node>): Type
    override fun validate(globalSymbolTable: HashMap<String, Node>)
}

interface Statement : Node {
    override fun validate(globalSymbolTable: HashMap<String, Node>)
    fun type(globalSymbolTable: HashMap<String, Node>): Type {
        return AnyType()
    }
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
    override fun validate(globalSymbolTable: HashMap<String, Node>) {
        if (globalSymbolTable.containsKey(varName)) {
            errors.add(
                Error(
                    "A variable named '$varName' has been already declared at ${globalSymbolTable[varName]!!.position!!.start}",
                    position!!.start
                )
            )
        } else {
            val valueType = value.type(globalSymbolTable)
            if (type == null) {
                type = valueType
            } else {
                if (type?.typeName() != valueType.typeName()) {
                    errors.add(
                        Error(
                            "Declared type \"$type\" doesn't match with value type \"$valueType\"",
                            position?.start!!
                        )
                    )
                }
            }
            globalSymbolTable[varName] = this
        }
//        value.validate(globalSymbolTable)
    }


    override fun type(globalSymbolTable: HashMap<String, Node>): Type {
        return type as Type
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
    override fun validate(globalSymbolTable: HashMap<String, Node>) {
        secondary as VarReference
        if (!globalSymbolTable.containsKey(secondary.name)) {
            errors.add(
                Error(
                    "Function ${secondary.name} is not declared yet.",
                    secondary.position?.start!!
                )
            )
        } else {
            val expression = globalSymbolTable[secondary.name]

            if (expression is Function)
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
                    if (expression.parameters[i].type(globalSymbolTable) != expressions[i].type(globalSymbolTable)) {
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
                                function.type(globalSymbolTable)
                                } is " +
                                        "not same as in function declaration parameter with typeName ${expression.parameters[i].parName} of type ${
                                        expression.parameters[i].type(globalSymbolTable)
                                        }",
                                expressions[i].position?.start!!
                            )
                        )
                    }
                }
            }

        }
    }


    override fun type(globalSymbolTable: HashMap<String, Node>): Type {
        secondary as VarReference
        val callObject = globalSymbolTable[secondary.name] as VarDeclaration
        return (callObject.type as FunctionType).types.last()
    }
}

data class ElementOf(
    val varName: Expression,
    val index: Expression,
    override val position: Position? = null
) : Expression {
    override fun validate(globalSymbolTable: HashMap<String, Node>) {
        varName.validate(globalSymbolTable)
        index.validate(globalSymbolTable)
    }

    override fun type(globalSymbolTable: HashMap<String, Node>): Type {
        return varName.type(globalSymbolTable)
    }
}

data class NamedTupleElement(
    val secondary: Expression,
    val fieldName: String,
    override val position: Position? = null
) : Expression {
    override fun validate(globalSymbolTable: HashMap<String, Node>) {
        secondary.validate(globalSymbolTable)
    }

    override fun type(globalSymbolTable: HashMap<String, Node>): Type {
        return secondary.type(globalSymbolTable)
    }
}

data class UnnamedTupleElement(
    val secondary: Expression,
    val fieldNum: String,
    override val position: Position? = null
) : Expression {
    override fun validate(globalSymbolTable: HashMap<String, Node>) {
        secondary.validate(globalSymbolTable)
    }

    override fun type(globalSymbolTable: HashMap<String, Node>): Type {
        return secondary.type(globalSymbolTable)
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
    override fun validate(globalSymbolTable: HashMap<String, Node>) {
        predicate.validate(globalSymbolTable)
        thenExpr.validate(globalSymbolTable)
        elseExpr.validate(globalSymbolTable)
    }

    override fun type(globalSymbolTable: HashMap<String, Node>): Type {
        val thenType = thenExpr.type(globalSymbolTable)
        val elseType = elseExpr.type(globalSymbolTable)

        return if (thenType.typeName() == elseType.typeName()) thenType else AnyType()
    }
}

/**
 * Types
 */

data class BooleanType(override val position: Position? = null) : Type {
    override fun validate(globalSymbolTable: HashMap<String, Node>) {

    }

    override fun toString(): String {
        return "boolean"
    }

}

data class IntegerType(override val position: Position? = null) : Type {
    override fun validate(globalSymbolTable: HashMap<String, Node>) {

    }

    override fun toString(): String {
        return "integer"
    }
}

data class RealType(override val position: Position? = null) : Type {
    override fun validate(globalSymbolTable: HashMap<String, Node>) {

    }

    override fun toString(): String {
        return "real"
    }
}

data class RationalType(override val position: Position? = null) : Type {
    override fun validate(globalSymbolTable: HashMap<String, Node>) {

    }

    override fun toString(): String {
        return "rational"
    }
}

data class ComplexType(override val position: Position? = null) : Type {
    override fun validate(globalSymbolTable: HashMap<String, Node>) {

    }

    override fun toString(): String {
        return "complex"
    }
}

data class StringType(override val position: Position? = null) : Type {
    override fun validate(globalSymbolTable: HashMap<String, Node>) {

    }

    override fun toString(): String {
        return "string"
    }
}

data class AnyType(override val position: Position? = null) : Type {
    override fun validate(globalSymbolTable: HashMap<String, Node>) {

    }

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
    override fun validate(globalSymbolTable: HashMap<String, Node>) {
        if (!globalSymbolTable.containsKey(name)) {
            errors.add(Error("There is no variable named '$name'", position!!.start))
        } else {
            val node = globalSymbolTable[name]
            if (node is VarDeclaration) {
                if(isBefore(node))
                errors.add(
                    Error(
                        "You cannot refer to variable '$name' before its declaration",
                        position!!.start
                    )
                )
            } else if (isBefore(node as Parameter)){
                errors.add(
                    Error(
                        "You cannot refer to variable '$name' before its declaration",
                        position!!.start
                    )
                )
            }
        }
    }

    override fun type(globalSymbolTable: HashMap<String, Node>): Type {
        val node = globalSymbolTable[name]
        return when (node) {
            is VarDeclaration -> {
                node.type(globalSymbolTable)
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
    override fun validate(globalSymbolTable: HashMap<String, Node>) {
    }

    override fun type(globalSymbolTable: HashMap<String, Node>): Type {
        return BooleanType()
    }
}

data class IntLit(
    val value: String,
    override val position: Position? = null
) : Expression {
    override fun validate(globalSymbolTable: HashMap<String, Node>) {

    }

    override fun type(globalSymbolTable: HashMap<String, Node>): Type {
        return IntegerType()
    }
}

data class RealLit(
    val value: String,
    override val position: Position? = null
) : Expression {
    override fun validate(globalSymbolTable: HashMap<String, Node>) {

    }

    override fun type(globalSymbolTable: HashMap<String, Node>): Type {
        return RealType()
    }
}

data class RatLit(
    val value: String,
    override val position: Position? = null
) : Expression {
    override fun validate(globalSymbolTable: HashMap<String, Node>) {

    }

    override fun type(globalSymbolTable: HashMap<String, Node>): Type {
        return RationalType()
    }
}

data class CompLit(
    val value: String,
    override val position: Position? = null
) : Expression {
    override fun validate(globalSymbolTable: HashMap<String, Node>) {

    }

    override fun type(globalSymbolTable: HashMap<String, Node>): Type {
        return ComplexType()
    }
}

data class StrLit(
    val value: String,
    override val position: Position? = null
) : Expression {
    override fun validate(globalSymbolTable: HashMap<String, Node>) {

    }

    override fun type(globalSymbolTable: HashMap<String, Node>): Type {
        return StringType()
    }
}

interface BinaryExpression : Expression {
    val left: Expression
    val right: Expression
    fun leftType(globalSymbolTable: HashMap<String, Node>): Type {
        return left.type(globalSymbolTable)
    }

    fun rightType(globalSymbolTable: HashMap<String, Node>): Type {
        return right.type(globalSymbolTable)
    }

    override fun validate(globalSymbolTable: HashMap<String, Node>) {
        left.validate(globalSymbolTable)
        right.validate(globalSymbolTable)
    }
}

data class SumExpression(
    override val left: Expression,
    override val right: Expression,
    override val position: Position? = null
) : BinaryExpression {
    override fun validate(globalSymbolTable: HashMap<String, Node>) {

    }

    override fun type(globalSymbolTable: HashMap<String, Node>): Type {
        val left = leftType(globalSymbolTable)
        val right = rightType(globalSymbolTable)
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
                AnyType()
            }
        }
    }
}

data class SubExpression(
    override val left: Expression,
    override val right: Expression,
    override val position: Position? = null
) : BinaryExpression {

    override fun type(globalSymbolTable: HashMap<String, Node>): Type {
        val left = leftType(globalSymbolTable)
        val right = rightType(globalSymbolTable)
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
                AnyType()
            }
            typePair.first is ArrayType && typePair.second is ArrayType -> {
                errors.add(
                    Error(
                        "Unsupported operator - for expressions of type array.",
                        position?.start!!
                    )
                )
                AnyType()
            }
            else -> {
                errors.add(
                    Error(
                        "Operator - cannot be applied on operands of types ($left, $right)",
                        position?.start!!
                    )
                )
                AnyType()
            }
        }
    }
}

data class MultExpression(
    override val left: Expression,
    override val right: Expression,
    override val position: Position? = null
) : BinaryExpression {
    override fun type(globalSymbolTable: HashMap<String, Node>): Type {
        val left = leftType(globalSymbolTable)
        val right = rightType(globalSymbolTable)
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
    override fun type(globalSymbolTable: HashMap<String, Node>): Type {
        val left = leftType(globalSymbolTable)
        val right = rightType(globalSymbolTable)
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
    override fun type(globalSymbolTable: HashMap<String, Node>): Type {
        val left = leftType(globalSymbolTable)
        val right = rightType(globalSymbolTable)
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
    override fun type(globalSymbolTable: HashMap<String, Node>): Type {
        val left = leftType(globalSymbolTable)
        val right = rightType(globalSymbolTable)
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
    override fun type(globalSymbolTable: HashMap<String, Node>): Type {
        val left = leftType(globalSymbolTable)
        val right = rightType(globalSymbolTable)
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
    override fun type(globalSymbolTable: HashMap<String, Node>): Type {
        val left = leftType(globalSymbolTable)
        val right = rightType(globalSymbolTable)
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
    override fun type(globalSymbolTable: HashMap<String, Node>): Type {
        val left = leftType(globalSymbolTable)
        val right = rightType(globalSymbolTable)
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
    override fun type(globalSymbolTable: HashMap<String, Node>): Type {
        val left = leftType(globalSymbolTable)
        val right = rightType(globalSymbolTable)
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
    override fun type(globalSymbolTable: HashMap<String, Node>): Type {
        val left = leftType(globalSymbolTable)
        val right = rightType(globalSymbolTable)
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
    override fun type(globalSymbolTable: HashMap<String, Node>): Type {
        val left = leftType(globalSymbolTable)
        val right = rightType(globalSymbolTable)
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
    override fun type(globalSymbolTable: HashMap<String, Node>): Type {
        val left = leftType(globalSymbolTable)
        val right = rightType(globalSymbolTable)
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
    override fun validate(globalSymbolTable: HashMap<String, Node>) {
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
        val left = secondary.type(globalSymbolTable)
        val right = expression.type(globalSymbolTable)
        if (left.typeName() != right.typeName()) {
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
    override fun validate(globalSymbolTable: HashMap<String, Node>) {
        if (!globalSymbolTable.containsKey((secondary as VarReference).name)) {
            errors.add(
                Error(
                    "Function ${secondary.name} is not declared yet.",
                    secondary.position?.start!!
                )
            )
            return
        }
        secondary.validate(globalSymbolTable)

        val expression = globalSymbolTable[secondary.name] as VarDeclaration
        val functionType = expression.type(globalSymbolTable)
        if (functionType is FunctionType) {

            if (expressions.size != functionType.types.size - 1) {
                errors.add(
                    Error(
                        "Wrong number of parameters when call function ${secondary.name}. ${expressions.size}!=${functionType.types.size - 1}",
                        secondary.position?.start!!
                    )
                )
            } else {
                for (i in 0 until expressions.size) {
                    val paramType = expressions[i].type(globalSymbolTable)
                    if (functionType.types[i].typeName() != paramType.typeName()) {
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
    override fun validate(globalSymbolTable: HashMap<String, Node>) {
        if (predicate.type(globalSymbolTable) != BooleanType()) {
            errors.add(Error("Predicate expression should return boolean value.", position?.start!!))
        }
        predicate.validate(globalSymbolTable)

        thenStatements.forEach { it.validate(globalSymbolTable) }
        elseStatements.forEach { it.validate(globalSymbolTable) }
    }
//    override fun type(globalSymbolTable: HashMap<String, Node>):Type{
//        val thenType = thenStatements[0].type(globalSymbolTable)
//        val elseType = elseStatements[0].type(globalSymbolTable)
//        return if (thenStatements.size == elseStatements.size && elseStatements.size == 1 && thenType.typeName() == elseType.typeName()) {
//            thenType
//        }
//        else AnyType()
//    }
}

data class LoopStatement(
    val loopHeader: LoopHeader,
    val statements: List<Statement>,
    override val position: Position? = null
) : Statement {
    override fun validate(globalSymbolTable: HashMap<String, Node>) {
        loopHeader.validate(globalSymbolTable)
        statements.forEach { statement -> statement.validate(globalSymbolTable) }
    }
}

interface LoopHeader : Statement

data class ForLoopHeader(
    val expressions: List<Expression>,
    val id: String? = null,
    val needRange: Boolean,
    override val position: Position? = null
) : LoopHeader {
    override fun validate(globalSymbolTable: HashMap<String, Node>) {

    }
}

class WhileLoopHeader(
    val expressions: List<Expression>,
    override val position: Position? = null
) : LoopHeader {
    override fun validate(globalSymbolTable: HashMap<String, Node>) {

    }
}

//data class LoopHeader(val expressions: List<Expression>,
//                      override val position: Position? = null): Statement

data class ReturnStatement(
    val expression: Expression,
    override val position: Position? = null
) : Statement {
    override fun validate(globalSymbolTable: HashMap<String, Node>) {

    }

    override fun type(globalSymbolTable: HashMap<String, Node>): Type {
        return expression.type(globalSymbolTable)
    }
}

data class BreakStatement(override val position: Position? = null) : Statement {
    override fun validate(globalSymbolTable: HashMap<String, Node>) {

    }
}

data class PrintStatement(
    val expressions: List<Expression>,
    override val position: Position? = null
) : Statement {
    override fun validate(globalSymbolTable: HashMap<String, Node>) {

    }
}


/**
 * Functions and function types
 */
data class FunctionType(
    val types: List<Type>,
    override val position: Position? = null
) : Type {
    override fun validate(globalSymbolTable: HashMap<String, Node>) {

    }

    override fun toString(): String {
        val paramList = types.subList(0, types.size - 1)
        var params = ""
        val returnT = types.last()
        paramList.forEach { params += it.typeName() + ", " }
        return "($params)->$returnT)"
    }
}

data class Function(
    val body: Body,
    val parameters: List<Parameter>,
    var type: Type? = null,
    override val position: Position? = null
) : Expression {
    override fun validate(globalSymbolTable: HashMap<String, Node>) {
        parameters.forEach {
            globalSymbolTable[it.parName] = it
        }

        body.validate(globalSymbolTable)
        if (body.expression != null) {
            val bodyExpressionType = body.expression.type(globalSymbolTable)
            if (type == null) type = bodyExpressionType
            else if (bodyExpressionType.typeName() != type?.typeName()) {
                errors.add(
                    Error(
                        "Function return type $bodyExpressionType doesn't match with declared type $type",
                        position?.start!!
                    )
                )
            }
        }

        if (type == null) type = AnyType()

    }

    override fun type(globalSymbolTable: HashMap<String, Node>): Type {
        validate(globalSymbolTable)
        val bodyExpressionType = body.expression?.type(globalSymbolTable)
        if (bodyExpressionType != null && type == null) {
            type = bodyExpressionType
        }
        val types = LinkedList<Type>()
        parameters.forEach { parameter -> types.add(parameter.type) }
        if (type != null) types.add(type as Type)
        else types.add(AnyType())
        return FunctionType(types)
    }
}

data class Parameter(
    val parName: String,
    val type: Type,
    override val position: Position? = null
) : Expression {
    override fun validate(globalSymbolTable: HashMap<String, Node>) {

    }

    override fun type(globalSymbolTable: HashMap<String, Node>): Type {
        return type
    }
}

data class Body(
    val statements: List<Statement>? = null,
    val expression: Expression? = null,
    override val position: Position? = null
) : Expression {
    override fun validate(globalSymbolTable: HashMap<String, Node>) {
        statements?.forEach { statement -> statement.validate(globalSymbolTable) }
    }

    override fun type(globalSymbolTable: HashMap<String, Node>): Type {
        return expression?.type(globalSymbolTable) ?: AnyType()
    }
}


/**
 * Arrays
 */
data class ArrayType(
    val type: Type,
    override val position: Position? = null
) : Type {
    override fun validate(globalSymbolTable: HashMap<String, Node>) {

    }

    override fun toString(): String {
        return "array[$type]"
    }
}


data class Array(
    val expressions: List<Expression>,
    override val position: Position? = null
) : Expression {
    override fun validate(globalSymbolTable: HashMap<String, Node>) {
        expressions.forEach { it.validate(globalSymbolTable) }
    }

    override fun type(globalSymbolTable: HashMap<String, Node>): Type {
        return expressions[0].type(globalSymbolTable)
    }
}


/**
 * Maps
 */
data class MapType(
    val types: List<Type>,
    override val position: Position? = null
) : Type {
    override fun validate(globalSymbolTable: HashMap<String, Node>) {

    }

    override fun toString(): String {
        return "map{${types[0]},${types[1]}}"
    }
}

data class Map(
    val pairs: List<Pair>,
    override val position: Position? = null
) : Expression {
    override fun validate(globalSymbolTable: HashMap<String, Node>) {
        pairs.forEach { it.validate(globalSymbolTable) }
    }

    override fun type(globalSymbolTable: HashMap<String, Node>): Type {
        return pairs[0].type(globalSymbolTable)
    }
}

data class Pair(
    val expressions: List<Expression>,
    override val position: Position? = null
) : Expression {
    override fun validate(globalSymbolTable: HashMap<String, Node>) {
        expressions.forEach { it.validate(globalSymbolTable) }
    }

    override fun type(globalSymbolTable: HashMap<String, Node>): Type {
        return TupleType(listOf(expressions[0].type(globalSymbolTable), expressions[1].type(globalSymbolTable)))
    }
}


/**
 * Tuples
 */
data class TupleType(
    val types: List<Type>,
    override val position: Position? = null
) : Type {
    override fun validate(globalSymbolTable: HashMap<String, Node>) {

    }

    override fun toString(): String {
        var tupleType = ""
        types.forEach { type ->
            tupleType += type.toString() + ","
        }
        return "tuple(${tupleType.substring(0, tupleType.length - 1)})"
    }
}

data class Tuple(
    val elements: List<TupleElement>,
    override val position: Position? = null
) : Expression {
    override fun validate(globalSymbolTable: HashMap<String, Node>) {
        elements.forEach { it.validate(globalSymbolTable) }
    }

    override fun type(globalSymbolTable: HashMap<String, Node>): Type {
        val types = LinkedList<Type>()
        elements.forEach { element -> types.add(element.type(globalSymbolTable)) }
        return TupleType(types)
    }
}

data class TupleElement(
    val name: String, val expression: Expression,
    override val position: Position? = null
) : Expression {
    override fun validate(globalSymbolTable: HashMap<String, Node>) {
        expression.validate(globalSymbolTable)
    }

    override fun type(globalSymbolTable: HashMap<String, Node>): Type {
        return expression.type(globalSymbolTable)
    }
}