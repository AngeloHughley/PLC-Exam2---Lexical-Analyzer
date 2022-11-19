# PLC-Exam2---Lexical-Analyzer
a. REGEX Rules

  Keywords = (num | void | determine | loop | public | class | method | main)+
 
  Arithmetic Operator =  ( + | - | *=  | = | += | -=)+
 
  Boolean Operator = ( <, >, <=, >=, ==, !=)+
  
  Identifiers: [a-z|A-Z]+
  
  Special Characters: ( (  ) | { } )+
  
  Int literals = [0-9]_4bytes
  
  Float literals = [0-9]+.[0-9]+_8bytes

b. EBNF Rules

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
  
  c. Conforming with Standard LL Grammar
    
  <block> statement must start with a ‘{‘
    
  <if_statement> must start with a ‘determine’
    
  <loop_statement> must start with a ‘loop’
    
  <assignment_statement> must start with a "identifier =" 
    
  <term> must start with a factor with a operator
    
  <factor> must start with a identifier or int literal with an expression then expression
    
  <expression> must start with a term and then an optional operator

  These rules passes the pairwise disjointness test
    
  Input strings are read from left to right 
    
  d. Is it ambiguous?
    This is a LL(1) Grammar, there is no form of ambiguity.
    
  h.
    
 
![unnamed](https://user-images.githubusercontent.com/60718254/202832874-1049179e-9c04-41bc-900e-899eceefb9d1.PNG)
![unnamed2](https://user-images.githubusercontent.com/60718254/202832882-b87f6a55-5d29-40dd-94e3-f5872f653e92.PNG)
![unnamed3](https://user-images.githubusercontent.com/60718254/202832889-e0052d7f-91f0-4070-b6d4-29e0b0fbf5b5.PNG)
![Capture](https://user-images.githubusercontent.com/60718254/202832779-b169a07c-7f01-4f87-ac8c-b90c94ac5bfe.PNG)

