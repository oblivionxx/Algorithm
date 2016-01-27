/*
Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.

For example:
Given n = 13,
Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.

Hint:

Beware of overflow.

 */
public class LT233_Number_of_Digit_One {
	//Trick. https://leetcode.com/discuss/46366/ac-short-java-solution. loop (k=1. k*=10)  r=n/k. m=n%k. cnt+ = (r+8)/10*k+(r%10==1?m+1:0)
	public int countDigitOne(int n) {
        int res = 0, a = 1, b = 1;
        while (n > 0) {
            res += (n + 8) / 10 * a + (n % 10 == 1?1:0) * b;
            b += n % 10 * a;
            a *= 10;
            n /= 10;
        }
        return res;
    }
}
