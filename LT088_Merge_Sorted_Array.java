/*
 Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
Note:
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2. 
The number of elements initialized in nums1 and nums2 are m and n respectively.
 */
/*
 * Array, Two Pointer.
 */
public class LT088_Merge_Sorted_Array {
	//compare from bwd and add to the end.
	public void merge(int A[], int m, int B[], int n) {
        while(m>0 && n>0) {
            if(A[m-1]>B[n-1]){
                A[m+n-1] = A[m-1];
                m--;
           }else{
        	   A[m+n-1] = B[n-1];
               n--;
           }
        }
        
        while(n>0){
            A[m+n-1] = B[n-1];
            n--;
        }
    }
}
