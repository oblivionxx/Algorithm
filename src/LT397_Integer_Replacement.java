/*
 * Given a positive integer n and you can do operations as follow:

If n is even, replace n with n/2.
If n is odd, you can replace n with either n + 1 or n - 1.
What is the minimum number of replacements needed for n to become 1?

Example 1:

Input:
8

Output:
3

Explanation:
8 -> 4 -> 2 -> 1
Example 2:

Input:
7

Output:
4

Explanation:
7 -> 8 -> 4 -> 2 -> 1
or
7 -> 6 -> 3 -> 2 -> 1

Math, Bit Manipulation
 */
public class LT397_Integer_Replacement {
    // Bit Manipulation. 111011 -> 111010 and 111011 -> 111100 remove the same number of 1's, but the second way is better.
    // need to remove as many 1's as possible, doing +1 in case of a tie? Not quite. The infamous test with n=3 fails for that strategy because 11 -> 10 -> 1 is better than 11 -> 100 -> 10 -> 1.
    // Fortunately, that's the only exception (or at least I can't think of any other, and there are none in the tests).
    // If n is even, halve it.
    // If n=3 or n-1 has less 1's than n+1, decrement n.
    // Otherwise, increment n.
    public int integerReplacement(int n) {
	int c = 0;
	while (n != 1) {
	    if ((n & 1) == 0) {
		n >>>= 1;
	    } else if (n == 3 || Integer.bitCount(n + 1) > Integer.bitCount(n - 1)) {
		--n;
	    } else {
		++n;
	    }
	    ++c;
	}
	return c;
    }
}
