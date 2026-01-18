// Problem Link : https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/description/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
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
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null)
            return null;

        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;

        ListNode slow = dummy;
        ListNode fast = dummy;
        ListNode prev = dummy;

        // This step will find the root of the BST, slow will eventually point to the root, next we'll break the tree into left list, right list and the root.
        while(fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = null;

        TreeNode root = new TreeNode(slow.val);
        root.left = sortedListToBST(prev.val!=Integer.MIN_VALUE ? dummy.next : null);
        root.right = sortedListToBST(slow.next!=null ? slow.next : null);

        return root;
    }
}
