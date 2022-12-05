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
	String string;
	public String keyword(String s, int i) {
		string = "Keyword: < " + s + " > " + "line: " + i;
		return string;
	}
	public String operator(String s, int i) {
		string = "Operator < " + s + " > " + "line: " + i;
		return string;
	}
	public String specialCharacter(String s, int i) {
		string = "Special Character < " + s + " > " + "line: " + i;	
		return string;
	}
	public String Literal(String s, int i) {
		string = "Literal < " + s + " > " + "line: " + i;
		return string;
		
	}
	public String Identifier(String s, int i) {
		string = "Identifier < " + s + " > " + "line: " + i;
		return string;
	}
	public String specialCharacterMissing(String s, int i) {
		string = "Syntax Error on line: " + i + " Missing  iter, whether, or method main";
		return string;
	}
	public String keywordMissing(String s, int i) {
		string = "Syntax Error on line: " + i + " Missing  iter or whether statement";	
		return string;
	}
	public String illegalCharacter(String s, int i) {
		string = "Lexical Error on line: " + i + " Illegal Character: " + s + " letters only";
		return string;
	}
	public String illegalCharLength(String s, int i) {
		string = "Lexical Error on line: " + i + " Character Length Max is: 7";
		return string;
	}
	//Method to check the amount of curly braces at the end of code
	public String curlyBraceCheck(int curlyBrace, int i) {
		if(curlyBrace > 0) {
			string = "Syntax Error: " + "on line: " + ++i + " Missing }";
			return string;
			
		} else if(curlyBrace < 0) {
			string = "Syntax Error: " + "on line: " + ++i + " Missing {";
			return string;
		}
		else {
			string = "Completed";
			return string;
		}
	}
	
}
public class Lexical {

