// Generated from FLangParser.g4 by ANTLR 4.5.1
package me.team.fproject;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FLangParser}.
 */
public interface FLangParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link FLangParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(FLangParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLangParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(FLangParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLangParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(FLangParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLangParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(FLangParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code secondaryExpression}
	 * labeled alternative in {@link FLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSecondaryExpression(FLangParser.SecondaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code secondaryExpression}
	 * labeled alternative in {@link FLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSecondaryExpression(FLangParser.SecondaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binaryOperation}
	 * labeled alternative in {@link FLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBinaryOperation(FLangParser.BinaryOperationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binaryOperation}
	 * labeled alternative in {@link FLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBinaryOperation(FLangParser.BinaryOperationContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLangParser#operatorSign}.
	 * @param ctx the parse tree
	 */
	void enterOperatorSign(FLangParser.OperatorSignContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLangParser#operatorSign}.
	 * @param ctx the parse tree
	 */
	void exitOperatorSign(FLangParser.OperatorSignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code primaryExpression}
	 * labeled alternative in {@link FLangParser#secondary}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpression(FLangParser.PrimaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code primaryExpression}
	 * labeled alternative in {@link FLangParser#secondary}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpression(FLangParser.PrimaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code call}
	 * labeled alternative in {@link FLangParser#secondary}.
	 * @param ctx the parse tree
	 */
	void enterCall(FLangParser.CallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code call}
	 * labeled alternative in {@link FLangParser#secondary}.
	 * @param ctx the parse tree
	 */
	void exitCall(FLangParser.CallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unnamedTupleElement}
	 * labeled alternative in {@link FLangParser#secondary}.
	 * @param ctx the parse tree
	 */
	void enterUnnamedTupleElement(FLangParser.UnnamedTupleElementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unnamedTupleElement}
	 * labeled alternative in {@link FLangParser#secondary}.
	 * @param ctx the parse tree
	 */
	void exitUnnamedTupleElement(FLangParser.UnnamedTupleElementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code namedTupleElement}
	 * labeled alternative in {@link FLangParser#secondary}.
	 * @param ctx the parse tree
	 */
	void enterNamedTupleElement(FLangParser.NamedTupleElementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code namedTupleElement}
	 * labeled alternative in {@link FLangParser#secondary}.
	 * @param ctx the parse tree
	 */
	void exitNamedTupleElement(FLangParser.NamedTupleElementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code element}
	 * labeled alternative in {@link FLangParser#secondary}.
	 * @param ctx the parse tree
	 */
	void enterElement(FLangParser.ElementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code element}
	 * labeled alternative in {@link FLangParser#secondary}.
	 * @param ctx the parse tree
	 */
	void exitElement(FLangParser.ElementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code elementaryExpression}
	 * labeled alternative in {@link FLangParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterElementaryExpression(FLangParser.ElementaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code elementaryExpression}
	 * labeled alternative in {@link FLangParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitElementaryExpression(FLangParser.ElementaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code conditionalExpression}
	 * labeled alternative in {@link FLangParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterConditionalExpression(FLangParser.ConditionalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code conditionalExpression}
	 * labeled alternative in {@link FLangParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitConditionalExpression(FLangParser.ConditionalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code functionExpression}
	 * labeled alternative in {@link FLangParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterFunctionExpression(FLangParser.FunctionExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code functionExpression}
	 * labeled alternative in {@link FLangParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitFunctionExpression(FLangParser.FunctionExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayExpression}
	 * labeled alternative in {@link FLangParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterArrayExpression(FLangParser.ArrayExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayExpression}
	 * labeled alternative in {@link FLangParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitArrayExpression(FLangParser.ArrayExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tupleExpression}
	 * labeled alternative in {@link FLangParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterTupleExpression(FLangParser.TupleExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tupleExpression}
	 * labeled alternative in {@link FLangParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitTupleExpression(FLangParser.TupleExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mapExpression}
	 * labeled alternative in {@link FLangParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterMapExpression(FLangParser.MapExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mapExpression}
	 * labeled alternative in {@link FLangParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitMapExpression(FLangParser.MapExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenExpression}
	 * labeled alternative in {@link FLangParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterParenExpression(FLangParser.ParenExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenExpression}
	 * labeled alternative in {@link FLangParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitParenExpression(FLangParser.ParenExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code falseLiteral}
	 * labeled alternative in {@link FLangParser#elementary}.
	 * @param ctx the parse tree
	 */
	void enterFalseLiteral(FLangParser.FalseLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code falseLiteral}
	 * labeled alternative in {@link FLangParser#elementary}.
	 * @param ctx the parse tree
	 */
	void exitFalseLiteral(FLangParser.FalseLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code trueLiteral}
	 * labeled alternative in {@link FLangParser#elementary}.
	 * @param ctx the parse tree
	 */
	void enterTrueLiteral(FLangParser.TrueLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code trueLiteral}
	 * labeled alternative in {@link FLangParser#elementary}.
	 * @param ctx the parse tree
	 */
	void exitTrueLiteral(FLangParser.TrueLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intLiteral}
	 * labeled alternative in {@link FLangParser#elementary}.
	 * @param ctx the parse tree
	 */
	void enterIntLiteral(FLangParser.IntLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intLiteral}
	 * labeled alternative in {@link FLangParser#elementary}.
	 * @param ctx the parse tree
	 */
	void exitIntLiteral(FLangParser.IntLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code realLiteral}
	 * labeled alternative in {@link FLangParser#elementary}.
	 * @param ctx the parse tree
	 */
	void enterRealLiteral(FLangParser.RealLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code realLiteral}
	 * labeled alternative in {@link FLangParser#elementary}.
	 * @param ctx the parse tree
	 */
	void exitRealLiteral(FLangParser.RealLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ratLiteral}
	 * labeled alternative in {@link FLangParser#elementary}.
	 * @param ctx the parse tree
	 */
	void enterRatLiteral(FLangParser.RatLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ratLiteral}
	 * labeled alternative in {@link FLangParser#elementary}.
	 * @param ctx the parse tree
	 */
	void exitRatLiteral(FLangParser.RatLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code compLiteral}
	 * labeled alternative in {@link FLangParser#elementary}.
	 * @param ctx the parse tree
	 */
	void enterCompLiteral(FLangParser.CompLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code compLiteral}
	 * labeled alternative in {@link FLangParser#elementary}.
	 * @param ctx the parse tree
	 */
	void exitCompLiteral(FLangParser.CompLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code strLiteral}
	 * labeled alternative in {@link FLangParser#elementary}.
	 * @param ctx the parse tree
	 */
	void enterStrLiteral(FLangParser.StrLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code strLiteral}
	 * labeled alternative in {@link FLangParser#elementary}.
	 * @param ctx the parse tree
	 */
	void exitStrLiteral(FLangParser.StrLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code idLiteral}
	 * labeled alternative in {@link FLangParser#elementary}.
	 * @param ctx the parse tree
	 */
	void enterIdLiteral(FLangParser.IdLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code idLiteral}
	 * labeled alternative in {@link FLangParser#elementary}.
	 * @param ctx the parse tree
	 */
	void exitIdLiteral(FLangParser.IdLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLangParser#conditional}.
	 * @param ctx the parse tree
	 */
	void enterConditional(FLangParser.ConditionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLangParser#conditional}.
	 * @param ctx the parse tree
	 */
	void exitConditional(FLangParser.ConditionalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignmentStatement}
	 * labeled alternative in {@link FLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentStatement(FLangParser.AssignmentStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignmentStatement}
	 * labeled alternative in {@link FLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentStatement(FLangParser.AssignmentStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code functionCallStatement}
	 * labeled alternative in {@link FLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCallStatement(FLangParser.FunctionCallStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code functionCallStatement}
	 * labeled alternative in {@link FLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCallStatement(FLangParser.FunctionCallStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code if}
	 * labeled alternative in {@link FLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIf(FLangParser.IfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code if}
	 * labeled alternative in {@link FLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIf(FLangParser.IfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code loop}
	 * labeled alternative in {@link FLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterLoop(FLangParser.LoopContext ctx);
	/**
	 * Exit a parse tree produced by the {@code loop}
	 * labeled alternative in {@link FLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitLoop(FLangParser.LoopContext ctx);
	/**
	 * Enter a parse tree produced by the {@code return}
	 * labeled alternative in {@link FLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterReturn(FLangParser.ReturnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code return}
	 * labeled alternative in {@link FLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitReturn(FLangParser.ReturnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code break}
	 * labeled alternative in {@link FLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterBreak(FLangParser.BreakContext ctx);
	/**
	 * Exit a parse tree produced by the {@code break}
	 * labeled alternative in {@link FLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitBreak(FLangParser.BreakContext ctx);
	/**
	 * Enter a parse tree produced by the {@code print}
	 * labeled alternative in {@link FLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterPrint(FLangParser.PrintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code print}
	 * labeled alternative in {@link FLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitPrint(FLangParser.PrintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code declarationStatement}
	 * labeled alternative in {@link FLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterDeclarationStatement(FLangParser.DeclarationStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code declarationStatement}
	 * labeled alternative in {@link FLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitDeclarationStatement(FLangParser.DeclarationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLangParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(FLangParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLangParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(FLangParser.FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLangParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(FLangParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLangParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(FLangParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLangParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(FLangParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLangParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(FLangParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLangParser#loopStatement}.
	 * @param ctx the parse tree
	 */
	void enterLoopStatement(FLangParser.LoopStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLangParser#loopStatement}.
	 * @param ctx the parse tree
	 */
	void exitLoopStatement(FLangParser.LoopStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLangParser#loopHeader}.
	 * @param ctx the parse tree
	 */
	void enterLoopHeader(FLangParser.LoopHeaderContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLangParser#loopHeader}.
	 * @param ctx the parse tree
	 */
	void exitLoopHeader(FLangParser.LoopHeaderContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLangParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(FLangParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLangParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(FLangParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLangParser#breakStatement}.
	 * @param ctx the parse tree
	 */
	void enterBreakStatement(FLangParser.BreakStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLangParser#breakStatement}.
	 * @param ctx the parse tree
	 */
	void exitBreakStatement(FLangParser.BreakStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLangParser#printStatement}.
	 * @param ctx the parse tree
	 */
	void enterPrintStatement(FLangParser.PrintStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLangParser#printStatement}.
	 * @param ctx the parse tree
	 */
	void exitPrintStatement(FLangParser.PrintStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLangParser#functionType}.
	 * @param ctx the parse tree
	 */
	void enterFunctionType(FLangParser.FunctionTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLangParser#functionType}.
	 * @param ctx the parse tree
	 */
	void exitFunctionType(FLangParser.FunctionTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLangParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(FLangParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLangParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(FLangParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLangParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(FLangParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLangParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(FLangParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLangParser#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(FLangParser.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLangParser#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(FLangParser.BodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLangParser#arrayType}.
	 * @param ctx the parse tree
	 */
	void enterArrayType(FLangParser.ArrayTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLangParser#arrayType}.
	 * @param ctx the parse tree
	 */
	void exitArrayType(FLangParser.ArrayTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLangParser#array}.
	 * @param ctx the parse tree
	 */
	void enterArray(FLangParser.ArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLangParser#array}.
	 * @param ctx the parse tree
	 */
	void exitArray(FLangParser.ArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLangParser#mapType}.
	 * @param ctx the parse tree
	 */
	void enterMapType(FLangParser.MapTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLangParser#mapType}.
	 * @param ctx the parse tree
	 */
	void exitMapType(FLangParser.MapTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLangParser#map}.
	 * @param ctx the parse tree
	 */
	void enterMap(FLangParser.MapContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLangParser#map}.
	 * @param ctx the parse tree
	 */
	void exitMap(FLangParser.MapContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLangParser#pair}.
	 * @param ctx the parse tree
	 */
	void enterPair(FLangParser.PairContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLangParser#pair}.
	 * @param ctx the parse tree
	 */
	void exitPair(FLangParser.PairContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLangParser#tupleType}.
	 * @param ctx the parse tree
	 */
	void enterTupleType(FLangParser.TupleTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLangParser#tupleType}.
	 * @param ctx the parse tree
	 */
	void exitTupleType(FLangParser.TupleTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLangParser#tuple}.
	 * @param ctx the parse tree
	 */
	void enterTuple(FLangParser.TupleContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLangParser#tuple}.
	 * @param ctx the parse tree
	 */
	void exitTuple(FLangParser.TupleContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLangParser#tupleElement}.
	 * @param ctx the parse tree
	 */
	void enterTupleElement(FLangParser.TupleElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLangParser#tupleElement}.
	 * @param ctx the parse tree
	 */
	void exitTupleElement(FLangParser.TupleElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLangParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(FLangParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLangParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(FLangParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLangParser#booleanType}.
	 * @param ctx the parse tree
	 */
	void enterBooleanType(FLangParser.BooleanTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLangParser#booleanType}.
	 * @param ctx the parse tree
	 */
	void exitBooleanType(FLangParser.BooleanTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLangParser#integerType}.
	 * @param ctx the parse tree
	 */
	void enterIntegerType(FLangParser.IntegerTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLangParser#integerType}.
	 * @param ctx the parse tree
	 */
	void exitIntegerType(FLangParser.IntegerTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLangParser#realType}.
	 * @param ctx the parse tree
	 */
	void enterRealType(FLangParser.RealTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLangParser#realType}.
	 * @param ctx the parse tree
	 */
	void exitRealType(FLangParser.RealTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLangParser#rationalType}.
	 * @param ctx the parse tree
	 */
	void enterRationalType(FLangParser.RationalTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLangParser#rationalType}.
	 * @param ctx the parse tree
	 */
	void exitRationalType(FLangParser.RationalTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLangParser#complexType}.
	 * @param ctx the parse tree
	 */
	void enterComplexType(FLangParser.ComplexTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLangParser#complexType}.
	 * @param ctx the parse tree
	 */
	void exitComplexType(FLangParser.ComplexTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLangParser#stringType}.
	 * @param ctx the parse tree
	 */
	void enterStringType(FLangParser.StringTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLangParser#stringType}.
	 * @param ctx the parse tree
	 */
	void exitStringType(FLangParser.StringTypeContext ctx);
}