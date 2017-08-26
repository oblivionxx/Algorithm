/*
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.

Example 1:
     3
    / \
   2   3
    \   \ 
     3   1
Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
Example 2:
     3
    / \
   4   5
  / \   \ 
 1   3   1
Maximum amount of money the thief can rob = 4 + 5 = 9.

Tree, DFS
 */
public class LT337_House_Robber_III {
    public int rob(TreeNode root) {
        //2 scenario
        if(root==null) return 0;
        int val = 0;
        
        if(root.left!=null)
            val+=rob(root.left.left)+rob(root.left.right);
        if(root.right!=null)
            val+=rob(root.right.left)+rob(root.right.right);
        
        return Math.max(val+root.val, rob(root.left)+rob(root.right));
        //optimize with dp https://discuss.leetcode.com/topic/39834/step-by-step-tackling-of-the-problem
        
    }

    //dp[i][0]表示以i为根的子树不偷根节点能获得的最高价值，dp[i][1]表示以i为根的子树偷根节点能获得的最高价值
    public int rob2(TreeNode root) {
        int[] ans = dp(root);
        return Math.max(ans[0], ans[1]);
    }
    public int[] dp(TreeNode root) {
        if (root == null) {
            int[] now = new int[]{0, 0};
            return now;
        }
        int[] left = dp(root.left);
        int[] right = dp(root.right);
        int[] now = new int[2];
        now[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        now[1] = left[0] + right[0] + root.val;
        return now;
    }
}
