import common.TreeLinkNode;

/*
Follow up for problem "Populating Next Right Pointers in Each Node".

What if the given tree could be any binary tree? Would your previous solution still work?

Note:

You may only use constant extra space.
For example,
Given the following binary tree,
         1
       /  \
      2    3
     / \    \
    4   5    7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \    \
    4-> 5 -> 7 -> NULL

Tree, DFS
 */
public class LT117_Populating_Next_Right_Pointers_in_Each_Node_II {
    public void connect(TreeLinkNode root) {
	if (root == null)
	    return;
	TreeLinkNode preNext = root.next;
	TreeLinkNode next = null;
	while (preNext != null) {
	    if (preNext.left != null) {
		next = preNext.left;
		break;
	    } else if (preNext.right != null) {
		next = preNext.right;
		break;
	    } else {
		preNext = preNext.next;
	    }
	}

	// attention: connect right first.
	if (root.right != null) {
	    root.right.next = next;
	}

	if (root.left != null) {
	    if (root.right != null)
		root.left.next = root.right;
	    else
		root.left.next = next;
	}

	// attention: connect right first.
	connect(root.right);
	connect(root.left);

	/*
	 * if change to connect left first. error case: 2 / \ 1 3 / \ / \ 0 7 9 1 / / \ / \ 2 1 0 8 8 / 7
	 * 
	 * Input: {2,1,3,0,7,9,1,2,#,1,0,#,#,8,8,#,#,#,#,7} Output: {2,#,1,3,#,0,7,9,1,#,2,1,0,#,7,#} Expected: {2,#,1,3,#,0,7,9,1,#,2,1,0,8,8,#,7,#}
	 * 
	 * because, after loop connect 1->0. will go to connect next level 7.
	 */

    }

    // iterative.recheck
    public void connect1(TreeLinkNode root) {
	TreeLinkNode dummyHead = new TreeLinkNode(0);
	TreeLinkNode pre = dummyHead;
	while (root != null) {
	    if (root.left != null) {
		pre.next = root.left;
		pre = pre.next;
	    }
	    if (root.right != null) {
		pre.next = root.right;
		pre = pre.next;
	    }
	    root = root.next;
	    if (root == null) {
		pre = dummyHead;
		root = dummyHead.next;
		dummyHead.next = null;
	    }
	}
    }
}
