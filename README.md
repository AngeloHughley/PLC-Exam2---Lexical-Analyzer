# PLC-Exam2---Lexical-Analyzer
a. REGEX Rules

  Keywords = (num | void | whether | iter | public | class | method | main)+
  
  Arithmetic Operator =  ( + | - | *=  | = | += | -=)+
  
  Boolean Operator = ( <, >, <=, >=, ==, !=)+
  
  Identifiers: [a-z|A-Z]+
  
  Special Characters: ( (  ) | { } )+
  
  variable declaration = num [a-zA-Z]+       -> variable cannot have numbers in it
  
  Integer code: num var = [int literal]4_b   -> must have 4_b when declaring int literal
  
  Int literals = [0-9]+_4b
  
  Float literals = [0-9]+.[0-9]+_8b          -> must have 8_b when declaring float literal
  

b. EBNF Rules

  <program> → ‘public method main (void)’ <statement_list> 
  
  <statement_list> → <statement> { <statement> }
    
  <statement>  → <assignment_statement> | <if_statement> | <loop_statement> | <block>
    
  <block> → ‘{‘  { <statement> } ‘ }’
    
  <if_statement> → ‘whether’  ‘(‘ <boolean_expression> ‘)’ <statement> [‘else’ <statement>]
    
  <loop_statement> → ‘iter’ ‘(‘ <boolean_expression> ‘)’ <statement> 
    
  <assignment_statement> → ‘identifier’ ‘=’ <expression>
    
  <boolean_expression> → <term> {(‘==’ | ‘!=’ | ‘>=’ | ‘<=’ | ‘>’ | ‘<’) <term>} 
    
  <expression> → <term> { (‘+’ | ‘-’) <term>}
    
  <term> → <factor> { (‘*’ | ‘/’ | ‘%’) <factor> }
    
  <factor> → ‘identifier’ | ‘num_lit_4b’ | ‘(‘ <expression> ‘)’
    
  
  c. Conforming with Standard LL Grammar
    
  <block> statement must start with a ‘{‘
    
  <if_statement> must start with a ‘whether’
    
  <loop_statement> must start with a ‘iter’
    
  <assignment_statement> must start with a identifier = 
    
  <term> must start with a factor with a operator
    
  <factor> must start with a identifier or int literal with an expression then expression
    
  <expression> must start with a term and then an optional operator

  These rules passes the pairwise disjointness test
  Input strings are read from left to right 
    

    
  d. Is it ambiguous?
    
    This is a LL(1) Grammar, there is no form of ambiguity.
    
    
  h.
    
 
  ![LR Rules](https://user-images.githubusercontent.com/60718254/205714067-fd021cca-b34f-455a-af6c-dbbc69594cdb.png)
  ![LR correct1](https://user-images.githubusercontent.com/60718254/205714181-a3bfcfac-a03b-405e-a27b-a1727200e631.png)
  ![LR correct2](https://user-images.githubusercontent.com/60718254/205714286-e32b55d5-5006-4798-ba41-91c01b353edc.png)
  ![LR incorrect](https://user-images.githubusercontent.com/60718254/205714222-d6ca2cdb-9071-4bb9-b1d0-a0ff28fa0476.png)
  ![LR incorrect2](https://user-images.githubusercontent.com/60718254/205714400-895536b4-3c40-4f93-8f29-4edeae876f1a.png)




