/*
 * Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.

Example:
Given a = 1 and b = 2, return 3.

Bit Manipulation
 */
public class LT371_Sum_Of_Two_Integers {
    public int getSum(int a, int b) {
        //XOR get sum，<<1位来算进位，然后把两者加起来即可
        //不考虑进位的加，0+0=0， 0+1=1, 1+0=1， 1+1=0==>XOR
        //如果只考虑进位的加0+0=0, 0+1=0, 1+0=0, 1+1=1==>AND
        //759+674. 如果我们不考虑进位，可以得到323. 如果我们只考虑进位，可以得到1110  我们把上面两个数字假期323+1110=1433就是最终结果了
        while(b!=0){
            int carry = (a&b)<<1;
            a = a^b;
            b = carry;
        }
        
        return a;
    }
}
