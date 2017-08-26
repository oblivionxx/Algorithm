/*
 Follow up for N-Queens problem.
 Now, instead outputting board configurations, return the total number of distinct solutions .
 */
/*
 * Backtracking
 */
public class LT052_N_Queens_II {
	public int totalNQueens(int n) {
		// use 1-d array for Q location. (row, arr[row])
		int[] res = new int[1];
		if (n <= 0)
			return 0;

		int[] arr = new int[n];
		helper(n, 0, res, arr);
		return res[0];
	}

	public void helper(int n, int curRow, int[] res, int[] arr) {
		if (curRow == n) {
			res[0]++;
		} else {
			// loop and backtracking
			for (int j = 0; j < n; j++) {
				arr[curRow] = j;
				if (isValid(arr, curRow))
					helper(n, curRow + 1, res, arr);
			}
		}
	}

	public boolean isValid(int[] arr, int curRow) {
		// check 0~curRow-1, if conflict with curRow
		for (int i = 0; i < curRow; i++) {
			if (arr[i] == arr[curRow] || curRow - i == Math.abs(arr[i] - arr[curRow]))
				return false;
		}
		return true;
	}
}
