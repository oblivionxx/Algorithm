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
	 public List<String> findStrobogrammatic2(int n) {
         return helper(n, n);
     }
     public List<String> helper(int n, int m) {
         if (n == 0) return new ArrayList<String>(Arrays.asList(""));
         if (n == 1) return new ArrayList<String>(Arrays.asList("0", "1", "8"));
         List<String> list = helper(n - 2, m);
         List<String> res = new ArrayList<String>();
         for (int i = 0; i < list.size(); i++) {
             String s = list.get(i);
             if (n != m) res.add("0" + s + "0");
             res.add("1" + s + "1");
             res.add("6" + s + "9");
             res.add("8" + s + "8");
             res.add("9" + s + "6");
         }
         return res;
     }
}
