/*
 * Given an integer, write a function to determine if it is a power of three.

Follow up:
Could you do it without using any loop / recursion?

Math
 */
public class LT326_Power_Of_3 {
    public boolean isPowerOfThree(int n) {
	if (n > 1) {
	    while (n % 3 == 0)
		n /= 3;
	}

	return n == 1;
	// other way: http://www.cnblogs.com/grandyang/p/5138212.html
    }
}
