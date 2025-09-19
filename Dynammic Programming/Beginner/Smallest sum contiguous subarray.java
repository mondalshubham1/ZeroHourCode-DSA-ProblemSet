// Problem Link : https://www.geeksforgeeks.org/problems/smallest-sum-contiguous-subarray/1

class Solution {
    static int smallestSumSubarray(int a[], int size) {
        // Used Kadane's Algorithm
        int kadane_sum=0;
        int ans = Integer.MAX_VALUE;
        for(int i=0; i<size; i++) {
            kadane_sum += a[i];
            ans = Math.min(ans, kadane_sum);
            if(kadane_sum >= 0) {
                kadane_sum=0;
            }
        }
        return ans;
    }
}
