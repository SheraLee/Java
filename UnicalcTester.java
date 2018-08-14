/**
 *This is the JUint tester for the Unicalc class
 * @author Xirui Li(CS12fhm,A13691309)
 * @version 1 December 2017
 */
package hw8;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UnicalcTester {
	
	private Unicalc testUnicalc;
	
	@Before
	/**
	 * set up the default Unicalc.
	 */
	public void setup(){
	   testUnicalc = new Unicalc();
	 }
	
	@Test
	/**
	 * test S define
	 */
	public void testSdef(){
		String input = "def smoot 67 in";
		testUnicalc.tokenize(input);
		
		AST ansGet = testUnicalc.S();
		
		List<String> emptyList = new ArrayList<String>();
		
		Quantity scale = new Quantity(67.0, emptyList, emptyList);
		Quantity original = new Quantity(1.0, Arrays.asList("in"), emptyList);
		
		AST valuePart = new Product(new Value(scale), new Value(original));
		AST ansExp = new Define("smoot",valuePart);
		
		assertEquals("1 smooth = 67 in is correctly defined", ansExp, ansGet);
	}
	
	@Test
	/**
	 * test L normalize
	 */
	public void testNormalize(){
		String input = "# 364.4 smoot";
		testUnicalc.tokenize(input);
		
		AST ansGet = testUnicalc.L();
		
		List<String> emptyList = new ArrayList<String>();
		
		Quantity value = new Quantity(364.4, emptyList, emptyList);
		Quantity units = new Quantity(1.0, Arrays.asList("smoot"), emptyList);
		
		AST toNormalize = new Product(new Value(value), new Value(units));
		AST ansExp = new Normalize(toNormalize);
		
		assertEquals("364.3 smoot is correctly normalized", ansExp, ansGet);
	}
	
	@Test
	/**
	 * test E sum
	 */
	public void testSum(){
		String input = "14m+9m";
		testUnicalc.tokenize(input);
		
		AST ansGet = testUnicalc.E();
		
		List<String> emptyList = new ArrayList<String>();
		
		Quantity value1 = new Quantity(14, emptyList, emptyList);
		Quantity units1 = new Quantity(1.0, Arrays.asList("m"), emptyList);
		
		Quantity value2 = new Quantity(9, emptyList, emptyList);
		Quantity units2 = new Quantity(1.0, Arrays.asList("m"), emptyList);

		AST toAdd1 = new Product(new Value(value1), new Value(units1));
		AST toAdd2 = new Product(new Value(value2), new Value(units2));
		
		AST ansExp = new Sum(toAdd1,toAdd2);
		
		assertEquals("364.3 smoot is correctly normalized", ansExp, ansGet);
	}
	
	@Test
	/**
	 * test E sum
	 */
	public void testSumNoPlus(){
		String input = "14";
		testUnicalc.tokenize(input);
		
		AST ansGet = testUnicalc.E();
		
		List<String> emptyList = new ArrayList<String>();
		
		Quantity value1 = new Quantity(14, emptyList, emptyList);
			
		AST ansExp = new Value(value1);
		
		assertEquals("364.3 smoot is correctly normalized", ansExp, ansGet);
	}
	@Test
	/**
	 * test P product
	 */
	public void testProduct(){
		String input = "60 Hz * 30s";
		testUnicalc.tokenize(input);
		
		AST ansGet = testUnicalc.E();
		
		List<String> emptyList = new ArrayList<String>();
		
		Quantity value1 = new Quantity(60, emptyList, emptyList);
		Quantity units1 = new Quantity(1.0, Arrays.asList("Hz"), emptyList);
		
		Quantity value2 = new Quantity(30, emptyList, emptyList);
		Quantity units2 = new Quantity(1.0, Arrays.asList("s"), emptyList);

		AST toProduct1 = new Product(new Value(value1), new Value(units1));
		AST toProduct2 = new Product(new Value(value2), new Value(units2));
		
		AST ansExp = new Product(toProduct1,toProduct2);
		
		assertEquals("60 Hz * 30s is correctly producted", ansExp, ansGet);		
	}
	
	@Test
	/**
	 * test K negation
	 */
	public void testKnegation(){
		String input = "- 67";
		testUnicalc.tokenize(input);
		
		AST ansGet = testUnicalc.K();	
		
		Quantity original = new Quantity(67, Arrays.asList(), Arrays.asList());
		AST ansExp = new Negation(new Value(original)) ;
		
		assertEquals("-67", ansExp, ansGet);	
	}
	
	@Test
	/**
	 * test Q product
	 */
	public void testQproduct(){
		String input = "60 Hz";
		testUnicalc.tokenize(input);
		
		List<String> emptyList = new ArrayList<String>();
		
		AST ansGet = testUnicalc.Q();	
		
		Quantity value1 = new Quantity(60, emptyList, emptyList);
		Quantity units1 = new Quantity(1.0, Arrays.asList("Hz"), emptyList);
		AST ansExp = new Product(new Value(value1), new Value(units1));
		
		assertEquals("60 Hz", ansExp, ansGet);
	}
	
	@Test
	/**
	 * test R power
	 */
	public void testRpower(){
		String input = "60 ^ 2";
		testUnicalc.tokenize(input);
		
		AST ansGet = testUnicalc.R();	
		
		Quantity original = new Quantity(60, Arrays.asList(), Arrays.asList());
		AST ansExp = new Power(new Value(original), 2) ;
		
		assertEquals("60 2", ansExp, ansGet);
	}
}
