/* Problem:
Calculate the nth Fibonacci number.

Constraints:
0 < n ≤ 30

Note:
this is my recursive implementation. An iterative
implmentation can be found in the "Arrays" folder. */

import java.util.*;

public class RecursionFibonacciNumbers {

    public static int fibonacci(int n) {

        // create base cases
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        // recursion
        } else {
            return fibonacci(n-1) + fibonacci(n-2);
        }
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();
        System.out.println(fibonacci(n));
    }
}
