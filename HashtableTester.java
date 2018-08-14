/**
 * Xirui Li(CS12fhm,A13691309) Xinyang Yu(cs12fir)
 */
package hw4;
import org.junit.*;
import static org.junit.Assert.*;
public class HashtableTester {
	private HashTable testHashTable1;
	@Before
	public void setUp()
	{
		testHashTable1 = new HashTable(0);
	}
	@Test
	public void Empty()
	{
    testHashTable1.contains("abc");
	System.out.println(testHashTable1.getSize());
	//assertEquals("Checking contains after insert",true,!testHashTable1.contains("abc"));
	}
	
/*	public void testInsert()
	{
		assertEquals("checking insert",true,testHashTable1.insert("abc"));
		assertEquals("Checking contains after insert",true,testHashTable1.contains("abc"));
	}
	@Test
	public void testInsertf()
	{
		assertEquals("checking insert",true,testHashTable1.insert("123"));
		assertEquals("checking insert",false,testHashTable1.insert("123"));
	}
	@Test
	public void testMultiInsert()
	{  
		testHashTable1.insert("abc");
		testHashTable1.insert("ab");
		testHashTable1.insert("ac");
		testHashTable1.insert("abcd");
		testHashTable1.insert("ade");
		testHashTable1.insert("abcf");
		testHashTable1.insert("ae");
		assertEquals("checking insert",false,testHashTable1.insert("ab"));
		assertEquals("checking insert",true,testHashTable1.insert("ssdddf"));
	}
	@Test
	public void testinsertNullPointerException()
	{
		try 
		{
			testHashTable1.insert(null);
			fail("Should have generated an exception");
		}
		catch(NullPointerException e) {
			//normal
		}
		
	}
	@Test
	public void testDelete()
	{
     	testHashTable1.insert("abc");
		assertEquals("Checking delete",true,testHashTable1.delete("abc"));
		assertEquals("Checking contains after delete",false,testHashTable1.contains("abc"));
	}
	@Test
	public void testDeletef() 
	{
		assertEquals("Checking delete",false,testHashTable1.delete("123"));
	}
	@Test
	public void testDeleteNullPointerException()
	{
		try
		{
			testHashTable1.delete(null);
			fail("should have generated an exception");
		}
		catch(NullPointerException e) {
			//normal
		}
	}
	@Test
	public void testGetSize()
	{
		testHashTable1.insert("abc");
		testHashTable1.insert("pqr");
		testHashTable1.insert("xyz");
		assertEquals("Checking getSize",new Integer(3),new Integer(testHashTable1.getSize()));
	}
	@Test
	public void testContain() 
	{
		testHashTable1.insert("abc");
		
		assertEquals("Checking Contain",true,testHashTable1.contains("abc"));
	}
	
	
	@Test
	public void testContainf()
	{   
		assertEquals("checking Contain",false,testHashTable1.contains("123"));
	}
	@Test
	public void testContainNullPointerException()
	{
		try
		{
			testHashTable1.contains(null);
			fail("should have generated an exception");
		}
		catch(NullPointerException e)
		{
			//normal
		}
	}*/
}
