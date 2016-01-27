/*
Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
Tree, DFS
 */
public class LT108_Convert_Sorted_Array_to_Binary_Search_Tree {
	//find mid. build recursively
	public TreeNode sortedArrayToBST(int[] nums) {
        if(nums==null || nums.length==0) return null;
        return helper(nums,0,nums.length-1);
    }
    
    private TreeNode helper(int[] nums, int start, int end){
        if(start>end)
            return null;							//important.
        int m=(start+end)/2;
        TreeNode root = new TreeNode(nums[m]);
        root.left = helper(nums,start,m-1);
        root.right = helper(nums,m+1,end);
        
        return root;
    }
}
