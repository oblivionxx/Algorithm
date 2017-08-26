
/*
Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return

  [
    ["aa","b"],
    ["a","a","b"]
  ]
  
  Backtracking
 */
import java.util.*;

public class LT131_Palindrome_Partitioning {
    public List<List<String>> partition(String s) {
	// one partition solution is one List<String>. can have different solutions. then return the List<List<>>
	// loop the index, find the first partition is palindrome
	// recursion the remaining part to find the palindrome.
	List<List<String>> res = new ArrayList<List<String>>();
	if (s == null || s.length() == 0)
	    return res;
	helper(s, new ArrayList<String>(), res);
	return res;

    }

    private void helper(String s, List<String> elm, List<List<String>> res) {
	if (s.length() == 0) {
	    res.add(new ArrayList<String>(elm));
	    return;
	}

	for (int i = 1; i <= s.length(); i++) {
	    String str1 = s.substring(0, i);
	    if (isPalindrome(str1)) {
		elm.add(str1);
		String restSubStr = s.substring(i);
		helper(restSubStr, elm, res);
		elm.remove(elm.size() - 1);
	    }
	}
    }

    private boolean isPalindrome(String str) {
	int i = 0;
	int j = str.length() - 1;
	while (i < j) {
	    if (str.charAt(i) != str.charAt(j)) {
		return false;
	    }
	    i++;
	    j--;
	}
	return true;
    }

}
