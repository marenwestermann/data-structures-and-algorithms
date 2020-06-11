/* Problem: You are given a pointer to the root node of a binary search tree and the
values of two nodes in the tree, v1 and v2. (Note: the tree only has single data values
and no key: values pairs.) You need to return the lowest common ancestor (LCA) of the
nodes containing values v1 and v2.

Note: recursive solution. */

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

class BinarySearchTreeLowestCommonAncestor2 {

    public static Node lca(Node root, int v1, int v2) {
        // Check if both child nodes lie to the left or right of the parent node.
        // If they lie on either side, the current node must be the LCA.

        // recursion
        if (v1 < root.data && v2 < root.data) {
            return lca(root.left, v1, v2); }
        if (v1 > root.data && v2 > root.data) {
            return lca(root.right, v1, v2); }

        // base case
        return root;
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
        int v1 = scan.nextInt();
        int v2 = scan.nextInt();
        scan.close();
        Node ans = lca(root,v1,v2);
        System.out.println(ans.data);
    }    
}
