import java.util.LinkedList;

/*
 The set [1,2,3,…,n] contains a total of n! unique permutations.

 By listing and labeling all of the permutations in order,
 We get the following sequence (ie, for n = 3):
	"123"
	"132"
	"213"
	"231"
	"312"
	"321"
	Given n and k, return the kth permutation sequence.
	
 Note: Given n will be between 1 and 9 inclusive.
 */
/*
 * Backtracking, Math
 */
public class LT060_Permutation_Sequence {
	public String getPermutation(int n, int k) {
		// The basic idea is to decide which is the correct number starting from
		// the highest digit use k/(n-1)!
		// The right part is k%(n-1)!

		// each number can be used only once.
		LinkedList<Integer> notUsed = new LinkedList<Integer>();

		int factorial = 1;

		for (int i = 1; i <= n; i++) {
			notUsed.add(i);
			if (i == n)
				break;
			factorial = factorial * i; // factorial of (n-1)
		}

		StringBuilder sb = new StringBuilder();
		k = k - 1; // index starting from 0;
		// do for n-1 round. n=1~n.
		int round = n - 1;
		while (round >= 0) {
			int index = k / factorial; // k/(n-1)!
			k %= factorial; // the rest. update k for next loop.
			sb.append(notUsed.get(index));
			notUsed.remove(index);
			if (round > 0)
				factorial /= round; // new (n-1)!. here round is also the size
									// of notUsed list.
			round--;
		}

		return sb.toString();
	}
}
