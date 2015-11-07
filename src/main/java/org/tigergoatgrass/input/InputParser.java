// Generated from Input.g4 by ANTLR 4.5
package org.tigergoatgrass.input;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class InputParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		OPENROW=1, CLOSEROW=2, SEPARATOR=3, NEWLINE=4, AFFINITY=5, NAME=6, WS=7;
	public static final int
		RULE_start = 0, RULE_items = 1, RULE_matrix = 2, RULE_row = 3;
	public static final String[] ruleNames = {
		"start", "items", "matrix", "row"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'['", "']'", "','"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "OPENROW", "CLOSEROW", "SEPARATOR", "NEWLINE", "AFFINITY", "NAME", 
		"WS"
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
	@NotNull
	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Input.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public InputParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class StartContext extends ParserRuleContext {
		public ItemsContext items() {
			return getRuleContext(ItemsContext.class,0);
		}
		public MatrixContext matrix() {
			return getRuleContext(MatrixContext.class,0);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InputListener ) ((InputListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InputListener ) ((InputListener)listener).exitStart(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(8); 
			items();
			setState(9); 
			matrix();
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

	public static class ItemsContext extends ParserRuleContext {
		public TerminalNode OPENROW() { return getToken(InputParser.OPENROW, 0); }
		public List<TerminalNode> NAME() { return getTokens(InputParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(InputParser.NAME, i);
		}
		public TerminalNode CLOSEROW() { return getToken(InputParser.CLOSEROW, 0); }
		public TerminalNode NEWLINE() { return getToken(InputParser.NEWLINE, 0); }
		public List<TerminalNode> SEPARATOR() { return getTokens(InputParser.SEPARATOR); }
		public TerminalNode SEPARATOR(int i) {
			return getToken(InputParser.SEPARATOR, i);
		}
		public ItemsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_items; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InputListener ) ((InputListener)listener).enterItems(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InputListener ) ((InputListener)listener).exitItems(this);
		}
	}

	public final ItemsContext items() throws RecognitionException {
		ItemsContext _localctx = new ItemsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_items);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(11); 
			match(OPENROW);
			setState(12); 
			match(NAME);
			setState(17);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEPARATOR) {
				{
				{
				setState(13); 
				match(SEPARATOR);
				setState(14); 
				match(NAME);
				}
				}
				setState(19);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(20); 
			match(CLOSEROW);
			setState(21); 
			match(NEWLINE);
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

	public static class MatrixContext extends ParserRuleContext {
		public List<RowContext> row() {
			return getRuleContexts(RowContext.class);
		}
		public RowContext row(int i) {
			return getRuleContext(RowContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(InputParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(InputParser.NEWLINE, i);
		}
		public MatrixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matrix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InputListener ) ((InputListener)listener).enterMatrix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InputListener ) ((InputListener)listener).exitMatrix(this);
		}
	}

	public final MatrixContext matrix() throws RecognitionException {
		MatrixContext _localctx = new MatrixContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_matrix);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(23); 
				row();
				setState(24); 
				match(NEWLINE);
				}
				}
				setState(28); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==OPENROW );
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

	public static class RowContext extends ParserRuleContext {
		public TerminalNode OPENROW() { return getToken(InputParser.OPENROW, 0); }
		public List<TerminalNode> AFFINITY() { return getTokens(InputParser.AFFINITY); }
		public TerminalNode AFFINITY(int i) {
			return getToken(InputParser.AFFINITY, i);
		}
		public TerminalNode CLOSEROW() { return getToken(InputParser.CLOSEROW, 0); }
		public List<TerminalNode> SEPARATOR() { return getTokens(InputParser.SEPARATOR); }
		public TerminalNode SEPARATOR(int i) {
			return getToken(InputParser.SEPARATOR, i);
		}
		public RowContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_row; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InputListener ) ((InputListener)listener).enterRow(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InputListener ) ((InputListener)listener).exitRow(this);
		}
	}

	public final RowContext row() throws RecognitionException {
		RowContext _localctx = new RowContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_row);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30); 
			match(OPENROW);
			setState(31); 
			match(AFFINITY);
			setState(36);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEPARATOR) {
				{
				{
				setState(32); 
				match(SEPARATOR);
				setState(33); 
				match(AFFINITY);
				}
				}
				setState(38);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(39); 
			match(CLOSEROW);
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

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\t,\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\3\2\3\2\3\2\3\3\3\3\3\3\3\3\7\3\22\n\3\f\3\16\3\25"+
		"\13\3\3\3\3\3\3\3\3\4\3\4\3\4\6\4\35\n\4\r\4\16\4\36\3\5\3\5\3\5\3\5\7"+
		"\5%\n\5\f\5\16\5(\13\5\3\5\3\5\3\5\2\2\6\2\4\6\b\2\2*\2\n\3\2\2\2\4\r"+
		"\3\2\2\2\6\34\3\2\2\2\b \3\2\2\2\n\13\5\4\3\2\13\f\5\6\4\2\f\3\3\2\2\2"+
		"\r\16\7\3\2\2\16\23\7\b\2\2\17\20\7\5\2\2\20\22\7\b\2\2\21\17\3\2\2\2"+
		"\22\25\3\2\2\2\23\21\3\2\2\2\23\24\3\2\2\2\24\26\3\2\2\2\25\23\3\2\2\2"+
		"\26\27\7\4\2\2\27\30\7\6\2\2\30\5\3\2\2\2\31\32\5\b\5\2\32\33\7\6\2\2"+
		"\33\35\3\2\2\2\34\31\3\2\2\2\35\36\3\2\2\2\36\34\3\2\2\2\36\37\3\2\2\2"+
		"\37\7\3\2\2\2 !\7\3\2\2!&\7\7\2\2\"#\7\5\2\2#%\7\7\2\2$\"\3\2\2\2%(\3"+
		"\2\2\2&$\3\2\2\2&\'\3\2\2\2\')\3\2\2\2(&\3\2\2\2)*\7\4\2\2*\t\3\2\2\2"+
		"\5\23\36&";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}