import java.util.HashSet;

/*
 Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
 The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
 Note:
	A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.
 */
/*
 * HashTable
 */
public class LT036_Valid_Sudoku {
	public boolean isValidSudoku(char[][] board) {
        //row
		for(int i=0;i<9;i++){
			HashSet<Character> set = new HashSet<>();
			for(int j=0;j<9;j++){
				if(board[i][j]!='.' && set.contains(board[i][j])) return false;
				set.add(board[i][j]);
			}
		}
		//col
		for(int i=0;i<9;i++){
			HashSet<Character> set = new HashSet<>();
			for(int j=0;j<9;j++){
				if(board[j][i]!='.' && set.contains(board[j][i])) return false;
				set.add(board[j][i]);
			}
		}
		
		//sub-square
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				HashSet<Character> set = new HashSet<>();
				for(int m=3*i;m<3*i+3;m++){
					for(int n=3*j;n<3*j+3;n++){
						if(board[m][n]!='.' && set.contains(board[m][n])) return false;		//can chang to if(board!='.' && !set.add(board)) return false;
						set.add(board[m][n]);
					}
				}
			}
		}
		
		return true;
    }
}
