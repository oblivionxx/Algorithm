/*
 The count-and-say sequence is the sequence of integers beginning as follows:
 1, 11, 21, 1211, 111221, ...
 1 is read off as "one 1" or 11.
 11 is read off as "two 1s" or 21.
 21 is read off as "one 2, then one 1" or 1211.
 Given an integer n, generate the nth sequence.
 Note: The sequence of integers will be represented as a string.
 */

/*
 * String
 */
public class LT038_Count_And_Say {
    public String countAndSay(int n) {
	if (n <= 0)
	    return null;
	int count = 1;
	String initString = "1";

	for (int i = 1; i < n; i++) {
	    // read the preString and translate
	    StringBuilder sb = new StringBuilder();
	    for (int j = 0; j < initString.length(); j++) {
		// loop the string. update count and form the new string
		if (j < initString.length() - 1 && initString.charAt(j) == initString.charAt(j + 1)) {
		    count++;
		} else {
		    sb.append(count).append(initString.charAt(j));
		    count = 1;
		}
	    }

	    initString = sb.toString();
	}

	return initString;
    }
}
