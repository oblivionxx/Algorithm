/*
Invert a binary tree.

     4
   /   \
  2     7
 / \   / \
1   3 6   9
to
     4
   /   \
  7     2
 / \   / \
9   6 3   1

Tree
 */
public class LT226_Invert_Binary_Tree {
	public TreeNode invertTree(TreeNode root) {
        if(root!=null)
            buildTree(root);
        return root;
        
    }
    
    public void buildTree(TreeNode root){
        
        if(root.left!=null)
            invertTree(root.left);
        if(root.right!=null)
            invertTree(root.right);
            
        TreeNode tmp=null;
        tmp = root.left;
        root.left = root.right;
        root.right = tmp;
            
    }
}
