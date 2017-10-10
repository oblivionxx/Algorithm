import java.util.*;

/*
 * Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231.

Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.

Could you do this in O(n) runtime?

Example:

Input: [3, 10, 5, 25, 2, 8]

Output: 28

Explanation: The maximum result is 5 ^ 25 = 28.

Bit Manipulation, Trie
 */
public class LT421_Maximum_XOR_of_Two_Numbers_in_an_Array {
    public int findMaximumXOR(int[] nums) {
	int max = 0, mask = 0;
	for (int i = 31; i >= 0; i--) {
	    mask = mask | (1 << i); // mask = 1000..., 1100... , 1110..., 1111.. max XOR is 100000000.
	    Set<Integer> set = new HashSet<>();
	    for (int num : nums) {
		set.add(num & mask);
		// add the number which has the mask as its prefix;
		// reserve Left prefix only. 表示在当前这一位上，数组里有这么多prefix possibility
	    }

	    // 假设当前2 number xor所能达到的最大值是这个tmp值
	    // 那么他一定满足a(xor)b = tmp,其中set.contains(a) && set.contains(b). 所以我们只需要判断b(xor)tmp的结果是不是在当前这一位下的set内
	    int tmp = max | (1 << i);

	    // if a ^ prefix =candidate then a ^ candidate = prefix, prefix ^ candidate = a (we use this one)
	    // prefix is already in the set, check if "a" exist in the set
	    for (int prefix : set) {
		if (set.contains(tmp ^ prefix)) { // eg. number with prefix 1000 and number with prefix 0111 can form the largest xor = 1000
		    max = tmp;
		    break;
		}
	    }
	}
	return max;
    }

    /*
     * https://segmentfault.com/a/1190000007283296 example: Given [14, 11, 7, 2], which in binary are [1110, 1011, 0111, 0010]. Since the MSB is 3, I'll start from i = 3 to make it simplify. i = 3,
     * set = {1000, 0000}, max = 1000 i = 2, set = {1100, 1000, 0100, 0000}, max = 1100 i = 1, set = {1110, 1010, 0110, 0010}, max = 1100 i = 0, set = {1110, 1011, 0111, 0010}, max = 1100
     */
    class Trie {
	Trie[] children;

	public Trie() {
	    children = new Trie[2];
	}
    }

    public int findMaximumXOR2(int[] nums) {
	if (nums == null || nums.length == 0) {
	    return 0;
	}
	// Init Trie.
	Trie root = new Trie();
	for (int num : nums) {
	    Trie curNode = root;
	    for (int i = 31; i >= 0; i--) {
		int curBit = (num >>> i) & 1;
		if (curNode.children[curBit] == null) {
		    curNode.children[curBit] = new Trie();
		}
		curNode = curNode.children[curBit];
	    }
	}
	
	int max = Integer.MIN_VALUE;
	for (int num : nums) {
	    Trie curNode = root;
	    int curSum = 0;
	    for (int i = 31; i >= 0; i--) {
		int curBit = (num >>> i) & 1;
		if (curNode.children[curBit ^ 1] != null) {
		    curSum += (1 << i);
		    curNode = curNode.children[curBit ^ 1];		//go to the other possible x that most far away from num(maximum x^num). do not follow the num.
		} else {
		    curNode = curNode.children[curBit];
		}
	    }
	    max = Math.max(curSum, max);
	}
	return max;
    }
}
