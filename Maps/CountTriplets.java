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
this solution works if the constraint i < j < k is neglected (i.e. the order of numbers
in the array is irrelevant). */

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

public class CountTriplets {

    // Complete the countTriplets function below.
    static long countTriplets(List<Long> arr, long r) {

        // create hashmap
        HashMap<Long, Long> hm = new HashMap<Long, Long>();

        // build dictionary (store the frequency of number occurences)
        for (Long l : arr) {
            if (hm.containsKey(l)) {
                hm.put(l, hm.get(l) + 1);
            } else {
                hm.put(l, (long) 1);
            }
        }

        // count triplets
        long counter = 0;

        // edge case: r==1
        if (r==1) {
            return hm.get(arr.get(0))*(hm.get(arr.get(0))-1)*(hm.get(arr.get(0))-2)/6;
        }

        // walk through list
        for (Long k : arr) {
            // define second and third number
            long secondNumber = k*r;
            long thirdNumber = k*r*r;
            // multiply the frequencies of the triplets (1 refers to the current number)
            if (hm.containsKey(secondNumber) && hm.containsKey(thirdNumber)) {
                counter += 1*hm.get(secondNumber)*hm.get(thirdNumber);
            }
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
