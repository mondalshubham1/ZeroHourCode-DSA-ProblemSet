// Problem Link : https://leetcode.com/problems/shortest-path-in-binary-matrix/description/

class Solution {

    // Helper class representing one entry in BFS queue
    // Stores:
    //  - row index
    //  - column index
    //  - distance from source (0,0)
    class QueueEntry {
        int row_index;
        int col_index;
        int dist;

        QueueEntry(int r, int c, int d) {
            this.row_index = r;
            this.col_index = c;
            this.dist = d;
        }
    }

    // Utility function to check if an index lies within bounds
    // index -> value to check
    // range -> upper limit (exclusive)
    boolean isIndexValid(int index, int range) {
        if(index >= 0 && index < range) {
            return true;
        }
        return false;
    }

    // BFS solution to find shortest path from top-left to bottom-right
    // in a binary matrix where:
    // 0 -> open cell
    // 1 -> blocked cell
    // Movement allowed in 8 directions
    public int shortestPathBinaryMatrix(int[][] grid) {

        int m = grid.length;        // number of rows
        int n = grid[0].length;     // number of columns

        // Visited matrix to avoid revisiting cells
        int[][] visited = new int[m][n];

        // BFS Queue
        Queue<QueueEntry> q = new LinkedList<>();

        // Start BFS from (0,0) with distance = 1
        q.offer(new QueueEntry(0,0,1));

        // Standard BFS loop
        while(q.size() > 0) {

            // Remove next cell to explore
            QueueEntry qe = q.poll();

            int r = qe.row_index;
            int c = qe.col_index;
            int d = qe.dist;

            // If current cell is blocked → skip processing
            if(grid[r][c] == 1) {
                continue;
            }

            // If destination reached → return shortest distance
            if(r == m-1 && c == n-1) {
                return d;
            }

            // Explore all 8 neighbors
            // -----------------------------------------

            // Up
            if(isIndexValid(r-1, m) && visited[r-1][c] == 0) {
                visited[r-1][c] = 1;
                q.offer(new QueueEntry(r-1,c,d+1));
            }

            // Down
            if(isIndexValid(r+1, m) && visited[r+1][c] == 0) {
                visited[r+1][c] = 1;
                q.offer(new QueueEntry(r+1,c,d+1));
            }

            // Left
            if(isIndexValid(c-1, n) && visited[r][c-1] == 0) {
                visited[r][c-1] = 1;
                q.offer(new QueueEntry(r,c-1,d+1));
            }

            // Right
            if(isIndexValid(c+1, n) && visited[r][c+1] == 0) {
                visited[r][c+1] = 1;
                q.offer(new QueueEntry(r,c+1,d+1));
            }

            // Top-Left diagonal
            if(isIndexValid(r-1, m) && isIndexValid(c-1, n)
                    && visited[r-1][c-1] == 0) {
                visited[r-1][c-1] = 1;
                q.offer(new QueueEntry(r-1,c-1,d+1));
            }

            // Top-Right diagonal
            if(isIndexValid(r-1, m) && isIndexValid(c+1, n)
                    && visited[r-1][c+1] == 0) {
                visited[r-1][c+1] = 1;
                q.offer(new QueueEntry(r-1,c+1,d+1));
            }

            // Bottom-Left diagonal
            if(isIndexValid(r+1, m) && isIndexValid(c-1, n)
                    && visited[r+1][c-1] == 0) {
                visited[r+1][c-1] = 1;
                q.offer(new QueueEntry(r+1,c-1,d+1));
            }

            // Bottom-Right diagonal
            if(isIndexValid(r+1, m) && isIndexValid(c+1, n)
                    && visited[r+1][c+1] == 0) {
                visited[r+1][c+1] = 1;
                q.offer(new QueueEntry(r+1,c+1,d+1));
            }
        }

        // If destination never reached
        return -1;
    }
}
