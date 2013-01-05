grammar CGEL;

options {
    output=AST;
    ASTLabelType=CommonTree; // tree.toStringTree
}

// Main Grammar

tokens {
  FUNCTION_INPUT;
  FUNCTION_OUTPUT;
  BLOCK;
  IF_ELSE;
}


@header {
package my.ir;
}

@lexer::header {
package my.ir;
}


function: 
        'function'! function_prototype^ block
    ;
function_prototype:
    '('! function_input ','! function_output ','! ID^  ')'!
    ;
function_output:
    | '(' ')' -> ^(FUNCTION_OUTPUT)
    | '(' var_decl (','  var_decl )*')' -> ^(FUNCTION_OUTPUT var_decl+)
    ;
function_input:
    | '(' ')' -> ^(FUNCTION_INPUT)
    | '(' var_decl (','  var_decl )*')' -> ^(FUNCTION_INPUT var_decl+)
    ;
var_decl:
       'var'^ '('! aType ','!  ID ')'! ';'
    ;
aType:
        ID
    ;

block:
       '{' (stmt)* '}' -> ^(BLOCK stmt*)
    ; 

stmt:
        var_decl | assignment | if_else_stmt 
    ;

assignment:
        '='^ '('! ID ','! expr ')'! ';'
    ;
if_else_stmt:
        'if' '(' expr ')' b1=block 'else' b2=block -> ^(IF_ELSE expr $b1 $b2 )
    ;

expr:
    | '@'^ ID  
    | ID^  
    | BINARY_OPERATOR^ '('!  expr ','! expr ')'!
    ;
// 
// Lexing rules 
// 
// White space
WS: (' ' | '\t' | '\r' | '\n' )+ { skip(); } ;

BINARY_OPERATOR: '+' | '-' | '*' | '/' ;


// Single-line comments
SL_COMMENTS: '//' ( ~ '\n' )* '\n' { skip(); } ;

ID: LETTER (LETTER | '0' .. '9')* 
    ;
fragment LETTER: ('a' .. 'z' | 'A' .. 'Z'| '_' ) ;


