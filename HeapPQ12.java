/**
 * Xirui Li(CS12fhm,A13691309) Xinyang Yu(cs12fir,A14188323)
 */
package hw6;

import java.util.ArrayList;
import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.NoSuchElementException;

/** HeapPQ12 class that implements an unbounded array-backed heap structure and is
 * an extension of the Java Collections AbstractQueue class 
 * <p>
 * The elements of the heap are ordered according to their natural 
 * ordering,  HeapPQ12 does not permit null elements. 
 * The top of this heap is the minimal or maximal element (called min/max)  
 * with respect to the specified natural ordering.  
 * If multiple elements are tied for min/max value, the top is one of 
 * those elements -- ties are broken arbitrarily. 
 * The queue retrieval operations poll and  peek 
 * access the element at the top of the heap.
 * <p>
 * A HeapPQ12 is unbounded, but has an internal capacity governing the size of 
 * an array used to store the elements on the queue. It is always at least as 
 * large as the queue size. As elements are added to a HeapPQ12, its capacity 
 * grows automatically. The details of the growth policy are not specified.
 * <p>
 * This class and its iterator implements the optional methods of the 
 * Iterator interface (including remove()). The Iterator provided in method 
 * iterator() is not guaranteed to traverse the elements of the HeapPQ12 in 
 * any particular order. 
 * <p>
 * Note that this implementation is not synchronized. Multiple threads 
 * should not access a HeapPQ12 instance concurrently if any of the 
 * threads modifies the HeapPQ12. 
 */
