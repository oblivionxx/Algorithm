import java.util.HashMap;

/*
 Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

 For example,
 S = "ADOBECODEBANC"
 T = "ABC"
 Minimum window is "BANC".

 Note:
 If there is no such window in S that covers all characters in T, return the empty string "".
 If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
 */
/*
 * Two Pointer, HashTable, String
 */
public class LT076_Minimum_Window_Substring {
	public String minWindow(String s, String t) {
        HashMap<Character, Integer> tMap = new HashMap<>();
        for(int i=0;i<t.length();i++){
        	char letter = t.charAt(i);
        	if(!tMap.containsKey(letter))
        		tMap.put(letter, 1);
        	else
        		tMap.put(letter, tMap.get(letter)+1);
        }
        
        //using two pointer to track the letter occurence in s.
        int left = 0;
        int count = 0;	//max equals to t.length
        int minStart = 0, minLength = Integer.MAX_VALUE;
        for(int right=0;right<s.length();right++){
        	char l = s.charAt(right);
        	if(tMap.containsKey(l)){
        		tMap.put(l, tMap.get(l)-1);
        		if(tMap.get(l)>=0)
        			count++;
        		while(count==t.length()){
        			//already contains all the letter in t. consider move left.
        			
        			//update optimal
        			if(right-left+1<minLength)  {  
                        minLength = right-left+1;  
                        minStart = left;                      
                    }
        			//consider move left
        			if(tMap.containsKey(s.charAt(left))){
        				tMap.put(s.charAt(left), tMap.get(s.charAt(left))+1);     //add one back to have one letter available??recheck
                        if(tMap.get(s.charAt(left))>0){  
                            count--;  
                        }  
        			}
        			
        			left++;		//if not tMap not contains left, left also++ here.
        		}
        	}
        }
        
        if(minLength>s.length())
            return "";
        
        return s.substring(minStart, minStart+minLength);
    }
}
