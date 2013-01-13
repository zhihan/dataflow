// $ANTLR 3.5 CGEL.g 2013-01-12 19:23:45

package my.ir;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class CGELLexer extends Lexer {
	public static final int EOF=-1;
	public static final int T__22=22;
	public static final int T__23=23;
	public static final int T__24=24;
	public static final int T__25=25;
	public static final int T__26=26;
	public static final int T__27=27;
	public static final int T__28=28;
	public static final int T__29=29;
	public static final int T__30=30;
	public static final int T__31=31;
	public static final int T__32=32;
	public static final int T__33=33;
	public static final int T__34=34;
	public static final int T__35=35;
	public static final int T__36=36;
	public static final int T__37=37;
	public static final int BINARY_OPERATOR=4;
	public static final int BLOCK=5;
	public static final int DIVIDE=6;
	public static final int FUNCTION_CALL=7;
	public static final int FUNCTION_INPUT=8;
	public static final int FUNCTION_OUTPUT=9;
	public static final int ID=10;
	public static final int IF_ELSE=11;
	public static final int LETTER=12;
	public static final int MINUS=13;
	public static final int MULTIPLY=14;
	public static final int PLUS=15;
	public static final int REAL_NUMBER=16;
	public static final int RELATIONAL_OPERATOR=17;
	public static final int SL_COMMENTS=18;
	public static final int UNARY_OPERATOR=19;
	public static final int WHILE=20;
	public static final int WS=21;

	// delegates
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public CGELLexer() {} 
	public CGELLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public CGELLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "CGEL.g"; }

	// $ANTLR start "T__22"
	public final void mT__22() throws RecognitionException {
		try {
			int _type = T__22;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// CGEL.g:6:7: ( '(' )
			// CGEL.g:6:9: '('
			{
			match('('); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__22"

	// $ANTLR start "T__23"
	public final void mT__23() throws RecognitionException {
		try {
			int _type = T__23;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// CGEL.g:7:7: ( ')' )
			// CGEL.g:7:9: ')'
			{
			match(')'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__23"

	// $ANTLR start "T__24"
	public final void mT__24() throws RecognitionException {
		try {
			int _type = T__24;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// CGEL.g:8:7: ( ',' )
			// CGEL.g:8:9: ','
			{
			match(','); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__24"

	// $ANTLR start "T__25"
	public final void mT__25() throws RecognitionException {
		try {
			int _type = T__25;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// CGEL.g:9:7: ( ';' )
			// CGEL.g:9:9: ';'
			{
			match(';'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__25"

	// $ANTLR start "T__26"
	public final void mT__26() throws RecognitionException {
		try {
			int _type = T__26;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// CGEL.g:10:7: ( '=' )
			// CGEL.g:10:9: '='
			{
			match('='); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__26"

	// $ANTLR start "T__27"
	public final void mT__27() throws RecognitionException {
		try {
			int _type = T__27;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// CGEL.g:11:7: ( '@' )
			// CGEL.g:11:9: '@'
			{
			match('@'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__27"

	// $ANTLR start "T__28"
	public final void mT__28() throws RecognitionException {
		try {
			int _type = T__28;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// CGEL.g:12:7: ( 'const' )
			// CGEL.g:12:9: 'const'
			{
			match("const"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__28"

	// $ANTLR start "T__29"
	public final void mT__29() throws RecognitionException {
		try {
			int _type = T__29;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// CGEL.g:13:7: ( 'else' )
			// CGEL.g:13:9: 'else'
			{
			match("else"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__29"

	// $ANTLR start "T__30"
	public final void mT__30() throws RecognitionException {
		try {
			int _type = T__30;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// CGEL.g:14:7: ( 'false' )
			// CGEL.g:14:9: 'false'
			{
			match("false"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__30"

	// $ANTLR start "T__31"
	public final void mT__31() throws RecognitionException {
		try {
			int _type = T__31;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// CGEL.g:15:7: ( 'function' )
			// CGEL.g:15:9: 'function'
			{
			match("function"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__31"

	// $ANTLR start "T__32"
	public final void mT__32() throws RecognitionException {
		try {
			int _type = T__32;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// CGEL.g:16:7: ( 'if' )
			// CGEL.g:16:9: 'if'
			{
			match("if"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__32"

	// $ANTLR start "T__33"
	public final void mT__33() throws RecognitionException {
		try {
			int _type = T__33;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// CGEL.g:17:7: ( 'true' )
			// CGEL.g:17:9: 'true'
			{
			match("true"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__33"

	// $ANTLR start "T__34"
	public final void mT__34() throws RecognitionException {
		try {
			int _type = T__34;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// CGEL.g:18:7: ( 'var' )
			// CGEL.g:18:9: 'var'
			{
			match("var"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__34"

	// $ANTLR start "T__35"
	public final void mT__35() throws RecognitionException {
		try {
			int _type = T__35;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// CGEL.g:19:7: ( 'while' )
			// CGEL.g:19:9: 'while'
			{
			match("while"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__35"

	// $ANTLR start "T__36"
	public final void mT__36() throws RecognitionException {
		try {
			int _type = T__36;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// CGEL.g:20:7: ( '{' )
			// CGEL.g:20:9: '{'
			{
			match('{'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__36"

	// $ANTLR start "T__37"
	public final void mT__37() throws RecognitionException {
		try {
			int _type = T__37;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// CGEL.g:21:7: ( '}' )
			// CGEL.g:21:9: '}'
			{
			match('}'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__37"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// CGEL.g:117:3: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
			// CGEL.g:117:5: ( ' ' | '\\t' | '\\r' | '\\n' )+
			{
			// CGEL.g:117:5: ( ' ' | '\\t' | '\\r' | '\\n' )+
			int cnt1=0;
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( ((LA1_0 >= '\t' && LA1_0 <= '\n')||LA1_0=='\r'||LA1_0==' ') ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// CGEL.g:
					{
					if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt1 >= 1 ) break loop1;
					EarlyExitException eee = new EarlyExitException(1, input);
					throw eee;
				}
				cnt1++;
			}

			 skip(); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WS"

	// $ANTLR start "PLUS"
	public final void mPLUS() throws RecognitionException {
		try {
			int _type = PLUS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// CGEL.g:119:5: ( '+' )
			// CGEL.g:119:7: '+'
			{
			match('+'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PLUS"

	// $ANTLR start "MINUS"
	public final void mMINUS() throws RecognitionException {
		try {
			int _type = MINUS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// CGEL.g:120:6: ( '-' )
			// CGEL.g:120:8: '-'
			{
			match('-'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MINUS"

	// $ANTLR start "MULTIPLY"
	public final void mMULTIPLY() throws RecognitionException {
		try {
			int _type = MULTIPLY;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// CGEL.g:121:9: ( '*' )
			// CGEL.g:121:11: '*'
			{
			match('*'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MULTIPLY"

	// $ANTLR start "DIVIDE"
	public final void mDIVIDE() throws RecognitionException {
		try {
			int _type = DIVIDE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// CGEL.g:122:7: ( '/' )
			// CGEL.g:122:9: '/'
			{
			match('/'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DIVIDE"

	// $ANTLR start "RELATIONAL_OPERATOR"
	public final void mRELATIONAL_OPERATOR() throws RecognitionException {
		try {
			int _type = RELATIONAL_OPERATOR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// CGEL.g:124:20: ( '==' | '>' | '<' | '>=' | '<=' )
			int alt2=5;
			switch ( input.LA(1) ) {
			case '=':
				{
				alt2=1;
				}
				break;
			case '>':
				{
				int LA2_2 = input.LA(2);
				if ( (LA2_2=='=') ) {
					alt2=4;
				}

				else {
					alt2=2;
				}

				}
				break;
			case '<':
				{
				int LA2_3 = input.LA(2);
				if ( (LA2_3=='=') ) {
					alt2=5;
				}

				else {
					alt2=3;
				}

				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 2, 0, input);
				throw nvae;
			}
			switch (alt2) {
				case 1 :
					// CGEL.g:124:22: '=='
					{
					match("=="); 

					}
					break;
				case 2 :
					// CGEL.g:124:29: '>'
					{
					match('>'); 
					}
					break;
				case 3 :
					// CGEL.g:124:35: '<'
					{
					match('<'); 
					}
					break;
				case 4 :
					// CGEL.g:124:41: '>='
					{
					match(">="); 

					}
					break;
				case 5 :
					// CGEL.g:124:48: '<='
					{
					match("<="); 

					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RELATIONAL_OPERATOR"

	// $ANTLR start "SL_COMMENTS"
	public final void mSL_COMMENTS() throws RecognitionException {
		try {
			int _type = SL_COMMENTS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// CGEL.g:127:12: ( '//' (~ '\\n' )* '\\n' )
			// CGEL.g:127:14: '//' (~ '\\n' )* '\\n'
			{
			match("//"); 

			// CGEL.g:127:19: (~ '\\n' )*
			loop3:
			while (true) {
				int alt3=2;
				int LA3_0 = input.LA(1);
				if ( ((LA3_0 >= '\u0000' && LA3_0 <= '\t')||(LA3_0 >= '\u000B' && LA3_0 <= '\uFFFF')) ) {
					alt3=1;
				}

				switch (alt3) {
				case 1 :
					// CGEL.g:
					{
					if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\uFFFF') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop3;
				}
			}

			match('\n'); 
			 skip(); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SL_COMMENTS"

	// $ANTLR start "ID"
	public final void mID() throws RecognitionException {
		try {
			int _type = ID;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// CGEL.g:129:3: ( LETTER ( LETTER | '0' .. '9' )* )
			// CGEL.g:129:5: LETTER ( LETTER | '0' .. '9' )*
			{
			mLETTER(); 

			// CGEL.g:129:12: ( LETTER | '0' .. '9' )*
			loop4:
			while (true) {
				int alt4=2;
				int LA4_0 = input.LA(1);
				if ( ((LA4_0 >= '0' && LA4_0 <= '9')||(LA4_0 >= 'A' && LA4_0 <= 'Z')||LA4_0=='_'||(LA4_0 >= 'a' && LA4_0 <= 'z')) ) {
					alt4=1;
				}

				switch (alt4) {
				case 1 :
					// CGEL.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop4;
				}
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ID"

	// $ANTLR start "REAL_NUMBER"
	public final void mREAL_NUMBER() throws RecognitionException {
		try {
			int _type = REAL_NUMBER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// CGEL.g:132:12: ( ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )? )
			// CGEL.g:132:14: ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )?
			{
			// CGEL.g:132:14: ( '0' .. '9' )+
			int cnt5=0;
			loop5:
			while (true) {
				int alt5=2;
				int LA5_0 = input.LA(1);
				if ( ((LA5_0 >= '0' && LA5_0 <= '9')) ) {
					alt5=1;
				}

				switch (alt5) {
				case 1 :
					// CGEL.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt5 >= 1 ) break loop5;
					EarlyExitException eee = new EarlyExitException(5, input);
					throw eee;
				}
				cnt5++;
			}

			// CGEL.g:132:28: ( '.' ( '0' .. '9' )+ )?
			int alt7=2;
			int LA7_0 = input.LA(1);
			if ( (LA7_0=='.') ) {
				alt7=1;
			}
			switch (alt7) {
				case 1 :
					// CGEL.g:132:29: '.' ( '0' .. '9' )+
					{
					match('.'); 
					// CGEL.g:132:33: ( '0' .. '9' )+
					int cnt6=0;
					loop6:
					while (true) {
						int alt6=2;
						int LA6_0 = input.LA(1);
						if ( ((LA6_0 >= '0' && LA6_0 <= '9')) ) {
							alt6=1;
						}

						switch (alt6) {
						case 1 :
							// CGEL.g:
							{
							if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							if ( cnt6 >= 1 ) break loop6;
							EarlyExitException eee = new EarlyExitException(6, input);
							throw eee;
						}
						cnt6++;
					}

					}
					break;

			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "REAL_NUMBER"

	// $ANTLR start "LETTER"
	public final void mLETTER() throws RecognitionException {
		try {
			// CGEL.g:135:16: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) )
			// CGEL.g:
			{
			if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LETTER"

	@Override
	public void mTokens() throws RecognitionException {
		// CGEL.g:1:8: ( T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | WS | PLUS | MINUS | MULTIPLY | DIVIDE | RELATIONAL_OPERATOR | SL_COMMENTS | ID | REAL_NUMBER )
		int alt8=25;
		alt8 = dfa8.predict(input);
		switch (alt8) {
			case 1 :
				// CGEL.g:1:10: T__22
				{
				mT__22(); 

				}
				break;
			case 2 :
				// CGEL.g:1:16: T__23
				{
				mT__23(); 

				}
				break;
			case 3 :
				// CGEL.g:1:22: T__24
				{
				mT__24(); 

				}
				break;
			case 4 :
				// CGEL.g:1:28: T__25
				{
				mT__25(); 

				}
				break;
			case 5 :
				// CGEL.g:1:34: T__26
				{
				mT__26(); 

				}
				break;
			case 6 :
				// CGEL.g:1:40: T__27
				{
				mT__27(); 

				}
				break;
			case 7 :
				// CGEL.g:1:46: T__28
				{
				mT__28(); 

				}
				break;
			case 8 :
				// CGEL.g:1:52: T__29
				{
				mT__29(); 

				}
				break;
			case 9 :
				// CGEL.g:1:58: T__30
				{
				mT__30(); 

				}
				break;
			case 10 :
				// CGEL.g:1:64: T__31
				{
				mT__31(); 

				}
				break;
			case 11 :
				// CGEL.g:1:70: T__32
				{
				mT__32(); 

				}
				break;
			case 12 :
				// CGEL.g:1:76: T__33
				{
				mT__33(); 

				}
				break;
			case 13 :
				// CGEL.g:1:82: T__34
				{
				mT__34(); 

				}
				break;
			case 14 :
				// CGEL.g:1:88: T__35
				{
				mT__35(); 

				}
				break;
			case 15 :
				// CGEL.g:1:94: T__36
				{
				mT__36(); 

				}
				break;
			case 16 :
				// CGEL.g:1:100: T__37
				{
				mT__37(); 

				}
				break;
			case 17 :
				// CGEL.g:1:106: WS
				{
				mWS(); 

				}
				break;
			case 18 :
				// CGEL.g:1:109: PLUS
				{
				mPLUS(); 

				}
				break;
			case 19 :
				// CGEL.g:1:114: MINUS
				{
				mMINUS(); 

				}
				break;
			case 20 :
				// CGEL.g:1:120: MULTIPLY
				{
				mMULTIPLY(); 

				}
				break;
			case 21 :
				// CGEL.g:1:129: DIVIDE
				{
				mDIVIDE(); 

				}
				break;
			case 22 :
				// CGEL.g:1:136: RELATIONAL_OPERATOR
				{
				mRELATIONAL_OPERATOR(); 

				}
				break;
			case 23 :
				// CGEL.g:1:156: SL_COMMENTS
				{
				mSL_COMMENTS(); 

				}
				break;
			case 24 :
				// CGEL.g:1:168: ID
				{
				mID(); 

				}
				break;
			case 25 :
				// CGEL.g:1:171: REAL_NUMBER
				{
				mREAL_NUMBER(); 

				}
				break;

		}
	}


	protected DFA8 dfa8 = new DFA8(this);
	static final String DFA8_eotS =
		"\5\uffff\1\30\1\uffff\7\26\6\uffff\1\42\4\uffff\4\26\1\47\3\26\2\uffff"+
		"\4\26\1\uffff\1\26\1\60\2\26\1\63\2\26\1\66\1\uffff\1\26\1\70\1\uffff"+
		"\1\71\1\26\1\uffff\1\73\2\uffff\1\26\1\uffff\1\26\1\76\1\uffff";
	static final String DFA8_eofS =
		"\77\uffff";
	static final String DFA8_minS =
		"\1\11\4\uffff\1\75\1\uffff\1\157\1\154\1\141\1\146\1\162\1\141\1\150\6"+
		"\uffff\1\57\4\uffff\1\156\1\163\1\154\1\156\1\60\1\165\1\162\1\151\2\uffff"+
		"\1\163\1\145\1\163\1\143\1\uffff\1\145\1\60\1\154\1\164\1\60\1\145\1\164"+
		"\1\60\1\uffff\1\145\1\60\1\uffff\1\60\1\151\1\uffff\1\60\2\uffff\1\157"+
		"\1\uffff\1\156\1\60\1\uffff";
	static final String DFA8_maxS =
		"\1\175\4\uffff\1\75\1\uffff\1\157\1\154\1\165\1\146\1\162\1\141\1\150"+
		"\6\uffff\1\57\4\uffff\1\156\1\163\1\154\1\156\1\172\1\165\1\162\1\151"+
		"\2\uffff\1\163\1\145\1\163\1\143\1\uffff\1\145\1\172\1\154\1\164\1\172"+
		"\1\145\1\164\1\172\1\uffff\1\145\1\172\1\uffff\1\172\1\151\1\uffff\1\172"+
		"\2\uffff\1\157\1\uffff\1\156\1\172\1\uffff";
	static final String DFA8_acceptS =
		"\1\uffff\1\1\1\2\1\3\1\4\1\uffff\1\6\7\uffff\1\17\1\20\1\21\1\22\1\23"+
		"\1\24\1\uffff\1\26\1\30\1\31\1\5\10\uffff\1\27\1\25\4\uffff\1\13\10\uffff"+
		"\1\15\2\uffff\1\10\2\uffff\1\14\1\uffff\1\7\1\11\1\uffff\1\16\2\uffff"+
		"\1\12";
	static final String DFA8_specialS =
		"\77\uffff}>";
	static final String[] DFA8_transitionS = {
			"\2\20\2\uffff\1\20\22\uffff\1\20\7\uffff\1\1\1\2\1\23\1\21\1\3\1\22\1"+
			"\uffff\1\24\12\27\1\uffff\1\4\1\25\1\5\1\25\1\uffff\1\6\32\26\4\uffff"+
			"\1\26\1\uffff\2\26\1\7\1\26\1\10\1\11\2\26\1\12\12\26\1\13\1\26\1\14"+
			"\1\15\3\26\1\16\1\uffff\1\17",
			"",
			"",
			"",
			"",
			"\1\25",
			"",
			"\1\31",
			"\1\32",
			"\1\33\23\uffff\1\34",
			"\1\35",
			"\1\36",
			"\1\37",
			"\1\40",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\41",
			"",
			"",
			"",
			"",
			"\1\43",
			"\1\44",
			"\1\45",
			"\1\46",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26",
			"\1\50",
			"\1\51",
			"\1\52",
			"",
			"",
			"\1\53",
			"\1\54",
			"\1\55",
			"\1\56",
			"",
			"\1\57",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26",
			"\1\61",
			"\1\62",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26",
			"\1\64",
			"\1\65",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26",
			"",
			"\1\67",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26",
			"",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26",
			"\1\72",
			"",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26",
			"",
			"",
			"\1\74",
			"",
			"\1\75",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26",
			""
	};

	static final short[] DFA8_eot = DFA.unpackEncodedString(DFA8_eotS);
	static final short[] DFA8_eof = DFA.unpackEncodedString(DFA8_eofS);
	static final char[] DFA8_min = DFA.unpackEncodedStringToUnsignedChars(DFA8_minS);
	static final char[] DFA8_max = DFA.unpackEncodedStringToUnsignedChars(DFA8_maxS);
	static final short[] DFA8_accept = DFA.unpackEncodedString(DFA8_acceptS);
	static final short[] DFA8_special = DFA.unpackEncodedString(DFA8_specialS);
	static final short[][] DFA8_transition;

	static {
		int numStates = DFA8_transitionS.length;
		DFA8_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA8_transition[i] = DFA.unpackEncodedString(DFA8_transitionS[i]);
		}
	}

	protected class DFA8 extends DFA {

		public DFA8(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 8;
			this.eot = DFA8_eot;
			this.eof = DFA8_eof;
			this.min = DFA8_min;
			this.max = DFA8_max;
			this.accept = DFA8_accept;
			this.special = DFA8_special;
			this.transition = DFA8_transition;
		}
		@Override
		public String getDescription() {
			return "1:1: Tokens : ( T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | WS | PLUS | MINUS | MULTIPLY | DIVIDE | RELATIONAL_OPERATOR | SL_COMMENTS | ID | REAL_NUMBER );";
		}
	}

}
