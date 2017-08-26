/*
 Implement atoi to convert a string to an integer.
 Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.
 Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.
 */

/*
 * Math, String
 * Consider: 1. null or empty case 2. sign 3.space. 4.get only digits in the string, neglect others 5.overflow!
 * Further consideraton: 'e' might be valid.
 */
public class LT008_String_to_Integer_atoi {
    public int myAtoi(String str) {
	double res = 0; // in case of overflow
	int sign = 1;

	// 1.null or empty string
	if (str == null || str.length() == 0)
	    return 0;

	// 2.trim starting and enging space
	str = str.trim();

	int idx = 0;
	// 3. get sign
	if (str.charAt(0) == '-') {
	    sign = -1;
	    idx++;
	} else if (str.charAt(0) == '+') {
	    idx++;
	}

	while (idx < str.length() && Character.isDigit(str.charAt(idx))) {
	    res = res * 10 + (str.charAt(idx) - '0');
	    idx++;
	}

	res = res * sign;
	// 4. overflow
	if (res > Integer.MAX_VALUE)
	    return Integer.MAX_VALUE;
	if (res < Integer.MIN_VALUE)
	    return Integer.MIN_VALUE;

	return (int) res;
    }
}
