/*
 Follow up for "Search in Rotated Sorted Array":
 What if duplicates are allowed?

 Would this affect the run-time complexity? How and why?

 Write a function to determine if a given target is in the array.
 */
/*
 * Binary Search
 */
public class LT081_Search_in_Rotated_Sorted_Array_II {
	// 3 case, mid is target. left is ordered. right is ordered.
	// left is ordered. then search in left. if target is in ordered left. ...
	// right is ordered. then search in right. ...
	public boolean search(int[] A, int target) {
        if(A==null || A.length==0)
            return false;
        int l = 0;
        int r = A.length-1;
        while(l<=r){
            int m = (l+r)/2;
            if(A[m]==target)
                return true;
            
            if(A[m]>A[l]){								//left is ordered.
                if(A[m]>target && A[l]<=target){		//target is in left side. attention: the equal.
                    r = m-1;
                }else{
                    l = m+1;
                }
            }else if(A[m]<A[l]){						//right is ordered
                if(A[m]<target && A[r]>=target){		//target is in right side.
                    l = m+1;
                }else{
                    r = m-1;
                }                
            }else{										//move left bound. mid is among duplicate numbers.
                l++;
            }
        }
        return false;
    }
}
