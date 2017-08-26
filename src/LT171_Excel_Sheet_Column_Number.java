/*
Related to question Excel Sheet Column Title

Given a column title as appear in an Excel sheet, return its corresponding column number.

For example:

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28 

Math
 */
public class LT171_Excel_Sheet_Column_Number {
	public int titleToNumber(String s) {
		int res = 0;
		for (int i = 0; i < s.length(); i++) {
			int cur = s.charAt(i) - 'A' + 1;
			res = res * 26 + cur;
		}

		return res;
	}
}
