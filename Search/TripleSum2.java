/* Problem:
Given 3 arrays a, b, c of different sizes, find the number of distinct triplets (p, q, r)
where p ∈ a, q ∈ b, and r ∈ c. The triplets must satisfy the criteria p ≤ q and q ≥ r.
Only unique numbers in each array are considered for distinct triplets.

Example:
a = [3, 5, 7], b = [3, 6], c = [4, 6, 9]. We can find 4 distinct triplets here:
(3, 6, 4), (3, 6, 6), (5, 6, 4), (5, 6, 6).

Constraints:
length of the arrays: 1 to 1e5
values of elements in the arrays: 1 to 1e8 */


import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class TripleSum2 {

    // helper function for keeping only unique numbers in an array
    private static int[] selectUnique(int[] arr) {

        // convert array to set
        Set<Integer> set = new HashSet<Integer>();
        for (int i : arr)
            set.add(i);

        // convert set to array
        int n = set.size();
        int[] newArr = new int[n];

        int j = 0;
        for (int x : set)
            newArr[j++] = x;

        return newArr;
    }


    static long triplets(int[] a, int[] b, int[] c) {

        // keep only unique numbers in the arrays
        a = selectUnique(a);
        b = selectUnique(b);
        c = selectUnique(c);

        // sort arrays (based on quicksort algorithm)
        Arrays.sort(a);
        Arrays.sort(b);
        Arrays.sort(c);

        // set up variables for storing how many values <= b[i] there are
        // in array a and c, respectively
        int numA = 0;
        int numC = 0;

        // set up counter
        long total = 0L;

        // iterate over integers in array b
        for (int i = 0; i < b.length; i++) {

            // check how many values are <= b[i] in arrays a and c
            // note: numA and numC are not reset to 0 after each iteration
            // through the for-loop (which is why array b is sorted above)
            while (numA < a.length && a[numA] <= b[i]) numA++;
            while (numC < c.length && c[numC] <= b[i]) numC++;

            // convert numA and numC to long datatype
            long numALong = (long) numA;
            long numCLong = (long) numC;

            // update total number of triplets
            total += numALong * numCLong;
        }

        return total;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] lenaLenbLenc = scanner.nextLine().split(" "); // read in length of arrays

        int lena = Integer.parseInt(lenaLenbLenc[0]);

        int lenb = Integer.parseInt(lenaLenbLenc[1]);

        int lenc = Integer.parseInt(lenaLenbLenc[2]);

        // declare and fill array a
        int[] arra = new int[lena];

        String[] arraItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < lena; i++) {
            int arraItem = Integer.parseInt(arraItems[i]);
            arra[i] = arraItem;
        }

        // declare and fill array b
        int[] arrb = new int[lenb];

        String[] arrbItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < lenb; i++) {
            int arrbItem = Integer.parseInt(arrbItems[i]);
            arrb[i] = arrbItem;
        }

        // declare and fill array c
        int[] arrc = new int[lenc];

        String[] arrcItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < lenc; i++) {
            int arrcItem = Integer.parseInt(arrcItems[i]);
            arrc[i] = arrcItem;
        }

        long ans = triplets(arra, arrb, arrc);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
