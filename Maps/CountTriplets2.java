/* Problem: 
You are given an array of integers and you need to find the number of triplet
indices (i, j, k). The integers at those indices have to be in geometric progression
for a given common ratio r.

Constraints:
1 ≤ n ≤ 10**5 (size of the array)
1 ≤ r ≤ 10**9 (common ratio)
1 ≤ arr[i] ≤ 10**9 (numbers in the array)
i < j < k

Example:
arr = [1, 4, 16, 64]
r = 4
Result: We have 2 triplets [1, 4, 16] and [4, 16, 64] at indices (0, 1, 2) and (1, 2, 3),
respectively.

Note:
For successfully solving this problem I needed to shift my perspective from the first
triplet number to the middle triplet number. This required using two hashmaps instead
of one (compare CountTriplets.java). The idea is to view every number as a potential
middle triplet number and check the frequencies of the previous and next number in the
geometric progression if these exist. */

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class CountTriplets2 {

    // Complete the countTriplets function below.
    static long countTriplets(List<Long> arr, long r) {

        // create hashmaps
        HashMap<Long, Long> left = new HashMap<Long, Long>();
        HashMap<Long, Long> right = new HashMap<Long, Long>();

        // build dictionary for right hashmap
        // (store the frequencies of number occurrences)
        for (Long l : arr) {
            if (right.containsKey(l)) right.put(l, right.get(l) + 1);
            else right.put(l, 1L);
        }

        // set up counter
        long counter = 0;


        // walk through list
        for (Long k : arr) {

            // calculate previous and next number
            long previous = 0;
            if (k % r == 0) previous = k/r;
            long next = k*r;

            // set up variables for frequencies of the previous and next number
            long freqPrev = 0;
            long freqNext = 0;

            // remove current number from right dictionary
            right.put(k, right.get(k) - 1);

            // get frequency of next number if present in right dictionary
            if (right.containsKey(next)) {
                freqNext = right.getOrDefault(next, 0L);
            }

            // get frequency of previous number if present in left dictionary
            if (left.containsKey(previous)) {
                freqPrev = left.getOrDefault(previous, 0L);
            }

            // update counter
            counter += freqNext * freqPrev;

            // insert current number into left dictionary
            if (left.containsKey(k)) left.put(k, left.get(k) + 1);
            else left.put(k, 1L);
        }

        return counter;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(nr[0]); // size of the array

        long r = Long.parseLong(nr[1]); // common ratio r

        List<Long> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Long::parseLong)
            .collect(toList());

        long ans = countTriplets(arr, r);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
