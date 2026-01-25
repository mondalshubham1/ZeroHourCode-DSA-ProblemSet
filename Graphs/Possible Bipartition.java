// Problem Link : https://leetcode.com/problems/possible-bipartition/description/

class Solution {

    int[] color;      // Stores group assignment: 0 = unassigned, -1 = Group A, 1 = Group B
    boolean[] visited; // Tracks which people have already been processed

    public boolean possibleBipartition(int n, int[][] dislikes) {

        // Create adjacency list to represent who dislikes whom (graph representation)
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        // Initialize empty list for each person (0 to n-1)
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // Build the graph from dislikes array (undirected edges)
        for (int[] pair : dislikes) {
            graph.get(pair[0] - 1).add(pair[1] - 1);
            graph.get(pair[1] - 1).add(pair[0] - 1);
        }

        // Initialize arrays for coloring and visited tracking
        color = new int[n];
        visited = new boolean[n];

        // Since the graph can be disconnected, check each person separately
        for (int i = 0; i < n; i++) {

            // If this person is not yet assigned to any group, start BFS from here
            if (!visited[i]) {

                // Try to assign groups for this connected component
                if (!bfsHelper(i, graph)) {
                    return false; // Conflict found, bipartition not possible
                }
            }
        }

        // If all components can be divided into two groups successfully
        return true;
    }

    // Performs BFS to assign people into two opposite groups
    boolean bfsHelper(int start, ArrayList<ArrayList<Integer>> graph) {

        Queue<Integer> q = new LinkedList<>();

        // Start with the given person
        q.offer(start);
        visited[start] = true;

        // Assign the first person to Group A
        color[start] = -1;

        // Process the queue
        while (!q.isEmpty()) {

            int person = q.poll();

            // Check all people this person dislikes
            for (int neighbor : graph.get(person)) {

                // If both people are in the same group, partition is invalid
                if (color[neighbor] != 0 && color[neighbor] == color[person]) {
                    return false;
                }

                // If neighbor has not been assigned a group yet
                if (!visited[neighbor]) {

                    // Assign neighbor to the opposite group
                    color[neighbor] = -1 * color[person];
                    visited[neighbor] = true;

                    // Add neighbor for further processing
                    q.offer(neighbor);
                }
            }
        }

        // No conflicts found in this connected component
        return true;
    }
}
