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
	class MyStack {
	    // create two new queue
	    Queue<Integer> one = new LinkedList<>();
	    Queue<Integer> two = new LinkedList<>();
	    
	    // Push element x onto stack.
	    public void push(int x) {
	        //complex here
	        //所有元素都倒序保存在q1中，即后添加的元素在q1的最前端
	        two.add(x); //
	        while(!one.isEmpty())
	            two.add(one.poll());
	        
	        //swap the two queues
	        Queue<Integer> tmp =one;
	        one = two;
	        two = tmp;
	        
	    }

	    // Removes the element on top of the stack.
	    public void pop() {
	        //remove the elm in the top of queue one
	        one.remove();
	        
	    }

	    // Get the top element.
	    public int top() {
	        return one.peek();
	    }

	    // Return whether the stack is empty.
	    public boolean empty() {
	        return one.isEmpty();
	        
	    }
	}
}
