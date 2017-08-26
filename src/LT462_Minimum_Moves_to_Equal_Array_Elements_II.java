import java.util.Arrays;

/*
 * Given a non-empty integer array, find the minimum number of moves required to make all array elements equal, where a move is incrementing a selected element by 1 or decrementing a selected element by 1.

You may assume the array's length is at most 10,000.

Example:

Input:
[1,2,3]

Output:
2

Explanation:
Only two moves are needed (remember each move increments or decrements one element):

[1,2,3]  =>  [2,2,3]  =>  [2,2,2]

Math
 */
public class LT462_Minimum_Moves_to_Equal_Array_Elements_II {
    public int minMoves2(int[] nums) {
        //find the median. larger --, smaller++
        //1. sort and get the diff to median O(nlgn)
        //Arrays.sort(nums);
        //int median = nums[nums.length >> 1];
        //return Arrays.stream(nums).map(i -> Math.abs(median - i)).sum();
        
        Arrays.sort(nums);
        int i = 0, j = nums.length-1;
        int count = 0;
        while(i < j){
            count += nums[j]-nums[i];
            i++;
            j--;
        }
        return count;
        
        //2. optimize to O(n) used quick select to select the median element
        //https://discuss.leetcode.com/topic/68758/java-o-n-time-using-quickselect/2
    }
}
