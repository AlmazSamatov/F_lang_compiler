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
    ID().text, type()?.toAst(considerPosition), expression().toAst(considerPosition), toPosition(considerPosition))

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
    is CallContext -> Call(secondary().toAst(considerPosition),
        expression().map { it.toAst(considerPosition) },
        toPosition(considerPosition))
    is ElementContext -> ElementOf(secondary().toAst(considerPosition),
        expression().toAst(considerPosition), toPosition(considerPosition))
    is NamedTupleElementContext -> NamedTupleElement(secondary().toAst(considerPosition), ID().text,
        toPosition(considerPosition))
    is UnnamedTupleElementContext -> UnnamedTupleElement(secondary().toAst(considerPosition), INT_LIT().text, toPosition(considerPosition))
    else -> throw UnsupportedOperationException(this.javaClass.canonicalName)
}

fun PrimaryContext.toAst(considerPosition: Boolean = false): Expression {
    return when (this) {
        is ElementaryExpressionContext -> elementary().toAst(considerPosition)
        is ConditionalExpressionContext -> conditional().toAst(considerPosition)
        is FunctionExpressionContext -> function().toAst(considerPosition)
        is ArrayExpressionContext -> array().toAst(considerPosition)
        is TupleExpressionContext -> tuple().toAst(considerPosition)
        is MapExpressionContext -> map().toAst(considerPosition)
        is ParenExpressionContext -> expression().toAst(considerPosition)
        else -> throw UnsupportedOperationException(this.javaClass.canonicalName)
    }
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

/**
 * Statements
 */
fun StatementContext.toAst(considerPosition: Boolean = false): Statement  {
    return when (this) {
        is FunctionCallContext -> FunctionCall(
            secondary().toAst(considerPosition),
            expression().map { it.toAst(considerPosition) },
            toPosition(considerPosition)
        )

        is AssignmentContext -> Assignment(
            secondary().toAst(considerPosition),
            expression().toAst(considerPosition),
            toPosition(considerPosition)
        )

        is IfStatementContext -> IfStatement(
            expression().toAst(considerPosition),
            statement().map { it.toAst(considerPosition) },
            toPosition(considerPosition)
        )

        is LoopContext -> LoopStatement(
            loopStatement().loopHeader().toAst(considerPosition),
            loopStatement().statement().map { it.toAst(considerPosition) },
            toPosition(considerPosition)
        )

        is ReturnStatementContext -> ReturnStatement(
            expression().toAst(considerPosition),
            toPosition(considerPosition)
        )

        is BreakStatementContext -> BreakStatement(toPosition(considerPosition))

        is PrintStatementContext -> PrintStatement(
            expression().map { it.toAst(considerPosition) },
            toPosition(considerPosition)
        )

        is DeclarationStatementContext -> VarDeclaration(declaration().ID().text,
            declaration().type()?.toAst(considerPosition),
            declaration().expression().toAst(considerPosition),
            toPosition(considerPosition))

        else -> throw UnsupportedOperationException(this.javaClass.canonicalName)
    }
}

fun FunctionCallContext.toAst(considerPosition: Boolean = false) : Statement =
    FunctionCall(secondary().toAst(considerPosition),
        expression().map { it.toAst(considerPosition) },
        toPosition(considerPosition))

fun AssignmentContext.toAst(considerPosition: Boolean = false) : Statement =
    Assignment(secondary().toAst(considerPosition),
        expression().toAst(considerPosition),
        toPosition(considerPosition))

fun IfStatementContext.toAst(considerPosition: Boolean = false) : Statement =
    IfStatement(expression().toAst(considerPosition),
        statement().map { it.toAst(considerPosition) },
        toPosition(considerPosition))

fun LoopStatementContext.toAst(considerPosition: Boolean = false) : Statement =
    LoopStatement(loopHeader().toAst(considerPosition),
        statement().map { it.toAst(considerPosition) },
        toPosition(considerPosition))

fun LoopHeaderContext.toAst(considerPosition: Boolean = false) : LoopHeader =
    LoopHeader(expression().map { it.toAst(considerPosition) },
        toPosition(considerPosition))

fun ReturnStatementContext.toAst(considerPosition: Boolean = false) : Statement =
    ReturnStatement(expression().toAst(considerPosition),
        toPosition(considerPosition))

fun BreakStatementContext.toAst(considerPosition: Boolean = false) : Statement =
    BreakStatement(toPosition(considerPosition))

fun PrintStatementContext.toAst(considerPosition: Boolean = false) : Statement =
    PrintStatement(expression().map { it.toAst(considerPosition) },
        toPosition(considerPosition))


/**
 * Functions and function types
 */

fun FunctionTypeContext.toAst(considerPosition: Boolean = false) : Type =
    FunctionType(type().map { it.toAst(considerPosition) }, toPosition(considerPosition))

fun FunctionContext.toAst(considerPosition: Boolean = false) : Expression =
    Function(body().toAst(considerPosition),
        parameter().map {it.toAst(considerPosition) },
        type()?.toAst(considerPosition),
        toPosition(considerPosition))

fun ParameterContext.toAst(considerPosition: Boolean = false) =
    Parameter(ID().toString(), type().toAst(considerPosition), toPosition(considerPosition))

fun BodyContext.toAst(considerPosition: Boolean = false) =
    Body(statement()?.map { it.toAst(considerPosition) },
        expression()?.toAst(considerPosition),
        toPosition(considerPosition))


/**
 * Arrays
 */

fun ArrayTypeContext.toAst(considerPosition: Boolean = false) : Type =
    me.team.f.ast.ArrayType(type().toAst(considerPosition), toPosition(considerPosition))

fun ArrayContext.toAst(considerPosition: Boolean = false) : Expression =
    Array(expression().map { it.toAst(considerPosition) }, toPosition(considerPosition))

/**
 * Maps
 */

fun MapTypeContext.toAst(considerPosition: Boolean = false) : Type =
    MapType(type().map { it.toAst(considerPosition) }, toPosition(considerPosition))

fun MapContext.toAst(considerPosition: Boolean = false) : Expression =
    Map(pair().map { it.toAst(considerPosition) }, toPosition(considerPosition))

fun PairContext.toAst(considerPosition: Boolean = false): Pair =
    Pair(expression().map { it.toAst(considerPosition) }, toPosition(considerPosition))


/**
 * Tuples
 */
fun TupleTypeContext.toAst(considerPosition: Boolean = false) : TupleType =
    TupleType(type().map { it.toAst(considerPosition) }, toPosition(considerPosition))

fun TupleContext.toAst(considerPosition: Boolean = false) : Expression =
    Tuple(tupleElement().map { it.toAst(considerPosition) }, toPosition(considerPosition))

fun TupleElementContext.toAst(considerPosition: Boolean = false) : TupleElement =
    TupleElement(expression().toAst(considerPosition), toPosition(considerPosition))

/**
 * Atomic Types
 */
fun TypeContext.toAst(considerPosition: Boolean = false) : Type = when(this) {
    is BooleanTContext -> BooleanType(toPosition(considerPosition))
    is IntegerTContext -> IntegerType(toPosition(considerPosition))
    is RealTContext -> RealType(toPosition(considerPosition))
    is RationalTContext -> RationalType(toPosition(considerPosition))
    is ComplexTContext -> ComplexType(toPosition(considerPosition))
    is StringTContext -> StringType(toPosition(considerPosition))
    is FunctionTContext -> functionType().toAst(considerPosition)
    is TupleTContext -> tupleType().toAst(considerPosition)
    is ArrayTContext -> arrayType().toAst(considerPosition)
    is MapTContext -> mapType().toAst(considerPosition)
    else -> throw UnsupportedOperationException(this.javaClass.canonicalName)
}
