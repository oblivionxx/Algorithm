/*
 * A magical string S consists of only '1' and '2' and obeys the following rules:

The string S is magical because concatenating the number of contiguous occurrences of characters '1' and '2' generates the string S itself.

The first few elements of string S is the following: S = "1221121221221121122……"

If we group the consecutive '1's and '2's in S, it will be:

1 22 11 2 1 22 1 22 11 2 11 22 ......

and the occurrences of '1's or '2's in each group are:

1 2 2 1 1 2 1 2 2 1 2 2 ......

You can see that the occurrence sequence above is the S itself.

Given an integer N as input, return the number of '1's in the first N number in the magical string S.

Note: N will not exceed 100,000.

Example 1:
Input: 6
Output: 3
Explanation: The first 6 elements of magical string S is "12211" and it contains three 1's, so return 3.

 */
public class LT481_Magical_String {
    /*
     * Value-of-Current-Element Index-of-Element-to-Create (start from 1) 1 1 2 2, 3 2 4, 5 1 6 1 7 2 8, 9
     */
    public int magicalString(int n) {
	if (n < 1)
	    return 0;
	if (n == 1)
	    return 1;

	int[] s = new int[n + 1]; // s save the string [1-n]
	int val = 1, index = 2, count = 1; // s[1] = 1. so count start from 1
	for (int i = 2; i <= n; i++) { // i loop the current position. index is the element to create
	    val = val == 2 ? 1 : 2; // flip value
	    s[index++] = val;
	    if (val == 1)
		count++;
	    if (index > n)
		break;
	    if (s[i] == 2) { // repeat.
		s[index++] = val;
		if (val == 1)
		    count++;
		if (index > n)
		    break;
	    }

	}

	return count;
    }
}
