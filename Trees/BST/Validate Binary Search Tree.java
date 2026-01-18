// Problem Link : https://leetcode.com/problems/validate-binary-search-tree/description/

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
    class PreviousValue {
        int prevValue = Integer.MAX_VALUE;
        boolean firstHit = true;
    }
    class Result {
        boolean ans = true;
    }
    public boolean isValidBST(TreeNode root) {
        if(root.left == null && root.right == null) {
            return true;
        }
        PreviousValue prev = new PreviousValue();
        Result result = new Result();
        inorderTraversal(root, result, prev);
        return result.ans;
    }
    public void inorderTraversal(TreeNode root, Result result, PreviousValue prev) {
        if(root == null) {
            return;
        }
        inorderTraversal(root.right, result, prev);
        if(prev.firstHit == true) {
            prev.firstHit = false;
        }
        else {
            if(root.val >= prev.prevValue) {
                result.ans = false;
            }
        }
        prev.prevValue = root.val;
        inorderTraversal(root.left, result, prev);
    }
}