	public static void main(String[] args) {
		File file = new File("C:\\Users\\Angelo\\eclipse-workspace\\PLC2\\src\\noError1.txt");
		ArrayList  <String> list = new ArrayList<>();
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
							list.add(a.keyword(eachWord[i], numberLine));
						
						 //Once we found a keyword, we need to use regex to find an identifier to take in letters. 
						 //So, make the keyword blank as we've already determined it
						
							eachWord[i] = ""; 
							a.errorChecker = true;
							break;
						case "+", "/", "*", "%", "-", "=", "*=":
							list.add(a.operator(eachWord[i], numberLine));
							a.errorChecker = true;
							break;	
						case "<", ">", "<=", ">=", "==", "!=":
							if(line.contains("iter") || line.contains("whether")) {
								list.add(a.operator(eachWord[i], numberLine));
								a.errorChecker = true;
							}
							else {
								list.add(a.keywordMissing(eachWord[i], numberLine));
								a.errorChecker = false;
							}
						case "{":
							if(line.contains("class")){
								list.add(a.specialCharacter(eachWord[i], numberLine));
								a.errorChecker = true;
								a.curlyBrace++;
							}
							else if(line.contains("whether")) {
								list.add(a.specialCharacter(eachWord[i], numberLine));
								a.errorChecker = true;
								a.curlyBrace++;
							}
							else if(line.contains("else")) {
								list.add(a.specialCharacter(eachWord[i], numberLine));
								a.errorChecker = true;
								a.curlyBrace++;
							}
							else if(line.contains("method") && line.contains("main") && line.contains("void") && line.contains("(") && line.contains(")")) {
								list.add(a.specialCharacter(eachWord[i], numberLine));
								a.errorChecker = true;
								a.curlyBrace++;
							}
							else if(line.contains("iter")) {
								list.add(a.specialCharacter(eachWord[i], numberLine));
								a.errorChecker = true;
								a.curlyBrace++;
							}
							else {
								list.add(a.specialCharacterMissing(eachWord[i], numberLine));
								a.errorChecker = false;							
							}
							break;
						case "}":
							list.add(a.specialCharacter(eachWord[i], numberLine));
							a.curlyBrace--;
							a.errorChecker = true;
						case "(":
							if(line.contains("whether")) {
								list.add(a.specialCharacter(eachWord[i], numberLine));
								a.openParenthesis++;
								a.errorChecker = true;
							
							}
							else if(line.contains("iter")) {
								list.add(a.specialCharacter(eachWord[i], numberLine));
								a.openParenthesis++;
								a.errorChecker = true;
								
							} 
							else if(line.contains("method") && line.contains("main") && line.contains("void")) {
								list.add(a.specialCharacter(eachWord[i], numberLine));
								a.openParenthesis++;
								a.errorChecker = true;			
						} 				
							break;
						case ")":
							if(line.contains("(")) {
								list.add(a.specialCharacter(eachWord[i], numberLine));
								a.openParenthesis--;
								a.errorChecker = true;
							} 
							else {
								String s;
								s = "Syntax Error Missing ( " + "on line: " + numberLine;
								list.add(s);
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
							list.add(a.Literal(eachWord[i], numberLine));
							a.errorChecker = true;
						}
						if(Pattern.matches("[0-9]+.[0-9]+_8b", eachWord[i])) {
							list.add(a.Literal(eachWord[i], numberLine));
							a.errorChecker = true;
							break;
						}
						
						if(Pattern.matches("[0-9]+", eachWord[i])) {  //No literal should have just the number, declare the size as well
							String s ="Lexical Error: Illegal Character: " + eachWord[i] + " on line: " + numberLine;
							list.add(s);
							a.errorChecker = false;
							break;
						}
						if(Pattern.matches("[a-zA-Z]+", eachWord[i]) && line.contains("num") && line.contains("=")) {
							if(eachWord[i].length() > 8) {
								list.add(a.illegalCharLength(eachWord[i], numberLine));
								a.errorChecker = false;
								break;
							}
							list.add(a.Identifier(eachWord[i], numberLine));
		
							a.errorChecker = true;
						}
						if(Pattern.matches("[0-9]+[a-zA-Z]*", eachWord[i]) && line.contains("num") && line.contains("=")) {
							list.add(a.illegalCharacter(eachWord[i], numberLine));
							a.errorChecker = false;
							break;
						}
						if(Pattern.matches("[a-zA-Z]+[0-9]+", eachWord[i]) && line.contains("num") && line.contains("=")) {
							list.add(a.illegalCharacter(eachWord[i], numberLine));
							a.errorChecker = false;
							break;
						}
					
						if(Pattern.matches("a-zA-Z]+", eachWord[i]) && line.contains("whether") && line.contains("(") && line.contains(")") && eachWord[i]!="num"){
							list.add(a.Identifier(eachWord[i], numberLine));
							a.errorChecker = true;
						}
						if(Pattern.matches("a-zA-Z]+", eachWord[i]) && line.contains("iter")){
							list.add(a.Identifier(eachWord[i], numberLine));
							a.errorChecker = true;
						}	
						if(Pattern.matches("[a-zA-Z]+", eachWord[i])) {
							list.add(a.Identifier(eachWord[i], numberLine));
							a.errorChecker = true;			
						}	
						if(Pattern.matches("[^a-zA-Z]", eachWord[i]) && Pattern.matches("^[0-9]", eachWord[i])) {
							list.add(a.illegalCharacter(eachWord[i], numberLine));
							a.errorChecker = false;			
						}	
						if(Pattern.matches("[@] | [#] | [$] | [~] | [`]", eachWord[i])) {
							String s = "Lexical Error: Illegal Character(s): " + eachWord[i] + " on line: " + numberLine;
							list.add(s);
							a.errorChecker = false;
						}
					}
						
				}		
			}
				
			list.add(a.curlyBraceCheck(a.curlyBrace, numberLine)); //Check for a even number of brackets. If not even, generate a syntax error in the last line
		}
		catch(Exception e) {
			System.out.println("File Not Found");
		}
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));  //printing each statement in list
		}
	}

}

