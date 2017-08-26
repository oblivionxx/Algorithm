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
	// bit manipulation + map. seperate nums by 0 and 1 to two groups from MSB to LSB
	int max = 0, mask = 0;
	for (int i = 31; i >= 0; i--) {
	    mask = mask | (1 << i); // mask = 1000, 1100 , 1110, 1111..
	    Set<Integer> set = new HashSet<>();
	    for (int num : nums) {
		set.add(num & mask); // reserve Left pre-fix bits and ignore Right bits
	    }
	    int tmp = max | (1 << i); // different how many digest
	    // if a ^ prefix =candidate then a ^ candidate = prefix, prefix ^ candidate = a (we use this one)
	    // prefix is already in the set, check if "a" exist in the set
	    for (int prefix : set) {
		if (set.contains(tmp ^ prefix)) {
		    max = tmp;
		    break;
		}
	    }
	}
	return max;
    }

    /*
     * example: Given [14, 11, 7, 2], which in binary are [1110, 1011, 0111, 0010]. Since the MSB is 3, I'll start from i = 3 to make it simplify.
     * 
     * i = 3, set = {1000, 0000}, max = 1000 i = 2, set = {1100, 1000, 0100, 0000}, max = 1100 i = 1, set = {1110, 1010, 0110, 0010}, max = 1100 i = 0, set = {1110, 1011, 0111, 0010}, max = 1100
     */
}
