// Problem Link : https://leetcode.com/problems/maximum-width-of-binary-tree/description/

// Perform level order traversal and assign the indices (2*i+1, 2*i+2) to each child node, but make sure the indices are not overflowing,
// hence subtract the all the indices first by the minimal index in that level to get an offset, and then find the child indices using that offset.

class Solution {
    class Pair {
        TreeNode treeNode;
        int index; 
        Pair(TreeNode treeNode, int index) {
            this.treeNode = treeNode;
            this.index = index;
        }
    }
    public int widthOfBinaryTree(TreeNode root) {
        Queue<Pair> queue = new LinkedList<>();
        int result = 1;
        if(root == null) {
            return 0;
        }
        queue.add(new Pair(root, 0));
        while (!queue.isEmpty()) {
            int levelLen = queue.size();
            
            // the minimum index of any node in that level
            int min_index = queue.peek().index;
            
            for (int i = 0; i < levelLen; i++) {
                Pair node = queue.poll();

                // Means you've reached the end node
                if(i==levelLen-1) {
                    result = Math.max(result, node.index-min_index+1);
                }

                if (node.treeNode.left != null) {
                    queue.add(new Pair(node.treeNode.left, 2*(node.index-min_index)+1));
                }

                if (node.treeNode.right != null) {
                    queue.add(new Pair(node.treeNode.right, 2*(node.index-min_index)+2));
                }
            }
        }
        return result;
    }
}
