/*
Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.

Example 1
Input: "2-1-1".

((2-1)-1) = 0
(2-(1-1)) = 2
Output: [0, 2]


Example 2
Input: "2*3-4*5"

(2*(3-(4*5))) = -34
((2*3)-(4*5)) = -14
((2*(3-4))*5) = -10
(2*((3-4)*5)) = -10
(((2*3)-4)*5) = 10
Output: [-34, -14, -10, -10, 10]

Divide and Conquer
 */
import java.util.*;
public class LT241_Different_Ways_to_Add_Parentheses {
	public List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new ArrayList<Integer>();
        if(input==null || input.length()==0) return res;
        String opt = "+-*";
        for(int i=0;i<input.length();i++){
            //divide input into two parts at position i. if i is a operator
            if(opt.contains(""+input.charAt(i))){
                String left = input.substring(0,i);
                String right = input.substring(i+1);
                List<Integer> leftList = diffWaysToCompute(left);
                List<Integer> rightList = diffWaysToCompute(right);
                for(Integer l : leftList) {
                    for(Integer r : rightList) {
                        if(input.charAt(i) == '+'){
                            res.add(l+r);
                        }else if(input.charAt(i) == '-'){
                            res.add(l-r);
                        }else if(input.charAt(i) == '*'){
                            res.add(l*r);
                        }else{
                            res.add(0);
                        }
                    }
                }
            }
        }
        
        if(res.size()==0)
            res.add(Integer.valueOf(input));
        return res;
    }
	
	//dp
	//https://leetcode.com/discuss/61840/java-recursive-9ms-and-dp-4ms-solution
}
