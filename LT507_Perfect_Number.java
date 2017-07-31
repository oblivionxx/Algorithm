/*
 * We define the Perfect Number is a positive integer that is equal to the sum of all its positive divisors except itself.

Now, given an integer n, write a function that returns true when it is a perfect number and false when it is not.
Example:
Input: 28
Output: True
Explanation: 28 = 1 + 2 + 4 + 7 + 14
Note: The input number n will not exceed 100,000,000. (1e8)

Math
 */
public class LT507_Perfect_Number {
    public boolean checkPerfectNumber(int num) {
        if(num==1) return false;
        int sum = 1;
        for(int i=2;i<=Math.sqrt(num);i++){
            if(num%i==0) sum+=(i+num/i);
            // i and num/i is a divisor
            // 对于n如果是平方数的话，那么我们此时相同的因子加来两次，所以我们要减掉一次
            if(i*i==num) sum-=i;
            if(sum>num) return false;
        }
        
        return sum==num;
    }
}
