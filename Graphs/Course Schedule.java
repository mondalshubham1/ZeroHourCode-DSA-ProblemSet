// Problem Link : https://leetcode.com/problems/course-schedule/description/

class Solution {

    // visited[i] represents the state of course i:
    // 0 = not visited yet
    // 1 = currently in DFS recursion stack (visiting)
    // 2 = completely processed (visited and exited)
    int[] visited;

    // Adjacency list representation of the graph
    // graph[u] contains all courses that depend on course u
    List<List<Integer>> graph;

    // Wrapper class to store final answer (used because Java passes objects by reference)
    class Result {
        boolean ans = true;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        // Initialize visited array
        visited = new int[numCourses];

        // Initialize graph with empty adjacency lists
        graph = new ArrayList<>();

        // Object to track whether a cycle is detected
        Result result = new Result();

        // Create adjacency list for each course
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        // Build the graph from prerequisites
        // Edge: prerequisite -> course
        for (int[] coursePair : prerequisites) {
            graph.get(coursePair[1]).add(coursePair[0]);
        }

        // Run DFS on every course (graph may be disconnected)
        for (int i = 0; i < numCourses; i++) {

            // If course i is not visited and no cycle found yet
            if (visited[i] == 0 && result.ans == true) {

                // Mark as visiting (in recursion stack)
                visited[i] = 1;

                // Perform DFS from this node
                dfs(i, result);
            }
        }

        // Return whether it is possible to finish all courses
        return result.ans;
    }

    // DFS function to detect cycle in directed graph
    void dfs(int node, Result result) {

        // If a cycle is already found, stop further processing
        if (result.ans == false) return;

        // Traverse all neighbors (courses dependent on current course)
        for (int neighbor : graph.get(node)) {

            // If neighbor has not been visited yet
            if (visited[neighbor] == 0) {

                // Mark neighbor as visiting
                visited[neighbor] = 1;

                // Recursively DFS neighbor
                dfs(neighbor, result);
            }

            // If neighbor is currently in recursion stack,
            // then a cycle is detected
            else if (visited[neighbor] == 1) {
                result.ans = false;
            }
        }

        // Mark current node as completely processed
        visited[node] = 2;
    }
}
