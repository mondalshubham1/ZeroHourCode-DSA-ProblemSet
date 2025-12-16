// Problem Link : https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/description/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    // Find all the nodes that are at distance of K from the given root, in the tree that is rooted at root.
    public void findKDistNodes(TreeNode root, int dist, List<Integer> ans) {
        if(dist < 0 || root == null)
            return;

        if(dist == 0) {
            ans.add(root.val);
            return;
        }
        findKDistNodes(root.right, dist-1, ans);
        findKDistNodes(root.left, dist-1, ans);
    }

    // For every node, find where do we find the target, in the left or the right subtree and also at what distance.
    // All nodes that are at distance k underneath the target needs to be added, and also all the nodes that fall in the path to target,
    // can also lead you to nodes at distance k.
    public int distOfNodeFromTarget(TreeNode root, TreeNode target, int k, List<Integer> ans) {
        if(root == null)
            return -1;

        if(root == target) {
            findKDistNodes(root, k, ans);
            return 0;
        }
            
        int left_distance = distOfNodeFromTarget(root.left, target, k, ans);
        int right_distance = distOfNodeFromTarget(root.right, target, k, ans);

        if(left_distance != -1) {
            if(left_distance == k-1) {
                ans.add(root.val);
            }
            findKDistNodes(root.right, k-left_distance-2, ans);
            return left_distance+1; 
        }
        if(right_distance != -1) {
            if(right_distance == k-1) {
                ans.add(root.val);
            }
            findKDistNodes(root.left, k-right_distance-2, ans);
            return right_distance+1; 
        }

        return -1;
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> ans = new ArrayList<>();
        distOfNodeFromTarget(root, target, k, ans);
        return ans;
    }
}
