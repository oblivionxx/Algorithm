import java.util.*;

/*
 * Given two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].

Note:
Each element in the result should appear as many times as it shows in both arrays.
The result can be in any order.
Follow up:
What if the given array is already sorted? How would you optimize your algorithm?
What if nums1's size is small compared to nums2's size? Which algorithm is better?
What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?

Binary Search, Hash Table, Two Pointers, Sort 
 */
public class LT350_Intersection_of_Two_Arrays_II {
    public int[] intersect(int[] nums1, int[] nums2) {
	// time complexity: max(O(m), O(n)) space complexity: choose one O(m) or O(n)
	HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
	ArrayList<Integer> result = new ArrayList<Integer>();
	for (int i = 0; i < nums1.length; i++) {
	    if (map.containsKey(nums1[i]))
		map.put(nums1[i], map.get(nums1[i]) + 1);
	    else
		map.put(nums1[i], 1);
	}

	for (int i = 0; i < nums2.length; i++) {
	    if (map.containsKey(nums2[i]) && map.get(nums2[i]) > 0) {
		result.add(nums2[i]);
		map.put(nums2[i], map.get(nums2[i]) - 1);
	    }
	}

	int[] r = new int[result.size()];
	for (int i = 0; i < result.size(); i++) {
	    r[i] = result.get(i);
	}

	return r;
    }

    // 1,2 So if two arrays are already sorted, and say m is much smaller than n, we should choose the algorithm that for each element in nums1, do binary search in nums2, so that the complexity is
    // O(mlgn). In this case, if memory is limited and nums2 is stored in disk, partition it and send portions of nums2 piece by piece. keep a pointer for nums1 indicating the current position, and it
    // should be working fine~

    // 3. What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
    // If only nums2 cannot fit in memory, put all elements of nums1 into a HashMap, read chunks of array that fit into the memory, and record the intersections.
    // If both nums1 and nums2 are so huge that neither fit into the memory, sort them individually (external sort), then read 2 elements from each array at a time in memory, record intersections.
}
