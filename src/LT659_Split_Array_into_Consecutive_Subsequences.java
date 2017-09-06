import java.util.*;

/*
 * You are given an integer array sorted in ascending order (may contain duplicates), you need to split them into several subsequences, where each subsequences consist of at least 3 consecutive integers. Return whether you can make such a split.

Example 1:
Input: [1,2,3,3,4,5]
Output: True
Explanation:
You can split them into two consecutive subsequences : 
1, 2, 3
3, 4, 5
Example 2:
Input: [1,2,3,3,4,4,5,5]
Output: True
Explanation:
You can split them into two consecutive subsequences : 
1, 2, 3, 4, 5
3, 4, 5
Example 3:
Input: [1,2,3,4,4,5]
Output: False
Note:
The length of the input is in range of [1, 10000]

Heap, Greedy
 */
public class LT659_Split_Array_into_Consecutive_Subsequences {
    public boolean isPossible(int[] nums) {
	Map<Integer, Integer> freq = new HashMap<>(), appendfreq = new HashMap<>();
	for (int i : nums)
	    freq.put(i, freq.getOrDefault(i, 0) + 1);

	for (int i : nums) {
	    if (freq.get(i) == 0)
		continue; // if the ele is already used in some sequence
	    else if (appendfreq.getOrDefault(i, 0) > 0) { // if the ele can be added in the last consecutive sequence
		freq.put(i, freq.get(i) - 1);
		appendfreq.put(i, appendfreq.get(i) - 1);
		appendfreq.put(i + 1, appendfreq.getOrDefault(i + 1, 0) + 1);
	    } else if (freq.getOrDefault(i + 1, 0) > 0 && freq.getOrDefault(i + 2, 0) > 0) {
		// this ele should form a consecutive sequence by itself since it cannot be appended to a previous sequence
		freq.put(i, freq.get(i) - 1);
		freq.put(i + 1, freq.get(i + 1) - 1);
		freq.put(i + 2, freq.get(i + 2) - 1);
		appendfreq.put(i + 3, appendfreq.getOrDefault(i + 3, 0) + 1);
	    } else
		return false; // doesn't belong to any consecutive sequence. EG 12334. 2nd 3 will go to this line

	}
	return true;
    }
}
