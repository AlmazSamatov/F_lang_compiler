// Generated from FLangLexer.g4 by ANTLR 4.5.1
package me.team.fproject;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FLangLexer extends Lexer {
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
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"NEWLINE", "WS", "IS", "IF", "IN", "THEN", "ELSE", "END", "FUNC", "DO", 
		"LOOP", "FOR", "WHILE", "TRUE", "FALSE", "INTEGER", "REAL", "BOOLEAN", 
		"RATIONAL", "COMPLEX", "STRING", "PRINT", "RETURN", "BREAK", "TYPE_CONV", 
		"LENGTH", "INT_LIT", "REAL_LIT", "RAT_LIT", "COMP_LIT", "STR_LIT", "PLUS", 
		"MINUS", "MULT", "DIVISION", "LESS", "GREATER", "LESS_EQ", "GREATER_EQ", 
		"EQUAL", "NOT_EQ", "AND", "OR", "XOR", "LINE_FUN", "ASSIGN", "LPAR", "RPAR", 
		"LSQUARE", "RSQUARE", "LCURLY", "RCURLY", "SEMI", "COLUMN", "COMMA", "DOT", 
		"DOTDOT", "ID"
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


	public FLangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "FLangLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2<\u0194\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\3\2\3\2\3\2"+
		"\5\2{\n\2\3\3\6\3~\n\3\r\3\16\3\177\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\5\3"+
		"\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n"+
		"\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3"+
		"\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3"+
		"\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3"+
		"\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3"+
		"\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3"+
		"\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3"+
		"\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3"+
		"\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\5\32\u011a"+
		"\n\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\7\34\u0126\n\34"+
		"\f\34\16\34\u0129\13\34\5\34\u012b\n\34\3\35\6\35\u012e\n\35\r\35\16\35"+
		"\u012f\3\35\3\35\6\35\u0134\n\35\r\35\16\35\u0135\3\36\3\36\3\36\3\36"+
		"\3\37\3\37\5\37\u013e\n\37\3\37\3\37\3\37\5\37\u0143\n\37\3 \3 \7 \u0147"+
		"\n \f \16 \u014a\13 \3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3"+
		"\'\3\'\3(\3(\3(\3)\3)\3*\3*\3*\3+\3+\3,\3,\3-\3-\3.\3.\3.\3/\3/\3/\3\60"+
		"\3\60\3\61\3\61\3\62\3\62\3\63\3\63\3\64\3\64\3\65\3\65\3\66\3\66\3\67"+
		"\3\67\38\38\39\39\3:\3:\3:\3;\7;\u0189\n;\f;\16;\u018c\13;\3;\3;\7;\u0190"+
		"\n;\f;\16;\u0193\13;\2\2<\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25"+
		"\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32"+
		"\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a"+
		"\62c\63e\64g\65i\66k\67m8o9q:s;u<\3\2\n\4\2\f\f\17\17\4\2\13\13\"\"\3"+
		"\2\63;\3\2\62;\3\2$$\3\2aa\3\2c|\6\2\62;C\\aac|\u01a5\2\3\3\2\2\2\2\5"+
		"\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2"+
		"\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33"+
		"\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2"+
		"\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2"+
		"\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2"+
		"\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K"+
		"\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2"+
		"\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2"+
		"\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q"+
		"\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\3z\3\2\2\2\5}\3\2\2\2\7\u0083\3\2\2\2\t"+
		"\u0086\3\2\2\2\13\u0089\3\2\2\2\r\u008c\3\2\2\2\17\u0091\3\2\2\2\21\u0096"+
		"\3\2\2\2\23\u009a\3\2\2\2\25\u009f\3\2\2\2\27\u00a2\3\2\2\2\31\u00a7\3"+
		"\2\2\2\33\u00ab\3\2\2\2\35\u00b1\3\2\2\2\37\u00b6\3\2\2\2!\u00bc\3\2\2"+
		"\2#\u00c4\3\2\2\2%\u00c9\3\2\2\2\'\u00d1\3\2\2\2)\u00da\3\2\2\2+\u00e2"+
		"\3\2\2\2-\u00e9\3\2\2\2/\u00ef\3\2\2\2\61\u00f6\3\2\2\2\63\u0119\3\2\2"+
		"\2\65\u011b\3\2\2\2\67\u012a\3\2\2\29\u012d\3\2\2\2;\u0137\3\2\2\2=\u013d"+
		"\3\2\2\2?\u0144\3\2\2\2A\u014d\3\2\2\2C\u014f\3\2\2\2E\u0151\3\2\2\2G"+
		"\u0153\3\2\2\2I\u0155\3\2\2\2K\u0157\3\2\2\2M\u0159\3\2\2\2O\u015c\3\2"+
		"\2\2Q\u015f\3\2\2\2S\u0161\3\2\2\2U\u0164\3\2\2\2W\u0166\3\2\2\2Y\u0168"+
		"\3\2\2\2[\u016a\3\2\2\2]\u016d\3\2\2\2_\u0170\3\2\2\2a\u0172\3\2\2\2c"+
		"\u0174\3\2\2\2e\u0176\3\2\2\2g\u0178\3\2\2\2i\u017a\3\2\2\2k\u017c\3\2"+
		"\2\2m\u017e\3\2\2\2o\u0180\3\2\2\2q\u0182\3\2\2\2s\u0184\3\2\2\2u\u018a"+
		"\3\2\2\2wx\7\17\2\2x{\7\f\2\2y{\t\2\2\2zw\3\2\2\2zy\3\2\2\2{\4\3\2\2\2"+
		"|~\t\3\2\2}|\3\2\2\2~\177\3\2\2\2\177}\3\2\2\2\177\u0080\3\2\2\2\u0080"+
		"\u0081\3\2\2\2\u0081\u0082\b\3\2\2\u0082\6\3\2\2\2\u0083\u0084\7k\2\2"+
		"\u0084\u0085\7u\2\2\u0085\b\3\2\2\2\u0086\u0087\7k\2\2\u0087\u0088\7h"+
		"\2\2\u0088\n\3\2\2\2\u0089\u008a\7k\2\2\u008a\u008b\7p\2\2\u008b\f\3\2"+
		"\2\2\u008c\u008d\7v\2\2\u008d\u008e\7j\2\2\u008e\u008f\7g\2\2\u008f\u0090"+
		"\7p\2\2\u0090\16\3\2\2\2\u0091\u0092\7g\2\2\u0092\u0093\7n\2\2\u0093\u0094"+
		"\7u\2\2\u0094\u0095\7g\2\2\u0095\20\3\2\2\2\u0096\u0097\7g\2\2\u0097\u0098"+
		"\7p\2\2\u0098\u0099\7f\2\2\u0099\22\3\2\2\2\u009a\u009b\7h\2\2\u009b\u009c"+
		"\7w\2\2\u009c\u009d\7p\2\2\u009d\u009e\7e\2\2\u009e\24\3\2\2\2\u009f\u00a0"+
		"\7f\2\2\u00a0\u00a1\7q\2\2\u00a1\26\3\2\2\2\u00a2\u00a3\7n\2\2\u00a3\u00a4"+
		"\7q\2\2\u00a4\u00a5\7q\2\2\u00a5\u00a6\7r\2\2\u00a6\30\3\2\2\2\u00a7\u00a8"+
		"\7h\2\2\u00a8\u00a9\7q\2\2\u00a9\u00aa\7t\2\2\u00aa\32\3\2\2\2\u00ab\u00ac"+
		"\7y\2\2\u00ac\u00ad\7j\2\2\u00ad\u00ae\7k\2\2\u00ae\u00af\7n\2\2\u00af"+
		"\u00b0\7g\2\2\u00b0\34\3\2\2\2\u00b1\u00b2\7v\2\2\u00b2\u00b3\7t\2\2\u00b3"+
		"\u00b4\7w\2\2\u00b4\u00b5\7g\2\2\u00b5\36\3\2\2\2\u00b6\u00b7\7h\2\2\u00b7"+
		"\u00b8\7c\2\2\u00b8\u00b9\7n\2\2\u00b9\u00ba\7u\2\2\u00ba\u00bb\7g\2\2"+
		"\u00bb \3\2\2\2\u00bc\u00bd\7k\2\2\u00bd\u00be\7p\2\2\u00be\u00bf\7v\2"+
		"\2\u00bf\u00c0\7g\2\2\u00c0\u00c1\7i\2\2\u00c1\u00c2\7g\2\2\u00c2\u00c3"+
		"\7t\2\2\u00c3\"\3\2\2\2\u00c4\u00c5\7t\2\2\u00c5\u00c6\7g\2\2\u00c6\u00c7"+
		"\7c\2\2\u00c7\u00c8\7n\2\2\u00c8$\3\2\2\2\u00c9\u00ca\7d\2\2\u00ca\u00cb"+
		"\7q\2\2\u00cb\u00cc\7q\2\2\u00cc\u00cd\7n\2\2\u00cd\u00ce\7g\2\2\u00ce"+
		"\u00cf\7c\2\2\u00cf\u00d0\7p\2\2\u00d0&\3\2\2\2\u00d1\u00d2\7t\2\2\u00d2"+
		"\u00d3\7c\2\2\u00d3\u00d4\7v\2\2\u00d4\u00d5\7k\2\2\u00d5\u00d6\7q\2\2"+
		"\u00d6\u00d7\7p\2\2\u00d7\u00d8\7c\2\2\u00d8\u00d9\7n\2\2\u00d9(\3\2\2"+
		"\2\u00da\u00db\7e\2\2\u00db\u00dc\7q\2\2\u00dc\u00dd\7o\2\2\u00dd\u00de"+
		"\7r\2\2\u00de\u00df\7n\2\2\u00df\u00e0\7g\2\2\u00e0\u00e1\7z\2\2\u00e1"+
		"*\3\2\2\2\u00e2\u00e3\7u\2\2\u00e3\u00e4\7v\2\2\u00e4\u00e5\7t\2\2\u00e5"+
		"\u00e6\7k\2\2\u00e6\u00e7\7p\2\2\u00e7\u00e8\7i\2\2\u00e8,\3\2\2\2\u00e9"+
		"\u00ea\7r\2\2\u00ea\u00eb\7t\2\2\u00eb\u00ec\7k\2\2\u00ec\u00ed\7p\2\2"+
		"\u00ed\u00ee\7v\2\2\u00ee.\3\2\2\2\u00ef\u00f0\7t\2\2\u00f0\u00f1\7g\2"+
		"\2\u00f1\u00f2\7v\2\2\u00f2\u00f3\7w\2\2\u00f3\u00f4\7t\2\2\u00f4\u00f5"+
		"\7p\2\2\u00f5\60\3\2\2\2\u00f6\u00f7\7d\2\2\u00f7\u00f8\7t\2\2\u00f8\u00f9"+
		"\7g\2\2\u00f9\u00fa\7c\2\2\u00fa\u00fb\7m\2\2\u00fb\62\3\2\2\2\u00fc\u00fd"+
		"\7t\2\2\u00fd\u00fe\7q\2\2\u00fe\u00ff\7w\2\2\u00ff\u0100\7p\2\2\u0100"+
		"\u011a\7f\2\2\u0101\u0102\7t\2\2\u0102\u011a\7g\2\2\u0103\u0104\7k\2\2"+
		"\u0104\u011a\7o\2\2\u0105\u0106\7p\2\2\u0106\u0107\7w\2\2\u0107\u011a"+
		"\7o\2\2\u0108\u0109\7f\2\2\u0109\u010a\7g\2\2\u010a\u010b\7p\2\2\u010b"+
		"\u010c\7q\2\2\u010c\u011a\7o\2\2\u010d\u010e\7e\2\2\u010e\u010f\7q\2\2"+
		"\u010f\u0110\7o\2\2\u0110\u0111\7r\2\2\u0111\u011a\7n\2\2\u0112\u0113"+
		"\7t\2\2\u0113\u0114\7c\2\2\u0114\u011a\7v\2\2\u0115\u0116\7p\2\2\u0116"+
		"\u0117\7q\2\2\u0117\u0118\7t\2\2\u0118\u011a\7o\2\2\u0119\u00fc\3\2\2"+
		"\2\u0119\u0101\3\2\2\2\u0119\u0103\3\2\2\2\u0119\u0105\3\2\2\2\u0119\u0108"+
		"\3\2\2\2\u0119\u010d\3\2\2\2\u0119\u0112\3\2\2\2\u0119\u0115\3\2\2\2\u011a"+
		"\64\3\2\2\2\u011b\u011c\7n\2\2\u011c\u011d\7g\2\2\u011d\u011e\7p\2\2\u011e"+
		"\u011f\7i\2\2\u011f\u0120\7v\2\2\u0120\u0121\7j\2\2\u0121\66\3\2\2\2\u0122"+
		"\u012b\7\62\2\2\u0123\u0127\t\4\2\2\u0124\u0126\t\5\2\2\u0125\u0124\3"+
		"\2\2\2\u0126\u0129\3\2\2\2\u0127\u0125\3\2\2\2\u0127\u0128\3\2\2\2\u0128"+
		"\u012b\3\2\2\2\u0129\u0127\3\2\2\2\u012a\u0122\3\2\2\2\u012a\u0123\3\2"+
		"\2\2\u012b8\3\2\2\2\u012c\u012e\t\5\2\2\u012d\u012c\3\2\2\2\u012e\u012f"+
		"\3\2\2\2\u012f\u012d\3\2\2\2\u012f\u0130\3\2\2\2\u0130\u0131\3\2\2\2\u0131"+
		"\u0133\7\60\2\2\u0132\u0134\t\5\2\2\u0133\u0132\3\2\2\2\u0134\u0135\3"+
		"\2\2\2\u0135\u0133\3\2\2\2\u0135\u0136\3\2\2\2\u0136:\3\2\2\2\u0137\u0138"+
		"\5\67\34\2\u0138\u0139\7^\2\2\u0139\u013a\5\67\34\2\u013a<\3\2\2\2\u013b"+
		"\u013e\5\67\34\2\u013c\u013e\59\35\2\u013d\u013b\3\2\2\2\u013d\u013c\3"+
		"\2\2\2\u013e\u013f\3\2\2\2\u013f\u0142\7k\2\2\u0140\u0143\5\67\34\2\u0141"+
		"\u0143\59\35\2\u0142\u0140\3\2\2\2\u0142\u0141\3\2\2\2\u0143>\3\2\2\2"+
		"\u0144\u0148\7$\2\2\u0145\u0147\n\6\2\2\u0146\u0145\3\2\2\2\u0147\u014a"+
		"\3\2\2\2\u0148\u0146\3\2\2\2\u0148\u0149\3\2\2\2\u0149\u014b\3\2\2\2\u014a"+
		"\u0148\3\2\2\2\u014b\u014c\7$\2\2\u014c@\3\2\2\2\u014d\u014e\7-\2\2\u014e"+
		"B\3\2\2\2\u014f\u0150\7/\2\2\u0150D\3\2\2\2\u0151\u0152\7,\2\2\u0152F"+
		"\3\2\2\2\u0153\u0154\7\61\2\2\u0154H\3\2\2\2\u0155\u0156\7>\2\2\u0156"+
		"J\3\2\2\2\u0157\u0158\7@\2\2\u0158L\3\2\2\2\u0159\u015a\7>\2\2\u015a\u015b"+
		"\7?\2\2\u015bN\3\2\2\2\u015c\u015d\7@\2\2\u015d\u015e\7?\2\2\u015eP\3"+
		"\2\2\2\u015f\u0160\7?\2\2\u0160R\3\2\2\2\u0161\u0162\7\61\2\2\u0162\u0163"+
		"\7?\2\2\u0163T\3\2\2\2\u0164\u0165\7(\2\2\u0165V\3\2\2\2\u0166\u0167\7"+
		"~\2\2\u0167X\3\2\2\2\u0168\u0169\7`\2\2\u0169Z\3\2\2\2\u016a\u016b\7?"+
		"\2\2\u016b\u016c\7@\2\2\u016c\\\3\2\2\2\u016d\u016e\7<\2\2\u016e\u016f"+
		"\7?\2\2\u016f^\3\2\2\2\u0170\u0171\7*\2\2\u0171`\3\2\2\2\u0172\u0173\7"+
		"+\2\2\u0173b\3\2\2\2\u0174\u0175\7]\2\2\u0175d\3\2\2\2\u0176\u0177\7_"+
		"\2\2\u0177f\3\2\2\2\u0178\u0179\7}\2\2\u0179h\3\2\2\2\u017a\u017b\7\177"+
		"\2\2\u017bj\3\2\2\2\u017c\u017d\7=\2\2\u017dl\3\2\2\2\u017e\u017f\7<\2"+
		"\2\u017fn\3\2\2\2\u0180\u0181\7.\2\2\u0181p\3\2\2\2\u0182\u0183\7\60\2"+
		"\2\u0183r\3\2\2\2\u0184\u0185\7\60\2\2\u0185\u0186\7\60\2\2\u0186t\3\2"+
		"\2\2\u0187\u0189\t\7\2\2\u0188\u0187\3\2\2\2\u0189\u018c\3\2\2\2\u018a"+
		"\u0188\3\2\2\2\u018a\u018b\3\2\2\2\u018b\u018d\3\2\2\2\u018c\u018a\3\2"+
		"\2\2\u018d\u0191\t\b\2\2\u018e\u0190\t\t\2\2\u018f\u018e\3\2\2\2\u0190"+
		"\u0193\3\2\2\2\u0191\u018f\3\2\2\2\u0191\u0192\3\2\2\2\u0192v\3\2\2\2"+
		"\u0193\u0191\3\2\2\2\17\2z\177\u0119\u0127\u012a\u012f\u0135\u013d\u0142"+
		"\u0148\u018a\u0191\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}