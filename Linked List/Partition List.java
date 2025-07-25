// Problem Link : https://leetcode.com/problems/partition-list/description/

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
class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        head = dummy;

        ListNode i=head;
        ListNode j=head.next;
        ListNode prevOfj=head;
        while(j!=null) {
            if(j.val < x){
                if(i==prevOfj) {
                    j=j.next;
                    i=i.next;
                    prevOfj=prevOfj.next;
                }
                else {
                    prevOfj.next = j.next;
                    j.next = i.next;
                    i.next = j;
                    i=i.next;
                    j=prevOfj.next;
                }
                continue;
            }
            j = j.next;
            prevOfj = prevOfj.next;
        }
        head = head.next;
        return head;
    }
}
