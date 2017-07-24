/*
Two elements of a binary search tree (BST) are swapped by mistake.
Recover the tree without changing its structure.

Note:
A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?

 */
public class LT099_Recover_Binary_Search_Tree {
	//O(n) space using a stack and inorder traversal. if the number is not correct. note.
	//O(1) space by Morris Traversal
	//http://www.cnblogs.com/AnnieKim/archive/2013/06/15/morristraversal.html
	//https://leetcode.com/discuss/26310/detail-explain-about-morris-traversal-finds-incorrect-pointer
	
	TreeNode pre;
    TreeNode first;
    TreeNode second;
    
    public void recoverTree(TreeNode root) {
        pre = null; first =null; second =null;
        inOrder(root);
        
        if(first!=null && second!=null){
            //swap value is enough
            int temp = first.val;
            first.val = second.val;
            second.val = temp;
        }
        
    }
    
    public void inOrder(TreeNode root){
        if(root==null) return;
        
        inOrder(root.left);
        if(pre==null)
            pre = root;
        else{
            if(pre.val > root.val){         //pre is when we finishing left subtree, when going up -->some left child. so should less than root.val
                if(first==null)
                    first = pre;
                second = root;   //第二个乱序的元素。如果用了else，则无法通过只有两个元素的情况
            }
            pre = root;
        }
        
        inOrder(root.right);
        
    }
}
