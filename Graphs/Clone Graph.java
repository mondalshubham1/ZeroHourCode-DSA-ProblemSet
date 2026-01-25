// Problem Link : https://leetcode.com/problems/clone-graph/description/

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    HashMap<Node, Node> cloneMap = new HashMap<>();

    public Node cloneGraph(Node node) {
        if(node == null) return null;
        
        Node clonedNode = new Node(node.val);
        cloneMap.put(node, clonedNode);

        dfs(node, clonedNode);
        return clonedNode;
    }
    void dfs(Node node, Node clonedNode) {

        for(Node neighbor : node.neighbors) {
            if(!cloneMap.containsKey(neighbor)) {
                Node clonedNeighbor = new Node(neighbor.val);
                cloneMap.put(neighbor, clonedNeighbor);
                clonedNode.neighbors.add(clonedNeighbor);

                dfs(neighbor, clonedNeighbor);
            } 
            else {
                clonedNode.neighbors.add(cloneMap.get(neighbor));
            }
        }
    }
}
