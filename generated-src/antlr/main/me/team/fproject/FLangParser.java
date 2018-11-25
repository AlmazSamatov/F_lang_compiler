// Generated from FLangParser.g4 by ANTLR 4.5.1
package me.team.fproject;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FLangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		NEWLINE=1, WS=2, IS=3, IF=4, IN=5, THEN=6, ELSE=7, END=8, FUNC=9, DO=10, 
		LOOP=11, FOR=12, WHILE=13, TRUE=14, FALSE=15, INTEGER=16, REAL=17, BOOLEAN=18, 
		RATIONAL=19, COMPLEX=20, STRING=21, PRINT=22, RETURN=23, BREAK=24, TYPE_CONV=25, 
		LENGTH=26, INT_LIT=27, REAL_LIT=28, RAT_LIT=29, COMP_LIT=30, STR_LIT=31, 
		PLUS=32, MINUS=33, MULT=34, DIVISION=35, LESS=36, GREATER=37, LESS_EQ=38, 
		GREATER_EQ=39, EQUAL=40, NOT_EQ=41, AND=42, OR=43, XOR=44, LINE_FUN=45, 
		ASSIGN=46, LPAR=47, RPAR=48, LSQUARE=49, RSQUARE=50, LCURLY=51, RCURLY=52, 
		SEMI=53, COLUMN=54, COMMA=55, DOT=56, DOTDOT=57, ID=58;
	public static final int
		RULE_program = 0, RULE_declaration = 1, RULE_expression = 2, RULE_expressionEnd = 3, 
		RULE_operatorSign = 4, RULE_secondary = 5, RULE_primary = 6, RULE_elementary = 7, 
		RULE_conditional = 8, RULE_statement = 9, RULE_functionCall = 10, RULE_assignment = 11, 
		RULE_ifStatement = 12, RULE_loopStatement = 13, RULE_loopHeader = 14, 
		RULE_returnStatement = 15, RULE_breakStatement = 16, RULE_printStatement = 17, 
		RULE_functionType = 18, RULE_function = 19, RULE_parameter = 20, RULE_body = 21, 
		RULE_arrayType = 22, RULE_array = 23, RULE_mapType = 24, RULE_map = 25, 
		RULE_pair = 26, RULE_tupleType = 27, RULE_tuple = 28, RULE_tupleElement = 29, 
		RULE_type = 30, RULE_booleanType = 31, RULE_integerType = 32, RULE_realType = 33, 
		RULE_rationalType = 34, RULE_complexType = 35, RULE_stringType = 36;
	public static final String[] ruleNames = {
		"program", "declaration", "expression", "expressionEnd", "operatorSign", 
		"secondary", "primary", "elementary", "conditional", "statement", "functionCall", 
		"assignment", "ifStatement", "loopStatement", "loopHeader", "returnStatement", 
		"breakStatement", "printStatement", "functionType", "function", "parameter", 
		"body", "arrayType", "array", "mapType", "map", "pair", "tupleType", "tuple", 
		"tupleElement", "type", "booleanType", "integerType", "realType", "rationalType", 
		"complexType", "stringType"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, "'is'", "'if'", "'in'", "'then'", "'else'", "'end'", 
		"'func'", "'do'", "'loop'", "'for'", "'while'", "'true'", "'false'", "'integer'", 
		"'real'", "'boolean'", "'rational'", "'complex'", "'string'", "'print'", 
		"'return'", "'break'", null, "'length'", null, null, null, null, null, 
		"'+'", "'-'", "'*'", "'/'", "'<'", "'>'", "'<='", "'>='", "'='", "'/='", 
		"'&'", "'|'", "'^'", "'=>'", "':='", "'('", "')'", "'['", "']'", "'{'", 
		"'}'", "';'", "':'", "','", "'.'", "'..'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "NEWLINE", "WS", "IS", "IF", "IN", "THEN", "ELSE", "END", "FUNC", 
		"DO", "LOOP", "FOR", "WHILE", "TRUE", "FALSE", "INTEGER", "REAL", "BOOLEAN", 
		"RATIONAL", "COMPLEX", "STRING", "PRINT", "RETURN", "BREAK", "TYPE_CONV", 
		"LENGTH", "INT_LIT", "REAL_LIT", "RAT_LIT", "COMP_LIT", "STR_LIT", "PLUS", 
		"MINUS", "MULT", "DIVISION", "LESS", "GREATER", "LESS_EQ", "GREATER_EQ", 
		"EQUAL", "NOT_EQ", "AND", "OR", "XOR", "LINE_FUN", "ASSIGN", "LPAR", "RPAR", 
		"LSQUARE", "RSQUARE", "LCURLY", "RCURLY", "SEMI", "COLUMN", "COMMA", "DOT", 
		"DOTDOT", "ID"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "FLangParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public FLangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public List<DeclarationContext> declaration() {
			return getRuleContexts(DeclarationContext.class);
		}
		public DeclarationContext declaration(int i) {
			return getRuleContext(DeclarationContext.class,i);
		}
		public List<TerminalNode> SEMI() { return getTokens(FLangParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(FLangParser.SEMI, i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			declaration();
			setState(79);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEMI) {
				{
				{
				setState(75);
				match(SEMI);
				setState(76);
				declaration();
				}
				}
				setState(81);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclarationContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(FLangParser.ID, 0); }
		public TerminalNode IS() { return getToken(FLangParser.IS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode COLUMN() { return getToken(FLangParser.COLUMN, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).enterDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).exitDeclaration(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			match(ID);
			setState(85);
			_la = _input.LA(1);
			if (_la==COLUMN) {
				{
				setState(83);
				match(COLUMN);
				setState(84);
				type();
				}
			}

			setState(87);
			match(IS);
			setState(88);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public SecondaryContext secondary() {
			return getRuleContext(SecondaryContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public OperatorSignContext operatorSign() {
			return getRuleContext(OperatorSignContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_expression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(91);
			secondary(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(99);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExpressionContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_expression);
					setState(93);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(94);
					operatorSign();
					setState(95);
					expression(2);
					}
					} 
				}
				setState(101);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ExpressionEndContext extends ParserRuleContext {
		public OperatorSignContext operatorSign() {
			return getRuleContext(OperatorSignContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionEndContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionEnd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).enterExpressionEnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).exitExpressionEnd(this);
		}
	}

	public final ExpressionEndContext expressionEnd() throws RecognitionException {
		ExpressionEndContext _localctx = new ExpressionEndContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_expressionEnd);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << MULT) | (1L << DIVISION) | (1L << LESS) | (1L << GREATER) | (1L << LESS_EQ) | (1L << GREATER_EQ) | (1L << EQUAL) | (1L << NOT_EQ) | (1L << AND) | (1L << OR) | (1L << XOR))) != 0)) {
				{
				setState(102);
				operatorSign();
				setState(103);
				expression(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperatorSignContext extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(FLangParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(FLangParser.MINUS, 0); }
		public TerminalNode MULT() { return getToken(FLangParser.MULT, 0); }
		public TerminalNode DIVISION() { return getToken(FLangParser.DIVISION, 0); }
		public TerminalNode LESS() { return getToken(FLangParser.LESS, 0); }
		public TerminalNode GREATER() { return getToken(FLangParser.GREATER, 0); }
		public TerminalNode LESS_EQ() { return getToken(FLangParser.LESS_EQ, 0); }
		public TerminalNode GREATER_EQ() { return getToken(FLangParser.GREATER_EQ, 0); }
		public TerminalNode EQUAL() { return getToken(FLangParser.EQUAL, 0); }
		public TerminalNode NOT_EQ() { return getToken(FLangParser.NOT_EQ, 0); }
		public TerminalNode AND() { return getToken(FLangParser.AND, 0); }
		public TerminalNode OR() { return getToken(FLangParser.OR, 0); }
		public TerminalNode XOR() { return getToken(FLangParser.XOR, 0); }
		public OperatorSignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operatorSign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).enterOperatorSign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).exitOperatorSign(this);
		}
	}

	public final OperatorSignContext operatorSign() throws RecognitionException {
		OperatorSignContext _localctx = new OperatorSignContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_operatorSign);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << MULT) | (1L << DIVISION) | (1L << LESS) | (1L << GREATER) | (1L << LESS_EQ) | (1L << GREATER_EQ) | (1L << EQUAL) | (1L << NOT_EQ) | (1L << AND) | (1L << OR) | (1L << XOR))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SecondaryContext extends ParserRuleContext {
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public SecondaryContext secondary() {
			return getRuleContext(SecondaryContext.class,0);
		}
		public TerminalNode LPAR() { return getToken(FLangParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(FLangParser.RPAR, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(FLangParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(FLangParser.COMMA, i);
		}
		public TerminalNode LSQUARE() { return getToken(FLangParser.LSQUARE, 0); }
		public TerminalNode RSQUARE() { return getToken(FLangParser.RSQUARE, 0); }
		public TerminalNode DOT() { return getToken(FLangParser.DOT, 0); }
		public TerminalNode ID() { return getToken(FLangParser.ID, 0); }
		public TerminalNode INT_LIT() { return getToken(FLangParser.INT_LIT, 0); }
		public SecondaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_secondary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).enterSecondary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).exitSecondary(this);
		}
	}

	public final SecondaryContext secondary() throws RecognitionException {
		return secondary(0);
	}

	private SecondaryContext secondary(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		SecondaryContext _localctx = new SecondaryContext(_ctx, _parentState);
		SecondaryContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_secondary, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(110);
			primary();
			}
			_ctx.stop = _input.LT(-1);
			setState(138);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(136);
					switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
					case 1:
						{
						_localctx = new SecondaryContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_secondary);
						setState(112);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(113);
						match(LPAR);
						setState(122);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << FUNC) | (1L << TRUE) | (1L << FALSE) | (1L << INT_LIT) | (1L << REAL_LIT) | (1L << RAT_LIT) | (1L << COMP_LIT) | (1L << STR_LIT) | (1L << LPAR) | (1L << LSQUARE) | (1L << LCURLY) | (1L << ID))) != 0)) {
							{
							setState(114);
							expression(0);
							setState(119);
							_errHandler.sync(this);
							_la = _input.LA(1);
							while (_la==COMMA) {
								{
								{
								setState(115);
								match(COMMA);
								setState(116);
								expression(0);
								}
								}
								setState(121);
								_errHandler.sync(this);
								_la = _input.LA(1);
							}
							}
						}

						setState(124);
						match(RPAR);
						}
						break;
					case 2:
						{
						_localctx = new SecondaryContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_secondary);
						setState(125);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(126);
						match(LSQUARE);
						setState(127);
						expression(0);
						setState(128);
						match(RSQUARE);
						}
						break;
					case 3:
						{
						_localctx = new SecondaryContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_secondary);
						setState(130);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(131);
						match(DOT);
						setState(132);
						match(ID);
						}
						break;
					case 4:
						{
						_localctx = new SecondaryContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_secondary);
						setState(133);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(134);
						match(DOT);
						setState(135);
						match(INT_LIT);
						}
						break;
					}
					} 
				}
				setState(140);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class PrimaryContext extends ParserRuleContext {
		public ElementaryContext elementary() {
			return getRuleContext(ElementaryContext.class,0);
		}
		public ConditionalContext conditional() {
			return getRuleContext(ConditionalContext.class,0);
		}
		public FunctionContext function() {
			return getRuleContext(FunctionContext.class,0);
		}
		public ArrayContext array() {
			return getRuleContext(ArrayContext.class,0);
		}
		public TupleContext tuple() {
			return getRuleContext(TupleContext.class,0);
		}
		public MapContext map() {
			return getRuleContext(MapContext.class,0);
		}
		public TerminalNode LPAR() { return getToken(FLangParser.LPAR, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(FLangParser.RPAR, 0); }
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).enterPrimary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).exitPrimary(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_primary);
		try {
			setState(151);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(141);
				elementary();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(142);
				conditional();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(143);
				function();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(144);
				array();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(145);
				tuple();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(146);
				map();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(147);
				match(LPAR);
				setState(148);
				expression(0);
				setState(149);
				match(RPAR);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElementaryContext extends ParserRuleContext {
		public TerminalNode FALSE() { return getToken(FLangParser.FALSE, 0); }
		public TerminalNode TRUE() { return getToken(FLangParser.TRUE, 0); }
		public TerminalNode INT_LIT() { return getToken(FLangParser.INT_LIT, 0); }
		public TerminalNode REAL_LIT() { return getToken(FLangParser.REAL_LIT, 0); }
		public TerminalNode RAT_LIT() { return getToken(FLangParser.RAT_LIT, 0); }
		public TerminalNode COMP_LIT() { return getToken(FLangParser.COMP_LIT, 0); }
		public TerminalNode STR_LIT() { return getToken(FLangParser.STR_LIT, 0); }
		public TerminalNode ID() { return getToken(FLangParser.ID, 0); }
		public ElementaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).enterElementary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).exitElementary(this);
		}
	}

	public final ElementaryContext elementary() throws RecognitionException {
		ElementaryContext _localctx = new ElementaryContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_elementary);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(153);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TRUE) | (1L << FALSE) | (1L << INT_LIT) | (1L << REAL_LIT) | (1L << RAT_LIT) | (1L << COMP_LIT) | (1L << STR_LIT) | (1L << ID))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionalContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(FLangParser.IF, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode THEN() { return getToken(FLangParser.THEN, 0); }
		public TerminalNode ELSE() { return getToken(FLangParser.ELSE, 0); }
		public TerminalNode END() { return getToken(FLangParser.END, 0); }
		public ConditionalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditional; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).enterConditional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).exitConditional(this);
		}
	}

	public final ConditionalContext conditional() throws RecognitionException {
		ConditionalContext _localctx = new ConditionalContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_conditional);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155);
			match(IF);
			setState(156);
			expression(0);
			setState(157);
			match(THEN);
			setState(158);
			expression(0);
			setState(159);
			match(ELSE);
			setState(160);
			expression(0);
			setState(161);
			match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public LoopStatementContext loopStatement() {
			return getRuleContext(LoopStatementContext.class,0);
		}
		public ReturnStatementContext returnStatement() {
			return getRuleContext(ReturnStatementContext.class,0);
		}
		public BreakStatementContext breakStatement() {
			return getRuleContext(BreakStatementContext.class,0);
		}
		public PrintStatementContext printStatement() {
			return getRuleContext(PrintStatementContext.class,0);
		}
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_statement);
		try {
			setState(171);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(163);
				assignment();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(164);
				functionCall();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(165);
				ifStatement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(166);
				loopStatement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(167);
				returnStatement();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(168);
				breakStatement();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(169);
				printStatement();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(170);
				declaration();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionCallContext extends ParserRuleContext {
		public SecondaryContext secondary() {
			return getRuleContext(SecondaryContext.class,0);
		}
		public TerminalNode LPAR() { return getToken(FLangParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(FLangParser.RPAR, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(FLangParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(FLangParser.COMMA, i);
		}
		public FunctionCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).enterFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).exitFunctionCall(this);
		}
	}

	public final FunctionCallContext functionCall() throws RecognitionException {
		FunctionCallContext _localctx = new FunctionCallContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_functionCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
			secondary(0);
			setState(174);
			match(LPAR);
			setState(183);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << FUNC) | (1L << TRUE) | (1L << FALSE) | (1L << INT_LIT) | (1L << REAL_LIT) | (1L << RAT_LIT) | (1L << COMP_LIT) | (1L << STR_LIT) | (1L << LPAR) | (1L << LSQUARE) | (1L << LCURLY) | (1L << ID))) != 0)) {
				{
				setState(175);
				expression(0);
				setState(180);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(176);
					match(COMMA);
					setState(177);
					expression(0);
					}
					}
					setState(182);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(185);
			match(RPAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignmentContext extends ParserRuleContext {
		public SecondaryContext secondary() {
			return getRuleContext(SecondaryContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(FLangParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).enterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).exitAssignment(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
			secondary(0);
			setState(188);
			match(ASSIGN);
			setState(189);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfStatementContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(FLangParser.IF, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode THEN() { return getToken(FLangParser.THEN, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode END() { return getToken(FLangParser.END, 0); }
		public TerminalNode ELSE() { return getToken(FLangParser.ELSE, 0); }
		public IfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).enterIfStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).exitIfStatement(this);
		}
	}

	public final IfStatementContext ifStatement() throws RecognitionException {
		IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_ifStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191);
			match(IF);
			setState(192);
			expression(0);
			setState(193);
			match(THEN);
			setState(194);
			statement();
			setState(197);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(195);
				match(ELSE);
				setState(196);
				statement();
				}
			}

			setState(199);
			match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LoopStatementContext extends ParserRuleContext {
		public TerminalNode LOOP() { return getToken(FLangParser.LOOP, 0); }
		public TerminalNode END() { return getToken(FLangParser.END, 0); }
		public LoopHeaderContext loopHeader() {
			return getRuleContext(LoopHeaderContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public LoopStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loopStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).enterLoopStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).exitLoopStatement(this);
		}
	}

	public final LoopStatementContext loopStatement() throws RecognitionException {
		LoopStatementContext _localctx = new LoopStatementContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_loopStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(202);
			_la = _input.LA(1);
			if (_la==FOR || _la==WHILE) {
				{
				setState(201);
				loopHeader();
				}
			}

			setState(204);
			match(LOOP);
			setState(206);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << FUNC) | (1L << LOOP) | (1L << FOR) | (1L << WHILE) | (1L << TRUE) | (1L << FALSE) | (1L << PRINT) | (1L << RETURN) | (1L << BREAK) | (1L << INT_LIT) | (1L << REAL_LIT) | (1L << RAT_LIT) | (1L << COMP_LIT) | (1L << STR_LIT) | (1L << LPAR) | (1L << LSQUARE) | (1L << LCURLY) | (1L << ID))) != 0)) {
				{
				setState(205);
				statement();
				}
			}

			setState(208);
			match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LoopHeaderContext extends ParserRuleContext {
		public TerminalNode FOR() { return getToken(FLangParser.FOR, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode ID() { return getToken(FLangParser.ID, 0); }
		public TerminalNode IN() { return getToken(FLangParser.IN, 0); }
		public TerminalNode DOTDOT() { return getToken(FLangParser.DOTDOT, 0); }
		public TerminalNode WHILE() { return getToken(FLangParser.WHILE, 0); }
		public LoopHeaderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loopHeader; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).enterLoopHeader(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).exitLoopHeader(this);
		}
	}

	public final LoopHeaderContext loopHeader() throws RecognitionException {
		LoopHeaderContext _localctx = new LoopHeaderContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_loopHeader);
		int _la;
		try {
			setState(222);
			switch (_input.LA(1)) {
			case FOR:
				enterOuterAlt(_localctx, 1);
				{
				setState(210);
				match(FOR);
				setState(213);
				switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
				case 1:
					{
					setState(211);
					match(ID);
					setState(212);
					match(IN);
					}
					break;
				}
				setState(215);
				expression(0);
				setState(218);
				_la = _input.LA(1);
				if (_la==DOTDOT) {
					{
					setState(216);
					match(DOTDOT);
					setState(217);
					expression(0);
					}
				}

				}
				break;
			case WHILE:
				enterOuterAlt(_localctx, 2);
				{
				setState(220);
				match(WHILE);
				setState(221);
				expression(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnStatementContext extends ParserRuleContext {
		public TerminalNode RETURN() { return getToken(FLangParser.RETURN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).enterReturnStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).exitReturnStatement(this);
		}
	}

	public final ReturnStatementContext returnStatement() throws RecognitionException {
		ReturnStatementContext _localctx = new ReturnStatementContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_returnStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224);
			match(RETURN);
			setState(226);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << FUNC) | (1L << TRUE) | (1L << FALSE) | (1L << INT_LIT) | (1L << REAL_LIT) | (1L << RAT_LIT) | (1L << COMP_LIT) | (1L << STR_LIT) | (1L << LPAR) | (1L << LSQUARE) | (1L << LCURLY) | (1L << ID))) != 0)) {
				{
				setState(225);
				expression(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BreakStatementContext extends ParserRuleContext {
		public TerminalNode BREAK() { return getToken(FLangParser.BREAK, 0); }
		public BreakStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_breakStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).enterBreakStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).exitBreakStatement(this);
		}
	}

	public final BreakStatementContext breakStatement() throws RecognitionException {
		BreakStatementContext _localctx = new BreakStatementContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_breakStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(228);
			match(BREAK);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrintStatementContext extends ParserRuleContext {
		public TerminalNode PRINT() { return getToken(FLangParser.PRINT, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(FLangParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(FLangParser.COMMA, i);
		}
		public PrintStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_printStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).enterPrintStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).exitPrintStatement(this);
		}
	}

	public final PrintStatementContext printStatement() throws RecognitionException {
		PrintStatementContext _localctx = new PrintStatementContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_printStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(230);
			match(PRINT);
			setState(239);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << FUNC) | (1L << TRUE) | (1L << FALSE) | (1L << INT_LIT) | (1L << REAL_LIT) | (1L << RAT_LIT) | (1L << COMP_LIT) | (1L << STR_LIT) | (1L << LPAR) | (1L << LSQUARE) | (1L << LCURLY) | (1L << ID))) != 0)) {
				{
				setState(231);
				expression(0);
				setState(236);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(232);
					match(COMMA);
					setState(233);
					expression(0);
					}
					}
					setState(238);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionTypeContext extends ParserRuleContext {
		public TerminalNode FUNC() { return getToken(FLangParser.FUNC, 0); }
		public TerminalNode LPAR() { return getToken(FLangParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(FLangParser.RPAR, 0); }
		public TerminalNode COLUMN() { return getToken(FLangParser.COLUMN, 0); }
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(FLangParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(FLangParser.COMMA, i);
		}
		public FunctionTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).enterFunctionType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).exitFunctionType(this);
		}
	}

	public final FunctionTypeContext functionType() throws RecognitionException {
		FunctionTypeContext _localctx = new FunctionTypeContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_functionType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(241);
			match(FUNC);
			setState(242);
			match(LPAR);
			setState(251);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FUNC) | (1L << INTEGER) | (1L << REAL) | (1L << BOOLEAN) | (1L << RATIONAL) | (1L << COMPLEX) | (1L << STRING) | (1L << LPAR) | (1L << LSQUARE) | (1L << LCURLY))) != 0)) {
				{
				setState(243);
				type();
				setState(248);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(244);
					match(COMMA);
					setState(245);
					type();
					}
					}
					setState(250);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(253);
			match(RPAR);
			setState(254);
			match(COLUMN);
			setState(255);
			type();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionContext extends ParserRuleContext {
		public TerminalNode FUNC() { return getToken(FLangParser.FUNC, 0); }
		public TerminalNode LPAR() { return getToken(FLangParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(FLangParser.RPAR, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public List<ParameterContext> parameter() {
			return getRuleContexts(ParameterContext.class);
		}
		public ParameterContext parameter(int i) {
			return getRuleContext(ParameterContext.class,i);
		}
		public TerminalNode COLUMN() { return getToken(FLangParser.COLUMN, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(FLangParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(FLangParser.COMMA, i);
		}
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).enterFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).exitFunction(this);
		}
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(257);
			match(FUNC);
			setState(258);
			match(LPAR);
			setState(267);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(259);
				parameter();
				setState(264);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(260);
					match(COMMA);
					setState(261);
					parameter();
					}
					}
					setState(266);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(269);
			match(RPAR);
			setState(272);
			_la = _input.LA(1);
			if (_la==COLUMN) {
				{
				setState(270);
				match(COLUMN);
				setState(271);
				type();
				}
			}

			setState(274);
			body();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(FLangParser.ID, 0); }
		public TerminalNode COLUMN() { return getToken(FLangParser.COLUMN, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).enterParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).exitParameter(this);
		}
	}

	public final ParameterContext parameter() throws RecognitionException {
		ParameterContext _localctx = new ParameterContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(276);
			match(ID);
			setState(277);
			match(COLUMN);
			setState(278);
			type();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BodyContext extends ParserRuleContext {
		public TerminalNode DO() { return getToken(FLangParser.DO, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TerminalNode END() { return getToken(FLangParser.END, 0); }
		public TerminalNode LINE_FUN() { return getToken(FLangParser.LINE_FUN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).enterBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).exitBody(this);
		}
	}

	public final BodyContext body() throws RecognitionException {
		BodyContext _localctx = new BodyContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_body);
		try {
			setState(286);
			switch (_input.LA(1)) {
			case DO:
				enterOuterAlt(_localctx, 1);
				{
				setState(280);
				match(DO);
				setState(281);
				statement();
				setState(282);
				match(END);
				}
				break;
			case LINE_FUN:
				enterOuterAlt(_localctx, 2);
				{
				setState(284);
				match(LINE_FUN);
				setState(285);
				expression(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayTypeContext extends ParserRuleContext {
		public TerminalNode LSQUARE() { return getToken(FLangParser.LSQUARE, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode RSQUARE() { return getToken(FLangParser.RSQUARE, 0); }
		public ArrayTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).enterArrayType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).exitArrayType(this);
		}
	}

	public final ArrayTypeContext arrayType() throws RecognitionException {
		ArrayTypeContext _localctx = new ArrayTypeContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_arrayType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(288);
			match(LSQUARE);
			setState(289);
			type();
			setState(290);
			match(RSQUARE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayContext extends ParserRuleContext {
		public TerminalNode LSQUARE() { return getToken(FLangParser.LSQUARE, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode RSQUARE() { return getToken(FLangParser.RSQUARE, 0); }
		public List<TerminalNode> COMMA() { return getTokens(FLangParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(FLangParser.COMMA, i);
		}
		public ArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).enterArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).exitArray(this);
		}
	}

	public final ArrayContext array() throws RecognitionException {
		ArrayContext _localctx = new ArrayContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_array);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(292);
			match(LSQUARE);
			setState(293);
			expression(0);
			setState(298);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(294);
				match(COMMA);
				setState(295);
				expression(0);
				}
				}
				setState(300);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(301);
			match(RSQUARE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MapTypeContext extends ParserRuleContext {
		public TerminalNode LCURLY() { return getToken(FLangParser.LCURLY, 0); }
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public TerminalNode COLUMN() { return getToken(FLangParser.COLUMN, 0); }
		public TerminalNode RCURLY() { return getToken(FLangParser.RCURLY, 0); }
		public MapTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mapType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).enterMapType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).exitMapType(this);
		}
	}

	public final MapTypeContext mapType() throws RecognitionException {
		MapTypeContext _localctx = new MapTypeContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_mapType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(303);
			match(LCURLY);
			setState(304);
			type();
			setState(305);
			match(COLUMN);
			setState(306);
			type();
			setState(307);
			match(RCURLY);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MapContext extends ParserRuleContext {
		public TerminalNode LCURLY() { return getToken(FLangParser.LCURLY, 0); }
		public List<PairContext> pair() {
			return getRuleContexts(PairContext.class);
		}
		public PairContext pair(int i) {
			return getRuleContext(PairContext.class,i);
		}
		public TerminalNode RCURLY() { return getToken(FLangParser.RCURLY, 0); }
		public List<TerminalNode> COMMA() { return getTokens(FLangParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(FLangParser.COMMA, i);
		}
		public MapContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_map; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).enterMap(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).exitMap(this);
		}
	}

	public final MapContext map() throws RecognitionException {
		MapContext _localctx = new MapContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_map);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(309);
			match(LCURLY);
			setState(310);
			pair();
			setState(315);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(311);
				match(COMMA);
				setState(312);
				pair();
				}
				}
				setState(317);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(318);
			match(RCURLY);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PairContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode COLUMN() { return getToken(FLangParser.COLUMN, 0); }
		public PairContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pair; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).enterPair(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).exitPair(this);
		}
	}

	public final PairContext pair() throws RecognitionException {
		PairContext _localctx = new PairContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_pair);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(320);
			expression(0);
			setState(321);
			match(COLUMN);
			setState(322);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TupleTypeContext extends ParserRuleContext {
		public TerminalNode LPAR() { return getToken(FLangParser.LPAR, 0); }
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public TerminalNode RPAR() { return getToken(FLangParser.RPAR, 0); }
		public List<TerminalNode> COMMA() { return getTokens(FLangParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(FLangParser.COMMA, i);
		}
		public TupleTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tupleType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).enterTupleType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).exitTupleType(this);
		}
	}

	public final TupleTypeContext tupleType() throws RecognitionException {
		TupleTypeContext _localctx = new TupleTypeContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_tupleType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(324);
			match(LPAR);
			setState(325);
			type();
			setState(330);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(326);
				match(COMMA);
				setState(327);
				type();
				}
				}
				setState(332);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(333);
			match(RPAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TupleContext extends ParserRuleContext {
		public TerminalNode LPAR() { return getToken(FLangParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(FLangParser.RPAR, 0); }
		public List<TupleElementContext> tupleElement() {
			return getRuleContexts(TupleElementContext.class);
		}
		public TupleElementContext tupleElement(int i) {
			return getRuleContext(TupleElementContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(FLangParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(FLangParser.COMMA, i);
		}
		public TupleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tuple; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).enterTuple(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).exitTuple(this);
		}
	}

	public final TupleContext tuple() throws RecognitionException {
		TupleContext _localctx = new TupleContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_tuple);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(335);
			match(LPAR);
			setState(344);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << FUNC) | (1L << TRUE) | (1L << FALSE) | (1L << INT_LIT) | (1L << REAL_LIT) | (1L << RAT_LIT) | (1L << COMP_LIT) | (1L << STR_LIT) | (1L << LPAR) | (1L << LSQUARE) | (1L << LCURLY) | (1L << ID))) != 0)) {
				{
				setState(336);
				tupleElement();
				setState(341);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(337);
					match(COMMA);
					setState(338);
					tupleElement();
					}
					}
					setState(343);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(346);
			match(RPAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TupleElementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode ID() { return getToken(FLangParser.ID, 0); }
		public TerminalNode IS() { return getToken(FLangParser.IS, 0); }
		public TupleElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tupleElement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).enterTupleElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).exitTupleElement(this);
		}
	}

	public final TupleElementContext tupleElement() throws RecognitionException {
		TupleElementContext _localctx = new TupleElementContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_tupleElement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(350);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				{
				setState(348);
				match(ID);
				setState(349);
				match(IS);
				}
				break;
			}
			setState(352);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public BooleanTypeContext booleanType() {
			return getRuleContext(BooleanTypeContext.class,0);
		}
		public IntegerTypeContext integerType() {
			return getRuleContext(IntegerTypeContext.class,0);
		}
		public RealTypeContext realType() {
			return getRuleContext(RealTypeContext.class,0);
		}
		public RationalTypeContext rationalType() {
			return getRuleContext(RationalTypeContext.class,0);
		}
		public ComplexTypeContext complexType() {
			return getRuleContext(ComplexTypeContext.class,0);
		}
		public StringTypeContext stringType() {
			return getRuleContext(StringTypeContext.class,0);
		}
		public FunctionTypeContext functionType() {
			return getRuleContext(FunctionTypeContext.class,0);
		}
		public TupleTypeContext tupleType() {
			return getRuleContext(TupleTypeContext.class,0);
		}
		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class,0);
		}
		public MapTypeContext mapType() {
			return getRuleContext(MapTypeContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_type);
		try {
			setState(364);
			switch (_input.LA(1)) {
			case BOOLEAN:
				enterOuterAlt(_localctx, 1);
				{
				setState(354);
				booleanType();
				}
				break;
			case INTEGER:
				enterOuterAlt(_localctx, 2);
				{
				setState(355);
				integerType();
				}
				break;
			case REAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(356);
				realType();
				}
				break;
			case RATIONAL:
				enterOuterAlt(_localctx, 4);
				{
				setState(357);
				rationalType();
				}
				break;
			case COMPLEX:
				enterOuterAlt(_localctx, 5);
				{
				setState(358);
				complexType();
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 6);
				{
				setState(359);
				stringType();
				}
				break;
			case FUNC:
				enterOuterAlt(_localctx, 7);
				{
				setState(360);
				functionType();
				}
				break;
			case LPAR:
				enterOuterAlt(_localctx, 8);
				{
				setState(361);
				tupleType();
				}
				break;
			case LSQUARE:
				enterOuterAlt(_localctx, 9);
				{
				setState(362);
				arrayType();
				}
				break;
			case LCURLY:
				enterOuterAlt(_localctx, 10);
				{
				setState(363);
				mapType();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BooleanTypeContext extends ParserRuleContext {
		public TerminalNode BOOLEAN() { return getToken(FLangParser.BOOLEAN, 0); }
		public BooleanTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_booleanType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).enterBooleanType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).exitBooleanType(this);
		}
	}

	public final BooleanTypeContext booleanType() throws RecognitionException {
		BooleanTypeContext _localctx = new BooleanTypeContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_booleanType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(366);
			match(BOOLEAN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IntegerTypeContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(FLangParser.INTEGER, 0); }
		public IntegerTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integerType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).enterIntegerType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).exitIntegerType(this);
		}
	}

	public final IntegerTypeContext integerType() throws RecognitionException {
		IntegerTypeContext _localctx = new IntegerTypeContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_integerType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(368);
			match(INTEGER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RealTypeContext extends ParserRuleContext {
		public TerminalNode REAL() { return getToken(FLangParser.REAL, 0); }
		public RealTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_realType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).enterRealType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).exitRealType(this);
		}
	}

	public final RealTypeContext realType() throws RecognitionException {
		RealTypeContext _localctx = new RealTypeContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_realType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(370);
			match(REAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RationalTypeContext extends ParserRuleContext {
		public TerminalNode RATIONAL() { return getToken(FLangParser.RATIONAL, 0); }
		public RationalTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rationalType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).enterRationalType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).exitRationalType(this);
		}
	}

	public final RationalTypeContext rationalType() throws RecognitionException {
		RationalTypeContext _localctx = new RationalTypeContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_rationalType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(372);
			match(RATIONAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComplexTypeContext extends ParserRuleContext {
		public TerminalNode COMPLEX() { return getToken(FLangParser.COMPLEX, 0); }
		public ComplexTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_complexType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).enterComplexType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).exitComplexType(this);
		}
	}

	public final ComplexTypeContext complexType() throws RecognitionException {
		ComplexTypeContext _localctx = new ComplexTypeContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_complexType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(374);
			match(COMPLEX);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StringTypeContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(FLangParser.STRING, 0); }
		public StringTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).enterStringType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FLangParserListener ) ((FLangParserListener)listener).exitStringType(this);
		}
	}

	public final StringTypeContext stringType() throws RecognitionException {
		StringTypeContext _localctx = new StringTypeContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_stringType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(376);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 2:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		case 5:
			return secondary_sempred((SecondaryContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean secondary_sempred(SecondaryContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 4);
		case 2:
			return precpred(_ctx, 3);
		case 3:
			return precpred(_ctx, 2);
		case 4:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3<\u017d\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\3\2\3\2\3\2\7\2P\n\2\f\2\16\2S\13"+
		"\2\3\3\3\3\3\3\5\3X\n\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4d\n"+
		"\4\f\4\16\4g\13\4\3\5\3\5\3\5\5\5l\n\5\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\7\7x\n\7\f\7\16\7{\13\7\5\7}\n\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\7\7\u008b\n\7\f\7\16\7\u008e\13\7\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u009a\n\b\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00ae\n\13\3\f\3"+
		"\f\3\f\3\f\3\f\7\f\u00b5\n\f\f\f\16\f\u00b8\13\f\5\f\u00ba\n\f\3\f\3\f"+
		"\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u00c8\n\16\3\16\3"+
		"\16\3\17\5\17\u00cd\n\17\3\17\3\17\5\17\u00d1\n\17\3\17\3\17\3\20\3\20"+
		"\3\20\5\20\u00d8\n\20\3\20\3\20\3\20\5\20\u00dd\n\20\3\20\3\20\5\20\u00e1"+
		"\n\20\3\21\3\21\5\21\u00e5\n\21\3\22\3\22\3\23\3\23\3\23\3\23\7\23\u00ed"+
		"\n\23\f\23\16\23\u00f0\13\23\5\23\u00f2\n\23\3\24\3\24\3\24\3\24\3\24"+
		"\7\24\u00f9\n\24\f\24\16\24\u00fc\13\24\5\24\u00fe\n\24\3\24\3\24\3\24"+
		"\3\24\3\25\3\25\3\25\3\25\3\25\7\25\u0109\n\25\f\25\16\25\u010c\13\25"+
		"\5\25\u010e\n\25\3\25\3\25\3\25\5\25\u0113\n\25\3\25\3\25\3\26\3\26\3"+
		"\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\5\27\u0121\n\27\3\30\3\30\3\30"+
		"\3\30\3\31\3\31\3\31\3\31\7\31\u012b\n\31\f\31\16\31\u012e\13\31\3\31"+
		"\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\7\33\u013c\n\33"+
		"\f\33\16\33\u013f\13\33\3\33\3\33\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3"+
		"\35\7\35\u014b\n\35\f\35\16\35\u014e\13\35\3\35\3\35\3\36\3\36\3\36\3"+
		"\36\7\36\u0156\n\36\f\36\16\36\u0159\13\36\5\36\u015b\n\36\3\36\3\36\3"+
		"\37\3\37\5\37\u0161\n\37\3\37\3\37\3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \5 \u016f"+
		"\n \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3&\2\4\6\f\'\2\4\6\b\n\f\16"+
		"\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJ\2\4\3\2\".\5\2"+
		"\20\21\35!<<\u018e\2L\3\2\2\2\4T\3\2\2\2\6\\\3\2\2\2\bk\3\2\2\2\nm\3\2"+
		"\2\2\fo\3\2\2\2\16\u0099\3\2\2\2\20\u009b\3\2\2\2\22\u009d\3\2\2\2\24"+
		"\u00ad\3\2\2\2\26\u00af\3\2\2\2\30\u00bd\3\2\2\2\32\u00c1\3\2\2\2\34\u00cc"+
		"\3\2\2\2\36\u00e0\3\2\2\2 \u00e2\3\2\2\2\"\u00e6\3\2\2\2$\u00e8\3\2\2"+
		"\2&\u00f3\3\2\2\2(\u0103\3\2\2\2*\u0116\3\2\2\2,\u0120\3\2\2\2.\u0122"+
		"\3\2\2\2\60\u0126\3\2\2\2\62\u0131\3\2\2\2\64\u0137\3\2\2\2\66\u0142\3"+
		"\2\2\28\u0146\3\2\2\2:\u0151\3\2\2\2<\u0160\3\2\2\2>\u016e\3\2\2\2@\u0170"+
		"\3\2\2\2B\u0172\3\2\2\2D\u0174\3\2\2\2F\u0176\3\2\2\2H\u0178\3\2\2\2J"+
		"\u017a\3\2\2\2LQ\5\4\3\2MN\7\67\2\2NP\5\4\3\2OM\3\2\2\2PS\3\2\2\2QO\3"+
		"\2\2\2QR\3\2\2\2R\3\3\2\2\2SQ\3\2\2\2TW\7<\2\2UV\78\2\2VX\5> \2WU\3\2"+
		"\2\2WX\3\2\2\2XY\3\2\2\2YZ\7\5\2\2Z[\5\6\4\2[\5\3\2\2\2\\]\b\4\1\2]^\5"+
		"\f\7\2^e\3\2\2\2_`\f\3\2\2`a\5\n\6\2ab\5\6\4\4bd\3\2\2\2c_\3\2\2\2dg\3"+
		"\2\2\2ec\3\2\2\2ef\3\2\2\2f\7\3\2\2\2ge\3\2\2\2hi\5\n\6\2ij\5\6\4\2jl"+
		"\3\2\2\2kh\3\2\2\2kl\3\2\2\2l\t\3\2\2\2mn\t\2\2\2n\13\3\2\2\2op\b\7\1"+
		"\2pq\5\16\b\2q\u008c\3\2\2\2rs\f\6\2\2s|\7\61\2\2ty\5\6\4\2uv\79\2\2v"+
		"x\5\6\4\2wu\3\2\2\2x{\3\2\2\2yw\3\2\2\2yz\3\2\2\2z}\3\2\2\2{y\3\2\2\2"+
		"|t\3\2\2\2|}\3\2\2\2}~\3\2\2\2~\u008b\7\62\2\2\177\u0080\f\5\2\2\u0080"+
		"\u0081\7\63\2\2\u0081\u0082\5\6\4\2\u0082\u0083\7\64\2\2\u0083\u008b\3"+
		"\2\2\2\u0084\u0085\f\4\2\2\u0085\u0086\7:\2\2\u0086\u008b\7<\2\2\u0087"+
		"\u0088\f\3\2\2\u0088\u0089\7:\2\2\u0089\u008b\7\35\2\2\u008ar\3\2\2\2"+
		"\u008a\177\3\2\2\2\u008a\u0084\3\2\2\2\u008a\u0087\3\2\2\2\u008b\u008e"+
		"\3\2\2\2\u008c\u008a\3\2\2\2\u008c\u008d\3\2\2\2\u008d\r\3\2\2\2\u008e"+
		"\u008c\3\2\2\2\u008f\u009a\5\20\t\2\u0090\u009a\5\22\n\2\u0091\u009a\5"+
		"(\25\2\u0092\u009a\5\60\31\2\u0093\u009a\5:\36\2\u0094\u009a\5\64\33\2"+
		"\u0095\u0096\7\61\2\2\u0096\u0097\5\6\4\2\u0097\u0098\7\62\2\2\u0098\u009a"+
		"\3\2\2\2\u0099\u008f\3\2\2\2\u0099\u0090\3\2\2\2\u0099\u0091\3\2\2\2\u0099"+
		"\u0092\3\2\2\2\u0099\u0093\3\2\2\2\u0099\u0094\3\2\2\2\u0099\u0095\3\2"+
		"\2\2\u009a\17\3\2\2\2\u009b\u009c\t\3\2\2\u009c\21\3\2\2\2\u009d\u009e"+
		"\7\6\2\2\u009e\u009f\5\6\4\2\u009f\u00a0\7\b\2\2\u00a0\u00a1\5\6\4\2\u00a1"+
		"\u00a2\7\t\2\2\u00a2\u00a3\5\6\4\2\u00a3\u00a4\7\n\2\2\u00a4\23\3\2\2"+
		"\2\u00a5\u00ae\5\30\r\2\u00a6\u00ae\5\26\f\2\u00a7\u00ae\5\32\16\2\u00a8"+
		"\u00ae\5\34\17\2\u00a9\u00ae\5 \21\2\u00aa\u00ae\5\"\22\2\u00ab\u00ae"+
		"\5$\23\2\u00ac\u00ae\5\4\3\2\u00ad\u00a5\3\2\2\2\u00ad\u00a6\3\2\2\2\u00ad"+
		"\u00a7\3\2\2\2\u00ad\u00a8\3\2\2\2\u00ad\u00a9\3\2\2\2\u00ad\u00aa\3\2"+
		"\2\2\u00ad\u00ab\3\2\2\2\u00ad\u00ac\3\2\2\2\u00ae\25\3\2\2\2\u00af\u00b0"+
		"\5\f\7\2\u00b0\u00b9\7\61\2\2\u00b1\u00b6\5\6\4\2\u00b2\u00b3\79\2\2\u00b3"+
		"\u00b5\5\6\4\2\u00b4\u00b2\3\2\2\2\u00b5\u00b8\3\2\2\2\u00b6\u00b4\3\2"+
		"\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00ba\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b9"+
		"\u00b1\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00bc\7\62"+
		"\2\2\u00bc\27\3\2\2\2\u00bd\u00be\5\f\7\2\u00be\u00bf\7\60\2\2\u00bf\u00c0"+
		"\5\6\4\2\u00c0\31\3\2\2\2\u00c1\u00c2\7\6\2\2\u00c2\u00c3\5\6\4\2\u00c3"+
		"\u00c4\7\b\2\2\u00c4\u00c7\5\24\13\2\u00c5\u00c6\7\t\2\2\u00c6\u00c8\5"+
		"\24\13\2\u00c7\u00c5\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9"+
		"\u00ca\7\n\2\2\u00ca\33\3\2\2\2\u00cb\u00cd\5\36\20\2\u00cc\u00cb\3\2"+
		"\2\2\u00cc\u00cd\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00d0\7\r\2\2\u00cf"+
		"\u00d1\5\24\13\2\u00d0\u00cf\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1\u00d2\3"+
		"\2\2\2\u00d2\u00d3\7\n\2\2\u00d3\35\3\2\2\2\u00d4\u00d7\7\16\2\2\u00d5"+
		"\u00d6\7<\2\2\u00d6\u00d8\7\7\2\2\u00d7\u00d5\3\2\2\2\u00d7\u00d8\3\2"+
		"\2\2\u00d8\u00d9\3\2\2\2\u00d9\u00dc\5\6\4\2\u00da\u00db\7;\2\2\u00db"+
		"\u00dd\5\6\4\2\u00dc\u00da\3\2\2\2\u00dc\u00dd\3\2\2\2\u00dd\u00e1\3\2"+
		"\2\2\u00de\u00df\7\17\2\2\u00df\u00e1\5\6\4\2\u00e0\u00d4\3\2\2\2\u00e0"+
		"\u00de\3\2\2\2\u00e1\37\3\2\2\2\u00e2\u00e4\7\31\2\2\u00e3\u00e5\5\6\4"+
		"\2\u00e4\u00e3\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5!\3\2\2\2\u00e6\u00e7"+
		"\7\32\2\2\u00e7#\3\2\2\2\u00e8\u00f1\7\30\2\2\u00e9\u00ee\5\6\4\2\u00ea"+
		"\u00eb\79\2\2\u00eb\u00ed\5\6\4\2\u00ec\u00ea\3\2\2\2\u00ed\u00f0\3\2"+
		"\2\2\u00ee\u00ec\3\2\2\2\u00ee\u00ef\3\2\2\2\u00ef\u00f2\3\2\2\2\u00f0"+
		"\u00ee\3\2\2\2\u00f1\u00e9\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f2%\3\2\2\2"+
		"\u00f3\u00f4\7\13\2\2\u00f4\u00fd\7\61\2\2\u00f5\u00fa\5> \2\u00f6\u00f7"+
		"\79\2\2\u00f7\u00f9\5> \2\u00f8\u00f6\3\2\2\2\u00f9\u00fc\3\2\2\2\u00fa"+
		"\u00f8\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb\u00fe\3\2\2\2\u00fc\u00fa\3\2"+
		"\2\2\u00fd\u00f5\3\2\2\2\u00fd\u00fe\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff"+
		"\u0100\7\62\2\2\u0100\u0101\78\2\2\u0101\u0102\5> \2\u0102\'\3\2\2\2\u0103"+
		"\u0104\7\13\2\2\u0104\u010d\7\61\2\2\u0105\u010a\5*\26\2\u0106\u0107\7"+
		"9\2\2\u0107\u0109\5*\26\2\u0108\u0106\3\2\2\2\u0109\u010c\3\2\2\2\u010a"+
		"\u0108\3\2\2\2\u010a\u010b\3\2\2\2\u010b\u010e\3\2\2\2\u010c\u010a\3\2"+
		"\2\2\u010d\u0105\3\2\2\2\u010d\u010e\3\2\2\2\u010e\u010f\3\2\2\2\u010f"+
		"\u0112\7\62\2\2\u0110\u0111\78\2\2\u0111\u0113\5> \2\u0112\u0110\3\2\2"+
		"\2\u0112\u0113\3\2\2\2\u0113\u0114\3\2\2\2\u0114\u0115\5,\27\2\u0115)"+
		"\3\2\2\2\u0116\u0117\7<\2\2\u0117\u0118\78\2\2\u0118\u0119\5> \2\u0119"+
		"+\3\2\2\2\u011a\u011b\7\f\2\2\u011b\u011c\5\24\13\2\u011c\u011d\7\n\2"+
		"\2\u011d\u0121\3\2\2\2\u011e\u011f\7/\2\2\u011f\u0121\5\6\4\2\u0120\u011a"+
		"\3\2\2\2\u0120\u011e\3\2\2\2\u0121-\3\2\2\2\u0122\u0123\7\63\2\2\u0123"+
		"\u0124\5> \2\u0124\u0125\7\64\2\2\u0125/\3\2\2\2\u0126\u0127\7\63\2\2"+
		"\u0127\u012c\5\6\4\2\u0128\u0129\79\2\2\u0129\u012b\5\6\4\2\u012a\u0128"+
		"\3\2\2\2\u012b\u012e\3\2\2\2\u012c\u012a\3\2\2\2\u012c\u012d\3\2\2\2\u012d"+
		"\u012f\3\2\2\2\u012e\u012c\3\2\2\2\u012f\u0130\7\64\2\2\u0130\61\3\2\2"+
		"\2\u0131\u0132\7\65\2\2\u0132\u0133\5> \2\u0133\u0134\78\2\2\u0134\u0135"+
		"\5> \2\u0135\u0136\7\66\2\2\u0136\63\3\2\2\2\u0137\u0138\7\65\2\2\u0138"+
		"\u013d\5\66\34\2\u0139\u013a\79\2\2\u013a\u013c\5\66\34\2\u013b\u0139"+
		"\3\2\2\2\u013c\u013f\3\2\2\2\u013d\u013b\3\2\2\2\u013d\u013e\3\2\2\2\u013e"+
		"\u0140\3\2\2\2\u013f\u013d\3\2\2\2\u0140\u0141\7\66\2\2\u0141\65\3\2\2"+
		"\2\u0142\u0143\5\6\4\2\u0143\u0144\78\2\2\u0144\u0145\5\6\4\2\u0145\67"+
		"\3\2\2\2\u0146\u0147\7\61\2\2\u0147\u014c\5> \2\u0148\u0149\79\2\2\u0149"+
		"\u014b\5> \2\u014a\u0148\3\2\2\2\u014b\u014e\3\2\2\2\u014c\u014a\3\2\2"+
		"\2\u014c\u014d\3\2\2\2\u014d\u014f\3\2\2\2\u014e\u014c\3\2\2\2\u014f\u0150"+
		"\7\62\2\2\u01509\3\2\2\2\u0151\u015a\7\61\2\2\u0152\u0157\5<\37\2\u0153"+
		"\u0154\79\2\2\u0154\u0156\5<\37\2\u0155\u0153\3\2\2\2\u0156\u0159\3\2"+
		"\2\2\u0157\u0155\3\2\2\2\u0157\u0158\3\2\2\2\u0158\u015b\3\2\2\2\u0159"+
		"\u0157\3\2\2\2\u015a\u0152\3\2\2\2\u015a\u015b\3\2\2\2\u015b\u015c\3\2"+
		"\2\2\u015c\u015d\7\62\2\2\u015d;\3\2\2\2\u015e\u015f\7<\2\2\u015f\u0161"+
		"\7\5\2\2\u0160\u015e\3\2\2\2\u0160\u0161\3\2\2\2\u0161\u0162\3\2\2\2\u0162"+
		"\u0163\5\6\4\2\u0163=\3\2\2\2\u0164\u016f\5@!\2\u0165\u016f\5B\"\2\u0166"+
		"\u016f\5D#\2\u0167\u016f\5F$\2\u0168\u016f\5H%\2\u0169\u016f\5J&\2\u016a"+
		"\u016f\5&\24\2\u016b\u016f\58\35\2\u016c\u016f\5.\30\2\u016d\u016f\5\62"+
		"\32\2\u016e\u0164\3\2\2\2\u016e\u0165\3\2\2\2\u016e\u0166\3\2\2\2\u016e"+
		"\u0167\3\2\2\2\u016e\u0168\3\2\2\2\u016e\u0169\3\2\2\2\u016e\u016a\3\2"+
		"\2\2\u016e\u016b\3\2\2\2\u016e\u016c\3\2\2\2\u016e\u016d\3\2\2\2\u016f"+
		"?\3\2\2\2\u0170\u0171\7\24\2\2\u0171A\3\2\2\2\u0172\u0173\7\22\2\2\u0173"+
		"C\3\2\2\2\u0174\u0175\7\23\2\2\u0175E\3\2\2\2\u0176\u0177\7\25\2\2\u0177"+
		"G\3\2\2\2\u0178\u0179\7\26\2\2\u0179I\3\2\2\2\u017a\u017b\7\27\2\2\u017b"+
		"K\3\2\2\2$QWeky|\u008a\u008c\u0099\u00ad\u00b6\u00b9\u00c7\u00cc\u00d0"+
		"\u00d7\u00dc\u00e0\u00e4\u00ee\u00f1\u00fa\u00fd\u010a\u010d\u0112\u0120"+
		"\u012c\u013d\u014c\u0157\u015a\u0160\u016e";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}