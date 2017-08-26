
/*
Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
For example,
Given [3,2,1,5,6,4] and k = 2, return 5.
Note: 
You may assume k is always valid, 1 ≤ k ≤ array's length.

Divide and Conquer(Quick Select), Heap, Sort
 */
import java.util.*;

public class LT215_Kth_Largest_Element_In_an_Array {
    // 1. sort O(nlgn)
    public int findKthLargest(int[] nums, int k) {
	Arrays.sort(nums);
	return nums[nums.length - k];
    }

    // 2. Heap O(nlgk) insert cost O(lgk). get peek operation O(1)
    public int findKthLargest2(int[] nums, int k) {
	PriorityQueue<Integer> queue = new PriorityQueue<Integer>(k, Collections.reverseOrder());
	for (int i : nums) {
	    queue.offer(i);
	}

	int res = 0;
	for (int i = 0; i < k; i++) {
	    res = queue.poll();
	}

	return res;
    }

    // 3. Quick Select. best and avg O(n). worst-case performance is quadratic:
    // O(n^2)
    public int findKthLargest3(int[] nums, int k) {
        if (nums == null || nums.length == 0) return Integer.MAX_VALUE;
        return findKthLargest(nums, 0, nums.length - 1, nums.length - k);		//quick select put smaller to left. so find nums.len-kth smallest number
    }    

    public int findKthLargest(int[] nums, int start, int end, int k) {
        if (start > end) return Integer.MAX_VALUE;
        int left = partition(nums, start, end);				//find left from start to end.
        if (left == k)// Found kth smallest number
            return nums[left];
        else if (left < k)// Check right part
            return findKthLargest(nums, left + 1, end, k);		//restrict range
        else // Check left part
            return findKthLargest(nums, start, left - 1, k);
    } 
    
    public int partition(int[] nums, int start, int end){
        int pivot = nums[end];// Take A[end] as the pivot, 
        int left = start;
        for (int i = start; i < end; i++) {
            if (nums[i] <= pivot) // Put numbers < pivot to pivot's left
                swap(nums, left++, i);			
        }
        swap(nums, left, end);// Finally, swap A[end] with A[left]
        return left;
    }

    public void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;				
    }
      
    //built in partition. easier to understand
    public int findKthLargest4(int[] nums, int k) {
        if (nums == null || nums.length == 0) return Integer.MAX_VALUE;
        return findKthLargest(nums, 0, nums.length - 1, nums.length - k);
    }    

    public int findKthLargest4(int[] nums, int start, int end, int k) {
        if (start > end) return Integer.MAX_VALUE;

        int pivot = nums[end];// Take A[end] as the pivot, 		
        int left = start;
        for (int i = start; i < end; i++) {
            if (nums[i] <= pivot) // Put numbers < pivot to pivot's left
                swap(nums, left++, i);			
        }
        swap(nums, left, end);// Finally, swap A[end] with A[left]

        if (left == k)// Found kth smallest number						//find left from start to end
            return nums[left];
        else if (left < k)// Check right part
            return findKthLargest(nums, left + 1, end, k);
        else // Check left part
            return findKthLargest(nums, start, left - 1, k);
    } 

}
