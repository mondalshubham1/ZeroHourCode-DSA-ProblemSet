// Problem Link : https://leetcode.com/problems/unique-binary-search-trees/description/

class Solution {
    public int numTrees(int n) {
        int[] ans = new int[n+1];
        ans[0]=1;

        for(int j=1; j<=n; j++) {   // Choose every number from 1 to n as the root, say j.
            int sum=0;
            for(int i=1; i<=j; i++) {       // For every j, find the number of BST you can form using all the numbers in the left, and similarly for right.
                int left_num_of_nodes = i-1;
                int right_num_of_nodes = j-i;
                sum += ans[left_num_of_nodes]*ans[right_num_of_nodes];
            }
            ans[j] = sum; 
        }
        return ans[n];
    }
}
