/* In this problem, you are given N integers. You need to find the maximum number 
of unique integers among all the possible contiguous subarrays of size M. Time limit 
is 3 second for this problem. */ 

import java.util.*;
public class JavaDequeue {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Deque<Integer> deque = new ArrayDeque<Integer>();
		int n = in.nextInt(); // number of integers
		int m = in.nextInt(); // size of subarray
		int nunique = 0; // keep track of number of unique numbers in deque

		// create hashset
		HashSet<Integer> hs = new HashSet<Integer>(); 
		
		// fill deque and hashset with m entries (start)
		for (int i = 0; i < m; i++) {
			int num = in.nextInt();
			deque.addLast(num);
			hs.add(num);
		}

		// get size of hashset
		nunique = hs.size();
		// cut runtime if possible
		if (nunique == m) System.out.println(nunique);
		else {
			
			// scan through numbers 
			for (int j = m; j < n; j++) {
				            
				// remove first number from deque
				int first_num = deque.removeFirst();
				// remove this number from set if it's not in deque anymore
				if (!deque.contains(first_num)) hs.remove(first_num);
				
				// add next number to deque and set 
				int num = in.nextInt();
				deque.addLast(num);
				hs.add(num);
				
				// update size of hashset if necessary
				if (hs.size() > nunique) nunique = hs.size();

				// cut runtime if possible
				if (nunique == m) break;
			}
			System.out.println(nunique);
		}
	}
}

