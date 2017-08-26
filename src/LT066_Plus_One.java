/*
 Given a non-negative number represented as an array of digits, plus one to the number.
 The digits are stored such that the most significant digit is at the head of the list.
 */
/*
 * Array, Math
 */
public class LT066_Plus_One {
	public int[] plusOne(int[] digits) {
		// add from bwd
		if (digits == null || digits.length == 0)
			return digits;
		int carry = 1;
		for (int i = digits.length - 1; i >= 0; i--) {
			int num = digits[i] + carry;
			carry = num / 10;
			digits[i] = num % 10;
		}

		if (carry > 0) {
			int[] res = new int[digits.length + 1];
			res[0] = 1;
			return res; // must be 999+1 case. so = 1000.
		}

		return digits;
	}
}
