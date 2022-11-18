# PLC-Exam2---Lexical-Analyzer
a. REGEX Rules

  Keywords = (num | void | determine | loop | public | class | method | main)+
 
  Arithmetic Operator =  ( + | - | *=  | = | += | -=)+
 
  Boolean Operator = ( <, >, <=, >=, ==, !=)+
  
  Identifiers: [a-z|A-Z]+
  
  Special Characters: ( (  ) | { } )+
  
  Int literals = [0-9]_4bytes
  
  Float literals = [0-9]+.[0-9]+_8bytes

b. EBNF

  <program> → ‘method main (void)’ <statement_list> 
  
  <statement_list> → <statement> { <statement> }
    
  <statement>  → <assignment_statement> | <if_statement> | <loop_statement> | <block>
    
  <block> → ‘{‘  { <statement> } ‘ }’
    
  <if_statement> → ‘whether’  ‘(‘ <boolean_expression> ‘)’ <statement> [‘else’ <statement>]
    
  <loop_statement> → ‘iter’ ‘(‘ <boolean_expression> ‘)’ <statement> 
    
  <assignment_statement> → ‘identifier’ ‘=’ <expression>
    
  <expression> → <term> { (‘+’ | ‘-’) <term>}
    
  <term> → <factor> { (‘*’ | ‘/’ | ‘%’) <factor> }
    
  <factor> → ‘identifier’ | ‘num_lit_4b’ | ‘(‘ <expression> ‘)’
  
  c. 
    
  <block> statement must start with a ‘{‘
    
  <if_statement> must start with a ‘determine’
    
  <loop_statement> must start with a ‘loop’
    
  <assignment_statement> must start with a identifier = 
    
  <term> must start with a factor with a operator
    
  <factor> must start with a identifier or int literal with an expression then expression
    
  <expression> must start with a term and then an optional operator

  These rules passes the pairwise disjointness test
    
  Input strings are read from left to right 

  
  
