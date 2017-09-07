import java.util.*;

import common.TreeNode;

/*
Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

Note:
Given target value is a floating point.
You may assume k is always valid, that is: k ≤ total nodes.
You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
Follow up:
Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?

Hint:

Consider implement these two helper functions:
getPredecessor(N), which returns the next smaller node to N.
getSuccessor(N), which returns the next larger node to N.
Try to assume that each node has a parent pointer, it makes the problem much easier.
Without parent pointer we just need to keep track of the path from the root to the current node using a stack.
You would need two stacks to track the path in finding predecessor and successor node separately.

Tree, Stack
 */
public class LT272_Closest_Binary_Search_Tree_Value_II {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
	Comparator<Integer> cmp = new Comparator<Integer>() {
	    public int compare(Integer o1, Integer o2) {
		if (o1 == o2) {
		    return 0;
		}
		if (Math.abs(o1 - target) < Math.abs(o2 - target)) {
		    return 1;
		} else {
		    return -1;
		}
	    }
	};
	// heap save on the top the fartest node to value
	Queue<Integer> queue = new PriorityQueue<Integer>(cmp);
	helper(root, target, k, queue);
	return new ArrayList<Integer>(queue);
    }

    //O(k) space
    public void helper(TreeNode root, double target, int k, Queue<Integer> queue) {
	if (root == null) {
	    return;
	}

	helper(root.left, target, k, queue);
	if (queue.size() < k) {
	    queue.offer(root.val);
	} else {
	    // update root to be the kth closest
	    if (Math.abs(queue.peek() - target) > Math.abs(root.val - target)) {
		queue.poll();
		queue.add(root.val);
	    }
	}
	helper(root.right, target, k, queue);
    }

    // use inorder traversal?
    public List<Integer> closestKValues2(TreeNode root, double target, int k) {
	Stack<Integer> s1 = new Stack<>(), s2 = new Stack<>();
	List<Integer> list = new ArrayList<>();
	inOrder(root, s1, true, target);
	inOrder(root, s2, false, target);
	while (list.size() < k) {
	    if (s1.isEmpty()) {
		list.add(s2.pop());
		continue;
	    }
	    if (s2.isEmpty()) {
		list.add(s1.pop());
		continue;
	    }
	    if (Math.abs(target - s1.peek()) > Math.abs(target - s2.peek()))
		list.add(s2.pop());
	    else
		list.add(s1.pop());
	}
	return list;
    }

    public void inOrder(TreeNode root, Stack<Integer> stack, boolean reverse, double target) {
	if (root == null)
	    return;
	inOrder(reverse ? root.right : root.left, stack, reverse, target);
	if ((target >= root.val && reverse) || (target < root.val && !reverse))
	    return;
	stack.push(root.val);
	inOrder(reverse ? root.left : root.right, stack, reverse, target);
    }
}
