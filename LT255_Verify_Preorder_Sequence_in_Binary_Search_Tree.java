/*
Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.

You may assume each number in the sequence is unique.

Follow up:
Could you do it using only constant space complexity?

Tree, Stack
 */
import java.util.*;
public class LT255_Verify_Preorder_Sequence_in_Binary_Search_Tree {
	public boolean verifyPreorder(int[] preorder) {
		recheck https://leetcode.com/discuss/51543/java-o-n-and-o-1-extra-space 
        //先记录根节点，再遍历左子树，然后遍历右子树；所以一个preorder序列有这样一个特点，左子树的序列必定都在右子树的序列之前；并且左子树的序列必定都小于根节点，右子树的序列都大于根节点；
        //如果多于一个元素，以当前节点为根节点；并从当前节点向后遍历，直到大于根节点的节点出现（或者到尾巴），那么根节点之后，该大节点之前的，是左子树；该大节点及之后的组成右子树；递归判断左右子树即可；
        //那么什么时候一个序列肯定不是一个preorder序列呢？前面得到的右子树，如果在其中出现了比根节点还小的数，那么就可以直接返回false了；
        //Time complexity O(n^2), space complexity O(1). 
        Stack<Integer> s = new Stack<Integer>();
        List<Integer> list = new ArrayList<Integer>();
        for(int i=0;i<preorder.length;i++){
            if(!list.isEmpty() && preorder[i] < list.get(list.size()-1)){
                return false;
            }
            while(!s.isEmpty() && s.peek()<preorder[i]){    
                list.add(s.pop());        //meet right
            }
            s.push(preorder[i]);
        }
        
        return true;
    }
}
