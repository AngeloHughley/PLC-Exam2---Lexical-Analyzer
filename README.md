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

  ![PLCb](https://user-images.githubusercontent.com/60718254/205716619-fc081e1c-61c4-4f5f-b155-7f6d0fc6cae4.png)
  
  
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




