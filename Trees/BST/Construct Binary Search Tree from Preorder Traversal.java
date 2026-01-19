// Problem Link : https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/description/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode bstFromPreorder(int[] preorder) {
        return helper(preorder, 0, preorder.length-1);
    }


    // This functions takes in a subarray, which is pre-order of a BST and returns a Tree.
    public TreeNode helper(int[] preorder, int start, int end) {
        if(start > end)
            return null;

        TreeNode root = new TreeNode(preorder[start]);   // Root will be the first element of the preorder traversal

        int i=start+1;           
        while(i<=end && preorder[i]<root.val) {         // All the element of the preorder of the left subtree will be lesser than the root
            i++;
        }

        TreeNode leftTree = helper(preorder, start+1, i-1);
        TreeNode rightTree = helper(preorder, i, end);

        root.left = leftTree;
        root.right = rightTree;

        return root;
    }
}
