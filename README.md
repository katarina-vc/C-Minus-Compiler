<h1 id="about"><b>C-Minus Compiler</b></h1>

<p>The goal of this project is to create a fully functional C-Minus Compiler as listed in <a href="https://www.amazon.com/Compiler-Construction-Principles-Kenneth-Louden/dp/0534939724">Compiler Construction: Principles and Practice</a>
    C-Minus is essentially a subset of the C programming language, but is missing many of its componenets. The grammar for C-Minus can be found
    <a href="http://www.csci-snc.com/ExamplesX/C-Syntax.pdf">here</a>.
    
<h1 id="toc">Table of Contents</h2>
<ul>
    <li><a href="#about">About</a></li>
    <li><a href="#howitworks">How it Works</a></li>
    <li><a href="#cminusexamples">C-Minus Code Examples</a></li>
    <li><a href="rejectedcode">Rejected Code Examples</a></li>
    <li><a href="sourcefiles">Source Files</a></li>
    <li><a href="runcode">How to Run the C-Minus</a></li>
</ul>
    
<h2 id="howitworks">How it Works</h2>
<p>The front end of a compiler consists of four main parts:
     <ul>
      <li><b>Lexical Analyzer/Scanner:</b> finds the <a href="https://en.wikipedia.org/wiki/Lexical_analysis#Lexeme">lexemes</a> and <a href="https://en.wikipedia.org/wiki/Lexical_analysis#Token">tokens</a></li>
      <li><b>Syntax Analyzer/Parser:</b> checks the syntax of the file and ensures it follows the C-Minus grammar</li>
      <li><b>Semantic Analyzer:</b> ensures that the code written "makes sense" semantically</li> 
      <li><b>Intermediate Code Generation:</b> creates assembly-like code</li></ul>

By passing a file to this project through a command-line argument, the lexical analyzer begins by identifying and creating a list of all the tokens. This list will then be passed to the parser, which then
follows through the rules of the C-Minus grammar to check the syntax of the C-Minus code. If it passes the syntactical analysis, the compiler will then
check the semantics of the file. The semantic analyzer ensures that everything written in the code "makes sense".</p>
<p><br>          For example:<br>
       <code>  void main(void){
                        y = 4 + z;
                        } </code></p><br>
is syntactically correct, but not semantically, as there is no declaration for the variable 'y' or 'z'.</p>
<p>Once our file has been checked for syntax and semantic correctness, the compiler will then generate quadruples to assist in intermediate code generation. This is the part that gives the compiler its functionality and ability to perform operations.</p>

<h2 id="cminusexamples">C-Minus Code Examples</h2>
<p>As listed in  <a href="https://www.amazon.com/Compiler-Construction-Principles-Kenneth-Louden/dp/0534939724">Compiler Construction: Principles and Practice</a>, here as an example of C- code that will compile: A program to perform Euclid's Algorithm to compute the greatest common denominator</p><br>
    

    int gcd(int u, int v){
    if(v == 0) return u;
    else return gcd(v,u-u/v*v);
    /* u-u/v*v == u mod v */
    }
    void main(void){
    int x; int y;
    x = input(); y = input();
    output(gcd(x,y));
    }

    
<h2 id="rejectedcode">Rejected Code Examples</h2>
<p>Using the same example as before, below is an example of code that would not compile using this C- compiler. Due to the semantics of C-, this example will not compile for two reasons: 
<ul>
    <li>All variable declarations inside of a function must be declared at the beginning of the function.</li>
    <li>Variables can not be declared and initialized at the same time</li>
</ul>
</p><br>

    int gcd(int u, int v){
    if(v == 0) return u;
    else return gcd(v,u-u/v*v);
    /* u-u/v*v == u mod v */
    }
    void main(void){
    int x = 0; 
    x = input(); y = input();
    output(gcd(x,y));
    int y;
    }
    
<h2 id="sourcefiles">Source Files</h2>
<b>This C- compiler project is currently in progress. Below you can find the source files for the Token class and Lexical Analyzer class.</b>
<ul>
    <li><a href="Token.java">Token.java</a></li>
    <li><a href="LexicalAnalyzer.java">LexicalAnalyzer.java</a></li>
</ul>
<h2 id="runcode">How to Run the C-Minus Compiler</h2>
<b>This C- compiler is currently in progress :).</b>
