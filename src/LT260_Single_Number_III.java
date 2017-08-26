/*
Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.

For example:

Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].

Note:
The order of the result is not important. So in the above example, [5, 3] is also correct.
Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?

Bit Manipulation
 */
public class LT260_Single_Number_III {
	//these two number has at least one digit are different. 
	//based on the last 1-bit in XOR result can group nums into two groups. 
	//one contains A the other contains B. Each group xor, then the result is A and B.
	public int[] singleNumber(int[] nums) {
        int xor = 0;
        for(int i=0;i<nums.length;i++){
            xor ^=nums[i];
        }
        
        int[] res = new int[2];
        int a = 0, b=0;
        //令lowbit = xor & -xor，lowbit的含义为xor从低位向高位，第一个非0位所对应的数字
        int lowbit = xor & -xor;
        for(int elm:nums){
            if((elm & lowbit)==lowbit)
                a ^= elm;
            else
                b ^= elm;
        }
        
        res[0] = a;
        res[1] = b;
        
        return res;
        
    }
}	
