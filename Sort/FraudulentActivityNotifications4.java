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
this solution is based on the counting sort algorithm which has a runtime of O(n+k) 
where n = number of items in the array and k = range of possible numbers. This 
solution is the fastest of all my solutions and met the expected runtime requirement. */

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class FraudulentActivityNotifications4 {

    // helper function for calculating the median
    private static int medianHelper(int[] arr, int maxSpend, int medianPos) {
        int intAtMedianPosition = 0;
        for (int i = 0; i < maxSpend+1; i++) {
            if (arr[i] >= medianPos) {
                intAtMedianPosition = i;
                break;
            }
        }
        return intAtMedianPosition;
    }

    // function for calculating the number of notifications 
    static int activityNotifications(int[] expenditure, int d) {

        // initialise notifications and max. expenditure
        int notifications = 0;
        int max = 200;

        // create array for storing occurrences of numbers
        // note: it is initialised it with 0s
        int[] count = new int[max+1];

        // store counts of numbers
        for (int i = 0; i < d; i++) {
            count[expenditure[i]]++;
        }
        
        // walk through expenditure array
        for (int i = d; i < expenditure.length; i++) {
            
            // add up counts; with this array we can determine the positions of numbers 
            // in a sorted array; note: the last number equals the length of the array
            int[] addedCounts = count.clone();
            for (int j = 1; j < max+1; j++) {
                addedCounts[j] += addedCounts[j-1];
            }

            // calculate the median;
            double median;
            if (d % 2 != 0) {
                int medianPosition = d/2 + 1;
                median = (double) medianHelper(addedCounts, max, medianPosition);
            } else {
                int medianPositionLeft = d/2;
                int medianPositionRight = d/2 + 1;
                median = ((double) medianHelper(addedCounts, max, medianPositionLeft)
                       + (double) medianHelper(addedCounts, max, medianPositionRight))/2;
            }

            // check if we need to send a notifcation
            if (expenditure[i] >= median*2) notifications++;

            // update count array
            count[expenditure[i]]++;
            count[expenditure[i-d]]--;
        }

        return notifications;
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
