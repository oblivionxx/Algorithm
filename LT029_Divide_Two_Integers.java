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
    
    public static int divide2(int dividend, int divisor) {
        if(divisor==0 || (dividend==Integer.MIN_VALUE && divisor==-1)) return Integer.MAX_VALUE;        //overflow
        int sign = ((dividend < 0) ^ (divisor < 0)) ? -1 : 1;
        long dvd = Math.abs((long)dividend), dvs = Math.abs(divisor);        
        int res = 0;
        while(dvd >=dvs){
            long tmp = dvs, multiple = 1;
            while(dvd>=(tmp<<1)){
                tmp<<=1;
                multiple<<=1;
            }                       //find the multiple s.t tmp*multiple <= dvd. multiple = 2,4,8...
            dvd-=tmp;               //then find tmp*multiple'<=dvd'. multiple'=2,4,8...      
            res+=multiple;          //res = like 8+4+2...
        }
        return res*sign;
    }
    
    public static void main(String[] args){
    	System.out.println(divide2(-Integer.MIN_VALUE,1));
    }
}
