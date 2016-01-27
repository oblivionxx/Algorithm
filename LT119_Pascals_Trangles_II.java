/*
Given an index k, return the kth row of the Pascal's triangle.
For example, given k = 3,
Return [1,3,3,1].

Note:
Could you optimize your algorithm to use only O(k) extra space?

Array
 */
import java.util.*;
public class LT119_Pascals_Trangles_II {
	public List<Integer> getRow(int rowIndex) {
		List<Integer> pre = new ArrayList<Integer>();
        pre.add(1);					//[1]--> row 0.  [1,1]-->row 1
        if(rowIndex<=0)
            return pre;
        
        for(int i=1; i<=rowIndex;i++){
            List<Integer> cur = new ArrayList<Integer>();
            cur.add(1);
            
            for(int j=0;j<pre.size()-1;j++){   //remember here is size-1
                cur.add(pre.get(j)+pre.get(j+1));
            }
            
            cur.add(1);
            pre = cur;
            if(i==rowIndex) return cur;
        }
        return pre;
    }
}
