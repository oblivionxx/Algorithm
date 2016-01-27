/*
Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
Note: If the given node has no in-order successor in the tree, return null.

Tree
 */
public class LT285_Inorder_Successor_in_BST {
	public TreeNode inorderSuccessor(TreeNode root, TreeNode node) {
        //2 case. without parent pointer
        //http://stackoverflow.com/questions/5471731/in-order-successor-in-binary-search-tree
        if (node.right != null) {		// 有右孩子，直接找右子树的最小节点
		return minValue(node.right);
    	}
    	
    	TreeNode succ = null;
    	while(root != null) {
    		if(root.val > node.val) {	// 继续找更小的
    			succ = root;		// 后继节点必然比node要大，所以只能在这里保存
    			root = root.left;
    		}
    		else if(root.val < node.val){		// 继续找更大的
    			root = root.right;
    		}
    		else{		// root节点和node节点重复，停止
    			break;
    		}
    	}
    	return succ;
    
    }
    
    public static TreeNode minValue(TreeNode node) {
    	TreeNode cur = node;
    
    	// 最小节点必定在最左下角
    	while (cur.left != null) {
    		cur = cur.left;
    	}
    	return cur;
    }
}
