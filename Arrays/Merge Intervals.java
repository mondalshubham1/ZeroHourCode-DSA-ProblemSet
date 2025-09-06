// Problem Link : https://leetcode.com/problems/merge-intervals/description/

class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] pair1, int[] pair2) {
                return Integer.compare(pair1[0], pair2[0]);
            }
        });
        ArrayList<int[]> ans = new ArrayList<>();
        int[] curr_pair = new int[2];
        curr_pair[0]=-1; curr_pair[1]=-1;
        for(int[] pair : intervals) {
            if(pair[0] > curr_pair[1]) {
                if(curr_pair[0]!=-1) {
                    ans.add(new int[]{curr_pair[0], curr_pair[1]});
                }
                curr_pair[0] = pair[0];
                curr_pair[1] = pair[1];
            }
            else {
                curr_pair[1] = Math.max(curr_pair[1], pair[1]);
            }
        }
        ans.add(curr_pair);
        int[][] ans_array = new int[ans.size()][2];
        for(int i=0; i<ans.size(); i++) {
            ans_array[i] = ans.get(i);
        }
        return ans_array;
    }
}
