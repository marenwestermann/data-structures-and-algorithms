/* Problem: 
Given a histogram, find the largest rectangle in the histogram. The bars in the
histogram have a bin width of 1. 

Note: 
The runtime of this solution is O(n).*/

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class LargestRectangle {

    // helper function for retrieving element on top of stack
    private static int peek(ArrayList<Integer> stack) {
        return stack.get(stack.size() -1);
    }

    // helper function for saving and removing element on top of stack
    private static int saveAndRemoveLast(ArrayList<Integer> stack) {
        int top = peek(stack);
        stack.remove(stack.size() - 1);
        return top;
    }

    // helper function for calculating maximum area
    private static long calculateArea(ArrayList<Integer> stack, int[] heights, int top, int i, long area) {
        // notes: 
        // heights[top]: height of bar in histogram at given index
        // i: right border of rectangle (right index)
        // peek(stack): left border of rectangle (left index)
        // -1: index correction
        long areaTemp;
        areaTemp = heights[top] * (!stack.isEmpty() ? i - peek(stack) - 1 : i);
        return Math.max(area, areaTemp);
    }

    static long largestRectangle(int[] h) {

        // set up variables
        ArrayList<Integer> positions = new ArrayList<Integer>();
        long area = 0;
        int stackTop;

        // walk through array
        int i = 0;
        while (i < h.length) {
            // if stack is empty or current height is greater than or equal to
            // the one on top of the stack, push current position to stack
            // and move forward
            if (positions.isEmpty() || h[i] >= h[peek(positions)]) {
                positions.add(i++);
            }
            // if current height is lower than the one on top of the stack,
            // remove the position on top of the stack and calculate area
            // note: we don't move forward here
            else {
                stackTop =  saveAndRemoveLast(positions);
                area = calculateArea(positions, h, stackTop, i, area);
            }
        }

        // end of array: empty stack
        while (!positions.isEmpty()) {
            stackTop =  saveAndRemoveLast(positions);
            area = calculateArea(positions, h, stackTop, i, area);
        }

        return area;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt(); // number of bins (heights) in the histogram
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] h = new int[n];

        String[] hItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) { // fill histogram array with heights
            int hItem = Integer.parseInt(hItems[i]);
            h[i] = hItem;
        }

        long result = largestRectangle(h);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
