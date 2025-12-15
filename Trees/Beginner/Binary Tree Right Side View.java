// Problem Link : https://leetcode.com/problems/binary-tree-right-side-view/description/

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        if(root == null) {
            return new ArrayList<>();
        }
        queue.add(root);
        while (!queue.isEmpty()) {
            int levelLen = queue.size();
            for (int i = 0; i < levelLen; i++) {
                TreeNode node = queue.poll();
                if (i == levelLen - 1) {
                    result.add(node.val);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return result;
    }
}
