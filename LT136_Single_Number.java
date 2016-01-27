/*
Given an array of integers, every element appears twice except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

HashTable, BitManipulation
 */
public class LT136_Single_Number {
	//HashMap too much space.
	//XOR with bitmanipulation
	public int singleNumber(int[] A) {
        int num = 0;
        for(int i=0;i<A.length;i++){
               num = num^A[i];
            }
            
        return num;
	}
}
