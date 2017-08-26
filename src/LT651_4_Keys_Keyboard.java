/*
 * Imagine you have a special keyboard with the following keys:

Key 1: (A): Prints one 'A' on screen.

Key 2: (Ctrl-A): Select the whole screen.

Key 3: (Ctrl-C): Copy selection to buffer.

Key 4: (Ctrl-V): Print buffer on screen appending it after what has already been printed.

Now, you can only press the keyboard for N times (with the above four keys), find out the maximum numbers of 'A' you can print on screen.

Example 1:
Input: N = 3
Output: 3
Explanation: 
We can at most get 3 A's on screen by pressing following key sequence:
A, A, A
Example 2:
Input: N = 7
Output: 9
Explanation: 
We can at most get 9 A's on screen by pressing following key sequence:
A, A, A, Ctrl A, Ctrl C, Ctrl V, Ctrl V
Note:
1 <= N <= 50
Answers will be in the range of 32-bit signed integer.

Math, Greedy, DP
 */
public class LT651_4_Keys_Keyboard {
    public int maxA(int N) {
	int[] dp = new int[N + 1];
	for (int i = 1; i <= N; i++) {
	    dp[i] = i;
	    for (int j = 3; j < i; j++) {
		dp[i] = Math.max(dp[i], dp[i - j] * (j - 1)); // dp[i-j] is how many As already there. (j-1) is to ctrl-c,a,v. j-1 at least *2. then j++, add one more ctrl-v.
	    }
	}
	return dp[N];
    }

    public int maxA2(int N) {
	if (N <= 6)
	    return N;
	int[] dp = new int[N + 1];
	for (int i = 1; i <= 6; i++) {
	    dp[i] = i;
	}
	for (int i = 7; i <= N; i++) {
	    dp[i] = Math.max(dp[i - 4] * 3, dp[i - 5] * 4);
	    // dp[i] = Math.max(dp[i - 4] * 3, Math.max(dp[i - 5] * 4, dp[i - 6] * 5));
	}
	return dp[N];
    }
}
