
/*
 Given an array of strings, group anagrams together.
 For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], 
 Return:
	
	[
	  ["ate", "eat","tea"],
	  ["nat","tan"],
	  ["bat"]
	]
 Note:
 For the return value, each inner list's elements must follow the lexicographic order.
 All inputs will be in lower-case.
 */
import java.util.*;

public class LT049_Group_Anagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
	List<List<String>> result = new ArrayList<List<String>>();
	if (strs == null || strs.length == 0)
	    return result;

	HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
	for (String s : strs) {
	    char[] ch = s.toCharArray();
	    Arrays.sort(ch);
	    String t = String.valueOf(ch); // sort anagram to string.
	    if (!map.containsKey(t)) {
		ArrayList<String> l = new ArrayList<String>();
		l.add(s);
		map.put(t, l);
	    } else {
		map.get(t).add(s);
	    }
	}

	for (ArrayList<String> l : map.values()) {
	    Collections.sort(l);
	    result.add(l);
	}

	return result;
    }
}
