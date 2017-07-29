import java.util.*;

/*
 * Given two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].

Note:
Each element in the result must be unique.
The result can be in any order.

Binary Search, Hash Table, Two Pointers, Sort 
 */
public class LT349_Intersection_of_Two_Arrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        //找两个数组的交集，即把两个数组中都有的元素不重不漏地找出来，所以其实比linked list的交点要简单。
        HashSet<Integer> set = new HashSet<Integer>();
        for(int i=0;i<nums1.length;i++)
            set.add(nums1[i]); //遍历增加
        List<Integer> resultList = new ArrayList<Integer>();
        for (int i=0;i<nums2.length;i++){
            if(set.contains(nums2[i])){
                resultList.add(nums2[i]);
                set.remove(nums2[i]); //记得删除
            }
        }
        //https://discuss.leetcode.com/topic/45685/three-java-solutions
        int result[] = new int[resultList.size()];
        for(int i=0;i<resultList.size();i++)
            result[i]=resultList.get(i);
        return result;
    }
}
