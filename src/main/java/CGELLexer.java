// $ANTLR 3.5 CGEL.g 2013-01-10 15:42:04

package my.ir;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class CGELLexer extends Lexer {
	public static final int EOF=-1;
	public static final int T__21=21;
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
	public static final int RELATIONAL_OPERATOR=16;
	public static final int SL_COMMENTS=17;
	public static final int UNARY_OPERATOR=18;
	public static final int WHILE=19;
	public static final int WS=20;

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

	// $ANTLR start "T__21"
	public final void mT__21() throws RecognitionException {
		try {
			int _type = T__21;
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
	// $ANTLR end "T__21"

	// $ANTLR start "T__22"
	public final void mT__22() throws RecognitionException {
		try {
			int _type = T__22;
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
	// $ANTLR end "T__22"

	// $ANTLR start "T__23"
	public final void mT__23() throws RecognitionException {
		try {
			int _type = T__23;
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
	// $ANTLR end "T__23"

	// $ANTLR start "T__24"
	public final void mT__24() throws RecognitionException {
		try {
			int _type = T__24;
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
	// $ANTLR end "T__24"

	// $ANTLR start "T__25"
	public final void mT__25() throws RecognitionException {
		try {
			int _type = T__25;
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
	// $ANTLR end "T__25"

	// $ANTLR start "T__26"
	public final void mT__26() throws RecognitionException {
		try {
			int _type = T__26;
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
	// $ANTLR end "T__26"

	// $ANTLR start "T__27"
	public final void mT__27() throws RecognitionException {
		try {
			int _type = T__27;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// CGEL.g:12:7: ( 'else' )
			// CGEL.g:12:9: 'else'
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
	// $ANTLR end "T__27"

	// $ANTLR start "T__28"
	public final void mT__28() throws RecognitionException {
		try {
			int _type = T__28;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// CGEL.g:13:7: ( 'function' )
			// CGEL.g:13:9: 'function'
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
	// $ANTLR end "T__28"

	// $ANTLR start "T__29"
	public final void mT__29() throws RecognitionException {
		try {
			int _type = T__29;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// CGEL.g:14:7: ( 'if' )
			// CGEL.g:14:9: 'if'
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
	// $ANTLR end "T__29"

	// $ANTLR start "T__30"
	public final void mT__30() throws RecognitionException {
		try {
			int _type = T__30;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// CGEL.g:15:7: ( 'var' )
			// CGEL.g:15:9: 'var'
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
	// $ANTLR end "T__30"

	// $ANTLR start "T__31"
	public final void mT__31() throws RecognitionException {
		try {
			int _type = T__31;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// CGEL.g:16:7: ( 'while' )
			// CGEL.g:16:9: 'while'
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
	// $ANTLR end "T__31"

	// $ANTLR start "T__32"
	public final void mT__32() throws RecognitionException {
		try {
			int _type = T__32;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// CGEL.g:17:7: ( '{' )
			// CGEL.g:17:9: '{'
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
	// $ANTLR end "T__32"

	// $ANTLR start "T__33"
	public final void mT__33() throws RecognitionException {
		try {
			int _type = T__33;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// CGEL.g:18:7: ( '}' )
			// CGEL.g:18:9: '}'
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
	// $ANTLR end "T__33"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// CGEL.g:98:3: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
			// CGEL.g:98:5: ( ' ' | '\\t' | '\\r' | '\\n' )+
			{
			// CGEL.g:98:5: ( ' ' | '\\t' | '\\r' | '\\n' )+
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
			// CGEL.g:100:5: ( '+' )
			// CGEL.g:100:7: '+'
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
			// CGEL.g:101:6: ( '-' )
			// CGEL.g:101:8: '-'
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
			// CGEL.g:102:9: ( '*' )
			// CGEL.g:102:11: '*'
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
			// CGEL.g:103:7: ( '/' )
			// CGEL.g:103:9: '/'
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
			// CGEL.g:105:20: ( '==' | '>' | '<' | '>=' | '<=' )
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
					// CGEL.g:105:22: '=='
					{
					match("=="); 

					}
					break;
				case 2 :
					// CGEL.g:105:29: '>'
					{
					match('>'); 
					}
					break;
				case 3 :
					// CGEL.g:105:35: '<'
					{
					match('<'); 
					}
					break;
				case 4 :
					// CGEL.g:105:41: '>='
					{
					match(">="); 

					}
					break;
				case 5 :
					// CGEL.g:105:48: '<='
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
			// CGEL.g:108:12: ( '//' (~ '\\n' )* '\\n' )
			// CGEL.g:108:14: '//' (~ '\\n' )* '\\n'
			{
			match("//"); 

			// CGEL.g:108:19: (~ '\\n' )*
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
			// CGEL.g:110:3: ( LETTER ( LETTER | '0' .. '9' )* )
			// CGEL.g:110:5: LETTER ( LETTER | '0' .. '9' )*
			{
			mLETTER(); 

			// CGEL.g:110:12: ( LETTER | '0' .. '9' )*
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

	// $ANTLR start "LETTER"
	public final void mLETTER() throws RecognitionException {
		try {
			// CGEL.g:112:16: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) )
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
		// CGEL.g:1:8: ( T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | WS | PLUS | MINUS | MULTIPLY | DIVIDE | RELATIONAL_OPERATOR | SL_COMMENTS | ID )
		int alt5=21;
		switch ( input.LA(1) ) {
		case '(':
			{
			alt5=1;
			}
			break;
		case ')':
			{
			alt5=2;
			}
			break;
		case ',':
			{
			alt5=3;
			}
			break;
		case ';':
			{
			alt5=4;
			}
			break;
		case '=':
			{
			int LA5_5 = input.LA(2);
			if ( (LA5_5=='=') ) {
				alt5=19;
			}

			else {
				alt5=5;
			}

			}
			break;
		case '@':
			{
			alt5=6;
			}
			break;
		case 'e':
			{
			int LA5_7 = input.LA(2);
			if ( (LA5_7=='l') ) {
				int LA5_22 = input.LA(3);
				if ( (LA5_22=='s') ) {
					int LA5_29 = input.LA(4);
					if ( (LA5_29=='e') ) {
						int LA5_34 = input.LA(5);
						if ( ((LA5_34 >= '0' && LA5_34 <= '9')||(LA5_34 >= 'A' && LA5_34 <= 'Z')||LA5_34=='_'||(LA5_34 >= 'a' && LA5_34 <= 'z')) ) {
							alt5=21;
						}

						else {
							alt5=7;
						}

					}

					else {
						alt5=21;
					}

				}

				else {
					alt5=21;
				}

			}

			else {
				alt5=21;
			}

			}
			break;
		case 'f':
			{
			int LA5_8 = input.LA(2);
			if ( (LA5_8=='u') ) {
				int LA5_23 = input.LA(3);
				if ( (LA5_23=='n') ) {
					int LA5_30 = input.LA(4);
					if ( (LA5_30=='c') ) {
						int LA5_35 = input.LA(5);
						if ( (LA5_35=='t') ) {
							int LA5_39 = input.LA(6);
							if ( (LA5_39=='i') ) {
								int LA5_41 = input.LA(7);
								if ( (LA5_41=='o') ) {
									int LA5_43 = input.LA(8);
									if ( (LA5_43=='n') ) {
										int LA5_44 = input.LA(9);
										if ( ((LA5_44 >= '0' && LA5_44 <= '9')||(LA5_44 >= 'A' && LA5_44 <= 'Z')||LA5_44=='_'||(LA5_44 >= 'a' && LA5_44 <= 'z')) ) {
											alt5=21;
										}

										else {
											alt5=8;
										}

									}

									else {
										alt5=21;
									}

								}

								else {
									alt5=21;
								}

							}

							else {
								alt5=21;
							}

						}

						else {
							alt5=21;
						}

					}

					else {
						alt5=21;
					}

				}

				else {
					alt5=21;
				}

			}

			else {
				alt5=21;
			}

			}
			break;
		case 'i':
			{
			int LA5_9 = input.LA(2);
			if ( (LA5_9=='f') ) {
				int LA5_24 = input.LA(3);
				if ( ((LA5_24 >= '0' && LA5_24 <= '9')||(LA5_24 >= 'A' && LA5_24 <= 'Z')||LA5_24=='_'||(LA5_24 >= 'a' && LA5_24 <= 'z')) ) {
					alt5=21;
				}

				else {
					alt5=9;
				}

			}

			else {
				alt5=21;
			}

			}
			break;
		case 'v':
			{
			int LA5_10 = input.LA(2);
			if ( (LA5_10=='a') ) {
				int LA5_25 = input.LA(3);
				if ( (LA5_25=='r') ) {
					int LA5_32 = input.LA(4);
					if ( ((LA5_32 >= '0' && LA5_32 <= '9')||(LA5_32 >= 'A' && LA5_32 <= 'Z')||LA5_32=='_'||(LA5_32 >= 'a' && LA5_32 <= 'z')) ) {
						alt5=21;
					}

					else {
						alt5=10;
					}

				}

				else {
					alt5=21;
				}

			}

			else {
				alt5=21;
			}

			}
			break;
		case 'w':
			{
			int LA5_11 = input.LA(2);
			if ( (LA5_11=='h') ) {
				int LA5_26 = input.LA(3);
				if ( (LA5_26=='i') ) {
					int LA5_33 = input.LA(4);
					if ( (LA5_33=='l') ) {
						int LA5_37 = input.LA(5);
						if ( (LA5_37=='e') ) {
							int LA5_40 = input.LA(6);
							if ( ((LA5_40 >= '0' && LA5_40 <= '9')||(LA5_40 >= 'A' && LA5_40 <= 'Z')||LA5_40=='_'||(LA5_40 >= 'a' && LA5_40 <= 'z')) ) {
								alt5=21;
							}

							else {
								alt5=11;
							}

						}

						else {
							alt5=21;
						}

					}

					else {
						alt5=21;
					}

				}

				else {
					alt5=21;
				}

			}

			else {
				alt5=21;
			}

			}
			break;
		case '{':
			{
			alt5=12;
			}
			break;
		case '}':
			{
			alt5=13;
			}
			break;
		case '\t':
		case '\n':
		case '\r':
		case ' ':
			{
			alt5=14;
			}
			break;
		case '+':
			{
			alt5=15;
			}
			break;
		case '-':
			{
			alt5=16;
			}
			break;
		case '*':
			{
			alt5=17;
			}
			break;
		case '/':
			{
			int LA5_18 = input.LA(2);
			if ( (LA5_18=='/') ) {
				alt5=20;
			}

			else {
				alt5=18;
			}

			}
			break;
		case '<':
		case '>':
			{
			alt5=19;
			}
			break;
		case 'A':
		case 'B':
		case 'C':
		case 'D':
		case 'E':
		case 'F':
		case 'G':
		case 'H':
		case 'I':
		case 'J':
		case 'K':
		case 'L':
		case 'M':
		case 'N':
		case 'O':
		case 'P':
		case 'Q':
		case 'R':
		case 'S':
		case 'T':
		case 'U':
		case 'V':
		case 'W':
		case 'X':
		case 'Y':
		case 'Z':
		case '_':
		case 'a':
		case 'b':
		case 'c':
		case 'd':
		case 'g':
		case 'h':
		case 'j':
		case 'k':
		case 'l':
		case 'm':
		case 'n':
		case 'o':
		case 'p':
		case 'q':
		case 'r':
		case 's':
		case 't':
		case 'u':
		case 'x':
		case 'y':
		case 'z':
			{
			alt5=21;
			}
			break;
		default:
			NoViableAltException nvae =
				new NoViableAltException("", 5, 0, input);
			throw nvae;
		}
		switch (alt5) {
			case 1 :
				// CGEL.g:1:10: T__21
				{
				mT__21(); 

				}
				break;
			case 2 :
				// CGEL.g:1:16: T__22
				{
				mT__22(); 

				}
				break;
			case 3 :
				// CGEL.g:1:22: T__23
				{
				mT__23(); 

				}
				break;
			case 4 :
				// CGEL.g:1:28: T__24
				{
				mT__24(); 

				}
				break;
			case 5 :
				// CGEL.g:1:34: T__25
				{
				mT__25(); 

				}
				break;
			case 6 :
				// CGEL.g:1:40: T__26
				{
				mT__26(); 

				}
				break;
			case 7 :
				// CGEL.g:1:46: T__27
				{
				mT__27(); 

				}
				break;
			case 8 :
				// CGEL.g:1:52: T__28
				{
				mT__28(); 

				}
				break;
			case 9 :
				// CGEL.g:1:58: T__29
				{
				mT__29(); 

				}
				break;
			case 10 :
				// CGEL.g:1:64: T__30
				{
				mT__30(); 

				}
				break;
			case 11 :
				// CGEL.g:1:70: T__31
				{
				mT__31(); 

				}
				break;
			case 12 :
				// CGEL.g:1:76: T__32
				{
				mT__32(); 

				}
				break;
			case 13 :
				// CGEL.g:1:82: T__33
				{
				mT__33(); 

				}
				break;
			case 14 :
				// CGEL.g:1:88: WS
				{
				mWS(); 

				}
				break;
			case 15 :
				// CGEL.g:1:91: PLUS
				{
				mPLUS(); 

				}
				break;
			case 16 :
				// CGEL.g:1:96: MINUS
				{
				mMINUS(); 

				}
				break;
			case 17 :
				// CGEL.g:1:102: MULTIPLY
				{
				mMULTIPLY(); 

				}
				break;
			case 18 :
				// CGEL.g:1:111: DIVIDE
				{
				mDIVIDE(); 

				}
				break;
			case 19 :
				// CGEL.g:1:118: RELATIONAL_OPERATOR
				{
				mRELATIONAL_OPERATOR(); 

				}
				break;
			case 20 :
				// CGEL.g:1:138: SL_COMMENTS
				{
				mSL_COMMENTS(); 

				}
				break;
			case 21 :
				// CGEL.g:1:150: ID
				{
				mID(); 

				}
				break;

		}
	}



}
