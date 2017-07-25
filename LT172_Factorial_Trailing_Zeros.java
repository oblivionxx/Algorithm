/*
 Given an integer n, return the number of trailing zeroes in n!.
 Note: Your solution should be in logarithmic time complexity.
 
 Math
 */

public class LT172_Factorial_Trailing_Zeros {
	// count multiple of 5, 25, 125...
	public int trailingZeroes(int n) {
		if (n < 0)
			return -1;

		int count = 0;
		for (long i = 5; n / i >= 1; i *= 5) {
			count += n / i;
		}

		return count;
	}

	public int trailingZeroes2(int n) {
		return n == 0 ? 0 : n / 5 + trailingZeroes(n / 5);
	}
}
