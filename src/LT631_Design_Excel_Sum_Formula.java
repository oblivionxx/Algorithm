import java.util.*;

/*
 * Your task is to design the basic function of Excel and implement the function of sum formula. Specifically, you need to implement the following functions:
Excel(int H, char W): This is the constructor. The inputs represents the height and width of the Excel form. H is a positive integer, range from 1 to 26. It represents the height. W is a character range from 'A' to 'Z'. It represents that the width is the number of characters from 'A' to W. The Excel form content is represented by a height * width 2D integer array C, it should be initialized to zero. You should assume that the first row of C starts from 1, and the first column of C starts from 'A'.

void Set(int row, char column, int val): Change the value at C(row, column) to be val.
int Get(int row, char column): Return the value at C(row, column).
int Sum(int row, char column, List of Strings : numbers): This function calculate and set the value at C(row, column), where the value should be the sum of cells represented by numbers. This function return the sum result at C(row, column). This sum formula should exist until this cell is overlapped by another value or another sum formula.

numbers is a list of strings that each string represent a cell or a range of cells. If the string represent a single cell, then it has the following format : ColRow. For example, "F7" represents the cell at (7, F).
If the string represent a range of cells, then it has the following format : ColRow1:ColRow2. The range will always be a rectangle, and ColRow1 represent the position of the top-left cell, and ColRow2 represents the position of the bottom-right cell.

Example 1:
Excel(3,"C"); 
// construct a 3*3 2D array with all zero.
//   A B C
// 1 0 0 0
// 2 0 0 0
// 3 0 0 0

Set(1, "A", 2);
// set C(1,"A") to be 2.
//   A B C
// 1 2 0 0
// 2 0 0 0
// 3 0 0 0

Sum(3, "C", ["A1", "A1:B2"]);
// set C(3,"C") to be the sum of value at C(1,"A") and the values sum of the rectangle range whose top-left cell is C(1,"A") and bottom-right cell is C(2,"B"). Return 4. 
//   A B C
// 1 2 0 0
// 2 0 0 0
// 3 0 0 4

Set(2, "B", 2);
// set C(2,"B") to be 2. Note C(3, "C") should also be changed.
//   A B C
// 1 2 0 0
// 2 0 2 0
// 3 0 0 6
Note:
You could assume that there won't be any circular sum reference. For example, A1 = sum(B1) and B1 = sum(A1).
The test cases are using double-quotes to represent a character.
Please remember to RESET your class variables declared in class Excel, as static/class variables are persisted across multiple test cases. Please see here for more details.

Design
 */
public class LT631_Design_Excel_Sum_Formula {
    // point is how to update sum when changing a cell.
    // https://discuss.leetcode.com/topic/93759/java-126ms-solution/9
    private HashMap<Integer, ArrayList<Integer>> depends = new HashMap<>();

    private int[][] board = null;
    private int H = 0;
    private int W = 0;

    public LT631_Design_Excel_Sum_Formula(int H, char W) {
	board = new int[H][W];
	this.H = H;
	this.W = W;
    }

    public void set(int r, char c, int v) {
	board[r - 1][c - 'A'] = v;
	if (depends.get((r - 1) * W + (c - 'A')) != null)
	    depends.remove((r - 1) * W + (c - 'A'));
    }

    public int get(int r, char c) {
	return get(r, c, new HashMap<>());

    }

    private int get(int r, char c, HashMap<String, Integer> mapping) {
	if (mapping.get(r + "" + c) == null) {
	    int key = (r - 1) * W + (c - 'A');
	    if (depends.get(key) == null) {
		mapping.put(r + "" + c, board[r - 1][c - 'A']);
	    } else {
		int result = 0;

		Iterator<Integer> it = ((ArrayList<Integer>) depends.get(key)).iterator();
		while (it.hasNext()) {
		    int item = (int) it.next();
		    result += get(item / W + 1, (char) ('A' + item % W), mapping);
		}
		mapping.put(r + "" + c, result);
	    }
	}
	return (int) (mapping.get(r + "" + c));
    }

    public int sum(int r, char c, String[] strs) {
	int key = (r - 1) * W + (c - 'A');
	depends.put(key, new ArrayList<Integer>());
	int result = 0;
	HashMap<String, Integer> mapping = new HashMap<String, Integer>();
	for (String s : strs) {
	    String[] arr = s.split(":");

	    if (arr.length == 2) {
		// System.out.println(arr[0]+","+arr[1]);
		int min = Integer.parseInt(arr[0].substring(1));
		int max = Integer.parseInt(arr[1].substring(1));
		// System.out.println(min+","+max);
		for (int i = min; i <= max; i++) {
		    for (char j = arr[0].charAt(0); j <= arr[1].charAt(0); j++) {
			((ArrayList<Integer>) depends.get(key)).add((i - 1) * W + (j - 'A'));
			result += get(i, j, mapping);
		    }
		}
	    } else {
		((ArrayList<Integer>) depends.get(key)).add((s.charAt(1) - '1') * W + (s.charAt(0) - 'A'));
		result += get(s.charAt(1) - '0', s.charAt(0));
	    }
	}
	return result;
    }
}

/**
 * Your Excel object will be instantiated and called as such: Excel obj = new Excel(H, W); obj.set(r,c,v); int param_2 = obj.get(r,c); int param_3 = obj.sum(r,c,strs);
 */
