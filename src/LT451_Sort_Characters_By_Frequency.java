import java.util.*;

/*
 * Given a string, sort it in decreasing order based on the frequency of characters.

Example 1:

Input:
"tree"

Output:
"eert"

Explanation:
'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
Example 2:

Input:
"cccaaa"

Output:
"cccaaa"

Explanation:
Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
Note that "cacaca" is incorrect, as the same characters must be together.
Example 3:

Input:
"Aabb"

Output:
"bbAa"

Explanation:
"bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.

Hash Map, Heap
 */
public class LT451_Sort_Characters_By_Frequency {
    public String frequencySort(String s) {
        // if A,a same. 
        HashMap<Character, Integer> map = new HashMap<>();    //save char, freq
        for(int i=0;i<s.length();i++){
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i),0)+1);
        }
        
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue()-a.getValue());
        for(Map.Entry<Character, Integer> entry: map.entrySet()){
            pq.add(entry);
        }
        
        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()){
            Map.Entry<Character, Integer> entry = pq.poll();
            int freq = entry.getValue();
            while(freq-->0){
                sb.append(entry.getKey());
            }
        }
        
        return sb.toString();
    }
}
