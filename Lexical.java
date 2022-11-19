import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.*;
class Analyze{
	boolean errorChecker = true; //Error checker that keeps track of any syntax or lexical errors. If false, terminate the program
	int openBrackets = 0;
	int openParenthesis = 0;
	int curlyBrace = 0;
	public void keyword(String s, int i) {
		System.out.println("Keyword: < " + s + " > " + "line: " + i);
	}
	public void operator(String s, int i) {
		System.out.println("Operator < " + s + " > " + "line: " + i);
	}
	public void specialCharacter(String s, int i) {
		System.out.println("Special Character < " + s + " > " + "line: " + i);	
	}
	public void Literal(String s, int i) {
		System.out.println("Literal < " + s + " > " + "line: " + i);	
	}
	public void Identifier(String s, int i) {
		System.out.println("Identifier < " + s + " > " + "line: " + i);
	}
	public void specialCharacterMissing(String s, int i) {
		System.out.println("Syntax Error on line: " + i + " Missing  iter, whether, or method main");
	}
	public void keywordMissing(String s, int i) {
		System.out.println("Syntax Error on line: " + i + " Missing  iter or whether statement");	
	}
	public void illegalCharacter(String s, int i) {
		System.out.println("Lexical Error on line: " + i + " Illegal Character: " + s + " letters only" );
	}
	public void illegalCharLength(String s, int i) {
		System.out.println("Lexical Error on line: " + i + " Character Length Max is: 7");
	}
	//Method to check the amount of curly braces at the end of code
	public void curlyBraceCheck(int curlyBrace, int i) {
		if(curlyBrace > 0) {
			System.out.println("Syntax Error: " + "on line: " + ++i + " Missing }");
			
		} else if(curlyBrace < 0) {
			System.out.println("Syntax Error: " + "on line: " + ++i + " Missing {");
		}
		else {
			System.out.println("Completed");
		}
	}
	
}
public class Lexical {

