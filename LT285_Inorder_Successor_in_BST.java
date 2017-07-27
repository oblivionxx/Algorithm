/*
Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
Note: If the given node has no in-order successor in the tree, return null.

Tree
 */
public class LT285_Inorder_Successor_in_BST {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        //BST. in-order is sorted
        // search p => O(h)
        //if p.right!=null. go to the left-most of the right subtree.(=min of right subtree)
        //if p.right==null. go to the parent of p.
       
        TreeNode succ = null;
        while (root != null) {
            if (p.val < root.val) {
                succ = root;            //track parent
                root = root.left;       //go to left-most node
            }
            else
                root = root.right;      //right==null. stop while and return succ.
        }
        return succ;
    }
}
