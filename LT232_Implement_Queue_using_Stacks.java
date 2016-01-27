import java.util.Stack;

/*
Implement the following operations of a queue using stacks.

push(x) -- Push element x to the back of queue.
pop() -- Removes the element from in front of queue.
peek() -- Get the front element.
empty() -- Return whether the queue is empty.
Notes:
You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and is empty operations are valid.
Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque (double-ended queue), as long as you use only standard operations of a stack.
You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).

Stack, Design
 */
public class LT232_Implement_Queue_using_Stacks {
	private Stack<Integer> firstStack = new Stack<Integer>();
    private Stack<Integer> secondStack = new Stack<Integer>();
    
    // Push element x to the back of queue.Inqueue
    public void push(int x) {  //put the new element into the bottom of first stack
    
        while(!firstStack.isEmpty()){
            secondStack.push(firstStack.peek());
            firstStack.pop();
        }
        secondStack.push(x);
        while(!secondStack.isEmpty()){
            firstStack.push(secondStack.peek());
            secondStack.pop();
        }
    }

    // Removes the element from in front of queue.Dequeue
    public void pop() {
        firstStack.pop();
        
    }

    // Get the front element.
    public int peek() {
        return firstStack.peek();
        
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return firstStack.isEmpty();
        
    }
}
