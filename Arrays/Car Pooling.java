// Problem Link : https://leetcode.com/problems/car-pooling/description/

class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        int start=Integer.MAX_VALUE;
        int end=Integer.MIN_VALUE;

        for(int[] trip : trips) {
            start = Math.min(start, trip[1]);
            end = Math.max(end, trip[2]);
        }

        int[] numOfPassengers = new int[end-start+1];    // I'm maintaining an array that holds the number of passengers in every kilometer
        // We can place the markers for each trip, and then take a prefix sum
        for(int[] trip : trips) {
            numOfPassengers[trip[1]-start] += trip[0];
            numOfPassengers[trip[2]-start] += -1*trip[0];
        }
        if(numOfPassengers[0] > capacity)
            return false;
        for(int i=1; i<end-start+1; i++) {
            numOfPassengers[i] = numOfPassengers[i-1]+numOfPassengers[i];
            if(numOfPassengers[i] > capacity)
                return false;
        }
        return true;
    }
}
