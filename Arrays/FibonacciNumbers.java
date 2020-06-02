/* Problem:
Calculate the nth Fibonacci number.

Constraints:
0 < n ≤ 30

Note:
this is my iterative implementation. A recursive
implmentation can be found in the "Recusion" folder. */

import java.util.*;

public class FibonacciNumbers {

    public static int fibonacci(int n) {

        // initialise array
        int[] array = new int[31];

        // fill first two positions (starting condition)
        array[0] = 0;
        array[1] = 1;

        // iteratively fill remaining positions
        for (int i = 2; i < array.length; i++) {
            array[i] = array[i-1] + array[i-2];
        }

        // return fibonaccy number at position n
        return array[n];
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();
        System.out.println(fibonacci(n));
    }
}
