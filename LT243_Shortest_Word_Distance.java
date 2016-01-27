/*
Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = “coding”, word2 = “practice”, return 3.
Given word1 = "makes", word2 = "coding", return 1.

Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.

Array
 */

import java.util.*;
public class LT243_Shortest_Word_Distance {
	//O(n)
	public int shortestDistance(String[] words, String word1, String word2) {
        int res = words.length;
        int index1 = -1;
        int index2 = -1; 
        for(int i=0;i<words.length;i++){
            if(words[i].equals(word1))
                index1 = i;
            else if(words[i].equals(word2))
                index2 = i;
            if(index1!=-1 && index2!=-1){
                res = Math.min(res, Math.abs(index1-index2));
            }
        }
        
        return res;
    }
	
	public int shortestDistance2(String[] words, String word1, String word2) {
        Map<String, List<Integer>> map = new HashMap<>();
        
        for (int i = 0; i < words.length; i++) {
            String s = words[i];
            List<Integer> list;
            if (map.containsKey(s)) {
                list = map.get(s);
            } else {
                list = new ArrayList<>();
            }
            list.add(i);
            map.put(s, list);
        }
        List<Integer> index1 = map.get(word1);
        List<Integer> index2 = map.get(word2);

        int diff = Integer.MAX_VALUE;
        int i=0,j=0;
        //O(m+n)
        while (i < index1.size() && j < index2.size()) {  		//here loop all the possibilities
            int pos1 = index1.get(i), pos2 = index2.get(j);  
            if (pos1 < pos2) {  
                diff = Math.min(diff, pos2 - pos1);  
                i++;  
            } else {  
                diff = Math.min(diff, pos1 - pos2);  
                j++;  
            }  
        }  

        return diff;
    
    }
}
