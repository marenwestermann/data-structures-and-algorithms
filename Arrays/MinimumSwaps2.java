/* Problem: 
You are given an unordered array consisting of consecutive integers without any duplicates. 
You are allowed to swap any two elements. You need to find the minimum number of swaps 
required to sort the array in ascending order. */

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class MinimumSwaps2 {

    // Complete the minimumSwaps function below.
    static int minimumSwaps(int[] arr) {

        int count = 0;
        // walk through array 
        // note: the array is 0-indexed
        for (int i = 0; i < arr.length; i++) {
            // don't do anything if the number is in the correct place
            if (i+1 == arr[i]) continue;
            // otherwise find the correct number in the array and swap
            else {
                for (int j = i+1; j < arr.length; j++) {
                    if (arr[j] == i+1) {
                        int temp = arr[j];
                        arr[j] = arr[i];
                        arr[i] = temp;
                        count++;
                        break;
                    }
                }
            }
        }

        return count;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt(); // length of array
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) { // fill array
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int res = minimumSwaps(arr);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
