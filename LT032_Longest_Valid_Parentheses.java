/*
 Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 For "(()", the longest valid parentheses substring is "()", which has length = 2.
 Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
 */
/*
 * DP, String
 */
public class LT032_Longest_Valid_Parentheses {
    public int longestValidParentheses(String s) {
        //dp from backward
    	//int[] dp. dp[len-1]=0. from len-2~0. if s[i]=')', dp[i]=0
    	//									   if s[i]='(', j=i+1+dp[i+1]. 
    	//												if s[j]=')' && j<s.length--> dp[i] = 2+dp[i+1].   case: ((.))
    	//												if s[j]=')' && j<s.length &&j+1<s.length --> dp[i] = 2+dp[i+1]+dp[j+1]
   
    	if(s==null || s.length()==0) return 0;
    	int maxLen = 0;
    	int[] dp = new int[s.length()];
    	dp[s.length()-1] = 0;
    	
    	for(int i=s.length()-2;i>=0;i--){
    		if(s.charAt(i)==')') 
    			dp[i] =0;
    		else{
    			int j=i+1+dp[i+1];
    			if(j<s.length()&&s.charAt(j)==')'){
    				dp[i] = dp[i+1]+2;
    				if(j+1<s.length())
    					dp[i]+=dp[j+1];
    			}
    				
    		}
    		
    		maxLen = Math.max(maxLen,dp[i]);
    	}
    	
    	return maxLen;
    }
}
