
/*
 You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.
 For example, given:
 s: "barfoothefoobarman"
 words: ["foo", "bar"]
 
 You should return the indices: [0,9].
 (order does not matter).
 */
/*
 * HashMap, Two Pointer, String
 */
import java.util.*;

public class LT030_Substring_With_Concatenation_Of_All_Words {
    public List<Integer> findSubstringSol(String s, String[] words) {
	// dict -> hashmap<word. freq>
	// window -> hashmap<word, freq> should be the same as wordlist
	List<Integer> res = new ArrayList<>();
	if (s == null || s.length() == 0)
	    return res;

	HashMap<String, Integer> dict = new HashMap<>();
	for (String str : words) {
	    dict.put(str, dict.getOrDefault(str, 0) + 1);
	}

	int n = words.length, single = words[0].length();

	for (int i = 0; i <= s.length() - n * single; i++) { // loop starting
							     // index
	    HashMap<String, Integer> sliding = new HashMap<>(dict);
	    for (int j = 0; j < n; j++) {
		String curWord = s.substring(i + j * single, i + (j + 1) * single); // search
										    // for
										    // each
										    // word
		if (sliding.containsKey(curWord)) { // update in the sliding
						    // HashMap
		    if (sliding.get(curWord) == 1)
			sliding.remove(curWord);
		    else
			sliding.put(curWord, sliding.get(curWord) - 1);
		} else {
		    break;
		}
		if (sliding.size() == 0)
		    res.add(i); // if sliding window contains all word. add
				// index i
	    }
	}
	return res;
    }

}
