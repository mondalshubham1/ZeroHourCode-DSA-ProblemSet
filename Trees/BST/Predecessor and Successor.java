// Problem Link : https://www.geeksforgeeks.org/problems/predecessor-and-successor/1

/*
class Node {
    int data;
    Node left, right;
    Node(int x) {
        data = x;
        left = right = null;
    }
}
*/

class Solution {
    public ArrayList<Node> findPreSuc(Node root, int key) {
        // code here
        Node successor = null;
        Node predecessor = null;
        
        Node curr = root;
        while(curr!=null) {
            if(curr.data > key) {
                successor = curr;
                curr=curr.left;
            }
            else {
                curr = curr.right;
            }
        }
        
        curr = root;
        while(curr!=null) {
            if(curr.data < key) {
                predecessor = curr;
                curr=curr.right;
            }
            else {
                curr = curr.left;
            }
        }
        
        ArrayList<Node>ans = new ArrayList<>();
        ans.add(predecessor);
        ans.add(successor);
        
        return ans;
    }
}
