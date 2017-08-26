/*
 Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.
 If the last word does not exist, return 0.
 Note: A word is defined as a character sequence consists of non-space characters only.

 For example, 
 Given s = "Hello World",
 return 5.
 */
/*
 * String
 */
public class LT058_Length_Of_Last_Word {
    public int lengthOfLastWord1(String s) {
	if (s == null || s.length() == 0)
	    return 0;
	String[] res = s.split("\\s+");
	return res.length == 0 ? 0 : res[res.length - 1].length();
    }

    public int lengthOfLastWord2(String s) {
	if (s == null || s.length() == 0)
	    return 0;
	s = s.trim(); // be careful case like "a ".
	int count = 0;
	for (int i = s.length() - 1; i >= 0; i--) {
	    if (s.charAt(i) != ' ') {
		count++;
	    } else
		return count;
	}

	return count;
    }
}
