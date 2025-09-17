// Problem Link : https://leetcode.com/problems/range-sum-query-2d-immutable/

class NumMatrix {
    int[][] psMatrix2D;
    public NumMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        psMatrix2D = new int[m][n];
        psMatrix2D[0][0] = matrix[0][0];
        for(int j=1; j<n; j++) {
            psMatrix2D[0][j] = psMatrix2D[0][j-1]+matrix[0][j];
        }
        for(int i=1; i<m; i++) {
            psMatrix2D[i][0] = psMatrix2D[i-1][0]+matrix[i][0];
        }

        for(int i=1; i<m; i++) {
            for(int j=1; j<n; j++) {
                psMatrix2D[i][j] = psMatrix2D[i][j-1] + psMatrix2D[i-1][j] - psMatrix2D[i-1][j-1] + matrix[i][j];
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int w,x,y,z;
        w = psMatrix2D[row2][col2];
        x = row1==0 ? 0 : psMatrix2D[row1-1][col2];
        y = col1==0 ? 0 : psMatrix2D[row2][col1-1];
        z = (row1==0 || col1==0) ? 0 : psMatrix2D[row1-1][col1-1];
        return w-x-y+z;
    }
}
