// $ANTLR 3.4 CGEL.g 2012-12-14 11:02:45

package my.ir;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class CGELLexer extends Lexer {
    public static final int EOF=-1;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__19=19;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int T__22=22;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int BINARY_OPERATOR=4;
    public static final int BLOCK=5;
    public static final int FUNCTION_INPUT=6;
    public static final int FUNCTION_OUTPUT=7;
    public static final int ID=8;
    public static final int IF_ELSE=9;
    public static final int LETTER=10;
    public static final int SL_COMMENTS=11;
    public static final int WS=12;

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
    public String getGrammarFileName() { return "CGEL.g"; }

    // $ANTLR start "T__13"
    public final void mT__13() throws RecognitionException {
        try {
            int _type = T__13;
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
    // $ANTLR end "T__13"

    // $ANTLR start "T__14"
    public final void mT__14() throws RecognitionException {
        try {
            int _type = T__14;
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
    // $ANTLR end "T__14"

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            int _type = T__15;
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
    // $ANTLR end "T__15"

    // $ANTLR start "T__16"
    public final void mT__16() throws RecognitionException {
        try {
            int _type = T__16;
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
    // $ANTLR end "T__16"

    // $ANTLR start "T__17"
    public final void mT__17() throws RecognitionException {
        try {
            int _type = T__17;
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
    // $ANTLR end "T__17"

    // $ANTLR start "T__18"
    public final void mT__18() throws RecognitionException {
        try {
            int _type = T__18;
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
    // $ANTLR end "T__18"

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
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
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
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
    // $ANTLR end "T__20"

    // $ANTLR start "T__21"
    public final void mT__21() throws RecognitionException {
        try {
            int _type = T__21;
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
    // $ANTLR end "T__21"

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            int _type = T__22;
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
    // $ANTLR end "T__22"

    // $ANTLR start "T__23"
    public final void mT__23() throws RecognitionException {
        try {
            int _type = T__23;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // CGEL.g:16:7: ( '{' )
            // CGEL.g:16:9: '{'
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
    // $ANTLR end "T__23"

    // $ANTLR start "T__24"
    public final void mT__24() throws RecognitionException {
        try {
            int _type = T__24;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // CGEL.g:17:7: ( '}' )
            // CGEL.g:17:9: '}'
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
    // $ANTLR end "T__24"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // CGEL.g:72:3: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // CGEL.g:72:5: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // CGEL.g:72:5: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt1=0;
            loop1:
            do {
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
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


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

    // $ANTLR start "BINARY_OPERATOR"
    public final void mBINARY_OPERATOR() throws RecognitionException {
        try {
            int _type = BINARY_OPERATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // CGEL.g:74:16: ( '+' | '-' | '*' | '/' )
            // CGEL.g:
            {
            if ( (input.LA(1) >= '*' && input.LA(1) <= '+')||input.LA(1)=='-'||input.LA(1)=='/' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BINARY_OPERATOR"

    // $ANTLR start "SL_COMMENTS"
    public final void mSL_COMMENTS() throws RecognitionException {
        try {
            int _type = SL_COMMENTS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // CGEL.g:78:12: ( '//' (~ '\\n' )* '\\n' )
            // CGEL.g:78:14: '//' (~ '\\n' )* '\\n'
            {
            match("//"); 



            // CGEL.g:78:19: (~ '\\n' )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0 >= '\u0000' && LA2_0 <= '\t')||(LA2_0 >= '\u000B' && LA2_0 <= '\uFFFF')) ) {
                    alt2=1;
                }


                switch (alt2) {
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
            	    break loop2;
                }
            } while (true);


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
            // CGEL.g:80:3: ( LETTER ( LETTER | '0' .. '9' )* )
            // CGEL.g:80:5: LETTER ( LETTER | '0' .. '9' )*
            {
            mLETTER(); 


            // CGEL.g:80:12: ( LETTER | '0' .. '9' )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0 >= '0' && LA3_0 <= '9')||(LA3_0 >= 'A' && LA3_0 <= 'Z')||LA3_0=='_'||(LA3_0 >= 'a' && LA3_0 <= 'z')) ) {
                    alt3=1;
                }


                switch (alt3) {
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
            	    break loop3;
                }
            } while (true);


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
            // CGEL.g:82:16: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) )
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

    public void mTokens() throws RecognitionException {
        // CGEL.g:1:8: ( T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | WS | BINARY_OPERATOR | SL_COMMENTS | ID )
        int alt4=16;
        switch ( input.LA(1) ) {
        case '(':
            {
            alt4=1;
            }
            break;
        case ')':
            {
            alt4=2;
            }
            break;
        case ',':
            {
            alt4=3;
            }
            break;
        case ';':
            {
            alt4=4;
            }
            break;
        case '=':
            {
            alt4=5;
            }
            break;
        case '@':
            {
            alt4=6;
            }
            break;
        case 'e':
            {
            int LA4_7 = input.LA(2);

            if ( (LA4_7=='l') ) {
                int LA4_17 = input.LA(3);

                if ( (LA4_17=='s') ) {
                    int LA4_22 = input.LA(4);

                    if ( (LA4_22=='e') ) {
                        int LA4_26 = input.LA(5);

                        if ( ((LA4_26 >= '0' && LA4_26 <= '9')||(LA4_26 >= 'A' && LA4_26 <= 'Z')||LA4_26=='_'||(LA4_26 >= 'a' && LA4_26 <= 'z')) ) {
                            alt4=16;
                        }
                        else {
                            alt4=7;
                        }
                    }
                    else {
                        alt4=16;
                    }
                }
                else {
                    alt4=16;
                }
            }
            else {
                alt4=16;
            }
            }
            break;
        case 'f':
            {
            int LA4_8 = input.LA(2);

            if ( (LA4_8=='u') ) {
                int LA4_18 = input.LA(3);

                if ( (LA4_18=='n') ) {
                    int LA4_23 = input.LA(4);

                    if ( (LA4_23=='c') ) {
                        int LA4_27 = input.LA(5);

                        if ( (LA4_27=='t') ) {
                            int LA4_30 = input.LA(6);

                            if ( (LA4_30=='i') ) {
                                int LA4_31 = input.LA(7);

                                if ( (LA4_31=='o') ) {
                                    int LA4_32 = input.LA(8);

                                    if ( (LA4_32=='n') ) {
                                        int LA4_33 = input.LA(9);

                                        if ( ((LA4_33 >= '0' && LA4_33 <= '9')||(LA4_33 >= 'A' && LA4_33 <= 'Z')||LA4_33=='_'||(LA4_33 >= 'a' && LA4_33 <= 'z')) ) {
                                            alt4=16;
                                        }
                                        else {
                                            alt4=8;
                                        }
                                    }
                                    else {
                                        alt4=16;
                                    }
                                }
                                else {
                                    alt4=16;
                                }
                            }
                            else {
                                alt4=16;
                            }
                        }
                        else {
                            alt4=16;
                        }
                    }
                    else {
                        alt4=16;
                    }
                }
                else {
                    alt4=16;
                }
            }
            else {
                alt4=16;
            }
            }
            break;
        case 'i':
            {
            int LA4_9 = input.LA(2);

            if ( (LA4_9=='f') ) {
                int LA4_19 = input.LA(3);

                if ( ((LA4_19 >= '0' && LA4_19 <= '9')||(LA4_19 >= 'A' && LA4_19 <= 'Z')||LA4_19=='_'||(LA4_19 >= 'a' && LA4_19 <= 'z')) ) {
                    alt4=16;
                }
                else {
                    alt4=9;
                }
            }
            else {
                alt4=16;
            }
            }
            break;
        case 'v':
            {
            int LA4_10 = input.LA(2);

            if ( (LA4_10=='a') ) {
                int LA4_20 = input.LA(3);

                if ( (LA4_20=='r') ) {
                    int LA4_25 = input.LA(4);

                    if ( ((LA4_25 >= '0' && LA4_25 <= '9')||(LA4_25 >= 'A' && LA4_25 <= 'Z')||LA4_25=='_'||(LA4_25 >= 'a' && LA4_25 <= 'z')) ) {
                        alt4=16;
                    }
                    else {
                        alt4=10;
                    }
                }
                else {
                    alt4=16;
                }
            }
            else {
                alt4=16;
            }
            }
            break;
        case '{':
            {
            alt4=11;
            }
            break;
        case '}':
            {
            alt4=12;
            }
            break;
        case '\t':
        case '\n':
        case '\r':
        case ' ':
            {
            alt4=13;
            }
            break;
        case '/':
            {
            int LA4_14 = input.LA(2);

            if ( (LA4_14=='/') ) {
                alt4=15;
            }
            else {
                alt4=14;
            }
            }
            break;
        case '*':
        case '+':
        case '-':
            {
            alt4=14;
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
        case 'w':
        case 'x':
        case 'y':
        case 'z':
            {
            alt4=16;
            }
            break;
        default:
            NoViableAltException nvae =
                new NoViableAltException("", 4, 0, input);

            throw nvae;

        }

        switch (alt4) {
            case 1 :
                // CGEL.g:1:10: T__13
                {
                mT__13(); 


                }
                break;
            case 2 :
                // CGEL.g:1:16: T__14
                {
                mT__14(); 


                }
                break;
            case 3 :
                // CGEL.g:1:22: T__15
                {
                mT__15(); 


                }
                break;
            case 4 :
                // CGEL.g:1:28: T__16
                {
                mT__16(); 


                }
                break;
            case 5 :
                // CGEL.g:1:34: T__17
                {
                mT__17(); 


                }
                break;
            case 6 :
                // CGEL.g:1:40: T__18
                {
                mT__18(); 


                }
                break;
            case 7 :
                // CGEL.g:1:46: T__19
                {
                mT__19(); 


                }
                break;
            case 8 :
                // CGEL.g:1:52: T__20
                {
                mT__20(); 


                }
                break;
            case 9 :
                // CGEL.g:1:58: T__21
                {
                mT__21(); 


                }
                break;
            case 10 :
                // CGEL.g:1:64: T__22
                {
                mT__22(); 


                }
                break;
            case 11 :
                // CGEL.g:1:70: T__23
                {
                mT__23(); 


                }
                break;
            case 12 :
                // CGEL.g:1:76: T__24
                {
                mT__24(); 


                }
                break;
            case 13 :
                // CGEL.g:1:82: WS
                {
                mWS(); 


                }
                break;
            case 14 :
                // CGEL.g:1:85: BINARY_OPERATOR
                {
                mBINARY_OPERATOR(); 


                }
                break;
            case 15 :
                // CGEL.g:1:101: SL_COMMENTS
                {
                mSL_COMMENTS(); 


                }
                break;
            case 16 :
                // CGEL.g:1:113: ID
                {
                mID(); 


                }
                break;

        }

    }


 

}