/*
 * Given a positive integer num, write a function which returns True if num is a perfect square else False.

Note: Do not use any built-in library function such as sqrt.

Example 1:

Input: 16
Returns: True
Example 2:

Input: 14
Returns: False

Binary Search, Math
 */
public class LT367_Valid_Perfect_Square {
    public boolean isPerfectSquare(int num) {
        //1. math. 1+3+5...n-2+n = perfect square.
        int i = 1;
        while (num > 0) {
            num -= i;
            i += 2;
        }
        return num == 0;
    }
    
    public boolean isPerfectSquare2(int num) {
        //2. sqrt newton way
        long x = num;
        while (x * x > num) {
            x = (x + num / x) / 2;
        }
        
        return x*x ==num;
    }
    
    public boolean isPerfectSquare3(int num) {
        //3. Binary
        long left=1, right=num;
        while(left<=right){
            long mid = left+(right-left)/2;
            long t = mid*mid;
            if(t==num) return true;
            else if(t>num) right=mid-1;
            else left = mid+1;
        }
        
        return false;
    }
}
