import java.util.PriorityQueue;

/*
Write a program to find the n-th ugly number.
Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
Note that 1 is typically treated as an ugly number.

Hint:

The naive approach is to call isUgly for every number until you reach the nth one. Most numbers are not ugly. Try to focus your effort on generating only the ugly ones.
An ugly number must be multiplied by either 2, 3, or 5 from a smaller ugly number.
The key is how to maintain the order of the ugly numbers. Try a similar approach of merging from three sorted lists: L1, L2, and L3.
Assume you have Uk, the kth ugly number. Then Uk+1 must be Min(L1 * 2, L2 * 3, L3 * 5).

DP, Heap, Math
 */
public class LT264_Ugly_Number_II {
	//dp. 3 single variable to store the number of min. 2,3,5 multiple
	public int nthUglyNumber(int n) {
        int[] result=new int[n];
        result[0]=1;
        int a=0;
        int b=0;
        int c=0;
        int factora=2;
        int factorb=3;
        int factorc=5;
        for(int i=1;i<n;i++){
            int min=Math.min(Math.min(factora,factorb),factorc);
            result[i]=min;
            if(factora==min){
                factora=2*result[++a];
            }
            if(factorb==min){
                factorb=3*result[++b];
            }
            if(factorc==min){
                factorc=5*result[++c];
            }
        }
        return result[n-1];
    }
	
	//heap. store factora, factorab...into heap
	public static int nthUglyNumber2(int n) {
	    if(n==1) return 1;
	    PriorityQueue<Long> q = new PriorityQueue<Long>();
	    q.add(1l);

	    for(long i=1; i<n; i++) {
	        long tmp = q.poll();
	        while(!q.isEmpty() && q.peek()==tmp) tmp = q.poll();		//i.e we can get 2*3 and 3*2. so two times need to get rid of one.
	        q.add(tmp*2);
	        q.add(tmp*3);
	        q.add(tmp*5);
	    }
	    return q.poll().intValue();
	}
	
	public static void main(String[] args){
		nthUglyNumber2(15);
	}
}
