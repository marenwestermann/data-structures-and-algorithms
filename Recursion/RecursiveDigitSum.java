/* Problem: 
Find the super digit (super checksum) of a number. You are given two numbers 
n (as a string) and k. From these numbers the number p is created by concatenating 
the string n k times. Calculate the super digit of p. 
Example: 
    n = 148; k = 3; therefore p = 148148148
    superDigit(p) = 1+4+8+1+4+8+1+4+8 = 39
    superDigit(39) = 3+9 = 12
    superDigit(12) = 1+2 = 3
    Answer: 3
*/


import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class RecursiveDigitSum {

    // Complete the superDigit function below.
    static String superDigit(String n, int k) {

        // base case
        if (n.length() == 1) return n;
        
        // calculate checksum
        long sum = 0;
        for (int i = 0; i < n.length(); i++) {
            sum  += Character.getNumericValue(n.charAt(i));
        }
        
        // recursion
        // multiply sum by k and set k equal to 1 so we 
        // don't multiply every time
        return superDigit(Long.toString(sum*k), 1);


    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        String n = nk[0];

        int k = Integer.parseInt(nk[1]);

        String result = superDigit(n, k);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
