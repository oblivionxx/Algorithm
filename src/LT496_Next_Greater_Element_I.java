import java.util.*;

/*
 * You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. 
 * Find all the next greater numbers for nums1's elements in the corresponding places of nums2.

The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. If it does not exist, output -1 for this number.

Example 1:
Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
Output: [-1,3,-1]
Explanation:
    For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
    For number 1 in the first array, the next greater number for it in the second array is 3.
    For number 2 in the first array, there is no next greater number for it in the second array, so output -1.
Example 2:
Input: nums1 = [2,4], nums2 = [1,2,3,4].
Output: [3,-1]
Explanation:
    For number 2 in the first array, the next greater number for it in the second array is 3.
    For number 4 in the first array, there is no next greater number for it in the second array, so output -1.
Note:
All elements in nums1 and nums2 are unique.
The length of both nums1 and nums2 would not exceed 1000.

Stack
 */
public class LT496_Next_Greater_Element_I {
    public int[] nextGreaterElement(int[] findNums, int[] nums) {
	// using stack O(n+m) to save the decreasing sequence. pop if find num in nums larger than stack
	// using hashmap to store. <stack(i), num> num is the next greater for stack(i)
	HashMap<Integer, Integer> map = new HashMap<>();
	Stack<Integer> stk = new Stack<>();
	for (int num : nums) {
	    while (!stk.isEmpty() && stk.peek() < num) {		//if increasing, save <preNum, nextGreater>
		map.put(stk.pop(), num);
	    }
	    stk.push(num);
	}
	for (int i = 0; i < findNums.length; i++) {
	    findNums[i] = map.getOrDefault(findNums[i], -1); // save space. findNums just used to look up. 
	}
	return findNums;
    }
    
    public int[] nextGreaterElement2(int[] findNums, int[] nums) {
	// loop findNums. find num in nums. then check its right in nums O(m*n)
	int[] res = new int[findNums.length];
	for (int i = 0; i < findNums.length; i++) {
	    int k = 0, j = 0;
	    for (; k < nums.length; k++) {
		if (findNums[i] == nums[k])
		    break;
	    }

	    for (j = k + 1; j < nums.length; j++) {
		if (nums[j] > nums[k]) {
		    res[i] = nums[j];
		    break;
		}
	    }

	    if (j == nums.length)
		res[i] = -1;
	}

	return res;
    }

}
