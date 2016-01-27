/*
Given an array of integers, every element appears three times except for one. Find that single one.
Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

BitManipulation
 */
public class LT137_Single_Number_II {
	//1. use bitmap of each number. count every time. O(n) space.
	
	//2. similar to bitmap. but project to 32-int. 
	public int singleNumber(int[] nums) {
        int[] bit = new int[32];
        for(int i=0;i<nums.length;i++){
            int k=1;
            //project each number to bitmap[32]
            for(int j =0; j<32; j++){
            	//check if jth bit is 1. rightmost is 0.
                bit[j] += (nums[i]>>j) & k;
            }
        }
        
        
        int target = 0;
        //only care bit[i]%3!=0
        for(int i=0;i<32;i++){
            target += (bit[i]%3 <<i); 
        }
        
        return target;
    }
	
	//3. XOR recheck
	public int singleNumber3(int[] nums) {
        //the single number only appear once, then return ones, if the number appear twice, then return twos.
        
        //ones记录1出现一次的数，twos记录1出现2次的数
        int ones = 0, twos = 0, xthrees = 0;
        for(int i = 0; i < nums.length; ++i) {
            twos |= (ones & nums[i]);       //appear twice using OR.
            ones ^= nums[i];                //appear once using XOR
            xthrees = ~(ones & twos);  		//当ones和twos中的某一位同时为1时表示二进制1出现3次，此时需要清零
            ones &= xthrees;
            twos &= xthrees;
        }
    
        return ones;
    
    }
}
