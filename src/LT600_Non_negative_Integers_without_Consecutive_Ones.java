/*
 * Given a positive integer n, find the number of non-negative integers less than or equal to n, whose binary representations do NOT contain consecutive ones.

Example 1:
Input: 5
Output: 5
Explanation: 
Here are the non-negative integers <= 5 with their corresponding binary representations:
0 : 0
1 : 1
2 : 10
3 : 11
4 : 100
5 : 101
Among them, only integer 3 disobeys the rule (two consecutive ones) and the other 5 satisfy the rule. 
Note: 1 <= n <= 109

DP
 */
public class LT600_Non_negative_Integers_without_Consecutive_Ones {
    //http://www.geeksforgeeks.org/count-number-binary-strings-without-consecutive-1s/
    //Let a[i] be the number of binary strings of length i which do not contain any two consecutive 1’s and which end in 0.      
    //let b[i] be the number of such strings which end in 1. 
    // a[i] = a[i - 1] + b[i - 1]       we can append 1 on a[i-1] which end with 0, or append 0 on b[i-1] which end with 1
    // b[i] = a[i - 1]                  we can only append 0 on a[i-1] to form new b[i]
    // return a[n]+b[n] exclude numbers > num
    public int findIntegers(int num) {
        StringBuilder sb = new StringBuilder(Integer.toBinaryString(num)).reverse();
        int n = sb.length();
        
        int a[] = new int[n];
        int b[] = new int[n];
        a[0] = b[0] = 1;
        for (int i = 1; i < n; i++) {
            a[i] = a[i - 1] + b[i - 1];
            b[i] = a[i - 1];
        }
        
        //https://discuss.leetcode.com/post/194125
        //only keep number <=num
        int result = a[n - 1] + b[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (sb.charAt(i) == '1' && sb.charAt(i + 1) == '1') break;				 //can include all values from dp. break
            if (sb.charAt(i) == '0' && sb.charAt(i + 1) == '0') result -= b[i];			//check the discussion above
        }
        
        return result;
    }
}
