
/*
Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.

Return all such possible sentences.

For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].

A solution is ["cats and dog", "cat sand dog"].

DP, Backtracking.
 */
import java.util.*;

public class LT140_Word_Break_II {
	// get all possible answer. O(n!)
	public List<String> wordBreak(String s, List<String> wordDict) {
		List<String> res = new ArrayList<>();
		helper(s, wordDict, res, "");
		return res;
	}

	public void helper(String s, List<String> wordDict, List<String> res, String elm) {
		if (s.length() == 0) {
			res.add(elm.trim());
			return;
		}

		for (String word : wordDict) {
			if (s.startsWith(word)) {
				String rest = s.substring(word.length());
				helper(rest, wordDict, res, elm + word + " ");
			}
		}
	}
}
