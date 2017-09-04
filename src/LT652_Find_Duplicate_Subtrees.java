import java.util.*;

import common.TreeNode;

/*
 * Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return the root node of any one of them.

Two trees are duplicate if they have the same structure with same node values.

Example 1: 
        1
       / \
      2   3
     /   / \
    4   2   4
       /
      4
The following are two duplicate subtrees:
      2
     /
    4
and
    4
Therefore, you need to return above trees' root in the form of a list.

Tree
 */
public class LT652_Find_Duplicate_Subtrees {
    // do preorder serialzation of all nodes. check if the serialization string exist already
    HashSet<String> list = new HashSet<>();
    List<TreeNode> res = new ArrayList<>();
    HashSet<String> done = new HashSet<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
	if (root == null)
	    return res;

	if (root != null) {
	    StringBuilder sb = new StringBuilder();
	    Serialize(root, sb);
	    String s = sb.toString();

	    if (!list.contains(s)) {
		list.add(s);
	    } else {
		if (!done.contains(s)) { // save in result only once
		    res.add(root);
		    done.add(s);
		}
	    }

	    findDuplicateSubtrees(root.left);
	    findDuplicateSubtrees(root.right);
	}

	return res;

    }

    public void Serialize(TreeNode root, StringBuilder sb) {
	String del = ",";
	sb.append(((root == null) ? "null" : root.val) + del);
	if (root != null) {
	    Serialize(root.left, sb);
	    Serialize(root.right, sb);
	}
    }

    // postOrder. O(n)
    public List<TreeNode> findDuplicateSubtrees2(TreeNode root) {
	List<TreeNode> res = new LinkedList<>();
	postorder(root, new HashMap<>(), res);
	return res;
    }

    public String postorder(TreeNode cur, Map<String, Integer> map, List<TreeNode> res) {
	if (cur == null)
	    return "#";
	String serial = cur.val + "," + postorder(cur.left, map, res) + "," + postorder(cur.right, map, res);
	if (map.getOrDefault(serial, 0) == 1)
	    res.add(cur);
	map.put(serial, map.getOrDefault(serial, 0) + 1);
	return serial;
    }
}
