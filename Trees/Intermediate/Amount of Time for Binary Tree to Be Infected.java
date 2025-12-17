// Problem Link : https://leetcode.com/problems/amount-of-time-for-binary-tree-to-be-infected/description/

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
    public int heightOfTree(TreeNode root) {
        if(root == null)
            return -1;

        int left_height = heightOfTree(root.left);
        int right_height = heightOfTree(root.right);
        return Math.max(left_height, right_height)+1;
    }

    public int distOfNodeFromTarget(TreeNode root, int start, Result res){
        if(root == null)
            return -1;

        if(root.val == start) {
            res.val = Math.max(res.val, heightOfTree(root));
            return 0;
        }
            
        int left_distance = distOfNodeFromTarget(root.left, start, res);
        int right_distance = distOfNodeFromTarget(root.right, start, res);

        if(left_distance != -1) {
            res.val = Math.max(res.val, left_distance + 2 + heightOfTree(root.right));
            return left_distance+1; 
        }
        if(right_distance != -1) {
            res.val = Math.max(res.val, right_distance + 2 + heightOfTree(root.left));
            return right_distance+1; 
        }

        return -1;
    }

    class Result {
        int val;
        Result(int val) {
            this.val = val;
        }
    }

    public int amountOfTime(TreeNode root, int start) {
        Result result = new Result(Integer.MIN_VALUE);
        distOfNodeFromTarget(root, start, result);
        return result.val;
    } 
}
