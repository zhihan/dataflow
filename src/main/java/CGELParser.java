// $ANTLR 3.4 CGEL.g 2013-01-10 11:35:55

package my.ir;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.tree.*;


@SuppressWarnings({"all", "warnings", "unchecked"})
public class CGELParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "BINARY_OPERATOR", "BLOCK", "FUNCTION_CALL", "FUNCTION_INPUT", "FUNCTION_OUTPUT", "ID", "IF_ELSE", "LETTER", "RELATIONAL_OPERATOR", "SL_COMMENTS", "WHILE", "WS", "'('", "')'", "','", "';'", "'='", "'@'", "'else'", "'function'", "'if'", "'var'", "'while'", "'{'", "'}'"
    };

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
    // CGEL.g:30:1: function : 'function' ! function_prototype ^ block ;
    public final CGELParser.function_return function() throws RecognitionException {
        CGELParser.function_return retval = new CGELParser.function_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal1=null;
        CGELParser.function_prototype_return function_prototype2 =null;

        CGELParser.block_return block3 =null;


        CommonTree string_literal1_tree=null;

        try {
            // CGEL.g:30:9: ( 'function' ! function_prototype ^ block )
            // CGEL.g:31:9: 'function' ! function_prototype ^ block
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal1=(Token)match(input,23,FOLLOW_23_in_function108); if (state.failed) return retval;

            pushFollow(FOLLOW_function_prototype_in_function111);
            function_prototype2=function_prototype();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) root_0 = (CommonTree)adaptor.becomeRoot(function_prototype2.getTree(), root_0);

            pushFollow(FOLLOW_block_in_function114);
            block3=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, block3.getTree());

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
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
    // CGEL.g:33:1: function_prototype : '(' ! function_input ',' ! function_output ',' ! ID ^ ')' !;
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
            // CGEL.g:33:19: ( '(' ! function_input ',' ! function_output ',' ! ID ^ ')' !)
            // CGEL.g:34:5: '(' ! function_input ',' ! function_output ',' ! ID ^ ')' !
            {
            root_0 = (CommonTree)adaptor.nil();


            char_literal4=(Token)match(input,16,FOLLOW_16_in_function_prototype129); if (state.failed) return retval;

            pushFollow(FOLLOW_function_input_in_function_prototype132);
            function_input5=function_input();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, function_input5.getTree());

            char_literal6=(Token)match(input,18,FOLLOW_18_in_function_prototype134); if (state.failed) return retval;

            pushFollow(FOLLOW_function_output_in_function_prototype137);
            function_output7=function_output();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, function_output7.getTree());

            char_literal8=(Token)match(input,18,FOLLOW_18_in_function_prototype139); if (state.failed) return retval;

            ID9=(Token)match(input,ID,FOLLOW_ID_in_function_prototype142); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            ID9_tree = 
            (CommonTree)adaptor.create(ID9)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(ID9_tree, root_0);
            }

            char_literal10=(Token)match(input,17,FOLLOW_17_in_function_prototype146); if (state.failed) return retval;

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
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
    // CGEL.g:36:1: function_output : (| '(' ')' -> ^( FUNCTION_OUTPUT ) | '(' var_decl ( ',' var_decl )* ')' -> ^( FUNCTION_OUTPUT ( var_decl )+ ) );
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
        RewriteRuleTokenStream stream_18=new RewriteRuleTokenStream(adaptor,"token 18");
        RewriteRuleTokenStream stream_16=new RewriteRuleTokenStream(adaptor,"token 16");
        RewriteRuleSubtreeStream stream_var_decl=new RewriteRuleSubtreeStream(adaptor,"rule var_decl");
        try {
            // CGEL.g:36:16: (| '(' ')' -> ^( FUNCTION_OUTPUT ) | '(' var_decl ( ',' var_decl )* ')' -> ^( FUNCTION_OUTPUT ( var_decl )+ ) )
            int alt2=3;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==18) ) {
                alt2=1;
            }
            else if ( (LA2_0==16) ) {
                int LA2_2 = input.LA(2);

                if ( (LA2_2==17) ) {
                    alt2=2;
                }
                else if ( (LA2_2==25) ) {
                    alt2=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 2, 2, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;

            }
            switch (alt2) {
                case 1 :
                    // CGEL.g:37:5: 
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    }
                    break;
                case 2 :
                    // CGEL.g:37:7: '(' ')'
                    {
                    char_literal11=(Token)match(input,16,FOLLOW_16_in_function_output164); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_16.add(char_literal11);


                    char_literal12=(Token)match(input,17,FOLLOW_17_in_function_output166); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_17.add(char_literal12);


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 37:15: -> ^( FUNCTION_OUTPUT )
                    {
                        // CGEL.g:37:18: ^( FUNCTION_OUTPUT )
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

                    }
                    break;
                case 3 :
                    // CGEL.g:38:7: '(' var_decl ( ',' var_decl )* ')'
                    {
                    char_literal13=(Token)match(input,16,FOLLOW_16_in_function_output180); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_16.add(char_literal13);


                    pushFollow(FOLLOW_var_decl_in_function_output182);
                    var_decl14=var_decl();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_var_decl.add(var_decl14.getTree());

                    // CGEL.g:38:20: ( ',' var_decl )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==18) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // CGEL.g:38:21: ',' var_decl
                    	    {
                    	    char_literal15=(Token)match(input,18,FOLLOW_18_in_function_output185); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_18.add(char_literal15);


                    	    pushFollow(FOLLOW_var_decl_in_function_output188);
                    	    var_decl16=var_decl();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_var_decl.add(var_decl16.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop1;
                        }
                    } while (true);


                    char_literal17=(Token)match(input,17,FOLLOW_17_in_function_output193); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_17.add(char_literal17);


                    // AST REWRITE
                    // elements: var_decl
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 38:42: -> ^( FUNCTION_OUTPUT ( var_decl )+ )
                    {
                        // CGEL.g:38:45: ^( FUNCTION_OUTPUT ( var_decl )+ )
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

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
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
    // CGEL.g:40:1: function_input : (| '(' ')' -> ^( FUNCTION_INPUT ) | '(' var_decl ( ',' var_decl )* ')' -> ^( FUNCTION_INPUT ( var_decl )+ ) );
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
        RewriteRuleTokenStream stream_18=new RewriteRuleTokenStream(adaptor,"token 18");
        RewriteRuleTokenStream stream_16=new RewriteRuleTokenStream(adaptor,"token 16");
        RewriteRuleSubtreeStream stream_var_decl=new RewriteRuleSubtreeStream(adaptor,"rule var_decl");
        try {
            // CGEL.g:40:15: (| '(' ')' -> ^( FUNCTION_INPUT ) | '(' var_decl ( ',' var_decl )* ')' -> ^( FUNCTION_INPUT ( var_decl )+ ) )
            int alt4=3;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==18) ) {
                alt4=1;
            }
            else if ( (LA4_0==16) ) {
                int LA4_2 = input.LA(2);

                if ( (LA4_2==17) ) {
                    alt4=2;
                }
                else if ( (LA4_2==25) ) {
                    alt4=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 2, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;

            }
            switch (alt4) {
                case 1 :
                    // CGEL.g:41:5: 
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    }
                    break;
                case 2 :
                    // CGEL.g:41:7: '(' ')'
                    {
                    char_literal18=(Token)match(input,16,FOLLOW_16_in_function_input219); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_16.add(char_literal18);


                    char_literal19=(Token)match(input,17,FOLLOW_17_in_function_input221); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_17.add(char_literal19);


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 41:15: -> ^( FUNCTION_INPUT )
                    {
                        // CGEL.g:41:18: ^( FUNCTION_INPUT )
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

                    }
                    break;
                case 3 :
                    // CGEL.g:42:7: '(' var_decl ( ',' var_decl )* ')'
                    {
                    char_literal20=(Token)match(input,16,FOLLOW_16_in_function_input235); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_16.add(char_literal20);


                    pushFollow(FOLLOW_var_decl_in_function_input237);
                    var_decl21=var_decl();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_var_decl.add(var_decl21.getTree());

                    // CGEL.g:42:20: ( ',' var_decl )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0==18) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // CGEL.g:42:21: ',' var_decl
                    	    {
                    	    char_literal22=(Token)match(input,18,FOLLOW_18_in_function_input240); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_18.add(char_literal22);


                    	    pushFollow(FOLLOW_var_decl_in_function_input243);
                    	    var_decl23=var_decl();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_var_decl.add(var_decl23.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop3;
                        }
                    } while (true);


                    char_literal24=(Token)match(input,17,FOLLOW_17_in_function_input248); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_17.add(char_literal24);


                    // AST REWRITE
                    // elements: var_decl
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 42:42: -> ^( FUNCTION_INPUT ( var_decl )+ )
                    {
                        // CGEL.g:42:45: ^( FUNCTION_INPUT ( var_decl )+ )
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

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
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
    // CGEL.g:44:1: var_decl : 'var' ^ '(' ! aType ',' ! ID ')' ! ';' ;
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
            // CGEL.g:44:9: ( 'var' ^ '(' ! aType ',' ! ID ')' ! ';' )
            // CGEL.g:45:8: 'var' ^ '(' ! aType ',' ! ID ')' ! ';'
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal25=(Token)match(input,25,FOLLOW_25_in_var_decl275); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal25_tree = 
            (CommonTree)adaptor.create(string_literal25)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(string_literal25_tree, root_0);
            }

            char_literal26=(Token)match(input,16,FOLLOW_16_in_var_decl278); if (state.failed) return retval;

            pushFollow(FOLLOW_aType_in_var_decl281);
            aType27=aType();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, aType27.getTree());

            char_literal28=(Token)match(input,18,FOLLOW_18_in_var_decl283); if (state.failed) return retval;

            ID29=(Token)match(input,ID,FOLLOW_ID_in_var_decl287); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            ID29_tree = 
            (CommonTree)adaptor.create(ID29)
            ;
            adaptor.addChild(root_0, ID29_tree);
            }

            char_literal30=(Token)match(input,17,FOLLOW_17_in_var_decl289); if (state.failed) return retval;

            char_literal31=(Token)match(input,19,FOLLOW_19_in_var_decl292); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal31_tree = 
            (CommonTree)adaptor.create(char_literal31)
            ;
            adaptor.addChild(root_0, char_literal31_tree);
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
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
    // CGEL.g:47:1: aType : ID ;
    public final CGELParser.aType_return aType() throws RecognitionException {
        CGELParser.aType_return retval = new CGELParser.aType_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token ID32=null;

        CommonTree ID32_tree=null;

        try {
            // CGEL.g:47:6: ( ID )
            // CGEL.g:48:9: ID
            {
            root_0 = (CommonTree)adaptor.nil();


            ID32=(Token)match(input,ID,FOLLOW_ID_in_aType311); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            ID32_tree = 
            (CommonTree)adaptor.create(ID32)
            ;
            adaptor.addChild(root_0, ID32_tree);
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
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
    // CGEL.g:51:1: block : '{' ( stmt )* '}' -> ^( BLOCK ( stmt )* ) ;
    public final CGELParser.block_return block() throws RecognitionException {
        CGELParser.block_return retval = new CGELParser.block_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal33=null;
        Token char_literal35=null;
        CGELParser.stmt_return stmt34 =null;


        CommonTree char_literal33_tree=null;
        CommonTree char_literal35_tree=null;
        RewriteRuleTokenStream stream_27=new RewriteRuleTokenStream(adaptor,"token 27");
        RewriteRuleTokenStream stream_28=new RewriteRuleTokenStream(adaptor,"token 28");
        RewriteRuleSubtreeStream stream_stmt=new RewriteRuleSubtreeStream(adaptor,"rule stmt");
        try {
            // CGEL.g:51:6: ( '{' ( stmt )* '}' -> ^( BLOCK ( stmt )* ) )
            // CGEL.g:52:8: '{' ( stmt )* '}'
            {
            char_literal33=(Token)match(input,27,FOLLOW_27_in_block330); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_27.add(char_literal33);


            // CGEL.g:52:12: ( stmt )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==20||(LA5_0 >= 24 && LA5_0 <= 26)) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // CGEL.g:52:13: stmt
            	    {
            	    pushFollow(FOLLOW_stmt_in_block333);
            	    stmt34=stmt();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_stmt.add(stmt34.getTree());

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            char_literal35=(Token)match(input,28,FOLLOW_28_in_block337); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_28.add(char_literal35);


            // AST REWRITE
            // elements: stmt
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 52:24: -> ^( BLOCK ( stmt )* )
            {
                // CGEL.g:52:27: ^( BLOCK ( stmt )* )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(BLOCK, "BLOCK")
                , root_1);

                // CGEL.g:52:35: ( stmt )*
                while ( stream_stmt.hasNext() ) {
                    adaptor.addChild(root_1, stream_stmt.nextTree());

                }
                stream_stmt.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
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
    // CGEL.g:55:1: stmt : ( var_decl | assignment | if_else_stmt | while_stmt );
    public final CGELParser.stmt_return stmt() throws RecognitionException {
        CGELParser.stmt_return retval = new CGELParser.stmt_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CGELParser.var_decl_return var_decl36 =null;

        CGELParser.assignment_return assignment37 =null;

        CGELParser.if_else_stmt_return if_else_stmt38 =null;

        CGELParser.while_stmt_return while_stmt39 =null;



        try {
            // CGEL.g:55:5: ( var_decl | assignment | if_else_stmt | while_stmt )
            int alt6=4;
            switch ( input.LA(1) ) {
            case 25:
                {
                alt6=1;
                }
                break;
            case 20:
                {
                alt6=2;
                }
                break;
            case 24:
                {
                alt6=3;
                }
                break;
            case 26:
                {
                alt6=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;

            }

            switch (alt6) {
                case 1 :
                    // CGEL.g:56:9: var_decl
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_var_decl_in_stmt367);
                    var_decl36=var_decl();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, var_decl36.getTree());

                    }
                    break;
                case 2 :
                    // CGEL.g:56:20: assignment
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_assignment_in_stmt371);
                    assignment37=assignment();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, assignment37.getTree());

                    }
                    break;
                case 3 :
                    // CGEL.g:56:33: if_else_stmt
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_if_else_stmt_in_stmt375);
                    if_else_stmt38=if_else_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, if_else_stmt38.getTree());

                    }
                    break;
                case 4 :
                    // CGEL.g:56:48: while_stmt
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_while_stmt_in_stmt379);
                    while_stmt39=while_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, while_stmt39.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
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
    // CGEL.g:59:1: assignment : '=' ^ '(' ! ID ',' ! expr ')' ! ';' ;
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
            // CGEL.g:59:11: ( '=' ^ '(' ! ID ',' ! expr ')' ! ';' )
            // CGEL.g:60:9: '=' ^ '(' ! ID ',' ! expr ')' ! ';'
            {
            root_0 = (CommonTree)adaptor.nil();


            char_literal40=(Token)match(input,20,FOLLOW_20_in_assignment399); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal40_tree = 
            (CommonTree)adaptor.create(char_literal40)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(char_literal40_tree, root_0);
            }

            char_literal41=(Token)match(input,16,FOLLOW_16_in_assignment402); if (state.failed) return retval;

            ID42=(Token)match(input,ID,FOLLOW_ID_in_assignment405); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            ID42_tree = 
            (CommonTree)adaptor.create(ID42)
            ;
            adaptor.addChild(root_0, ID42_tree);
            }

            char_literal43=(Token)match(input,18,FOLLOW_18_in_assignment407); if (state.failed) return retval;

            pushFollow(FOLLOW_expr_in_assignment410);
            expr44=expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr44.getTree());

            char_literal45=(Token)match(input,17,FOLLOW_17_in_assignment412); if (state.failed) return retval;

            char_literal46=(Token)match(input,19,FOLLOW_19_in_assignment415); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal46_tree = 
            (CommonTree)adaptor.create(char_literal46)
            ;
            adaptor.addChild(root_0, char_literal46_tree);
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
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
    // CGEL.g:62:1: if_else_stmt : 'if' '(' expr ')' b1= block 'else' b2= block -> ^( IF_ELSE expr $b1 $b2) ;
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
        RewriteRuleTokenStream stream_17=new RewriteRuleTokenStream(adaptor,"token 17");
        RewriteRuleTokenStream stream_22=new RewriteRuleTokenStream(adaptor,"token 22");
        RewriteRuleTokenStream stream_24=new RewriteRuleTokenStream(adaptor,"token 24");
        RewriteRuleTokenStream stream_16=new RewriteRuleTokenStream(adaptor,"token 16");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        try {
            // CGEL.g:62:13: ( 'if' '(' expr ')' b1= block 'else' b2= block -> ^( IF_ELSE expr $b1 $b2) )
            // CGEL.g:63:9: 'if' '(' expr ')' b1= block 'else' b2= block
            {
            string_literal47=(Token)match(input,24,FOLLOW_24_in_if_else_stmt434); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_24.add(string_literal47);


            char_literal48=(Token)match(input,16,FOLLOW_16_in_if_else_stmt436); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_16.add(char_literal48);


            pushFollow(FOLLOW_expr_in_if_else_stmt438);
            expr49=expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expr.add(expr49.getTree());

            char_literal50=(Token)match(input,17,FOLLOW_17_in_if_else_stmt440); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_17.add(char_literal50);


            pushFollow(FOLLOW_block_in_if_else_stmt444);
            b1=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_block.add(b1.getTree());

            string_literal51=(Token)match(input,22,FOLLOW_22_in_if_else_stmt446); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_22.add(string_literal51);


            pushFollow(FOLLOW_block_in_if_else_stmt450);
            b2=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_block.add(b2.getTree());

            // AST REWRITE
            // elements: expr, b2, b1
            // token labels: 
            // rule labels: retval, b1, b2
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_b1=new RewriteRuleSubtreeStream(adaptor,"rule b1",b1!=null?b1.tree:null);
            RewriteRuleSubtreeStream stream_b2=new RewriteRuleSubtreeStream(adaptor,"rule b2",b2!=null?b2.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 63:52: -> ^( IF_ELSE expr $b1 $b2)
            {
                // CGEL.g:63:55: ^( IF_ELSE expr $b1 $b2)
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

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
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
    // CGEL.g:65:1: while_stmt : 'while' '(' expr ')' b= block -> ^( WHILE expr $b) ;
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
        RewriteRuleTokenStream stream_17=new RewriteRuleTokenStream(adaptor,"token 17");
        RewriteRuleTokenStream stream_16=new RewriteRuleTokenStream(adaptor,"token 16");
        RewriteRuleTokenStream stream_26=new RewriteRuleTokenStream(adaptor,"token 26");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        try {
            // CGEL.g:65:11: ( 'while' '(' expr ')' b= block -> ^( WHILE expr $b) )
            // CGEL.g:66:9: 'while' '(' expr ')' b= block
            {
            string_literal52=(Token)match(input,26,FOLLOW_26_in_while_stmt483); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_26.add(string_literal52);


            char_literal53=(Token)match(input,16,FOLLOW_16_in_while_stmt485); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_16.add(char_literal53);


            pushFollow(FOLLOW_expr_in_while_stmt487);
            expr54=expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expr.add(expr54.getTree());

            char_literal55=(Token)match(input,17,FOLLOW_17_in_while_stmt489); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_17.add(char_literal55);


            pushFollow(FOLLOW_block_in_while_stmt493);
            b=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_block.add(b.getTree());

            // AST REWRITE
            // elements: b, expr
            // token labels: 
            // rule labels: retval, b
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_b=new RewriteRuleSubtreeStream(adaptor,"rule b",b!=null?b.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 66:38: -> ^( WHILE expr $b)
            {
                // CGEL.g:66:41: ^( WHILE expr $b)
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

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
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


    public static class function_call_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "function_call"
    // CGEL.g:71:1: function_call : (n= ID ) '(' ( expr )? ( ',' expr )* ')' -> ^( FUNCTION_CALL $n ( expr )* ) ;
    public final CGELParser.function_call_return function_call() throws RecognitionException {
        CGELParser.function_call_return retval = new CGELParser.function_call_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token n=null;
        Token char_literal56=null;
        Token char_literal58=null;
        Token char_literal60=null;
        CGELParser.expr_return expr57 =null;

        CGELParser.expr_return expr59 =null;


        CommonTree n_tree=null;
        CommonTree char_literal56_tree=null;
        CommonTree char_literal58_tree=null;
        CommonTree char_literal60_tree=null;
        RewriteRuleTokenStream stream_17=new RewriteRuleTokenStream(adaptor,"token 17");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_18=new RewriteRuleTokenStream(adaptor,"token 18");
        RewriteRuleTokenStream stream_16=new RewriteRuleTokenStream(adaptor,"token 16");
        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        try {
            // CGEL.g:71:14: ( (n= ID ) '(' ( expr )? ( ',' expr )* ')' -> ^( FUNCTION_CALL $n ( expr )* ) )
            // CGEL.g:72:9: (n= ID ) '(' ( expr )? ( ',' expr )* ')'
            {
            // CGEL.g:72:9: (n= ID )
            // CGEL.g:72:10: n= ID
            {
            n=(Token)match(input,ID,FOLLOW_ID_in_function_call530); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(n);


            }


            char_literal56=(Token)match(input,16,FOLLOW_16_in_function_call534); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_16.add(char_literal56);


            // CGEL.g:72:21: ( expr )?
            int alt7=2;
            switch ( input.LA(1) ) {
                case 18:
                    {
                    int LA7_1 = input.LA(2);

                    if ( (synpred11_CGEL()) ) {
                        alt7=1;
                    }
                    }
                    break;
                case 17:
                    {
                    int LA7_2 = input.LA(2);

                    if ( (synpred11_CGEL()) ) {
                        alt7=1;
                    }
                    }
                    break;
                case BINARY_OPERATOR:
                case ID:
                case RELATIONAL_OPERATOR:
                case 21:
                    {
                    alt7=1;
                    }
                    break;
            }

            switch (alt7) {
                case 1 :
                    // CGEL.g:72:21: expr
                    {
                    pushFollow(FOLLOW_expr_in_function_call536);
                    expr57=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr57.getTree());

                    }
                    break;

            }


            // CGEL.g:72:27: ( ',' expr )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==18) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // CGEL.g:72:28: ',' expr
            	    {
            	    char_literal58=(Token)match(input,18,FOLLOW_18_in_function_call540); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_18.add(char_literal58);


            	    pushFollow(FOLLOW_expr_in_function_call542);
            	    expr59=expr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_expr.add(expr59.getTree());

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);


            char_literal60=(Token)match(input,17,FOLLOW_17_in_function_call546); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_17.add(char_literal60);


            // AST REWRITE
            // elements: n, expr
            // token labels: n
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleTokenStream stream_n=new RewriteRuleTokenStream(adaptor,"token n",n);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 72:43: -> ^( FUNCTION_CALL $n ( expr )* )
            {
                // CGEL.g:72:46: ^( FUNCTION_CALL $n ( expr )* )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(FUNCTION_CALL, "FUNCTION_CALL")
                , root_1);

                adaptor.addChild(root_1, stream_n.nextNode());

                // CGEL.g:72:65: ( expr )*
                while ( stream_expr.hasNext() ) {
                    adaptor.addChild(root_1, stream_expr.nextTree());

                }
                stream_expr.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
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
    // $ANTLR end "function_call"


    public static class expr_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "expr"
    // CGEL.g:75:1: expr : (| '@' ^ ID | ID ^| function_call ^| BINARY_OPERATOR ^ '(' ! expr ',' ! expr ')' !| RELATIONAL_OPERATOR ^ '(' ! expr ',' ! expr ')' !);
    public final CGELParser.expr_return expr() throws RecognitionException {
        CGELParser.expr_return retval = new CGELParser.expr_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal61=null;
        Token ID62=null;
        Token ID63=null;
        Token BINARY_OPERATOR65=null;
        Token char_literal66=null;
        Token char_literal68=null;
        Token char_literal70=null;
        Token RELATIONAL_OPERATOR71=null;
        Token char_literal72=null;
        Token char_literal74=null;
        Token char_literal76=null;
        CGELParser.function_call_return function_call64 =null;

        CGELParser.expr_return expr67 =null;

        CGELParser.expr_return expr69 =null;

        CGELParser.expr_return expr73 =null;

        CGELParser.expr_return expr75 =null;


        CommonTree char_literal61_tree=null;
        CommonTree ID62_tree=null;
        CommonTree ID63_tree=null;
        CommonTree BINARY_OPERATOR65_tree=null;
        CommonTree char_literal66_tree=null;
        CommonTree char_literal68_tree=null;
        CommonTree char_literal70_tree=null;
        CommonTree RELATIONAL_OPERATOR71_tree=null;
        CommonTree char_literal72_tree=null;
        CommonTree char_literal74_tree=null;
        CommonTree char_literal76_tree=null;

        try {
            // CGEL.g:75:5: (| '@' ^ ID | ID ^| function_call ^| BINARY_OPERATOR ^ '(' ! expr ',' ! expr ')' !| RELATIONAL_OPERATOR ^ '(' ! expr ',' ! expr ')' !)
            int alt9=6;
            switch ( input.LA(1) ) {
            case EOF:
            case 17:
            case 18:
                {
                alt9=1;
                }
                break;
            case 21:
                {
                alt9=2;
                }
                break;
            case ID:
                {
                int LA9_3 = input.LA(2);

                if ( (LA9_3==16) ) {
                    alt9=4;
                }
                else if ( (LA9_3==EOF||(LA9_3 >= 17 && LA9_3 <= 18)) ) {
                    alt9=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 9, 3, input);

                    throw nvae;

                }
                }
                break;
            case BINARY_OPERATOR:
                {
                alt9=5;
                }
                break;
            case RELATIONAL_OPERATOR:
                {
                alt9=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;

            }

            switch (alt9) {
                case 1 :
                    // CGEL.g:76:5: 
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    }
                    break;
                case 2 :
                    // CGEL.g:76:7: '@' ^ ID
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    char_literal61=(Token)match(input,21,FOLLOW_21_in_expr576); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal61_tree = 
                    (CommonTree)adaptor.create(char_literal61)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(char_literal61_tree, root_0);
                    }

                    ID62=(Token)match(input,ID,FOLLOW_ID_in_expr579); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ID62_tree = 
                    (CommonTree)adaptor.create(ID62)
                    ;
                    adaptor.addChild(root_0, ID62_tree);
                    }

                    }
                    break;
                case 3 :
                    // CGEL.g:77:7: ID ^
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    ID63=(Token)match(input,ID,FOLLOW_ID_in_expr589); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ID63_tree = 
                    (CommonTree)adaptor.create(ID63)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(ID63_tree, root_0);
                    }

                    }
                    break;
                case 4 :
                    // CGEL.g:78:7: function_call ^
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_function_call_in_expr600);
                    function_call64=function_call();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) root_0 = (CommonTree)adaptor.becomeRoot(function_call64.getTree(), root_0);

                    }
                    break;
                case 5 :
                    // CGEL.g:79:7: BINARY_OPERATOR ^ '(' ! expr ',' ! expr ')' !
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    BINARY_OPERATOR65=(Token)match(input,BINARY_OPERATOR,FOLLOW_BINARY_OPERATOR_in_expr610); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    BINARY_OPERATOR65_tree = 
                    (CommonTree)adaptor.create(BINARY_OPERATOR65)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(BINARY_OPERATOR65_tree, root_0);
                    }

                    char_literal66=(Token)match(input,16,FOLLOW_16_in_expr613); if (state.failed) return retval;

                    pushFollow(FOLLOW_expr_in_expr617);
                    expr67=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr67.getTree());

                    char_literal68=(Token)match(input,18,FOLLOW_18_in_expr619); if (state.failed) return retval;

                    pushFollow(FOLLOW_expr_in_expr622);
                    expr69=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr69.getTree());

                    char_literal70=(Token)match(input,17,FOLLOW_17_in_expr624); if (state.failed) return retval;

                    }
                    break;
                case 6 :
                    // CGEL.g:80:7: RELATIONAL_OPERATOR ^ '(' ! expr ',' ! expr ')' !
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    RELATIONAL_OPERATOR71=(Token)match(input,RELATIONAL_OPERATOR,FOLLOW_RELATIONAL_OPERATOR_in_expr633); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RELATIONAL_OPERATOR71_tree = 
                    (CommonTree)adaptor.create(RELATIONAL_OPERATOR71)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(RELATIONAL_OPERATOR71_tree, root_0);
                    }

                    char_literal72=(Token)match(input,16,FOLLOW_16_in_expr636); if (state.failed) return retval;

                    pushFollow(FOLLOW_expr_in_expr639);
                    expr73=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr73.getTree());

                    char_literal74=(Token)match(input,18,FOLLOW_18_in_expr641); if (state.failed) return retval;

                    pushFollow(FOLLOW_expr_in_expr644);
                    expr75=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr75.getTree());

                    char_literal76=(Token)match(input,17,FOLLOW_17_in_expr646); if (state.failed) return retval;

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
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

    // $ANTLR start synpred11_CGEL
    public final void synpred11_CGEL_fragment() throws RecognitionException {
        // CGEL.g:72:21: ( expr )
        // CGEL.g:72:21: expr
        {
        pushFollow(FOLLOW_expr_in_synpred11_CGEL536);
        expr();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred11_CGEL

    // Delegated rules

    public final boolean synpred11_CGEL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred11_CGEL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


 

    public static final BitSet FOLLOW_23_in_function108 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_function_prototype_in_function111 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_block_in_function114 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_function_prototype129 = new BitSet(new long[]{0x0000000000050000L});
    public static final BitSet FOLLOW_function_input_in_function_prototype132 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_function_prototype134 = new BitSet(new long[]{0x0000000000050000L});
    public static final BitSet FOLLOW_function_output_in_function_prototype137 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_function_prototype139 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_ID_in_function_prototype142 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_function_prototype146 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_function_output164 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_function_output166 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_function_output180 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_var_decl_in_function_output182 = new BitSet(new long[]{0x0000000000060000L});
    public static final BitSet FOLLOW_18_in_function_output185 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_var_decl_in_function_output188 = new BitSet(new long[]{0x0000000000060000L});
    public static final BitSet FOLLOW_17_in_function_output193 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_function_input219 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_function_input221 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_function_input235 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_var_decl_in_function_input237 = new BitSet(new long[]{0x0000000000060000L});
    public static final BitSet FOLLOW_18_in_function_input240 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_var_decl_in_function_input243 = new BitSet(new long[]{0x0000000000060000L});
    public static final BitSet FOLLOW_17_in_function_input248 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_var_decl275 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_var_decl278 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_aType_in_var_decl281 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_var_decl283 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_ID_in_var_decl287 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_var_decl289 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_var_decl292 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_aType311 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_block330 = new BitSet(new long[]{0x0000000017100000L});
    public static final BitSet FOLLOW_stmt_in_block333 = new BitSet(new long[]{0x0000000017100000L});
    public static final BitSet FOLLOW_28_in_block337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_var_decl_in_stmt367 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_in_stmt371 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_if_else_stmt_in_stmt375 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_while_stmt_in_stmt379 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_assignment399 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_assignment402 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_ID_in_assignment405 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_assignment407 = new BitSet(new long[]{0x0000000000221210L});
    public static final BitSet FOLLOW_expr_in_assignment410 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_assignment412 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_assignment415 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_if_else_stmt434 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_if_else_stmt436 = new BitSet(new long[]{0x0000000000221210L});
    public static final BitSet FOLLOW_expr_in_if_else_stmt438 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_if_else_stmt440 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_block_in_if_else_stmt444 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_if_else_stmt446 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_block_in_if_else_stmt450 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_while_stmt483 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_while_stmt485 = new BitSet(new long[]{0x0000000000221210L});
    public static final BitSet FOLLOW_expr_in_while_stmt487 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_while_stmt489 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_block_in_while_stmt493 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_function_call530 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_function_call534 = new BitSet(new long[]{0x0000000000261210L});
    public static final BitSet FOLLOW_expr_in_function_call536 = new BitSet(new long[]{0x0000000000060000L});
    public static final BitSet FOLLOW_18_in_function_call540 = new BitSet(new long[]{0x0000000000261210L});
    public static final BitSet FOLLOW_expr_in_function_call542 = new BitSet(new long[]{0x0000000000060000L});
    public static final BitSet FOLLOW_17_in_function_call546 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_expr576 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_ID_in_expr579 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_expr589 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_function_call_in_expr600 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BINARY_OPERATOR_in_expr610 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_expr613 = new BitSet(new long[]{0x0000000000241210L});
    public static final BitSet FOLLOW_expr_in_expr617 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_expr619 = new BitSet(new long[]{0x0000000000221210L});
    public static final BitSet FOLLOW_expr_in_expr622 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_expr624 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RELATIONAL_OPERATOR_in_expr633 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_expr636 = new BitSet(new long[]{0x0000000000241210L});
    public static final BitSet FOLLOW_expr_in_expr639 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_expr641 = new BitSet(new long[]{0x0000000000221210L});
    public static final BitSet FOLLOW_expr_in_expr644 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_expr646 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_synpred11_CGEL536 = new BitSet(new long[]{0x0000000000000002L});

}