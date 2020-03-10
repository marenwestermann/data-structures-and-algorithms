/* Problem: 
Given two strings, determine if they share a common substring. A substring may be 
as small as one character. For example, the words "a", "and", "art" share the common 
substring a. The words "be" and "cat" do not share a substring. */

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class TwoStrings {
.
    static String twoStrings(String s1, String s2) {
        // We only need to look at single character substrings. If two strings
        // don't share at least one character, they don't share longer substrings.
        
        HashSet<Character> hs1 = new HashSet<Character>();
        HashSet<Character> hs2 = new HashSet<Character>();

        // fill hashset1 with characters from string1
        for (int i = 0; i < s1.length(); i++) {
            hs1.add(s1.charAt(i));
        }

        // fill hashset2 with characters from string2
        for (int i = 0; i < s2.length(); i++) {
            hs2.add(s2.charAt(i));
        }

        // check for intersecting characters
        hs1.retainAll(hs2);
        if (!hs1.isEmpty()) return "YES";

        return "NO";

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s1 = scanner.nextLine();

            String s2 = scanner.nextLine();

            String result = twoStrings(s1, s2);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
