/*
Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its bottom-up level order traversal as:
[
  [15,7],
  [9,20],
  [3]
]
Tree, BFS
 */
import java.util.*;
public class LT107_Binary_Tree_Level_Order_Traversal_II {
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
        //difference to I: when add to res, using res.add(0, item)
        //add before the rest items.
        List<List<Integer>> res = new ArrayList<>();
        if(root==null) return res;
        
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> item = new ArrayList<>();
            for(int i=0;i<size;i++){
                TreeNode cur = queue.poll();
                if(cur.left!=null){
                    queue.add(cur.left);
                }
                if(cur.right!=null){
                    queue.add(cur.right);
                }
                
                item.add(cur.val);
            }
            res.add(0, item);       //only difference
        }
        
        return res;
    }
}
