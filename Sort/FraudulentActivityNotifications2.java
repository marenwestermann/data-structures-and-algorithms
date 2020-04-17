/* Problem: 
HackerLand National Bank has a simple policy for warning clients about possible 
fraudulent account activity. If the amount spent by a client on a particular day  
is greater than or equal to 2x the client's median spending for a trailing number 
of days, they send the client a notification about potential fraud. The bank doesn't 
send the client any notifications until they have at least that trailing number of 
prior days' transaction data.
Given the number of trailing days d and a client's total daily expenditures for 
a period of n days, find and print the number of times the client will receive a 
notification over all n days.

Note: 
this solution is based on the quick-select algorithm which has a runtime of O(n). 
This solution didn't meet the expected runtime requirement. */

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class FraudulentActivityNotifications2 {

    // helper function for swapping items
    private static void exch(int[] a, int i, int j) {
        
        // exchange a[i] and a[j]
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    
    // helper function for shuffling the array
    private static void shuffle(int[] a) {

        int n = a.length;
        for (int i = 0; i < n; i++) {
            // get random integer between 0 and n-i
            int uniform = (int) (Math.random() * n-i);
            // random number between i and n-1
            int r = i + uniform;
            exch(a, i, r);
        }
    }    
    
    // helper function for partitioning
    private static int partition(int[] a, int low, int high) {

        // set variables        
        int i = low+1;
        int j = high;
        int v = a[low]; // partitioning item
        
        // look through the array
        while (true) {
            // look for left swap
            while (a[i] < v) {
                if (i == high) break;
                i++;
            }
            // look for right swap
            while (v < a[j]) {
                if (j == low) break;
                j--;
            }
            // stop if pointers have crossed; otherwise swap
            if (i >= j) break;
            else exch(a, i, j);
        }

        // put partitioning item at a[j]
        exch(a, low, j);
        return j; // j is now in place
    }
    
    
    // helper function for selecting the kth item (quick-select algorithm)
    private static int select(int[] a, int k) {
        
        shuffle(a);
        int low = 0;
        int high = a.length -1;
        while (high > low) {
            int j = partition(a, low, high);
            if (j < k) low = j+1; // look to the right
            else if (j > k) high = j-1; // look to the left
            else return a[k]; // we found the kth item
        }
        return a[k];
    }
    
    // function for calculating the number of notifications    
    static int activityNotifications(int[] expenditure, int d) {

        // number of notifications
        int count = 0;

        // walk through array
        for (int i = 0; i <expenditure.length; i++) {
            
            // special case: insufficient prior data
            if (i < d) continue;
            
            // create subarray of length d from original array
            int [] prevDays = Arrays.copyOfRange(expenditure, i-d, i);

            // calculate the median; note: the array is 0-indexed
            double median;
            if (d % 2 != 0) {
                int medianPosition = d/2;
                median = (double) select(prevDays, medianPosition);
                // System.out.println(median);
            } else {
                int medianPositionLeft = d/2 -1;
                int medianPositionRight = d/2;
                median = ((double) select(prevDays, medianPositionLeft)
                       + (double) select(prevDays, medianPositionRight))/2;
                // System.out.println(median);
            }

            // check if we need to send a notifcation
            if (expenditure[i] >= median*2) count++;
        }

        return count;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nd = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nd[0]);

        int d = Integer.parseInt(nd[1]);

        int[] expenditure = new int[n];

        String[] expenditureItems = scanner.nextLine().split(" "); // read in numbers of array
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int expenditureItem = Integer.parseInt(expenditureItems[i]);
            expenditure[i] = expenditureItem;
        }

        int result = activityNotifications(expenditure, d);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