public class HeapPQ12<E extends Comparable <? super E>> extends 
    AbstractQueue<E> 
{
    
    /* -- Define any private member variables here -- */
    /* In particular, you will need an ArrayList<E> to hold the elements
     * in the heap.  You will also want many more variables */
	private ArrayList<E> list1; // an ArrayList<E> to hold the elements
	private int size;// size of the queue
	private boolean isMaxHeap;//type of the heap

    /** O-argument constructor. Creates an empty HeapPQ12 with unspecified
     *  initial capacity, and is a min-heap 
     */ 
    public HeapPQ12()
    {
    	this.isMaxHeap = false;
    	this.list1 = new ArrayList<>(5);
    	list1.add(null);
    }

    /** 
     * Constructor to build a min or max heap
     * @param isMaxHeap   if true, this is a max-heap, else a min-heap 
     */ 
    public HeapPQ12(boolean isMaxHeap)
    { if(isMaxHeap){
    	this.isMaxHeap = true;
       }
      else{this.isMaxHeap = false;
       }
      this.list1 = new ArrayList<>(5);
      list1.add(null);
    }

    /** 
     * Constructor to build a with specified initial capacity 
     *  min or max heap
     * @param capacity      initial capacity of the heap.   
     * @param isMaxHeap     if true, this is a max-heap, else a min-heap 
     */ 
    public HeapPQ12(int capacity, boolean isMaxHeap)
    {if(isMaxHeap){
    	this.isMaxHeap = true;
      }
     else{this.isMaxHeap = false;}
     this.list1 = new ArrayList<E>(capacity);
     list1.add(null);
    }
    
    
    /** Copy constructor. Creates HeapPQ12 with a deep copy of the argument
     * It makes a new HeapPQ12 object with a new ArrayList backing store that contains (new) references to the same elements stored in the parameter HeapPQ12
     * @param toCopy      the heap that should be copied
     */
    public HeapPQ12 (HeapPQ12<E> toCopy)
    {
    	list1 = new ArrayList<E>();
    	Iterator<E> ite1 = toCopy.iterator();
    	for (int i = 0; i < toCopy.size(); i++){
    		list1.add(ite1.next());
    	}
    	this.isMaxHeap = toCopy.isMaxHeap;
    	this.size = toCopy.size;
    }

    /* The following are defined "stub" methods that provide degenerate
     * implementations of methods defined as abstract in parent classes.
     * These need to be coded properly for HeapPQ12 to function correctly
     */

    /** Size of the heap
     * @return the number of elements stored in the heap
    */
    public int size()
    {
        return size; 
    }

    /** 
     * @return an Iterator for the heap 
    */
    public Iterator<E> iterator()
    {
    	return new HeapPQ12Iterator();
    }

    /** peek - see AbstractQueue for details 
     * 
     * @return Element at top of heap. Do not remove 
    */
    public E peek()
    {   if(this.size() == 0){
    	return null;
    }
        return list1.get(1);
    }
    /** poll - see AbstractQueue for details 
     * @return Element at top of heap. And remove it from the heap. 
     *  return <tt>null</tt> if the heap is empty
    */
    public E poll()
    {   if(this.size() == 0){
    	 return null;
         }
        if(this.size() == 1){
         E toReturn = list1.get(1);
         list1.remove(1);
         this.size--;
         return toReturn;
        }
        else{ 
        E toReturn = list1.get(1);
    	list1.set(1, list1.get(size));
    	list1.remove(size);
    	this.size--;
    	this.trickleDown(1);
        return toReturn;
        }
    }
    
    /** offer -- see AbstractQueue for details
     * insert an element in the heap
     * @return true
     * @throws NullPointerException 
     *  if the specified element is null    
     */
    public boolean offer (E e) throws NullPointerException 
    {   if( e == null){
    	 throw new NullPointerException();
        }
    	if (list1.size()+1 == this.size()) {
        	ArrayList <E> list2 = new ArrayList<E>(list1.size()*2);
        	for (int i = 0; i < list1.size();i++) {
        		list2.set(i, list1.get(i));
        	}
        	list1 = list2;
    	}
        list1.add(e);
        size++;
        this.bubbleUp(size);
        return true;
    }

    /* ------ Private Helper Methods ----
     *  DEFINE YOUR HELPER METHODS HERE
     */
    /**
     * Swap two nodes
     * @param list where is the two nodes belonged to 
     * @param one of the index of the node to swap 
     * @param the other index of the node to swap
     */
    private void swap(ArrayList<E> list, int indexToSwap1, int indexToWap2 ) {
    	E kid = list.get(indexToSwap1);
    	list.set(indexToSwap1, list.get(indexToWap2));
    	list.set(indexToWap2, kid);	
    }
    
    /**
     * Bubble up the node to find its proper position
     * @param idx of the node to bubble up
     */
    private void bubbleUp(int idx){
    	if (idx == 1) {
    		return;
    	}
    	if (this.compareWith(list1.get(idx),list1.get(idx/2)) == 1){
    		return;
    	}
    	else if(this.compareWith(list1.get(idx),list1.get(idx/2)) == -1) {
    		swap(list1,idx,idx/2);
    		this.bubbleUp(idx/2);
    	}
    }
    
    /**
     * Trickle down the node to find its proper position
     * @param idx of the node to trickle down
     */
    private void trickleDown(int indx){
    	int lInd = indx*2;
    	int rInd = indx*2 + 1;
    	int childInd;
    	
    	// leaf base case
    	if(lInd > this.size()) {
    		return;
    	}
    	
    	//Single Child case
        if(rInd > this.size()){
        	// base case
        	if(this.compareWith(list1.get(indx),list1.get(lInd) ) == -1){
        		return;
        	}
        	else{
        		childInd = lInd;
        		this.swap(list1,childInd,indx);
        		this.trickleDown(childInd);
        	}
        }
        else{
        	 //base case
    	     if(this.compareWith(list1.get(indx),list1.get(lInd) ) == -1 && this.compareWith(list1.get(indx),list1.get(rInd) ) == -1){
    		    return;
    	    }
    	     else{ 
    		      if (this.compareWith(list1.get(lInd), list1.get(rInd)) == -1){
    		          childInd = lInd;
    		     }
    		     else{ childInd = rInd;}
    		this.swap(list1,childInd,indx);
    		this.trickleDown(childInd);
    		return;
    	     }
    	}
    }
    
    
    /**
     * Compare a and b based the type of the heap
     * @param a needed to compare to b
     * @param b the elements to compare with
     * @return -1 or 1 based on teh type
     */
    public int compareWith(E a, E b) {
    	if(!this.isMaxHeap){
    			if(a.compareTo(b) == -1 ){
    				return -1;
    			}
    			else{
    				return 1;
    			}		
    	}
    		
    	if(this.isMaxHeap){
    			if(a.compareTo(b) == -1 ){
    				return 1;
    			}
    			else{
    				return -1;
    			}		
    	}
    	return 0;
    	}
      
  

    /** Inner Class for an Iterator 
     * @param <E>**/
    /* stub routines */

    private class HeapPQ12Iterator implements Iterator<E>
    {
        private boolean canRemove;
        private int idx;
        
        public HeapPQ12Iterator()
        {
        	canRemove = false;
        	idx = 0;
        }

        /** hasNext() to implement the Iterator<E> interface 
         */
        public boolean hasNext()
        {
            if(idx+1 <= size){
            	return true;
            }
            return false;
        }

        /** next() to implement the Iterator<E> interface 
         */
        public E next() throws NoSuchElementException
        {
        	if (this.hasNext()){
        		idx ++;
        		canRemove = true;
        		return list1.get(idx);
        	}
        	else{
        		throw new NoSuchElementException();
        	}
            
        }
        /** remove() to implement the Iterator<E> interface 
         */
        public void remove() throws IllegalStateException
        { 
        	if(!canRemove || size == 0){
        	throw new IllegalStateException();
            }
        	if (canRemove){
        		list1.set(idx, list1.get(size));
        		list1.remove(size);
        		size--;
        		bubbleUp(idx);
        		trickleDown(idx);
        		this.canRemove = false;
        		idx--;
        		
        	}
        	
        }   
    }
    
}



