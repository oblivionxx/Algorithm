/*
Additive number is a string whose digits can form additive sequence.

A valid additive sequence should contain at least three numbers. Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.

For example:
"112358" is an additive number because the digits can form an additive sequence: 1, 1, 2, 3, 5, 8.

1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
"199100199" is also an additive number, the additive sequence is: 1, 99, 100, 199.
1 + 99 = 100, 99 + 100 = 199
Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.

Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.

Follow up:
How would you handle overflow for very large input integers?

 */
public class LT306_Additive_Number {
    public boolean isAdditiveNumber(String num) {
	int len = num.length();
	for (int i = 1; i <= (len - 1) / 2; i++) {
	    if (num.charAt(0) == '0' && i >= 2)
		continue;
	    for (int j = i + 1; len - j >= j - i && len - j >= i; j++) {
		if (num.charAt(i) == '0' && j - i >= 2)
		    continue;

		if (isAdditiveHelper(num, i, j))
		    return true;
	    }
	}

	return false;
    }

    public boolean isAdditiveHelper(String num, int i, int j) {
	long x1 = Long.parseLong(num.substring(0, i));
	long x2 = Long.parseLong(num.substring(i, j));
	String sum;

	for (int start = j; start != num.length(); start += sum.length()) {
	    x2 = x2 + x1;
	    x1 = x2 - x1;
	    sum = ((Long) x2).toString();
	    if (!num.startsWith(sum, start))
		return false;
	}
	return true;
    }

}
