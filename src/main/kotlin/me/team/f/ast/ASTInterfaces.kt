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

interface Declaration: Node {}

interface Expression: Node {}

interface Statement: Node {}

interface Type: Node {}

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

data class IntLit(val value: String,
                  override val position: Position? = null) : Expression

data class RealLit(val value: String,
                  override val position: Position? = null) : Expression

data class RatLit(val value: String,
                  override val position: Position? = null) : Expression

data class CompLit(val value: String,
                  override val position: Position? = null) : Expression

data class StrLit(val value: String,
                  override val position: Position? = null) : Expression

data class Conditional(val predicateExpr: Expression,
                       val thenExpr: Expression,
                       val elseExpr: Expression,
                       override val position: Position? = null): Expression

//data class Function(val params: String,
//                    val type: String? = null,
//                    val body: String,
//                    override val position: Position? = null): Expression

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

data class Assignment(val varName: String,
                      val value: Expression,
                      override val position: Position? = null) : Statement

//data class FunctionCall(,
//                        override val position: Position? = null): Statement

data class If(val predicate: Expression, val body: Statement,       //TODO(check)
              override val position: Position? = null): Statement

data class Loop(val header: Expression, val statement: Statement,   //TODO(check)
                override val position: Position? = null): Statement

data class Return(override val position: Position? = null): Statement

data class Break(override val position: Position? = null): Statement

data class Print(val value: Expression,
                 override val position: Position? = null): Statement















