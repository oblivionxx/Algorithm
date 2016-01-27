import java.util.Arrays;
import java.util.Comparator;

/*
Given a list of non negative integers, arrange them such that they form the largest number.
For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
Note: The result may be very large, so you need to return a string instead of an integer.

Sort
 */
public class LT179_Largest_Number {
	public String largestNumber(int[] nums) {
        String[] str  = new String[nums.length];
        //change to string[].
        for(int i=0;i<nums.length;i++){
            str[i] = String.valueOf(nums[i]);
        }
        
        //sort string[].
        Comparator<String> cmp = new Comparator<String>(){
            public int compare(String s1, String s2){
                String s1s2 = s1+s2;
                String s2s1 = s2+s1;
                return s2s1.compareTo(s1s2);
            }
        };
        
        Arrays.sort(str, cmp);
        StringBuilder stb = new StringBuilder();
        
        //append to get the largest number.
        for(int i=0;i<str.length;i++){
            stb.append(str[i]);    
        }
        
        if(stb.charAt(0)=='0') return "0";
        else
            return stb.toString();
        
    }
}
