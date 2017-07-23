/*
 Reverse digits of an integer.
  Example1: x = 123, return 321
  Example2: x = -123, return -321
 */

/*
 * Math
 * Consider 1. last digit=0  2. overflow after reverse. then return 0. 3. negative value
 */
public class LT007_Reverse_Integer {
    public int reverse(int x) {
    	int res=0;
    	while(x!=0){	//x>0 cannot work for negative input.
    		if(Math.abs(res)>Integer.MAX_VALUE/10)
    			return 0;
    		res = res*10+x%10;
    		x = x/10;
    	}
    	return res;
    }
    
    public int reverseSol2(int x) {
    	//using long
    	long res=0;
    	while(x!=0){	//x>0 cannot work for negative input.
    		res = res*10+x%10;
    		if(res>Integer.MAX_VALUE || res<Integer.MIN_VALUE)
    			return 0;
    		x = x/10;
    	}
    	return (int)res;
    }
    
}
