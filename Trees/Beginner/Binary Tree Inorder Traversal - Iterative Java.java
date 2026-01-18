// Problem Link : https://leetcode.com/problems/binary-tree-inorder-traversal/

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
    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> st = new Stack<>();
        TreeNode curr = root;

        List<Integer> ans = new ArrayList<>();

        if(root == null)
            return ans;
        
        while(curr!=null || !st.isEmpty()) {
            if(curr != null) {
                st.push(curr);
                curr = curr.left;
            }
            else {
                TreeNode node = st.pop();
                ans.add(node.val);
                curr = node.right;
            }   
        }
        return ans;
     }
}
