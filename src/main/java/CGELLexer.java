// $ANTLR 3.4 CGEL.g 2013-01-10 11:35:56

package my.ir;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class CGELLexer extends Lexer {
    public static final int EOF=-1;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__19=19;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int T__22=22;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int BINARY_OPERATOR=4;
    public static final int BLOCK=5;
    public static final int FUNCTION_CALL=6;
    public static final int FUNCTION_INPUT=7;
    public static final int FUNCTION_OUTPUT=8;
    public static final int ID=9;
    public static final int IF_ELSE=10;
    public static final int LETTER=11;
    public static final int RELATIONAL_OPERATOR=12;
    public static final int SL_COMMENTS=13;
    public static final int WHILE=14;
    public static final int WS=15;

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

    // $ANTLR start "T__16"
    public final void mT__16() throws RecognitionException {
        try {
            int _type = T__16;
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
    // $ANTLR end "T__16"

    // $ANTLR start "T__17"
    public final void mT__17() throws RecognitionException {
        try {
            int _type = T__17;
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
    // $ANTLR end "T__17"

    // $ANTLR start "T__18"
    public final void mT__18() throws RecognitionException {
        try {
            int _type = T__18;
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
    // $ANTLR end "T__18"

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
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
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
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
    // $ANTLR end "T__20"

    // $ANTLR start "T__21"
    public final void mT__21() throws RecognitionException {
        try {
            int _type = T__21;
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
    // $ANTLR end "T__21"

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            int _type = T__22;
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
    // $ANTLR end "T__22"

    // $ANTLR start "T__23"
    public final void mT__23() throws RecognitionException {
        try {
            int _type = T__23;
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
    // $ANTLR end "T__23"

    // $ANTLR start "T__24"
    public final void mT__24() throws RecognitionException {
        try {
            int _type = T__24;
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
    // $ANTLR end "T__24"

    // $ANTLR start "T__25"
    public final void mT__25() throws RecognitionException {
        try {
            int _type = T__25;
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
    // $ANTLR end "T__25"

    // $ANTLR start "T__26"
    public final void mT__26() throws RecognitionException {
        try {
            int _type = T__26;
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
    // $ANTLR end "T__26"

    // $ANTLR start "T__27"
    public final void mT__27() throws RecognitionException {
        try {
            int _type = T__27;
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
    // $ANTLR end "T__27"

    // $ANTLR start "T__28"
    public final void mT__28() throws RecognitionException {
        try {
            int _type = T__28;
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
    // $ANTLR end "T__28"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // CGEL.g:86:3: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // CGEL.g:86:5: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // CGEL.g:86:5: ( ' ' | '\\t' | '\\r' | '\\n' )+
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
            // CGEL.g:88:16: ( '+' | '-' | '*' | '/' )
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

    // $ANTLR start "RELATIONAL_OPERATOR"
    public final void mRELATIONAL_OPERATOR() throws RecognitionException {
        try {
            int _type = RELATIONAL_OPERATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // CGEL.g:90:20: ( '==' | '>' | '<' | '>=' | '<=' )
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
                    // CGEL.g:90:22: '=='
                    {
                    match("=="); 



                    }
                    break;
                case 2 :
                    // CGEL.g:90:29: '>'
                    {
                    match('>'); 

                    }
                    break;
                case 3 :
                    // CGEL.g:90:35: '<'
                    {
                    match('<'); 

                    }
                    break;
                case 4 :
                    // CGEL.g:90:41: '>='
                    {
                    match(">="); 



                    }
                    break;
                case 5 :
                    // CGEL.g:90:48: '<='
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
            // CGEL.g:93:12: ( '//' (~ '\\n' )* '\\n' )
            // CGEL.g:93:14: '//' (~ '\\n' )* '\\n'
            {
            match("//"); 



            // CGEL.g:93:19: (~ '\\n' )*
            loop3:
            do {
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
            // CGEL.g:95:3: ( LETTER ( LETTER | '0' .. '9' )* )
            // CGEL.g:95:5: LETTER ( LETTER | '0' .. '9' )*
            {
            mLETTER(); 


            // CGEL.g:95:12: ( LETTER | '0' .. '9' )*
            loop4:
            do {
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
            // CGEL.g:97:16: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) )
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
        // CGEL.g:1:8: ( T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | WS | BINARY_OPERATOR | RELATIONAL_OPERATOR | SL_COMMENTS | ID )
        int alt5=18;
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
                alt5=16;
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
                int LA5_20 = input.LA(3);

                if ( (LA5_20=='s') ) {
                    int LA5_26 = input.LA(4);

                    if ( (LA5_26=='e') ) {
                        int LA5_31 = input.LA(5);

                        if ( ((LA5_31 >= '0' && LA5_31 <= '9')||(LA5_31 >= 'A' && LA5_31 <= 'Z')||LA5_31=='_'||(LA5_31 >= 'a' && LA5_31 <= 'z')) ) {
                            alt5=18;
                        }
                        else {
                            alt5=7;
                        }
                    }
                    else {
                        alt5=18;
                    }
                }
                else {
                    alt5=18;
                }
            }
            else {
                alt5=18;
            }
            }
            break;
        case 'f':
            {
            int LA5_8 = input.LA(2);

            if ( (LA5_8=='u') ) {
                int LA5_21 = input.LA(3);

                if ( (LA5_21=='n') ) {
                    int LA5_27 = input.LA(4);

                    if ( (LA5_27=='c') ) {
                        int LA5_32 = input.LA(5);

                        if ( (LA5_32=='t') ) {
                            int LA5_36 = input.LA(6);

                            if ( (LA5_36=='i') ) {
                                int LA5_38 = input.LA(7);

                                if ( (LA5_38=='o') ) {
                                    int LA5_40 = input.LA(8);

                                    if ( (LA5_40=='n') ) {
                                        int LA5_41 = input.LA(9);

                                        if ( ((LA5_41 >= '0' && LA5_41 <= '9')||(LA5_41 >= 'A' && LA5_41 <= 'Z')||LA5_41=='_'||(LA5_41 >= 'a' && LA5_41 <= 'z')) ) {
                                            alt5=18;
                                        }
                                        else {
                                            alt5=8;
                                        }
                                    }
                                    else {
                                        alt5=18;
                                    }
                                }
                                else {
                                    alt5=18;
                                }
                            }
                            else {
                                alt5=18;
                            }
                        }
                        else {
                            alt5=18;
                        }
                    }
                    else {
                        alt5=18;
                    }
                }
                else {
                    alt5=18;
                }
            }
            else {
                alt5=18;
            }
            }
            break;
        case 'i':
            {
            int LA5_9 = input.LA(2);

            if ( (LA5_9=='f') ) {
                int LA5_22 = input.LA(3);

                if ( ((LA5_22 >= '0' && LA5_22 <= '9')||(LA5_22 >= 'A' && LA5_22 <= 'Z')||LA5_22=='_'||(LA5_22 >= 'a' && LA5_22 <= 'z')) ) {
                    alt5=18;
                }
                else {
                    alt5=9;
                }
            }
            else {
                alt5=18;
            }
            }
            break;
        case 'v':
            {
            int LA5_10 = input.LA(2);

            if ( (LA5_10=='a') ) {
                int LA5_23 = input.LA(3);

                if ( (LA5_23=='r') ) {
                    int LA5_29 = input.LA(4);

                    if ( ((LA5_29 >= '0' && LA5_29 <= '9')||(LA5_29 >= 'A' && LA5_29 <= 'Z')||LA5_29=='_'||(LA5_29 >= 'a' && LA5_29 <= 'z')) ) {
                        alt5=18;
                    }
                    else {
                        alt5=10;
                    }
                }
                else {
                    alt5=18;
                }
            }
            else {
                alt5=18;
            }
            }
            break;
        case 'w':
            {
            int LA5_11 = input.LA(2);

            if ( (LA5_11=='h') ) {
                int LA5_24 = input.LA(3);

                if ( (LA5_24=='i') ) {
                    int LA5_30 = input.LA(4);

                    if ( (LA5_30=='l') ) {
                        int LA5_34 = input.LA(5);

                        if ( (LA5_34=='e') ) {
                            int LA5_37 = input.LA(6);

                            if ( ((LA5_37 >= '0' && LA5_37 <= '9')||(LA5_37 >= 'A' && LA5_37 <= 'Z')||LA5_37=='_'||(LA5_37 >= 'a' && LA5_37 <= 'z')) ) {
                                alt5=18;
                            }
                            else {
                                alt5=11;
                            }
                        }
                        else {
                            alt5=18;
                        }
                    }
                    else {
                        alt5=18;
                    }
                }
                else {
                    alt5=18;
                }
            }
            else {
                alt5=18;
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
        case '/':
            {
            int LA5_15 = input.LA(2);

            if ( (LA5_15=='/') ) {
                alt5=17;
            }
            else {
                alt5=15;
            }
            }
            break;
        case '<':
        case '>':
            {
            alt5=16;
            }
            break;
        case '*':
        case '+':
        case '-':
            {
            alt5=15;
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
            alt5=18;
            }
            break;
        default:
            NoViableAltException nvae =
                new NoViableAltException("", 5, 0, input);

            throw nvae;

        }

        switch (alt5) {
            case 1 :
                // CGEL.g:1:10: T__16
                {
                mT__16(); 


                }
                break;
            case 2 :
                // CGEL.g:1:16: T__17
                {
                mT__17(); 


                }
                break;
            case 3 :
                // CGEL.g:1:22: T__18
                {
                mT__18(); 


                }
                break;
            case 4 :
                // CGEL.g:1:28: T__19
                {
                mT__19(); 


                }
                break;
            case 5 :
                // CGEL.g:1:34: T__20
                {
                mT__20(); 


                }
                break;
            case 6 :
                // CGEL.g:1:40: T__21
                {
                mT__21(); 


                }
                break;
            case 7 :
                // CGEL.g:1:46: T__22
                {
                mT__22(); 


                }
                break;
            case 8 :
                // CGEL.g:1:52: T__23
                {
                mT__23(); 


                }
                break;
            case 9 :
                // CGEL.g:1:58: T__24
                {
                mT__24(); 


                }
                break;
            case 10 :
                // CGEL.g:1:64: T__25
                {
                mT__25(); 


                }
                break;
            case 11 :
                // CGEL.g:1:70: T__26
                {
                mT__26(); 


                }
                break;
            case 12 :
                // CGEL.g:1:76: T__27
                {
                mT__27(); 


                }
                break;
            case 13 :
                // CGEL.g:1:82: T__28
                {
                mT__28(); 


                }
                break;
            case 14 :
                // CGEL.g:1:88: WS
                {
                mWS(); 


                }
                break;
            case 15 :
                // CGEL.g:1:91: BINARY_OPERATOR
                {
                mBINARY_OPERATOR(); 


                }
                break;
            case 16 :
                // CGEL.g:1:107: RELATIONAL_OPERATOR
                {
                mRELATIONAL_OPERATOR(); 


                }
                break;
            case 17 :
                // CGEL.g:1:127: SL_COMMENTS
                {
                mSL_COMMENTS(); 


                }
                break;
            case 18 :
                // CGEL.g:1:139: ID
                {
                mID(); 


                }
                break;

        }

    }


 

}