/*
Follow up for H-Index: What if the citations array is sorted in ascending order? Could you optimize your algorithm?
Hint:
Expected runtime complexity is in O(log n) and the input is sorted.

Binary Search
 */
public class LT275_H_Index_II {
	public int hIndex(int[] citations) {
        if(citations == null || citations.length == 0)
            return 0;
        int len = citations.length;
        
        int low = 0;
        int high = len-1;
        
        while(low<=high){
            int mid = (low+high)/2;
            if (citations[mid] == len-mid)  
                return citations[mid]; 
            else if(len-mid>citations[mid])
                low = mid+1;
            else
                high = mid-1;
                
        }
        
        return len-high-1;
    }
}
