// Problem Link : https://leetcode.com/problems/n-queens/description/

class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<Character>> board = new ArrayList<>();
        List<List<String>> ans = new ArrayList<>();

        for(int row=1; row<=n; row++) {
            List<Character> rowList = new ArrayList<>();
            for(int col=1; col<=n; col++) {
                rowList.add('.');
            }
            board.add(rowList);  
        }

        solveNQueensHelper(0, board, ans);

        return ans;

    }

    public void solveNQueensHelper(int row, List<List<Character>> board, List<List<String>> ans) {
        int n = board.size();

        if(row == n) {
            ans.add(convertBoardToAString(board));
            return;
        }

        for(int j=0; j<n; j++) {
            if(checkIfYouCanPlaceQueen(board, row, j)) {
                board.get(row).set(j, 'Q');
                solveNQueensHelper(row+1, board, ans);
                board.get(row).set(j, '.');
            }       
        }
    }

    public List<String> convertBoardToAString(List<List<Character>> board) {
        List<String> boardString = new ArrayList<>();
        for(List<Character> l : board) {
            StringBuilder rowString = new StringBuilder();
            for(Character c : l) {
                rowString.append(c);
            }
            boardString.add(rowString.toString());
        }
        return boardString;
    }

    public boolean checkIfYouCanPlaceQueen(List<List<Character>> board, int row, int col) {
        int n = board.size();
        
        // Check up
        for(int i=row-1; i>=0; i--) {
            if(board.get(i).get(col) == 'Q') {
                return false;
            }
        }

        // Check left
        for(int j=col-1; j>=0; j--) {
            if(board.get(row).get(j) == 'Q') {
                return false;
            }
        }

        // Check left diagonal up
        for(int i=row-1, j=col+1; i>=0 && j<n; i--,j++) {
            if(board.get(i).get(j) == 'Q') {
                return false;
            }
        }


        // Check right diagonal up
        for(int i=row-1, j=col-1; i>=0 && j>=0; i--,j--) {
            if(board.get(i).get(j) == 'Q') {
                return false;
            }
        }

        return true;
    }
}
