<h1><b>C-Minus Compiler</b></h1>

<p>The goal of this project is to create a fully functional C-Minus Compiler as listed in <a href="https://www.amazon.com/Compiler-Construction-Principles-Kenneth-Louden/dp/0534939724">Compiler Construction: Principles and Practice</a>
    C-Minus is essentially a subset of the C programming language, but is missing many of its componenets. The grammar for C-Minus can be found
    <a href="http://www.csci-snc.com/ExamplesX/C-Syntax.pdf">here</a>.
    
<h2>How it Works</h2>
<p>The front end of a compiler consists of four main parts:
     <ul>
      <li><b>Lexical Analyzer/Scanner:</b> finds the <a href="https://en.wikipedia.org/wiki/Lexical_analysis#Lexeme">lexemes</a> and <a href="https://en.wikipedia.org/wiki/Lexical_analysis#Token">tokens</a></li>
      <li><b>Syntax Analyzer/Parser:</b> checks the syntax of the file and ensures it follows the C-Minus grammar</li>
      <li><b>Semantic Analyzer:</b> ensures that the code written "makes sense" semantically</li> 
      <li><b>Intermediate Code Generation:</b> creates assembly-like code</li></ul>

By passing a file to this project through a command-line argument, the lexical analyzer begins by identifying and creating a list of all the tokens. This list will then be passed to the parser, which then
follows through the rules of the C-Minus grammar to check the syntax of the C-Minus code. If it passes the syntactical analysis, the compiler will then
check the semantics of the file. The semantic analyzer ensures that everything written in the code "makes sense".
<br>          For example:<br>
       <code>  void main(void){
                        y = 4 + z;
                        } </code>
