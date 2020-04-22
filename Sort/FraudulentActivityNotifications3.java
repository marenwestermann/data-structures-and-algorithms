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
this solution is based on the heapsort algorithm which has a runtime of O(n log n). 
This solution didn't meet the expected runtime requirement. */

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class FraudulentActivityNotifications3 {

    // Complete the activityNotifications function below.
    static int activityNotifications(int[] expenditure, int d) {

        // number of notifications
        int count = 0;

        // create priority queue
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        
        // walk through array
        for (int i = 0; i <expenditure.length; i++) {
            
            // special case: insufficient prior data
            if (i < d) continue;
            
            // add values of previous days d to priority queue
            for (int j = i-d; j < i; j++) pq.add(expenditure[j]);
            
            // put values of priority queue into array in sorted order
            int[] prevDays = new int[d];
            int k = 0; 
            while (!pq.isEmpty()) {
                prevDays[k] = pq.poll();
                k++;
            }

            // calculate the median; note: the array is 0-indexed
            double median;
            if (d % 2 != 0) median = prevDays[d/2];
            else median = ((double) prevDays[d/2 -1] + (double) prevDays[d/2])/2;

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
