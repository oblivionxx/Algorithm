/*
Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:

"abc" -> "bcd" -> ... -> "xyz"
Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.

For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"], 
Return:

[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]
Note: For the return value, each inner list's elements must follow the lexicographic order.

HashMap, String
 */
import java.util.*;
public class LT249_Group_Shifted_Strings {
	public List<List<String>> groupStrings(String[] strings) {
        HashMap<String, List<String>> map = new HashMap<>();
        List<List<String>> res = new ArrayList<List<String>>();
        
        //find the shift pattern and put it in the map. each letter offset compare to the first letter
        for(int i=0;i<strings.length;i++){
            StringBuffer sb = new StringBuffer();  
            for(int j=0;j<strings[i].length();j++){		//check each string in the string array. Use interger.toString(int offset)
                sb.append(Integer.toString(((strings[i].charAt(j) - strings[i].charAt(0)) + 26) % 26));  
                sb.append(" ");  
            }
            
            String shift = sb.toString();  
            if(map.containsKey(shift)) {  
                map.get(shift).add(strings[i]);  
            } else {  
                List<String> l = new ArrayList<>();  
                l.add(strings[i]);  
                map.put(shift, l);  
            }  
        }
        
        for(String s :map.keySet()) {  
            Collections.sort(map.get(s));  
            res.add(map.get(s));  
        }   
        return res;  
    }
}
