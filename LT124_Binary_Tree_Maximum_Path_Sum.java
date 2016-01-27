/*
Given a binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path does not need to go through the root.

For example:
Given the below binary tree,

       1
      / \
     2   3
Return 6.

Tree, DFS
 */
public class LT124_Binary_Tree_Maximum_Path_Sum {
	//1. Node itself
    //2. Node with left Child
    //3. Node with right child
    //4. Node with left+right child
    
    //val can be negative
    
    public int maxPathSum(TreeNode root) {
        int[] max = new int[1];
        max[0] = Integer.MIN_VALUE;
        maxPath(root,max);
        return max[0];
    }
    
    //return maximum from case 1,2,3.used for next recursion
    //max[0] store for case 1,2,3,4
    private int maxPath(TreeNode root, int[] max) {
        if (root == null)
            return 0;
        int left = maxPath(root.left, max);		//左边一支儿（不算自己）
        int right = maxPath(root.right, max);
        int arch = left + right + root.val; 									//case 4. 
        int single = Math.max(root.val, Math.max(left, right) + root.val);		//case 1,2,3
       
        max[0] = Math.max(max[0], Math.max(arch, single));		//update maximum. compare case 1,2,3,4 and previous maximum
        return single;  										//return value is not local maximum. either left or right path. cannot be arch(arch can be local maximum).
    }
}
