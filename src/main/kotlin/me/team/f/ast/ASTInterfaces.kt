package me.team.f.ast

import kotlin.reflect.KParameter
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor

interface Node {
    var position: Position?
}

data class LineCol(val line: Int, val col: Int)

data class Position(val start: LineCol, val end: LineCol)

//fun Node.isBefore(other: Node) : Boolean =
//    position!!.start.isBefore(other.position!!.start)

//fun LineCol.isBefore(other: LineCol) : Boolean =
//    line < other.line || (line == other.line && col < other.col)

//fun getPos(startLine: Int, startCol: Int, endLine: Int, endCol: Int): Position {
//    val start = LineCol(startLine, startCol)
//    val end = LineCol(endLine, endCol)
//    return Position(start, end)
//}

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
fun <T: Node> Node.specificProcess(classF: Class<T>, operation: (T) -> Unit) {
    process {
        if (classF.isInstance(it)) {
            operation(it as T)
        }
    }
}

//fun Node.transform(operation: (Node) -> Node) : Node {
//    operation(this)
//    val changes = HashMap<String, Any>()
//    this.javaClass.kotlin.memberProperties.forEach { p ->
//        val v = p.get(this)
//        when (v) {
//            is Node -> {
//                val newValue = v.transform(operation)
//                if (newValue != v) changes[p.name] = newValue
//            }
//            is Collection<*> -> {
//                val newValue = v.map { if (it is Node) it.transform(operation) else it }
//                if (newValue != v) changes[p.name] = newValue
//            }
//        }
//    }
//    var instanceToTransform = this
//    if (!changes.isEmpty()) {
//        val constructor = this.javaClass.kotlin.primaryConstructor!!
//        val params = HashMap<KParameter, Any?>()
//        constructor.parameters.forEach { param ->
//            if (changes.containsKey(param.name)) {
//                params[param] = changes[param.name]
//            } else {
//                params[param] = this.javaClass.kotlin.memberProperties.find { param.name == it.name }!!.get(this)
//            }
//        }
//        instanceToTransform = constructor.callBy(params)
//    }
//    return operation(instanceToTransform)
//}

/**
 * Grammar part
 */

data class Program(val declarations: List<Declaration>,
                   override var position: Position? = null): Node

interface Declaration: Node

interface Expression: Node

interface Statement: Node

interface Type: Node


/**
 * Declaration
 */

data class VarDeclaration(val varName: String,
                          val type: Type? = null,
                          val value: Expression,
                          override var position: Position? = null): Declaration, Statement

/**
 * Secondary
 */

data class Call(val secondary: Expression,
                val expressions: List<Expression>,
                override var position: Position? = null): Expression

data class ElementOf(val varName: Expression,
                     val index: Expression,
                     override var position: Position? = null): Expression

data class NamedTupleElement(val secondary: Expression,
                             val fieldName: String,
                             override var position: Position? = null): Expression

data class UnnamedTupleElement(val secondary: Expression,
                               val fieldNum: String,
                               override var position: Position? = null): Expression

/**
 * Primary
 */
data class Conditional(val predicate: Expression,
                       val thenExpr: Expression,
                       val elseExpr: Expression,
                       override var position: Position? = null): Expression

/**
 * Types
 */

data class UndefinedType(override var position: Position? = null): Type

data class BooleanType(override var position: Position? = null): Type

data class IntegerType(override var position: Position? = null): Type

data class RealType(override var position: Position? = null): Type

data class RationalType(override var position: Position? = null): Type

data class ComplexType(override var position: Position? = null): Type

data class StringType(override var position: Position? = null): Type

/**
 * Expressions
 */

data class VarReference(val name: String,
                        override var position: Position? = null): Expression

data class BoolLit(val value: String,
                   override var position: Position? = null): Expression

data class IntLit(val value: String,
                  override var position: Position? = null): Expression

data class RealLit(val value: String,
                   override var position: Position? = null): Expression

data class RatLit(val value: String,
                  override var position: Position? = null): Expression

data class CompLit(val value: String,
                   override var position: Position? = null): Expression

data class StrLit(val value: String,
                  override var position: Position? = null): Expression

