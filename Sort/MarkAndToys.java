/* Problem: 
Mark and Jane are very happy after having their first child. Their son loves toys, 
so Mark wants to buy some. There are a number of different toys lying in front of him, 
tagged with their prices. Mark has only a certain amount to spend, and he wants to 
maximize the number of toys he buys with this money.
Given a list of prices and an amount to spend, what is the maximum number of toys Mark 
can buy? For example, if prices = [1,2,3,4] and Mark has k = 7 to spend, he can buy 
items [1,2,3] for 6, or [3,4] for 7 units of currency. He would choose the first group 
of 3 items. */

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class MarkAndToys {

    // Complete the maximumToys function below.
    static int maximumToys(int[] prices, int k) {
        // sort the array in ascending order(based on quicksort algorithm)
        Arrays.sort(prices);
        // keep buying items until we reach the budget limit
        int count = 0;
        int sum = 0;
        for (int i = 0; i < prices.length; i++) {
            if (k > sum) {
                sum = sum+prices[i];
                    if (sum > k) break;
                count++;
            }
        }
        
        return count;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]); // number of toys

        int k = Integer.parseInt(nk[1]); // budget limit

        int[] prices = new int[n];

        String[] pricesItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) { // fill array with prices
            int pricesItem = Integer.parseInt(pricesItems[i]);
            prices[i] = pricesItem;
        }

        int result = maximumToys(prices, k);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

