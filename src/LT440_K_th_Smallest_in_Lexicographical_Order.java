/*
 * Given integers n and k, find the lexicographically k-th smallest integer in the range from 1 to n.

Note: 1 ≤ k ≤ n ≤ 109.

Example:

Input:
n: 13   k: 2

Output:
10

Explanation:
The lexicographical order is [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9], so the second smallest number is 10.

 */
public class LT440_K_th_Smallest_in_Lexicographical_Order {
    // Actually this is a de-nary tree (each node has 10 children). Find the kth element is to do a k steps preorder traverse of the tree.the idea is to calculate the steps between curr and curr + 1
    // (neighbor nodes in same level), in order to skip some unnecessary moves.
    public int findKthNumber(int n, int k) {
	int curr = 1;
	k = k - 1;
	while (k > 0) {
	    int steps = calSteps(n, curr, curr + 1);
	    if (steps <= k) {
		curr += 1;
		k -= steps;
	    } else {
		curr *= 10;
		k -= 1;
	    }
	}
	return curr; // When k=0, this is the value we want
    }

    // use long in case of overflow
    // n1= curr. n2=curr+1.
    // if n2 <= n, what means n1's right most node exists, we can simply add the number of nodes from n1 to n2 to steps.
    // else if n2 > n, what means n (the biggest node) is on the path between n1 to n2, add (n + 1 - n1) to steps.
    public int calSteps(int n, long n1, long n2) {
	int steps = 0;
	while (n1 <= n) {
	    steps += Math.min(n + 1, n2) - n1; // 2-1. 200-100. 2000-1000... if n<n2. then n-n1
	    n1 *= 10;
	    n2 *= 10;
	}
	return steps;
    }
}
