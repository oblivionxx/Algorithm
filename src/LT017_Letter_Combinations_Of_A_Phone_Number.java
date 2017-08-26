/*
 Given a digit string, return all possible letter combinations that the number could represent.
 A mapping of digit to letters (just like on the telephone buttons) is given below.
  2-abc, 3-def, 4-ghi, 5-jkl, 6-mno, 7-pqrs, 8-tuv, 9-wxyz
 
 Input:Digit string "23"
 Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */

/*
 Backtracking, String
 */
import java.util.*;

public class LT017_Letter_Combinations_Of_A_Phone_Number {
    public List<String> letterCombinations(String digits) {
	String[] str = { "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
	List<String> res = new ArrayList<String>();
	if (digits == null || digits.length() == 0)
	    return res;
	helper(res, str, new StringBuilder(), 0, digits);
	return res;
    }

    public void helper(List<String> res, String[] str, StringBuilder sb, int idx, String digits) {
	if (idx == digits.length()) {
	    res.add(sb.toString());
	    return;
	}

	int num = digits.charAt(idx) - '0';
	String pattern = str[num - 2];
	// can merge to pattern=str[digits.charAt(idx)-'2'];

	for (int i = 0; i < pattern.length(); i++) {
	    sb.append(pattern.charAt(i));
	    helper(res, str, sb, idx + 1, digits);
	    sb.deleteCharAt(sb.length() - 1);
	}

    }
}
