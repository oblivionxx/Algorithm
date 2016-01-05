/*
 Divide two integers without using multiplication, division and mod operator.
 If it is overflow, return MAX_INT.
 */
/*
 * Math, Binary Search
 */
public class LT029_Divide_Two_Integers {
    public int divide(int dividend, int divisor) {
    	//long type division in binary.
    	//num=a_0*2^0+a_1*2^1+a_2*2^2+...+a_n*2^n, time complexity is O(logn).
        if(divisor==0) return Integer.MAX_VALUE;
        if(divisor==-1 && dividend==Integer.MIN_VALUE) return Integer.MAX_VALUE;
        
        long pDividend = Math.abs((long)dividend);
        long pDivisor = Math.abs((long)divisor);
        
        int result = 0;
        while(pDividend>=pDivisor){
        	int numShift = 0;
        	while(pDividend>=(pDivisor<<numShift))
        		numShift++;
        	
        	result+=1<<(numShift-1);  //so result is 2^numshift.
        	pDividend -=pDivisor<<(numShift-1);	//subtract from dividend. num - a_n*2^n
        }
        
        if(dividend>0 && divisor >0)
        	return result;
        else
        	return -result;
    }
}
