/**
 * Xirui Li(CS12fhm,A13691309) Xinyang Yu(cs12fir,A14188323)
 */
package hw5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;


public class Complexity

{
	/**
	 * remove all the element in the collection and print out the time used for this.
	 * @param c where the elements are remooved from
	 * @param toRemove where stores the order of elements to remove. 
	 */
	public static void removeAll(Collection<Integer> c, ArrayList<Integer> toRemove ){	        
	   
		long totalTime;
	    long startTime;
	    
        startTime = System.nanoTime();
           
        for (int i = 0; i < c.size(); i++) {
                c.remove(toRemove.get(i));
            }
        totalTime = System.nanoTime() - startTime;
        System.out.println(c.size() + ", " + totalTime);
        }

	   
    public static void main(String[] args) 
    {
       
        int increment = 2000;//size increased by 2000
        int numSteps = 40; // total times to increase the size of the collection
        int arrayListSizeSort = 5000;// size of An ArrayList, where elements are removed in sorted order
        int arrayListSizeRand = 5000;//size of An ArrayList, where elements are removed in random order
        int linkedListSizeSort = 5000;//size of A LinkedList, where elements are removed in sorted order
        int linkedListSizeRand = 5000;//size of A LinkedList, where elements are removed in random order
        int HashTableSizeSort = 5000;//size of A HashSet, where elements are removed in sorted order
        int HashTabletSizeRand = 5000;// size of A HashSet, where elements are removed in random order
        
        //removeall time  for An ArrayList, where elements are removed in sorted order
        System.out.println("An ArrayList, where elements are removed in sorted order");
        for (int step = 0; step < numSteps; step++) {
            ArrayList<Integer> list1 = new ArrayList<Integer>();
            ArrayList<Integer> toRemove1 = new ArrayList<Integer>();

            for (int i = 0; i < arrayListSizeSort; i++) {
                list1.add(i);
                toRemove1.add(i);
            }
            
            removeAll(list1,toRemove1);
            arrayListSizeSort += increment;
        }
      
      //removeall time  for An ArrayList,where elements are removed in random order
        System.out.println("An ArrayList, where elements are removed in random order.");
        // remove for increasing sizes
        for (int step = 0; step < numSteps; step++) {
            ArrayList<Integer> list2 = new ArrayList<Integer>();
            ArrayList<Integer> toRemove2 = new ArrayList<Integer>();
            for (int i = 0; i < arrayListSizeRand; i++) {
            	toRemove2.add(i);
                list2.add(i);
            }
            Collections.shuffle(toRemove2);
            removeAll(list2,toRemove2);
            arrayListSizeRand += increment;
        }
         
        
        // removeall time A LinkedList, where elements are removed in sorted order"
        System.out.println("A LinkedList, where elements are removed in sorted order");
     // remove for increasing sizes
        for (int step = 0; step < numSteps; step++) {
            LinkedList<Integer> list3 = new LinkedList<Integer>();
            ArrayList<Integer> toRemove3 = new ArrayList<Integer>();
            for (int i = 0; i < linkedListSizeSort; i++) {
                list3.add(i);
                toRemove3.add(i);
            }
            removeAll(list3,toRemove3);
            linkedListSizeSort += increment;
        }
        
        // removeall time A LinkedList, where elements are removed in random order"
        System.out.println("A LinkedList, where elements are removed in random order");
     // remove for increasing sizes
        for (int step = 0; step < numSteps; step++) {
            LinkedList<Integer> list4 = new LinkedList<Integer>();
            ArrayList<Integer> toRemove4 = new ArrayList<Integer>();
            for (int i = 0; i < linkedListSizeRand; i++) {
                list4.add(i);
                toRemove4.add(i);
            }
            Collections.shuffle(toRemove4);
            removeAll(list4,toRemove4);
            linkedListSizeRand += increment;
        }

       
     // removeall time A HashSet, where elements are removed in sorted order"
        System.out.println("A HashSet, where elements are removed in sorted order");
     // remove for increasing sizes
        for (int step = 0; step < numSteps; step++) {
            HashSet <Integer> list5 = new HashSet<Integer>();
            ArrayList<Integer> toRemove5 = new ArrayList<Integer>();
            for (int i = 0; i < HashTableSizeSort; i++) {
                list5.add(i);
                toRemove5.add(i);
            }
            removeAll(list5,toRemove5);
            HashTableSizeSort += increment;
        }
        
     // removeall time A HashSet,  where elements are removed in random order"
       System.out.println("A HashSet, where elements are removed in random order");
    // remove for increasing sizes
        for (int step = 0; step < numSteps; step++) {
            HashSet <Integer> list6 = new HashSet<Integer>();
            ArrayList<Integer> toRemove6 = new ArrayList<Integer>(); 
            for (int i = 0; i < HashTabletSizeRand; i++) {
                toRemove6.add(i);
                list6.add(i);
            }
            Collections.shuffle(toRemove6);
            removeAll(list6,toRemove6);
            HashTabletSizeRand += increment;
        }
        
    }
}