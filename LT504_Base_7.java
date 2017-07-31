/*
 * Given an integer, return its base 7 string representation.

Example 1:
Input: 100
Output: "202"
Example 2:
Input: -7
Output: "-10"
Note: The input will be in range of [-1e7, 1e7].

 */
public class LT504_Base_7 {
    public String convertToBase7(int num) {
	if (num == 0)
	    return "0";
	String res = "";
	boolean isPositive = num > 0;
	while (num != 0) {
	    res = Math.abs(num % 7) + res;
	    num /= 7;
	}

	return isPositive ? res : "-" + res;
    }

    // recursion
    public String convertToBase7_2(int num) {
	if (num < 0)
	    return "-" + convertToBase7_2(-num);
	if (num < 7)
	    return Integer.toString(num);
	return convertToBase7_2(num / 7) + Integer.toString(num % 7);
    }
}
