/*
 * Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...

Note:
n is positive and will fit within the range of a 32-bit signed integer (n < 231).

Example 1:

Input:
3

Output:
3
Example 2:

Input:
11

Output:
0

Explanation:
The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.

Math
 */
public class LT400_Nth_Digit {
    public int findNthDigit(int m) {
	// 1-9, 1digit. 9
	// 10-99, 2digit. 90
	// 100-999, 3digit, 900...
	long n = m;
	long count = 9, len = 1, start = 1;
	while (n > len * count) { // determine which range
	    n -= len * count; // n-9, -180, -2700...
	    len++;
	    start *= 10;
	    count *= 10;
	}

	start = start + (n - 1) / len; // get the number. overflow
	return String.valueOf(start).charAt((int) ((n - 1) % len)) - '0';

    }
}
