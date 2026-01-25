// Problem Link : https://www.geeksforgeeks.org/problems/bfs-traversal-of-graph/1

class Solution {
    public ArrayList<Integer> bfs(ArrayList<ArrayList<Integer>> adj) {

        // Number of vertices in the graph
        int n = adj.size();

        // List to store the BFS traversal result
        ArrayList<Integer> result = new ArrayList<>();

        // Queue is used for BFS (FIFO order)
        Queue<Integer> q = new LinkedList<>();

        // visited array to keep track of already visited nodes
        boolean[] visited = new boolean[n];

        // Initialize all nodes as not visited
        for(int i = 0; i < n; i++) {
            visited[i] = false;
        }

        // Start BFS from node 0 (as per problem statement)
        q.offer(0);          // push node 0 into queue
        visited[0] = true;   // mark node 0 as visited

        // Continue BFS until queue becomes empty
        while(!q.isEmpty()) {

            // Remove front element from queue
            int node = q.poll();

            // Add the current node to BFS result
            result.add(node);

            // Traverse all adjacent vertices of the current node
            for(int x : adj.get(node)) {

                // If the adjacent node is not visited yet
                if(!visited[x]) {

                    // Add it to queue for future processing
                    q.offer(x);

                    // Mark it as visited so it is not added again
                    visited[x] = true;
                }
            }
        }

        // Return the BFS traversal order
        return result;
    }
}
