import java.util.HashMap;

/*
 * Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.

Example:
Given "bcabc"
Return "abc"

Given "cbacdcbc"
Return "acdb"

Stack, Greedy
 */
public class LT316_Remove_Duplicate_Letters {
    public String removeDuplicateLetters(String s) {
        char[] array = s.toCharArray();
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < array.length; i++) {
            char c = array[i];
            map.put(c, i);                  //save letter, last appeared index
        }
        char[] result = new char[map.size()];           //map.size = identical letters 
        int start = 0;
        for (int i = 0; i < result.length; i++) {
            int end = findMinPos(map);                  //between 0 to end. all the letters appeared. pick the lexicographical smallest letter. 
            char min = 'z' + 1;
            for (int j = start; j <= end; j++) {        //pick the lexicographical smallest letter "min" from start to end 
                if (map.containsKey(array[j]) && array[j] < min) {
                    min = array[j];                     
                    start = j + 1;                      //important. dont start from beginning. start from last letter picked. to end: next smallest letter in the map
                }
            }
            result[i] = min;
            map.remove(min);                            //remove the letter picked. 
        }
        
        return String.valueOf(result);
    }
    
    private int findMinPos(HashMap<Character, Integer> map) {
        int pos = Integer.MAX_VALUE;
        for (Integer end : map.values()) {
            pos = Math.min(pos, end);                   //find smallest index.
        }
        return pos;
    }
}
