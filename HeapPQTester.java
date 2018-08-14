/**
 * Xirui Li(CS12fhm,A13691309) Xinyang Yu(cs12fir,A14188323)
 */
package hw6;
//not failling poll for buggy implemts

import org.junit.*;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class HeapPQTester {
private HeapPQ12<Integer> heapMin;
private HeapPQ12<Integer> heapMax;

 @Before
 /**
  * Create a max and a min heap
  */
 public void setup(){
	 heapMax = new HeapPQ12<Integer>(5,true);
	 heapMin = new HeapPQ12<Integer>(5,false);
 }
 
 @Test
 /**
  * Test the offer method
  */
 public void testOffer(){
	 assertTrue("Checking offer",heapMax.offer(2));
	 assertTrue("Checking offer",heapMin.offer(2));
	 assertEquals(new Integer(2),heapMax.peek());
	 assertEquals(new Integer(2),heapMin.peek());
 }
 
 @Test
 /**
  * test resize method 
  */
 public void testOfferResize(){
	 assertTrue("Checking offer",heapMax.offer(2));
	 assertTrue("Checking offer",heapMin.offer(2));
	 assertTrue("Checking offer",heapMax.offer(3));
	 assertTrue("Checking offer",heapMin.offer(3));
	 assertTrue("Checking offer",heapMax.offer(4));
	 assertTrue("Checking offer",heapMin.offer(1));
	 assertEquals(new Integer(4),heapMax.peek());
	 assertEquals(new Integer(1),heapMin.peek());
 }
 
 @Test
 /**
  * test NullPointerException of Offer method
  */
 public void testOfferlNullPointerException(){
	 try 
		{
			heapMax.offer(null );
			fail("Should have generated an exception");
		}
		catch(NullPointerException e) {
			//normal
		}
 }
 
 @Test	
 /**
  * test Poll method for Max heap
  */
 public void testPollMax(){
	 heapMax.offer(2);
	 heapMax.offer(3);
	 heapMax.offer(4);
	 heapMax.offer(5);
	 heapMax.offer(6);
	 heapMax.offer(7);
	 assertEquals(new Integer(7),heapMax.poll());
	 assertEquals(new Integer(6),heapMax.poll());
	 assertEquals(new Integer(5),heapMax.poll());
	 assertEquals(new Integer(4),heapMax.poll());
	 assertEquals(new Integer(3),heapMax.poll());
	 assertEquals(new Integer(2),heapMax.poll());
 }

 @Test
 /**
  * test Poll method of min heap
  */
 public void testPollMin(){
	 heapMin.offer(2);
	 heapMin.offer(3);
	 heapMin.offer(4);
	 heapMin.offer(5);
	 heapMin.offer(6);
	 heapMin.offer(7); 
	 assertEquals(new Integer(2),heapMin.poll());
	 assertEquals(new Integer(3),heapMin.poll());
	 assertEquals(new Integer(4),heapMin.poll());
	 assertEquals(new Integer(5),heapMin.poll());
	 assertEquals(new Integer(6),heapMin.poll());
	 assertEquals(new Integer(7),heapMin.poll());
 }
 
 @Test 
/**
 * test poll method when null should be return 
 */
 public void testPollReturnNull(){
	 assertEquals(null,heapMin.poll());
	 assertEquals(null,heapMax.poll());
 }
 
 @Test
 /**
  * test peek method when NUll should be returned
  */
 public void testPeekReturnNull(){
	 assertEquals(null,heapMin.peek());
	 assertEquals(null,heapMax.peek());
 }
 
 @Test
 /**
  * test the size method
  */
 public void testSize(){
	 heapMax.offer(2);
	 heapMax.offer(3);
	 heapMax.offer(4);
	 heapMax.offer(5);
	 heapMax.offer(6);
	 heapMax.offer(7);
	 assertEquals(6,heapMax.size());
 }
 
 @Test
 /**
  * test The iterator's hasnext funtction 
  */
 public void testIteratorHasNext(){
	 heapMax.offer(2);
	 heapMax.offer(3);
	 Iterator<Integer> iter = heapMax.iterator();
	 assertTrue("Checking has next",iter.hasNext());
	 assertTrue("Checking has next",iter.hasNext());
 }
 
 @Test
 /**
  * test iterator's next function
  */
 public void testIteratorNext(){
	 heapMax.offer(2);
	 heapMax.offer(3);
	 Iterator<Integer> iter = heapMax.iterator();
	 assertEquals(new Integer(3),iter.next());
	 assertEquals(new Integer(2),iter.next());
 }
 
 @Test
 /**
  * test the next function of NoSuchElement Exception 
  */
 public void testIterNoSuchElementException(){
	 try 
		{ Iterator<Integer> iter = heapMax.iterator();
		  iter.next();
		  fail("Should have generated an exception");
		}
		catch(NoSuchElementException e) {
			//normal
		}
 }

 @Test
 /**
  * test the iterator's remove method
  */
 public void testRemove(){
	 heapMax.offer(2);
	 heapMax.offer(3);
	 heapMax.offer(4);
	 heapMax.offer(5);
	 heapMax.offer(6);
	 Iterator<Integer> iter = heapMax.iterator();
	 iter.next();
	 iter.remove();
	 assertEquals(new Integer(5),heapMax.peek());
	 iter.next();
	 iter.remove();
	 assertEquals(new Integer(4),heapMax.peek());
	 iter.next();
	 iter.remove();
	 assertEquals(new Integer(3),heapMax.peek());
	 iter.next();
	 iter.remove();
	 assertEquals(new Integer(2),heapMax.peek());
 }
 
 
 @Test
 /**
  * test IllegalStateException of iterator's remove method
  */
 public void testIterIllegalStateException(){
	 try 
		{ Iterator<Integer> iter = heapMax.iterator();
		  iter.remove();
		  fail("Should have generated an exception");
		}
		catch(IllegalStateException e) {
			//normal
		}
 }
 
}
