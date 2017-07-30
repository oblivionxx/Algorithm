/*
 * Given an arbitrary ransom note string and another string containing letters from all the magazines, write a function that will return true if the ransom note can be constructed from the magazines ; otherwise, it will return false.

Each letter in the magazine string can only be used once in your ransom note.

Note:
You may assume that both strings contain only lowercase letters.

canConstruct("a", "b") -> false
canConstruct("aa", "ab") -> false
canConstruct("aa", "aab") -> true

String
 */
public class LT383_Ransom_Note {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] letter = new int[26];
        for(int i=0;i<magazine.length();i++){
            letter[magazine.charAt(i)-'a']++;
        }
        
        for(int i=0;i<ransomNote.length();i++){
            letter[ransomNote.charAt(i)-'a']--;
            if(letter[ransomNote.charAt(i)-'a']<0) return false;
        }
        
        return true;
    }
}
