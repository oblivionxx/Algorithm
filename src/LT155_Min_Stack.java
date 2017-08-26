/*
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.

Stack, Design
 */
import java.util.*;
public class LT155_Min_Stack {
	//implement using 2 linkedlist or 2 stack.
    Stack<Integer> stk = new Stack<>();
    Stack<Integer> min = new Stack<>();		//store the min number. 
    
    public void push(int x) {
        if(min.isEmpty()|| min.peek()>=x)
            min.push(x);
        stk.push(x);
    }

    public void pop() {
        if(stk.isEmpty()){
            return;
        }
          
        int elm = stk.pop();
        if(!min.isEmpty() && min.peek()==elm)			//some more steps for min-stack.
            min.pop();
        
    }

    public int top() {
        if(!stk.isEmpty())
            return stk.peek();
        return 0;
    }

    public int getMin() {
        if(!min.isEmpty())
            return min.peek();
            
        return 0;
    }
	
	/*
	ArrayList<Integer> stk = new ArrayList<>();
    ArrayList<Integer> min = new ArrayList<>();
    
    public void push(int x) {
        if(min.isEmpty()|| min.get(min.size()-1)>=x)
            min.add(x);
        stk.add(x);
    }

    public void pop() {
        if(stk.isEmpty()){
            return;
        }
          
        int elm = stk.remove(stk.size()-1);
        if(!min.isEmpty() && min.get(min.size()-1)==elm)
            min.remove(min.size()-1);
        
    }

    //stk.peek
    public int top() {
        if(!stk.isEmpty())
            return stk.get(stk.size()-1);
        return 0;
    }

    //min.peek
    public int getMin() {
        if(!min.isEmpty())
            return min.get(min.size()-1);
            
        return 0;
    }
    */
}
