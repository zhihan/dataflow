// $ANTLR 3.5 CGEL.g 2013-01-12 14:01:57

package my.ir;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.tree.*;


@SuppressWarnings("all")
public class CGELParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "BINARY_OPERATOR", "BLOCK", "DIVIDE", 
		"FUNCTION_CALL", "FUNCTION_INPUT", "FUNCTION_OUTPUT", "ID", "IF_ELSE", 
		"LETTER", "MINUS", "MULTIPLY", "PLUS", "REAL_NUMBER", "RELATIONAL_OPERATOR", 
		"SL_COMMENTS", "UNARY_OPERATOR", "WHILE", "WS", "'('", "')'", "','", "';'", 
		"'='", "'@'", "'const'", "'else'", "'false'", "'function'", "'if'", "'true'", 
		"'var'", "'while'", "'{'", "'}'"
	};
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
	@Override public String[] getTokenNames() { return CGELParser.tokenNames; }
	@Override public String getGrammarFileName() { return "CGEL.g"; }


	public static class function_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "function"
	// CGEL.g:32:1: function : 'function' ! function_prototype ^ block ;
	public final CGELParser.function_return function() throws RecognitionException {
		CGELParser.function_return retval = new CGELParser.function_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal1=null;
		ParserRuleReturnScope function_prototype2 =null;
		ParserRuleReturnScope block3 =null;

		CommonTree string_literal1_tree=null;

		try {
			// CGEL.g:32:9: ( 'function' ! function_prototype ^ block )
			// CGEL.g:33:9: 'function' ! function_prototype ^ block
			{
			root_0 = (CommonTree)adaptor.nil();


			string_literal1=(Token)match(input,31,FOLLOW_31_in_function118); if (state.failed) return retval;
			pushFollow(FOLLOW_function_prototype_in_function121);
			function_prototype2=function_prototype();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) root_0 = (CommonTree)adaptor.becomeRoot(function_prototype2.getTree(), root_0);
			pushFollow(FOLLOW_block_in_function124);
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
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "function_prototype"
	// CGEL.g:35:1: function_prototype : '(' ! function_input ',' ! function_output ',' ! ID ^ ')' !;
	public final CGELParser.function_prototype_return function_prototype() throws RecognitionException {
		CGELParser.function_prototype_return retval = new CGELParser.function_prototype_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token char_literal4=null;
		Token char_literal6=null;
		Token char_literal8=null;
		Token ID9=null;
		Token char_literal10=null;
		ParserRuleReturnScope function_input5 =null;
		ParserRuleReturnScope function_output7 =null;

		CommonTree char_literal4_tree=null;
		CommonTree char_literal6_tree=null;
		CommonTree char_literal8_tree=null;
		CommonTree ID9_tree=null;
		CommonTree char_literal10_tree=null;

		try {
			// CGEL.g:35:19: ( '(' ! function_input ',' ! function_output ',' ! ID ^ ')' !)
			// CGEL.g:36:5: '(' ! function_input ',' ! function_output ',' ! ID ^ ')' !
			{
			root_0 = (CommonTree)adaptor.nil();


			char_literal4=(Token)match(input,22,FOLLOW_22_in_function_prototype139); if (state.failed) return retval;
			pushFollow(FOLLOW_function_input_in_function_prototype142);
			function_input5=function_input();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, function_input5.getTree());

			char_literal6=(Token)match(input,24,FOLLOW_24_in_function_prototype144); if (state.failed) return retval;
			pushFollow(FOLLOW_function_output_in_function_prototype147);
			function_output7=function_output();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, function_output7.getTree());

			char_literal8=(Token)match(input,24,FOLLOW_24_in_function_prototype149); if (state.failed) return retval;
			ID9=(Token)match(input,ID,FOLLOW_ID_in_function_prototype152); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			ID9_tree = (CommonTree)adaptor.create(ID9);
			root_0 = (CommonTree)adaptor.becomeRoot(ID9_tree, root_0);
			}

			char_literal10=(Token)match(input,23,FOLLOW_23_in_function_prototype156); if (state.failed) return retval;
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
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "function_output"
	// CGEL.g:38:1: function_output : ( '(' ')' -> ^( FUNCTION_OUTPUT ) | '(' var_decl ( ',' var_decl )* ')' -> ^( FUNCTION_OUTPUT ( var_decl )+ ) );
	public final CGELParser.function_output_return function_output() throws RecognitionException {
		CGELParser.function_output_return retval = new CGELParser.function_output_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token char_literal11=null;
		Token char_literal12=null;
		Token char_literal13=null;
		Token char_literal15=null;
		Token char_literal17=null;
		ParserRuleReturnScope var_decl14 =null;
		ParserRuleReturnScope var_decl16 =null;

		CommonTree char_literal11_tree=null;
		CommonTree char_literal12_tree=null;
		CommonTree char_literal13_tree=null;
		CommonTree char_literal15_tree=null;
		CommonTree char_literal17_tree=null;
		RewriteRuleTokenStream stream_22=new RewriteRuleTokenStream(adaptor,"token 22");
		RewriteRuleTokenStream stream_23=new RewriteRuleTokenStream(adaptor,"token 23");
		RewriteRuleTokenStream stream_24=new RewriteRuleTokenStream(adaptor,"token 24");
		RewriteRuleSubtreeStream stream_var_decl=new RewriteRuleSubtreeStream(adaptor,"rule var_decl");

		try {
			// CGEL.g:38:16: ( '(' ')' -> ^( FUNCTION_OUTPUT ) | '(' var_decl ( ',' var_decl )* ')' -> ^( FUNCTION_OUTPUT ( var_decl )+ ) )
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( (LA2_0==22) ) {
				int LA2_1 = input.LA(2);
				if ( (LA2_1==23) ) {
					alt2=1;
				}
				else if ( (LA2_1==34) ) {
					alt2=2;
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 2, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
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
					// CGEL.g:39:6: '(' ')'
					{
					char_literal11=(Token)match(input,22,FOLLOW_22_in_function_output173); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_22.add(char_literal11);

					char_literal12=(Token)match(input,23,FOLLOW_23_in_function_output175); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_23.add(char_literal12);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 39:14: -> ^( FUNCTION_OUTPUT )
					{
						// CGEL.g:39:17: ^( FUNCTION_OUTPUT )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(FUNCTION_OUTPUT, "FUNCTION_OUTPUT"), root_1);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// CGEL.g:40:7: '(' var_decl ( ',' var_decl )* ')'
					{
					char_literal13=(Token)match(input,22,FOLLOW_22_in_function_output189); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_22.add(char_literal13);

					pushFollow(FOLLOW_var_decl_in_function_output191);
					var_decl14=var_decl();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_var_decl.add(var_decl14.getTree());
					// CGEL.g:40:20: ( ',' var_decl )*
					loop1:
					while (true) {
						int alt1=2;
						int LA1_0 = input.LA(1);
						if ( (LA1_0==24) ) {
							alt1=1;
						}

						switch (alt1) {
						case 1 :
							// CGEL.g:40:21: ',' var_decl
							{
							char_literal15=(Token)match(input,24,FOLLOW_24_in_function_output194); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_24.add(char_literal15);

							pushFollow(FOLLOW_var_decl_in_function_output197);
							var_decl16=var_decl();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_var_decl.add(var_decl16.getTree());
							}
							break;

						default :
							break loop1;
						}
					}

					char_literal17=(Token)match(input,23,FOLLOW_23_in_function_output202); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_23.add(char_literal17);

					// AST REWRITE
					// elements: var_decl
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 40:42: -> ^( FUNCTION_OUTPUT ( var_decl )+ )
					{
						// CGEL.g:40:45: ^( FUNCTION_OUTPUT ( var_decl )+ )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(FUNCTION_OUTPUT, "FUNCTION_OUTPUT"), root_1);
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
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "function_input"
	// CGEL.g:42:1: function_input : ( '(' ')' -> ^( FUNCTION_INPUT ) | '(' var_decl ( ',' var_decl )* ')' -> ^( FUNCTION_INPUT ( var_decl )+ ) );
	public final CGELParser.function_input_return function_input() throws RecognitionException {
		CGELParser.function_input_return retval = new CGELParser.function_input_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token char_literal18=null;
		Token char_literal19=null;
		Token char_literal20=null;
		Token char_literal22=null;
		Token char_literal24=null;
		ParserRuleReturnScope var_decl21 =null;
		ParserRuleReturnScope var_decl23 =null;

		CommonTree char_literal18_tree=null;
		CommonTree char_literal19_tree=null;
		CommonTree char_literal20_tree=null;
		CommonTree char_literal22_tree=null;
		CommonTree char_literal24_tree=null;
		RewriteRuleTokenStream stream_22=new RewriteRuleTokenStream(adaptor,"token 22");
		RewriteRuleTokenStream stream_23=new RewriteRuleTokenStream(adaptor,"token 23");
		RewriteRuleTokenStream stream_24=new RewriteRuleTokenStream(adaptor,"token 24");
		RewriteRuleSubtreeStream stream_var_decl=new RewriteRuleSubtreeStream(adaptor,"rule var_decl");

		try {
			// CGEL.g:42:15: ( '(' ')' -> ^( FUNCTION_INPUT ) | '(' var_decl ( ',' var_decl )* ')' -> ^( FUNCTION_INPUT ( var_decl )+ ) )
			int alt4=2;
			int LA4_0 = input.LA(1);
			if ( (LA4_0==22) ) {
				int LA4_1 = input.LA(2);
				if ( (LA4_1==23) ) {
					alt4=1;
				}
				else if ( (LA4_1==34) ) {
					alt4=2;
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 4, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
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
					// CGEL.g:43:6: '(' ')'
					{
					char_literal18=(Token)match(input,22,FOLLOW_22_in_function_input227); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_22.add(char_literal18);

					char_literal19=(Token)match(input,23,FOLLOW_23_in_function_input229); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_23.add(char_literal19);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 43:14: -> ^( FUNCTION_INPUT )
					{
						// CGEL.g:43:17: ^( FUNCTION_INPUT )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(FUNCTION_INPUT, "FUNCTION_INPUT"), root_1);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// CGEL.g:44:7: '(' var_decl ( ',' var_decl )* ')'
					{
					char_literal20=(Token)match(input,22,FOLLOW_22_in_function_input243); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_22.add(char_literal20);

					pushFollow(FOLLOW_var_decl_in_function_input245);
					var_decl21=var_decl();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_var_decl.add(var_decl21.getTree());
					// CGEL.g:44:20: ( ',' var_decl )*
					loop3:
					while (true) {
						int alt3=2;
						int LA3_0 = input.LA(1);
						if ( (LA3_0==24) ) {
							alt3=1;
						}

						switch (alt3) {
						case 1 :
							// CGEL.g:44:21: ',' var_decl
							{
							char_literal22=(Token)match(input,24,FOLLOW_24_in_function_input248); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_24.add(char_literal22);

							pushFollow(FOLLOW_var_decl_in_function_input251);
							var_decl23=var_decl();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_var_decl.add(var_decl23.getTree());
							}
							break;

						default :
							break loop3;
						}
					}

					char_literal24=(Token)match(input,23,FOLLOW_23_in_function_input256); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_23.add(char_literal24);

					// AST REWRITE
					// elements: var_decl
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 44:42: -> ^( FUNCTION_INPUT ( var_decl )+ )
					{
						// CGEL.g:44:45: ^( FUNCTION_INPUT ( var_decl )+ )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(FUNCTION_INPUT, "FUNCTION_INPUT"), root_1);
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
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "var_decl"
	// CGEL.g:46:1: var_decl : 'var' ^ '(' ! aType ',' ! ID ')' ! ';' !;
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
		ParserRuleReturnScope aType27 =null;

		CommonTree string_literal25_tree=null;
		CommonTree char_literal26_tree=null;
		CommonTree char_literal28_tree=null;
		CommonTree ID29_tree=null;
		CommonTree char_literal30_tree=null;
		CommonTree char_literal31_tree=null;

		try {
			// CGEL.g:46:9: ( 'var' ^ '(' ! aType ',' ! ID ')' ! ';' !)
			// CGEL.g:47:8: 'var' ^ '(' ! aType ',' ! ID ')' ! ';' !
			{
			root_0 = (CommonTree)adaptor.nil();


			string_literal25=(Token)match(input,34,FOLLOW_34_in_var_decl283); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			string_literal25_tree = (CommonTree)adaptor.create(string_literal25);
			root_0 = (CommonTree)adaptor.becomeRoot(string_literal25_tree, root_0);
			}

			char_literal26=(Token)match(input,22,FOLLOW_22_in_var_decl286); if (state.failed) return retval;
			pushFollow(FOLLOW_aType_in_var_decl289);
			aType27=aType();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, aType27.getTree());

			char_literal28=(Token)match(input,24,FOLLOW_24_in_var_decl291); if (state.failed) return retval;
			ID29=(Token)match(input,ID,FOLLOW_ID_in_var_decl295); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			ID29_tree = (CommonTree)adaptor.create(ID29);
			adaptor.addChild(root_0, ID29_tree);
			}

			char_literal30=(Token)match(input,23,FOLLOW_23_in_var_decl297); if (state.failed) return retval;
			char_literal31=(Token)match(input,25,FOLLOW_25_in_var_decl300); if (state.failed) return retval;
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
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "aType"
	// CGEL.g:49:1: aType : ID ;
	public final CGELParser.aType_return aType() throws RecognitionException {
		CGELParser.aType_return retval = new CGELParser.aType_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token ID32=null;

		CommonTree ID32_tree=null;

		try {
			// CGEL.g:49:6: ( ID )
			// CGEL.g:50:9: ID
			{
			root_0 = (CommonTree)adaptor.nil();


			ID32=(Token)match(input,ID,FOLLOW_ID_in_aType320); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			ID32_tree = (CommonTree)adaptor.create(ID32);
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
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "block"
	// CGEL.g:53:1: block : '{' ( stmt )* '}' -> ^( BLOCK ( stmt )* ) ;
	public final CGELParser.block_return block() throws RecognitionException {
		CGELParser.block_return retval = new CGELParser.block_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token char_literal33=null;
		Token char_literal35=null;
		ParserRuleReturnScope stmt34 =null;

		CommonTree char_literal33_tree=null;
		CommonTree char_literal35_tree=null;
		RewriteRuleTokenStream stream_36=new RewriteRuleTokenStream(adaptor,"token 36");
		RewriteRuleTokenStream stream_37=new RewriteRuleTokenStream(adaptor,"token 37");
		RewriteRuleSubtreeStream stream_stmt=new RewriteRuleSubtreeStream(adaptor,"rule stmt");

		try {
			// CGEL.g:53:6: ( '{' ( stmt )* '}' -> ^( BLOCK ( stmt )* ) )
			// CGEL.g:54:8: '{' ( stmt )* '}'
			{
			char_literal33=(Token)match(input,36,FOLLOW_36_in_block339); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_36.add(char_literal33);

			// CGEL.g:54:12: ( stmt )*
			loop5:
			while (true) {
				int alt5=2;
				int LA5_0 = input.LA(1);
				if ( (LA5_0==ID||LA5_0==26||LA5_0==32||(LA5_0 >= 34 && LA5_0 <= 35)) ) {
					alt5=1;
				}

				switch (alt5) {
				case 1 :
					// CGEL.g:54:13: stmt
					{
					pushFollow(FOLLOW_stmt_in_block342);
					stmt34=stmt();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_stmt.add(stmt34.getTree());
					}
					break;

				default :
					break loop5;
				}
			}

			char_literal35=(Token)match(input,37,FOLLOW_37_in_block346); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_37.add(char_literal35);

			// AST REWRITE
			// elements: stmt
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 54:24: -> ^( BLOCK ( stmt )* )
			{
				// CGEL.g:54:27: ^( BLOCK ( stmt )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(BLOCK, "BLOCK"), root_1);
				// CGEL.g:54:35: ( stmt )*
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
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "stmt"
	// CGEL.g:57:1: stmt : ( var_decl | assignment | if_else_stmt | while_stmt | call_stmt );
	public final CGELParser.stmt_return stmt() throws RecognitionException {
		CGELParser.stmt_return retval = new CGELParser.stmt_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope var_decl36 =null;
		ParserRuleReturnScope assignment37 =null;
		ParserRuleReturnScope if_else_stmt38 =null;
		ParserRuleReturnScope while_stmt39 =null;
		ParserRuleReturnScope call_stmt40 =null;


		try {
			// CGEL.g:57:5: ( var_decl | assignment | if_else_stmt | while_stmt | call_stmt )
			int alt6=5;
			switch ( input.LA(1) ) {
			case 34:
				{
				alt6=1;
				}
				break;
			case 26:
				{
				alt6=2;
				}
				break;
			case 32:
				{
				alt6=3;
				}
				break;
			case 35:
				{
				alt6=4;
				}
				break;
			case ID:
				{
				alt6=5;
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
					// CGEL.g:58:9: var_decl
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_var_decl_in_stmt376);
					var_decl36=var_decl();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, var_decl36.getTree());

					}
					break;
				case 2 :
					// CGEL.g:58:20: assignment
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_assignment_in_stmt380);
					assignment37=assignment();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, assignment37.getTree());

					}
					break;
				case 3 :
					// CGEL.g:58:33: if_else_stmt
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_if_else_stmt_in_stmt384);
					if_else_stmt38=if_else_stmt();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, if_else_stmt38.getTree());

					}
					break;
				case 4 :
					// CGEL.g:58:48: while_stmt
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_while_stmt_in_stmt388);
					while_stmt39=while_stmt();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, while_stmt39.getTree());

					}
					break;
				case 5 :
					// CGEL.g:58:61: call_stmt
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_call_stmt_in_stmt392);
					call_stmt40=call_stmt();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, call_stmt40.getTree());

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
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "assignment"
	// CGEL.g:61:1: assignment : '=' ^ '(' ! ID ',' ! expr ')' ! ';' !;
	public final CGELParser.assignment_return assignment() throws RecognitionException {
		CGELParser.assignment_return retval = new CGELParser.assignment_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token char_literal41=null;
		Token char_literal42=null;
		Token ID43=null;
		Token char_literal44=null;
		Token char_literal46=null;
		Token char_literal47=null;
		ParserRuleReturnScope expr45 =null;

		CommonTree char_literal41_tree=null;
		CommonTree char_literal42_tree=null;
		CommonTree ID43_tree=null;
		CommonTree char_literal44_tree=null;
		CommonTree char_literal46_tree=null;
		CommonTree char_literal47_tree=null;

		try {
			// CGEL.g:61:11: ( '=' ^ '(' ! ID ',' ! expr ')' ! ';' !)
			// CGEL.g:62:9: '=' ^ '(' ! ID ',' ! expr ')' ! ';' !
			{
			root_0 = (CommonTree)adaptor.nil();


			char_literal41=(Token)match(input,26,FOLLOW_26_in_assignment412); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			char_literal41_tree = (CommonTree)adaptor.create(char_literal41);
			root_0 = (CommonTree)adaptor.becomeRoot(char_literal41_tree, root_0);
			}

			char_literal42=(Token)match(input,22,FOLLOW_22_in_assignment415); if (state.failed) return retval;
			ID43=(Token)match(input,ID,FOLLOW_ID_in_assignment418); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			ID43_tree = (CommonTree)adaptor.create(ID43);
			adaptor.addChild(root_0, ID43_tree);
			}

			char_literal44=(Token)match(input,24,FOLLOW_24_in_assignment420); if (state.failed) return retval;
			pushFollow(FOLLOW_expr_in_assignment423);
			expr45=expr();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, expr45.getTree());

			char_literal46=(Token)match(input,23,FOLLOW_23_in_assignment425); if (state.failed) return retval;
			char_literal47=(Token)match(input,25,FOLLOW_25_in_assignment428); if (state.failed) return retval;
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
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "if_else_stmt"
	// CGEL.g:64:1: if_else_stmt : 'if' '(' expr ')' b1= block 'else' b2= block -> ^( IF_ELSE expr $b1 $b2) ;
	public final CGELParser.if_else_stmt_return if_else_stmt() throws RecognitionException {
		CGELParser.if_else_stmt_return retval = new CGELParser.if_else_stmt_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal48=null;
		Token char_literal49=null;
		Token char_literal51=null;
		Token string_literal52=null;
		ParserRuleReturnScope b1 =null;
		ParserRuleReturnScope b2 =null;
		ParserRuleReturnScope expr50 =null;

		CommonTree string_literal48_tree=null;
		CommonTree char_literal49_tree=null;
		CommonTree char_literal51_tree=null;
		CommonTree string_literal52_tree=null;
		RewriteRuleTokenStream stream_32=new RewriteRuleTokenStream(adaptor,"token 32");
		RewriteRuleTokenStream stream_22=new RewriteRuleTokenStream(adaptor,"token 22");
		RewriteRuleTokenStream stream_23=new RewriteRuleTokenStream(adaptor,"token 23");
		RewriteRuleTokenStream stream_29=new RewriteRuleTokenStream(adaptor,"token 29");
		RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
		RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");

		try {
			// CGEL.g:64:13: ( 'if' '(' expr ')' b1= block 'else' b2= block -> ^( IF_ELSE expr $b1 $b2) )
			// CGEL.g:65:9: 'if' '(' expr ')' b1= block 'else' b2= block
			{
			string_literal48=(Token)match(input,32,FOLLOW_32_in_if_else_stmt448); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_32.add(string_literal48);

			char_literal49=(Token)match(input,22,FOLLOW_22_in_if_else_stmt450); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_22.add(char_literal49);

			pushFollow(FOLLOW_expr_in_if_else_stmt452);
			expr50=expr();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_expr.add(expr50.getTree());
			char_literal51=(Token)match(input,23,FOLLOW_23_in_if_else_stmt454); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_23.add(char_literal51);

			pushFollow(FOLLOW_block_in_if_else_stmt458);
			b1=block();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_block.add(b1.getTree());
			string_literal52=(Token)match(input,29,FOLLOW_29_in_if_else_stmt460); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_29.add(string_literal52);

			pushFollow(FOLLOW_block_in_if_else_stmt464);
			b2=block();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_block.add(b2.getTree());
			// AST REWRITE
			// elements: b2, expr, b1
			// token labels: 
			// rule labels: retval, b1, b2
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
			RewriteRuleSubtreeStream stream_b1=new RewriteRuleSubtreeStream(adaptor,"rule b1",b1!=null?b1.getTree():null);
			RewriteRuleSubtreeStream stream_b2=new RewriteRuleSubtreeStream(adaptor,"rule b2",b2!=null?b2.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 65:52: -> ^( IF_ELSE expr $b1 $b2)
			{
				// CGEL.g:65:55: ^( IF_ELSE expr $b1 $b2)
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(IF_ELSE, "IF_ELSE"), root_1);
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
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "while_stmt"
	// CGEL.g:67:1: while_stmt : 'while' '(' expr ')' b= block -> ^( WHILE expr $b) ;
	public final CGELParser.while_stmt_return while_stmt() throws RecognitionException {
		CGELParser.while_stmt_return retval = new CGELParser.while_stmt_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal53=null;
		Token char_literal54=null;
		Token char_literal56=null;
		ParserRuleReturnScope b =null;
		ParserRuleReturnScope expr55 =null;

		CommonTree string_literal53_tree=null;
		CommonTree char_literal54_tree=null;
		CommonTree char_literal56_tree=null;
		RewriteRuleTokenStream stream_35=new RewriteRuleTokenStream(adaptor,"token 35");
		RewriteRuleTokenStream stream_22=new RewriteRuleTokenStream(adaptor,"token 22");
		RewriteRuleTokenStream stream_23=new RewriteRuleTokenStream(adaptor,"token 23");
		RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
		RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");

		try {
			// CGEL.g:67:11: ( 'while' '(' expr ')' b= block -> ^( WHILE expr $b) )
			// CGEL.g:68:9: 'while' '(' expr ')' b= block
			{
			string_literal53=(Token)match(input,35,FOLLOW_35_in_while_stmt497); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_35.add(string_literal53);

			char_literal54=(Token)match(input,22,FOLLOW_22_in_while_stmt499); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_22.add(char_literal54);

			pushFollow(FOLLOW_expr_in_while_stmt501);
			expr55=expr();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_expr.add(expr55.getTree());
			char_literal56=(Token)match(input,23,FOLLOW_23_in_while_stmt503); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_23.add(char_literal56);

			pushFollow(FOLLOW_block_in_while_stmt507);
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
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
			RewriteRuleSubtreeStream stream_b=new RewriteRuleSubtreeStream(adaptor,"rule b",b!=null?b.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 68:38: -> ^( WHILE expr $b)
			{
				// CGEL.g:68:41: ^( WHILE expr $b)
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(WHILE, "WHILE"), root_1);
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


	public static class call_stmt_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "call_stmt"
	// CGEL.g:71:1: call_stmt : function_call ^ ';' !;
	public final CGELParser.call_stmt_return call_stmt() throws RecognitionException {
		CGELParser.call_stmt_return retval = new CGELParser.call_stmt_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token char_literal58=null;
		ParserRuleReturnScope function_call57 =null;

		CommonTree char_literal58_tree=null;

		try {
			// CGEL.g:71:10: ( function_call ^ ';' !)
			// CGEL.g:72:9: function_call ^ ';' !
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_function_call_in_call_stmt539);
			function_call57=function_call();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) root_0 = (CommonTree)adaptor.becomeRoot(function_call57.getTree(), root_0);
			char_literal58=(Token)match(input,25,FOLLOW_25_in_call_stmt542); if (state.failed) return retval;
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
	// $ANTLR end "call_stmt"


	public static class function_call_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "function_call"
	// CGEL.g:75:1: function_call : n= ID '(' expr ( ',' expr )* ')' -> ^( FUNCTION_CALL $n ( expr )+ ) ;
	public final CGELParser.function_call_return function_call() throws RecognitionException {
		CGELParser.function_call_return retval = new CGELParser.function_call_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token n=null;
		Token char_literal59=null;
		Token char_literal61=null;
		Token char_literal63=null;
		ParserRuleReturnScope expr60 =null;
		ParserRuleReturnScope expr62 =null;

		CommonTree n_tree=null;
		CommonTree char_literal59_tree=null;
		CommonTree char_literal61_tree=null;
		CommonTree char_literal63_tree=null;
		RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
		RewriteRuleTokenStream stream_22=new RewriteRuleTokenStream(adaptor,"token 22");
		RewriteRuleTokenStream stream_23=new RewriteRuleTokenStream(adaptor,"token 23");
		RewriteRuleTokenStream stream_24=new RewriteRuleTokenStream(adaptor,"token 24");
		RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");

		try {
			// CGEL.g:75:14: (n= ID '(' expr ( ',' expr )* ')' -> ^( FUNCTION_CALL $n ( expr )+ ) )
			// CGEL.g:76:9: n= ID '(' expr ( ',' expr )* ')'
			{
			n=(Token)match(input,ID,FOLLOW_ID_in_function_call565); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_ID.add(n);

			char_literal59=(Token)match(input,22,FOLLOW_22_in_function_call567); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_22.add(char_literal59);

			pushFollow(FOLLOW_expr_in_function_call569);
			expr60=expr();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_expr.add(expr60.getTree());
			// CGEL.g:76:23: ( ',' expr )*
			loop7:
			while (true) {
				int alt7=2;
				int LA7_0 = input.LA(1);
				if ( (LA7_0==24) ) {
					alt7=1;
				}

				switch (alt7) {
				case 1 :
					// CGEL.g:76:24: ',' expr
					{
					char_literal61=(Token)match(input,24,FOLLOW_24_in_function_call572); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_24.add(char_literal61);

					pushFollow(FOLLOW_expr_in_function_call574);
					expr62=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_expr.add(expr62.getTree());
					}
					break;

				default :
					break loop7;
				}
			}

			char_literal63=(Token)match(input,23,FOLLOW_23_in_function_call578); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_23.add(char_literal63);

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
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 76:39: -> ^( FUNCTION_CALL $n ( expr )+ )
			{
				// CGEL.g:76:42: ^( FUNCTION_CALL $n ( expr )+ )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(FUNCTION_CALL, "FUNCTION_CALL"), root_1);
				adaptor.addChild(root_1, stream_n.nextNode());
				if ( !(stream_expr.hasNext()) ) {
					throw new RewriteEarlyExitException();
				}
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
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "expr"
	// CGEL.g:79:1: expr : ( '@' ^ ID |id= ID ( '(' ')' -> ^( FUNCTION_CALL $id) | -> ^( $id) | '(' expr ( ',' expr )* ')' -> ^( FUNCTION_CALL $id ( expr )+ ) ) | RELATIONAL_OPERATOR '(' e1= expr ',' e2= expr ')' -> ^( BINARY_OPERATOR RELATIONAL_OPERATOR $e1 $e2) | ( MINUS '(' expr ')' )=> MINUS '(' expr ')' -> ^( UNARY_OPERATOR MINUS expr ) |op= binary_operator '(' e1= expr ',' e2= expr ')' -> ^( BINARY_OPERATOR $op $e1 $e2) | const_expr ^);
	public final CGELParser.expr_return expr() throws RecognitionException {
		CGELParser.expr_return retval = new CGELParser.expr_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token id=null;
		Token char_literal64=null;
		Token ID65=null;
		Token char_literal66=null;
		Token char_literal67=null;
		Token char_literal68=null;
		Token char_literal70=null;
		Token char_literal72=null;
		Token RELATIONAL_OPERATOR73=null;
		Token char_literal74=null;
		Token char_literal75=null;
		Token char_literal76=null;
		Token MINUS77=null;
		Token char_literal78=null;
		Token char_literal80=null;
		Token char_literal81=null;
		Token char_literal82=null;
		Token char_literal83=null;
		ParserRuleReturnScope e1 =null;
		ParserRuleReturnScope e2 =null;
		ParserRuleReturnScope op =null;
		ParserRuleReturnScope expr69 =null;
		ParserRuleReturnScope expr71 =null;
		ParserRuleReturnScope expr79 =null;
		ParserRuleReturnScope const_expr84 =null;

		CommonTree id_tree=null;
		CommonTree char_literal64_tree=null;
		CommonTree ID65_tree=null;
		CommonTree char_literal66_tree=null;
		CommonTree char_literal67_tree=null;
		CommonTree char_literal68_tree=null;
		CommonTree char_literal70_tree=null;
		CommonTree char_literal72_tree=null;
		CommonTree RELATIONAL_OPERATOR73_tree=null;
		CommonTree char_literal74_tree=null;
		CommonTree char_literal75_tree=null;
		CommonTree char_literal76_tree=null;
		CommonTree MINUS77_tree=null;
		CommonTree char_literal78_tree=null;
		CommonTree char_literal80_tree=null;
		CommonTree char_literal81_tree=null;
		CommonTree char_literal82_tree=null;
		CommonTree char_literal83_tree=null;
		RewriteRuleTokenStream stream_MINUS=new RewriteRuleTokenStream(adaptor,"token MINUS");
		RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
		RewriteRuleTokenStream stream_22=new RewriteRuleTokenStream(adaptor,"token 22");
		RewriteRuleTokenStream stream_23=new RewriteRuleTokenStream(adaptor,"token 23");
		RewriteRuleTokenStream stream_24=new RewriteRuleTokenStream(adaptor,"token 24");
		RewriteRuleTokenStream stream_RELATIONAL_OPERATOR=new RewriteRuleTokenStream(adaptor,"token RELATIONAL_OPERATOR");
		RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
		RewriteRuleSubtreeStream stream_binary_operator=new RewriteRuleSubtreeStream(adaptor,"rule binary_operator");

		try {
			// CGEL.g:79:5: ( '@' ^ ID |id= ID ( '(' ')' -> ^( FUNCTION_CALL $id) | -> ^( $id) | '(' expr ( ',' expr )* ')' -> ^( FUNCTION_CALL $id ( expr )+ ) ) | RELATIONAL_OPERATOR '(' e1= expr ',' e2= expr ')' -> ^( BINARY_OPERATOR RELATIONAL_OPERATOR $e1 $e2) | ( MINUS '(' expr ')' )=> MINUS '(' expr ')' -> ^( UNARY_OPERATOR MINUS expr ) |op= binary_operator '(' e1= expr ',' e2= expr ')' -> ^( BINARY_OPERATOR $op $e1 $e2) | const_expr ^)
			int alt10=6;
			switch ( input.LA(1) ) {
			case 27:
				{
				alt10=1;
				}
				break;
			case ID:
				{
				alt10=2;
				}
				break;
			case RELATIONAL_OPERATOR:
				{
				alt10=3;
				}
				break;
			case MINUS:
				{
				int LA10_4 = input.LA(2);
				if ( (synpred1_CGEL()) ) {
					alt10=4;
				}
				else if ( (true) ) {
					alt10=5;
				}

				}
				break;
			case DIVIDE:
			case MULTIPLY:
			case PLUS:
				{
				alt10=5;
				}
				break;
			case 28:
				{
				alt10=6;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 10, 0, input);
				throw nvae;
			}
			switch (alt10) {
				case 1 :
					// CGEL.g:80:6: '@' ^ ID
					{
					root_0 = (CommonTree)adaptor.nil();


					char_literal64=(Token)match(input,27,FOLLOW_27_in_expr607); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal64_tree = (CommonTree)adaptor.create(char_literal64);
					root_0 = (CommonTree)adaptor.becomeRoot(char_literal64_tree, root_0);
					}

					ID65=(Token)match(input,ID,FOLLOW_ID_in_expr610); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ID65_tree = (CommonTree)adaptor.create(ID65);
					adaptor.addChild(root_0, ID65_tree);
					}

					}
					break;
				case 2 :
					// CGEL.g:81:7: id= ID ( '(' ')' -> ^( FUNCTION_CALL $id) | -> ^( $id) | '(' expr ( ',' expr )* ')' -> ^( FUNCTION_CALL $id ( expr )+ ) )
					{
					id=(Token)match(input,ID,FOLLOW_ID_in_expr620); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_ID.add(id);

					// CGEL.g:81:13: ( '(' ')' -> ^( FUNCTION_CALL $id) | -> ^( $id) | '(' expr ( ',' expr )* ')' -> ^( FUNCTION_CALL $id ( expr )+ ) )
					int alt9=3;
					int LA9_0 = input.LA(1);
					if ( (LA9_0==22) ) {
						int LA9_1 = input.LA(2);
						if ( (LA9_1==23) ) {
							alt9=1;
						}
						else if ( (LA9_1==DIVIDE||LA9_1==ID||(LA9_1 >= MINUS && LA9_1 <= PLUS)||LA9_1==RELATIONAL_OPERATOR||(LA9_1 >= 27 && LA9_1 <= 28)) ) {
							alt9=3;
						}

						else {
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								input.consume();
								NoViableAltException nvae =
									new NoViableAltException("", 9, 1, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

					}
					else if ( ((LA9_0 >= 23 && LA9_0 <= 24)) ) {
						alt9=2;
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						NoViableAltException nvae =
							new NoViableAltException("", 9, 0, input);
						throw nvae;
					}

					switch (alt9) {
						case 1 :
							// CGEL.g:82:13: '(' ')'
							{
							char_literal66=(Token)match(input,22,FOLLOW_22_in_expr638); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_22.add(char_literal66);

							char_literal67=(Token)match(input,23,FOLLOW_23_in_expr640); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_23.add(char_literal67);

							// AST REWRITE
							// elements: id
							// token labels: id
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							if ( state.backtracking==0 ) {
							retval.tree = root_0;
							RewriteRuleTokenStream stream_id=new RewriteRuleTokenStream(adaptor,"token id",id);
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 82:21: -> ^( FUNCTION_CALL $id)
							{
								// CGEL.g:82:24: ^( FUNCTION_CALL $id)
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(FUNCTION_CALL, "FUNCTION_CALL"), root_1);
								adaptor.addChild(root_1, stream_id.nextNode());
								adaptor.addChild(root_0, root_1);
								}

							}


							retval.tree = root_0;
							}

							}
							break;
						case 2 :
							// CGEL.g:83:11: 
							{
							// AST REWRITE
							// elements: id
							// token labels: id
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							if ( state.backtracking==0 ) {
							retval.tree = root_0;
							RewriteRuleTokenStream stream_id=new RewriteRuleTokenStream(adaptor,"token id",id);
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 83:11: -> ^( $id)
							{
								// CGEL.g:83:14: ^( $id)
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot(stream_id.nextNode(), root_1);
								adaptor.addChild(root_0, root_1);
								}

							}


							retval.tree = root_0;
							}

							}
							break;
						case 3 :
							// CGEL.g:84:11: '(' expr ( ',' expr )* ')'
							{
							char_literal68=(Token)match(input,22,FOLLOW_22_in_expr679); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_22.add(char_literal68);

							pushFollow(FOLLOW_expr_in_expr681);
							expr69=expr();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_expr.add(expr69.getTree());
							// CGEL.g:84:20: ( ',' expr )*
							loop8:
							while (true) {
								int alt8=2;
								int LA8_0 = input.LA(1);
								if ( (LA8_0==24) ) {
									alt8=1;
								}

								switch (alt8) {
								case 1 :
									// CGEL.g:84:21: ',' expr
									{
									char_literal70=(Token)match(input,24,FOLLOW_24_in_expr684); if (state.failed) return retval; 
									if ( state.backtracking==0 ) stream_24.add(char_literal70);

									pushFollow(FOLLOW_expr_in_expr686);
									expr71=expr();
									state._fsp--;
									if (state.failed) return retval;
									if ( state.backtracking==0 ) stream_expr.add(expr71.getTree());
									}
									break;

								default :
									break loop8;
								}
							}

							char_literal72=(Token)match(input,23,FOLLOW_23_in_expr690); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_23.add(char_literal72);

							// AST REWRITE
							// elements: expr, id
							// token labels: id
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							if ( state.backtracking==0 ) {
							retval.tree = root_0;
							RewriteRuleTokenStream stream_id=new RewriteRuleTokenStream(adaptor,"token id",id);
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 84:36: -> ^( FUNCTION_CALL $id ( expr )+ )
							{
								// CGEL.g:84:39: ^( FUNCTION_CALL $id ( expr )+ )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(FUNCTION_CALL, "FUNCTION_CALL"), root_1);
								adaptor.addChild(root_1, stream_id.nextNode());
								if ( !(stream_expr.hasNext()) ) {
									throw new RewriteEarlyExitException();
								}
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
							break;

					}

					}
					break;
				case 3 :
					// CGEL.g:87:7: RELATIONAL_OPERATOR '(' e1= expr ',' e2= expr ')'
					{
					RELATIONAL_OPERATOR73=(Token)match(input,RELATIONAL_OPERATOR,FOLLOW_RELATIONAL_OPERATOR_in_expr725); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_RELATIONAL_OPERATOR.add(RELATIONAL_OPERATOR73);

					char_literal74=(Token)match(input,22,FOLLOW_22_in_expr727); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_22.add(char_literal74);

					pushFollow(FOLLOW_expr_in_expr731);
					e1=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_expr.add(e1.getTree());
					char_literal75=(Token)match(input,24,FOLLOW_24_in_expr733); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_24.add(char_literal75);

					pushFollow(FOLLOW_expr_in_expr737);
					e2=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_expr.add(e2.getTree());
					char_literal76=(Token)match(input,23,FOLLOW_23_in_expr739); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_23.add(char_literal76);

					// AST REWRITE
					// elements: RELATIONAL_OPERATOR, e2, e1
					// token labels: 
					// rule labels: retval, e1, e2
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
					RewriteRuleSubtreeStream stream_e1=new RewriteRuleSubtreeStream(adaptor,"rule e1",e1!=null?e1.getTree():null);
					RewriteRuleSubtreeStream stream_e2=new RewriteRuleSubtreeStream(adaptor,"rule e2",e2!=null?e2.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 87:55: -> ^( BINARY_OPERATOR RELATIONAL_OPERATOR $e1 $e2)
					{
						// CGEL.g:88:9: ^( BINARY_OPERATOR RELATIONAL_OPERATOR $e1 $e2)
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(BINARY_OPERATOR, "BINARY_OPERATOR"), root_1);
						adaptor.addChild(root_1, stream_RELATIONAL_OPERATOR.nextNode());
						adaptor.addChild(root_1, stream_e1.nextTree());
						adaptor.addChild(root_1, stream_e2.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 4 :
					// CGEL.g:90:7: ( MINUS '(' expr ')' )=> MINUS '(' expr ')'
					{
					MINUS77=(Token)match(input,MINUS,FOLLOW_MINUS_in_expr783); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_MINUS.add(MINUS77);

					char_literal78=(Token)match(input,22,FOLLOW_22_in_expr785); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_22.add(char_literal78);

					pushFollow(FOLLOW_expr_in_expr787);
					expr79=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_expr.add(expr79.getTree());
					char_literal80=(Token)match(input,23,FOLLOW_23_in_expr789); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_23.add(char_literal80);

					// AST REWRITE
					// elements: MINUS, expr
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 90:50: -> ^( UNARY_OPERATOR MINUS expr )
					{
						// CGEL.g:91:9: ^( UNARY_OPERATOR MINUS expr )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(UNARY_OPERATOR, "UNARY_OPERATOR"), root_1);
						adaptor.addChild(root_1, stream_MINUS.nextNode());
						adaptor.addChild(root_1, stream_expr.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 5 :
					// CGEL.g:92:7: op= binary_operator '(' e1= expr ',' e2= expr ')'
					{
					pushFollow(FOLLOW_binary_operator_in_expr821);
					op=binary_operator();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_binary_operator.add(op.getTree());
					char_literal81=(Token)match(input,22,FOLLOW_22_in_expr823); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_22.add(char_literal81);

					pushFollow(FOLLOW_expr_in_expr827);
					e1=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_expr.add(e1.getTree());
					char_literal82=(Token)match(input,24,FOLLOW_24_in_expr829); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_24.add(char_literal82);

					pushFollow(FOLLOW_expr_in_expr833);
					e2=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_expr.add(e2.getTree());
					char_literal83=(Token)match(input,23,FOLLOW_23_in_expr835); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_23.add(char_literal83);

					// AST REWRITE
					// elements: op, e1, e2
					// token labels: 
					// rule labels: retval, e1, e2, op
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
					RewriteRuleSubtreeStream stream_e1=new RewriteRuleSubtreeStream(adaptor,"rule e1",e1!=null?e1.getTree():null);
					RewriteRuleSubtreeStream stream_e2=new RewriteRuleSubtreeStream(adaptor,"rule e2",e2!=null?e2.getTree():null);
					RewriteRuleSubtreeStream stream_op=new RewriteRuleSubtreeStream(adaptor,"rule op",op!=null?op.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 92:54: -> ^( BINARY_OPERATOR $op $e1 $e2)
					{
						// CGEL.g:93:9: ^( BINARY_OPERATOR $op $e1 $e2)
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(BINARY_OPERATOR, "BINARY_OPERATOR"), root_1);
						adaptor.addChild(root_1, stream_op.nextTree());
						adaptor.addChild(root_1, stream_e1.nextTree());
						adaptor.addChild(root_1, stream_e2.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 6 :
					// CGEL.g:94:7: const_expr ^
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_const_expr_in_expr867);
					const_expr84=const_expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) root_0 = (CommonTree)adaptor.becomeRoot(const_expr84.getTree(), root_0);
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


	public static class binary_operator_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "binary_operator"
	// CGEL.g:97:1: binary_operator : ( PLUS | MINUS | MULTIPLY | DIVIDE );
	public final CGELParser.binary_operator_return binary_operator() throws RecognitionException {
		CGELParser.binary_operator_return retval = new CGELParser.binary_operator_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token set85=null;

		CommonTree set85_tree=null;

		try {
			// CGEL.g:97:16: ( PLUS | MINUS | MULTIPLY | DIVIDE )
			// CGEL.g:
			{
			root_0 = (CommonTree)adaptor.nil();


			set85=input.LT(1);
			if ( input.LA(1)==DIVIDE||(input.LA(1) >= MINUS && input.LA(1) <= PLUS) ) {
				input.consume();
				if ( state.backtracking==0 ) adaptor.addChild(root_0, (CommonTree)adaptor.create(set85));
				state.errorRecovery=false;
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
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
	// $ANTLR end "binary_operator"


	public static class const_expr_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "const_expr"
	// CGEL.g:101:1: const_expr : 'const' ^ '(' ! aType ',' ! ( REAL_NUMBER | 'true' | 'false' ) ')' !;
	public final CGELParser.const_expr_return const_expr() throws RecognitionException {
		CGELParser.const_expr_return retval = new CGELParser.const_expr_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal86=null;
		Token char_literal87=null;
		Token char_literal89=null;
		Token set90=null;
		Token char_literal91=null;
		ParserRuleReturnScope aType88 =null;

		CommonTree string_literal86_tree=null;
		CommonTree char_literal87_tree=null;
		CommonTree char_literal89_tree=null;
		CommonTree set90_tree=null;
		CommonTree char_literal91_tree=null;

		try {
			// CGEL.g:101:11: ( 'const' ^ '(' ! aType ',' ! ( REAL_NUMBER | 'true' | 'false' ) ')' !)
			// CGEL.g:102:9: 'const' ^ '(' ! aType ',' ! ( REAL_NUMBER | 'true' | 'false' ) ')' !
			{
			root_0 = (CommonTree)adaptor.nil();


			string_literal86=(Token)match(input,28,FOLLOW_28_in_const_expr918); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			string_literal86_tree = (CommonTree)adaptor.create(string_literal86);
			root_0 = (CommonTree)adaptor.becomeRoot(string_literal86_tree, root_0);
			}

			char_literal87=(Token)match(input,22,FOLLOW_22_in_const_expr921); if (state.failed) return retval;
			pushFollow(FOLLOW_aType_in_const_expr924);
			aType88=aType();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, aType88.getTree());

			char_literal89=(Token)match(input,24,FOLLOW_24_in_const_expr926); if (state.failed) return retval;
			set90=input.LT(1);
			if ( input.LA(1)==REAL_NUMBER||input.LA(1)==30||input.LA(1)==33 ) {
				input.consume();
				if ( state.backtracking==0 ) adaptor.addChild(root_0, (CommonTree)adaptor.create(set90));
				state.errorRecovery=false;
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			char_literal91=(Token)match(input,23,FOLLOW_23_in_const_expr981); if (state.failed) return retval;
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
	// $ANTLR end "const_expr"

	// $ANTLR start synpred1_CGEL
	public final void synpred1_CGEL_fragment() throws RecognitionException {
		// CGEL.g:90:7: ( MINUS '(' expr ')' )
		// CGEL.g:90:8: MINUS '(' expr ')'
		{
		match(input,MINUS,FOLLOW_MINUS_in_synpred1_CGEL772); if (state.failed) return;

		match(input,22,FOLLOW_22_in_synpred1_CGEL774); if (state.failed) return;

		pushFollow(FOLLOW_expr_in_synpred1_CGEL776);
		expr();
		state._fsp--;
		if (state.failed) return;

		match(input,23,FOLLOW_23_in_synpred1_CGEL778); if (state.failed) return;

		}

	}
	// $ANTLR end synpred1_CGEL

	// Delegated rules

	public final boolean synpred1_CGEL() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred1_CGEL_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}



	public static final BitSet FOLLOW_31_in_function118 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_function_prototype_in_function121 = new BitSet(new long[]{0x0000001000000000L});
	public static final BitSet FOLLOW_block_in_function124 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_22_in_function_prototype139 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_function_input_in_function_prototype142 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_24_in_function_prototype144 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_function_output_in_function_prototype147 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_24_in_function_prototype149 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_ID_in_function_prototype152 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_23_in_function_prototype156 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_22_in_function_output173 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_23_in_function_output175 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_22_in_function_output189 = new BitSet(new long[]{0x0000000400000000L});
	public static final BitSet FOLLOW_var_decl_in_function_output191 = new BitSet(new long[]{0x0000000001800000L});
	public static final BitSet FOLLOW_24_in_function_output194 = new BitSet(new long[]{0x0000000400000000L});
	public static final BitSet FOLLOW_var_decl_in_function_output197 = new BitSet(new long[]{0x0000000001800000L});
	public static final BitSet FOLLOW_23_in_function_output202 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_22_in_function_input227 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_23_in_function_input229 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_22_in_function_input243 = new BitSet(new long[]{0x0000000400000000L});
	public static final BitSet FOLLOW_var_decl_in_function_input245 = new BitSet(new long[]{0x0000000001800000L});
	public static final BitSet FOLLOW_24_in_function_input248 = new BitSet(new long[]{0x0000000400000000L});
	public static final BitSet FOLLOW_var_decl_in_function_input251 = new BitSet(new long[]{0x0000000001800000L});
	public static final BitSet FOLLOW_23_in_function_input256 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_34_in_var_decl283 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_22_in_var_decl286 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_aType_in_var_decl289 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_24_in_var_decl291 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_ID_in_var_decl295 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_23_in_var_decl297 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_25_in_var_decl300 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_aType320 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_36_in_block339 = new BitSet(new long[]{0x0000002D04000400L});
	public static final BitSet FOLLOW_stmt_in_block342 = new BitSet(new long[]{0x0000002D04000400L});
	public static final BitSet FOLLOW_37_in_block346 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_var_decl_in_stmt376 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_assignment_in_stmt380 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_if_else_stmt_in_stmt384 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_while_stmt_in_stmt388 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_call_stmt_in_stmt392 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_26_in_assignment412 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_22_in_assignment415 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_ID_in_assignment418 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_24_in_assignment420 = new BitSet(new long[]{0x000000001802E440L});
	public static final BitSet FOLLOW_expr_in_assignment423 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_23_in_assignment425 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_25_in_assignment428 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_32_in_if_else_stmt448 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_22_in_if_else_stmt450 = new BitSet(new long[]{0x000000001802E440L});
	public static final BitSet FOLLOW_expr_in_if_else_stmt452 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_23_in_if_else_stmt454 = new BitSet(new long[]{0x0000001000000000L});
	public static final BitSet FOLLOW_block_in_if_else_stmt458 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_29_in_if_else_stmt460 = new BitSet(new long[]{0x0000001000000000L});
	public static final BitSet FOLLOW_block_in_if_else_stmt464 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_35_in_while_stmt497 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_22_in_while_stmt499 = new BitSet(new long[]{0x000000001802E440L});
	public static final BitSet FOLLOW_expr_in_while_stmt501 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_23_in_while_stmt503 = new BitSet(new long[]{0x0000001000000000L});
	public static final BitSet FOLLOW_block_in_while_stmt507 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_function_call_in_call_stmt539 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_25_in_call_stmt542 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_function_call565 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_22_in_function_call567 = new BitSet(new long[]{0x000000001802E440L});
	public static final BitSet FOLLOW_expr_in_function_call569 = new BitSet(new long[]{0x0000000001800000L});
	public static final BitSet FOLLOW_24_in_function_call572 = new BitSet(new long[]{0x000000001802E440L});
	public static final BitSet FOLLOW_expr_in_function_call574 = new BitSet(new long[]{0x0000000001800000L});
	public static final BitSet FOLLOW_23_in_function_call578 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_27_in_expr607 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_ID_in_expr610 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_expr620 = new BitSet(new long[]{0x0000000000400002L});
	public static final BitSet FOLLOW_22_in_expr638 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_23_in_expr640 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_22_in_expr679 = new BitSet(new long[]{0x000000001802E440L});
	public static final BitSet FOLLOW_expr_in_expr681 = new BitSet(new long[]{0x0000000001800000L});
	public static final BitSet FOLLOW_24_in_expr684 = new BitSet(new long[]{0x000000001802E440L});
	public static final BitSet FOLLOW_expr_in_expr686 = new BitSet(new long[]{0x0000000001800000L});
	public static final BitSet FOLLOW_23_in_expr690 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_RELATIONAL_OPERATOR_in_expr725 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_22_in_expr727 = new BitSet(new long[]{0x000000001802E440L});
	public static final BitSet FOLLOW_expr_in_expr731 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_24_in_expr733 = new BitSet(new long[]{0x000000001802E440L});
	public static final BitSet FOLLOW_expr_in_expr737 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_23_in_expr739 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_MINUS_in_expr783 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_22_in_expr785 = new BitSet(new long[]{0x000000001802E440L});
	public static final BitSet FOLLOW_expr_in_expr787 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_23_in_expr789 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_binary_operator_in_expr821 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_22_in_expr823 = new BitSet(new long[]{0x000000001802E440L});
	public static final BitSet FOLLOW_expr_in_expr827 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_24_in_expr829 = new BitSet(new long[]{0x000000001802E440L});
	public static final BitSet FOLLOW_expr_in_expr833 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_23_in_expr835 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_const_expr_in_expr867 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_28_in_const_expr918 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_22_in_const_expr921 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_aType_in_const_expr924 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_24_in_const_expr926 = new BitSet(new long[]{0x0000000240010000L});
	public static final BitSet FOLLOW_set_in_const_expr929 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_23_in_const_expr981 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_MINUS_in_synpred1_CGEL772 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_22_in_synpred1_CGEL774 = new BitSet(new long[]{0x000000001802E440L});
	public static final BitSet FOLLOW_expr_in_synpred1_CGEL776 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_23_in_synpred1_CGEL778 = new BitSet(new long[]{0x0000000000000002L});
}
