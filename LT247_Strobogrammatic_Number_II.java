/*
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
Find all strobogrammatic numbers that are of length = n.
For example,
Given n = 2, return ["11","69","88","96"].

Hint:
Try to use recursion and notice that it should recurse with n - 2 instead of n - 1.

Math, Recursion
 */
import java.util.*;
public class LT247_Strobogrammatic_Number_II {
	public List<String> findStrobogrammatic(int n) {
        List<String> odd = Arrays.asList("0", "1", "8");
        List<String> even = Arrays.asList("");
        List<String> r = even;  //set default
        if(n%2==1)  r=odd;
            
        for(int i=(n%2+2);i<=n;i+=2){
            List<String> newList = new ArrayList<>();
            for(String str : r){
                if(i!=n)
                    newList.add(0+str+0);
                newList.add(1+str+1);
                newList.add(8+str+8);
                newList.add(6+str+9);
                newList.add(9+str+6);
                
            }
            r = newList;    //update list
        }
        
        return r;
    }
	
	//recursion
	recheck
    
}
