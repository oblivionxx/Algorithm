/*
 Write a function to find the longest common prefix string amongst an array of strings.
 */

/*
 * String 
 */
public class LT014_Longest_Common_Prefix {
    public String longestCommonPrefix(String[] strs) {
	if (strs.length == 0 || strs == null)
	    return "";
	String res = strs[0];
	for (int i = 1; i < strs.length; i++) {
	    while (!strs[i].startsWith(res)) {
		// can change to while(strs[i].indexOf(res)!= 0)
		res = res.substring(0, res.length() - 1);
	    }
	}
	return res;
    }
}
