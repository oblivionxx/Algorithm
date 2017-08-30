
/*
Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Examples:
"()())()" -> ["()()()", "(())()"]
"(a)())()" -> ["(a)()()", "(a())()"]
")(" -> [""]

DFS, BFS
 */
import java.util.*;

public class LT301_Remove_Invalid_Parentheses {
    // https://discuss.leetcode.com/topic/34875/easy-short-concise-and-fast-java-dfs-3-ms-solution/2
    public List<String> removeInvalidParentheses3(String s) {
	List<String> ans = new ArrayList<>();
	remove(s, ans, 0, 0, new char[] { '(', ')' });
	return ans;
    }

    public void remove(String s, List<String> ans, int last_i, int last_j, char[] par) {
	for (int stack = 0, i = last_i; i < s.length(); ++i) {
	    if (s.charAt(i) == par[0])
		stack++;
	    if (s.charAt(i) == par[1])
		stack--;
	    if (stack >= 0)
		continue;
	    for (int j = last_j; j <= i; ++j)
		if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1]))
		    remove(s.substring(0, j) + s.substring(j + 1, s.length()), ans, i, j, par);
	    return;
	}
	String reversed = new StringBuilder(s).reverse().toString();
	if (par[0] == '(') // finished left to right
	    remove(reversed, ans, 0, 0, new char[] { ')', '(' });
	else // finished right to left
	    ans.add(reversed);
    }
    
    public List<String> removeInvalidParentheses(String s) {
	// BFS.
	List<String> res = new ArrayList<String>();
	if (s == null || s.length() == 0) {
	    res.add("");
	    return res;
	}
	// remove preceeding ) and trailling (

	int i = 0;
	while (i < s.length() && s.charAt(i) != '(')
	    i++;
	s = s.substring(0, i).replace(")", "") + ((i == s.length()) ? "" : s.substring(i));

	int j = s.length() - 1;
	while (j >= 0 && s.charAt(j) != ')')
	    j--;
	s = s.substring(0, j + 1) + ((j == s.length() - 1) ? "" : s.substring(j + 1).replace("(", ""));

	// check if valid parentheses between
	Queue<String> q = new LinkedList<>();
	Set<String> visited = new HashSet<>();
	q.offer(s);
	visited.add(s);
	boolean found = false;
	while (!q.isEmpty()) {
	    s = q.poll();
	    if (isValid(s)) {
		res.add(s);
		found = true;
	    }
	    if (found)
		continue;

	    // 不合法的话，我们队其进行遍历，对于遇到的左右括号的字符，我们去掉括号字符生成一个新的字符串，如果这个字符串之前没有遇到过，将其排入队中，我们用哈希表记录一个字符串是否出现过。我们对队列中的每个元素都进行相同的操作，直到队列为空还没找到合法的字符串的话，那就返回空集

	    for (int k = 0; k < s.length(); k++) { // do this for every s in the
						   // queue.
		// jump non-parenthese character
		if (s.charAt(k) != '(' && s.charAt(k) != ')')
		    continue;
		// Avoid dup.continuous (((+ or )))+ are guaranteed to be
		// duplicate
		if (k > 0 && s.charAt(k) == s.charAt(k - 1))
		    continue;
		// so charAt(k) is either ( or ). try remove one. then
		String t = s.substring(0, k) + s.substring(k + 1);
		if (!visited.contains(t)) {
		    q.offer(t);
		    visited.add(t);
		}
	    }
	}
	return res;

    }

    private boolean isValid(String s) {
	int count = 0;
	for (char c : s.toCharArray()) {
	    if (c == '(')
		count++;
	    else if (c == ')') {
		count--;
		if (count < 0)
		    return false;
	    }
	}
	return count == 0;
    }

    // DFS
    /*
     * Limit max removal rmL and rmR for backtracking boundary. Otherwise it will exhaust all possible valid substrings, not shortest ones. Scan from left to right, avoiding invalid strs (on the fly)
     * by checking num of open parens. If it's '(', either use it, or remove it. If it's '(', either use it, or remove it. Otherwise just append it. Lastly set StringBuilder to the last decision
     * point. In each step, make sure:
     * 
     * i does not exceed s.length(). Max removal rmL rmR and num of open parens are non negative. De-duplicate by adding to a HashSet. Compared to 106 ms BFS (Queue & Set), it's faster and easier.
     * Hope it helps! Thanks.
     */
    public List<String> removeInvalidParentheses2(String s) {
	Set<String> res = new HashSet<>();
	int rmL = 0, rmR = 0;
	for (int i = 0; i < s.length(); i++) {
	    if (s.charAt(i) == '(')
		rmL++;
	    if (s.charAt(i) == ')') {
		if (rmL != 0)
		    rmL--;
		else
		    rmR++;
	    }
	}
	DFS(res, s, 0, rmL, rmR, 0, new StringBuilder());
	return new ArrayList<String>(res);
    }

    public void DFS(Set<String> res, String s, int i, int rmL, int rmR, int open, StringBuilder sb) {
	if (i == s.length() && rmL == 0 && rmR == 0 && open == 0) {
	    res.add(sb.toString());
	    return;
	}
	if (i == s.length() || rmL < 0 || rmR < 0 || open < 0)
	    return;

	char c = s.charAt(i);
	int len = sb.length();

	if (c == '(') {
	    DFS(res, s, i + 1, rmL - 1, rmR, open, sb);
	    DFS(res, s, i + 1, rmL, rmR, open + 1, sb.append(c));

	} else if (c == ')') {
	    DFS(res, s, i + 1, rmL, rmR - 1, open, sb);
	    DFS(res, s, i + 1, rmL, rmR, open - 1, sb.append(c));

	} else {
	    DFS(res, s, i + 1, rmL, rmR, open, sb.append(c));
	}

	sb.setLength(len);
    }


}
