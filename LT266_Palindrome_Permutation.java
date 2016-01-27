import java.util.HashMap;

/*
Given a string, determine if a permutation of the string could form a palindrome.
For example,
"code" -> False, "aab" -> True, "carerac" -> True.

Hint:

Consider the palindromes of odd vs even length. What difference do you notice?
Count the frequency of each character.
If each character occurs even number of times, then it must be a palindrome. How about character which occurs odd number of times?

HashTable
 */
public class LT266_Palindrome_Permutation {
	//just count the freq.
	public boolean canPermutePalindrome(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        char[] arr = s.toCharArray();
        
        for(char c:arr){
            if(!map.containsKey(c))
                map.put(c,1);
            else
                map.put(c, map.get(c)+1);
        }
        
        int odd = 0;
        for(int i:map.values()){
            if(i%2==1)
                odd++;
        }
        
        if(odd>1) return false;
        
        return true;
    }
}
