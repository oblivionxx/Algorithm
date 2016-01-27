import java.util.Arrays;

/*
Given two strings s and t, write a function to determine if t is an anagram of s.

For example,
s = "anagram", t = "nagaram", return true.
s = "rat", t = "car", return false.

Note:
You may assume the string contains only lowercase alphabets.

Follow up:
What if the inputs contain unicode characters? How would you adapt your solution to such case?

Hashtable, sort
 */
public class LT242_Valid_Anagram {
	public boolean isAnagram(String s, String t) {
        if(s.length()!=t.length()) return false;
        
        char[] s1 = s.toCharArray();
        char[] s2 = t.toCharArray();
        
        Arrays.sort(s1);
        Arrays.sort(s2);
        
        String s_new = new String(s1);
        String t_new = new String(s2);
        
        return s_new.equals(t_new);
        
    }
	
	//or use hashmap to count occurrence.
}
