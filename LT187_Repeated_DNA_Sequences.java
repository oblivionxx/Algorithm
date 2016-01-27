/*
All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
For example,
Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",

Return:
["AAAAACCCCC", "CCCCCAAAAA"].

HashTable, Bit Manipulation
 */
import java.util.*;
public class LT187_Repeated_DNA_Sequences {
	//rolling hash technique or in case of string search also known as Rabin-Karp algorithm.
	//check. https://leetcode.com/discuss/24595/short-java-rolling-hash-solution
    public List<String> findRepeatedDnaSequences(String s) {
        
        List<String> rslt = new ArrayList<String>();
        HashSet<Integer> resultHash = new HashSet<Integer>();
        HashSet<Integer> tmp = new HashSet<Integer>();
        
        int len = s.length();
        if(len<10){  
            return rslt;  
        }  
        
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();    
        map.put('A', 0);    	//00
        map.put('C', 1);    	//01
        map.put('G', 2);    	//10
        map.put('T', 3);  		//11
        
        int hash = 0;
        for(int i=0;i<len;i++){
            //map string into int
            if(i<9){
                //map ACGT to 0-1 sequence.
                hash = (hash<<2) + map.get(s.charAt(i));		
            }else{					
            	//start from 10th letter
                hash=(hash<<2)+map.get(s.charAt(i));  
                hash&=(1<<20)-1;			//get latest 20 bit from 01 sequence.
       
                if(tmp.contains(hash) && !resultHash.contains(hash)){
                	resultHash.add(hash);
                    rslt.add(s.substring(i-9,i+1));
                }else
                    tmp.add(hash);
            }
        }
        return rslt;
    }
}
