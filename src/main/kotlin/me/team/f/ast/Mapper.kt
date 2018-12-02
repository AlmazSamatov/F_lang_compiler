package me.team.f.ast

import me.team.fproject.FLangParser.*
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.Token

interface ParseTreeToAstMapper<in PTN: ParserRuleContext, out ASTN: Node> {
    fun map(parseTreeNode: PTN): ASTN
}

fun ProgramContext.toAst(considerPosition: Boolean = false): Program = Program(
//    this.declaration().map { it.expression().toAst(considerPosition) }, toPosition(considerPosition))
    this.declaration().map { it.toAst(considerPosition) }, toPosition(considerPosition))

fun Token.startPoint() = LineCol(line, charPositionInLine)

fun Token.endPoint() = LineCol(line, charPositionInLine + text.length)

fun ParserRuleContext.toPosition(considerPosition: Boolean): Position? {
    return if (considerPosition) Position(start.startPoint(), stop.endPoint()) else null
}

fun DeclarationContext.toAst(considerPosition: Boolean = false): Declaration = VarDeclaration (
    this.ID().text, this.expression().toAst(considerPosition), toPosition(considerPosition))

fun ExpressionContext.toAst(considerPosition: Boolean = false): Expression = when (this) {
    is BinaryOperationContext -> toAst(considerPosition)
    is SecondaryExpressionContext -> secondary().toAst(considerPosition)
    else -> throw UnsupportedOperationException(this.javaClass.canonicalName)
}

fun BinaryOperationContext.toAst(considerPosition: Boolean = false): Expression = when (operatorSign().text) {
    "+" -> SumExpression(left.toAst(considerPosition), right.toAst(considerPosition), toPosition(considerPosition))
    "-" -> SubExpression(left.toAst(considerPosition), right.toAst(considerPosition), toPosition(considerPosition))
    "*" -> MultExpression(left.toAst(considerPosition), right.toAst(considerPosition), toPosition(considerPosition))
    "/" -> DivExpression(left.toAst(considerPosition), right.toAst(considerPosition), toPosition(considerPosition))
    "<" -> LessExpression(left.toAst(considerPosition), right.toAst(considerPosition), toPosition(considerPosition))
    ">" -> GreaterExpression(left.toAst(considerPosition), right.toAst(considerPosition), toPosition(considerPosition))
    "<=" -> LessEqExpression(left.toAst(considerPosition), right.toAst(considerPosition), toPosition(considerPosition))
    ">=" -> GreaterEqExpression(left.toAst(considerPosition), right.toAst(considerPosition), toPosition(considerPosition))
    "=" -> EqualExpression(left.toAst(considerPosition), right.toAst(considerPosition), toPosition(considerPosition))
    "/=" -> NotEqExpression(left.toAst(considerPosition), right.toAst(considerPosition), toPosition(considerPosition))
    "&" -> AndExpression(left.toAst(considerPosition), right.toAst(considerPosition), toPosition(considerPosition))
    "|" -> OrExpression(left.toAst(considerPosition), right.toAst(considerPosition), toPosition(considerPosition))
    "^" -> XorExpression(left.toAst(considerPosition), right.toAst(considerPosition), toPosition(considerPosition))
    else -> throw UnsupportedOperationException(this.javaClass.canonicalName)
}

fun SecondaryContext.toAst(considerPosition: Boolean = false): Expression = when (this) {
    is PrimaryExpressionContext -> primary().toAst(considerPosition)
    is CallContext -> toAst(considerPosition)  // TODO(Check)
    is ElementContext -> ElementOf(secondary().toAst(considerPosition),
        expression().toAst(considerPosition), toPosition(considerPosition))
    is NamedTupleElementContext -> toAst(considerPosition)  // TODO(Check)
    is UnnamedTupleElementContext -> toAst(considerPosition) // TODO(Check)
    else -> throw UnsupportedOperationException(this.javaClass.canonicalName)
}

fun PrimaryContext.toAst(considerPosition: Boolean = false): Expression = when (this) {
    is ElementaryExpressionContext -> elementary().toAst(considerPosition)
    is ConditionalExpressionContext -> conditional().toAst(considerPosition)
    is FunctionExpressionContext -> toAst(considerPosition)
    is ArrayExpressionContext -> toAst(considerPosition)
    is TupleExpressionContext -> toAst(considerPosition)
    is MapExpressionContext -> toAst(considerPosition)
    is ParenExpressionContext -> toAst(considerPosition)
    else -> throw UnsupportedOperationException(this.javaClass.canonicalName)
}

fun ElementaryContext.toAst(considerPosition: Boolean = false): Expression = when (this) {
    is FalseLiteralContext -> BoolLit(text, toPosition(considerPosition))
    is TrueLiteralContext -> BoolLit(text, toPosition(considerPosition))
    is IntLiteralContext -> IntLit(text, toPosition(considerPosition))
    is RealLiteralContext-> RealLit(text, toPosition(considerPosition))
    is RatLiteralContext -> RatLit(text, toPosition(considerPosition))
    is CompLiteralContext -> CompLit(text, toPosition(considerPosition))
    is StrLiteralContext -> StrLit(text, toPosition(considerPosition))
    is IdLiteralContext -> VarReference(text, toPosition(considerPosition))
    else -> throw UnsupportedOperationException(this.javaClass.canonicalName)
}

fun ConditionalContext.toAst(considerPosition: Boolean = false): Expression =
    Conditional(predicate.toAst(considerPosition),
        thenExpr.toAst(considerPosition), elseExpr.toAst(considerPosition))

fun FunctionExpressionContext.toAst(considerPosition: Boolean = false): Expression {
    return Function
}

fun StatementContext.toAst(considerPosition: Boolean = false): Statement = when(this) {
    is LoopStatementContext -> Loop(loopHeader().expression(0).toAst(), statement(0).toAst(), toPosition(considerPosition))
    else -> throw UnsupportedOperationException(this.javaClass.canonicalName)
}