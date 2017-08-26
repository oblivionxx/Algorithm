import java.util.Arrays;

/*
 * Given an array consists of non-negative integers, your task is to count the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.

Example 1:
Input: [2,2,3,4]
Output: 3
Explanation:
Valid combinations are: 
2,3,4 (using the first 2)
2,3,4 (using the second 2)
2,2,3
Note:
The length of the given array won't exceed 1000.
The integers in the given array are in the range of [0, 1000].

Array
 */
public class LT611_Valid_Triangle_Number {
    public int triangleNumber(int[] nums) {
	//O(n^2 + nlogn)
        //Assume a=nums[i] is the longest edge, 
	//b=nums[left] and c=nums[right] are shorter ones, to form a triangle, they need to satisfy len(b) + len(c) > len(a). Translate to 2sum smaller
        int result = 0;
        if (nums.length < 3) return result;
        
        Arrays.sort(nums);
        for (int i = 2; i < nums.length; i++) {
            int left = 0, right = i - 1;
            while (left < right) {
                if (nums[left] + nums[right] > nums[i]) {
                    result += (right - left);
                    right--;
                }
                else {
                    left++;
                }
            }
        }
        
        return result;
        
    }
}
