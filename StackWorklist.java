/**
 *  Title: class MyLinkedListTester
 *  Description: JUnit test class for LinkedList class
 *  @author Xirui Li(cs5fhc,A13691309), Xinyang Yu(cs12fir,A14188323)
 *  @version  12-OCT-2017
 * */
import java.util.LinkedList;

public class StackWorklist implements SearchWorklist {
    
	/**
	 * LinkedList Adapter
	 */
	public LinkedList<Square> stackList = new LinkedList<Square>();
	
	/**
	 * Add a square into the worklist
	 * @param the squares need to be explored
	 */
	@Override
	public void add(Square s) {
		
		Boolean notRepeat = true;
		for(int i = 0; i < stackList.size();i++){
			Square test = stackList.get(i);
			if (test.getRow() == s.getRow() && test.getCol() == s.getCol()){
			    notRepeat = false;
		    }
		}
		if(notRepeat){
        stackList.add(0,s);
		} 
	}

	/** Removes and returns the next Square to be explored 
	 * @return The next Square to explore */	
	@Override
	public Square getNext() {
		Square var = stackList.get(0);
		stackList.remove(0);
		return var;
	}
	
   /** isEmpty
	* @return true if the worklist is empty, false otherwise
	*/
	@Override
	public boolean isEmpty() {
		return stackList.isEmpty();
	}
	
	/** size of the worklist
	 * @return The number of elements in the worklist
	 */
	@Override
	public int size() {
		return stackList.size();
	}

}
