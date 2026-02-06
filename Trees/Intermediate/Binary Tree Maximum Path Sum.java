// Problem Link : https://leetcode.com/problems/binary-tree-maximum-path-sum/

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

    // Wrapper class to hold the global maximum path sum.
    // We use a reference object so updates inside recursion
    // are reflected outside (Java passes primitives by value).
    class Result {
        int ans = Integer.MIN_VALUE; // Initialize to smallest possible
    }

    // Entry function called by LeetCode
    public int maxPathSum(TreeNode root) {
        Result result = new Result();

        // Start DFS traversal to compute path sums
        helper(root, result);

        // Final maximum path sum across entire tree
        return result.ans;
    }

    // Recursive helper that computes:
    // Updates global maximum path sum
    // Returns max gain from current node upwards to parent
    public int helper(TreeNode root, Result result) {

        // Base case: null node contributes 0 to path sum
        if(root == null) return 0;

        // Recursively compute max path gain from left subtree
        // If negative, ignore it by taking 0 (don't include harmful path)
        int left_path = Math.max(0, helper(root.left, result));

        // Same logic for right subtree
        int right_path = Math.max(0, helper(root.right, result));

        // Path that passes THROUGH this node and possibly both children
        // This is a candidate for global maximum
        //        left -> root -> right
        result.ans = Math.max(
            result.ans,
            root.val + left_path + right_path
        );

        // Return the maximum gain INCLUDING current node
        // but only ONE side can be extended upward
        // (because parent path cannot fork)
        return Math.max(left_path, right_path) + root.val;
    }
}
