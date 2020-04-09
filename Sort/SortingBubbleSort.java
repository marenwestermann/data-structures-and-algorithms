/* Problem: 
Given an array of integers, sort the array in ascending order using the Bubble Sort algorithm. 
Once sorted, print the following three lines:
1. Array is sorted in <numSwaps> swaps.
2. First Element: <firstElement>
3. Last Element: <lastElement> */


import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class SortingBubbleSort {

    // Complete the countSwaps function below.
    static void countSwaps(int[] a) {

        // start with the assumption that the array is not sorted
        boolean sorted = false;
        // initialise counter for number of swaps
        int count = 0;
        // keep track of last unsorted element for runtime optimisation (after each
        // walk through the array the last element will be in the correct place)
        int lastUnsorted = a.length -1;

        // keep sorting until all elements are sorted; this can require 
        // multiple walks through the array
        while (!sorted) {
            // assume the array is sorted; if not, the value will be 
            // set to false and we'll walk through the array again
            sorted = true;
            // walk through the array
            for (int i = 0; i < lastUnsorted; i++) {
                if (a[i] > a[i+1]) {
                    int temp = a[i];
                    a[i] = a[i+1];
                    a[i+1] = temp;
                    sorted = false;
                    count++;
                } 
            }
            lastUnsorted--;
        }

        System.out.println("Array is sorted in " + count + " swaps.");
        System.out.println("First Element: " + a[0]);
        System.out.println("Last Element: " + a[a.length-1]);

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt(); // length of array
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] a = new int[n];

        String[] aItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) { // fill array
            int aItem = Integer.parseInt(aItems[i]);
            a[i] = aItem;
        }

        countSwaps(a);

        scanner.close();
    }
}
