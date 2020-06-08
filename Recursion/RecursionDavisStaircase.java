/* Problem: 
Davis has a number of staircases in his house and he likes to climb each staircase 1, 2, or 3
steps at a time. Being a very precocious child, he wonders how many ways there are to reach the 
top of the staircase.
Given the respective heights for each of the staircases in his house, find and print the number 
of ways he can climb each staircase. */

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class RecursionDavisStaircase {

    // Complete the stepPerms function below.
    static int stepPerms(int n, int[] array) {

        // check if stepPerms has already been calculated
        if (n > 0 && array[n] != 0) return array[n];

        // base case
        if (n == 0) return array[n];

        // recursion
        if (n == 1) { 
            array[n] = stepPerms(n - 1, array); }
        else if (n == 2) {
            array[n] = stepPerms(n - 1, array) + stepPerms(n-2, array); }
        else {
            array[n] = stepPerms(n-1, array) + stepPerms(n-2, array) + stepPerms(n-3, array); }
        
		return array[n];
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int s = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int sItr = 0; sItr < s; sItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            // initialise array
            int[] arr = new int[n+1];
            // fill first position (base case)
            arr[0] = 1;

            int res = stepPerms(n, arr);

            bufferedWriter.write(String.valueOf(res));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
