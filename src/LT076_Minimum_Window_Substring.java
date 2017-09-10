import java.util.HashMap;

/*
 Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

 For example,
 S = "ADOBECODEBANC"
 T = "ABC"
 Minimum window is "BANC".

 Note:
 If there is no such window in S that covers all characters in T, return the empty string "".
 If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
 */
/*
 * Two Pointer, HashTable, String
 */
public class LT076_Minimum_Window_Substring {
    public String minWindow(String s, String t) {
	if (s == null || s.length() < t.length() || s.length() == 0) {
	    return "";
	}
	HashMap<Character, Integer> map = new HashMap<Character, Integer>();
	for (char c : t.toCharArray()) {
	    if (map.containsKey(c)) {
		map.put(c, map.get(c) + 1);
	    } else {
		map.put(c, 1);
	    }
	}

	int left = 0, minLeft = 0, minLen = s.length() + 1;
	int count = t.length();
	for (int right = 0; right < s.length(); right++) {
	    if (map.containsKey(s.charAt(right))) {
		map.put(s.charAt(right), map.get(s.charAt(right)) - 1);
		if (map.get(s.charAt(right)) >= 0) {
		    count--;
		}

		while (count == 0) { // if so, satisfy the window that
				     // s.substring contains all letters in
				     // t. update left.
		    if (right - left + 1 < minLen) {
			minLeft = left;
			minLen = right - left + 1;
		    }
		    if (map.containsKey(s.charAt(left))) {
			map.put(s.charAt(left), map.get(s.charAt(left)) + 1);
			if (map.get(s.charAt(left)) > 0) {
			    count++;
			}
		    }
		    left++;
		}
	    }
	}

	if (minLen > s.length()) {
	    return "";
	}

	return s.substring(minLeft, minLeft + minLen);
    }
    
    public static String minWindow2(String s, String t) {
        int[] cnt = new int[256];//char 有256个数
        for (char c : t.toCharArray()) cnt[c]++;
        
        int min = Integer.MAX_VALUE, from = 0, total = t.length();
        for (int r = 0, l = 0; r < s.length(); r++) {
            if (cnt[s.charAt(r)] > 0) total--;
            cnt[s.charAt(r)]--;
            //if (cnt[s.charAt(r)]-- > 0) total--;
            while (total == 0) {                    	// total=0 means valid window
                if (r - l + 1 < min) {			//try to update the min length
                    min = r - l + 1;
                    from = l;
                }
                ++cnt[s.charAt(l)];
                if (cnt[s.charAt(l)] > 0) total++;		//aaab, ab
                l++;
                //if (++cnt[s.charAt(l++)] > 0) total++;
            }
        }
        return (min == Integer.MAX_VALUE) ? "" : s.substring(from, from + min);
    }
    
    public static void main(String[] args){
	System.out.println(minWindow2("aaaab","ab"));
    }
}
