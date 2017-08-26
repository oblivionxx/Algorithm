
/*
 Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 For example, given n = 3, a solution set is:
 "((()))", "(()())", "(())()", "()(())", "()()()"
 */
/*
 * Backtracking, String
 */
import java.util.*;

public class LT022_Generate_Parentheses {
    public List<String> generateParenthesis(int n) {
	List<String> res = new ArrayList<>();
	if (n <= 0)
	    return res;
	helper(res, new String(), n, n);

	return res;
    }

    public void helper(List<String> res, String s, int l, int r) {
	if (l > r)
	    return;
	if (l == 0 && r == 0) {
	    res.add(s);
	    return;
	}

	if (l > 0) // point.
	    helper(res, s + '(', l - 1, r);
	if (r > 0) // no else
	    helper(res, s + ')', l, r - 1);

    }
}
