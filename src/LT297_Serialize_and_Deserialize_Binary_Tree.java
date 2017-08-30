import common.TreeNode;

/*
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

For example, you may serialize the following tree

    1
   / \
  2   3
     / \
    4   5
as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.

Tree, Design
 */
public class LT297_Serialize_and_Deserialize_Binary_Tree {
    // Idea: pre-order traverse the tree
    // plug in # in return if current node is null
    public String serialize(TreeNode root) {
	// write your code here
	if (root == null)
	    return "# ";
	String rnt = new String();
	rnt += (root.val + " ");
	rnt += serialize(root.left);
	rnt += serialize(root.right);
	return rnt;
    }

    /**
     * This method will be invoked second, the argument data is what exactly you serialized at method "serialize", that means the data is not given by system, it's given by your own serialize method.
     * So the format of data is designed by yourself, and deserialize it here as you serialize it in "serialize" method.
     */
    public TreeNode deserialize(String data) {
	// write your code here
	String[] str = data.split(" ");
	int[] index = new int[1];
	return helpDeserial(str, index);

    }

    // Idea: recursive build the tree using the inorder traverse array
    public TreeNode helpDeserial(String[] str, int[] index) {
	String curr = str[index[0]];
	if (curr.equals("#")) {
	    index[0]++;
	    return null;
	} else {
	    TreeNode node = new TreeNode(Integer.parseInt(str[index[0]]));
	    index[0]++;
	    node.left = helpDeserial(str, index);
	    node.right = helpDeserial(str, index);

	    return node;
	}
    }

//    // Encodes a tree to a single string.
//    public String serialize(TreeNode root) {
//	if (root == null) { // preorder sequence. split by , # for null
//	    return "#,";
//	}
//	String res = root.val + ",";
//	res += serialize(root.left);
//	res += serialize(root.right);
//	return res;
//    }
//
//    // Decodes your encoded data to tree.
//    public TreeNode deserialize(String data) {
//	String[] strings = data.split(",");
//	LinkedList<String> list = new LinkedList<>();
//	for (String string : strings) {
//	    list.add(string);
//	}
//	return reconPreOrder(list);
//    }
//
//    public TreeNode reconPreOrder(LinkedList<String> queue) {
//	String val = queue.poll();
//	if (val.equals("#")) {
//	    return null;
//	}
//	TreeNode head = new TreeNode(Integer.valueOf(val));
//	head.left = reconPreOrder(queue); // in the preorder queue. root, left sub, right sub.
//	head.right = reconPreOrder(queue); // after processing the left subtree nodes, the rest is for right subtree
//	return head;
//    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
