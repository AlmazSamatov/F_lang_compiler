lexer grammar FLangLexer;

// Whitespace
NEWLINE            : '\r\n' | '\r' | '\n' -> skip;
WS                 : [\t ]+ -> skip;

// Keywords
IS                 : 'is' ;
IF                 : 'if' ;
IN                 : 'in' ;
THEN               : 'then' ;
ELSE               : 'else' ;
END                : 'end' ;
FUNC               : 'func' ;
DO                 : 'do' ;
LOOP               : 'loop' ;
FOR                : 'for' ;
WHILE              : 'while' ;
TRUE               : 'true' ;
FALSE              : 'false' ;
INTEGER            : 'integer' ;
REAL               : 'real' ;
BOOLEAN            : 'boolean' ;
RATIONAL           : 'rational' ;
COMPLEX            : 'complex' ;
STRING             : 'string' ;
PRINT              : 'print' ;
RETURN             : 'return' ;

// Standard functions
TYPE_CONV          : 'round' | 're' | 'im' | 'num' | 'denom' |
                     'compl' | 'rat' | 'norm' ;
LENGTH             : 'length' ;

// Literals
INT_LIT            : '0' | [1-9][0-9]* ;
REAL_LIT           : [0-9]+ '.' [0-9]+ ;
RAT_LIT            : INT_LIT '\\' INT_LIT ;
COMP_LIT           : (INT_LIT | REAL_LIT) 'i' (INT_LIT | REAL_LIT) ;
STR_LIT            : '"' ~["]* '"' ;

// Operators - done
PLUS               : '+' ;
MINUS              : '-' ;
MULT               : '*' ;
DIVISION           : '/' ;
LESS               : '<' ;
GREATER            : '>' ;
LESS_EQ            : '<=' ;
GREATER_EQ         : '>=' ;
EQUAL              : '=' ;
NOT_EQ             : '/=' ;
AND                : '&' ;
OR                 : '|' ;
XOR                : '^' ;

LINE_FUN           : '=>' ;
ASSIGN             : ':=' ;

// Delimiters
LPAR               : '(' ;
RPAR               : ')' ;
LSQUARE            : '[' ;
RSQUARE            : ']' ;
SEMI               : ';' ;
COLUMN             : ':' ;
COMMA              : ',' ;


// Identifiers
ID                 : [_]*[a-z][A-Za-z0-9_]* ;