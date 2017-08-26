
/*
Given a string containing only digits, restore it by returning all possible valid IP address combinations.
For example:
Given "25525511135",
return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)

Backtracking, String
 */
import java.util.*;

public class LT093_Restore_IP_Addresses {
	// do by segment 0~3
	public List<String> restoreIpAddresses(String s) {
		List<String> res = new ArrayList<String>();
		String item = new String(); // if use stringbuilder. not immutable?!
									// when recursion, will have some problem
		if (s.length() < 4 || s.length() > 12)
			return res;
		helper(s, res, item, 0);
		return res;

	}

	private void helper(String s, List<String> res, String item, int segment) {
		// condition: 4 segments
		if (segment == 3 && isValid(s)) {
			res.add(item + s);
			return;
		}

		// loop segment
		for (int i = 0; i < s.length() - 1 && i < 3; i++) { // i is to get
															// 0~255.
			String str1 = s.substring(0, i + 1);
			if (isValid(str1)) {
				String restSubStr = s.substring(i + 1);
				helper(restSubStr, res, item + str1 + '.', segment + 1);
			}
		}
	}

	private boolean isValid(String s) {
		if (s.charAt(0) == '0') // 实现中需要一个判断数字是否为合法ip地址的一项的函数，首先要在0-255之间，其次前面字符不能是0
			// 如果开头是0，判断整个串是不是0，不是的话该字符就是非法的。因为001，01都是不对的。
			return s.equals("0");

		int foo = Integer.parseInt(s);
		if (foo > 0 && foo <= 255)
			return true;
		return false;
	}
}
