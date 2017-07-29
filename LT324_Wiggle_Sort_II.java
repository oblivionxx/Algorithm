import java.util.Random;

/*
 * Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....

Example:
(1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6]. 
(2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].

Note:
You may assume all input has valid answer.

Follow Up:
Can you do it in O(n) time and/or in-place with O(1) extra space?

Sort
 */
public class LT324_Wiggle_Sort_II {
    public void wiggleSort(int[] nums) {
        //various way: https://discuss.leetcode.com/topic/71990/summary-of-the-various-solutions-to-wiggle-sort-for-your-reference
        int n = nums.length, m = (n + 1) >> 1;
        int median = kthSmallestNumber(nums, m);
    
        for (int i = 0, j = 0, k = n - 1; j <= k;) {
            if (nums[A(j, n)] > median) {
                swap(nums, A(i++, n), A(j++, n));
            } else if (nums[A(j, n)] < median) {
                swap(nums, A(j, n), A(k--, n));
            } else {
                j++;
            }
        }
    }

    private int A(int i, int n) {
        return (2 * i + 1) % (n | 1);
    }
    
    private int kthSmallestNumber(int[] nums, int k) {
        Random random = new Random();
    
        for (int i = nums.length - 1; i >= 0; i--) {
            swap(nums, i, random.nextInt(i + 1));
        }
    
        int l = 0, r = nums.length - 1;
        k--;
        
        while (l < r) {
            int m = getMiddle(nums, l, r);
        
            if (m < k) {
                l = m + 1;
            } else if (m > k) {
                r = m - 1;
            } else {
                break;
            }
        }
    
        return nums[k];
    }
    
    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
    
    private int getMiddle(int[] nums, int l, int r) {
        int i = l;
    
        for (int j = l + 1; j <= r; j++) {
            if (nums[j] < nums[l]) swap(nums, ++i, j);
        }
    
        swap(nums, l, i);
        return i;
    }
}
