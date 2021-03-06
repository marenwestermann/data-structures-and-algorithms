/* Problem: Find the height of a binary tree. The height of a binary tree 
is the number of edges between the tree's root and its furthest leaf. */ 

import java.util.*;
import java.io.*;

class Node {

    Node left;
    Node right;
    int data;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

class TreeHeightOfABinaryTree {

    public static int height(Node root) {

        // base case: we reach a leaf
        if (root == null) return -1;

        // recursively compute the height of each subtree
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        // keep track of greatest height
        return (Math.max(leftHeight, rightHeight) + 1);

    }

    public static Node insert(Node root, int data) {
        if(root == null) {
            return new Node(data);
        } else {
            Node cur;
            if(data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        Node root = null;
        while(t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        scan.close();
        int height = height(root);
        System.out.println(height);
    }
}
