/* Problem: 
It's New Year's Day and everyone's in line for the Wonderland rollercoaster ride! 
There are a number of people queued up, and each person wears a sticker indicating 
their initial position in the queue. Initial positions increment 1 by from 1 at the 
front of the line to n at the back.
Any person in the queue can bribe the person directly in front of them to swap positions. 
If two people swap positions, they still wear the same sticker denoting their original 
places in line. One person can bribe at most two others. For example, if n = 8
and Person 5 bribes Person 4, the queue will look like this: 1,2,3,5,4,6,7,8 .
Fascinated by this chaotic queue, you decide you must know the minimum number of bribes 
that took place to get the queue into its current state! 

Comment: 
This solution didn't meet the expected runtime requirement. I didn't take full
advantage of the fact that people can move forward by a max of two spots. */

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class NewYearChaos1 {

    // Complete the minimumBribes function below.
    static int minimumBribes(int[] q) {

        // store array entries in hashset; we wan't to use the contains()
        // function which has linear runtime complexity in a hashset
        HashSet<Integer> hs = new HashSet<Integer>();
        for (int i = 0; i < q.length; i++) {
            hs.add(q[i]);
        }
        
        // walk through array
        // note: it needs to be taken into account that the array is 0 indexed
        int count = 0;
        
        for (int i = 0; i < q.length; i++) {
                // special case: the queue is too chaotic; this means someone 
                // moved more than two spots forward
            if ((i+1)-q[i] < -2) {
                return -1;
            } else {
                // check if there are smaller numbers in the hashset than the 
                // current one; note: there can only be a max of two smaller
                // numbers as one person can only bribe twice
                for (int j = 1; j < q[i]; j++) {
                    if (hs.contains(j)) count++;
                }
                // remove number from hashset (because we've analysed it)
                hs.remove(q[i]);
                
            }
        }
        return count;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt(); // number of test cases
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt(); // length of array
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] q = new int[n];

            String[] qItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) { // fill array
                int qItem = Integer.parseInt(qItems[i]);
                q[i] = qItem;
            }

            if (minimumBribes(q) == -1) {
                System.out.println("Too chaotic");
            } else {
                System.out.println(minimumBribes(q));
            }
        }

        scanner.close();
    }
}

