/* A string containing only parentheses is balanced if the following is true: 
1. if it is an empty string 
2. if A and B are correct, AB is correct, 
3. if A is correct, (A) and {A} and [A] are also correct.
Examples of some correctly balanced strings are: "{}()", "[{()}]", "({()})"
Examples of some unbalanced strings are: "{}(", "({)}", "[[", "}{" etc.
Given a string, determine if it is balanced or not. */

import java.util.*;


class Stack {
    
	private Node first = null;

	private static class Node {
		char item;
		Node next;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public void push(char item) {
		Node oldfirst = first;
		first = new Node(); 
		first.item = item;
		first.next = oldfirst;
	}

	public void pop() {
		first = first.next;
	}

	public char peek() {
		if (isEmpty()) throw new NoSuchElementException("Stack underflow");
		return first.item;
	}
}
    
public class JavaStack {

	public static void main(String []args) {

		Scanner sc = new Scanner(System.in);
		Stack stack = new Stack();
		
		while (sc.hasNext()) {
			String s = sc.next(); // read in string
			
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				if (c == '(' || c == '{' || c == '[') stack.push(c);
				else {
					if (c == ')' && (!stack.isEmpty()) && stack.peek() == '(') {
						stack.pop(); }
					else if (c == '}' && (!stack.isEmpty()) && stack.peek() == '{') {
						stack.pop(); }
					else if (c == ']' && (!stack.isEmpty()) && stack.peek() == '[') {
						stack.pop(); }
					else { 
						stack.push(c);
						break; }
				}
			}
			// print result
			System.out.println(stack.isEmpty()); 

			// clear list
			while (!stack.isEmpty()) {
			    stack.pop();
			}
		}
		
	}
}

