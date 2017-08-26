/*
This is a follow up of Shortest Word Distance. The only difference is now you are given the list of words and your method will be called repeatedly many times with different parameters. How would you optimize it?

Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = “coding”, word2 = “practice”, return 3.
Given word1 = "makes", word2 = "coding", return 1.

Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.

HashTable, Design
 */
import java.util.*;
public class LT244_Shortest_Word_Distance_II {
	//make the hashmap in the constructor
	public HashMap<String, List<Integer>> map = new HashMap<String, List<Integer>>();
    
    public LT244_Shortest_Word_Distance_II(String[] words) {
        for(int i=0;i<words.length;i++){
           
            if(map.containsKey(words[i]))
                map.get(words[i]).add(i);
            else{
                List<Integer> list = new ArrayList<Integer>();
                list.add(i);
                map.put(words[i],list);
            }
        }
    }
    
    public int shortest(String word1, String word2) {
        //The task here is to find the minimum difference between two sorted lists. 
        //O(m + n). 
        int diff = Integer.MAX_VALUE;
        int i=0,j=0;
        List<Integer> index1 = map.get(word1);
        List<Integer> index2 = map.get(word2);
        while (i < index1.size() && j < index2.size()) {  
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

//Your WordDistance object will be instantiated and called as such:
//WordDistance wordDistance = new WordDistance(words);
//wordDistance.shortest("word1", "word2");
//wordDistance.shortest("anotherWord1", "anotherWord2");
