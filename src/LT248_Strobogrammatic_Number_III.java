/*
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.
For example,
Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three strobogrammatic numbers.

Note:
Because the range might be a large number, the low and high numbers are represented as string.

Math, Recursion
 */
import java.util.*;
public class LT248_Strobogrammatic_Number_III {
	public int strobogrammaticInRange(String low, String high) {
		//get digits
        int ll = low.length();
        int hl = high.length();
        int result = 0;
        
        if(ll > hl || (ll == hl&&low.compareTo(high) > 0)) {
            return 0;
        }
        
        List<String> list = findStrobogrammatic(ll);
        if(ll==hl){ //easy case
            for (String s : list) {
                if (s.compareTo(low) >= 0 && s.compareTo(high) <= 0) {
                        result++;
                    }
            }
        }else{
            //need to generate...
            //store those already generated and >low
            for (int i = list.size() - 1; i >= 0; i--) {
                String s = list.get(i);
                if (s.compareTo(low) >= 0) {
                    result++;
                }
            }
            list = findStrobogrammatic(hl); 
            //add those in new list and <high
            for (String s : list) {
                if (s.compareTo(high) <= 0) {
                    result++;
                }
            }
            
            //all the number with genereate length n=[ll+1,hl-1]
            for (int i = ll + 1; i < hl; i++) {
                result += findStrobogrammatic(i).size();        
            }
        }
        
        
        return result;
        
        
    }
    
    
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
                newList.add(6+str+9);
                newList.add(8+str+8);
                newList.add(9+str+6);
                
            }
            
            r = newList;    //update list
        }
        
        return r;
    }
}
