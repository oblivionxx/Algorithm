/*
 * Given a binary tree, return the tilt of the whole tree.
 * The tilt of a tree node is defined as the absolute difference between the sum of all left subtree node values and the sum of all right subtree node values. Null node has tilt 0.
 * The tilt of the whole tree is defined as the sum of all nodes' tilt.
 * 
 * Tree
 */
public class LT563_Binary_Tree_Tilt {
	int res = 0;
    public int findTilt(TreeNode root) {
        sum(root);
        return res;
    }
    
    public int sum(TreeNode root){
        if(root==null) return 0;
        int leftsum = sum(root.left);
        int rightsum = sum(root.right);
        res+= Math.abs(leftsum-rightsum);
        return leftsum+rightsum+root.val;           //sum of subTree starting from root
    }
}
