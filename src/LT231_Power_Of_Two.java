/*
Given an integer, write a function to determine if it is a power of two.

Math, Bit Manipulation
 */
public class LT231_Power_Of_Two {
    public boolean isPowerOfTwo(int n) {
	return n > 0 && (n & (n - 1)) == 0; // == is higher priority than &
    }
}
