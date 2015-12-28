/*
 * Given a string S, find the longest palindromic substring in S. 
 * You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.
 */

/*
 * String
 */
public class LT005_Longest_Palindrome_Substring {
    public String longestPalindrome(String s) {
    	//O(n^2)
        if(s==null || s.length()==0) return "";
        String init = s.substring(0,1);
        for(int i=0;i<s.length();i++){
        	//odd length
        	String s1 = isPalindrome(s,i,i);
        	if(s1.length()>init.length()) init=s1;
        	
        	//even length
        	String s2 = isPalindrome(s,i,i+1);
        	if(s2.length()>init.length()) init=s2;
        }
        
        return init;
    }
    
    public String isPalindrome(String s, int left, int right){
    	//from center to two side. Need to consider the boundary
    	while(left>=0 && right<s.length() && s.charAt(left)==s.charAt(right)){
    		left--;
    		right++;
    	}
    	
    	return s.substring(left+1, right);
    }
}
