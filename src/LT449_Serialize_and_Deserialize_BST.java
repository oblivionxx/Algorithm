import java.util.LinkedList;

import common.TreeNode;

/*
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to the original tree structure.

The encoded string should be as compact as possible.

Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.

Tree
 */
public class LT449_Serialize_and_Deserialize_BST {
    // This method doesn't use the property of BST. O(n^2)
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
	if (root == null) { // preorder sequence. split by , # for null
	    return "#,";
	}
	String res = root.val + ",";
	res += serialize(root.left);
	res += serialize(root.right);
	return res;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
	String[] strings = data.split(",");
	LinkedList<String> list = new LinkedList<>();
	for (String string : strings) {
	    list.add(string);
	}
	return reconPreOrder(list);
    }

    public TreeNode reconPreOrder(LinkedList<String> queue) {
	String val = queue.poll();
	if (val.equals("#")) {
	    return null;
	}
	TreeNode head = new TreeNode(Integer.valueOf(val));
	head.left = reconPreOrder(queue); // in the preorder queue. root, left sub, right sub.
	head.right = reconPreOrder(queue); // after processing the left subtree nodes, the rest is for right subtree
	return head;
    }
}
