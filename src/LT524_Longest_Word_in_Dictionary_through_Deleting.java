import java.util.*;

/*
 * Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting some characters of the given string. If there are more than one possible results, return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.

Example 1:
Input:
s = "abpcplea", d = ["ale","apple","monkey","plea"]

Output: 
"apple"
Example 2:
Input:
s = "abpcplea", d = ["a","b","c"]

Output: 
"a"
Note:
All the strings in the input will only contain lower-case letters.
The size of the dictionary won't exceed 1,000.
The length of all the strings in the input won't exceed 1,000.

Two Pointers, Sort
 */
public class LT524_Longest_Word_in_Dictionary_through_Deleting {
    public String findLongestWord(String s, List<String> d) {
        //sort by length and then lexicographical
        Collections.sort(d, (a,b) -> a.length() != b.length() ? -Integer.compare(a.length(), b.length()) :  a.compareTo(b));
        for (String dictWord : d) {
            if(isSubsequence(s,dictWord)) return dictWord;
        }
        return "";
    }
    
    public boolean isSubsequence(String s, String t){
        int i=0, j=0;
        while(i<s.length()){
            if(s.charAt(i)==t.charAt(j)){
                j++;
                if(j==t.length()) return true;
            }
            i++;
        }
        return false;
    }
}
