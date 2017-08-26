
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

    // https://discuss.leetcode.com/topic/34260/java-dp-dfs-memoization-dfs-and-dp-pruning-solutions-with-analysis
    public List<String> wordBreak2(String s, List<String> wordDict) {
	return memDFS(s, wordDict, new HashMap<String, List<String>>());
    }

    private List<String> memDFS(String s, List<String> wordDict, Map<String, List<String>> map) {
	if (map.containsKey(s))
	    return map.get(s);
	List<String> res = new ArrayList<>();

	for (String word : wordDict) {
	    if (s.startsWith(word)) {
		if (word.length() == s.length()) {
		    res.add(word);
		    continue;
		}
		List<String> subList = memDFS(s.substring(word.length()), wordDict, map);
		for (String sub : subList) {
		    res.add(word + " " + sub);
		}
	    }
	}
	map.put(s, res);
	return res;
    }
}
