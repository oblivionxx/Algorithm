import java.util.*;

/*
Given a string, find the length of the longest substring T that contains at most k distinct characters.
For example, Given s = “eceba” and k = 2,
T is "ece" which its length is 3.

HashTable, String
 */
public class LT340_Longest_Substring_with_At_Most_K_Distinct_Characters {
    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
	int[] count = new int[256];
	int num = 0, i = 0, res = 0;
	for (int j = 0; j < s.length(); j++) {
	    if (count[s.charAt(j)]++ == 0)
		num++;
	    if (num > k) {
		while (--count[s.charAt(i++)] > 0) ; // count[i]-- until one letter ->0
//		--count[s.charAt(i)];
//		while (count[s.charAt(i)] > 0)
//		    i++;
//		num--;
	    }
	    res = Math.max(res, j - i + 1);
	}
	return res;
    }

    public static void main(String[] args) {
	String str = "eceba";
	System.out.println(lengthOfLongestSubstringKDistinct(str, 2));
    }

    public static int lengthOfLongestSubstringKDistinct2(String s, int k) {
	Map<Character, Integer> map = new HashMap<>();
	int left = 0;
	int best = 0;
	for (int i = 0; i < s.length(); i++) {
	    char c = s.charAt(i);
	    map.put(c, map.getOrDefault(c, 0) + 1);
	    while (map.size() > k) {
		char leftChar = s.charAt(left);
		map.put(leftChar, map.get(leftChar) - 1);
		if (map.get(leftChar) == 0) {
		    map.remove(leftChar);
		}
		left++;
	    }
	    best = Math.max(best, i - left + 1);
	}
	return best;
    }
}
