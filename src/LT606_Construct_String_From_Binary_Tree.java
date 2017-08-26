import utils.TreeNode;

/*
 * You need to construct a string consists of parenthesis and integers from a binary tree with the preorder traversing way.
 * The null node needs to be represented by empty parenthesis pair "()". And you need to omit all the empty parenthesis pairs that don't affect the one-to-one mapping relationship between the string and the original binary tree.
 * eg: Binary tree: [1,2,3,4] -->"1(2(4))(3)"
 * eg: Binary tree: [1,2,3,null,4] -->"1(2()(4))(3)"
 * 
 * Tree, String
 */
public class LT606_Construct_String_From_Binary_Tree {
    public String tree2str(TreeNode t) {
	StringBuilder sb = new StringBuilder();
	helper(t, sb);
	return sb.toString();
    }

    public void helper(TreeNode t, StringBuilder sb) {
	if (t != null) {
	    sb.append(t.val);
	    if (t.left != null || t.right != null) {
		sb.append("(");
		helper(t.left, sb); // did not do null check here to satisfy the question
		sb.append(")");
		if (t.right != null) { // did null check for right node
		    sb.append("(");
		    helper(t.right, sb);
		    sb.append(")");
		}
	    }
	}
    }
}
