/** Class: Minimath
  * An example on which to build Unicalc parser and interpreter
  * @author Christine Alvarado
  * @date May 18. 2014
  * */

import java.util.*;        // Scanner, LinkedList, ...
import java.util.regex.*;  // Pattern, Matcher, ...

class Minimath
{

  private LinkedList<String> toks;  // Used by the parser functions
                                    //    to keep track of the remaining tokens.
                                    // Initialized by tokenize(...)
                                    // Parsing functions remove the tokens
  
  public Minimath() 
  { 
     // Nothing we need to do in the constructor.
  }

  // method tokenize
  //   Takes a string, and sets this.toks to be a linked 
  //     list of all Unicalc tokens in this string
  //     (ignoring whitespace, and other things that can't
  //     be part of a token.)
  //  
  public void tokenize(String input)
  {
    this.toks = new LinkedList<String>( Tokenizer.tokenize( input ) );
    
    System.out.println("Tokens: " + this.toks);
    
    return;
  }
  
  // Minimath's grammar:
    // <A> := <B> | <S>
    //<B> := <ident> = <A>
    // <ident> := w | x | y | z  
    //  S := <P>+<S> | <P>-<S> | <P>
    //   P -> <M>*<P> | <M>/<P> | <M>
    // M -> <const> | (<S>) 
    // <const> := 0 | 1 | ... | 9
  
  
  public AST S() 
  {
    //  S := <P>+<S> | <P>-<S> | <P>
    // The tokens are in toks

    // There is a class called Sum and a class call Difference 
    // that implement the interface AST.
    // Both constructors take two ASTs as arguments.
    
    // let's imagine toks was ["2", "+", "3"] here
    AST resultSoFar = P();
    
    // so now toks would be ["+", "3"]
    if (toks.size() == 0) {
        return resultSoFar;
    }
    if ("+".equals(toks.peek())) {
        toks.pop();
        AST newResult = new Sum(resultSoFar, S());
        return newResult;
    }
    else if ("-".equals(toks.peek())) {
        toks.pop();
        AST newResult = new Difference(resultSoFar, S());
        return newResult;
        
    }
    else {
        return resultSoFar;
    }
    
    
  }
  
  // method parse
  // Tries to parse the contents of this.toks
  //    using recursive descent, starting with the start symbol.
  // output: an abstract syntax tree representing the input
  //
  public AST parse()
  {
    // Begin parsing with the start symbol
    AST answer = this.A(); 
    
    // Display results  
    System.out.println("AST: " + answer);
    if (! toks.isEmpty()) {
      System.out.println("Extra tokens left over: " + toks);
    }
    System.out.println();
      
    return answer;
  }
  
  public AST A()
  {
    // <A> := <B> | <S>
    // Sneak and look for a B, which starts with a letter and then an equals
    // This is a little bit cheating, but makes our life easier
    if ( toks.size() > 1 && "=".equals( toks.get(1) ) ) {
      // It's a B
      return B();
    }
    else {
      return S();
    }
  }
    
  public AST B()
  {
    //<B> := <ident> = <A>
    String name = ident();
    String next = toks.peek();
    if ( !"=".equals( next ) ) {
      throw new ParseError( "Expected = but found " + next );
    }
    toks.pop();  // pop the =
    AST a = A();
    return new Define( name, a );
  }
  
  // This one is different and returns a String so that we can use it to 
  // define variables as well as evaluate them.
  public String ident()
  {
    // Legal variables are w, x, y and z
    String next = toks.peek();
    if ( next.equals( "w" ) || next.equals("x") || next.equals("y") ||
        next.equals( "z" ) ) {
      toks.pop();
      return next;
    }
    else throw new ParseError( next + " is not a legal variable name" );
  }
  
