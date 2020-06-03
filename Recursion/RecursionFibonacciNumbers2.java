/* Problem:
Calculate the nth Fibonacci number.

Constraints:
0 < n ≤ 30

Note:
this is my recursive implementation with memoisation. An iterative
implmentation can be found in the "Arrays" folder. */

import java.util.*;

public class RecursionFibonacciNumbers2 {

    public static int fibonacci(int n, int[] array) {

        // check if fibonacci(n) has already been calculated
        if (n > 1 && array[n] != 0) return array[n];

        // base cases
        if (n == 0) return array[n];
        else if (n == 1) return array[n];
        // recursion
        array[n] = fibonacci(n-1, array) + fibonacci(n-2, array);

        return array[n];
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();

        // initialse array
        int[] arr = new int[n+1];
        // fill first two positions (starting condition)
        arr[0] = 0;
        arr[1] = 1;

        System.out.println(fibonacci(n, arr));
    }
}
