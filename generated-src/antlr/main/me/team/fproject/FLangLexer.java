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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2<\u0196\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\3\2\3\2\3\2"+
		"\5\2{\n\2\3\2\3\2\3\3\6\3\u0080\n\3\r\3\16\3\u0081\3\3\3\3\3\4\3\4\3\4"+
		"\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3"+
		"\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r"+
		"\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3"+
		"\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3"+
		"\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3"+
		"\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3"+
		"\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3"+
		"\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3"+
		"\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3"+
		"\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3"+
		"\32\5\32\u011c\n\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34"+
		"\7\34\u0128\n\34\f\34\16\34\u012b\13\34\5\34\u012d\n\34\3\35\6\35\u0130"+
		"\n\35\r\35\16\35\u0131\3\35\3\35\6\35\u0136\n\35\r\35\16\35\u0137\3\36"+
		"\3\36\3\36\3\36\3\37\3\37\5\37\u0140\n\37\3\37\3\37\3\37\5\37\u0145\n"+
		"\37\3 \3 \7 \u0149\n \f \16 \u014c\13 \3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3"+
		"$\3%\3%\3&\3&\3\'\3\'\3\'\3(\3(\3(\3)\3)\3*\3*\3*\3+\3+\3,\3,\3-\3-\3"+
		".\3.\3.\3/\3/\3/\3\60\3\60\3\61\3\61\3\62\3\62\3\63\3\63\3\64\3\64\3\65"+
		"\3\65\3\66\3\66\3\67\3\67\38\38\39\39\3:\3:\3:\3;\7;\u018b\n;\f;\16;\u018e"+
		"\13;\3;\3;\7;\u0192\n;\f;\16;\u0195\13;\2\2<\3\3\5\4\7\5\t\6\13\7\r\b"+
		"\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26"+
		"+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S"+
		"+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67m8o9q:s;u<\3\2\n\4\2\f\f\17\17"+
		"\4\2\13\13\"\"\3\2\63;\3\2\62;\3\2$$\3\2aa\3\2c|\6\2\62;C\\aac|\u01a7"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2"+
		"\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3"+
		"\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2"+
		"\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2"+
		"U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3"+
		"\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2"+
		"\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\3z\3\2\2\2\5\177\3\2\2"+
		"\2\7\u0085\3\2\2\2\t\u0088\3\2\2\2\13\u008b\3\2\2\2\r\u008e\3\2\2\2\17"+
		"\u0093\3\2\2\2\21\u0098\3\2\2\2\23\u009c\3\2\2\2\25\u00a1\3\2\2\2\27\u00a4"+
		"\3\2\2\2\31\u00a9\3\2\2\2\33\u00ad\3\2\2\2\35\u00b3\3\2\2\2\37\u00b8\3"+
		"\2\2\2!\u00be\3\2\2\2#\u00c6\3\2\2\2%\u00cb\3\2\2\2\'\u00d3\3\2\2\2)\u00dc"+
		"\3\2\2\2+\u00e4\3\2\2\2-\u00eb\3\2\2\2/\u00f1\3\2\2\2\61\u00f8\3\2\2\2"+
		"\63\u011b\3\2\2\2\65\u011d\3\2\2\2\67\u012c\3\2\2\29\u012f\3\2\2\2;\u0139"+
		"\3\2\2\2=\u013f\3\2\2\2?\u0146\3\2\2\2A\u014f\3\2\2\2C\u0151\3\2\2\2E"+
		"\u0153\3\2\2\2G\u0155\3\2\2\2I\u0157\3\2\2\2K\u0159\3\2\2\2M\u015b\3\2"+
		"\2\2O\u015e\3\2\2\2Q\u0161\3\2\2\2S\u0163\3\2\2\2U\u0166\3\2\2\2W\u0168"+
		"\3\2\2\2Y\u016a\3\2\2\2[\u016c\3\2\2\2]\u016f\3\2\2\2_\u0172\3\2\2\2a"+
		"\u0174\3\2\2\2c\u0176\3\2\2\2e\u0178\3\2\2\2g\u017a\3\2\2\2i\u017c\3\2"+
		"\2\2k\u017e\3\2\2\2m\u0180\3\2\2\2o\u0182\3\2\2\2q\u0184\3\2\2\2s\u0186"+
		"\3\2\2\2u\u018c\3\2\2\2wx\7\17\2\2x{\7\f\2\2y{\t\2\2\2zw\3\2\2\2zy\3\2"+
		"\2\2{|\3\2\2\2|}\b\2\2\2}\4\3\2\2\2~\u0080\t\3\2\2\177~\3\2\2\2\u0080"+
		"\u0081\3\2\2\2\u0081\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0083\3\2\2"+
		"\2\u0083\u0084\b\3\2\2\u0084\6\3\2\2\2\u0085\u0086\7k\2\2\u0086\u0087"+
		"\7u\2\2\u0087\b\3\2\2\2\u0088\u0089\7k\2\2\u0089\u008a\7h\2\2\u008a\n"+
		"\3\2\2\2\u008b\u008c\7k\2\2\u008c\u008d\7p\2\2\u008d\f\3\2\2\2\u008e\u008f"+
		"\7v\2\2\u008f\u0090\7j\2\2\u0090\u0091\7g\2\2\u0091\u0092\7p\2\2\u0092"+
		"\16\3\2\2\2\u0093\u0094\7g\2\2\u0094\u0095\7n\2\2\u0095\u0096\7u\2\2\u0096"+
		"\u0097\7g\2\2\u0097\20\3\2\2\2\u0098\u0099\7g\2\2\u0099\u009a\7p\2\2\u009a"+
		"\u009b\7f\2\2\u009b\22\3\2\2\2\u009c\u009d\7h\2\2\u009d\u009e\7w\2\2\u009e"+
		"\u009f\7p\2\2\u009f\u00a0\7e\2\2\u00a0\24\3\2\2\2\u00a1\u00a2\7f\2\2\u00a2"+
		"\u00a3\7q\2\2\u00a3\26\3\2\2\2\u00a4\u00a5\7n\2\2\u00a5\u00a6\7q\2\2\u00a6"+
		"\u00a7\7q\2\2\u00a7\u00a8\7r\2\2\u00a8\30\3\2\2\2\u00a9\u00aa\7h\2\2\u00aa"+
		"\u00ab\7q\2\2\u00ab\u00ac\7t\2\2\u00ac\32\3\2\2\2\u00ad\u00ae\7y\2\2\u00ae"+
		"\u00af\7j\2\2\u00af\u00b0\7k\2\2\u00b0\u00b1\7n\2\2\u00b1\u00b2\7g\2\2"+
		"\u00b2\34\3\2\2\2\u00b3\u00b4\7v\2\2\u00b4\u00b5\7t\2\2\u00b5\u00b6\7"+
		"w\2\2\u00b6\u00b7\7g\2\2\u00b7\36\3\2\2\2\u00b8\u00b9\7h\2\2\u00b9\u00ba"+
		"\7c\2\2\u00ba\u00bb\7n\2\2\u00bb\u00bc\7u\2\2\u00bc\u00bd\7g\2\2\u00bd"+
		" \3\2\2\2\u00be\u00bf\7k\2\2\u00bf\u00c0\7p\2\2\u00c0\u00c1\7v\2\2\u00c1"+
		"\u00c2\7g\2\2\u00c2\u00c3\7i\2\2\u00c3\u00c4\7g\2\2\u00c4\u00c5\7t\2\2"+
		"\u00c5\"\3\2\2\2\u00c6\u00c7\7t\2\2\u00c7\u00c8\7g\2\2\u00c8\u00c9\7c"+
		"\2\2\u00c9\u00ca\7n\2\2\u00ca$\3\2\2\2\u00cb\u00cc\7d\2\2\u00cc\u00cd"+
		"\7q\2\2\u00cd\u00ce\7q\2\2\u00ce\u00cf\7n\2\2\u00cf\u00d0\7g\2\2\u00d0"+
		"\u00d1\7c\2\2\u00d1\u00d2\7p\2\2\u00d2&\3\2\2\2\u00d3\u00d4\7t\2\2\u00d4"+
		"\u00d5\7c\2\2\u00d5\u00d6\7v\2\2\u00d6\u00d7\7k\2\2\u00d7\u00d8\7q\2\2"+
		"\u00d8\u00d9\7p\2\2\u00d9\u00da\7c\2\2\u00da\u00db\7n\2\2\u00db(\3\2\2"+
		"\2\u00dc\u00dd\7e\2\2\u00dd\u00de\7q\2\2\u00de\u00df\7o\2\2\u00df\u00e0"+
		"\7r\2\2\u00e0\u00e1\7n\2\2\u00e1\u00e2\7g\2\2\u00e2\u00e3\7z\2\2\u00e3"+
		"*\3\2\2\2\u00e4\u00e5\7u\2\2\u00e5\u00e6\7v\2\2\u00e6\u00e7\7t\2\2\u00e7"+
		"\u00e8\7k\2\2\u00e8\u00e9\7p\2\2\u00e9\u00ea\7i\2\2\u00ea,\3\2\2\2\u00eb"+
		"\u00ec\7r\2\2\u00ec\u00ed\7t\2\2\u00ed\u00ee\7k\2\2\u00ee\u00ef\7p\2\2"+
		"\u00ef\u00f0\7v\2\2\u00f0.\3\2\2\2\u00f1\u00f2\7t\2\2\u00f2\u00f3\7g\2"+
		"\2\u00f3\u00f4\7v\2\2\u00f4\u00f5\7w\2\2\u00f5\u00f6\7t\2\2\u00f6\u00f7"+
		"\7p\2\2\u00f7\60\3\2\2\2\u00f8\u00f9\7d\2\2\u00f9\u00fa\7t\2\2\u00fa\u00fb"+
		"\7g\2\2\u00fb\u00fc\7c\2\2\u00fc\u00fd\7m\2\2\u00fd\62\3\2\2\2\u00fe\u00ff"+
		"\7t\2\2\u00ff\u0100\7q\2\2\u0100\u0101\7w\2\2\u0101\u0102\7p\2\2\u0102"+
		"\u011c\7f\2\2\u0103\u0104\7t\2\2\u0104\u011c\7g\2\2\u0105\u0106\7k\2\2"+
		"\u0106\u011c\7o\2\2\u0107\u0108\7p\2\2\u0108\u0109\7w\2\2\u0109\u011c"+
		"\7o\2\2\u010a\u010b\7f\2\2\u010b\u010c\7g\2\2\u010c\u010d\7p\2\2\u010d"+
		"\u010e\7q\2\2\u010e\u011c\7o\2\2\u010f\u0110\7e\2\2\u0110\u0111\7q\2\2"+
		"\u0111\u0112\7o\2\2\u0112\u0113\7r\2\2\u0113\u011c\7n\2\2\u0114\u0115"+
		"\7t\2\2\u0115\u0116\7c\2\2\u0116\u011c\7v\2\2\u0117\u0118\7p\2\2\u0118"+
		"\u0119\7q\2\2\u0119\u011a\7t\2\2\u011a\u011c\7o\2\2\u011b\u00fe\3\2\2"+
		"\2\u011b\u0103\3\2\2\2\u011b\u0105\3\2\2\2\u011b\u0107\3\2\2\2\u011b\u010a"+
		"\3\2\2\2\u011b\u010f\3\2\2\2\u011b\u0114\3\2\2\2\u011b\u0117\3\2\2\2\u011c"+
		"\64\3\2\2\2\u011d\u011e\7n\2\2\u011e\u011f\7g\2\2\u011f\u0120\7p\2\2\u0120"+
		"\u0121\7i\2\2\u0121\u0122\7v\2\2\u0122\u0123\7j\2\2\u0123\66\3\2\2\2\u0124"+
		"\u012d\7\62\2\2\u0125\u0129\t\4\2\2\u0126\u0128\t\5\2\2\u0127\u0126\3"+
		"\2\2\2\u0128\u012b\3\2\2\2\u0129\u0127\3\2\2\2\u0129\u012a\3\2\2\2\u012a"+
		"\u012d\3\2\2\2\u012b\u0129\3\2\2\2\u012c\u0124\3\2\2\2\u012c\u0125\3\2"+
		"\2\2\u012d8\3\2\2\2\u012e\u0130\t\5\2\2\u012f\u012e\3\2\2\2\u0130\u0131"+
		"\3\2\2\2\u0131\u012f\3\2\2\2\u0131\u0132\3\2\2\2\u0132\u0133\3\2\2\2\u0133"+
		"\u0135\7\60\2\2\u0134\u0136\t\5\2\2\u0135\u0134\3\2\2\2\u0136\u0137\3"+
		"\2\2\2\u0137\u0135\3\2\2\2\u0137\u0138\3\2\2\2\u0138:\3\2\2\2\u0139\u013a"+
		"\5\67\34\2\u013a\u013b\7^\2\2\u013b\u013c\5\67\34\2\u013c<\3\2\2\2\u013d"+
		"\u0140\5\67\34\2\u013e\u0140\59\35\2\u013f\u013d\3\2\2\2\u013f\u013e\3"+
		"\2\2\2\u0140\u0141\3\2\2\2\u0141\u0144\7k\2\2\u0142\u0145\5\67\34\2\u0143"+
		"\u0145\59\35\2\u0144\u0142\3\2\2\2\u0144\u0143\3\2\2\2\u0145>\3\2\2\2"+
		"\u0146\u014a\7$\2\2\u0147\u0149\n\6\2\2\u0148\u0147\3\2\2\2\u0149\u014c"+
		"\3\2\2\2\u014a\u0148\3\2\2\2\u014a\u014b\3\2\2\2\u014b\u014d\3\2\2\2\u014c"+
		"\u014a\3\2\2\2\u014d\u014e\7$\2\2\u014e@\3\2\2\2\u014f\u0150\7-\2\2\u0150"+
		"B\3\2\2\2\u0151\u0152\7/\2\2\u0152D\3\2\2\2\u0153\u0154\7,\2\2\u0154F"+
		"\3\2\2\2\u0155\u0156\7\61\2\2\u0156H\3\2\2\2\u0157\u0158\7>\2\2\u0158"+
		"J\3\2\2\2\u0159\u015a\7@\2\2\u015aL\3\2\2\2\u015b\u015c\7>\2\2\u015c\u015d"+
		"\7?\2\2\u015dN\3\2\2\2\u015e\u015f\7@\2\2\u015f\u0160\7?\2\2\u0160P\3"+
		"\2\2\2\u0161\u0162\7?\2\2\u0162R\3\2\2\2\u0163\u0164\7\61\2\2\u0164\u0165"+
		"\7?\2\2\u0165T\3\2\2\2\u0166\u0167\7(\2\2\u0167V\3\2\2\2\u0168\u0169\7"+
		"~\2\2\u0169X\3\2\2\2\u016a\u016b\7`\2\2\u016bZ\3\2\2\2\u016c\u016d\7?"+
		"\2\2\u016d\u016e\7@\2\2\u016e\\\3\2\2\2\u016f\u0170\7<\2\2\u0170\u0171"+
		"\7?\2\2\u0171^\3\2\2\2\u0172\u0173\7*\2\2\u0173`\3\2\2\2\u0174\u0175\7"+
		"+\2\2\u0175b\3\2\2\2\u0176\u0177\7]\2\2\u0177d\3\2\2\2\u0178\u0179\7_"+
		"\2\2\u0179f\3\2\2\2\u017a\u017b\7}\2\2\u017bh\3\2\2\2\u017c\u017d\7\177"+
		"\2\2\u017dj\3\2\2\2\u017e\u017f\7=\2\2\u017fl\3\2\2\2\u0180\u0181\7<\2"+
		"\2\u0181n\3\2\2\2\u0182\u0183\7.\2\2\u0183p\3\2\2\2\u0184\u0185\7\60\2"+
		"\2\u0185r\3\2\2\2\u0186\u0187\7\60\2\2\u0187\u0188\7\60\2\2\u0188t\3\2"+
		"\2\2\u0189\u018b\t\7\2\2\u018a\u0189\3\2\2\2\u018b\u018e\3\2\2\2\u018c"+
		"\u018a\3\2\2\2\u018c\u018d\3\2\2\2\u018d\u018f\3\2\2\2\u018e\u018c\3\2"+
		"\2\2\u018f\u0193\t\b\2\2\u0190\u0192\t\t\2\2\u0191\u0190\3\2\2\2\u0192"+
		"\u0195\3\2\2\2\u0193\u0191\3\2\2\2\u0193\u0194\3\2\2\2\u0194v\3\2\2\2"+
		"\u0195\u0193\3\2\2\2\17\2z\u0081\u011b\u0129\u012c\u0131\u0137\u013f\u0144"+
		"\u014a\u018c\u0193\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}