/*
Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Note:
Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.

Tree, Binary Search
 */
public class LT270_Closest_Binary_Search_Tree_Value {
	public int closestValue(TreeNode root, double target) {
        //recursion
        int a = root.val;
        TreeNode kid = target < a ? root.left : root.right;
        if (kid == null) return a;
        int b = closestValue(kid, target);      
        return Math.abs(a - target) < Math.abs(b - target) ? a : b;     //compare target, kid, root
    }
	
	//Closest is either the root's value (a) or the closest in the appropriate subtree (b).
    public int closestValue2(TreeNode root, double target) {
        //iterative
        int closest = root.val;
        while (root!=null) {
            if (Math.abs(closest - target) >= Math.abs(root.val - target))    
                //track pre parent and cur kid to compare the target
                closest = root.val;
            root = target<root.val?root.left:root.right;
        }
        return closest;
    }
}
