/* Given a string, s, matching the regular expression [A-Za-z !,?._'@]+, 
split the string into tokens. We define a token to be one or more 
consecutive English alphabetic letters. Then, print the number of tokens, 
followed by each token on a new line. */

import java.io.*;
import java.util.*;

public class JavaStringTokens {

    public static void main(String[] args) {
        
		Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();

        String s_trim = s.trim(); // remove leading and trailing spaces
        String[] arrOfStrings = s_trim.split("[ !,?._'@]+");
        
		// print 0 if string is empty
        if (s_trim.equals("")) { 
            System.out.println(0);
        }
		// don't process very large strings      
		else if (s_trim.length() > 400000) {
            return;
        }
		else {
			// number of tokens            
			System.out.println(arrOfStrings.length);

			// print each token
		    for (String str : arrOfStrings)
		        System.out.println(str);
        }
        
        scan.close();
    }
}

