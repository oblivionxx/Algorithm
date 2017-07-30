/*
 * Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

Examples:

s = "leetcode"
return 0.

s = "loveleetcode",
return 2.

 */
public class LT387_First_Unique_Character_in_a_String {
    public int firstUniqChar(String s) {
        // calculate freq and then find the 1st non-repeating character. scan twice
        // or use two pointer?
        // only letter then int[26] otherwise int[256] for all ASCII
        int[] freq = new int[26];
        for(int i=0;i<s.length();i++){
            freq[s.charAt(i)-'a']++;
        }
        
        for(int i=0;i<s.length();i++){
            if(freq[s.charAt(i)-'a']==1) return i;
        }
        
        return -1;
    }
}
