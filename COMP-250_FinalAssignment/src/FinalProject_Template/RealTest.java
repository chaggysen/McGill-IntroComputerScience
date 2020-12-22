package FinalProject_Template;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class RealTest {
	

	
	public static void main(String[] args) {
		
		MyHashTable<String, Integer> testTable = new MyHashTable<String, Integer>(10);
		testTable.put("yo", 1);
		testTable.put("yo", 10);
		testTable.put("yoo", 2);
		testTable.put("yooo", 3);
		testTable.put("yooooo", 4);
		
		ArrayList <String> sortedList = new ArrayList<String>();
		sortedList = testTable.fastSort(testTable);
		System.out.println(sortedList);
	}
	

}
