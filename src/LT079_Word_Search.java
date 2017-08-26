/*
 Given a 2D board and a word, find if the word exists in the grid.
 The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

	For example,
	Given board =
	[
	  ['A','B','C','E'],
	  ['S','F','C','S'],
	  ['A','D','E','E']
	]
	word = "ABCCED", -> returns true,
	word = "SEE", -> returns true,
	word = "ABCB", -> returns false.
 */
/*
 * Array, Backtracking
 * Follow up: count how many possibilities?  Check 212. show all the results. (add a global variable or parameter to dfs helper(). and helper is void)
 */
public class LT079_Word_Search {
    // Should use DFS
    public static void main(String[] args) {
	char[][] board = { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } };
	String word = "SEE";
	System.out.println(exist(board, word));
    }

    public static boolean exist(char[][] board, String word) {
	int m = board.length;
	int n = (m == 0 ? 0 : board[0].length);
	if (word == null || word.length() == 0)
	    return false;
	boolean[][] visited = new boolean[m][n];
	for (int i = 0; i < m; i++) {
	    for (int j = 0; j < n; j++) {
		if (helper(board, word, i, j, visited))
		    return true;
	    }
	}

	return false;
    }

    public static boolean helper(char[][] board, String word, int i, int j, boolean[][] visited) {
	if (!visited[i][j]) {
	    if (word.length() == 1 && board[i][j] == word.charAt(0))
		return true;
	    int m = board.length;
	    int n = (m == 0 ? 0 : board[0].length);

	    if (board[i][j] == word.charAt(0)) {
		visited[i][j] = true;
		if (i + 1 < m && helper(board, word.substring(1), i + 1, j, visited))
		    return true;
		if (i - 1 >= 0 && helper(board, word.substring(1), i - 1, j, visited))
		    return true;
		if (j + 1 < n && helper(board, word.substring(1), i, j + 1, visited))
		    return true;
		if (j - 1 >= 0 && helper(board, word.substring(1), i, j - 1, visited))
		    return true;
		visited[i][j] = false;
	    }
	}
	return false;
    }

    // another way
    public boolean exist2(char[][] board, String word) {
	if (word == null || word.length() == 0)
	    return true;
	if (board == null || board.length == 0 || board[0].length == 0)
	    return false;
	boolean[][] used = new boolean[board.length][board[0].length];
	for (int i = 0; i < board.length; i++) {
	    for (int j = 0; j < board[0].length; j++) {
		if (search(board, word, 0, i, j, used))
		    return true;
	    }
	}
	return false;
    }

    private boolean search(char[][] board, String word, int index, int i, int j, boolean[][] used) {
	if (index == word.length())
	    return true;
	if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || used[i][j]
		|| board[i][j] != word.charAt(index))
	    return false;
	used[i][j] = true;
	boolean res = search(board, word, index + 1, i - 1, j, used) || search(board, word, index + 1, i + 1, j, used)
		|| search(board, word, index + 1, i, j - 1, used) || search(board, word, index + 1, i, j + 1, used);
	used[i][j] = false;
	return res;
    }

}
