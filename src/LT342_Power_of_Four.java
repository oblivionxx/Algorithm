/*
 * Given an integer (signed 32 bits), write a function to check whether it is a power of 4.

Example:
Given num = 16, return true. Given num = 5, return false.

Follow up: Could you solve it without loops/recursion?

Bit Manipulation
 */
public class LT342_Power_of_Four {
    public boolean isPowerOfFour(int num) {
	// (0x55555555) <==> 1010101010101010101010101010101
	return num > 0 && ((num & (num - 1)) == 0) && (num & 0x55555555) == num;
    }
}
