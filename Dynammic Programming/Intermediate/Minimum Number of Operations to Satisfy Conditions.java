// Problem Link : https://leetcode.com/problems/minimum-number-of-operations-to-satisfy-conditions/description/

class Solution {

    public int minimumOperations(int[][] grid) {

        int m = grid.length;        // number of rows in grid
        int n = grid[0].length;     // number of columns in grid

        /*
        Problem Constraints Restated:

        1) Vertical constraint:
           grid[i][j] == grid[i+1][j]
           -> All elements in a column must be equal.
              So each column will finally contain ONE chosen digit.

        2) Horizontal constraint:
           grid[i][j] != grid[i][j+1]
           -> Adjacent columns must use different digits.

        Therefore the task becomes:
        For each column choose a digit (0..9) such that:
            - Cost to convert column to that digit is minimized
            - Adjacent columns use different digits

        Cost to convert column j to digit d:
            m - freq[d][j]
        (because freq[d][j] cells already match d)
        */


        // freq[digit][j] = frequency of 'digit' in column j
        int[][] freq = new int[10][n];

        // Build frequency table for each column
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                freq[grid[i][j]][j]++;
            }
        }


        /*
        dp[digit][j] =
            Minimum operations required to satisfy columns j..n-1
            IF column j is forced to be 'digit'

        We fill this DP from right to left.
        */
        int[][] dp = new int[10][n];


        // Base case:
        // Last column has no neighbor to the right,
        // so cost is simply converting it to that digit
        for (int digit = 0; digit <= 9; digit++) {
            dp[digit][n - 1] = m - freq[digit][n - 1];
        }


        /*
        Transition:

        For each column j and chosen digit:
            Cost = cost to fix column j
                   + minimum cost of fixing column j+1
                     using any DIFFERENT digit

        We try all possible digits for next column
        except the current one.
        */
        for (int j = n - 2; j >= 0; j--) {
            for (int digit = 0; digit <= 9; digit++) {

                int tmp = Integer.MAX_VALUE;

                // Find best choice for next column digit
                for (int front_digit = 0; front_digit <= 9; front_digit++) {
                    if (front_digit != digit) {
                        tmp = Math.min(tmp, dp[front_digit][j + 1]);
                    }
                }

                // Add current column conversion cost
                dp[digit][j] = m - freq[digit][j] + tmp;
            }
        }


        // Final answer:
        // Choose the best digit for the first column
        int ans = Integer.MAX_VALUE;
        for (int digit = 0; digit <= 9; digit++) {
            ans = Math.min(ans, dp[digit][0]);
        }

        return ans;
    }
}
