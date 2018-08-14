/**
 *This is the JUint tester for the Quantity class
 * @author Xirui Li(CS12fhm,A13691309)
 * @version 1 December 2017
 */
package hw8;

import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class QuantityTester {
	private Quantity defaultQt1;
	private Quantity deepCopyQt1;
	private Quantity deepCopyQt2;
	private Quantity treeArgQt1;
	private Quantity treeArgQt2;
	private Quantity zeroTestQt;
	
	
	 @Before
	 /**
	  * set up tree quantity by tree constructor.
	  */
	 public void setup(){
		defaultQt1 = new Quantity();
		deepCopyQt1 = new Quantity(defaultQt1);
		treeArgQt1 = new Quantity(10.0, Arrays.asList("m"), Arrays.asList("s", "s"));
		treeArgQt2 = new Quantity(2.0, Arrays.asList("m"), Arrays.asList("s", "s"));
		zeroTestQt = new Quantity(0.0, Arrays.asList("m"), Arrays.asList("s", "s"));
		deepCopyQt2 = new Quantity(treeArgQt1);
	 }
	 
	 @Test
	 /**
	  * test the defaultConstructor
	  */
	 public void testDefaultConstructor(){
         String defaultStr1 = "1.0";
         assertEquals("defaultQt1 was correctly constructed",defaultStr1, defaultQt1.toString());     		 
	 }
	 
	 @Test
	 /**
	  * test the deep copy constructor
	  */
	 public void testDeepCopy(){
		 assertEquals("defaultQt1 was correctly coppied", defaultQt1, deepCopyQt1);
		 assertEquals("treeArgQt1 was correctly coppied", treeArgQt1, deepCopyQt2);
	 }

	 @Test
	 /**
	  * test the treeArgumentConstructor
	  */
	 public void testTreeArgumentConstructor(){
		 String treeArgQt1Str1 = "10.0 m s^-2";
		 assertEquals("treeArgQt1 was correctly constructed", treeArgQt1Str1, treeArgQt1.toString());
		 
		 String  treeArgQt1Str2 = "2.0 m s^-2";
		 assertEquals("treeArgQt2 was correctly constructed", treeArgQt1Str2, treeArgQt2.toString());
		 
		assertEquals( "1000.0 meter",new Quantity(1000, Arrays.asList("meter"),Arrays.asList()).toString());
	 }
	 
	 @Test
	 /**
	  * test the method mul()
	  */
	 public void testMul(){
		 String ansExp1 = "1.0";
		 assertEquals("defaultQt1 * default1Qt corretly", ansExp1, defaultQt1.mul(defaultQt1).toString());
		 
		 String ansExp2 = "10.0 m s^-2";
		 assertEquals("defaultQt1 * treeArgQt1 corretly", ansExp2, defaultQt1.mul(treeArgQt1).toString());
	 }

    @Test
    /**
     * test the method div when there is no IllegalArgumentException
     */
     public void testDiv(){
    	
    	assertEquals("treeArgQt1/defaultQt1 is corret", treeArgQt1,treeArgQt1.div(defaultQt1));
    	
    	String ansExp1 = "1.0";
    	assertEquals("treeArgQt1/treeArgQt1 is corret", ansExp1,treeArgQt1.div(treeArgQt1).toString()); 
    	
    	String ansExp2 = "5.0";
    	assertEquals("treeArgQt1/treeArgQt2 is corret", ansExp2,treeArgQt1.div(treeArgQt2).toString());
    	
    }
    
    @Test
    /**
     * test the IllegalArgumentException of method div when the value in the Quantity argument is zero
     */
    public void testDivZeroIllegalArgumentException(){
    	try 
		{   treeArgQt1.div(zeroTestQt);
    		fail("Should have generated an exception");
		}
		catch(IllegalArgumentException e) {
			//normal
		}
    }
    
    @Test
    /**
     * test the IllegalArgumentException of method div when its argument is null
     */
    public void testDivNullIllegalArgumentException(){
    	try 
		{   treeArgQt1.div(null);
    		fail("Should have generated an exception");
		}
		catch(IllegalArgumentException e) {
			//normal
		}
    }
    
    @Test
    /**
     * test the method pow
     */
    public void testPow(){
    	assertEquals("defaultQt.pow(2) is corret", defaultQt1,defaultQt1.pow(2));
    	
    	String ansExp = "100.0 m^2 s^-4";
    	assertEquals("treeArgQ1t.pow(2) is corret", ansExp,treeArgQt1.pow(2).toString());
    }
    
    @Test
    /**
     * test the the method add() when there is no llegalArgumentExceptoinits
     */
    public void testadd(){
    	String ansExp1 = "12.0 m s^-2" ;
    	assertEquals("treeArgQ1t + treeArgQt2 is corret", ansExp1,treeArgQt1.add(treeArgQt2).toString());
    	
    	String ansExp2 = "2.0";
    	assertEquals("defaultQt1 + defaultQt1 is corret", ansExp2,defaultQt1.add(defaultQt1).toString());
    }
    
    @Test 
    /**
     * test the add's illegalArgumentExceptoinits when argument is null 
     */
    public void testAddNullIllegalArgumentExceptoinits(){
    	try 
		{   treeArgQt1.add(null);
    		fail("Should have generated an exception");
		}
		catch(IllegalArgumentException e) {
			//normal
		}
    }
    
    @Test
    /**
     * test the add's illegalArgumentExceptoinits when two Quantity objects do not have the same units
     */
    public void testAddUnitsIllegalArgumentExceptoinits(){
    	try 
		{   treeArgQt1.add(defaultQt1);
    		fail("Should have generated an exception");
		}
		catch(IllegalArgumentException e) {
			//normal
		}
    }
    
    @Test
    /**
     * test the the method sub() when there is no llegalArgumentExceptoinits
     */
    public void testSub(){
    	String ansExp1 = "8.0 m s^-2" ;
    	assertEquals("treeArgQ1t - treeArgQt2 is corret", ansExp1,treeArgQt1.sub(treeArgQt2).toString());
    	
    	String ansExp2 = "0.0";
    	assertEquals("defaultQt1 - defaultQt1 is corret", ansExp2,defaultQt1.sub(defaultQt1).toString());
    }
    
    @Test 
    /**
     * test the sub's illegalArgumentExceptoinits when argument is null 
     */
    public void testSubNullIllegalArgumentExceptoinits(){
    	try 
		{   treeArgQt1.sub(null);
    		fail("Should have generated an exception");
		}
		catch(IllegalArgumentException e) {
			//normal
		}
    }
    
    @Test
    /**
     * test the illegalArgumentExceptoinits when two Quantity objects do not have the same units
     */
    public void testSubUnitsIllegalArgumentExceptoinits(){
    	try 
		{   treeArgQt1.sub(defaultQt1);
    		fail("Should have generated an exception");
		}
		catch(IllegalArgumentException e) {
			//normal
		}
    }
    
    @Test
    /**
     * test the negate method
     */
    public void testNegate(){
        String ansExp1 = "-10.0 m s^-2";
        assertEquals("negate of treeArgQ1t is corret", ansExp1,treeArgQt1.negate().toString());
    }
    
    @Test
    /**
     * test the normalizedUnit
     */
    public void testNormalizedUnit(){
    	Map<String,Quantity> db = new HashMap<String,Quantity>();
    	List<String> emp = new ArrayList<String>();  
    	            
    	db.put("km", new Quantity(1000, Arrays.asList("meter"), emp));
    	
    	Quantity ansExp = new Quantity(1000.0, Arrays.asList("meter"), Arrays.asList());
    	assertEquals("normalize 1km is corret", ansExp,Quantity.normalizedUnit("km", db));
    }
    
    @Test
    /**
     * test the normalize 
     */
    public void testNormalize(){
    	Map<String,Quantity> db = new HashMap<String,Quantity>();
    	
    	List<String> emp = new ArrayList<String>();  
    	            
    	db.put("km", new Quantity(1000, Arrays.asList("meter"), emp));
    	
    	Quantity toNormalize = new Quantity(5,Arrays.asList("km"), emp);
    	Quantity ansExp = new Quantity(5000.0, Arrays.asList("meter"), Arrays.asList());
    	assertEquals("normalize 5km is corret", ansExp,toNormalize.normalize(db));
    	
    }
    
    @Test
    /**
     * test the equal 
     */
    public void testEquals(){
    	assertTrue(defaultQt1.equals(defaultQt1));
    	
    	List<String> emp = new ArrayList<String>();  
    	Quantity toCompare = new Quantity(1.000001,emp,emp);
    	assertTrue(defaultQt1.equals(toCompare));
    }
    
    @Test
    /**
     * test hashCode();
     */
    public void testHashCode(){
    	Quantity toCompare = new Quantity(10.000001,Arrays.asList("m"), Arrays.asList("s","s"));
    	int codeToCompare = toCompare.hashCode();
    	int codeTreeArgQ1t = treeArgQt1.hashCode();
    	assertEquals("hashCode is the same ",codeToCompare,codeTreeArgQ1t);
    }
 

}



