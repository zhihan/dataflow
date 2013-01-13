// $ANTLR 3.5 CGEL.g 2013-01-12 19:23:44

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
			// elements: expr, b
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
	// CGEL.g:75:1: function_call : n= ID ( '(' ')' -> ^( FUNCTION_CALL $n) | '(' expr ( ',' expr )* ')' -> ^( FUNCTION_CALL $n ( expr )+ ) ) ;
	public final CGELParser.function_call_return function_call() throws RecognitionException {
		CGELParser.function_call_return retval = new CGELParser.function_call_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token n=null;
		Token char_literal59=null;
		Token char_literal60=null;
		Token char_literal61=null;
		Token char_literal63=null;
		Token char_literal65=null;
		ParserRuleReturnScope expr62 =null;
		ParserRuleReturnScope expr64 =null;

		CommonTree n_tree=null;
		CommonTree char_literal59_tree=null;
		CommonTree char_literal60_tree=null;
		CommonTree char_literal61_tree=null;
		CommonTree char_literal63_tree=null;
		CommonTree char_literal65_tree=null;
		RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
		RewriteRuleTokenStream stream_22=new RewriteRuleTokenStream(adaptor,"token 22");
		RewriteRuleTokenStream stream_23=new RewriteRuleTokenStream(adaptor,"token 23");
		RewriteRuleTokenStream stream_24=new RewriteRuleTokenStream(adaptor,"token 24");
		RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");

		try {
			// CGEL.g:75:14: (n= ID ( '(' ')' -> ^( FUNCTION_CALL $n) | '(' expr ( ',' expr )* ')' -> ^( FUNCTION_CALL $n ( expr )+ ) ) )
			// CGEL.g:76:9: n= ID ( '(' ')' -> ^( FUNCTION_CALL $n) | '(' expr ( ',' expr )* ')' -> ^( FUNCTION_CALL $n ( expr )+ ) )
			{
			n=(Token)match(input,ID,FOLLOW_ID_in_function_call565); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_ID.add(n);

			// CGEL.g:76:14: ( '(' ')' -> ^( FUNCTION_CALL $n) | '(' expr ( ',' expr )* ')' -> ^( FUNCTION_CALL $n ( expr )+ ) )
			int alt8=2;
			int LA8_0 = input.LA(1);
			if ( (LA8_0==22) ) {
				int LA8_1 = input.LA(2);
				if ( (LA8_1==23) ) {
					alt8=1;
				}
				else if ( (LA8_1==DIVIDE||LA8_1==ID||(LA8_1 >= MINUS && LA8_1 <= PLUS)||LA8_1==RELATIONAL_OPERATOR||(LA8_1 >= 27 && LA8_1 <= 28)) ) {
					alt8=2;
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 8, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 8, 0, input);
				throw nvae;
			}

			switch (alt8) {
				case 1 :
					// CGEL.g:77:13: '(' ')'
					{
					char_literal59=(Token)match(input,22,FOLLOW_22_in_function_call581); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_22.add(char_literal59);

					char_literal60=(Token)match(input,23,FOLLOW_23_in_function_call583); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_23.add(char_literal60);

					// AST REWRITE
					// elements: n
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
					// 77:21: -> ^( FUNCTION_CALL $n)
					{
						// CGEL.g:77:24: ^( FUNCTION_CALL $n)
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(FUNCTION_CALL, "FUNCTION_CALL"), root_1);
						adaptor.addChild(root_1, stream_n.nextNode());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// CGEL.g:78:11: '(' expr ( ',' expr )* ')'
					{
					char_literal61=(Token)match(input,22,FOLLOW_22_in_function_call604); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_22.add(char_literal61);

					pushFollow(FOLLOW_expr_in_function_call606);
					expr62=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_expr.add(expr62.getTree());
					// CGEL.g:78:20: ( ',' expr )*
					loop7:
					while (true) {
						int alt7=2;
						int LA7_0 = input.LA(1);
						if ( (LA7_0==24) ) {
							alt7=1;
						}

						switch (alt7) {
						case 1 :
							// CGEL.g:78:21: ',' expr
							{
							char_literal63=(Token)match(input,24,FOLLOW_24_in_function_call609); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_24.add(char_literal63);

							pushFollow(FOLLOW_expr_in_function_call611);
							expr64=expr();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_expr.add(expr64.getTree());
							}
							break;

						default :
							break loop7;
						}
					}

					char_literal65=(Token)match(input,23,FOLLOW_23_in_function_call615); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_23.add(char_literal65);

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
					// 78:36: -> ^( FUNCTION_CALL $n ( expr )+ )
					{
						// CGEL.g:78:39: ^( FUNCTION_CALL $n ( expr )+ )
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
					break;

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
	// CGEL.g:82:1: expr : ( '@' ^ ID |id= ID ( -> ^( $id) | '(' ')' -> ^( FUNCTION_CALL $id) | '(' expr ( ',' expr )* ')' -> ^( FUNCTION_CALL $id ( expr )+ ) ) | RELATIONAL_OPERATOR '(' e1= expr ',' e2= expr ')' -> ^( BINARY_OPERATOR RELATIONAL_OPERATOR $e1 $e2) | ( MINUS '(' expr ')' )=> MINUS '(' expr ')' -> ^( UNARY_OPERATOR MINUS expr ) |op= binary_operator '(' e1= expr ',' e2= expr ')' -> ^( BINARY_OPERATOR $op $e1 $e2) | const_expr ^);
	public final CGELParser.expr_return expr() throws RecognitionException {
		CGELParser.expr_return retval = new CGELParser.expr_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token id=null;
		Token char_literal66=null;
		Token ID67=null;
		Token char_literal68=null;
		Token char_literal69=null;
		Token char_literal70=null;
		Token char_literal72=null;
		Token char_literal74=null;
		Token RELATIONAL_OPERATOR75=null;
		Token char_literal76=null;
		Token char_literal77=null;
		Token char_literal78=null;
		Token MINUS79=null;
		Token char_literal80=null;
		Token char_literal82=null;
		Token char_literal83=null;
		Token char_literal84=null;
		Token char_literal85=null;
		ParserRuleReturnScope e1 =null;
		ParserRuleReturnScope e2 =null;
		ParserRuleReturnScope op =null;
		ParserRuleReturnScope expr71 =null;
		ParserRuleReturnScope expr73 =null;
		ParserRuleReturnScope expr81 =null;
		ParserRuleReturnScope const_expr86 =null;

		CommonTree id_tree=null;
		CommonTree char_literal66_tree=null;
		CommonTree ID67_tree=null;
		CommonTree char_literal68_tree=null;
		CommonTree char_literal69_tree=null;
		CommonTree char_literal70_tree=null;
		CommonTree char_literal72_tree=null;
		CommonTree char_literal74_tree=null;
		CommonTree RELATIONAL_OPERATOR75_tree=null;
		CommonTree char_literal76_tree=null;
		CommonTree char_literal77_tree=null;
		CommonTree char_literal78_tree=null;
		CommonTree MINUS79_tree=null;
		CommonTree char_literal80_tree=null;
		CommonTree char_literal82_tree=null;
		CommonTree char_literal83_tree=null;
		CommonTree char_literal84_tree=null;
		CommonTree char_literal85_tree=null;
		RewriteRuleTokenStream stream_MINUS=new RewriteRuleTokenStream(adaptor,"token MINUS");
		RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
		RewriteRuleTokenStream stream_22=new RewriteRuleTokenStream(adaptor,"token 22");
		RewriteRuleTokenStream stream_23=new RewriteRuleTokenStream(adaptor,"token 23");
		RewriteRuleTokenStream stream_24=new RewriteRuleTokenStream(adaptor,"token 24");
		RewriteRuleTokenStream stream_RELATIONAL_OPERATOR=new RewriteRuleTokenStream(adaptor,"token RELATIONAL_OPERATOR");
		RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
		RewriteRuleSubtreeStream stream_binary_operator=new RewriteRuleSubtreeStream(adaptor,"rule binary_operator");

		try {
			// CGEL.g:82:5: ( '@' ^ ID |id= ID ( -> ^( $id) | '(' ')' -> ^( FUNCTION_CALL $id) | '(' expr ( ',' expr )* ')' -> ^( FUNCTION_CALL $id ( expr )+ ) ) | RELATIONAL_OPERATOR '(' e1= expr ',' e2= expr ')' -> ^( BINARY_OPERATOR RELATIONAL_OPERATOR $e1 $e2) | ( MINUS '(' expr ')' )=> MINUS '(' expr ')' -> ^( UNARY_OPERATOR MINUS expr ) |op= binary_operator '(' e1= expr ',' e2= expr ')' -> ^( BINARY_OPERATOR $op $e1 $e2) | const_expr ^)
			int alt11=6;
			switch ( input.LA(1) ) {
			case 27:
				{
				alt11=1;
				}
				break;
			case ID:
				{
				alt11=2;
				}
				break;
			case RELATIONAL_OPERATOR:
				{
				alt11=3;
				}
				break;
			case MINUS:
				{
				int LA11_4 = input.LA(2);
				if ( (synpred1_CGEL()) ) {
					alt11=4;
				}
				else if ( (true) ) {
					alt11=5;
				}

				}
				break;
			case DIVIDE:
			case MULTIPLY:
			case PLUS:
				{
				alt11=5;
				}
				break;
			case 28:
				{
				alt11=6;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 11, 0, input);
				throw nvae;
			}
			switch (alt11) {
				case 1 :
					// CGEL.g:83:6: '@' ^ ID
					{
					root_0 = (CommonTree)adaptor.nil();


					char_literal66=(Token)match(input,27,FOLLOW_27_in_expr658); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal66_tree = (CommonTree)adaptor.create(char_literal66);
					root_0 = (CommonTree)adaptor.becomeRoot(char_literal66_tree, root_0);
					}

					ID67=(Token)match(input,ID,FOLLOW_ID_in_expr661); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ID67_tree = (CommonTree)adaptor.create(ID67);
					adaptor.addChild(root_0, ID67_tree);
					}

					}
					break;
				case 2 :
					// CGEL.g:84:7: id= ID ( -> ^( $id) | '(' ')' -> ^( FUNCTION_CALL $id) | '(' expr ( ',' expr )* ')' -> ^( FUNCTION_CALL $id ( expr )+ ) )
					{
					id=(Token)match(input,ID,FOLLOW_ID_in_expr671); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_ID.add(id);

					// CGEL.g:84:13: ( -> ^( $id) | '(' ')' -> ^( FUNCTION_CALL $id) | '(' expr ( ',' expr )* ')' -> ^( FUNCTION_CALL $id ( expr )+ ) )
					int alt10=3;
					int LA10_0 = input.LA(1);
					if ( ((LA10_0 >= 23 && LA10_0 <= 24)) ) {
						alt10=1;
					}
					else if ( (LA10_0==22) ) {
						int LA10_2 = input.LA(2);
						if ( (LA10_2==23) ) {
							alt10=2;
						}
						else if ( (LA10_2==DIVIDE||LA10_2==ID||(LA10_2 >= MINUS && LA10_2 <= PLUS)||LA10_2==RELATIONAL_OPERATOR||(LA10_2 >= 27 && LA10_2 <= 28)) ) {
							alt10=3;
						}

						else {
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								input.consume();
								NoViableAltException nvae =
									new NoViableAltException("", 10, 2, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						NoViableAltException nvae =
							new NoViableAltException("", 10, 0, input);
						throw nvae;
					}

					switch (alt10) {
						case 1 :
							// CGEL.g:85:13: 
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
							// 85:13: -> ^( $id)
							{
								// CGEL.g:85:16: ^( $id)
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
						case 2 :
							// CGEL.g:86:11: '(' ')'
							{
							char_literal68=(Token)match(input,22,FOLLOW_22_in_expr706); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_22.add(char_literal68);

							char_literal69=(Token)match(input,23,FOLLOW_23_in_expr708); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_23.add(char_literal69);

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
							// 86:19: -> ^( FUNCTION_CALL $id)
							{
								// CGEL.g:86:22: ^( FUNCTION_CALL $id)
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
						case 3 :
							// CGEL.g:87:11: '(' expr ( ',' expr )* ')'
							{
							char_literal70=(Token)match(input,22,FOLLOW_22_in_expr730); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_22.add(char_literal70);

							pushFollow(FOLLOW_expr_in_expr732);
							expr71=expr();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_expr.add(expr71.getTree());
							// CGEL.g:87:20: ( ',' expr )*
							loop9:
							while (true) {
								int alt9=2;
								int LA9_0 = input.LA(1);
								if ( (LA9_0==24) ) {
									alt9=1;
								}

								switch (alt9) {
								case 1 :
									// CGEL.g:87:21: ',' expr
									{
									char_literal72=(Token)match(input,24,FOLLOW_24_in_expr735); if (state.failed) return retval; 
									if ( state.backtracking==0 ) stream_24.add(char_literal72);

									pushFollow(FOLLOW_expr_in_expr737);
									expr73=expr();
									state._fsp--;
									if (state.failed) return retval;
									if ( state.backtracking==0 ) stream_expr.add(expr73.getTree());
									}
									break;

								default :
									break loop9;
								}
							}

							char_literal74=(Token)match(input,23,FOLLOW_23_in_expr741); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_23.add(char_literal74);

							// AST REWRITE
							// elements: id, expr
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
							// 87:36: -> ^( FUNCTION_CALL $id ( expr )+ )
							{
								// CGEL.g:87:39: ^( FUNCTION_CALL $id ( expr )+ )
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
					// CGEL.g:90:7: RELATIONAL_OPERATOR '(' e1= expr ',' e2= expr ')'
					{
					RELATIONAL_OPERATOR75=(Token)match(input,RELATIONAL_OPERATOR,FOLLOW_RELATIONAL_OPERATOR_in_expr776); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_RELATIONAL_OPERATOR.add(RELATIONAL_OPERATOR75);

					char_literal76=(Token)match(input,22,FOLLOW_22_in_expr778); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_22.add(char_literal76);

					pushFollow(FOLLOW_expr_in_expr782);
					e1=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_expr.add(e1.getTree());
					char_literal77=(Token)match(input,24,FOLLOW_24_in_expr784); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_24.add(char_literal77);

					pushFollow(FOLLOW_expr_in_expr788);
					e2=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_expr.add(e2.getTree());
					char_literal78=(Token)match(input,23,FOLLOW_23_in_expr790); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_23.add(char_literal78);

					// AST REWRITE
					// elements: e1, e2, RELATIONAL_OPERATOR
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
					// 90:55: -> ^( BINARY_OPERATOR RELATIONAL_OPERATOR $e1 $e2)
					{
						// CGEL.g:91:9: ^( BINARY_OPERATOR RELATIONAL_OPERATOR $e1 $e2)
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
					// CGEL.g:93:7: ( MINUS '(' expr ')' )=> MINUS '(' expr ')'
					{
					MINUS79=(Token)match(input,MINUS,FOLLOW_MINUS_in_expr834); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_MINUS.add(MINUS79);

					char_literal80=(Token)match(input,22,FOLLOW_22_in_expr836); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_22.add(char_literal80);

					pushFollow(FOLLOW_expr_in_expr838);
					expr81=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_expr.add(expr81.getTree());
					char_literal82=(Token)match(input,23,FOLLOW_23_in_expr840); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_23.add(char_literal82);

					// AST REWRITE
					// elements: expr, MINUS
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 93:50: -> ^( UNARY_OPERATOR MINUS expr )
					{
						// CGEL.g:94:9: ^( UNARY_OPERATOR MINUS expr )
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
					// CGEL.g:95:7: op= binary_operator '(' e1= expr ',' e2= expr ')'
					{
					pushFollow(FOLLOW_binary_operator_in_expr872);
					op=binary_operator();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_binary_operator.add(op.getTree());
					char_literal83=(Token)match(input,22,FOLLOW_22_in_expr874); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_22.add(char_literal83);

					pushFollow(FOLLOW_expr_in_expr878);
					e1=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_expr.add(e1.getTree());
					char_literal84=(Token)match(input,24,FOLLOW_24_in_expr880); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_24.add(char_literal84);

					pushFollow(FOLLOW_expr_in_expr884);
					e2=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_expr.add(e2.getTree());
					char_literal85=(Token)match(input,23,FOLLOW_23_in_expr886); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_23.add(char_literal85);

					// AST REWRITE
					// elements: op, e2, e1
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
					// 95:54: -> ^( BINARY_OPERATOR $op $e1 $e2)
					{
						// CGEL.g:96:9: ^( BINARY_OPERATOR $op $e1 $e2)
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
					// CGEL.g:97:7: const_expr ^
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_const_expr_in_expr918);
					const_expr86=const_expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) root_0 = (CommonTree)adaptor.becomeRoot(const_expr86.getTree(), root_0);
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
	// CGEL.g:100:1: binary_operator : ( PLUS | MINUS | MULTIPLY | DIVIDE );
	public final CGELParser.binary_operator_return binary_operator() throws RecognitionException {
		CGELParser.binary_operator_return retval = new CGELParser.binary_operator_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token set87=null;

		CommonTree set87_tree=null;

		try {
			// CGEL.g:100:16: ( PLUS | MINUS | MULTIPLY | DIVIDE )
			// CGEL.g:
			{
			root_0 = (CommonTree)adaptor.nil();


			set87=input.LT(1);
			if ( input.LA(1)==DIVIDE||(input.LA(1) >= MINUS && input.LA(1) <= PLUS) ) {
				input.consume();
				if ( state.backtracking==0 ) adaptor.addChild(root_0, (CommonTree)adaptor.create(set87));
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
	// CGEL.g:104:1: const_expr : 'const' ^ '(' ! aType ',' ! ( REAL_NUMBER | 'true' | 'false' ) ')' !;
	public final CGELParser.const_expr_return const_expr() throws RecognitionException {
		CGELParser.const_expr_return retval = new CGELParser.const_expr_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal88=null;
		Token char_literal89=null;
		Token char_literal91=null;
		Token set92=null;
		Token char_literal93=null;
		ParserRuleReturnScope aType90 =null;

		CommonTree string_literal88_tree=null;
		CommonTree char_literal89_tree=null;
		CommonTree char_literal91_tree=null;
		CommonTree set92_tree=null;
		CommonTree char_literal93_tree=null;

		try {
			// CGEL.g:104:11: ( 'const' ^ '(' ! aType ',' ! ( REAL_NUMBER | 'true' | 'false' ) ')' !)
			// CGEL.g:105:9: 'const' ^ '(' ! aType ',' ! ( REAL_NUMBER | 'true' | 'false' ) ')' !
			{
			root_0 = (CommonTree)adaptor.nil();


			string_literal88=(Token)match(input,28,FOLLOW_28_in_const_expr969); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			string_literal88_tree = (CommonTree)adaptor.create(string_literal88);
			root_0 = (CommonTree)adaptor.becomeRoot(string_literal88_tree, root_0);
			}

			char_literal89=(Token)match(input,22,FOLLOW_22_in_const_expr972); if (state.failed) return retval;
			pushFollow(FOLLOW_aType_in_const_expr975);
			aType90=aType();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, aType90.getTree());

			char_literal91=(Token)match(input,24,FOLLOW_24_in_const_expr977); if (state.failed) return retval;
			set92=input.LT(1);
			if ( input.LA(1)==REAL_NUMBER||input.LA(1)==30||input.LA(1)==33 ) {
				input.consume();
				if ( state.backtracking==0 ) adaptor.addChild(root_0, (CommonTree)adaptor.create(set92));
				state.errorRecovery=false;
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			char_literal93=(Token)match(input,23,FOLLOW_23_in_const_expr1032); if (state.failed) return retval;
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
		// CGEL.g:93:7: ( MINUS '(' expr ')' )
		// CGEL.g:93:8: MINUS '(' expr ')'
		{
		match(input,MINUS,FOLLOW_MINUS_in_synpred1_CGEL823); if (state.failed) return;

		match(input,22,FOLLOW_22_in_synpred1_CGEL825); if (state.failed) return;

		pushFollow(FOLLOW_expr_in_synpred1_CGEL827);
		expr();
		state._fsp--;
		if (state.failed) return;

		match(input,23,FOLLOW_23_in_synpred1_CGEL829); if (state.failed) return;

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
	public static final BitSet FOLLOW_22_in_function_call581 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_23_in_function_call583 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_22_in_function_call604 = new BitSet(new long[]{0x000000001802E440L});
	public static final BitSet FOLLOW_expr_in_function_call606 = new BitSet(new long[]{0x0000000001800000L});
	public static final BitSet FOLLOW_24_in_function_call609 = new BitSet(new long[]{0x000000001802E440L});
	public static final BitSet FOLLOW_expr_in_function_call611 = new BitSet(new long[]{0x0000000001800000L});
	public static final BitSet FOLLOW_23_in_function_call615 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_27_in_expr658 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_ID_in_expr661 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_expr671 = new BitSet(new long[]{0x0000000000400002L});
	public static final BitSet FOLLOW_22_in_expr706 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_23_in_expr708 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_22_in_expr730 = new BitSet(new long[]{0x000000001802E440L});
	public static final BitSet FOLLOW_expr_in_expr732 = new BitSet(new long[]{0x0000000001800000L});
	public static final BitSet FOLLOW_24_in_expr735 = new BitSet(new long[]{0x000000001802E440L});
	public static final BitSet FOLLOW_expr_in_expr737 = new BitSet(new long[]{0x0000000001800000L});
	public static final BitSet FOLLOW_23_in_expr741 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_RELATIONAL_OPERATOR_in_expr776 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_22_in_expr778 = new BitSet(new long[]{0x000000001802E440L});
	public static final BitSet FOLLOW_expr_in_expr782 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_24_in_expr784 = new BitSet(new long[]{0x000000001802E440L});
	public static final BitSet FOLLOW_expr_in_expr788 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_23_in_expr790 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_MINUS_in_expr834 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_22_in_expr836 = new BitSet(new long[]{0x000000001802E440L});
	public static final BitSet FOLLOW_expr_in_expr838 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_23_in_expr840 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_binary_operator_in_expr872 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_22_in_expr874 = new BitSet(new long[]{0x000000001802E440L});
	public static final BitSet FOLLOW_expr_in_expr878 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_24_in_expr880 = new BitSet(new long[]{0x000000001802E440L});
	public static final BitSet FOLLOW_expr_in_expr884 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_23_in_expr886 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_const_expr_in_expr918 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_28_in_const_expr969 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_22_in_const_expr972 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_aType_in_const_expr975 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_24_in_const_expr977 = new BitSet(new long[]{0x0000000240010000L});
	public static final BitSet FOLLOW_set_in_const_expr980 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_23_in_const_expr1032 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_MINUS_in_synpred1_CGEL823 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_22_in_synpred1_CGEL825 = new BitSet(new long[]{0x000000001802E440L});
	public static final BitSet FOLLOW_expr_in_synpred1_CGEL827 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_23_in_synpred1_CGEL829 = new BitSet(new long[]{0x0000000000000002L});
}
