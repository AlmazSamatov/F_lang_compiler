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
	 * Enter a parse tree produced by {@link FLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(FLangParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(FLangParser.ExpressionContext ctx);
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
	 * Enter a parse tree produced by {@link FLangParser#secondary}.
	 * @param ctx the parse tree
	 */
	void enterSecondary(FLangParser.SecondaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLangParser#secondary}.
	 * @param ctx the parse tree
	 */
	void exitSecondary(FLangParser.SecondaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLangParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(FLangParser.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLangParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(FLangParser.PrimaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLangParser#elementary}.
	 * @param ctx the parse tree
	 */
	void enterElementary(FLangParser.ElementaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLangParser#elementary}.
	 * @param ctx the parse tree
	 */
	void exitElementary(FLangParser.ElementaryContext ctx);
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
	 * Enter a parse tree produced by {@link FLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(FLangParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(FLangParser.StatementContext ctx);
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