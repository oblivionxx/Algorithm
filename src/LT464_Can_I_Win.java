import java.util.*;

/*
 * In the "100 game," two players take turns adding, to a running total, any integer from 1..10. The player who first causes the running total to reach or exceed 100 wins.

What if we change the game so that players cannot re-use integers?

For example, two players might take turns drawing from a common pool of numbers of 1..15 without replacement until they reach a total >= 100.

Given an integer maxChoosableInteger and another integer desiredTotal, determine if the first player to move can force a win, assuming both players play optimally.

You can always assume that maxChoosableInteger will not be larger than 20 and desiredTotal will not be larger than 300.

Example

Input:
maxChoosableInteger = 10
desiredTotal = 11

Output:
false

Explanation:
No matter which integer the first player choose, the first player will lose.
The first player can choose an integer from 1 up to 10.
If the first player choose 1, the second player can only choose integers from 2 up to 10.
The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
Same with other integers chosen by the first player, the second player will always win.

DP, Minimax
 */
public class LT464_Can_I_Win {
    public boolean canIWin1(int maxChoosableInteger, int desiredTotal) {
	if (desiredTotal <= 0)
	    return true;
	if (maxChoosableInteger * (maxChoosableInteger + 1) / 2 < desiredTotal)
	    return false;
	return canIWin1(desiredTotal, new int[maxChoosableInteger], new HashMap<>());
    }

    private boolean canIWin1(int total, int[] state, HashMap<String, Boolean> hashMap) {
	String curr = Arrays.toString(state);
	if (hashMap.containsKey(curr))
	    return hashMap.get(curr);
	for (int i = 0; i < state.length; i++) {
	    if (state[i] == 0) {
		state[i] = 1;
		if (total <= i + 1 || !canIWin1(total - (i + 1), state, hashMap)) {
		    hashMap.put(curr, true);
		    state[i] = 0;
		    return true;
		}
		state[i] = 0;
	    }
	}
	hashMap.put(curr, false);
	return false;
    }

    public boolean canIWin2(int maxChoosableInteger, int desiredTotal) {
	if (desiredTotal <= 0)
	    return true;
	if (maxChoosableInteger * (maxChoosableInteger + 1) / 2 < desiredTotal)
	    return false;
	return canIWin2(desiredTotal, maxChoosableInteger, 0, new HashMap<>());
    }

    private boolean canIWin2(int total, int n, int state, HashMap<Integer, Boolean> hashMap) {
	if (hashMap.containsKey(state))
	    return hashMap.get(state);
	for (int i = 0; i < n; i++) {
	    if ((state & (1 << i)) != 0)
		continue;
	    if (total <= i + 1 || !canIWin2(total - (i + 1), n, state | (1 << i), hashMap)) {
		hashMap.put(state, true);
		return true;
	    }
	}
	hashMap.put(state, false);
	return false;
    }
}
