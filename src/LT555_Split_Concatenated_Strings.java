/*
Given a list of strings, you could concatenate these strings together into a loop, where for each string you could choose to reverse it or not. Among all the possible loops, you need to find the lexicographically biggest string after cutting the loop, which will make the looped string into a regular one.

Specifically, to find the lexicographically biggest string, you need to experience two phases:

Concatenate all the strings into a loop, where you can reverse some strings or not and connect them in the same order as given.
Cut and make one breakpoint in any place of the loop, which will make the looped string into a regular one starting from the character at the cutpoint.
And your job is to find the lexicographically biggest one among all the possible regular strings.

Example:
Input: "abc", "xyz"
Output: "zyxcba"
Explanation: You can get the looped string "-abcxyz-", "-abczyx-", "-cbaxyz-", "-cbazyx-", 
where '-' represents the looped status. 
The answer string came from the fourth looped one, 
where you could cut from the middle character 'a' and get "zyxcba".
Note:
The input strings will only contain lowercase letters.
The total length of all the strings will not over 1,000.

String
 */
public class LT555_Split_Concatenated_Strings {
    public String splitLoopedString(String[] strs) {
	// 1. use strs[i] or reserve(strs[i]) <- lexicographically biggest string
	// 2. find strs[i] with maxCharacter. ["lc", "love", "ydc"]，-> "ylclovecd" 只有cut所在的字符串的翻转可能不按规律
	char max = 'a';
	for (int i = 0; i < strs.length; i++) {
	    for (char c : strs[i].toCharArray()) {
		if (c > max)
		    max = c; // find the maxCharacter. will be the start
	    }
	    String rev = new StringBuilder(strs[i]).reverse().toString();
	    if (strs[i].compareTo(rev) < 0)
		strs[i] = rev; // update biggest string
	}

	String res = "";
	for (int i = 0; i < strs.length; i++) {
	    if (!strs[i].contains("" + max))
		continue;
	    String rev = new StringBuilder(strs[i]).reverse().toString(); // find str[i] contains maxCharacter
	    for (String st : new String[] { strs[i], rev }) { // try two directions. ydc or cdy
		for (int k = 0; k < st.length(); k++) { // ydc or cdy
		    if (st.charAt(k) != max)
			continue;
		    StringBuilder t = new StringBuilder(st.substring(k));
		    for (int j = i + 1; j < strs.length; j++)
			t.append(strs[j]); // add strs[j] j>i (order of strs is same as given)
		    for (int j = 0; j < i; j++)
			t.append(strs[j]); // add strs[j] j<i
		    t.append(st.substring(0, k));
		    if (t.toString().compareTo(res) > 0)
			res = t.toString();
		}
	    }
	}
	return res;
    }
}
