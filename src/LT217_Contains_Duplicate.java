import java.util.*;

/*
Given an array of integers, find if the array contains any duplicates. Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.

Array, HashTable
 */
public class LT217_Contains_Duplicate {
	public boolean containsDuplicate(int[] nums) {     
        Set<Integer> mySet = new HashSet<>(); 
        for(int i:nums){
            if(!mySet.add(i)) return true;
        }
        return false;
    }
}
