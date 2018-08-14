/**
 * Xirui Li(CS12fhm,A13691309) Xinyang Yu(cs12fir)
 */
package hw4;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;

public class SpellChecker {
    String wordTocheck = null; 
    HashTable dictionaryTable = null;
    File dictionary;
    int wordLength;
    ArrayList<String> suggestion= null;
    String[]suggestionA = null;
    
 /**
  * Constructor for a SpellChecker   
  * @param dictionary
  * @param wordToCheck
  */
   public SpellChecker(File dictionary, String wordToCheck) {//should I load dictionary in constructor
        dictionaryTable = new HashTable(3);
	    this.dictionary = dictionary;
    	this.wordTocheck = wordToCheck;
    	this.wordLength = wordToCheck.length();
    }
    
    
  /**
   * default constructor;
   */
    public SpellChecker() {
        dictionaryTable = new HashTable(3);
        
}

    
    /**
     * load the dictionary 
     * @param reader
     */
     public void readDictionary(Reader reader) {//where should reader be created how to find the file
    	 Scanner br = new Scanner(reader);
			   while (br.hasNext()) {
				   String temp = br.next();
				   if(temp!=null){
			      this.dictionaryTable.insert(temp);}		      
			   }	
		 br.close();
     }
     
     /**
      * To check if there is a wrong letter,and add suggestions into the ArrayList
      */
     private void wrongLetter(){
    	StringBuilder str;
    	for(int i = 0; i < this.wordLength; i++){
    		for(int j = 0; j < 27; j++){
    			str = new StringBuilder(this.wordTocheck);
      			str.setCharAt(i, (char) (j + 96));
    			String newWordTocheck = str.toString();
    			if(dictionaryTable.contains(newWordTocheck)){
    				suggestion.add(newWordTocheck);
    			}
    		  }	
    		}	
    	}
    	
    	
     /**
      * To check if there is a missing letter,and add suggestions into the ArrayList
      */
      private void insertedLetter(){	
    	  StringBuilder str;
    	  for(int i = 0; i <= this.wordLength; i++){
      		for(int j = 0; j < 27; j++){
      			 str = new StringBuilder(this.wordTocheck);
      			str.insert(i, (char) (j + 96));
      			String newWordTocheck = str.toString();
      			if(dictionaryTable.contains(newWordTocheck)){
      				if(!suggestion.contains(newWordTocheck)){
      				suggestion.add(newWordTocheck);
      				}
      			}
      		  }	
      		}		 
      }
      
      /**
       * to check if the search word has one extra letter 
       */
      
      private void deletedLetter() {
    	  StringBuilder str;
    	  for (int i = 0; i < this.wordLength;i++) {
    			  str = new StringBuilder (this.wordTocheck);
    			  str.deleteCharAt(i);
    			  String newWordTocheck = str.toString();
    			  if (dictionaryTable.contains(newWordTocheck)) {
    				  suggestion.add(newWordTocheck);
    			  }
    	  }
      }
      
      /**
       * to check if the order of the letters is wrong
       */
      private void displacementLetters() {
    	  StringBuilder str;
    		for(int i = 0; i < this.wordLength - 1; i++){       		
        			str = new StringBuilder (this.wordTocheck);
        			char temp  = str.charAt(i); 
        			str.setCharAt(i,str.charAt(i + 1));
        			str.setCharAt(i+1, temp);
        			String newWordTocheck = str.toString();
        			if(dictionaryTable.contains(newWordTocheck)){
        				suggestion.add(newWordTocheck);
        				//reset the word ?? did i change original word?
        			}        		  
        		}	  	  
      }
      
      /**
       * to check if space is missing
       */
     private void missingSpace(){
    	 StringBuilder str;
   	     for(int i = 0; i <= this.wordLength; i++){
     			str = new StringBuilder(this.wordTocheck);
     			str.insert(i, " ");
     			String newWordTocheck = str.toString();
     			String[] arr = newWordTocheck.split(" ");
     			if(dictionaryTable.contains(arr[0]) && dictionaryTable.contains(arr[1]) ){
     				suggestion.add(newWordTocheck);
     			}
     		  }			 
     }
     
     /**
      * check the spelling of the word and check if there is a suggestion
      * @param word to check spelling
      * @return the suggestion spelling if the word cannot be found
      */
     public String[] checkWord(String word) { 
    	 suggestion= null;
    	 suggestionA = null;
    	 this.wordTocheck = word;
    	 this.wordLength = word.length();
    	 
    	 if(!dictionaryTable.contains(word)){
         suggestion = new ArrayList<String>(); 
         this.wrongLetter();
         this.deletedLetter();
         this.insertedLetter();
         this.displacementLetters();
         this.missingSpace();
         suggestionA = new String[suggestion.size()];
	     suggestionA = suggestion.toArray(suggestionA);
	     return suggestionA;
    	 }	 
    	 return null;
      }
}