import java.util.HashMap;

/*
Given inorder and postorder traversal of a tree, construct the binary tree.
Note:
You may assume that duplicates do not exist in the tree.
Tree, Array, DFS
 */
public class LT106_Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal {
	public TreeNode buildTree(int[] inorder, int[] postorder) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<inorder.length;i++){					//use inorder as a map. 
            map.put(inorder[i], i);
        }
        
        return helper(postorder, 0, postorder.length-1, inorder, 0, inorder.length-1, map);
    }
    
    public TreeNode helper(int[] post, int postL, int postR, int[] in, int inL, int inR, HashMap<Integer, Integer> map){
        if(postL>postR || inL>inR) return null;
        TreeNode root = new TreeNode(post[postR]);
        int index = map.get(root.val);
        
        //from inorder find how many elements for left tree and right tree.
        //inorder index is root. left->[inL, index-1]. has index-inL-1 elements.   right->[index+1, inR].
        //postorder postR is root.  left -> postL~(index-inL-1 elements)postL+index-inL-1    right -> postL+index-inL~postR-1
        
        root.left = helper(post, postL, postL+index-inL-1, in, inL, index-1, map);
        root.right = helper(post, postL+index-inL, postR-1, in, index+1, inR, map);
        
        return root;

    }	
}
