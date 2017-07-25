/*
Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
For example,
Given [3,2,1,5,6,4] and k = 2, return 5.
Note: 
You may assume k is always valid, 1 ≤ k ≤ array's length.

Divide and Conquer(Quick Select), Heap, Sort
 */
import java.util.*;
public class LT215_Kth_Largest_Element_In_an_Array {
	//1. sort O(nlgn)
	public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length-k];
    }
	
	//2. Heap O(nlgk)   insert cost O(lgk). get peek operation O(1)
	public int findKthLargest2(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(k, Collections.reverseOrder());
        for(int i:nums){
            queue.offer(i);
        }
        
        int res = 0;
        for(int i=0;i<k;i++){
            res = queue.poll();
        }
        
        return res;
    }
	
	//3. Quick Select. best and avg O(n). worst-case performance is quadratic: O(n^2)
    public int findKthLargest3(int[] nums, int k) {
        return findK(nums,nums.length-k,0,nums.length-1);
    }

    public int findK(int[] nums,int k,int i,int j){
        if(i>=j) return nums[i];
        int m=partition(nums,i,j);
        if(m==k) return nums[m];
        if(m<k)
            return findK(nums,k,m+1,j);
        else
            return findK(nums,k,i,m-1);
    }

    //快速排序中的partition方法
    public int partition(int[] nums,int i,int j){
        int x=nums[i];
        int m=i;
        int n=i+1;
        while(n<=j){
            if(nums[n]<x){
                m++;
                int tmp=nums[m];
                nums[m]=nums[n];
                nums[n]=tmp;
            }
            n++;
        }

        //将x与nums[m]进行互换
        int tmp=nums[m];
        nums[m]=nums[i];
        nums[i]=tmp;

        return m;

    }
}
