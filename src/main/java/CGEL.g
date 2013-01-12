grammar CGEL;

options {
    output=AST;
    ASTLabelType=CommonTree; // tree.toStringTree
    backtrack=false;   // Function -> exp is recursive
}

// Main Grammar

tokens {
  FUNCTION_INPUT;
  FUNCTION_OUTPUT;
  BLOCK;
  IF_ELSE;
  WHILE;
  FUNCTION_CALL;
  BINARY_OPERATOR;
  UNARY_OPERATOR;
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
     '(' ')' -> ^(FUNCTION_OUTPUT)
    | '(' var_decl (','  var_decl )* ')' -> ^(FUNCTION_OUTPUT var_decl+)
    ;
function_input:
     '(' ')' -> ^(FUNCTION_INPUT)
    | '(' var_decl (','  var_decl )* ')' -> ^(FUNCTION_INPUT var_decl+)
    ;
var_decl:
       'var'^ '('! aType ','!  ID ')'! ';'!
    ;
aType:
        ID
    ;

block:
       '{' (stmt)* '}' -> ^(BLOCK stmt*)
    ; 

stmt:
        var_decl | assignment | if_else_stmt | while_stmt | call_stmt
    ;

assignment:
        '='^ '('! ID ','! expr ')'! ';'!
    ;
if_else_stmt:
        'if' '(' expr ')' b1=block 'else' b2=block -> ^(IF_ELSE expr $b1 $b2)
    ;
while_stmt:
        'while' '(' expr ')' b=block -> ^(WHILE expr $b) 
    ;

call_stmt:
        function_call^ ';'!
    ;

function_call:
        n=ID '(' expr (',' expr)* ')' -> ^(FUNCTION_CALL $n expr+)
    ;

expr:
     '@'^ ID
    | id=ID (  // Sub-rule used for left factorization 
            '(' ')' -> ^(FUNCTION_CALL $id )
        | -> ^($id)
        | '(' expr (',' expr)* ')' -> ^(FUNCTION_CALL $id expr+)
        )
    
    | RELATIONAL_OPERATOR '(' e1=expr ',' e2=expr ')' -> 
        ^(BINARY_OPERATOR RELATIONAL_OPERATOR $e1 $e2)

    | (MINUS '(' expr ')') => MINUS '(' expr ')' ->  
        ^(UNARY_OPERATOR MINUS expr)  
    | op=binary_operator '(' e1=expr ',' e2=expr ')' -> 
        ^(BINARY_OPERATOR $op $e1 $e2)
    | const_expr^ 
    ;

binary_operator: 
    PLUS | MINUS | MULTIPLY | DIVIDE
    ;

const_expr:
        'const'^ '('! aType ','! (
            REAL_NUMBER 
        | 'true' 
        | 'false'
        ) ')'! 
    ;


// 
// Lexing rules 
// 
// White space
WS: (' ' | '\t' | '\r' | '\n' )+ { skip(); } ;

PLUS: '+' ;
MINUS: '-' ;
MULTIPLY: '*' ;
DIVIDE: '/' ;

RELATIONAL_OPERATOR: '==' | '>' | '<' | '>=' | '<=' ;

// Single-line comments
SL_COMMENTS: '//' ( ~ '\n' )* '\n' { skip(); } ;

ID: LETTER (LETTER | '0' .. '9')* 
    ;

REAL_NUMBER: ('0' .. '9')+ ('.' ('0' .. '9')+)? 
    ;

fragment LETTER: ('a' .. 'z' | 'A' .. 'Z'| '_' ) ;

