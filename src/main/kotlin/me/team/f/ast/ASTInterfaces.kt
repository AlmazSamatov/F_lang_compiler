package me.team.f.ast

interface Node {
    val position: Position?
}

data class LineCol(val line: Int, val col: Int)

data class Position(val start: LineCol, val end: LineCol)

fun getPos(startLine: Int, startCol: Int, endLine: Int, endCol: Int): Position {
    val start = LineCol(startLine, startCol)
    val end = LineCol(endLine, endCol)
    return Position(start, end)
}

/**
 * Grammar part
 */

data class Program(val declarations: List<Declaration>,
                   override val position: Position? = null): Node

interface Declaration: Node

interface Expression: Node

interface Statement: Node

interface Type: Node

interface Primary: Node


/**
 * Declaration
 */

data class VarDeclaration(val varName: String, val value: Expression, val type: Type? = null, override val position: Position? = null): Declaration, Statement

data class BinaryOperation(val left:Expression,
                           val right: Expression,
                           val operatorSign: String,
                           override val position: Position? = null): Expression

/**
 * Secondary
 */

data class Call(val secondary: Expression,
                val expressions: List<Expression>,
                override val position: Position? = null): Expression

data class ElementOf(val varName: Expression,
                     val index: Expression,
                     override val position: Position? = null): Expression

data class NamedTupleElement(val secondary: Expression,
                             val name: String,
                             override val position: Position? = null): Expression

data class UnnamedTupleElement(val secondary: Expression,
                               val fieldNum: String,
                               override val position: Position? = null): Expression

/**
 * Primary
 */
data class Conditional(val predicate: Expression,
                       val thenExpr: Expression,
                       val elseExpr: Expression,
                       override val position: Position? = null): Expression

data class FunctionExpression(val function: Primary,
                              override val position: Position? = null): Expression

data class ArrayExpression(val array: Primary,
                           override val position: Position? = null): Expression

data class TupleExpression(val tuple: Primary,
                           override val position: Position? = null): Expression

data class MapExpression(val map: Primary,
                         override val position: Position? = null): Expression

data class ParenExpression(val expression: Expression,
                           override val position: Position? = null) : Expression

/**
 * Types
 */

data class BooleanType(override val position: Position? = null): Type

data class IntegerType(override val position: Position? = null): Type

data class RealType(override val position: Position? = null): Type

data class RationalType(override val position: Position? = null): Type

data class ComplexType(override val position: Position? = null): Type

data class StringType(override val position: Position? = null): Type

/**
 * Expressions
 */

data class VarReference(val name: String,
                        override val position: Position? = null): Expression

data class BoolLit(val value: String,
                   override val position: Position? = null): Expression

data class IntLit(val value: String,
                  override val position: Position? = null): Expression

data class RealLit(val value: String,
                  override val position: Position? = null): Expression

data class RatLit(val value: String,
                  override val position: Position? = null): Expression

data class CompLit(val value: String,
                  override val position: Position? = null): Expression

data class StrLit(val value: String,
                  override val position: Position? = null): Expression

interface BinaryExpression: Expression {
    val left: Expression
    val right: Expression
}

data class SumExpression(override val left: Expression,
                         override val right: Expression,
                         override val position: Position? = null): BinaryExpression

data class SubExpression(override val left: Expression,
                         override val right: Expression,
                         override val position: Position? = null): BinaryExpression

data class MultExpression(override val left: Expression,
                         override val right: Expression,
                         override val position: Position? = null): BinaryExpression

data class DivExpression(override val left: Expression,
                         override val right: Expression,
                         override val position: Position? = null): BinaryExpression

data class LessExpression(override val left: Expression,
                         override val right: Expression,
                         override val position: Position? = null): BinaryExpression

data class GreaterExpression(override val left: Expression,
                         override val right: Expression,
                         override val position: Position? = null): BinaryExpression

data class LessEqExpression(override val left: Expression,
                         override val right: Expression,
                         override val position: Position? = null): BinaryExpression

data class GreaterEqExpression(override val left: Expression,
                         override val right: Expression,
                         override val position: Position? = null): BinaryExpression

data class EqualExpression(override val left: Expression,
                         override val right: Expression,
                         override val position: Position? = null): BinaryExpression

data class NotEqExpression(override val left: Expression,
                         override val right: Expression,
                         override val position: Position? = null): BinaryExpression

data class AndExpression(override val left: Expression,
                         override val right: Expression,
                         override val position: Position? = null): BinaryExpression

data class OrExpression(override val left: Expression,
                         override val right: Expression,
                         override val position: Position? = null): BinaryExpression

data class XorExpression(override val left: Expression,
                         override val right: Expression,
                         override val position: Position? = null): BinaryExpression

/**
 * Statements
 */

data class Assignment(val secondary: Expression,
                      val expression: Expression,
                      override val position: Position? = null): Statement

data class FunctionCall(val secondary: Expression,
                        val expressions: List<Expression>,
                        override val position: Position? = null): Statement

data class IfStatement(val expression: Expression,
                       val statements: List<Statement>,
                       override val position: Position? = null): Statement

data class LoopStatement(val loopHeader: LoopHeader,
                         val statements: List<Statement>,
                         override val position: Position? = null) : Statement

data class LoopHeader(val expressions: List<Expression>,
                override val position: Position? = null): Statement

data class ReturnStatement(val expression: Expression,
                           override val position: Position? = null): Statement

data class BreakStatement(override val position: Position? = null): Statement

data class PrintStatement(val expressions: List<Expression>,
                 override val position: Position? = null): Statement


/**
 * Functions and function types
 */
data class FunctionType(val types: List<Type>,
                        override val position: Position? = null): Type

data class Function(val body: Body,
                    val parameters: List<Parameter>,
                    val type: Type,
                    override val position: Position? = null): Primary

data class Parameter(val type: Type,
                     override val position: Position? = null): Expression

data class Body(val statements: List<Statement>? = null,
                val expression: Expression? = null,
                override val position: Position? = null): Expression


/**
 * Arrays
 */
data class ArrayType(val type: Type,
                     override val position: Position? = null): Type


data class Array(val expressions: List<Expression>,
                 override val position: Position? = null): Primary


/**
 * Maps
 */
data class MapType(val types: List<Type>,
                   override val position: Position? = null) : Type

data class Map(val pairs: List<Pair>,
               override val position: Position? = null): Primary

data class Pair(val expressions: List<Expression>,
                override val position: Position? = null): Expression


/**
 * Tuples
 */
data class TupleType(val type: List<Type>,
                     override val position: Position? = null): Type

data class Tuple(val elements: List<TupleElement>,
                 override val position: Position? = null): Primary

data class TupleElement(val type: Expression,
                        override val position: Position? = null): Expression















