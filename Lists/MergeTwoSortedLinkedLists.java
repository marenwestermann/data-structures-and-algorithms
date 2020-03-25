/* Problem:
You’re given the pointer to the head nodes of two sorted linked lists. The data 
in both lists will be sorted in ascending order. Change the next pointers to obtain 
a single, merged linked list which also has data in ascending order. Either head 
pointer given may be null meaning that the corresponding list is empty. */

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class MergeTwoSortedLinkedLists {

	static class SinglyLinkedListNode {
		public int data;
		public SinglyLinkedListNode next;

		public SinglyLinkedListNode(int nodeData) {
			this.data = nodeData;
			this.next = null;
		}
	}

	static class SinglyLinkedList {
		public SinglyLinkedListNode head;
		public SinglyLinkedListNode tail;

		public SinglyLinkedList() {
			this.head = null;
			this.tail = null;
		}

		public void insertNode(int nodeData) {
			SinglyLinkedListNode node = new SinglyLinkedListNode(nodeData);

			if (this.head == null) {
				this.head = node;
			} else {
				this.tail.next = node;
			}

			this.tail = node;
		}
	}

	public static void printSinglyLinkedList(SinglyLinkedListNode node, String sep, BufferedWriter bufferedWriter) throws IOException {
		while (node != null) {
			bufferedWriter.write(String.valueOf(node.data));

			node = node.next;

			if (node != null) {
				bufferedWriter.write(sep);
			}
		}
	}

	// Complete the mergeLists function below.

	/*
	 * For your reference:
	 *
	 * SinglyLinkedListNode {
	 *     int data;
	 *     SinglyLinkedListNode next;
	 * }
	 *
	 */

	static SinglyLinkedListNode mergeLists(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
		// special case: both linked lists are empty
		if (head1 == null && head2 == null) return null;

		// create dummy node
		SinglyLinkedListNode dummy = new SinglyLinkedListNode(0);
		// create pointer
		SinglyLinkedListNode current = dummy;

		while (head1 != null || head2 != null) {

			// special cases: one linked list is empty or 
			// we reach the end of one of the lists
			if (head1 == null) {
				current.next = head2;
				break;
			}
			if (head2 == null) {
				current.next = head1;
				break;
			}

			// check which node is smaller and append
			if (head1.data < head2.data) {
				// add node
				current.next = head1;
				// move pointers forward
				current = current.next;
				head1 = head1.next;
			} else {
				// add node
				current.next = head2;
				// move pointers forward
				current = current.next;
				head2 = head2.next;
			}
		}

		return dummy.next;

	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int tests = scanner.nextInt(); // number of test cases
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int testsItr = 0; testsItr < tests; testsItr++) {
			SinglyLinkedList llist1 = new SinglyLinkedList();

			int llist1Count = scanner.nextInt(); // length of list
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

			for (int i = 0; i < llist1Count; i++) { // fill list with nodes
				int llist1Item = scanner.nextInt();
				scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

				llist1.insertNode(llist1Item);
			}

			SinglyLinkedList llist2 = new SinglyLinkedList();

			int llist2Count = scanner.nextInt(); // length of list
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

			for (int i = 0; i < llist2Count; i++) { // fill list with nodes
				int llist2Item = scanner.nextInt();
				scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

				llist2.insertNode(llist2Item);
			}

			SinglyLinkedListNode llist3 = mergeLists(llist1.head, llist2.head);

			printSinglyLinkedList(llist3, " ", bufferedWriter);
			bufferedWriter.newLine();
		}

		bufferedWriter.close();

		scanner.close();
	}
}
