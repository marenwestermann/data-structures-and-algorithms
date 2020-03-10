/* You are given a phone book that consists of people's names and their phone number. 
After that you will be given some person's name as query. For each query, print the 
phone number of that person. */

//Complete this code or write your own from scratch
import java.util.*;
import java.io.*;

class JavaMap{

	public static void main(String []argh)
	{
		Scanner in = new Scanner(System.in);
		int n=in.nextInt(); // number of people + phone number combinations to add
		in.nextLine();
		Map<String,Integer> hm = new HashMap<String, Integer>();

		// fill phone book
        for(int i=0;i<n;i++)
		{
			String name=in.nextLine();
			int phone=in.nextInt();
			in.nextLine();
            hm.put(name, phone);
		}
		
		// retreive phone number
		while(in.hasNext())
		{
			String s=in.nextLine();
            if (hm.containsKey(s)) {
                System.out.println(s + "=" + hm.get(s));
            } else {
                System.out.println("Not found");
            }
		}
        in.close();
	}
}