	public static void main(String[] args) {
		File file = new File("C:\\Users\\Angelo\\eclipse-workspace\\PLC2\\src\\noError1.txt");
		HashSet <String> set = new HashSet<>();
		Analyze a = new Analyze();
		try {
			FileReader reader = new FileReader(file);
			Scanner scan = new Scanner(file);
			int numberLine = 0; //Used for keeping up with the line #
			while(scan.hasNextLine()) {
				String line = scan.nextLine();
				String [] eachWord = line.split(" "); 
				numberLine++;
				for(int i = 0; i < eachWord.length; i++) {
					if(a.errorChecker == false) break;	
					
					//Checking each possible words in each line
					
					if(!eachWord[i].isEmpty()) {
						switch(eachWord[i]) {
						case "num", "class", "String", "iter", "final", "static", "dec", "whether", "else", "void", "true", "false", "method", "public" :
							a.keyword(eachWord[i], numberLine);
						
						 //Once we found a keyword, we need to use regex to find an identifier to take in letters. 
						 //So, make the keyword blank as we've already determined it
						
							eachWord[i] = ""; 
							a.errorChecker = true;
							break;
						case "+", "/", "*", "%", "-", "=", "*=":
							a.operator(eachWord[i], numberLine);
							a.errorChecker = true;
							break;	
						case "<", ">", "<=", ">=", "==", "!=":
							if(line.contains("iter") || line.contains("whether")) {
								a.operator(eachWord[i], numberLine);
								a.errorChecker = true;
							}
							else {
								a.keywordMissing(eachWord[i], numberLine);
								a.errorChecker = false;
							}
						case "{":
							if(line.contains("class")){
								a.specialCharacter(eachWord[i], numberLine);
								a.errorChecker = true;
								a.curlyBrace++;
							}
							else if(line.contains("whether")) {
								a.specialCharacter(eachWord[i], numberLine);
								a.errorChecker = true;
								a.curlyBrace++;
							}
							else if(line.contains("else")) {
								a.specialCharacter(eachWord[i], numberLine);
								a.errorChecker = true;
								a.curlyBrace++;
							}
							else if(line.contains("method") && line.contains("main") && line.contains("void") && line.contains("(") && line.contains(")")) {
								a.specialCharacter(eachWord[i], numberLine);
								a.errorChecker = true;
								a.curlyBrace++;
							}
							else if(line.contains("iter")) {
								a.specialCharacter(eachWord[i], numberLine);
								a.errorChecker = true;
								a.curlyBrace++;
							}
							else {
								a.specialCharacterMissing(eachWord[i], numberLine);
								a.errorChecker = false;							
							}
							break;
						case "}":
							a.specialCharacter(eachWord[i], numberLine);
							a.curlyBrace--;
							a.errorChecker = true;
						case "(":
							if(line.contains("whether")) {
								a.specialCharacter(eachWord[i], numberLine);
								a.openParenthesis++;
								a.errorChecker = true;
							
							}
							else if(line.contains("iter")) {
								a.specialCharacter(eachWord[i], numberLine);
								a.openParenthesis++;
								a.errorChecker = true;
								
							} 
							else if(line.contains("method") && line.contains("main") && line.contains("void")) {
								a.specialCharacter(eachWord[i], numberLine);
								a.openParenthesis++;
								a.errorChecker = true;			
						} 				
							break;
						case ")":
							if(line.contains("(")) {
								a.specialCharacter(eachWord[i], numberLine);
								a.openParenthesis--;
								a.errorChecker = true;
							} 
							else {
								System.out.println("Syntax Error Missing ( " + "on line: " + numberLine);
								a.errorChecker = false;							
							}
							break;	
						case "//":    //Just for declaring comments
							continue;
					}					
					}
					
					if(a.errorChecker) {
						//Checking for all possible patterns in recognizing literals or identifiers
						
						if(Pattern.matches("[0-9]+_4b", eachWord[i])) {
							a.Literal(eachWord[i], numberLine);
							a.errorChecker = true;
						}
						if(Pattern.matches("[0-9]+.[0-9]+_8b", eachWord[i])) {
							a.Literal(eachWord[i], numberLine);
							a.errorChecker = true;
							break;
						}
						
						if(Pattern.matches("[0-9]+", eachWord[i])) {  //No literal should have just the number, declare the size as well
							System.out.println("Lexical Error: Illegal Character: " + eachWord[i] + " on line: " + numberLine);
							a.errorChecker = false;
							break;
						}
						if(Pattern.matches("[a-zA-Z]+", eachWord[i]) && line.contains("num") && line.contains("=")) {
							if(eachWord[i].length() > 8) {
								a.illegalCharLength(eachWord[i], numberLine);
								a.errorChecker = false;
								break;
							}
							a.Identifier(eachWord[i], numberLine);
							set.add(eachWord[i]);
							a.errorChecker = true;
						}
						if(Pattern.matches("[0-9]+[a-zA-Z]*", eachWord[i]) && line.contains("num") && line.contains("=")) {
							a.illegalCharacter(eachWord[i], numberLine);
							a.errorChecker = false;
							break;
						}
						if(Pattern.matches("[a-zA-Z]+[0-9]+", eachWord[i]) && line.contains("num") && line.contains("=")) {
							a.illegalCharacter(eachWord[i], numberLine);
							a.errorChecker = false;
							break;
						}
					
						if(Pattern.matches("a-zA-Z]+", eachWord[i]) && line.contains("whether") && line.contains("(") && line.contains(")") && eachWord[i]!="num"){
							a.Identifier(eachWord[i], numberLine);
							a.errorChecker = true;
						}
						if(Pattern.matches("a-zA-Z]+", eachWord[i]) && line.contains("iter") && set.contains(eachWord[i])){
							a.Identifier(eachWord[i], numberLine);
							a.errorChecker = true;
						}	
						if(Pattern.matches("[a-zA-Z]+", eachWord[i])) {
							a.Identifier(eachWord[i], numberLine);
							a.errorChecker = true;			
						}	
						if(Pattern.matches("[^a-zA-Z]", eachWord[i]) && Pattern.matches("^[0-9]", eachWord[i])) {
							a.illegalCharacter(eachWord[i], numberLine);
							a.errorChecker = false;			
						}	
						if(Pattern.matches("[@] | [#] | [$] | [~] | [`]", eachWord[i])) {
							System.out.println("Lexical Error: Illegal Character(s): " + eachWord[i] + " on line: " + numberLine);
							a.errorChecker = false;
						}
					}
						
				}		
			}
				
			a.curlyBraceCheck(a.curlyBrace, numberLine); //Check for a even number of brackets. If not even, generate a syntax error in the last line
		}
		catch(Exception e) {
			System.out.println("File Not Found");
		}
	}

}

