/*
 You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.
 For example, given:
 s: "barfoothefoobarman"
 words: ["foo", "bar"]
 
 You should return the indices: [0,9].
 (order does not matter).
 */
/*
 * HashMap, Two Pointer, String
 */
import java.util.*;

public class LT030_Substring_With_Concatenation_Of_All_Words {
    public List<Integer> findSubstring(String s, String[] words) {
        //wordlist--> hashmap1<word, freq>
    	//window  --> hashmap2<word, freq>
    	//check if two map matches.
    	List<Integer> res = new ArrayList<>();
    	if(s==null||s.length()==0) return res;
    	
    	HashMap<String, Integer> dict = new HashMap<>();
    	for(String w:words){
    		if(!dict.containsKey(w))
    			dict.put(w, 1);
    		else
    			dict.put(w,dict.get(w)+1);
    	}
    	
    	int single = words[0].length();	//word pattern length 
    	
    	for(int i=0;i<single;i++){		//jump by word. if search from 0~end-window. TLE.
    		HashMap<String, Integer> curMap = new HashMap<>();
    		int count = 0;		//should match words.length. 
    		int left = i;
    		for(int j=i;j<s.length()-single;j+=single){	
    			//j is current cursor to select word
    			String str = s.substring(j,j+single);
    			if(!dict.containsKey(str)){
    				curMap.clear();
    				count = 0;
    				left = j+single;	//jump to next word
    			}else{
    				if(curMap.containsKey(str))
    					curMap.put(str, curMap.get(str)+1);
    				else
    					curMap.put(str,1);
    				
    				if(curMap.get(str)<=dict.get(str))
    						count++;
    				else{
    					//not correct. need to move left border to delete on str from window
    					while(curMap.get(str)>dict.get(str)){
    						String tmp = s.substring(left, left+single);
    						if(curMap.containsKey(tmp)){
    							curMap.put(tmp, curMap.get(tmp)-1);	//delete word from curMap
    							if(curMap.get(tmp)<dict.get(tmp))
    								count--;
    						}
    						left+=single;		//jump to next word.
    					}
    					
    					if(count==words.length){
    						res.add(left);	//find all word in the window
    						//right move one word. and repeat
    						String tmp = s.substring(left, left+single);
    						if(curMap.containsKey(tmp))
    							curMap.put(tmp, curMap.get(tmp)-1);	//delete word from curMap
    						count--;
    						left+=single;		//jump to left word.
    					}
    				}
    			}
    		}
    	}
    	
    	return res;
    }
    
    
    public List<Integer> findSubstringSol2(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        if (s == null || words.length == 0) return ans;
        int n = words.length, wordLen = words[0].length();
        Map<String, Integer> hist = new HashMap<>();
        for (String w : words) {
            hist.put(w, hist.containsKey(w)?0:1);
        }
        Map<String, Integer> curHist = new HashMap<>();
        for (int i = 0; i <= s.length() - n*wordLen; i++) {
            if (hist.containsKey(s.substring(i, i+wordLen))) {
                curHist.clear();
                for (int j = 0; j < n; j++) {
                    String word = s.substring(i + j*wordLen, i+(j+1)*wordLen);
                    if (hist.containsKey(word)) {
                        curHist.put(word, curHist.containsKey(word)?0:1);
                        if (curHist.get(word) > hist.get(word))
                            break;
                    } else 
                        break;
                }
                if (hist.equals(curHist)) ans.add(i);
            }
        }
        return ans;
    }
    
}
