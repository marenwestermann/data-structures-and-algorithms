/* Problem: 
Given two strings, a and b, that may or may not be of the same length, determine 
the minimum number of character deletions required to make a and b anagrams. Any 
characters can be deleted from either of the strings.
We consider two strings to be anagrams of each other if the first string's letters 
can be rearranged to form the second string.In other words, both strings must 
contain the same exact letters in the same exact frequency. */ 

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class StringsMakingAnagrams {

    // helper function for storing frequencies of characters in a hashmap
    private static HashMap<Character, Integer> charFrequencies(String s) {

        HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
        
        for (int i = 0; i < s.length(); i++) {
            if (hm.containsKey(s.charAt(i))) {
                hm.put(s.charAt(i), hm.get(s.charAt(i)) +1);
            } else hm.put(s.charAt(i), 1);
        }

        return hm;
    }

    // helper function for calculating differences in character frequencies
    private static int getDifferences(HashMap<Character, Integer> hmX, HashMap<Character, Integer> hmY) {
        
        int count = 0;
        for (Map.Entry<Character, Integer> entry : hmX.entrySet()) {
            if (!hmY.containsKey(entry.getKey())) {
                count += entry.getValue();
            } else if (entry.getValue() > hmY.get(entry.getKey())) {
                count += entry.getValue() - hmY.get(entry.getKey());
            }
        }
        return count;
    }
    
    // function for calculating the number of deletions
    static int makeAnagram(String a, String b) {

        // store character frequencies in hashmap
        HashMap<Character, Integer> hmA = charFrequencies(a);
        HashMap<Character, Integer> hmB = charFrequencies(b);

        return getDifferences(hmA, hmB) + getDifferences(hmB, hmA);

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String a = scanner.nextLine();

        String b = scanner.nextLine();

        int res = makeAnagram(a, b);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
