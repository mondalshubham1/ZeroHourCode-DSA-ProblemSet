// Problem Link : https://leetcode.com/problems/maximum-width-ramp/

class Solution {
    public int maxWidthRamp(int[] nums) {
        int n = nums.length;
        int[] max_after_this = new int[n];             // Stores the maximum value that you have after jth index
        max_after_this[n-1] = Integer.MIN_VALUE;
        
        for(int i=n-2; i>=0; i--) {                     
            max_after_this[i] = Math.max(max_after_this[i+1], nums[i+1]);
        }

        int start = 0;
        int end = 1;

        int ramp_size = 0;
        while(end < n) {        // Maintain a start and end index, everytime you're trying to find the longest ramp starting with start.  
            if(nums[end] >= nums[start]) {                      
                ramp_size = Math.max(ramp_size, end-start);
            }
            if(max_after_this[end] >= nums[start]) {
                end++;
            }
            else {
                start++;
            }
            if(start == end) {
                end++;
            }
        }
        return ramp_size;
    }
}
