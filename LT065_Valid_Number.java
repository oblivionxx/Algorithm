/*
 Validate if a given string is numeric.
	Some examples:
	"0" => true
	" 0.1 " => true
	"abc" => false
	"1 a" => false
	"2e10" => true
 */
/*
 * Math, String
 */
public class LT065_Valid_Number {
	// space, number, sign, point, exponential.
	/*
	 * recheck https://leetcode.com/discuss/26682/clear-java-solution-with-ifs
	 * https://leetcode.com/discuss/23447/a-clean-design-solution-by-using-
	 * design-pattern
	 * https://leetcode.com/discuss/16637/my-short-solution-without-dfa-and-re
	 * https://leetcode.com/discuss/47396/ac-java-solution-with-clear-
	 * explanation Regex how to do? what is dfa, ifs
	 */
	public static boolean isNumber(String s) {
		s = s.trim();
		if (s.length() == 0)
			return false;
		int i = 0;
		if (s.charAt(0) == '+' || s.charAt(0) == '-') { // 忽略符号位
			i++;
		}

		s = s.substring(i);
		if (s.length() == 0)
			return false;

		int dot = -1; // 记录点的位置
		int ee = -1; // 记录e的位置
		for (i = 0; i < s.length(); i++) {
			if (dot == -1 && s.charAt(i) == '.') {
				dot = i;
			} else if (ee == -1 && s.charAt(i) == 'e') {
				ee = i;
				if (i + 1 < s.length() && (s.charAt(i + 1) == '-' || s.charAt(i + 1) == '+')) {
					i++;
				}
			} else {
				if (Character.isDigit(s.charAt(i))) {
					continue;
				} else {
					return false;
				}
			}
		}

		// xxx.xxexx
		String startStr, midStr, lastStr;
		if (dot == -1 && ee == -1) { // xxx
			startStr = s; // xxx
			if (startStr.length() < 1) {
				return false;
			}
		} else if (dot != -1 && ee == -1) { // xxx.yyy
			startStr = s.substring(0, dot); // xxx
			midStr = s.substring(dot + 1); // yyy
			if (startStr.length() < 1 && midStr.length() < 1) {
				return false;
			}
		} else if (dot == -1 && ee != -1) { // xxxeyyy
			startStr = s.substring(0, ee); // xxx
			if (startStr.length() < 1) {
				return false;
			}
			if (ee + 1 < s.length() && (s.charAt(ee + 1) == '-' || s.charAt(ee + 1) == '+')) { // xxxe-zz
				lastStr = s.substring(ee + 2); // zz
			} else {
				lastStr = s.substring(ee + 1);
			}
			if (lastStr.length() < 1) {
				return false;
			}
		} else { // xxx.yyezz
			if (dot > ee) { // 位置不对
				return false;
			}
			startStr = s.substring(0, dot); // xxx
			midStr = s.substring(dot + 1, ee); // yy
			if (startStr.length() < 1 && midStr.length() < 1) {
				return false;
			}
			if (ee + 1 < s.length() && (s.charAt(ee + 1) == '-' || s.charAt(ee + 1) == '+')) {
				lastStr = s.substring(ee + 2); // zz
			} else {
				lastStr = s.substring(ee + 1);
			}
			if (lastStr.length() < 1) {
				return false;
			}
		}
		return true;
	}
}
