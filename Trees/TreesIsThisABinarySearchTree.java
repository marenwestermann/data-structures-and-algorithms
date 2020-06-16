/* Problem: Write a function that checks if a binary tree is a binary search tree.
The function must return a boolean denoting whether or not the binary tree is
a binary search tree. The tree is not made of key:value pairs but integer values only
and there are no duplicate values.

Solution description:
Recursively check if the nodes in the tree are within the allowed range. At the root
the range spans from minus infinity to the root value on the left side and from the
root value to plus infinity on the right side. The range gets narrower the further
we travel down the tree.

Note: this is not a working Java program but only the function including a helper
function. The full code was not visible and I didn't write my own wrapper code. */

    public boolean checkBST(Node root) {

        return checkBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);    
    }

    private boolean checkBST(Node root, int min, int max) {

        // base case 
        if (root == null) return true;

        // check if BST condition is violated
        if (root.data < min || root.data > max) return false;

        // recursion
        boolean validLeft = checkBST(root.left, min, root.data - 1);
        boolean validRight = checkBST(root.right, root.data + 1, max);
        return validLeft && validRight;
    }
