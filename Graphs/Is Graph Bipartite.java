// Problem Link : https://leetcode.com/problems/is-graph-bipartite/description/

class Solution {

    int n;                 // number of nodes in the graph
    int[] color;           // color array: 0 = uncolored, -1 = black, 1 = white
    boolean[] visited;     // keeps track of visited nodes

    public boolean isBipartite(int[][] graph) {

        // Total number of nodes
        n = graph.length;

        // Initialize color array for all nodes (initially uncolored)
        color = new int[n];

        // Initialize visited array
        visited = new boolean[n];

        // Graph may be disconnected, so we must check each component
        for (int i = 0; i < n; i++) {

            // If node i is not visited, start BFS from it
            if (!visited[i]) {

                // Check if this connected component is bipartite
                boolean result = bfsHelper(i, graph);

                // If any component is not bipartite, return false
                if (result == false)
                    return false;
            }
        }

        // If all components are bipartite, return true
        return true;
    }

    // Helper function that performs BFS and checks bipartite property
    boolean bfsHelper(int i, int[][] graph) {

        // Queue for BFS traversal
        Queue<Integer> q = new LinkedList<>();

        // Start BFS from node i
        q.offer(i);

        // Mark starting node as visited
        visited[i] = true;

        // Assign first color (black = -1)
        color[i] = -1;

        // Perform BFS
        while (!q.isEmpty()) {

            // Remove front node from queue
            int node = q.poll();

            // Traverse all neighbors of the current node
            for (int neighbor : graph[node]) {

                // If neighbor already has a color and it is same as current node,
                // then graph is NOT bipartite
                if (color[neighbor] != 0 && color[neighbor] == color[node]) {
                    return false;
                }

                // If neighbor has not been visited yet
                if (!visited[neighbor]) {

                    // Add neighbor to queue for BFS processing
                    q.offer(neighbor);

                    // Mark neighbor as visited
                    visited[neighbor] = true;

                    // Assign opposite color to neighbor
                    color[neighbor] = -1 * color[node];
                }
            }
        }

        // If BFS completes without conflicts, this component is bipartite
        return true;
    }
}
