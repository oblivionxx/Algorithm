import java.util.*;

/*
 * Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

Example 1:
Given words = ["bat", "tab", "cat"]
Return [[0, 1], [1, 0]]
The palindromes are ["battab", "tabbat"]
Example 2:
Given words = ["abcd", "dcba", "lls", "s", "sssll"]
Return [[0, 1], [1, 0], [3, 2], [2, 4]]
The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]

Hash Table, Trie, String
 */
public class LT336_Palindrome_Pairs {
    // There are several cases to be considered that isPalindrome(s1 + s2):
    // Case 1: If s1 is a blank string, then for any string that is palindrome s2, s1+s2 and s2+s1 are palindrome.
    // Case 2: If s2 is the reversing string of s1, then s1+s2 and s2+s1 are palindrome.
    // Case 3: If s1[0:cut] is palindrome and there exists s2 is the reversing string of s1[cut+1:] , then s2+s1 is palindrome.
    // Case 4: Similiar to case3. If s1[cut+1: ] is palindrome and there exists s2 is the reversing string of s1[0:cut] , then s1+s2 is palindrome.
    public List<List<Integer>> palindromePairs(String[] words) {
	List<List<Integer>> ret = new ArrayList<>();
	if (words == null || words.length < 2)
	    return ret;
	Map<String, Integer> map = new HashMap<String, Integer>();
	for (int i = 0; i < words.length; i++)
	    map.put(words[i], i); // word, index.
	for (int i = 0; i < words.length; i++) {
	    for (int j = 0; j <= words[i].length(); j++) { // notice it should be "j <= words[i].length()" include empty string
		String str1 = words[i].substring(0, j);
		String str2 = words[i].substring(j);
		if (isPalindrome(str1)) { // str1 could be "" or any palindrome string
		    String str2rvs = new StringBuilder(str2).reverse().toString();
		    if (map.containsKey(str2rvs) && map.get(str2rvs) != i) {
			List<Integer> list = new ArrayList<Integer>();
			list.add(map.get(str2rvs));
			list.add(i);
			ret.add(list);
		    }
		}
		if (isPalindrome(str2)) {
		    String str1rvs = new StringBuilder(str1).reverse().toString();
		    // check "str2.length() != 0" to avoid duplicates. eg [abcd, dcba]
		    if (map.containsKey(str1rvs) && map.get(str1rvs) != i && str2.length() != 0) {
			List<Integer> list = new ArrayList<Integer>();
			list.add(i);
			list.add(map.get(str1rvs));
			ret.add(list);
		    }
		}
	    }
	}
	return ret;
    }

    private boolean isPalindrome(String str) {
	int left = 0;
	int right = str.length() - 1;
	while (left <= right) {
	    if (str.charAt(left++) != str.charAt(right--))
		return false;
	}
	return true;
    }
}
