/*
 * Given an 2D board, count how many battleships are in it. The battleships are represented with 'X's, empty slots are represented with '.'s. You may assume the following rules:

You receive a valid board, made of only battleships or empty slots.
Battleships can only be placed horizontally or vertically. In other words, they can only be made of the shape 1xN (1 row, N columns) or Nx1 (N rows, 1 column), where N can be of any size.
At least one horizontal or vertical cell separates between two battleships - there are no adjacent battleships.
Example:
X..X
...X
...X
In the above board there are 2 battleships.
Invalid Example:
...X
XXXX
...X
This is an invalid board that you will not receive - as battleships will always have a cell separating between them.
Follow up:
Could you do it in one-pass, using only O(1) extra memory and without modifying the value of the board?

 */
public class LT419_Battleships_in_a_Board {
    public int countBattleships(char[][] board) {
	// starting from top left corner, if no adjcent x add count. given the assumption of valid battleship.
	//assuming has valid board. 这道题降低了难度的做法是限定了不会有相邻的两个战舰的存在，有了这一点限制，那么我们只需要遍历一次二维数组就行了，只要找出战舰的起始点。所谓的战舰起始点，就是为X的点，而且该点的上方和左边的点不能为X，所以我们只要找出所有满足这个条件的点即可
	int m = board.length;
	if (m == 0)
	    return 0;
	int n = board[0].length;
	int count = 0;

	for (int i = 0; i < m; i++) {
	    for (int j = 0; j < n; j++) {
		if (board[i][j] == '.')
		    continue;
		if (i > 0 && board[i - 1][j] == 'X')		//如果左邻居或者上邻居单元格是'X'，则说明当前单元格是左边或者上边战舰的一部分；not add count
		    continue;
		if (j > 0 && board[i][j - 1] == 'X')
		    continue;
		//if (board[i][j] == '.' || (i > 0 && board[i - 1][j] == 'X') || (j > 0 && board[i][j - 1] == 'X')) continue;
		count++;					//else add count
	    }
	}

	return count;
    }
}
