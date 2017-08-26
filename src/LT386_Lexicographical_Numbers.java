import java.util.*;

/*
 * Given an integer n, return 1 - n in lexicographical order.

For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].

Please optimize your algorithm to use less time and space. The input size may be as large as 5,000,000.

 */
public class LT386_Lexicographical_Numbers {
    public List<Integer> lexicalOrder(int n) {
	List<Integer> list = new ArrayList<>(n);
	int curr = 1;
	for (int i = 1; i <= n; i++) {
	    list.add(curr);
	    if (curr * 10 <= n) { // eg. try 1, then 10, 100...
		curr *= 10;
	    } else if (curr % 10 != 9 && curr + 1 <= n) {
		curr++; // try 11, 12...19
	    } else {
		curr = curr / 10;
		while (curr % 10 == 9) {
		    curr = curr / 10;
		}
		curr++; // 499. next=5
	    }
	}
	return list;
    }
}
