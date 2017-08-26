import java.util.Stack;

/*
 * Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.

Note:
The length of num is less than 10002 and will be ≥ k.
The given num does not contain any leading zero.
Example 1:

Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
Example 2:

Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
Example 3:

Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.

Stack, Greedy
 */
public class LT402_Remove_K_Digits {
    public String removeKdigits(String num, int k) {
	// one can simply scan from left to right, and remove the first "peak" digit; the peak digit is larger than its right neighbor. One can repeat this procedure k times, and obtain the first
	// algorithm. O(k*n)
	// using a stack to find peak. dont need to scan every time from the begining
	if (k >= num.length())
	    return "0";
	Stack<Character> stack = new Stack<>();
	int i = 0;
	while (i < num.length()) {
	    // whenever meet a digit which is less than the previous digit, discard the previous one
	    while (k > 0 && !stack.isEmpty() && stack.peek() > num.charAt(i)) {
		stack.pop();
		k--;
	    }
	    stack.push(num.charAt(i));
	    i++;
	}

	// corner case. like 1111
	while (k > 0) {
	    stack.pop();
	    k--;
	}

	// construct the number from the stack
	StringBuilder sb = new StringBuilder();
	while (!stack.isEmpty())
	    sb.append(stack.pop());
	sb.reverse();

	// remove all the 0 at the head. eg 10200-->0200-->200
	while (sb.length() > 1 && sb.charAt(0) == '0')
	    sb.deleteCharAt(0);
	return sb.toString();
    }
}
