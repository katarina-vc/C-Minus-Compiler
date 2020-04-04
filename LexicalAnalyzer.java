/*
 * Name: Katarina Capalbo
 * LexicalAnalyzer.java Description: This class lexically analyzes the file given to main() in Parser.java. The constructor accepts a 
 * Scanner object from main() and searches for the tokens as defined in Token.java. When a token is properly identified, it
 * adds them to an ArrayList of Token objects. The function getTokenList() returns the completed ArrayList.
 *
 * Date: 1/16/2020
 */

/*
 * Keywords in C-: else if int return void while
 * Symbols in C-: + - *  / < <= > >= == != = ; , ( ) [ ] { } */ /*
 * Identifiers (ID) are defined by: letterletter*
 * where letter = a|..|z|A|..|Z
 * 
 * Numbers (NUM) are defined by: digitdigit*
 * where digit = 0|..|9
 * 
 * White space consists of blanks, newlines, and tabs. White space is ignored
 * except that it must seperate ID's NUM's and keywords.
 * 
 * Comments are defined by the usual C notations, and can be placed anywhere 
 * white space can appear. Comments may not be nested.
 */

import java.util.regex.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LexicalAnalyzer {
	
	static ArrayList<Token> tokens = new ArrayList<Token>(); // list of tokens to pass to parser
	
	// default constructor
	public LexicalAnalyzer() {
	}
	
	// lexically analyze the file passed in 
	public LexicalAnalyzer(Scanner input) {
		// string to hold the temporary words to build tokens
	    String letWord = "";
	    String numWord = "";
	    String symWord = "";
	    String errWord = "";
		
	    // declarations
	    String inputLine; // holds each line in the file individually
		String testChar; // holds char to test
	    
	
		String[] symbolPairs = {"<=", ">=", "==","/", "//*/", "//*/", "!="}; // list of symbol pairs in C-
		ArrayList<String> symbolList = new ArrayList<String>(); // list to hold symbols for detecting symbol pairs
		
		// flags 
		boolean lineCommentFlag = false;
		boolean blockCommentFlag = false;
		boolean errorFlag = false;
    	boolean symFlag = false;
    			
    			
		// while loop will read through and print each line in the file
		while(input.hasNextLine()){
			
		    inputLine = input.nextLine(); // store file line into string inputLine
		    //System.out.printf("INPUT: %s\n", inputLine); // output each file line
		    String[] split = inputLine.split("\\s+"); // split all the words up into a word array
		   
		    // reset temporary words at the beginning of each new line
		    letWord = ""; 
		    numWord = "";
		    symWord = "";
		    errWord = "";
		    
		    // set lineCommentFlag to false for each new line
		    lineCommentFlag = false;
		    
		    // this for loop will loop for the amount of "words" in each line of the file
		    for(int i = 0; i < split.length; i++) {
		    	// set flags to false for each new word
		         errorFlag = false;
		    	 symFlag = false;
		    	
		    	// this for loop will loop for the amount of chars in each word in the file
		    	for(int j = 0; j < split[i].length(); j++) {
		    		
		    		// set testChar 
		    		testChar = String.valueOf(split[i].charAt(j));	
		    	 
		      /****** TEST FOR LEGAL CHARACTERS, ELSE ITS AN ERROR************************************************/
		    	if(legalCharacter(testChar) && errorFlag == false){
		    		//System.out.println("testChar: " + testChar);
		    		//System.out.println("flag: " + blockCommentFlag);
		      /****** TEST FOR COMMENTS ***************************************************************************/
		    		
		    		// detect for first "/"
		    		if(testChar.matches("/")  && (!lineCommentFlag) && (!blockCommentFlag)){
		    			// check for the next char to determine line or block comment
		    			if(j < split[i].length() - 1) {
		    				j++;
	    					testChar = String.valueOf(split[i].charAt(j));
	    					switch(testChar) {
	    					case "/": // line comment
	    						
	    						lineCommentFlag = true;
	    						break;
	    					case"*": // block comment
	    						blockCommentFlag = true;
	    						break;
	    					}
	    					
	    					if((!lineCommentFlag) && (!blockCommentFlag)  ) {
    							j--;
    							testChar = String.valueOf(split[i].charAt(j));
    						}else if(blockCommentFlag) {
    							break;
    						}
		    			}
		    		}// end detectComment if
		    		
		    		// search for closing block comment "*/"
		    		if(blockCommentFlag == true && testChar.matches("\\*") && (j < split[i].length() - 1)) {
			    				j++;
		    					testChar = String.valueOf(split[i].charAt(j));

		    					if((testChar.matches("/")) && (j < split[i].length())){
		    							blockCommentFlag = false;

		    							if(j < split[i].length() - 1) {
		    			    				j++;
		    		    					testChar = String.valueOf(split[i].charAt(j));
		    							}else {
		    								break;
		    							}
		    					}else {
		    						j--;
		    						testChar = String.valueOf(split[i].charAt(j));
		    					}
		    		}// end blockComment if
		    		
		    		
		      /****** TEST FOR SYMBOLS ***************************************************************************/
	    			if(testChar.matches("[=+-/*///<>!;,(){}\\[\\]]") && (!lineCommentFlag) && (!blockCommentFlag)) {
	    				
	    				// while symbol, loop and add to word, search for symbol pairs
	    				while((testChar.matches("[=+-/*///<>!;,(){}\\[\\]]")) &&  (!Character.isWhitespace(testChar.charAt(0)))){
	    					symWord += testChar;
	    					
	    					// check for symbol pairs
		    				if(symWord.length() == 2) {
		    					symFlag = false;
		    					for(int k = 0; k < symbolPairs.length; k++) {
		    						if(symWord.matches(symbolPairs[k])) {
			    						symbolList.add(symWord);
			    						
			    						symWord = "";
			    						symFlag = true;
		    						}
		    					}
		    					if(!symFlag) {
		    						
		    						//check for lone !
		    						if(String.valueOf(symWord.charAt(0)).matches("!")) {
		    							errorFlag = true;
		    							j--;
		    							break;
		    						}else {
		    							symbolList.add(String.valueOf(symWord.charAt(0)));
		    						}	
		    						symWord = String.valueOf(symWord.charAt(1));
		    					}
		    				}
		    				
		    				if(j < split[i].length()-1) {
		    					j++;
		    					testChar = String.valueOf(split[i].charAt(j));
		    					if(!(legalCharacter(testChar))){
		    						errorFlag = true;
		    						errWord += symWord;
		    					}
		    				}
		    				else {
		    					break;
		    				}
	    				}// end while loop
	    				
	    				// ADD SYMBOL TOKEN
	    				if(!symbolList.isEmpty()){
	    					for(int l = 0; l < symbolList.size(); l++) {
	    						//System.out.println(symbolList.get(l));
	    						// !!! added token to token list
	    						Token tkn = new Token(TokenValue.SYMBOL, symbolList.get(l));
	    						tokens.add(tkn);
	    					}
	    					if(symbols(symWord)) {
	    						
	    						// check for lone "!"
	    						if(symWord.matches("!")) {
	    							errorFlag = true;
	    							errWord += symWord;
	    						}else{
	    							//System.out.println(symWord);
	    							// !!! added token to token list
	    							Token tkn = new Token(TokenValue.SYMBOL, symWord);
		    						tokens.add(tkn);
	    						}
	    					}
	    				}
	    				else if(symbols(symWord)) {
	    					
	    					// check for lone "!"
	    					if(symWord.matches("!")) {
    							errorFlag = true;
    							errWord += symWord;
    						}else{
    							//System.out.println(symWord);
    							Token tkn = new Token(TokenValue.SYMBOL, symWord);
	    						tokens.add(tkn);
    						}
	    				}
	    				
	    				symbolList.clear();
	    				symWord = "";
	    			}// end symbol if
	    			
		          /****** TEST FOR CHARACTERS ************************************************************/
		    		if(Character.isLetter(testChar.charAt(0)) && (!lineCommentFlag) && (!blockCommentFlag)) {
		    			
		    			while(Character.isLetter(testChar.charAt(0)) && (!Character.isWhitespace(testChar.charAt(0))) ){
		    				letWord += testChar;
		    				if(j < split[i].length()-1) {
		    					j++;
		    					testChar = String.valueOf(split[i].charAt(j));
		    					if(!(legalCharacter(testChar))){
		    						errorFlag = true;
		    						j--;
		    						break;
		    					}
		    					if(symbols(testChar)) {
		    						j--; // decrement so it will catch this char on the next big char loop
		    					}
		    				}else {
		    					break;
		    				}
		    			}// end while

		    			// ADD KEYWORD OR ID TOKEN
						if(keyword(letWord)) {
							Token tkn = new Token(TokenValue.KEYWORD, letWord);
    						tokens.add(tkn); 
						}// if not keyword check for identifier
						else if(identifier(letWord)){ // its in identifier
							Token tkn = new Token(TokenValue.ID, letWord);
    						tokens.add(tkn); 
						}
						
						letWord = "";
		    		}// end if
		    			
		    			
		    			
		         /****** TEST FOR DIGITS ************************************************************/
		    			if(Character.isDigit(testChar.charAt(0)) && (!lineCommentFlag) &&(!blockCommentFlag)){
		    				
		    				while((Character.isDigit(testChar.charAt(0)) && (!Character.isWhitespace(testChar.charAt(0))) )){
		    					numWord += testChar;
		    					
			    				if(j < split[i].length()-1) {
			    					j++;
			    					testChar = String.valueOf(split[i].charAt(j));
			    					if(!(legalCharacter(testChar))){
			    						errorFlag = true;
			    						j--;
			    						break;
			    					}
			    					if(symbols(testChar) || Character.isLetter(testChar.charAt(0))) {
			    						j--; // decrement so it will catch this char on the next big char loop
			    					}
			    				}else {
			    					break;
			    				}
			    			}// end while
		    
		    				// ADD NUM TOKEN
		    				if(number(numWord)) {
		    					Token tkn = new Token(TokenValue.NUM, numWord);
	    						tokens.add(tkn); 
		    					numWord = "";
		    				}	
		    			}// end num if

		    			
		    	
		    			
		    	}/***** TEST FOR ERRORS ****************************************************************/
		    	else if((!lineCommentFlag) && (!blockCommentFlag)){ 
		    		errorFlag = true;
		    		errWord = errWord + testChar;
		    	}
		    	
		    	}// end for testChar loop
		    	
				/******* OUTPUT ERRORS *****************************************************************/
				if(errorFlag == true){
					//System.out.printf("ERROR: %s\n", errWord);
				}

				// reset the words
			    letWord = ""; 
			    numWord = "";
			    symWord = "";
			    errWord = "";
		    }// end for loop
		}// end while loop	   
	}// end constructor
	
/***** FUNCTIONS ******************************************************************************/
	

	// returns true if the character is legal within C-
	public static boolean legalCharacter(String c){
		return (c.matches("[a-zA-Z]|[0-9]|[=+-/*/////<>!;,(){}\\[\\]\\s+]|\\[|\\]"));
	}// end legalCharacter()
	
	
	
	// returns true if the word is a keyword and outputs
	public static boolean keyword(String word){
		// list of C- keywords
		String[] keywordArray = new String[]{"else", "if", "int", "return", "void", "while" };
		
		for(int i = 0; i < keywordArray.length; i++){
			if(word.matches(keywordArray[i])) {
				//System.out.printf("KW: %s\n", word);
				
				return true;
			}
		}
		
		return false;
	}// end keyword()
	
	// returns true if the word is an identifier
	public static boolean identifier(String word) {
		if(word.matches("[a-zA-Z]+"))
		{
			//System.out.printf("ID: %s\n", word);
			return true;
		}
		
		return false;
	}// end identifier()

	// returns true if the word is a number/digit
	public static boolean number(String word) {
		if(word.matches("[0-9]+")){
			//System.out.printf("INT: %s\n", word);
			return true;
		}
		
		return false;
	}
	
	// returns true if the word is a symbol
	public static boolean symbols(String word) {
		if(word.matches("[=+-/*///<>!;,(){}\\[\\]]+")) {
			return true;
		}
		return false;
	}// end symbols()
	
	public ArrayList<Token> getTokenList(){
		return tokens;
	}
	
}// end class
