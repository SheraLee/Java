/**
 *  Title: class MyLinkedListTester
 *  Description: JUnit test class for LinkedList class
 *  @author Xirui Li(cs5fhc,A13691309), Xinyang Yu(cs12fir,A14188323)
 *  @version  12-OCT-2017
 * */
import java.util.LinkedList;

public class QueueWorklist implements SearchWorklist {
	public LinkedList<Square> queueList = new LinkedList<Square>();
	
	/**
	 * Add a square into the worklist
	 * @param the squares need to be explored
	 */
	
	@Override
	public void add(Square s) {
		Boolean notRepeat = true;
		for(int i = 0; i < queueList.size();i++){
			Square test = queueList.get(i);
			if (test.getRow() == s.getRow() && test.getCol() == s.getCol()){
			    notRepeat = false;
			 }
		}
		if(notRepeat){
        queueList.add(s);
		}
	}

	/** Removes and returns the next Square to be explored 
	 * @return The next Square to explore */	
	@Override
	public Square getNext() {
		Square var = queueList.get(0);
		queueList.remove(0);
		return var;
	}
	
   /** isEmpty
	* @return true if the worklist is empty, false otherwise
	*/
	@Override
	public boolean isEmpty() {
		return queueList.isEmpty();
	}
	
	/** size of the worklist
	 * @return The number of elements in the worklist
	 */
	@Override
	public int size() {
		return queueList.size();
	}

}

