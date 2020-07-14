/* Problem:
Two strings are anagrams of each other if the letters of one string can be
rearranged to form the other string. Given a string, find the number of pairs
of substrings of the string that are anagrams of each other.
Example:
s = "mom"
The list of all anagrammatic pairs is [m, m] and [mo, om] at positions [[0],[2]]
and [[0,1],[1,2]], respectively. Therefore, the answer is 2.

Note:
This approach is a brute force approach that could be improved. */

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class SherlockAndAnagrams {

    // helper function for storing frequencies of characters in a hashmap
    private static HashMap<Character, Integer> charFrequencies(String str) {

        HashMap<Character, Integer> hm = new HashMap<Character, Integer>();

        // iterate over string
        for (int i = 0; i < str.length(); i++) {
            if (hm.containsKey(str.charAt(i))) {
                hm.put(str.charAt(i), hm.get(str.charAt(i)) +1);
            } else hm.put(str.charAt(i), 1);
        }

        return hm;
    }

    static int sherlockAndAnagrams(String s) {

        // set up counter
        int counter = 0;

        // gradually increase substring length l
        for (int l = 1; l < s.length(); l++) {

            // walk through string
            for (int i = 0; i <= (s.length() -l); i++) {
                
                // create substring
                String substring1 = s.substring(i,i+l);
                // store frequencies of characters in hashmap
                HashMap<Character, Integer> hm1 = charFrequencies(substring1);

                // walk through remaining string
                for (int j = i+1; j <= (s.length() -l); j++) {
                    
                    String substring2 = s.substring(j,j+l);
                    HashMap<Character, Integer> hm2 = charFrequencies(substring2);
                    // if the hashmaps are equal, an anagram is found
                    if (hm1.equals(hm2)) counter++;
                }
            }
        }

        return counter;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt(); // number of strings
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine(); // read in strings

            int result = sherlockAndAnagrams(s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
