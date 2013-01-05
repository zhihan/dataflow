// $ANTLR 3.4 CGEL.g 2013-01-05 14:12:25

package my.ir;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.runtime.tree.*;


@SuppressWarnings({"all", "warnings", "unchecked"})
public class CGELParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "BINARY_OPERATOR", "BLOCK", "FUNCTION_INPUT", "FUNCTION_OUTPUT", "ID", "IF_ELSE", "LETTER", "RELATIONAL_OPERATOR", "SL_COMMENTS", "WHILE", "WS", "'('", "')'", "','", "';'", "'='", "'@'", "'else'", "'function'", "'if'", "'var'", "'while'", "'{'", "'}'"
    };

    public static final int EOF=-1;
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
    public static final int T__25=25;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int BINARY_OPERATOR=4;
    public static final int BLOCK=5;
    public static final int FUNCTION_INPUT=6;
    public static final int FUNCTION_OUTPUT=7;
    public static final int ID=8;
    public static final int IF_ELSE=9;
    public static final int LETTER=10;
    public static final int RELATIONAL_OPERATOR=11;
    public static final int SL_COMMENTS=12;
    public static final int WHILE=13;
    public static final int WS=14;

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public CGELParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public CGELParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

protected TreeAdaptor adaptor = new CommonTreeAdaptor();

