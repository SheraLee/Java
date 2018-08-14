/**
 *This is the JUint tester for the AST class
 * @author Xirui Li(CS12fhm,A13691309)
 * @version 1 December 2017
 */
package hw8;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class ASTTester {
	private Quantity defaultQt1 = new Quantity();
	private Quantity treeArgQt1 = new Quantity(10.0, Arrays.asList("m"), Arrays.asList("s", "s"));
	private Quantity treeArgQt2 = new Quantity(2.0, Arrays.asList("m"), Arrays.asList("s", "s"));
	AST testProduct; 
	AST testQuotient; 
	
    @Before
     /**
	  * set up ASTs.
	  */
	public void setup(){
    	testProduct = new Product(new Value(treeArgQt1), new Value(treeArgQt2));
	 }
    
    @Test
    /**
     *test product eval()
     */
    public void testProductEval(){
    	Product testProduct = new Product(new Value(treeArgQt1), new Value(treeArgQt2));	
    	Quantity ansGet = testProduct.eval(QuantityDB.getDB());
    	Quantity ansExp = new Quantity(20.0,Arrays.asList("m","m"), Arrays.asList("s", "s","s", "s"));
    	assertEquals("product treeArgQt1 and treeArgQt1", ansExp, ansGet);
    }
    
    @Test 
    /**
     *test Quotient eval()
     */
    public void testQuotientEval(){
    	Quotient testQuotient = new Quotient(new Value(treeArgQt1), new Value(treeArgQt2));	
    	Quantity ansGet = testQuotient.eval(QuantityDB.getDB());
    	Quantity ansExp = new Quantity(5.0,Arrays.asList(), Arrays.asList());
    	assertEquals("Quotient treeArgQt1 and treeArgQt2", ansExp, ansGet);
    }
    
    @Test
    /**
     * test power eval()
     */
    public void testPowerEval(){
    	Power testPower = new Power(new Value(treeArgQt2), 2);	
    	Quantity ansGet = testPower.eval(QuantityDB.getDB());
    	Quantity ansExp = new Quantity(4.0,Arrays.asList("m", "m"), Arrays.asList("s", "s","s", "s"));
    	assertEquals("Power treeArgQt2 to power2", ansExp, ansGet);
    }
    
    @Test
    /**
     * test sum eval()
     */
    public void testSumEval(){
    	Sum testSum = new Sum(new Value(treeArgQt1), new Value(treeArgQt2));	
    	Quantity ansGet = testSum.eval(QuantityDB.getDB());
    	Quantity ansExp = new Quantity(12.0,Arrays.asList("m"), Arrays.asList("s", "s"));
    	assertEquals("Power treeArgQt2 to power2", ansExp, ansGet);
    }
    
    @Test
    /**
     * test difference eval()
     */
    public void testDifferenceEval(){
    	Difference testDifference = new Difference(new Value(treeArgQt1), new Value(treeArgQt2));
    	Quantity ansGet = testDifference.eval(QuantityDB.getDB());
    	Quantity ansExp = new Quantity(8.0,Arrays.asList("m"), Arrays.asList("s", "s"));
    	assertEquals("Difference between ", ansExp, ansGet);
    }
    
    @Test
    /**
     * test negation eval()
     */
    public void testNegation(){
    	Negation testNegation = new Negation(new Value(treeArgQt1));
    	Quantity ansGet = testNegation.eval(QuantityDB.getDB());
    	Quantity ansExp = new Quantity(-10.0,Arrays.asList("m"), Arrays.asList("s", "s"));
    	assertEquals("Difference between ", ansExp, ansGet);
    }
    
    @Test
    /**
     * test Define eval
     */
    public void testDefine(){
    	Quantity scale = new Quantity(67.0, Arrays.asList(), Arrays.asList());
		Quantity original = new Quantity(1.0, Arrays.asList("in"), Arrays.asList());
    	AST valuePart = new Product(new Value(scale), new Value(original));
		AST testDefine = new Define("smoot",valuePart);
		Quantity ansGet = testDefine.eval(QuantityDB.getDB());
		Quantity ansExp = new Quantity(67.0,Arrays.asList("in"), Arrays.asList());
		assertEquals("define ", ansExp, ansGet);
    }
    
    @Test
    /**
     * test Value eval 
     */
    public void testValue(){
        Value testValue = new Value(treeArgQt1);
        Quantity ansGet = testValue.eval(QuantityDB.getDB());
        Quantity ansExp = treeArgQt1;
        assertEquals("value eval ", ansExp, ansGet);
    }
    
    @Test
    /**
     * test Normalize Eval
     */
    public void testNormalize(){
    	Quantity testUnit = new Quantity(1,Arrays.asList("km"), Arrays.asList());
    	Normalize testNormalize = new Normalize(new Value(testUnit));
    	Quantity ansGet = testNormalize.eval(QuantityDB.getDB());
    	Quantity ansExp = new Quantity(1000,Arrays.asList("meter"), Arrays.asList());
    	assertEquals("normalize eval ", ansExp, ansGet);
    	
    }
    
}

