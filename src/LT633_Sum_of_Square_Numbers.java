/*
 * Given a non-negative integer c, your task is to decide whether there're two integers a and b such that a2 + b2 = c.

Example 1:
Input: 5
Output: True
Explanation: 1 * 1 + 2 * 2 = 5
Example 2:
Input: 3
Output: False

Math
 */
public class LT633_Sum_of_Square_Numbers {
    // An integer greater than one can be written as a sum of two squares if and only if its prime decomposition contains no prime congruent to 3 (mod 4) raised to an odd power.mod4=3的质因数 次方是偶数
    public boolean judgeSquareSum(int c) {
	for (int i = 0; i <= Math.sqrt(c); i++) {
	    if (Math.floor(Math.sqrt(c - i * i)) == Math.sqrt(c - i * i))
		return true;
	}
	return false;
    }
    
    //Linear.
    public boolean judgeSquareSum2(int c) {
        int root = (int) Math.sqrt(c);
        int left = 0, right = root;
        while (left <= right) {
            if (left * left + right * right < c) {
                left++;
            }
            else if (left * left + right * right > c) {
                right--;
            }
            else {
                return true;
            }
        }
        return false;
        
    }
}
