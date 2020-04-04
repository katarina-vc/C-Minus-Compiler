/*
 * Name: Katarina Capalbo
 * Token.java Description: Token.java contains the basic information necessary to define each token as it is found by the Lexical Analyzer
 * 						   (LexicalAnalyzer.java). This token data is then utilized during the parsing and semantic analysis process to ensure
 * 						   the C- code follows the grammar and is semantically correct. 
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

// enum TokenValue
 enum TokenValue{
	KEYWORD,
	SYMBOL,
	ID,
	NUM,
	END
}// end Token
	
// start class
public class Token {

	// variable declarations
	private String tokenName;
	private TokenValue value;
	
	// default constructor
	public Token(){
	}
		
	// constructor
	public Token(TokenValue v, String name){
		this.value = v;
		this.tokenName = name;
	}
	
	public Token(TokenValue v, char name){
		this.value = v;
		this.tokenName = String.valueOf(name);
	}
	
	// set TokenValue
	public void setTokenValue(TokenValue v){
		this.value = v;
	}
	
	// return TokenValue
	public TokenValue getTokenValue() {
		return value;
	}
	
	// set TokenName
	public void setTokenName(String name) {
		this.tokenName = name;
	}
	
	// return TokenName
	public String getTokenName() {
		return tokenName;
	}
	
	 @Override
	   public String toString() {
	        return(this.getTokenName());
	   }
	 
}// end class
