/*
 Implement int sqrt(int x).
 Compute and return the square root of x.
 */
/*
 * Math, Binary Search
 */
public class LT069_Sqrt_x {
	 //1. Newton method.
	public int mySqrt1(int x) {
		if (x == 0) return 0;
	    double lastY = 0;
	    double y = 1;
	    while (y != lastY)
	    {
	        lastY = y;
	        y = (y + x / y) / 2;
	    }
	    return (int)(y);
    }
	
	public double mySqrt2(int x) {
		if (x == 0) return 0;
		double epsilon = 1e-15;    // relative error tolerance
        double y = x;              // estimate of the square root of c
	   
        while (Math.abs(y - x/y) > epsilon*y) {
            y = (x/y + y) / 2.0;
        }
        
        return y;
    }
	
	//2. Binary Search. O(lgn), O(1)
	public int mySqrt3(int x) {
        //low*low <= n < high*high
        //二分过程中终止条件的确认。因为整数的乘法有可能导致溢出，而这种溢出的检测跟整数加法直接判断是否小于0是不同的，
		//因为整数的乘法有可能引起多次溢出，当奇数次溢出时是负数，但是偶数次溢出时就又变回正数了，比如2147395599, 如果用是否小于0来终止二分的话，它的平方根会返回617921965，而不是46339。
        //最好的办法就是在二分之前，要求end小于sqrt(INT_MAX)
        if(x<0) return -1;
        if(x==0) return 0;
        int low = 1;
        //对于一个非负数n，它的平方根不会小于大于（n/2+1). so search from 1 to n/2+1.
        int high = x/2+1; 
       
        while(high>=low){
            //can use long to store mid. but not good
            int mid =(high+low)/2;
            if(x==mid*mid)
                return mid;
            if(x/mid<mid)
                high = mid-1;
            else
                low = mid+1;
        }
        
        return (high+low)/2;
        
    }
}
