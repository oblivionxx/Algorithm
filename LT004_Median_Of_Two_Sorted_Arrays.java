/*
 * Here are two sorted arrays nums1 and nums2 of size m and n respectively. 
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

 */

/*
 * Divide and Conquer, Binary Search, Array
 */
public class LT004_Median_Of_Two_Sorted_Arrays {
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		//1. Divide and Conquer. Recursion. O(lg(m+n))
		/* (aMid < bMid) Search [aRight + b], else Search [bRight + a]
		if A[k/2-1]==B[k/2-1]，then find。
		if A[k/2-1]<B[k/2-1], throw A[0]-A[k/2-1]
		if A[k/2-1]>B[k/2-1]，throw B[0]-B[k/2-1]
		*/
		
	    int m = nums1.length, n=nums2.length;
		int total = m + n;
		if (total%2==1)
			return findKth(nums1, 0, m-1, nums2, 0, n-1, total / 2 + 1);
		else
			return (findKth(nums1, 0, m-1, nums2, 0, n-1, total / 2)
					+ findKth(nums1, 0, m-1, nums2, 0, n-1, total / 2 + 1)) / 2;
	}
	
	public double findKth(int a[], int al, int ar, int b[], int bl, int br, int k){
    	//always assume that m is equal or smaller than n
		//remaining length
		int m=ar-al+1;
		int n=br-bl+1;
    	if (m > n)
    		return findKth(b, bl, br, a, al, ar, k);
    	if (m == 0)
    		return b[k - 1];
    	if (k == 1)
    		return Math.min(a[al], b[bl]);
    	//divide k into two parts
    	int pa = Math.min(k / 2, m), pb = k - pa;
    	if (a[al+pa - 1] < b[bl+pb - 1])
    		return findKth(a, al+pa, ar, b, bl, br, k - pa); //throw away left part of a, #pa number.
    	else if (a[al+pa - 1] > b[bl+pb - 1])
    		return findKth(a, al, ar, b, bl+pb, br, k - pb); //throw away left part of b, #pb number.
    	else
    		return a[al+pa - 1];
    }
	
	public double findMedianSortedArraysSol2(int[] nums1, int[] nums2) {
		/*2. Binary Search. O(lg(m+n))
		            LeftPart           |            RightPart 
		{ A[0], A[1], ... , A[i - 1] } | { A[i], A[i + 1], ... , A[m - 1] }
		{ B[0], B[1], ... , B[j - 1] } | { B[j], B[j + 1], ... , B[n - 1] }
		Ensure:
		 1) LeftPart's length == RightPart's length (or RightPart's length + 1)
		 2) All elements in RightPart are greater than elements in LeftPart.(find cut position i, j)
		then we split all elements in {A, B} into two parts with equal length, and one part is
		always greater than the other part. Then the median can be easily found.
		
		search i from 0~m to satisfy:
		 (1) i + j == m - i + n - j (or: m - i + n - j + 1)
	     if n >= m, we just need to set: 
	           i = 0 ~ m, j = (m + n + 1) / 2 - i
		 (2) B[j - 1] <= A[i] and A[i - 1] <= B[j]
		     considering edge values, we need to ensure:
		           (j == 0 or i == m or B[j - 1] <= A[i]) and (i == 0 or j == n or A[i - 1] <= B[j])
			
		if B[j - 1] > A[i], then cut "i" can't be in [0, i]. search in i~m.
		if A[i - 1] > B[j], then cut "i" can't be in [i, m]. search in 0~i-1
		
		Implementation:
		using binary search, find i from [imin, imax].
		
		imin =0, imax=m
		i = (imin + imax) / 2; j = (m + n + 1) / 2 - i
		if B[j - 1] > A[i]: continue searching in [i + 1, imax]
		elif A[i - 1] > B[j]: continue searching in [imin, i - 1]
		else: bingo! this is our object "i"
		
		*/
		int m=nums1.length, n=nums2.length;
		if(m>n) return findMedianSortedArrays(nums2, nums1); //keep m<n and search i in the shorter array
		
		int imin=0, imax=m, i=0,j=0;
		while(imin<=imax){
		    i=(imin+imax)/2;
		    j=(m+n+1)/2-i;
			if(i<m && j>0 && nums2[j-1]>nums1[i]) imin = i+1;
			else if(i>0 && j<n && nums1[i-1]>nums2[j]) imax = i-1;
			else
				break;
		}
		
		double pre =0;
		if(i==0) pre=nums2[j-1];
		else if(j==0) pre=nums1[i-1];
		else 
			pre = Math.max(nums1[i - 1], nums2[j - 1]);
		if((m+n)%2==1) return pre;
		
		double last = 0;
		if(i==m) last = nums2[j];
		else if(j==n) last=nums1[i];
		else
			last = Math.min(nums1[i], nums2[j]);
		
		return (pre+last)/2.0;
		
    }
	
	public double findMedianSortedArraysSol3(int[] nums1, int[] nums2) {
		//3. Merge two list until (m+n)/2. O(m+n)
		//1). even and odd case. 2).nums1.length<len/2. or nums2.length<len/2 
		int i=0,j=0;
		int len = nums1.length+nums2.length;
		boolean odd = false;
		if(len%2!=0) odd = true;
		if(len==0) return 0;
		if(len==1){
			if(nums1.length==1) return nums1[0];
			else
				return nums2[0];
		}
		
		double pre=0, last=0;
		while(i+j<=len/2){
			pre = last;
			if(i>=nums1.length){
				last = nums2[j++];
			}else if(j>=nums2.length){
				last = nums1[i++];
			}else if(nums1[i]>nums2[j]){
				last = nums2[j++];
			}else{
				last = nums1[i++];
			}
		}
		
		if(!odd) return (pre+last)/2;
		
		return last;
	}
	

	
}
