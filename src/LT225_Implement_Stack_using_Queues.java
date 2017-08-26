
/*
Implement the following operations of a stack using queues.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
empty() -- Return whether the stack is empty.
Notes:
You must use only standard operations of a queue -- which means only push to back, peek/pop from front, size, and is empty operations are valid.
Depending on your language, queue may not be supported natively. You may simulate a queue by using a list or deque (double-ended queue), as long as you use only standard operations of a queue.
You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack).
Update (2015-06-11):
The class name of the Java function had been updated to MyStack instead of Stack.

Design, Stack
 */
import java.util.*;

public class LT225_Implement_Stack_using_Queues {
    public class MyStack {
	// one Queue solution
	private Queue<Integer> q = new LinkedList<Integer>();

	/** Initialize your data structure here. */
	public MyStack() {

	}

	/** Push element x onto stack. */
	public void push(int x) {
	    q.add(x);
	    for (int i = 1; i < q.size(); i++) { // rotate the queue to make the tail be the head
		q.add(q.poll());
	    }
	}

	/** Removes the element on top of the stack and returns that element. */
	public int pop() {
	    return q.poll();
	}

	/** Get the top element. */
	public int top() {
	    return q.peek();
	}

	/** Returns whether the stack is empty. */
	public boolean empty() {
	    return q.isEmpty();
	}
    }
}