interface BinaryExpression: Expression {
    val left: Expression
    val right: Expression
}

data class SumExpression(override val left: Expression,
                         override val right: Expression,
                         override var position: Position? = null): BinaryExpression

data class SubExpression(override val left: Expression,
                         override val right: Expression,
                         override var position: Position? = null): BinaryExpression

data class MultExpression(override val left: Expression,
                          override val right: Expression,
                          override var position: Position? = null): BinaryExpression

data class DivExpression(override val left: Expression,
                         override val right: Expression,
                         override var position: Position? = null): BinaryExpression

data class LessExpression(override val left: Expression,
                          override val right: Expression,
                          override var position: Position? = null): BinaryExpression

data class GreaterExpression(override val left: Expression,
                             override val right: Expression,
                             override var position: Position? = null): BinaryExpression

data class LessEqExpression(override val left: Expression,
                            override val right: Expression,
                            override var position: Position? = null): BinaryExpression

data class GreaterEqExpression(override val left: Expression,
                               override val right: Expression,
                               override var position: Position? = null): BinaryExpression

data class EqualExpression(override val left: Expression,
                           override val right: Expression,
                           override var position: Position? = null): BinaryExpression

data class NotEqExpression(override val left: Expression,
                           override val right: Expression,
                           override var position: Position? = null): BinaryExpression

data class AndExpression(override val left: Expression,
                         override val right: Expression,
                         override var position: Position? = null): BinaryExpression

data class OrExpression(override val left: Expression,
                        override val right: Expression,
                        override var position: Position? = null): BinaryExpression

data class XorExpression(override val left: Expression,
                         override val right: Expression,
                         override var position: Position? = null): BinaryExpression

/**
 * Statements
 */

data class Assignment(val secondary: Expression,
                      val expression: Expression,
                      override var position: Position? = null): Statement

data class FunctionCall(val secondary: Expression,
                        val expressions: List<Expression>,
                        override var position: Position? = null): Statement

data class IfStatement(val predicate: Expression,
                       val thenStatements: List<Statement>,
                       val elseStatements: List<Statement>,
                       override var position: Position? = null): Statement

data class LoopStatement(val loopHeader: LoopHeader,
                         val statements: List<Statement>,
                         override var position: Position? = null): Statement

interface LoopHeader: Statement

data class ForLoopHeader(val expressions: List<Expression>,
                         val id: String? = null,
                         val needRange: Boolean,
                         override var position: Position? = null): LoopHeader

data class WhileLoopHeader(val expressions: List<Expression>,
                           override var position: Position? = null): LoopHeader

//data class LoopHeader(val expressions: List<Expression>,
//                      override val position: Position? = null): Statement

data class ReturnStatement(val expression: Expression,
                           override var position: Position? = null): Statement

data class BreakStatement(override var position: Position? = null): Statement

data class PrintStatement(val expressions: List<Expression>,
                          override var position: Position? = null): Statement


/**
 * Functions and function types
 */
data class FunctionType(val types: List<Type>,
                        override var position: Position? = null): Type

data class Function(val body: Body,
                    val parameters: List<Parameter>,
                    val type: Type? = null,
                    override var position: Position? = null): Expression

data class Parameter(val parName: String,
                     val type: Type,
                     override var position: Position? = null): Expression

data class Body(val statements: List<Statement>? = null,
                val expression: Expression? = null,
                override var position: Position? = null): Expression


/**
 * Arrays
 */
data class ArrayType(val type: Type,
                     override var position: Position? = null): Type


data class Array(val expressions: List<Expression>,
                 override var position: Position? = null): Expression


/**
 * Maps
 */
data class MapType(val types: List<Type>,
                   override var position: Position? = null) : Type

data class Map(val pairs: List<Pair>,
               override var position: Position? = null): Expression

data class Pair(val expressions: List<Expression>,
                override var position: Position? = null): Expression


/**
 * Tuples
 */
data class TupleType(val type: List<Type>,
                     override var position: Position? = null): Type

data class Tuple(val elements: List<TupleElement>,
                 override var position: Position? = null): Expression

data class TupleElement(val name: String, val expression: Expression,
                        override var position: Position? = null): Expression


