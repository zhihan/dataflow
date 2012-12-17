// $ANTLR 3.4 CGEL.g 2012-12-14 11:02:43

package my.ir;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.runtime.tree.*;


@SuppressWarnings({"all", "warnings", "unchecked"})
public class CGELParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "BINARY_OPERATOR", "BLOCK", "FUNCTION_INPUT", "FUNCTION_OUTPUT", "ID", "IF_ELSE", "LETTER", "SL_COMMENTS", "WS", "'('", "')'", "','", "';'", "'='", "'@'", "'else'", "'function'", "'if'", "'var'", "'{'", "'}'"
    };

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
    // CGEL.g:27:1: function : 'function' ! function_prototype ^ block ;
    public final CGELParser.function_return function() throws RecognitionException {
        CGELParser.function_return retval = new CGELParser.function_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal1=null;
        CGELParser.function_prototype_return function_prototype2 =null;

        CGELParser.block_return block3 =null;


        CommonTree string_literal1_tree=null;

        try {
            // CGEL.g:27:9: ( 'function' ! function_prototype ^ block )
            // CGEL.g:28:9: 'function' ! function_prototype ^ block
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal1=(Token)match(input,20,FOLLOW_20_in_function86); 

            pushFollow(FOLLOW_function_prototype_in_function89);
            function_prototype2=function_prototype();

            state._fsp--;

            root_0 = (CommonTree)adaptor.becomeRoot(function_prototype2.getTree(), root_0);

            pushFollow(FOLLOW_block_in_function92);
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
    // CGEL.g:30:1: function_prototype : '(' ! function_input ',' ! function_output ',' ! ID ^ ')' !;
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
            // CGEL.g:30:19: ( '(' ! function_input ',' ! function_output ',' ! ID ^ ')' !)
            // CGEL.g:31:5: '(' ! function_input ',' ! function_output ',' ! ID ^ ')' !
            {
            root_0 = (CommonTree)adaptor.nil();


            char_literal4=(Token)match(input,13,FOLLOW_13_in_function_prototype107); 

            pushFollow(FOLLOW_function_input_in_function_prototype110);
            function_input5=function_input();

            state._fsp--;

            adaptor.addChild(root_0, function_input5.getTree());

            char_literal6=(Token)match(input,15,FOLLOW_15_in_function_prototype112); 

            pushFollow(FOLLOW_function_output_in_function_prototype115);
            function_output7=function_output();

            state._fsp--;

            adaptor.addChild(root_0, function_output7.getTree());

            char_literal8=(Token)match(input,15,FOLLOW_15_in_function_prototype117); 

            ID9=(Token)match(input,ID,FOLLOW_ID_in_function_prototype120); 
            ID9_tree = 
            (CommonTree)adaptor.create(ID9)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(ID9_tree, root_0);


            char_literal10=(Token)match(input,14,FOLLOW_14_in_function_prototype124); 

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
    // CGEL.g:33:1: function_output : (| '(' ')' -> ^( FUNCTION_OUTPUT ) | '(' var_decl ( ',' var_decl )* ')' -> ^( FUNCTION_OUTPUT ( var_decl )+ ) );
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
        RewriteRuleTokenStream stream_15=new RewriteRuleTokenStream(adaptor,"token 15");
        RewriteRuleTokenStream stream_13=new RewriteRuleTokenStream(adaptor,"token 13");
        RewriteRuleTokenStream stream_14=new RewriteRuleTokenStream(adaptor,"token 14");
        RewriteRuleSubtreeStream stream_var_decl=new RewriteRuleSubtreeStream(adaptor,"rule var_decl");
        try {
            // CGEL.g:33:16: (| '(' ')' -> ^( FUNCTION_OUTPUT ) | '(' var_decl ( ',' var_decl )* ')' -> ^( FUNCTION_OUTPUT ( var_decl )+ ) )
            int alt2=3;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==15) ) {
                alt2=1;
            }
            else if ( (LA2_0==13) ) {
                int LA2_2 = input.LA(2);

                if ( (LA2_2==14) ) {
                    alt2=2;
                }
                else if ( (LA2_2==22) ) {
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
                    // CGEL.g:34:5: 
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    }
                    break;
                case 2 :
                    // CGEL.g:34:7: '(' ')'
                    {
                    char_literal11=(Token)match(input,13,FOLLOW_13_in_function_output142);  
                    stream_13.add(char_literal11);


                    char_literal12=(Token)match(input,14,FOLLOW_14_in_function_output144);  
                    stream_14.add(char_literal12);


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
                    // 34:15: -> ^( FUNCTION_OUTPUT )
                    {
                        // CGEL.g:34:18: ^( FUNCTION_OUTPUT )
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
                    // CGEL.g:35:7: '(' var_decl ( ',' var_decl )* ')'
                    {
                    char_literal13=(Token)match(input,13,FOLLOW_13_in_function_output158);  
                    stream_13.add(char_literal13);


                    pushFollow(FOLLOW_var_decl_in_function_output160);
                    var_decl14=var_decl();

                    state._fsp--;

                    stream_var_decl.add(var_decl14.getTree());

                    // CGEL.g:35:20: ( ',' var_decl )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==15) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // CGEL.g:35:21: ',' var_decl
                    	    {
                    	    char_literal15=(Token)match(input,15,FOLLOW_15_in_function_output163);  
                    	    stream_15.add(char_literal15);


                    	    pushFollow(FOLLOW_var_decl_in_function_output166);
                    	    var_decl16=var_decl();

                    	    state._fsp--;

                    	    stream_var_decl.add(var_decl16.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop1;
                        }
                    } while (true);


                    char_literal17=(Token)match(input,14,FOLLOW_14_in_function_output170);  
                    stream_14.add(char_literal17);


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
                    // 35:41: -> ^( FUNCTION_OUTPUT ( var_decl )+ )
                    {
                        // CGEL.g:35:44: ^( FUNCTION_OUTPUT ( var_decl )+ )
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
    // CGEL.g:37:1: function_input : (| '(' ')' -> ^( FUNCTION_INPUT ) | '(' var_decl ( ',' var_decl )* ')' -> ^( FUNCTION_INPUT ( var_decl )+ ) );
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
        RewriteRuleTokenStream stream_15=new RewriteRuleTokenStream(adaptor,"token 15");
        RewriteRuleTokenStream stream_13=new RewriteRuleTokenStream(adaptor,"token 13");
        RewriteRuleTokenStream stream_14=new RewriteRuleTokenStream(adaptor,"token 14");
        RewriteRuleSubtreeStream stream_var_decl=new RewriteRuleSubtreeStream(adaptor,"rule var_decl");
        try {
            // CGEL.g:37:15: (| '(' ')' -> ^( FUNCTION_INPUT ) | '(' var_decl ( ',' var_decl )* ')' -> ^( FUNCTION_INPUT ( var_decl )+ ) )
            int alt4=3;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==15) ) {
                alt4=1;
            }
            else if ( (LA4_0==13) ) {
                int LA4_2 = input.LA(2);

                if ( (LA4_2==14) ) {
                    alt4=2;
                }
                else if ( (LA4_2==22) ) {
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
                    // CGEL.g:38:5: 
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    }
                    break;
                case 2 :
                    // CGEL.g:38:7: '(' ')'
                    {
                    char_literal18=(Token)match(input,13,FOLLOW_13_in_function_input196);  
                    stream_13.add(char_literal18);


                    char_literal19=(Token)match(input,14,FOLLOW_14_in_function_input198);  
                    stream_14.add(char_literal19);


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
                    // 38:15: -> ^( FUNCTION_INPUT )
                    {
                        // CGEL.g:38:18: ^( FUNCTION_INPUT )
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
                    // CGEL.g:39:7: '(' var_decl ( ',' var_decl )* ')'
                    {
                    char_literal20=(Token)match(input,13,FOLLOW_13_in_function_input212);  
                    stream_13.add(char_literal20);


                    pushFollow(FOLLOW_var_decl_in_function_input214);
                    var_decl21=var_decl();

                    state._fsp--;

                    stream_var_decl.add(var_decl21.getTree());

                    // CGEL.g:39:20: ( ',' var_decl )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0==15) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // CGEL.g:39:21: ',' var_decl
                    	    {
                    	    char_literal22=(Token)match(input,15,FOLLOW_15_in_function_input217);  
                    	    stream_15.add(char_literal22);


                    	    pushFollow(FOLLOW_var_decl_in_function_input220);
                    	    var_decl23=var_decl();

                    	    state._fsp--;

                    	    stream_var_decl.add(var_decl23.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop3;
                        }
                    } while (true);


                    char_literal24=(Token)match(input,14,FOLLOW_14_in_function_input224);  
                    stream_14.add(char_literal24);


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
                    // 39:41: -> ^( FUNCTION_INPUT ( var_decl )+ )
                    {
                        // CGEL.g:39:44: ^( FUNCTION_INPUT ( var_decl )+ )
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
    // CGEL.g:41:1: var_decl : 'var' ^ '(' ! aType ',' ! ID ')' ! ';' ;
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
            // CGEL.g:41:9: ( 'var' ^ '(' ! aType ',' ! ID ')' ! ';' )
            // CGEL.g:42:8: 'var' ^ '(' ! aType ',' ! ID ')' ! ';'
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal25=(Token)match(input,22,FOLLOW_22_in_var_decl251); 
            string_literal25_tree = 
            (CommonTree)adaptor.create(string_literal25)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(string_literal25_tree, root_0);


            char_literal26=(Token)match(input,13,FOLLOW_13_in_var_decl254); 

            pushFollow(FOLLOW_aType_in_var_decl257);
            aType27=aType();

            state._fsp--;

            adaptor.addChild(root_0, aType27.getTree());

            char_literal28=(Token)match(input,15,FOLLOW_15_in_var_decl259); 

            ID29=(Token)match(input,ID,FOLLOW_ID_in_var_decl263); 
            ID29_tree = 
            (CommonTree)adaptor.create(ID29)
            ;
            adaptor.addChild(root_0, ID29_tree);


            char_literal30=(Token)match(input,14,FOLLOW_14_in_var_decl265); 

            char_literal31=(Token)match(input,16,FOLLOW_16_in_var_decl268); 
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
    // CGEL.g:44:1: aType : ID ;
    public final CGELParser.aType_return aType() throws RecognitionException {
        CGELParser.aType_return retval = new CGELParser.aType_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token ID32=null;

        CommonTree ID32_tree=null;

        try {
            // CGEL.g:44:6: ( ID )
            // CGEL.g:45:9: ID
            {
            root_0 = (CommonTree)adaptor.nil();


            ID32=(Token)match(input,ID,FOLLOW_ID_in_aType287); 
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
    // CGEL.g:48:1: block : '{' ( stmt )* '}' -> ^( BLOCK ( stmt )* ) ;
    public final CGELParser.block_return block() throws RecognitionException {
        CGELParser.block_return retval = new CGELParser.block_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal33=null;
        Token char_literal35=null;
        CGELParser.stmt_return stmt34 =null;


        CommonTree char_literal33_tree=null;
        CommonTree char_literal35_tree=null;
        RewriteRuleTokenStream stream_23=new RewriteRuleTokenStream(adaptor,"token 23");
        RewriteRuleTokenStream stream_24=new RewriteRuleTokenStream(adaptor,"token 24");
        RewriteRuleSubtreeStream stream_stmt=new RewriteRuleSubtreeStream(adaptor,"rule stmt");
        try {
            // CGEL.g:48:6: ( '{' ( stmt )* '}' -> ^( BLOCK ( stmt )* ) )
            // CGEL.g:49:8: '{' ( stmt )* '}'
            {
            char_literal33=(Token)match(input,23,FOLLOW_23_in_block306);  
            stream_23.add(char_literal33);


            // CGEL.g:49:12: ( stmt )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==17||(LA5_0 >= 21 && LA5_0 <= 22)) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // CGEL.g:49:13: stmt
            	    {
            	    pushFollow(FOLLOW_stmt_in_block309);
            	    stmt34=stmt();

            	    state._fsp--;

            	    stream_stmt.add(stmt34.getTree());

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            char_literal35=(Token)match(input,24,FOLLOW_24_in_block313);  
            stream_24.add(char_literal35);


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
            // 49:24: -> ^( BLOCK ( stmt )* )
            {
                // CGEL.g:49:27: ^( BLOCK ( stmt )* )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(BLOCK, "BLOCK")
                , root_1);

                // CGEL.g:49:35: ( stmt )*
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
    // CGEL.g:52:1: stmt : ( var_decl | assignment | if_else_stmt );
    public final CGELParser.stmt_return stmt() throws RecognitionException {
        CGELParser.stmt_return retval = new CGELParser.stmt_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CGELParser.var_decl_return var_decl36 =null;

        CGELParser.assignment_return assignment37 =null;

        CGELParser.if_else_stmt_return if_else_stmt38 =null;



        try {
            // CGEL.g:52:5: ( var_decl | assignment | if_else_stmt )
            int alt6=3;
            switch ( input.LA(1) ) {
            case 22:
                {
                alt6=1;
                }
                break;
            case 17:
                {
                alt6=2;
                }
                break;
            case 21:
                {
                alt6=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;

            }

            switch (alt6) {
                case 1 :
                    // CGEL.g:53:9: var_decl
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_var_decl_in_stmt343);
                    var_decl36=var_decl();

                    state._fsp--;

                    adaptor.addChild(root_0, var_decl36.getTree());

                    }
                    break;
                case 2 :
                    // CGEL.g:53:20: assignment
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_assignment_in_stmt347);
                    assignment37=assignment();

                    state._fsp--;

                    adaptor.addChild(root_0, assignment37.getTree());

                    }
                    break;
                case 3 :
                    // CGEL.g:53:33: if_else_stmt
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_if_else_stmt_in_stmt351);
                    if_else_stmt38=if_else_stmt();

                    state._fsp--;

                    adaptor.addChild(root_0, if_else_stmt38.getTree());

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
    // CGEL.g:56:1: assignment : '=' ^ '(' ! ID ',' ! expr ')' ! ';' ;
    public final CGELParser.assignment_return assignment() throws RecognitionException {
        CGELParser.assignment_return retval = new CGELParser.assignment_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal39=null;
        Token char_literal40=null;
        Token ID41=null;
        Token char_literal42=null;
        Token char_literal44=null;
        Token char_literal45=null;
        CGELParser.expr_return expr43 =null;


        CommonTree char_literal39_tree=null;
        CommonTree char_literal40_tree=null;
        CommonTree ID41_tree=null;
        CommonTree char_literal42_tree=null;
        CommonTree char_literal44_tree=null;
        CommonTree char_literal45_tree=null;

        try {
            // CGEL.g:56:11: ( '=' ^ '(' ! ID ',' ! expr ')' ! ';' )
            // CGEL.g:57:9: '=' ^ '(' ! ID ',' ! expr ')' ! ';'
            {
            root_0 = (CommonTree)adaptor.nil();


            char_literal39=(Token)match(input,17,FOLLOW_17_in_assignment372); 
            char_literal39_tree = 
            (CommonTree)adaptor.create(char_literal39)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(char_literal39_tree, root_0);


            char_literal40=(Token)match(input,13,FOLLOW_13_in_assignment375); 

            ID41=(Token)match(input,ID,FOLLOW_ID_in_assignment378); 
            ID41_tree = 
            (CommonTree)adaptor.create(ID41)
            ;
            adaptor.addChild(root_0, ID41_tree);


            char_literal42=(Token)match(input,15,FOLLOW_15_in_assignment380); 

            pushFollow(FOLLOW_expr_in_assignment383);
            expr43=expr();

            state._fsp--;

            adaptor.addChild(root_0, expr43.getTree());

            char_literal44=(Token)match(input,14,FOLLOW_14_in_assignment385); 

            char_literal45=(Token)match(input,16,FOLLOW_16_in_assignment388); 
            char_literal45_tree = 
            (CommonTree)adaptor.create(char_literal45)
            ;
            adaptor.addChild(root_0, char_literal45_tree);


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
    // CGEL.g:59:1: if_else_stmt : 'if' '(' expr ')' b1= block 'else' b2= block -> ^( IF_ELSE expr $b1 $b2) ;
    public final CGELParser.if_else_stmt_return if_else_stmt() throws RecognitionException {
        CGELParser.if_else_stmt_return retval = new CGELParser.if_else_stmt_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal46=null;
        Token char_literal47=null;
        Token char_literal49=null;
        Token string_literal50=null;
        CGELParser.block_return b1 =null;

        CGELParser.block_return b2 =null;

        CGELParser.expr_return expr48 =null;


        CommonTree string_literal46_tree=null;
        CommonTree char_literal47_tree=null;
        CommonTree char_literal49_tree=null;
        CommonTree string_literal50_tree=null;
        RewriteRuleTokenStream stream_21=new RewriteRuleTokenStream(adaptor,"token 21");
        RewriteRuleTokenStream stream_19=new RewriteRuleTokenStream(adaptor,"token 19");
        RewriteRuleTokenStream stream_13=new RewriteRuleTokenStream(adaptor,"token 13");
        RewriteRuleTokenStream stream_14=new RewriteRuleTokenStream(adaptor,"token 14");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        try {
            // CGEL.g:59:13: ( 'if' '(' expr ')' b1= block 'else' b2= block -> ^( IF_ELSE expr $b1 $b2) )
            // CGEL.g:60:9: 'if' '(' expr ')' b1= block 'else' b2= block
            {
            string_literal46=(Token)match(input,21,FOLLOW_21_in_if_else_stmt407);  
            stream_21.add(string_literal46);


            char_literal47=(Token)match(input,13,FOLLOW_13_in_if_else_stmt409);  
            stream_13.add(char_literal47);


            pushFollow(FOLLOW_expr_in_if_else_stmt411);
            expr48=expr();

            state._fsp--;

            stream_expr.add(expr48.getTree());

            char_literal49=(Token)match(input,14,FOLLOW_14_in_if_else_stmt413);  
            stream_14.add(char_literal49);


            pushFollow(FOLLOW_block_in_if_else_stmt417);
            b1=block();

            state._fsp--;

            stream_block.add(b1.getTree());

            string_literal50=(Token)match(input,19,FOLLOW_19_in_if_else_stmt419);  
            stream_19.add(string_literal50);


            pushFollow(FOLLOW_block_in_if_else_stmt423);
            b2=block();

            state._fsp--;

            stream_block.add(b2.getTree());

            // AST REWRITE
            // elements: expr, b1, b2
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
            // 60:52: -> ^( IF_ELSE expr $b1 $b2)
            {
                // CGEL.g:60:55: ^( IF_ELSE expr $b1 $b2)
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


    public static class expr_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "expr"
    // CGEL.g:63:1: expr : (| '@' ^ ID | ID ^| BINARY_OPERATOR ^ '(' ! expr ',' ! expr ')' !);
    public final CGELParser.expr_return expr() throws RecognitionException {
        CGELParser.expr_return retval = new CGELParser.expr_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal51=null;
        Token ID52=null;
        Token ID53=null;
        Token BINARY_OPERATOR54=null;
        Token char_literal55=null;
        Token char_literal57=null;
        Token char_literal59=null;
        CGELParser.expr_return expr56 =null;

        CGELParser.expr_return expr58 =null;


        CommonTree char_literal51_tree=null;
        CommonTree ID52_tree=null;
        CommonTree ID53_tree=null;
        CommonTree BINARY_OPERATOR54_tree=null;
        CommonTree char_literal55_tree=null;
        CommonTree char_literal57_tree=null;
        CommonTree char_literal59_tree=null;

        try {
            // CGEL.g:63:5: (| '@' ^ ID | ID ^| BINARY_OPERATOR ^ '(' ! expr ',' ! expr ')' !)
            int alt7=4;
            switch ( input.LA(1) ) {
            case 14:
            case 15:
                {
                alt7=1;
                }
                break;
            case 18:
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
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;

            }

            switch (alt7) {
                case 1 :
                    // CGEL.g:64:5: 
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    }
                    break;
                case 2 :
                    // CGEL.g:64:7: '@' ^ ID
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    char_literal51=(Token)match(input,18,FOLLOW_18_in_expr456); 
                    char_literal51_tree = 
                    (CommonTree)adaptor.create(char_literal51)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(char_literal51_tree, root_0);


                    ID52=(Token)match(input,ID,FOLLOW_ID_in_expr459); 
                    ID52_tree = 
                    (CommonTree)adaptor.create(ID52)
                    ;
                    adaptor.addChild(root_0, ID52_tree);


                    }
                    break;
                case 3 :
                    // CGEL.g:65:7: ID ^
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    ID53=(Token)match(input,ID,FOLLOW_ID_in_expr469); 
                    ID53_tree = 
                    (CommonTree)adaptor.create(ID53)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(ID53_tree, root_0);


                    }
                    break;
                case 4 :
                    // CGEL.g:66:7: BINARY_OPERATOR ^ '(' ! expr ',' ! expr ')' !
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    BINARY_OPERATOR54=(Token)match(input,BINARY_OPERATOR,FOLLOW_BINARY_OPERATOR_in_expr480); 
                    BINARY_OPERATOR54_tree = 
                    (CommonTree)adaptor.create(BINARY_OPERATOR54)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(BINARY_OPERATOR54_tree, root_0);


                    char_literal55=(Token)match(input,13,FOLLOW_13_in_expr483); 

                    pushFollow(FOLLOW_expr_in_expr487);
                    expr56=expr();

                    state._fsp--;

                    adaptor.addChild(root_0, expr56.getTree());

                    char_literal57=(Token)match(input,15,FOLLOW_15_in_expr489); 

                    pushFollow(FOLLOW_expr_in_expr492);
                    expr58=expr();

                    state._fsp--;

                    adaptor.addChild(root_0, expr58.getTree());

                    char_literal59=(Token)match(input,14,FOLLOW_14_in_expr494); 

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


 

    public static final BitSet FOLLOW_20_in_function86 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_function_prototype_in_function89 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_block_in_function92 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_function_prototype107 = new BitSet(new long[]{0x000000000000A000L});
    public static final BitSet FOLLOW_function_input_in_function_prototype110 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_function_prototype112 = new BitSet(new long[]{0x000000000000A000L});
    public static final BitSet FOLLOW_function_output_in_function_prototype115 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_function_prototype117 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_ID_in_function_prototype120 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_function_prototype124 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_function_output142 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_function_output144 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_function_output158 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_var_decl_in_function_output160 = new BitSet(new long[]{0x000000000000C000L});
    public static final BitSet FOLLOW_15_in_function_output163 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_var_decl_in_function_output166 = new BitSet(new long[]{0x000000000000C000L});
    public static final BitSet FOLLOW_14_in_function_output170 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_function_input196 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_function_input198 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_function_input212 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_var_decl_in_function_input214 = new BitSet(new long[]{0x000000000000C000L});
    public static final BitSet FOLLOW_15_in_function_input217 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_var_decl_in_function_input220 = new BitSet(new long[]{0x000000000000C000L});
    public static final BitSet FOLLOW_14_in_function_input224 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_var_decl251 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_var_decl254 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_aType_in_var_decl257 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_var_decl259 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_ID_in_var_decl263 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_var_decl265 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_var_decl268 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_aType287 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_block306 = new BitSet(new long[]{0x0000000001620000L});
    public static final BitSet FOLLOW_stmt_in_block309 = new BitSet(new long[]{0x0000000001620000L});
    public static final BitSet FOLLOW_24_in_block313 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_var_decl_in_stmt343 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_in_stmt347 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_if_else_stmt_in_stmt351 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_assignment372 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_assignment375 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_ID_in_assignment378 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_assignment380 = new BitSet(new long[]{0x0000000000044110L});
    public static final BitSet FOLLOW_expr_in_assignment383 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_assignment385 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_assignment388 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_if_else_stmt407 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_if_else_stmt409 = new BitSet(new long[]{0x0000000000044110L});
    public static final BitSet FOLLOW_expr_in_if_else_stmt411 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_if_else_stmt413 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_block_in_if_else_stmt417 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_if_else_stmt419 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_block_in_if_else_stmt423 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_expr456 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_ID_in_expr459 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_expr469 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BINARY_OPERATOR_in_expr480 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_expr483 = new BitSet(new long[]{0x0000000000048110L});
    public static final BitSet FOLLOW_expr_in_expr487 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_expr489 = new BitSet(new long[]{0x0000000000044110L});
    public static final BitSet FOLLOW_expr_in_expr492 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_expr494 = new BitSet(new long[]{0x0000000000000002L});

}