/*
 * A sequence of number is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.

For example, these are arithmetic sequence:

1, 3, 5, 7, 9
7, 7, 7, 7
3, -1, -5, -9
The following sequence is not arithmetic.

1, 1, 2, 5, 7

A zero-indexed array A consisting of N numbers is given. A slice of that array is any pair of integers (P, Q) such that 0 <= P < Q < N.

A slice (P, Q) of array A is called arithmetic if the sequence:
A[P], A[p + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, this means that P + 1 < Q.

The function should return the number of arithmetic slices in the array A.


Example:

A = [1, 2, 3, 4]

return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.

DP, Math
 */
public class LT413_Arithmetic_Slices {
    //dp[i] default =0
    //i) We need minimum 3 indices to make arithmetic progression,
    //ii) So start at index 2, see if we got two diffs same, so we get a current 1 arith sequence
    //iii) At any index i, if we see it forms arith seq with former two, that means running (dp[i]) sequence gets extended upto this index, at the same time we get one more sequence (the three numbers ending at i), so dp[i]=dp[i-1]+1. Any time this happens, add the curr value to total sum.
    //iv) Any time we find ith index does not form arith seq, dp[i]=0
    
    public int numberOfArithmeticSlices(int[] A) {
        if(A==null || A.length<3) return 0;
        int[] dp = new int[A.length];
        int res = 0;
        for(int i=2;i<dp.length;i++){
            if(A[i]-A[i-1]==A[i-1]-A[i-2])
                dp[i] = dp[i-1]+1;
            res+=dp[i];
        }
        
        return res;
    }
}
