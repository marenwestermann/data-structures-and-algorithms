/* For this problem, we have 2 types of queries you can perform on a List:
insert y at index x and delete the element at index x. 
Given a list L of N integers perform Q queries on the list. Once all queries are completed, print the modified list as a single line of space-separated integers. */ 

import java.io.*;
import java.util.*;

public class JavaList {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt(); // length of list

		List<Integer> l = new ArrayList<Integer>();

		for (int i = 0; i < n; i++) {
			l.add(i, scan.nextInt()); // add numbers to list
		}
		
		int o = scan.nextInt(); // number of queries
		
		// perform queries        
		for (int j = 0; j < o; j++) {
			String query = scan.next();
			if (query.equals("Insert")) {
				l.add(scan.nextInt(), scan.nextInt());
			} else {
				l.remove(scan.nextInt());
			}
		}
		scan.close();

		for (Integer num : l) {
			System.out.print(num + " ");
		}
	}
}

