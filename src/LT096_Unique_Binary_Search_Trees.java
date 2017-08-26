/*
Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

For example,
Given n = 3, there are a total of 5 unique BST's.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
   
Tree, DP-Catalan Number
 */
public class LT096_Unique_Binary_Search_Trees {
    // pick a root, then divide problem to be left and right subtree.
    // 以这个结点为根的可行二叉树数量就是左右子树可行二叉树数量的乘积，所以总的数量是将以所有结点为根的可行结果累加起来
    public int numTrees(int n) {
	if (n == 0 || n == 1)
	    return 1;

	int[] dp = new int[n + 1];
	dp[0] = 1;

	for (int i = 1; i < n + 1; i++) {
	    for (int j = 0; j < i; j++)
		dp[i] += dp[j] * dp[i - j - 1];
	}

	// Ct+1 += Ci*Ct-i Catalan number
	// O(n^2), O(n)
	// 根据上述递推式依次求出1到n的的结果即可。时间上每次求解i个结点的二叉查找树数量的需要一个i步的循环，总体要求n次，所以总时间复杂度是O(1+2+...+n)=O(n^2)。空间上需要一个数组来维护，并且需要前i个的所有信息，所以是O(n)
	return dp[n];
    }
}
