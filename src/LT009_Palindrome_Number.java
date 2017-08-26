/*
 * Determine whether an integer is a palindrome. Do this without extra space.
 */
/*
 * Math
 * Negative integers cannot be palindromes
 * 1. Convert integer to string use extra space. 
 * 2. Reverse integer may cause overflow
 */
public class LT009_Palindrome_Number {
    public boolean isPalindrome(int x) {
	// negative not possible
	if (x < 0)
	    return false;

	// get how many digit

	int digit = 1;
	while (x / digit >= 10) {
	    digit *= 10;
	}

	while (x > 0) {
	    int right = x % 10;
	    int left = x / digit;
	    if (left != right)
		return false;
	    x = x % (digit) / 10;
	    digit /= 100;
	}

	return true;
    }

    public boolean isPalindromeSol2(int x) {
	// only compare half of digits in x
	if (x < 0 || (x != 0 && x % 10 == 0))
	    return false;
	int rev = 0;
	while (x > rev) {
	    rev = rev * 10 + x % 10; // reverse the right half of x.
	    x = x / 10; // x is the remaining left half
	}
	return (x == rev || x == rev / 10);
    }
}
