/*
 Write a program to solve a Sudoku puzzle by filling the empty cells.
 Empty cells are indicated by the character '.'.
 You may assume that there will be only one unique solution.
 */
/*
 * Backtracking, HashTable
 */
public class LT037_Sudoku_Solver {
    public void solveSudoku(char[][] board) {
	if (board == null || board.length == 0)
	    return;
	helper(board);
    }

    public boolean helper(char[][] board) {
	int m = board.length;
	int n = board[0].length;
	for (int i = 0; i < m; i++) {
	    for (int j = 0; j < n; j++) {
		if (board[i][j] == '.') {
		    for (char k = '1'; k <= '9'; k++) {
			if (isValid(board, i, j, k)) {
			    board[i][j] = k;
			    if (helper(board))
				return true;
			    else
				board[i][j] = '.'; // necessary.otherwise, next
						   // time will not fill this
						   // position.
			}
		    }
		    // don't forget. if cannot fill 1-9, means wrong.
		    // backtracking.
		    return false;
		}
	    }
	}

	return true;
    }

    public boolean isValid(char[][] board, int i, int j, int elm) {
	// check if ith row, jth col, and i-j sub-square contains elm
	for (int k = 0; k < 9; k++) {
	    if (board[i][k] == elm)
		return false;
	}

	for (int l = 0; l < 9; l++) {
	    if (board[l][j] == elm)
		return false;
	}

	for (int k = i / 3 * 3; k < i / 3 * 3 + 3; k++) {
	    for (int l = j / 3 * 3; l < j / 3 * 3 + 3; l++) {
		if (board[k][l] == elm)
		    return false;
	    }
	}

	return true;
    }
}