  /** Parse an S expression.
    * @return An AST representing this S expression
    * @throws ParseError if the expression cannot be parsed as an S
    * */
  public AST S2()
  {
    //  S := <P>+<S> | <P>-<S> | <P>

    AST p = P();  
    String next = toks.peek();
    if ( next == null ) {
      return p;
    }
    else if ( next.equals( "+" ) ) {
      toks.pop();
      return new Sum( p, S() );
    }
    else if ( next.equals( "-" ) ) {
      toks.pop();
      return new Difference( p, S() );
    }
    else {
      return p;
    }
  }

  
  /** Parse a P expression.
    * @return An AST representing this P expression
    * @throws ParseError if the expression cannot be parsed as a P
    * */  
  public AST P()
  {
    //   P -> <M>*<P> | <M>/<P> | <M>
    
    AST m = M();
    
    String next = toks.peek();
    if ( next == null ) {
      return m;
    }
    else if ( next.equals( "*" ) ) {
      toks.pop();
      return new Product( m, P() );
    }
    else if ( next.equals( "/" ) ) {
      toks.pop();
      return new Quotient( m, P() );
    }
    else {
      return m;
    }
      
  }
    
    
  /** Parse a M expression.
    * @return An AST representing this M expression
    * @throws ParseError if the expression cannot be parsed as a M
    * */
  public AST M()
  {
    // M -> <const> | (<S>) 
    AST tree = null;
    String next = toks.peek();
    // Look to see if we have a (
    if ( "(".equals( next ) ) {
      toks.pop();
      tree = S();
      next = toks.peek();
      if ( !")".equals( next ) ) {
        throw new ParseError( "Expected ) but found " + next );
      }
      toks.pop();
    }
    else if ( isNumber( next ) ) {  // We know it's a C (or better be)
      tree = C();
    }
    else if ( isAlphabetic( next ) ) {
      String n = ident();
      tree = new Variable( n );
    }
    else {
      throw new ParseError( "Expected (, number or variable name, but found " + next );
    }
    
    return tree;
  }

  // private method isNumber
  //   Checks that the given string is non-null
  //      and could be converted to a double
  //      (via the Double.parseDouble function)
  //      without generating an error
  //
  private static boolean isNumber(String s)
  {
    if (s == null) return false;
    
    try { 
      Double.parseDouble(s);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }
  
  // private method isAlphabetic
  //   Checks that the given string is non-null
  //      and consists of (ASCII) letters or underscores
  //      (but not digits).
  //
  private static boolean isAlphabetic(String s)
  {
    return s != null && s.matches("[a-zA-Z_]+");
  }
  
  /** Parse a <const> expression (a number).
    * @return An AST representing this number
    * @throws ParseError if the expression cannot be parsed as a <const>
    * */
  public AST C()
  {
    // <const> := 0 | 1 | ... | 9
    // Actually we're a little liberal here, accepting any positive int
    
    String next = toks.peek();
    Constant toReturn = null;
    try {
      toReturn = new Constant( Integer.parseInt(next) );
    } catch (NumberFormatException e) {
      throw new ParseError("Expected an integer, but found: '" + next + "'");
    }
    toks.pop();
    return toReturn;
  }
    
  /////////////////////////////////////////////////////////////////////////////////////
  
  /** method main
   * inputs: the usual
   * outputs: none, but this method has the Minimath read-eval-print loop
   */   
  public static void main(String[] args)
  {
    Scanner console = new Scanner(System.in);
    Minimath minimath = new Minimath();

    Map<String,Double> env = new HashMap<String, Double>();

    while (true)
    {
      System.out.print("input>  ");
      String input = console.nextLine();
      
      if (input.equals("")) break;  // Quit if no input
      
      minimath.tokenize(input);
      
      try {
        AST ast = minimath.parse();
        System.out.println("Result: " + ast.eval(env) + "\n");
      } catch (ParseError e) {
        System.err.println( "Parse Error: " + e.getMessage() + "\n");
      }

    }
  }

}      

// ParseError class
//    A new kind of exception to be thrown if there is
//    an error in parsing

class ParseError extends RuntimeException {
   public ParseError(String message) {
         // Create a ParseError object containing
         // the given message as its error message.
      super(message);
   }
}

