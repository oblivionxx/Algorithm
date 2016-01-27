import java.util.HashSet;

/*
Given an array of integers, find if the array contains any duplicates. Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.

Array, HashTable
 */
public class LT217_Contains_Duplicate {
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<Integer>();
        for(int elm:nums){
            if(set.contains(elm))
                return true;
            else
                set.add(elm);
        }
        
        return false;
    }
}
