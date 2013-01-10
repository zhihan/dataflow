grammar CGEL;

options {
    output=AST;
    ASTLabelType=CommonTree; // tree.toStringTree
    backtrack=true;   // Function -> exp is recursive
}

// Main Grammar

tokens {
  FUNCTION_INPUT;
  FUNCTION_OUTPUT;
  BLOCK;
  IF_ELSE;
  WHILE;
  FUNCTION_CALL;
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
    | '(' var_decl (','  var_decl )* ')' -> ^(FUNCTION_OUTPUT var_decl+)
    ;
function_input:
    | '(' ')' -> ^(FUNCTION_INPUT)
    | '(' var_decl (','  var_decl )* ')' -> ^(FUNCTION_INPUT var_decl+)
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
        var_decl | assignment | if_else_stmt | while_stmt
    ;

assignment:
        '='^ '('! ID ','! expr ')'! ';'
    ;
if_else_stmt:
        'if' '(' expr ')' b1=block 'else' b2=block -> ^(IF_ELSE expr $b1 $b2)
    ;
while_stmt:
        'while' '(' expr ')' b=block -> ^(WHILE expr $b) 
    ;

// I don't know why the following grammar works. 
// But it does parse 0,1, or more arguments.
function_call:
        (n=ID)  '(' expr? (',' expr)* ')' -> ^(FUNCTION_CALL $n expr*)
    ;

expr:
    | '@'^ ID  
    | ID^  
    | function_call^ 
    | BINARY_OPERATOR^ '('!  expr ','! expr ')'!
    | RELATIONAL_OPERATOR^ '('! expr ','! expr ')'!
    ;
// 
// Lexing rules 
// 
// White space
WS: (' ' | '\t' | '\r' | '\n' )+ { skip(); } ;

BINARY_OPERATOR: '+' | '-' | '*' | '/' ;

RELATIONAL_OPERATOR: '==' | '>' | '<' | '>=' | '<=' ;

// Single-line comments
SL_COMMENTS: '//' ( ~ '\n' )* '\n' { skip(); } ;

ID: LETTER (LETTER | '0' .. '9')* 
    ;
fragment LETTER: ('a' .. 'z' | 'A' .. 'Z'| '_' ) ;


