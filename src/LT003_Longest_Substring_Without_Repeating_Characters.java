/*
 Given a string, find the length of the longest substring without repeating characters. 
 For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3. For "bbbbb" the longest substring is "b", with the length of 1.
 */

/*
 * HashTable, TwoPointer, String 
 */
import java.util.*;

public class LT003_Longest_Substring_Without_Repeating_Characters {
    public int lengthOfLongestSubstring(String s) {
	if (s == null || s.length() == 0)
	    return 0;
	int left = 0, right = 0;
	int max = 0;
	HashSet<Character> set = new HashSet<>();
	while (right < s.length()) {
	    // using for loop has some problem with "abac" and "abbc". when
	    // having duplicate, the remove left is not correct.
	    if (!set.contains(s.charAt(right))) {
		set.add(s.charAt(right));
		right++;
		max = Math.max(set.size(), max);
	    } else {
		set.remove(s.charAt(left));
		left++;
	    }
	}

	return max;
    }

    public int lengthOfLongestSubstringSol2(String s) {
	if (s.length() == 0)
	    return 0;
	HashMap<Character, Integer> map = new HashMap<Character, Integer>();
	int max = 0;
	for (int i = 0, j = 0; i < s.length(); ++i) {
	    if (map.containsKey(s.charAt(i))) {
		j = Math.max(j, map.get(s.charAt(i)) + 1);
	    }
	    map.put(s.charAt(i), i);
	    max = Math.max(max, i - j + 1); // update the next start of
					    // substring. quicker
	}

	return max;
    }
}
