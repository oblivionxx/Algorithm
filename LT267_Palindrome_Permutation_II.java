/*
Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.
For example:
Given s = "aabb", return ["abba", "baab"].
Given s = "abc", return [].

Hint:

If a palindromic permutation exists, we just need to generate the first half of the string.
To generate all distinct permutations of a (half of) string, use a similar approach from: Permutations II or Next Permutation.

Backtracking
 */
import java.util.*;
public class LT267_Palindrome_Permutation_II {
    public List<String> generatePalindromes(String s) {
        List<String> res = new ArrayList<String>();
        if (s == null || s.length() == 0) 
            return res;
            
        //step 1
        HashMap<Character, Integer> map = new HashMap<>();
        if(!canPermutePalindrome(s, map))
            return res;
            
        //step 2. form the left half
        StringBuffer sb = new StringBuffer();
        char middle = '\0';
         
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            char key = (char) pair.getKey();
            int val = (int) pair.getValue();
            while (val > 1) {
                sb.append(key);
                val -= 2;
            }
             
            if (val == 1) {
                middle = key;
            }
        }
        
        
        // Step 3: gnerate the permutations of the string
        permutation(sb.toString().toCharArray(), 0, res);
        List<String> result2 = new ArrayList<>();
         
        // Step 4: append the right half of the string
        for (String str : res) {
            StringBuffer tmp = new StringBuffer(str);
            if (middle != '\0') {
                tmp.append(middle);
            }
             
            for (int i = str.length() - 1; i >= 0; i--) {
                tmp.append(str.charAt(i));
            }
            result2.add(tmp.toString());
        }
         
        return result2;
    
    }
    
    
    public boolean canPermutePalindrome(String s,HashMap<Character, Integer> map ) {
        char[] arr = s.toCharArray();
        
        for(char c:arr){
            if(!map.containsKey(c))
                map.put(c,1);
            else
                map.put(c, map.get(c)+1);
        }
        
        int odd = 0;
        for(int i:map.values())
        {
            if(i%2==1)
                odd++;
        }
        
        if(odd>1) return false;
        return true;
    }
    
    
    private void permutation(char[] s, int start, List<String> result) {
        if (start >= s.length) {
            result.add(new String(s));
            return;
        }
         
        for (int i = start; i < s.length; i++) {
            if (!containsDuplicate(s, start, i)) {
                swap(s, i, start);
                permutation(s, start + 1, result);
                swap(s, i, start);
            }
        }
    }
     
    private void swap(char[] s, int i, int j) {
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }
     
    private boolean containsDuplicate(char[] s, int start, int end) {
        for (int i = start; i < end; i++) {
            if (s[i] == s[end]) {
                return true;
            }
        }
         
        return false;
    }
}
