
/*
 The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.

For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

00 - 0
01 - 1
11 - 3
10 - 2
Note:
For a given n, a gray code sequence is not uniquely defined.
For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.
For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
 */
/*
 * Backtracking
 */
import java.util.*;

public class LT089_Gray_Code {
	// use recursion to generate n-1th gray code. then add 0 and 1 to the
	// beginning.
	public List<Integer> grayCode(int n) {
		if (n == 0) { // end condition
			List<Integer> res = new ArrayList<Integer>();
			res.add(0);
			return res;
		}
		// 1位格雷码有两个码字
		// (n+1)位格雷码中的前2^n个码字等于n位格雷码的码字，按顺序书写，加前缀0
		List<Integer> res = grayCode(n - 1); // do recursion

		int addOne = 1 << (n - 1);
		int orginSize = res.size();
		// (n+1)位格雷码中的后2^n个码字等于n位格雷码的码字，按逆序书写，加前缀1。
		for (int i = orginSize - 1; i >= 0; i--) {
			res.add(addOne + res.get(i));
		}

		return res;
	}
	
	public List<Integer> grayCode2(int n) {
        //iterative.
        ArrayList<Integer> arr = new ArrayList<Integer>();
        arr.add(0);
        for(int i=0;i<n;i++){
            int inc = 1<<i;
            for(int j=arr.size()-1;j>=0;j--){
                arr.add(arr.get(j)+inc);
            }
        }
        return arr;
    
    }
}
