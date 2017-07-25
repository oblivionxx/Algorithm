import java.util.Stack;

/*
Given a binary tree, flatten it to a linked list in-place.

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6
The flattened tree should look like:
   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6
click to show hints.

Hints:
If you notice carefully in the flattened tree, each node's right child points to the next node of a pre-order traversal.

Tree, DFS
 */
public class LT114_Flatten_Binary_Tree_To_Linked_List {
	public void flatten(TreeNode root) {
        if (root == null) return; 
        TreeNode left = root.left;
        TreeNode right = root.right;
        
        root.left = null;
        flatten(left);
        flatten(right);
        
        root.right = left;
        TreeNode cur = root;
        while (cur.right != null) cur = cur.right;
        cur.right = right;
    }
	
	public void flatten2(TreeNode root) {
		if(root==null) return;
        Stack<TreeNode> stk = new Stack<TreeNode>();
        TreeNode cur = root;
        
        while(!stk.isEmpty() || cur!=null){
            if(cur.right!=null){
                stk.push(cur.right);   //inOrder push, go down to the left child
            }
            
            if(cur.left != null){
                cur.right = cur.left;		//update right child.
                cur.left = null;
            }else{
                if(!stk.isEmpty()){
                     TreeNode temp = stk.pop();
                     cur.right=temp;		//update right child
                }
            }
            cur = cur.right;
            //对整棵树一直向右子树方向遍历。当遍历的节点有右孩子时，就将其入栈。有左孩子时，将其更新为当前节点的右孩子，左孩子置空。当左孩子为空时而栈不空时，
        }
        return;
    }
}
