import java.util.*;

/*
 Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 */
/*
 * Stack, String
 */
public class LT020_Valid_Parentheses {
    public boolean isValid(String s) {
	char[] str = s.toCharArray();
	HashMap<Character, Character> map = new HashMap<Character, Character>();
	map.put('[', ']');
	map.put('(', ')');
	map.put('{', '}');

	Stack<Character> stk = new Stack<Character>();
	for (Character c : str) {
	    if (map.keySet().contains(c)) {
		stk.push(c);
	    } else if (map.values().contains(c)) {
		if (!stk.isEmpty() && map.get(stk.peek()) == c)
		    stk.pop();
		else
		    return false;
	    }
	}
	return stk.isEmpty();
    }
}