public void setTreeAdaptor(TreeAdaptor adaptor) {
    this.adaptor = adaptor;
}
public TreeAdaptor getTreeAdaptor() {
    return adaptor;
}
    public String[] getTokenNames() { return CGELParser.tokenNames; }
    public String getGrammarFileName() { return "CGEL.g"; }


    public static class function_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "function"
    // CGEL.g:28:1: function : 'function' ! function_prototype ^ block ;
    public final CGELParser.function_return function() throws RecognitionException {
        CGELParser.function_return retval = new CGELParser.function_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal1=null;
        CGELParser.function_prototype_return function_prototype2 =null;

        CGELParser.block_return block3 =null;


        CommonTree string_literal1_tree=null;

        try {
            // CGEL.g:28:9: ( 'function' ! function_prototype ^ block )
            // CGEL.g:29:9: 'function' ! function_prototype ^ block
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal1=(Token)match(input,22,FOLLOW_22_in_function91); 

            pushFollow(FOLLOW_function_prototype_in_function94);
            function_prototype2=function_prototype();

            state._fsp--;

            root_0 = (CommonTree)adaptor.becomeRoot(function_prototype2.getTree(), root_0);

            pushFollow(FOLLOW_block_in_function97);
            block3=block();

            state._fsp--;

            adaptor.addChild(root_0, block3.getTree());

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "function"


    public static class function_prototype_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "function_prototype"
    // CGEL.g:31:1: function_prototype : '(' ! function_input ',' ! function_output ',' ! ID ^ ')' !;
    public final CGELParser.function_prototype_return function_prototype() throws RecognitionException {
        CGELParser.function_prototype_return retval = new CGELParser.function_prototype_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal4=null;
        Token char_literal6=null;
        Token char_literal8=null;
        Token ID9=null;
        Token char_literal10=null;
        CGELParser.function_input_return function_input5 =null;

        CGELParser.function_output_return function_output7 =null;


        CommonTree char_literal4_tree=null;
        CommonTree char_literal6_tree=null;
        CommonTree char_literal8_tree=null;
        CommonTree ID9_tree=null;
        CommonTree char_literal10_tree=null;

        try {
            // CGEL.g:31:19: ( '(' ! function_input ',' ! function_output ',' ! ID ^ ')' !)
            // CGEL.g:32:5: '(' ! function_input ',' ! function_output ',' ! ID ^ ')' !
            {
            root_0 = (CommonTree)adaptor.nil();


            char_literal4=(Token)match(input,15,FOLLOW_15_in_function_prototype112); 

            pushFollow(FOLLOW_function_input_in_function_prototype115);
            function_input5=function_input();

            state._fsp--;

            adaptor.addChild(root_0, function_input5.getTree());

            char_literal6=(Token)match(input,17,FOLLOW_17_in_function_prototype117); 

            pushFollow(FOLLOW_function_output_in_function_prototype120);
            function_output7=function_output();

            state._fsp--;

            adaptor.addChild(root_0, function_output7.getTree());

            char_literal8=(Token)match(input,17,FOLLOW_17_in_function_prototype122); 

            ID9=(Token)match(input,ID,FOLLOW_ID_in_function_prototype125); 
            ID9_tree = 
            (CommonTree)adaptor.create(ID9)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(ID9_tree, root_0);


            char_literal10=(Token)match(input,16,FOLLOW_16_in_function_prototype129); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "function_prototype"


    public static class function_output_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "function_output"
    // CGEL.g:34:1: function_output : (| '(' ')' -> ^( FUNCTION_OUTPUT ) | '(' var_decl ( ',' var_decl )* ')' -> ^( FUNCTION_OUTPUT ( var_decl )+ ) );
    public final CGELParser.function_output_return function_output() throws RecognitionException {
        CGELParser.function_output_return retval = new CGELParser.function_output_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal11=null;
        Token char_literal12=null;
        Token char_literal13=null;
        Token char_literal15=null;
        Token char_literal17=null;
        CGELParser.var_decl_return var_decl14 =null;

        CGELParser.var_decl_return var_decl16 =null;


        CommonTree char_literal11_tree=null;
        CommonTree char_literal12_tree=null;
        CommonTree char_literal13_tree=null;
        CommonTree char_literal15_tree=null;
        CommonTree char_literal17_tree=null;
        RewriteRuleTokenStream stream_17=new RewriteRuleTokenStream(adaptor,"token 17");
        RewriteRuleTokenStream stream_15=new RewriteRuleTokenStream(adaptor,"token 15");
        RewriteRuleTokenStream stream_16=new RewriteRuleTokenStream(adaptor,"token 16");
        RewriteRuleSubtreeStream stream_var_decl=new RewriteRuleSubtreeStream(adaptor,"rule var_decl");
        try {
            // CGEL.g:34:16: (| '(' ')' -> ^( FUNCTION_OUTPUT ) | '(' var_decl ( ',' var_decl )* ')' -> ^( FUNCTION_OUTPUT ( var_decl )+ ) )
            int alt2=3;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==17) ) {
                alt2=1;
            }
            else if ( (LA2_0==15) ) {
                int LA2_2 = input.LA(2);

                if ( (LA2_2==16) ) {
                    alt2=2;
                }
                else if ( (LA2_2==24) ) {
                    alt2=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 2, 2, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;

            }
            switch (alt2) {
                case 1 :
                    // CGEL.g:35:5: 
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    }
                    break;
                case 2 :
                    // CGEL.g:35:7: '(' ')'
                    {
                    char_literal11=(Token)match(input,15,FOLLOW_15_in_function_output147);  
                    stream_15.add(char_literal11);


                    char_literal12=(Token)match(input,16,FOLLOW_16_in_function_output149);  
                    stream_16.add(char_literal12);


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 35:15: -> ^( FUNCTION_OUTPUT )
                    {
                        // CGEL.g:35:18: ^( FUNCTION_OUTPUT )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(FUNCTION_OUTPUT, "FUNCTION_OUTPUT")
                        , root_1);

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 3 :
                    // CGEL.g:36:7: '(' var_decl ( ',' var_decl )* ')'
                    {
                    char_literal13=(Token)match(input,15,FOLLOW_15_in_function_output163);  
                    stream_15.add(char_literal13);


                    pushFollow(FOLLOW_var_decl_in_function_output165);
                    var_decl14=var_decl();

                    state._fsp--;

                    stream_var_decl.add(var_decl14.getTree());

                    // CGEL.g:36:20: ( ',' var_decl )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==17) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // CGEL.g:36:21: ',' var_decl
                    	    {
                    	    char_literal15=(Token)match(input,17,FOLLOW_17_in_function_output168);  
                    	    stream_17.add(char_literal15);


                    	    pushFollow(FOLLOW_var_decl_in_function_output171);
                    	    var_decl16=var_decl();

                    	    state._fsp--;

                    	    stream_var_decl.add(var_decl16.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop1;
                        }
                    } while (true);


                    char_literal17=(Token)match(input,16,FOLLOW_16_in_function_output175);  
                    stream_16.add(char_literal17);


                    // AST REWRITE
                    // elements: var_decl
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 36:41: -> ^( FUNCTION_OUTPUT ( var_decl )+ )
                    {
                        // CGEL.g:36:44: ^( FUNCTION_OUTPUT ( var_decl )+ )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(FUNCTION_OUTPUT, "FUNCTION_OUTPUT")
                        , root_1);

                        if ( !(stream_var_decl.hasNext()) ) {
                            throw new RewriteEarlyExitException();
                        }
                        while ( stream_var_decl.hasNext() ) {
                            adaptor.addChild(root_1, stream_var_decl.nextTree());

                        }
                        stream_var_decl.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "function_output"


    public static class function_input_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "function_input"
    // CGEL.g:38:1: function_input : (| '(' ')' -> ^( FUNCTION_INPUT ) | '(' var_decl ( ',' var_decl )* ')' -> ^( FUNCTION_INPUT ( var_decl )+ ) );
    public final CGELParser.function_input_return function_input() throws RecognitionException {
        CGELParser.function_input_return retval = new CGELParser.function_input_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal18=null;
        Token char_literal19=null;
        Token char_literal20=null;
        Token char_literal22=null;
        Token char_literal24=null;
        CGELParser.var_decl_return var_decl21 =null;

        CGELParser.var_decl_return var_decl23 =null;


        CommonTree char_literal18_tree=null;
        CommonTree char_literal19_tree=null;
        CommonTree char_literal20_tree=null;
        CommonTree char_literal22_tree=null;
        CommonTree char_literal24_tree=null;
        RewriteRuleTokenStream stream_17=new RewriteRuleTokenStream(adaptor,"token 17");
        RewriteRuleTokenStream stream_15=new RewriteRuleTokenStream(adaptor,"token 15");
        RewriteRuleTokenStream stream_16=new RewriteRuleTokenStream(adaptor,"token 16");
        RewriteRuleSubtreeStream stream_var_decl=new RewriteRuleSubtreeStream(adaptor,"rule var_decl");
        try {
            // CGEL.g:38:15: (| '(' ')' -> ^( FUNCTION_INPUT ) | '(' var_decl ( ',' var_decl )* ')' -> ^( FUNCTION_INPUT ( var_decl )+ ) )
            int alt4=3;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==17) ) {
                alt4=1;
            }
            else if ( (LA4_0==15) ) {
                int LA4_2 = input.LA(2);

                if ( (LA4_2==16) ) {
                    alt4=2;
                }
                else if ( (LA4_2==24) ) {
                    alt4=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 2, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;

            }
            switch (alt4) {
                case 1 :
                    // CGEL.g:39:5: 
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    }
                    break;
                case 2 :
                    // CGEL.g:39:7: '(' ')'
                    {
                    char_literal18=(Token)match(input,15,FOLLOW_15_in_function_input201);  
                    stream_15.add(char_literal18);


                    char_literal19=(Token)match(input,16,FOLLOW_16_in_function_input203);  
                    stream_16.add(char_literal19);


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 39:15: -> ^( FUNCTION_INPUT )
                    {
                        // CGEL.g:39:18: ^( FUNCTION_INPUT )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(FUNCTION_INPUT, "FUNCTION_INPUT")
                        , root_1);

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 3 :
                    // CGEL.g:40:7: '(' var_decl ( ',' var_decl )* ')'
                    {
                    char_literal20=(Token)match(input,15,FOLLOW_15_in_function_input217);  
                    stream_15.add(char_literal20);


                    pushFollow(FOLLOW_var_decl_in_function_input219);
                    var_decl21=var_decl();

                    state._fsp--;

                    stream_var_decl.add(var_decl21.getTree());

                    // CGEL.g:40:20: ( ',' var_decl )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0==17) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // CGEL.g:40:21: ',' var_decl
                    	    {
                    	    char_literal22=(Token)match(input,17,FOLLOW_17_in_function_input222);  
                    	    stream_17.add(char_literal22);


                    	    pushFollow(FOLLOW_var_decl_in_function_input225);
                    	    var_decl23=var_decl();

                    	    state._fsp--;

                    	    stream_var_decl.add(var_decl23.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop3;
                        }
                    } while (true);


                    char_literal24=(Token)match(input,16,FOLLOW_16_in_function_input229);  
                    stream_16.add(char_literal24);


                    // AST REWRITE
                    // elements: var_decl
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 40:41: -> ^( FUNCTION_INPUT ( var_decl )+ )
                    {
                        // CGEL.g:40:44: ^( FUNCTION_INPUT ( var_decl )+ )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(FUNCTION_INPUT, "FUNCTION_INPUT")
                        , root_1);

                        if ( !(stream_var_decl.hasNext()) ) {
                            throw new RewriteEarlyExitException();
                        }
                        while ( stream_var_decl.hasNext() ) {
                            adaptor.addChild(root_1, stream_var_decl.nextTree());

                        }
                        stream_var_decl.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "function_input"


    public static class var_decl_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "var_decl"
    // CGEL.g:42:1: var_decl : 'var' ^ '(' ! aType ',' ! ID ')' ! ';' ;
    public final CGELParser.var_decl_return var_decl() throws RecognitionException {
        CGELParser.var_decl_return retval = new CGELParser.var_decl_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal25=null;
        Token char_literal26=null;
        Token char_literal28=null;
        Token ID29=null;
        Token char_literal30=null;
        Token char_literal31=null;
        CGELParser.aType_return aType27 =null;


        CommonTree string_literal25_tree=null;
        CommonTree char_literal26_tree=null;
        CommonTree char_literal28_tree=null;
        CommonTree ID29_tree=null;
        CommonTree char_literal30_tree=null;
        CommonTree char_literal31_tree=null;

        try {
            // CGEL.g:42:9: ( 'var' ^ '(' ! aType ',' ! ID ')' ! ';' )
            // CGEL.g:43:8: 'var' ^ '(' ! aType ',' ! ID ')' ! ';'
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal25=(Token)match(input,24,FOLLOW_24_in_var_decl256); 
            string_literal25_tree = 
            (CommonTree)adaptor.create(string_literal25)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(string_literal25_tree, root_0);


            char_literal26=(Token)match(input,15,FOLLOW_15_in_var_decl259); 

            pushFollow(FOLLOW_aType_in_var_decl262);
            aType27=aType();

            state._fsp--;

            adaptor.addChild(root_0, aType27.getTree());

            char_literal28=(Token)match(input,17,FOLLOW_17_in_var_decl264); 

            ID29=(Token)match(input,ID,FOLLOW_ID_in_var_decl268); 
            ID29_tree = 
            (CommonTree)adaptor.create(ID29)
            ;
            adaptor.addChild(root_0, ID29_tree);


            char_literal30=(Token)match(input,16,FOLLOW_16_in_var_decl270); 

            char_literal31=(Token)match(input,18,FOLLOW_18_in_var_decl273); 
            char_literal31_tree = 
            (CommonTree)adaptor.create(char_literal31)
            ;
            adaptor.addChild(root_0, char_literal31_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "var_decl"


    public static class aType_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "aType"
    // CGEL.g:45:1: aType : ID ;
    public final CGELParser.aType_return aType() throws RecognitionException {
        CGELParser.aType_return retval = new CGELParser.aType_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token ID32=null;

        CommonTree ID32_tree=null;

        try {
            // CGEL.g:45:6: ( ID )
            // CGEL.g:46:9: ID
            {
            root_0 = (CommonTree)adaptor.nil();


            ID32=(Token)match(input,ID,FOLLOW_ID_in_aType292); 
            ID32_tree = 
            (CommonTree)adaptor.create(ID32)
            ;
            adaptor.addChild(root_0, ID32_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "aType"


    public static class block_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "block"
    // CGEL.g:49:1: block : '{' ( stmt )* '}' -> ^( BLOCK ( stmt )* ) ;
    public final CGELParser.block_return block() throws RecognitionException {
        CGELParser.block_return retval = new CGELParser.block_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal33=null;
        Token char_literal35=null;
        CGELParser.stmt_return stmt34 =null;


        CommonTree char_literal33_tree=null;
        CommonTree char_literal35_tree=null;
        RewriteRuleTokenStream stream_26=new RewriteRuleTokenStream(adaptor,"token 26");
        RewriteRuleTokenStream stream_27=new RewriteRuleTokenStream(adaptor,"token 27");
        RewriteRuleSubtreeStream stream_stmt=new RewriteRuleSubtreeStream(adaptor,"rule stmt");
        try {
            // CGEL.g:49:6: ( '{' ( stmt )* '}' -> ^( BLOCK ( stmt )* ) )
            // CGEL.g:50:8: '{' ( stmt )* '}'
            {
            char_literal33=(Token)match(input,26,FOLLOW_26_in_block311);  
            stream_26.add(char_literal33);


            // CGEL.g:50:12: ( stmt )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==19||(LA5_0 >= 23 && LA5_0 <= 25)) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // CGEL.g:50:13: stmt
            	    {
            	    pushFollow(FOLLOW_stmt_in_block314);
            	    stmt34=stmt();

            	    state._fsp--;

            	    stream_stmt.add(stmt34.getTree());

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            char_literal35=(Token)match(input,27,FOLLOW_27_in_block318);  
            stream_27.add(char_literal35);


            // AST REWRITE
            // elements: stmt
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 50:24: -> ^( BLOCK ( stmt )* )
            {
                // CGEL.g:50:27: ^( BLOCK ( stmt )* )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(BLOCK, "BLOCK")
                , root_1);

                // CGEL.g:50:35: ( stmt )*
                while ( stream_stmt.hasNext() ) {
                    adaptor.addChild(root_1, stream_stmt.nextTree());

                }
                stream_stmt.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "block"


    public static class stmt_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "stmt"
    // CGEL.g:53:1: stmt : ( var_decl | assignment | if_else_stmt | while_stmt );
    public final CGELParser.stmt_return stmt() throws RecognitionException {
        CGELParser.stmt_return retval = new CGELParser.stmt_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CGELParser.var_decl_return var_decl36 =null;

        CGELParser.assignment_return assignment37 =null;

        CGELParser.if_else_stmt_return if_else_stmt38 =null;

        CGELParser.while_stmt_return while_stmt39 =null;



        try {
            // CGEL.g:53:5: ( var_decl | assignment | if_else_stmt | while_stmt )
            int alt6=4;
            switch ( input.LA(1) ) {
            case 24:
                {
                alt6=1;
                }
                break;
            case 19:
                {
                alt6=2;
                }
                break;
            case 23:
                {
                alt6=3;
                }
                break;
            case 25:
                {
                alt6=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;

            }

            switch (alt6) {
                case 1 :
                    // CGEL.g:54:9: var_decl
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_var_decl_in_stmt348);
                    var_decl36=var_decl();

                    state._fsp--;

                    adaptor.addChild(root_0, var_decl36.getTree());

                    }
                    break;
                case 2 :
                    // CGEL.g:54:20: assignment
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_assignment_in_stmt352);
                    assignment37=assignment();

                    state._fsp--;

                    adaptor.addChild(root_0, assignment37.getTree());

                    }
                    break;
                case 3 :
                    // CGEL.g:54:33: if_else_stmt
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_if_else_stmt_in_stmt356);
                    if_else_stmt38=if_else_stmt();

                    state._fsp--;

                    adaptor.addChild(root_0, if_else_stmt38.getTree());

                    }
                    break;
                case 4 :
                    // CGEL.g:54:48: while_stmt
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_while_stmt_in_stmt360);
                    while_stmt39=while_stmt();

                    state._fsp--;

                    adaptor.addChild(root_0, while_stmt39.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "stmt"


    public static class assignment_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "assignment"
    // CGEL.g:57:1: assignment : '=' ^ '(' ! ID ',' ! expr ')' ! ';' ;
    public final CGELParser.assignment_return assignment() throws RecognitionException {
        CGELParser.assignment_return retval = new CGELParser.assignment_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal40=null;
        Token char_literal41=null;
        Token ID42=null;
        Token char_literal43=null;
        Token char_literal45=null;
        Token char_literal46=null;
        CGELParser.expr_return expr44 =null;


        CommonTree char_literal40_tree=null;
        CommonTree char_literal41_tree=null;
        CommonTree ID42_tree=null;
        CommonTree char_literal43_tree=null;
        CommonTree char_literal45_tree=null;
        CommonTree char_literal46_tree=null;

        try {
            // CGEL.g:57:11: ( '=' ^ '(' ! ID ',' ! expr ')' ! ';' )
            // CGEL.g:58:9: '=' ^ '(' ! ID ',' ! expr ')' ! ';'
            {
            root_0 = (CommonTree)adaptor.nil();


            char_literal40=(Token)match(input,19,FOLLOW_19_in_assignment380); 
            char_literal40_tree = 
            (CommonTree)adaptor.create(char_literal40)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(char_literal40_tree, root_0);


            char_literal41=(Token)match(input,15,FOLLOW_15_in_assignment383); 

            ID42=(Token)match(input,ID,FOLLOW_ID_in_assignment386); 
            ID42_tree = 
            (CommonTree)adaptor.create(ID42)
            ;
            adaptor.addChild(root_0, ID42_tree);


            char_literal43=(Token)match(input,17,FOLLOW_17_in_assignment388); 

            pushFollow(FOLLOW_expr_in_assignment391);
            expr44=expr();

            state._fsp--;

            adaptor.addChild(root_0, expr44.getTree());

            char_literal45=(Token)match(input,16,FOLLOW_16_in_assignment393); 

            char_literal46=(Token)match(input,18,FOLLOW_18_in_assignment396); 
            char_literal46_tree = 
            (CommonTree)adaptor.create(char_literal46)
            ;
            adaptor.addChild(root_0, char_literal46_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "assignment"


    public static class if_else_stmt_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "if_else_stmt"
    // CGEL.g:60:1: if_else_stmt : 'if' '(' expr ')' b1= block 'else' b2= block -> ^( IF_ELSE expr $b1 $b2) ;
    public final CGELParser.if_else_stmt_return if_else_stmt() throws RecognitionException {
        CGELParser.if_else_stmt_return retval = new CGELParser.if_else_stmt_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal47=null;
        Token char_literal48=null;
        Token char_literal50=null;
        Token string_literal51=null;
        CGELParser.block_return b1 =null;

        CGELParser.block_return b2 =null;

        CGELParser.expr_return expr49 =null;


        CommonTree string_literal47_tree=null;
        CommonTree char_literal48_tree=null;
        CommonTree char_literal50_tree=null;
        CommonTree string_literal51_tree=null;
        RewriteRuleTokenStream stream_21=new RewriteRuleTokenStream(adaptor,"token 21");
        RewriteRuleTokenStream stream_23=new RewriteRuleTokenStream(adaptor,"token 23");
        RewriteRuleTokenStream stream_15=new RewriteRuleTokenStream(adaptor,"token 15");
        RewriteRuleTokenStream stream_16=new RewriteRuleTokenStream(adaptor,"token 16");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        try {
            // CGEL.g:60:13: ( 'if' '(' expr ')' b1= block 'else' b2= block -> ^( IF_ELSE expr $b1 $b2) )
            // CGEL.g:61:9: 'if' '(' expr ')' b1= block 'else' b2= block
            {
            string_literal47=(Token)match(input,23,FOLLOW_23_in_if_else_stmt415);  
            stream_23.add(string_literal47);


            char_literal48=(Token)match(input,15,FOLLOW_15_in_if_else_stmt417);  
            stream_15.add(char_literal48);


            pushFollow(FOLLOW_expr_in_if_else_stmt419);
            expr49=expr();

            state._fsp--;

            stream_expr.add(expr49.getTree());

            char_literal50=(Token)match(input,16,FOLLOW_16_in_if_else_stmt421);  
            stream_16.add(char_literal50);


            pushFollow(FOLLOW_block_in_if_else_stmt425);
            b1=block();

            state._fsp--;

            stream_block.add(b1.getTree());

            string_literal51=(Token)match(input,21,FOLLOW_21_in_if_else_stmt427);  
            stream_21.add(string_literal51);


            pushFollow(FOLLOW_block_in_if_else_stmt431);
            b2=block();

            state._fsp--;

            stream_block.add(b2.getTree());

            // AST REWRITE
            // elements: b2, b1, expr
            // token labels: 
            // rule labels: retval, b1, b2
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_b1=new RewriteRuleSubtreeStream(adaptor,"rule b1",b1!=null?b1.tree:null);
            RewriteRuleSubtreeStream stream_b2=new RewriteRuleSubtreeStream(adaptor,"rule b2",b2!=null?b2.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 61:52: -> ^( IF_ELSE expr $b1 $b2)
            {
                // CGEL.g:61:55: ^( IF_ELSE expr $b1 $b2)
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(IF_ELSE, "IF_ELSE")
                , root_1);

                adaptor.addChild(root_1, stream_expr.nextTree());

                adaptor.addChild(root_1, stream_b1.nextTree());

                adaptor.addChild(root_1, stream_b2.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "if_else_stmt"


    public static class while_stmt_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "while_stmt"
    // CGEL.g:63:1: while_stmt : 'while' '(' expr ')' b= block -> ^( WHILE expr $b) ;
    public final CGELParser.while_stmt_return while_stmt() throws RecognitionException {
        CGELParser.while_stmt_return retval = new CGELParser.while_stmt_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal52=null;
        Token char_literal53=null;
        Token char_literal55=null;
        CGELParser.block_return b =null;

        CGELParser.expr_return expr54 =null;


        CommonTree string_literal52_tree=null;
        CommonTree char_literal53_tree=null;
        CommonTree char_literal55_tree=null;
        RewriteRuleTokenStream stream_15=new RewriteRuleTokenStream(adaptor,"token 15");
        RewriteRuleTokenStream stream_16=new RewriteRuleTokenStream(adaptor,"token 16");
        RewriteRuleTokenStream stream_25=new RewriteRuleTokenStream(adaptor,"token 25");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        try {
            // CGEL.g:63:11: ( 'while' '(' expr ')' b= block -> ^( WHILE expr $b) )
            // CGEL.g:64:9: 'while' '(' expr ')' b= block
            {
            string_literal52=(Token)match(input,25,FOLLOW_25_in_while_stmt464);  
            stream_25.add(string_literal52);


            char_literal53=(Token)match(input,15,FOLLOW_15_in_while_stmt466);  
            stream_15.add(char_literal53);


            pushFollow(FOLLOW_expr_in_while_stmt468);
            expr54=expr();

            state._fsp--;

            stream_expr.add(expr54.getTree());

            char_literal55=(Token)match(input,16,FOLLOW_16_in_while_stmt470);  
            stream_16.add(char_literal55);


            pushFollow(FOLLOW_block_in_while_stmt474);
            b=block();

            state._fsp--;

            stream_block.add(b.getTree());

            // AST REWRITE
            // elements: b, expr
            // token labels: 
            // rule labels: retval, b
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_b=new RewriteRuleSubtreeStream(adaptor,"rule b",b!=null?b.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 64:38: -> ^( WHILE expr $b)
            {
                // CGEL.g:64:41: ^( WHILE expr $b)
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(WHILE, "WHILE")
                , root_1);

                adaptor.addChild(root_1, stream_expr.nextTree());

                adaptor.addChild(root_1, stream_b.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "while_stmt"


    public static class expr_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "expr"
    // CGEL.g:67:1: expr : (| '@' ^ ID | ID ^| BINARY_OPERATOR ^ '(' ! expr ',' ! expr ')' !| RELATIONAL_OPERATOR ^ '(' ! expr ',' ! expr ')' !);
    public final CGELParser.expr_return expr() throws RecognitionException {
        CGELParser.expr_return retval = new CGELParser.expr_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal56=null;
        Token ID57=null;
        Token ID58=null;
        Token BINARY_OPERATOR59=null;
        Token char_literal60=null;
        Token char_literal62=null;
        Token char_literal64=null;
        Token RELATIONAL_OPERATOR65=null;
        Token char_literal66=null;
        Token char_literal68=null;
        Token char_literal70=null;
        CGELParser.expr_return expr61 =null;

        CGELParser.expr_return expr63 =null;

        CGELParser.expr_return expr67 =null;

        CGELParser.expr_return expr69 =null;


        CommonTree char_literal56_tree=null;
        CommonTree ID57_tree=null;
        CommonTree ID58_tree=null;
        CommonTree BINARY_OPERATOR59_tree=null;
        CommonTree char_literal60_tree=null;
        CommonTree char_literal62_tree=null;
        CommonTree char_literal64_tree=null;
        CommonTree RELATIONAL_OPERATOR65_tree=null;
        CommonTree char_literal66_tree=null;
        CommonTree char_literal68_tree=null;
        CommonTree char_literal70_tree=null;

        try {
            // CGEL.g:67:5: (| '@' ^ ID | ID ^| BINARY_OPERATOR ^ '(' ! expr ',' ! expr ')' !| RELATIONAL_OPERATOR ^ '(' ! expr ',' ! expr ')' !)
            int alt7=5;
            switch ( input.LA(1) ) {
            case 16:
            case 17:
                {
                alt7=1;
                }
                break;
            case 20:
                {
                alt7=2;
                }
                break;
            case ID:
                {
                alt7=3;
                }
                break;
            case BINARY_OPERATOR:
                {
                alt7=4;
                }
                break;
            case RELATIONAL_OPERATOR:
                {
                alt7=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;

            }

            switch (alt7) {
                case 1 :
                    // CGEL.g:68:5: 
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    }
                    break;
                case 2 :
                    // CGEL.g:68:7: '@' ^ ID
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    char_literal56=(Token)match(input,20,FOLLOW_20_in_expr504); 
                    char_literal56_tree = 
                    (CommonTree)adaptor.create(char_literal56)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(char_literal56_tree, root_0);


                    ID57=(Token)match(input,ID,FOLLOW_ID_in_expr507); 
                    ID57_tree = 
                    (CommonTree)adaptor.create(ID57)
                    ;
                    adaptor.addChild(root_0, ID57_tree);


                    }
                    break;
                case 3 :
                    // CGEL.g:69:7: ID ^
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    ID58=(Token)match(input,ID,FOLLOW_ID_in_expr517); 
                    ID58_tree = 
                    (CommonTree)adaptor.create(ID58)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(ID58_tree, root_0);


                    }
                    break;
                case 4 :
                    // CGEL.g:70:7: BINARY_OPERATOR ^ '(' ! expr ',' ! expr ')' !
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    BINARY_OPERATOR59=(Token)match(input,BINARY_OPERATOR,FOLLOW_BINARY_OPERATOR_in_expr528); 
                    BINARY_OPERATOR59_tree = 
                    (CommonTree)adaptor.create(BINARY_OPERATOR59)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(BINARY_OPERATOR59_tree, root_0);


                    char_literal60=(Token)match(input,15,FOLLOW_15_in_expr531); 

                    pushFollow(FOLLOW_expr_in_expr535);
                    expr61=expr();

                    state._fsp--;

                    adaptor.addChild(root_0, expr61.getTree());

                    char_literal62=(Token)match(input,17,FOLLOW_17_in_expr537); 

                    pushFollow(FOLLOW_expr_in_expr540);
                    expr63=expr();

                    state._fsp--;

                    adaptor.addChild(root_0, expr63.getTree());

                    char_literal64=(Token)match(input,16,FOLLOW_16_in_expr542); 

                    }
                    break;
                case 5 :
                    // CGEL.g:71:7: RELATIONAL_OPERATOR ^ '(' ! expr ',' ! expr ')' !
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    RELATIONAL_OPERATOR65=(Token)match(input,RELATIONAL_OPERATOR,FOLLOW_RELATIONAL_OPERATOR_in_expr551); 
                    RELATIONAL_OPERATOR65_tree = 
                    (CommonTree)adaptor.create(RELATIONAL_OPERATOR65)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(RELATIONAL_OPERATOR65_tree, root_0);


                    char_literal66=(Token)match(input,15,FOLLOW_15_in_expr554); 

                    pushFollow(FOLLOW_expr_in_expr557);
                    expr67=expr();

                    state._fsp--;

                    adaptor.addChild(root_0, expr67.getTree());

                    char_literal68=(Token)match(input,17,FOLLOW_17_in_expr559); 

                    pushFollow(FOLLOW_expr_in_expr562);
                    expr69=expr();

                    state._fsp--;

                    adaptor.addChild(root_0, expr69.getTree());

                    char_literal70=(Token)match(input,16,FOLLOW_16_in_expr564); 

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "expr"

    // Delegated rules


 

    public static final BitSet FOLLOW_22_in_function91 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_function_prototype_in_function94 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_block_in_function97 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_function_prototype112 = new BitSet(new long[]{0x0000000000028000L});
    public static final BitSet FOLLOW_function_input_in_function_prototype115 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_function_prototype117 = new BitSet(new long[]{0x0000000000028000L});
    public static final BitSet FOLLOW_function_output_in_function_prototype120 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_function_prototype122 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_ID_in_function_prototype125 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_function_prototype129 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_function_output147 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_function_output149 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_function_output163 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_var_decl_in_function_output165 = new BitSet(new long[]{0x0000000000030000L});
    public static final BitSet FOLLOW_17_in_function_output168 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_var_decl_in_function_output171 = new BitSet(new long[]{0x0000000000030000L});
    public static final BitSet FOLLOW_16_in_function_output175 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_function_input201 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_function_input203 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_function_input217 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_var_decl_in_function_input219 = new BitSet(new long[]{0x0000000000030000L});
    public static final BitSet FOLLOW_17_in_function_input222 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_var_decl_in_function_input225 = new BitSet(new long[]{0x0000000000030000L});
    public static final BitSet FOLLOW_16_in_function_input229 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_var_decl256 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_var_decl259 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_aType_in_var_decl262 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_var_decl264 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_ID_in_var_decl268 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_var_decl270 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_var_decl273 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_aType292 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_block311 = new BitSet(new long[]{0x000000000B880000L});
    public static final BitSet FOLLOW_stmt_in_block314 = new BitSet(new long[]{0x000000000B880000L});
    public static final BitSet FOLLOW_27_in_block318 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_var_decl_in_stmt348 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_in_stmt352 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_if_else_stmt_in_stmt356 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_while_stmt_in_stmt360 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_assignment380 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_assignment383 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_ID_in_assignment386 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_assignment388 = new BitSet(new long[]{0x0000000000110910L});
    public static final BitSet FOLLOW_expr_in_assignment391 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_assignment393 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_assignment396 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_if_else_stmt415 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_if_else_stmt417 = new BitSet(new long[]{0x0000000000110910L});
    public static final BitSet FOLLOW_expr_in_if_else_stmt419 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_if_else_stmt421 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_block_in_if_else_stmt425 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_if_else_stmt427 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_block_in_if_else_stmt431 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_while_stmt464 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_while_stmt466 = new BitSet(new long[]{0x0000000000110910L});
    public static final BitSet FOLLOW_expr_in_while_stmt468 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_while_stmt470 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_block_in_while_stmt474 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_expr504 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_ID_in_expr507 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_expr517 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BINARY_OPERATOR_in_expr528 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_expr531 = new BitSet(new long[]{0x0000000000120910L});
    public static final BitSet FOLLOW_expr_in_expr535 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_expr537 = new BitSet(new long[]{0x0000000000110910L});
    public static final BitSet FOLLOW_expr_in_expr540 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_expr542 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RELATIONAL_OPERATOR_in_expr551 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_expr554 = new BitSet(new long[]{0x0000000000120910L});
    public static final BitSet FOLLOW_expr_in_expr557 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_expr559 = new BitSet(new long[]{0x0000000000110910L});
    public static final BitSet FOLLOW_expr_in_expr562 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_expr564 = new BitSet(new long[]{0x0000000000000002L});

}