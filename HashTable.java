/**
 * Xirui Li(CS12fhm,A13691309) Xinyang Yu(cs12fir,A14188323)
 */
package hw4;

import java.io.BufferedWriter;
import java.io.File;
import java.io.PrintStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

public class HashTable implements IHashTable {

	LinkedList<String>[] hashArray;// You will need a HashTable of LinkedLists.

	private int nelems; // Number of element stored in the hash table
	private int expand; // Number of times that the table has been expanded
	private int collision = 0; // Number of collisions since last expansion
	private String statsFileName; // FilePath for the file to write statistics
									// upon every rehash
	private boolean printStats = false; // Boolean to decide whether to write
										// statistics to file or not after
										// rehashing

	/**
	 * Constructor for hash table
	 * 
	 * @param Initial
	 *            size of the hash table
	 */
	@SuppressWarnings("unchecked")
	public HashTable(int size) {
		hashArray = new LinkedList[size];
		// Initialize
	}

	/**
	 * Constructor for hash table
	 * 
	 * @param Initial
	 *            size of the hash table
	 * @param File
	 *            path to write statistics
	 */
	@SuppressWarnings("unchecked")
	public HashTable(int size, String fileName) {
		hashArray = new LinkedList[size];
		this.statsFileName = fileName;
		this.printStats = true;

		// Set printStats to true and statsFileName to fileName
	}

	@Override
	public boolean insert(String value) throws NullPointerException {
		// Check exception
		if (value == null) {
			throw new NullPointerException();
		}
		// test if contains
		if (!this.contains(value)) {
			// rehash
			if (this.loadFactor() > (2 / 3)) {
				rehash();
				this.insert(value);
				return true;
			}
			int index = this.getIndex(value);
			// when there bucket is empty at that index
			if (hashArray[index] == null) {
				LinkedList<String> chainList = new LinkedList<String>();
				hashArray[index] = chainList;
				chainList.add(value);
				nelems++;
				return true;
			}
			// add the value into the chain,if not repeated
			else {
				hashArray[index].add(value);
				nelems++;
				collision++;
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean delete(String value) throws NullPointerException {
		// Check exception
		if (value == null) {
			throw new NullPointerException();
		}
		if (!this.contains(value)) {
			return false;
		} else {
			hashArray[this.getIndex(value)].remove(value);
			nelems--;
			return true;
		}
	}

	@Override
	public boolean contains(String value) throws NullPointerException {
		// Check exception
		if (value == null) {
			throw new NullPointerException();
		}

		int index = this.getIndex(value);
		if (hashArray[index] == null) {
			return false;
		} 
		else {
			for (int i = 0; i < hashArray[index].size(); i++) {
				if (hashArray[index].get(i).equals(value)) {
					return true;
				}
			}
			return false;
		}
	}

	@Override
	public void printTable() {
		for (int i = 0; i < hashArray.length; i++) {
			String toPrint = i + ": ";
			if (hashArray[i] != null) {
				for (int j = 0; j < hashArray[i].size(); j++) {
					if (j > 0) {
						toPrint += ",";
					}
					toPrint += hashArray[i].get(j);
				}
			}
			System.out.println(toPrint);
		}
	}

	@Override
	public int getSize() {
		return nelems;
	}

	/*
	 * TODO - Helper methods can make your code more efficient and look neater.
	 * We expect AT LEAST the follwoing helper methods in your code rehash() to
	 * expand and rehash the items into the table when load factor goes over
	 * ththreshold. printStatistics() to print the statistics after each
	 * expansion. This method will be called from insert/rehash only if
	 * printStats=true
	 */

	/**
	 * Help method that map the bucket index by converting the string into
	 * integer and modulos the capacity of the hash table
	 * 
	 * @param String
	 *            to find the bucket index
	 * @return the index
	 */
	public int getIndex(String S) {
		char ch[] = S.toCharArray();
		int sNum = 0;
		for (int i = 0; i < S.length(); i++) {
			sNum = ((ch[i] - 96) + sNum * 27) % this.hashArray.length;
		}
		
		return sNum;
	}

	/**
	 * compute load factor
	 */
	public double loadFactor() {
		double loadFactor = nelems / hashArray.length;
		return loadFactor;
	}

	/**
	 * find longest chain
	 */
	public int longestChain() {
		int longest = 0;
		for (int i = 0; i < hashArray.length; i++) {
			if (hashArray[i] != null) {
				if (hashArray[i].size() > longest) {
					longest = hashArray[i].size();
				}
			}
		}
		return longest;
	}

	/**
	 * create the statistics line to print.
	 */
	public String printStatistics() {
		DecimalFormat df = new DecimalFormat("#.00");
		String toPrint = expand + " resizes,load factor " + df.format(this.loadFactor()) + " " + this.collision
				+ " collisons, " + this.longestChain() + " longest chain";
		return toPrint;
	}

	/**
	 * if printStats is true, print statistics in the file
	 */
	public void printToFile() {
		if (printStats) {
			File file = new File(statsFileName);
			try {
				if (!file.exists()) {
					file.createNewFile();
				}
				FileWriter fw = new FileWriter(file, true);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(this.printStatistics());
				bw.newLine();

				bw.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			// cite:https://www.youtube.com/watch?v=72BEuCPMgTo
		}
	}

	/**
	 * Rehash to expand and rehash the items into the table when load factor
	 */
	@SuppressWarnings("unchecked")
	public void rehash() {
		this.printToFile();
		LinkedList<String>[] temp = hashArray;
		hashArray = new LinkedList[2 * this.hashArray.length];

		nelems = 0; // Number of element reset
		expand++; // Number of times that the table has been expanded add
		collision = 0; // reset
		for (int i = 0; i < temp.length; i++) {
			if (temp[i] != null) {
				for (int j = 0; j < temp[i].size(); j++) {
					this.insert(temp[i].get(j));
				}
			}
		}

	}

}