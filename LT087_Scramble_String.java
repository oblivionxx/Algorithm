import java.util.Arrays;

/*
Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.

Below is one possible representation of s1 = "great":

    great
   /    \
  gr    eat
 / \    /  \
g   r  e   at
           / \
          a   t
To scramble the string, we may choose any non-leaf node and swap its two children.

For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".

    rgeat
   /    \
  rg    eat
 / \    /  \
r   g  e   at
           / \
          a   t
We say that "rgeat" is a scrambled string of "great".

Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".

    rgtae
   /    \
  rg    tae
 / \    /  \
r   g  ta  e
       / \
      t   a
We say that "rgtae" is a scrambled string of "great".

Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
 */
/*
 * DP, String
 */
public class LT087_Scramble_String {
	//1. 3d Boolean DP
    public boolean isScramble(String s1, String s2) {
        if(s1.length()!=s2.length()) return false;
        if(s1.equals(s2)) return true;
        
        int len = s1.length();
        boolean [][][] dp = new boolean[len][len][len+1];
        //dp[i][j][len]表示的是以i和j分别为s1和s2起点的长度为len的字符串是不是互为scramble
        
        for(int i=0;i<len;i++){
            for(int j=0;j<len;j++)
                dp[i][j][1] = s1.charAt(i)==s2.charAt(j);   //s1.substring(i,i+1) is scramble to s2.substring(j,j+1)
        }
        
        for(int sublen=2; sublen<=len; sublen++) {  		//loop substring len = 2~len
            for(int i=0; i<=len-sublen; i++) {  			//loop starting position.
                for(int j=0; j<=len-sublen; j++) {  
                    for(int p=1; p<sublen; p++) {       	//loop split position  in the substring 
                        dp[i][j][sublen] |= (dp[i][j][p] && dp[i+p][j+p][sublen-p]) ||   
                                                     (dp[i][j+sublen-p][p] && dp[i+p][j][sublen-p]);  
                    }  
                }  
            }  
        }  
          
        return dp[0][0][len];  
    }
    
    //2. Recursion of String.
    public boolean isScramble2(String s1, String s2) {
        if(s1.length()!=s2.length()) return false;
        if(s1.equals(s2)) return true;
        
        //scramble order.
        char[] ts1 = s1.toCharArray();
        char[] ts2 = s2.toCharArray();
        Arrays.sort(ts1);
        Arrays.sort(ts2);
        if(!new String(ts1).equals(new String(ts2))){  
            return false;  
        }  
         
        for(int i=1;i<s1.length();i++){
            String s11 = s1.substring(0,i);
            String s12 = s1.substring(i);
            
            //from left start len=i substring
            String s21 = s2.substring(0,i);
            String s22 = s2.substring(i);
            if(isScramble(s11,s21) && isScramble(s12, s22)) return true;
            
            //from right end len=i substring.
            s21 = s2.substring(s2.length() - i);
            s22 = s2.substring(0,s2.length() - i);
            if(isScramble(s11,s21) && isScramble(s12,s22)) return true;
            
        }
        return false;
    }
}
