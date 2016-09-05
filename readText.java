package lab4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

//***************************************************************************************************************************************************** //
//Author: Oscar Ivan Ricaud.
	/*Assignment: Lab 4
	Instructor: Professor Julio Urenda
	TA: Saiful Abu
	Course 2302
	Data of Last modification July 17th, 2016
	*/

//Program purpose:
	/* The purpose of this program is to implement a hash table to get a better understanding how this algorithm is fast. 
	 * It first reads a text file and converts each word into a node and then using the ASCII it converts the word
	 * into an integer. I then created a hash table size on powers of 2 based on the size of the text file. So if the text
	 * file is size 100 it will round up to the nearest power, 2^7 = 128 table size.
	*/
//How to operate lab4 readText.java:
	/* Click play button and read console for output
	 */
//***************************************************************************************************************************************************** //

/*
 * for n = 1 and file= "my name is oscar", n-grams would be "my", "name", "is", "oscar", 
 * for n = 2 n-grams would be "my name", "name is", "is oscar". 
 * for n = 3, n-grams would be "my name is", "name is oscar". 
 * for n = 4 n-grams would be "my name is oscar".
 */

public class readText {
	static int counter = 0;
	static String emptySpace = "";
	static int n = 15;
	
	public static void main(String[] args) throws IOException {
		/* Begin to read a text file, change path if necessery */ 
		File file = new File("/Users/oscarricaud/Desktop/name.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		StringBuffer fileContents = new StringBuffer();
		String line = br.readLine();
		while (line != null) {
			fileContents.append(line);
			line = br.readLine();
		}
		br.close();
		LinkedList <String> list = new LinkedList<String>();
		list = convertoTxtLinkedList(fileContents.toString());
		System.out.println("list : " + list);
		/* End to read a text file */
		
		/* Begin to convert the words into nodes */
		iNode x = null;
		x = convertToNode(x, list);
		System.out.print("For " + n + "-grams of S = [" );
		iNode.printList(x);
		System.out.println("] is:");
		/* End to convert the words into nodes*/
		
		/* Create the hash table respect to the table length by rounding up the nearest powers */
		int log = (int) (Math.log(list.size()) / Math.log(2));
		int tableSize = (int) Math.pow(2, log+1 );
		Hashtable column = new Hashtable(tableSize);
		int min = 0;
		int max = n;
		int ListSize = list.size();
		iNode x2 = null;
		
		/* Build the n-Gram sublists */ 
		column.buildSublists(x, min, max , ListSize, x2);
		
		/* Print hash table */ 
		int filled = 0;
		for(int i = 0 ; i  < column.myTable.length; i++){

			if(!(column.myTable[i].isEmpty())){
				filled++;	
			}
			System.out.println("Hash Table at [" + i + "] = " + column.myTable[i].toString());	
		}
		System.out.println("Table filled " + filled + " slots out of " + (column.myTable.length) + " in the hash table");
		double loadFactor = (double)(filled)/(column.myTable.length);
		System.out.println("The load factor is: " + filled + "/ " + (column.myTable.length) +  " = " + loadFactor);
	}
	// Converts the list into nodes //
	private static iNode convertToNode(iNode x, LinkedList<String> list) {
		for(int i = list.size()-1; i >= 0; i--){
			x = new iNode(list.get(i), x);
		}
		return x;
	}
	/* Converts the text file into a signly linked list */
	private static LinkedList<String> convertoTxtLinkedList(String string2) {
		LinkedList <String> list = new LinkedList<String>();
		int end = 0;
		int begin = 0;
		for(int i = 0 ; i < string2.length(); i++){
			char temp = string2.charAt(i);
			if(temp !=(' ')){
				end++;
			}
			else{
				String temp2 = string2.substring(begin, end);
				list.add(temp2);
				begin = end+1;
				end = begin;
			}
		}
		return list;
	}
}
