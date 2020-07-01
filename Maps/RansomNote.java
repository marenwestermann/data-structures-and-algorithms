/* Problem:
Harold is a kidnapper who wrote a ransom note, but now he is worried it will be
traced back to him through his handwriting. He found a magazine and wants to know
if he can cut out whole words from it and use them to create an untraceable replica
of his ransom note. The words in his note are case-sensitive and he must use only
whole words available in the magazine. He cannot use substrings or concatenation
to create the words he needs.
Given the words in the magazine and the words in the ransom note, print 'Yes' if he
can replicate his ransom note exactly using whole words from the magazine; otherwise,
print 'No'. */

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class RansomNote {

    // Complete the checkMagazine function below.
    static void checkMagazine(String[] magazine, String[] note) {

        // create hashmap
        HashMap<String, Integer> hm = new HashMap<String, Integer>();

        // read in words from the magazine and keep track of their frequency
        // (build dictionary)
        for (int i = 0; i < magazine.length; i++) {
            if (hm.containsKey(magazine[i])) {
                hm.put(magazine[i], hm.get(magazine[i]) + 1);
            } else {
                hm.put(magazine[i], 1);
            }
        }

        // check if ransom can be created from the words in the magazine
        for (int j = 0; j < note.length; j++) {
            if (!hm.containsKey(note[j]) || hm.get(note[j]) == 0) {
                System.out.println("No");
                return;
            } else {
                hm.put(note[j], hm.get(note[j]) - 1);
            }
        }
        System.out.println("Yes");

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] mn = scanner.nextLine().split(" ");

        int m = Integer.parseInt(mn[0]); // number of words in the magazine

        int n = Integer.parseInt(mn[1]); // number of words in the ransom

        String[] magazine = new String[m];

        String[] magazineItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            String magazineItem = magazineItems[i];
            magazine[i] = magazineItem;
        }

        String[] note = new String[n];

        String[] noteItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            String noteItem = noteItems[i];
            note[i] = noteItem;
        }

        checkMagazine(magazine, note);

        scanner.close();
    }
}
