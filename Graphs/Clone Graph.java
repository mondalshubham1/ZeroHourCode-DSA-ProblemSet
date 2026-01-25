// Problem Link : https://leetcode.com/problems/clone-graph/description/

class Solution {

    // HashMap to store mapping from original node -> cloned node
    // This helps to:
    // 1. Avoid cloning the same node multiple times
    // 2. Handle cycles in the graph
    HashMap<Node, Node> cloneMap = new HashMap<>();

    public Node cloneGraph(Node node) {

        // Base case: if input graph is empty
        if (node == null) return null;

        // Create clone of the starting node
        Node clonedNode = new Node(node.val);

        // Store the mapping: original node -> cloned node
        cloneMap.put(node, clonedNode);

        // Perform DFS to clone the entire graph
        dfs(node, clonedNode);

        // Return the cloned starting node
        return clonedNode;
    }

    // DFS function to traverse original graph and build cloned graph
    void dfs(Node node, Node clonedNode) {

        // Traverse all neighbors of the current original node
        for (Node neighbor : node.neighbors) {

            // If this neighbor is not yet cloned
            if (!cloneMap.containsKey(neighbor)) {

                // Create a clone of the neighbor node
                Node clonedNeighbor = new Node(neighbor.val);

                // Store mapping in HashMap
                cloneMap.put(neighbor, clonedNeighbor);

                // Add cloned neighbor to the current cloned node's neighbor list
                clonedNode.neighbors.add(clonedNeighbor);

                // Recursively clone the neighbor's neighbors
                dfs(neighbor, clonedNeighbor);
            }
            else {
                // If neighbor is already cloned (cycle or previously visited),
                // directly add the already created cloned node
                clonedNode.neighbors.add(cloneMap.get(neighbor));
            }
        }
    }
}
