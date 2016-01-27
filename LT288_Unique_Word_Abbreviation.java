import java.util.*;

/*
An abbreviation of a word follows the form <first letter><number><last letter>. Below are some examples of word abbreviations:
a) it                      --> it    (no abbreviation)

     1
b) d|o|g                   --> d1g

              1    1  1
     1---5----0----5--8
c) i|nternationalizatio|n  --> i18n

              1
     1---5----0
d) l|ocalizatio|n          --> l10n

Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.
Example: 
Given dictionary = [ "deer", "door", "cake", "card" ]

isUnique("dear") -> false
isUnique("cart") -> true
isUnique("cane") -> false
isUnique("make") -> true

HashTable, Design
 */
public class LT288_Unique_Word_Abbreviation {
	public class ValidWordAbbr {
	    HashMap<String, HashSet<String>> map = new HashMap<>();

	    public ValidWordAbbr(String[] dictionary) {
	        for(String s:dictionary){
	            String abbr = convert(s);
	            if(!map.containsKey(abbr)){
	                HashSet<String> set = new HashSet<>();
	                set.add(s);
	                map.put(abbr, set);
	            }
	            else{
	                HashSet<String> set =map.get(abbr);
	                set.add(s);
	                map.put(abbr,set);
	            }
	        }
	    }

	    public boolean isUnique(String word) {
	        String abbr = convert(word);
	        if(!map.containsKey(abbr))
	            return true;
	        if(map.get(abbr).contains(word) && map.get(abbr).size() == 1)		// means the map.get(abbr).equals(abbr)
	            return true;
	        return false;
	    }
	    
	    private String convert(String s){
	        if(s==null || s.length()==0) return "";
	        if(s.length()<3) return s;
	        return s.charAt(0) + Integer.toString(s.length() - 2)+ s.charAt(s.length()-1);
	    }
	}


	// Your ValidWordAbbr object will be instantiated and called as such:
	// ValidWordAbbr vwa = new ValidWordAbbr(dictionary);
	// vwa.isUnique("Word");
	// vwa.isUnique("anotherWord");
}
