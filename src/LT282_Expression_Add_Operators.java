import java.util.ArrayList;
import java.util.List;

/*
Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.

Examples: 
"123", 6 -> ["1+2+3", "1*2*3"] 
"232", 8 -> ["2*3+2", "2+3*2"]
"105", 5 -> ["1*0+5","10-5"]
"00", 0 -> ["0+0", "0-0", "0*0"]
"3456237490", 9191 -> []

Divide and Conquer
 */
public class LT282_Expression_Add_Operators {
    public List<String> addOperators(String num, int target) {
	List<String> res = new ArrayList<String>();
	helper(res, num, target, "", 0, 0);
	return res;
    }

    public void helper(List<String> res, String num, int target, String tmp, long curRes, long preNum) { // change tmp to string is much faster
	if (curRes == target && num.length() == 0) {
	    res.add(new String(tmp));
	    return;
	}

	// split the num string
	for (int i = 1; i <= num.length(); i++) {
	    String curString = num.substring(0, i);
	    if (curString.length() > 1 && curString.charAt(0) == '0')
		return; // the split not correct, return, not continue

	    long curNum = Long.parseLong(curString);
	    String nextString = num.substring(i);

	    if (tmp.length() != 0) { // first time, if tmp ="", can only add number, not operator
		helper(res, nextString, target, tmp + "+" + curNum, curRes + curNum, curNum);
		helper(res, nextString, target, tmp + "-" + curNum, curRes - curNum, -curNum);
		helper(res, nextString, target, tmp + "*" + curNum, curRes - preNum + preNum * curNum, preNum * curNum);
		// 1. 乘号之前是加号或减号，例如2+3*4，要将先减去刚才加的3得到2，然后再加上3乘以4
		// 2. 另外一种情况是乘号之前也是乘号，如果2+3*4*5，我们要把刚才加的3*4给去掉，然后再加上3*4*5. so pre = preNum*curNum
	    } else
		helper(res, nextString, target, curString, curNum, curNum);

	}
    }
}
