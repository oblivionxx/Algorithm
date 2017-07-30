/*
 * You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.

Given n, find the total number of full staircase rows that can be formed.

n is a non-negative integer and fits within the range of a 32-bit signed integer.

Example 1:

n = 5

The coins can form the following rows:
¤
¤ ¤
¤ ¤

Because the 3rd row is incomplete, we return 2.
Example 2:

n = 8

The coins can form the following rows:
¤
¤ ¤
¤ ¤ ¤
¤ ¤

Because the 4th row is incomplete, we return 3.

Binary Search, Math
 */
public class LT441_Arranging_Coins {
    public int arrangeCoins(int n) {
        //1. n minus each line
        if(n==0) return 0;
        int iterate = 1;
        while(n>=iterate){
           n-=iterate;
           iterate++;
        }
        
        return iterate-1;
    }
    
    public int arrangeCoins2(int n) {
        //2. math n = (1 + x) * x / 2, 我们用一元二次方程的求根公式可以得到 x = (-1 + sqrt(8 * n + 1)) / 2, 然后取整后就是能填满的行数
        return (int)((-1 + Math.sqrt(1 + 8 * (long)n)) / 2);
    }
    
    public int arrangeCoins3(int n) {
        //3. binary search        (x * ( x + 1)) / 2 <= n
        int start = 0, end = n;
        int mid = 0;
        while (start <= end){
            mid = (start + end) >>> 1;
            if ((0.5 * mid * mid + 0.5 * mid ) <= n){
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        return start - 1;
    }
}
