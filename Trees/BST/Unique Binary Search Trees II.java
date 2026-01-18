// Problem Link : https://leetcode.com/problems/unique-binary-search-trees-ii/description/

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

// You can optimise further by caching the return values of each helper(i, j)
class Solution {
    public List<TreeNode> generateTrees(int n) {
        return helper(1, n);
    }
    public List<TreeNode> helper(int i, int j) {
        if(i>j) {
            List<TreeNode> tmp = new ArrayList<>();
            tmp.add(null);
            return tmp;
        }
        List<TreeNode> ans = new ArrayList<>();
        for(int k=i; k<=j; k++) {
            List<TreeNode> leftSubTrees = helper(i, k-1);
            List<TreeNode> rightSubTrees = helper(k+1, j);
            for(TreeNode l : leftSubTrees) {
                for(TreeNode r : rightSubTrees) {
                    TreeNode root = new TreeNode(k);
                    root.left = l;
                    root.right = r;
                    ans.add(root);
                }
            }
        }
        return ans;
    }
}
