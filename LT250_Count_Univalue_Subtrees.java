/*
Given a binary tree, count the number of uni-value subtrees.

A Uni-value subtree means all nodes of the subtree have the same value.

For example:
Given binary tree,
              5
             / \
            1   5
           / \   \
          5   5   5
return 4.

Tree
 */
public class LT250_Count_Univalue_Subtrees {
	recheck
	//improved. recheck
	public int countUnivalSubtrees2(TreeNode root) {
        int[] count = new int[1];
        helper2(root, count);
        return count[0];
    }

	//check isUnivalued subtree
    private boolean helper2(TreeNode node, int[] count) {
        if (node == null) {
            return true;
        }
        boolean left = helper2(node.left, count);
        boolean right = helper2(node.right, count);
        if (left && right) {
            if (node.left != null && node.val != node.left.val) {
                return false;
            }
            if (node.right != null && node.val != node.right.val) {
                return false;
            }
            count[0]++;
            return true;
        }
        //if left or right = false, means left or right sub is not univalued. so dont need to count node, left, and right. is sure not to be univalued subtree.
        return false;
    }
	
	//need return type boolean to check parent==child, and a global variable to save sum
    public int countUnivalSubtrees(TreeNode root) {
        if(root==null) return 0;
        int[] sum = new int[1];

        helper(root, sum);
        return sum[0];
    }
    
    public boolean helper(TreeNode root, int[] sum){
        if(root.left==null && root.right==null){
            sum[0]++;
            return true;
        }else if(root.left!=null && root.right==null){
            if(helper(root.left, sum) && root.val==root.left.val){
                sum[0]++;
                return true;
            }else
                return false;
        }else if(root.left==null && root.right!=null){
            if(helper(root.right, sum) && root.val==root.right.val){
                sum[0]++;
                return true;
            }else
                return false;
        }else{      //has two child not null. check if equal...
            boolean l = helper(root.left,sum);
            boolean r = helper(root.right, sum);
        
            if(l&&r&&root.val==root.left.val && root.val==root.right.val){
                sum[0]++;
                return true;
                
            }
            else
                return false;
        }
        
    }
}
