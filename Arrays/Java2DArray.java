/* Problem: 
You are given a 6*6 2D array. An hourglass in an array is a portion shaped like this:
a b c
  d
e f g
The sum of an hourglass is the sum of all the numbers within it. In this problem you 
have to print the largest sum among all the hourglasses in the array. */ 

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Java2DArray {

    // create hourglass subarray
    private static int[][] hourglass(int[][] arr, int m, int n) {
        int[][] subArr = new int[3][]; // creates array of: [null, null, null]
        for (int i = 0; i < 3; i++) {
            subArr[i] = Arrays.copyOfRange(arr[i+m], 0+n, 3+n);
        }

        subArr[1][0] = 0;
        subArr[1][2] = 0;

        return subArr;
    }

    // sum array entries
    private static int sumHourglass(int[][] arr) {
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sum += arr[i][j];
            }
        }
        return sum;
    }

    // find largest sum
    private static int findLargestSum(int[][] array) {
        int largestSum = -64; // largest possible neg number is (-9*7) = 63
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int[][] glass = hourglass(array, i, j);
                int sum = sumHourglass(glass);
                if (sum > largestSum) largestSum = sum;
            }
        }
        return largestSum;
    }
    

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int[][] arr = new int[6][6];

        for (int i = 0; i < 6; i++) {
            String[] arrRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 6; j++) {
                int arrItem = Integer.parseInt(arrRowItems[j]);
                arr[i][j] = arrItem;
            }
        }

        scanner.close();

        int largestSum = findLargestSum(arr);
        System.out.println(largestSum);
    }
}
