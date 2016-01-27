/*
 The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 Given an integer n, return all distinct solutions to the n-queens puzzle.

 Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

 For example,
 There exist two distinct solutions to the 4-queens puzzle:
	[
	 [".Q..",  // Solution 1
	  "...Q",
	  "Q...",
	  "..Q."],
	
	 ["..Q.",  // Solution 2
	  "Q...",
	  "...Q",
	  ".Q.."]
	]
 */
/*
 * Backtracking
 */
import java.util.*;
public class LT051_N_Queens {
	public List<List<String>> solveNQueens(int n) {
		//use 1-d array for Q location. (row, arr[row])
		List<List<String>> res = new ArrayList<List<String>>();
		if(n<=0) return res;
		
		int[] arr = new int[n];
		helper(n,0,res, arr);
		return res;
    }
	
	public void helper(int n, int curRow, List<List<String>> res, int[] arr){
		if(curRow==n){
			List<String> elm = new ArrayList<>();
			//loop row
			for(int i=0;i<n;i++){
				StringBuilder sb = new StringBuilder();
				for(int j=0;j<n;j++){
					if(arr[i]==j)
						sb.append("Q");
					else
						sb.append(".");
				}
				elm.add(sb.toString());
			}
			res.add(elm);
		}else{
			//loop and backtracking
			for(int j=0;j<n;j++){
				arr[curRow] = j;
				if(isValid(arr, curRow))
					helper(n, curRow+1, res, arr);
			}
		}
	}
	
	public boolean isValid(int[] arr, int curRow){
		//check 0~curRow-1, if conflict with curRow
		for(int i=0;i<curRow;i++){
			if(arr[i]==arr[curRow] || curRow-i == Math.abs(arr[i]-arr[curRow]))
				return false;
		}
		return true;
	}
}
