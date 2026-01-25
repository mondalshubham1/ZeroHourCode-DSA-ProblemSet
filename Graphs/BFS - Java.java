// Problem Link : https://www.geeksforgeeks.org/problems/bfs-traversal-of-graph/1

class Solution {
    public ArrayList<Integer> bfs(ArrayList<ArrayList<Integer>> adj) {
        // code here
        int n = adj.size();
        ArrayList<Integer> result = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n];
        
        for(int i=0; i<n; i++) {
            visited[i] = false;
        }
        
        q.offer(0);
        visited[0] = true;
        
        while(!q.isEmpty()) {
            int node = q.poll();
            result.add(node);
            
            for(int x : adj.get(node)) {
                if(!visited[x]) {
                    q.offer(x);
                    visited[x]=true;
                }
            }
        }
        
        return result;
    }
}
