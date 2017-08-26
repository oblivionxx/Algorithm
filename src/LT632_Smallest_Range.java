import java.util.*;

/*
 * You have k lists of sorted integers in ascending order. Find the smallest range that includes at least one number from each of the k lists.

We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c if b-a == d-c.

Example 1:
Input:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
Output: [20,24]
Explanation: 
List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
List 2: [0, 9, 12, 20], 20 is in range [20,24].
List 3: [5, 18, 22, 30], 22 is in range [20,24].
Note:
The given list may contain duplicates, so ascending order means >= here.
1 <= k <= 3500
-105 <= value of elements <= 105.
For Java users, please note that the input type has been changed to List<List<Integer>>. And after you reset the code template, you'll see this point.

Hash Table, String, Two Pointers
 */
public class LT632_Smallest_Range {
    // get kth element from each list. can form a range that contains at least one elm from each list. track the min and max
    // loop k. update the range. eg. [0,2,3] [4,5] -> init pq add [0,4]. max=4 1st round add 2,5 to pq. will update the list to [2,4].
    // have a priority queue to store the first&smallest elm. pop up in sequence. and add the next elm into priority queue.
    public int[] smallestRange(List<List<Integer>> nums) {
	PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);
	int max = Integer.MIN_VALUE;
	for (int i = 0; i < nums.size(); i++) {
	    max = Math.max(max, nums.get(i).get(0)); // eg. 1st element of list 3 could be bigger than 3rd element of list 1. track max.
	    pq.add(new int[] { nums.get(i).get(0), i, 0 }); // cur element from list i, i, index of elm
	}
	int[] result = { pq.peek()[0], max }; // result = [smallest of first elm of each list, max]
	while (result[1] - result[0] != 0 && pq.peek()[2] + 1 < nums.get(pq.peek()[1]).size()) { // index of element within list size
	    int[] curr = pq.poll();
	    pq.add(new int[] { nums.get(curr[1]).get(curr[2] + 1), curr[1], curr[2] + 1 }); // put next element into priority queue
	    max = Math.max(max, nums.get(curr[1]).get(curr[2] + 1));
	    if (max - pq.peek()[0] < result[1] - result[0])
		result = new int[] { pq.peek()[0], max }; // update the minRange = max-min
	}
	return result;
    }
}
