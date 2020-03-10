/* You are given n pairs of strings. Two pairs (a,b) and (c,d) are identical if 
a=c and b=d. That also implies (a,b) is not same as (b,a). After taking each pair 
as input, you need to print number of unique pairs you currently have. */ 

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Hashset {

	public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int t = s.nextInt(); // number of pairs
        String [] pair_left = new String[t];
        String [] pair_right = new String[t];
        
		// read in pairs and store them separately in arrays
        for (int i = 0; i < t; i++) {
            pair_left[i] = s.next();
            pair_right[i] = s.next();
        }

		// combine pairs and store in hashset; print size of hashset
        Set<String> hs = new HashSet<String>();
        for (int i = 0; i < t; i++) {
            hs.add(pair_left[i] + " "+ pair_right[i]);
            System.out.println(hs.size());
        }

	}
}
