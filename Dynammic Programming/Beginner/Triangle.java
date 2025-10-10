// Problem Link : https://leetcode.com/problems/triangle/description/

class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        List<List<Integer>> DP = new ArrayList<>();
        int n = triangle.size();

        for(int i=1; i<=n; i++) {
            List<Integer> tmp = new ArrayList<>();
            for(int j=1; j<=i; j++) {
                tmp.add(0);
            }
            DP.add(tmp);
        }

        for(int j=0; j<=n-1; j++) {
            DP.get(n-1).set(j, triangle.get(n-1).get(j)); 
        } 
        for(int i=n-2; i>=0; i--) {
            for(int j=0; j<=i; j++) {
                DP.get(i).set(j, triangle.get(i).get(j) + Math.min(DP.get(i+1).get(j), DP.get(i+1).get(j+1)));
            }
        }

        return DP.get(0).get(0);
    }
}

// Better solution with better Space complexity exists, it can be done with a 1-D DP array. 
