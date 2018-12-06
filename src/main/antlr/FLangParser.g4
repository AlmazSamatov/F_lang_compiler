parser grammar FLangParser;

options { tokenVocab=FLangLexer; }

program
    : declaration (SEMI declaration)*
    ;

declaration
    : ID (COLUMN type)? IS expression
    ;

// Expressions
expression
    : secondary                                        # secondaryExpression
    | left=expression operatorSign right=expression    # binaryOperation
    ;

operatorSign
    : PLUS | MINUS | MULT | DIVISION | LESS | GREATER
    | LESS_EQ | GREATER_EQ | EQUAL | NOT_EQ | AND | OR | XOR
    ;

secondary
    : primary                                                   # primaryExpression
    | secondary LPAR (expression (COMMA expression)*)? RPAR     # call
    | secondary LSQUARE expression RSQUARE                      # element
    | secondary DOT ID                                          # namedTupleElement
    | secondary DOT INT_LIT                                     # unnamedTupleElement
    ;

primary
    : elementary                # elementaryExpression
    | conditional               # conditionalExpression
    | function                  # functionExpression
    | array                     # arrayExpression
    | tuple                     # tupleExpression
    | map                       # mapExpression
    | LPAR expression RPAR      # parenExpression
    ;

elementary
    : FALSE     # falseLiteral
    | TRUE      # trueLiteral
    | INT_LIT   # intLiteral
    | REAL_LIT  # realLiteral
    | RAT_LIT   # ratLiteral
    | COMP_LIT  # compLiteral
    | STR_LIT   # strLiteral
    | ID        # idLiteral
    ;


conditional
    : IF predicate=expression THEN thenExpr=expression ELSE elseExpr=expression END
    ;

// Statements
statement
    : assignment            # assignmentStatement
    | functionCall          # functionCallStatement
    | ifStatement           # if
    | loopStatement         # loop
    | returnStatement       # return
    | breakStatement        # break
    | printStatement        # print
    | declaration           # declarationStatement
    ;

functionCall
    : secondary LPAR (expression (COMMA expression)*)? RPAR
    ;

assignment
    : secondary ASSIGN expression
    ;

ifStatement
    : IF predicate=expression THEN thenStatements+=statement+ (ELSE elseStatements+=statement+)? END
    ;

loopStatement
    : loopHeader LOOP statement* END
    ;

loopHeader
    : FOR (ID IN)? expression (DOTDOT expression)?
    | WHILE expression
    ;

returnStatement
    : RETURN (expression)?
    ;

breakStatement
    : BREAK
    ;

printStatement
    : PRINT (expression (COMMA expression)*)?
    ;


// Functions and function types
functionType
    : FUNC LPAR (type (COMMA type)*)? RPAR COLUMN type
    ;

function
    : FUNC LPAR (parameter (COMMA parameter)*)? RPAR (COLUMN type)? body
    ;

parameter
    : ID COLUMN type
    ;

body
    : DO statement+ END
    | LINE_FUN expression
    ;

// Arrays
arrayType
    : LSQUARE type RSQUARE
    ;

array
    : LSQUARE expression (COMMA expression)* RSQUARE
    ;

// Maps
mapType
    : LCURLY type COLUMN type RCURLY
    ;

map
    : LCURLY pair (COMMA pair)* RCURLY
    ;

pair
    : expression COLUMN expression
    ;

// Tuples
tupleType
    : LPAR type (COMMA type)* RPAR
    ;

tuple
    : LPAR (tupleElement (COMMA tupleElement)*)? RPAR
    ;

tupleElement
    : (ID | INT_LIT) IS expression
    ;


// Atomic types
type
    : booleanType      # booleanT
    | integerType      # integerT
    | realType         # realT
    | rationalType     # rationalT
    | complexType      # complexT
    | stringType       # stringT
    | functionType     # functionT
    | tupleType        # tupleT
    | arrayType        # arrayT
    | mapType          # mapT
    ;

booleanType
    : BOOLEAN
    ;

integerType
    : INTEGER
    ;

realType
    : REAL
    ;

rationalType
    : RATIONAL
    ;

complexType
    : COMPLEX
    ;

stringType
    : STRING
    ;


