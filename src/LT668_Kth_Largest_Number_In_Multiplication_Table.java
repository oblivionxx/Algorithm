/*
 * Nearly every one have used the Multiplication Table. But could you find out the k-th largest number quickly from the multiplication table?

Given the height m and the length n of a m * n Multiplication Table, and a positive integer k, you need to return the k-th largest number in this table.

Example 1:
Input: m = 3, n = 3, k = 5
Output: 
Explanation: 
The Multiplication Table:
1	2	3
2	4	6
3	6	9

The 5-th largest number is 3 (1, 2, 2, 3, 3).
Example 2:
Input: m = 2, n = 3, k = 6
Output: 
Explanation: 
The Multiplication Table:
1	2	3
2	4	6

The 6-th largest number is 6 (1, 2, 2, 3, 4, 6).
Note:
The m and n will be in the range [1, 30000].
The k will be in the range [1, m * n]

Binary Search
 */
public class LT668_Kth_Largest_Number_In_Multiplication_Table {
    //name is misleading. just find kth smallest.
    public int findKthNumber(int m, int n, int k) {
    	int low = 1 , high = m * n + 1;
        
    	while (low < high) {
    	    int mid = low + (high - low) / 2;
    	    int c = count(mid, m, n);
    	    if (c >= k) high = mid;                 //mid" is not guaranteed to be an element in the m * n multiplication table. so cannot return mid when c=k
            else low = mid + 1;
    	}
        
    	return high;
    }
    
    private int count(int v, int m, int n) {
	    int count = 0;
    	for (int i = 1; i <= m; i++) {
            int temp = Math.min(v / i , n);         //count num <= target(v)
            count += temp;
	    }
	    return count;
    }
}
