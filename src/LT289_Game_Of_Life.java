/*
According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies, as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population..
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
Write a function to compute the next state (after one update) of the board given its current state.

Follow up: 
Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first and then use their updated values to update other cells.
In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. How would you address these problems?

Array
 */
public class LT289_Game_Of_Life {
    public void gameOfLife(int[][] board) {
	// four state. use binary 0, 1 to say die or alive. two bit binary to show current state and next state
	// To get current state, we simple do: board[i][j] & 1
	// To get next state, we simple do: board[i][j] >> 1
	// 2 bit number for 4 condition in two stage die or live.

	if (board == null || board.length == 0)
	    return;
	// case 1. neighbour<2 1->0 initial second state=0, so dont care. 01-->01
	// case 2. neighbour=[2,3] 1->1 need to change.01-->11
	// case 3. neighbour>3 1->0 dont care 01-->01
	// case 4. neighbour=3 0->1 need to change 00-->10
	int m = board.length, n = board[0].length;
	for (int i = 0; i < m; i++) {
	    for (int j = 0; j < n; j++) {
		int lives = liveNeighbors(board, m, n, i, j);
		if ((board[i][j] & 1) == 1 && (lives >= 2 && lives <= 3))
		    board[i][j] = 3;
		if ((board[i][j] & 1) == 0 && lives == 3)
		    board[i][j] = 2;
	    }
	}

	// all value right move 1 position
	for (int i = 0; i < m; i++) {
	    for (int j = 0; j < n; j++) {
		board[i][j] >>= 1; // Get the 2nd state
	    }
	}
    }

    public int liveNeighbors(int[][] board, int m, int n, int i, int j) {
	int lives = 0;
	int[][] d = { { 1, -1 }, { 1, 0 }, { 1, 1 }, { 0, -1 }, { 0, 1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } };
	for (int k = 0; k < 8; k++) {
	    int x = d[k][0] + i;
	    int y = d[k][1] + j;
	    if (x < 0 || x >= m || y < 0 || y >= n) {
		continue;
	    }
	    if ((board[x][y] & 1) == 1) {
		lives++;
	    }
	}
	return lives;
    }
}
