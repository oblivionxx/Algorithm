
/*
Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Some examples:
  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
  
Stack
 */
import java.util.Stack;

public class LT150_Evaluate_Reverse_Polish_Notation {
    public int evalRPN(String[] tokens) {
	Stack<Integer> stk = new Stack<Integer>(); // store numbers

	for (String str : tokens) {
	    if (!str.equals("+") && !str.equals("-") && !str.equals("*") && !str.equals("/"))
		stk.push(Integer.parseInt(str));
	    else if (str.equals("+")) {
		int num1 = stk.pop();
		int num2 = stk.pop();
		stk.push(num1 + num2);
	    } else if (str.equals("-")) {
		int num1 = stk.pop();
		int num2 = stk.pop();
		stk.push(num2 - num1);
	    } else if (str.equals("*")) {
		int num1 = stk.pop();
		int num2 = stk.pop();
		stk.push(num1 * num2);
	    } else if (str.equals("/")) {
		int num1 = stk.pop();
		int num2 = stk.pop();
		stk.push(num2 / num1); // push the result back to stack again.
	    }
	    // pay attention to the order in - and /. Remember compare string using equals. not =
	}

	return stk.pop();
    }
}
