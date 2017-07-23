/*
 Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 For "(()", the longest valid parentheses substring is "()", which has length = 2.
 Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
 */
/*
 * DP, String
 */
public class LT032_Longest_Valid_Parentheses {
    //dp[i] to store the longest parentheses end at index i
    //If s[i] is '(', set dp[i] to 0,because any string end with '(' cannot be a valid one.
    //Else if s[i] is ')'
    //  If s[i-1] is '(', dp[i] = dp[i-2] + 2
    //  Else if s[i-1] is ')' and s[i-dp[i-1]-1] == '(', dp[i] = dp[i-1] + 2 + dp[i-dp[i-1]-2]
    //  eg.input "()(())", at i = 5, dp array is [0,2,0,0,2,0], dp[5] = dp[4] + 2 + dp[1] = 6.
    public int longestValidParentheses(String s) {
        if(s==null || s.length()==0) return 0;
        int max = 0;
        int[] dp = new int[s.length()];
        dp[0] = 0;
        for(int i=1;i<s.length();i++){
            if(s.charAt(i)==')'){
                if(s.charAt(i-1)=='('){
                    dp[i]=(i-2>=0)?dp[i-2]+2:2;
                   
                }else{
                    if((i-dp[i-1]-1) >=0 && s.charAt(i-dp[i-1]-1) == '('){
                        dp[i] = dp[i-1] + 2 + ((i-dp[i-1]-2>=0)?dp[i-dp[i-1]-2]:0);
                    }
                } 
                max = Math.max(max, dp[i]);
            }
            //else ='(' dp[i] is default 0. can skip
        }
        
        return max;
    }
}
