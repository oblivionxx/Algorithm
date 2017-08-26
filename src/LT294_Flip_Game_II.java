/*
You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.
Write a function to determine if the starting player can guarantee a win.
For example, given s = "++++", return true. The starting player can guarantee a win by flipping the middle "++" to become "+--+".

Follow up:
Derive your algorithm's runtime complexity.

Backtracking
 */
public class LT294_Flip_Game_II {
    public boolean canWin(String s) {
	int n = s.length();
	if (n < 2)
	    return false;
	return helper(s);
    }

    /*
     * exponential. can be optimized to dp. T(N): total ways to flip the string of length N. Assume the original string is "+++++++++". Move the "--" from left to write you will get: "--+++++++" (T(N
     * - 2)), "+--++++++" (T(N - 3)), "++--+++++"(T(2) * T(N - 4)), "+++--++++"(T(3) * T(N - 5)), .......,"++++++--+"(T(N - 3)), "+++++++--" T(N - 2) When a string separated by "--", e.g.,
     * "+++--++++", the time complexity is T(3) * T(N - 5), not T(N - 2). Note, T(3)*T(N - 5) < T(N - 2). So, the correct equation should be (we need to test every flip strategy) T(N) = T(N-2) +
     * T(N-3) + (T(2) * T(N-4)) + (T(3) * T(N-5)) + ... (T(N-5) T(3)) + (T(N-4) * T(2)) + T(N-3) + T(N-2) The T(N) is obviously bounded by N!!. But, I am not convinced that it is exponential.
     */
    public boolean helper(String s) {
	StringBuilder sb = new StringBuilder();
	sb.append(s);
	// backtracking
	for (int i = 0; i < s.length() - 1; i++) {
	    if (s.charAt(i) == s.charAt(i + 1) && s.charAt(i + 1) == '+') {
		sb.setCharAt(i, '-');
		sb.setCharAt(i + 1, '-');
		// if opponent lose, then I win
		if (!helper(sb.toString()))
		    return true; // sb. caches the new string flipped
		// backtracking
		sb.setCharAt(i, '+');
		sb.setCharAt(i + 1, '+');
	    }
	}

	return false;
    }

    // https://leetcode.com/discuss/64344/theory-matters-from-backtracking-128ms-to-dp-0ms
}
