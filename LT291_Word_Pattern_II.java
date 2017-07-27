import java.util.HashMap;

/*
Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.

Examples:
pattern = "abab", str = "redblueredblue" should return true.
pattern = "aaaa", str = "asdasdasdasd" should return true.
pattern = "aabb", str = "xyzabcxzyabc" should return false.
Notes:
You may assume both pattern and str contains only lowercase letters.

Backtracking
 */
public class LT291_Word_Pattern_II {
	check 
	public class Solution {
	    HashMap<Character, String> map1;
	    HashMap<String, Character> map2;
	    boolean res;
	    
	    public boolean wordPatternMatch(String pattern, String str) {
	        map1 = new HashMap<Character, String>();
	        map2 = new HashMap<String, Character>();
	        res = false;
	        // 递归回溯

	        helper(pattern, str, 0, 0);
	        return res;
	    }
	    
	    public void helper(String pattern, String str, int pPos, int sPos){
	        if(pPos==pattern.length() && sPos==str.length()){
	            res = true;
	            return;     //finish. has solution
	        }
	        
	        if(pPos>=pattern.length() || sPos>=str.length()) return;    //end. backtracking. 
	        
	        char c = pattern.charAt(pPos);
	        // 尝试从当前位置到结尾的所有划分方式
	        for(int cut = sPos + 1; cut <= str.length(); cut++){
	            // 拆出一个子串
	            String substr = str.substring(sPos, cut);
	            // 如果这个子串没有被映射过，而且当前pattern的字符也没有产生过映射
	            // 则新建一对映射，并且继续递归求解
	            if(!map2.containsKey(substr) && !map1.containsKey(c)){
	                map1.put(c, substr);
	                map2.put(substr,c);
	                helper(pattern, str, pPos + 1, cut);
	                map1.remove(c);
	                map2.remove(substr);
	            // 如果已经有映射了，但是是匹配的，也继续求解
	            } else if(map1.containsKey(c) && map1.get(c).equals(substr)){
	                helper(pattern, str, pPos + 1, cut);
	            }
	            // 否则跳过该子串，尝试下一种拆分
	        }
	        
	    }
	}
}
