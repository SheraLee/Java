// AST.java
// Classes for representing Minimath Abstract Syntax Tree.
// Original author: Christine ALvarado



import java.util.*;

/** Interface the specifies an Abstract Syntax Tree for 
  * a simple math language */
interface AST {
  
  /** Returns a string representaiton of the tree.
    * @return A string representation of the tree */
  public String   toString();
  
  /** Evaluate the tree
    * @param env The environment in which to do the evaluation
    * @return A double representing the value of the tree.
    * */
  public double eval(Map<String,Double> env);
}

// For full style points, we should have comments on all of 
// our functions below...

/** AST to represent a product */
class Product implements AST
{
  private AST left;  // Left of the *
  private AST right; // Right of the *

  /** Construct a new Product */
  public Product(AST l, AST r)
  {
    this.left = l;
    this.right = r;
  }
  
  public double eval(Map<String,Double> env)
  {
    return left.eval(env) * right.eval(env); 
  }  
  
  public String toString()
  {
    return ("Product(" + left + "," + right + ")");
  }  
}

class Quotient implements AST
{
  private AST left;
  private AST right;

  public Quotient(AST l, AST r)
  {
    this.left = l;
    this.right = r;
  }
  
  public double eval(Map<String,Double> env)
  {
    return left.eval(env) / right.eval(env); 
  }  
  
  public String toString()
  {
    return ("Quotient(" + left + "," + right + ")");
  }
}

class Sum implements AST
{
  private AST left;
  private AST right;

  public Sum(AST l, AST r)
  {
    this.left = l;
    this.right = r;
  }
  
  public double eval(Map<String,Double> env)
  {
    return left.eval(env) + right.eval(env);
  }  
  
  public String toString()
  {
    return ("Sum(" + left + "," + right + ")");
  }
}

class Difference implements AST
{
  private AST left;
  private AST right;

  public Difference(AST l, AST r)
  {
    this.left = l;
    this.right = r;
  }
  
  public double eval(Map<String,Double> env)
  {
    return left.eval(env) - right.eval(env);
  }  

  public String toString()
  {
    return ("Difference(" + left + "," + right + ")");
  }

}


class Constant implements AST
{
  private double quant;

  public Constant(double d)
  {
    this.quant = d;
  }
  
  public double eval(Map<String,Double> env)
  {
    return quant; 
  }  
  
  public String toString()
  {
    return ("Const(" + quant + ")");
  }
}

class Variable implements AST
{
  private String name;

  public Variable(String n)
  {
    this.name = n;
  }
  
  public double eval(Map<String,Double> env)
  {
    return env.get(name); 
  }  
  
  public String toString()
  {
    return ("Variable(" + name + ")");
  }
}

class Define implements AST
{
  private AST value;
  private String name;

  public Define(String n, AST v)
  {
    this.name = n;
    this.value = v;
  }
  
  public double eval(Map<String,Double> env)
  {
    double val = value.eval(env);
    env.put( name, val );
    return val; 
  }  
  
  public String toString()
  {
    return ("Define(" + name + ", " + value + ")");
  }
}

