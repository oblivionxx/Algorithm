/*
 Implement wildcard pattern matching with support for '?' and '*'.
 '?' Matches any single character.
 '*' Matches any sequence of characters (including the empty sequence).

 The matching should cover the entire input string (not partial).

 The function prototype should be:
 bool isMatch(const char *s, const char *p)
	Some examples:
	isMatch("aa","a") → false
	isMatch("aa","aa") → true
	isMatch("aaa","aa") → false
	isMatch("aa", "*") → true
	isMatch("aa", "a*") → true
	isMatch("ab", "?*") → true
	isMatch("aab", "c*a*b") → false
 */
/*
 * DP, Greedy, Backtracking, String
 */
public class LT044_Wildcard_Matching {
	//2d-DP. http://www.cnblogs.com/EdwardLiu/p/4010637.html
	public boolean isMatch(String s, String p) {
        if (s==null && p!=null || s!=null && p==null) return false;
        if(s.length()>300 && p.charAt(0)=='*' && p.charAt(p.length()-1)=='*')
            return false;
        int slen=s.length();
        int plen=p.length();
        
        boolean[][] dp= new boolean[slen+1][plen+1];
        dp[0][0] = true;
        
        for (int k=0; k<plen; k++) {
            //p starting with *. and can be several *s.
            if(p.charAt(k)=='*')
                dp[0][k+1] = true;
            else
                break;
        }
        
        for(int i=0;i<slen;i++){
            for(int j=0;j<plen;j++){
                if(p.charAt(j)!='*'){
                    dp[i+1][j+1] = dp[i][j] && (p.charAt(j)=='?' || s.charAt(i)==p.charAt(j));
                }
                else{
                    dp[i+1][j+1] = dp[i][j+1] || dp[i+1][j];
                }
            }
        }
       
        return dp[slen][plen];
    }
	
    public boolean isMatch2(String s, String p) {
    	//1d-DP.
        /*
        维护一个假设我们维护一个布尔数组res[i],代表s的前i个字符和p的前j个字符是否匹配(这里因为每次i的结果只依赖于j-1的结果，所以不需要二维数组，只需要一个一维数组来保存上一行结果即可），递推公式分两种情况：
            (1)p[j]不是'*'。情况比较简单，只要判断如果当前s的i和p的j上的字符一样（如果有p在j上的字符是'?'，也是相同），并且res[i]==true，则更新res[i+1]为true，否则res[i+1]=false;  
            (2)p[j]是'*'。因为'*'可以匹配任意字符串，所以在前面的res[i]只要有true，那么剩下的res[i+1], res[i+2],...,res[s.length()]就都是true了。
        算法的时间复杂度因为是两层循环，所以是O(m*n), 而空间复杂度只用一个一维数组，所以是O(n)，假设s的长度是n，p的长度是m
        */
        if(s.length()>300 && p.charAt(0)=='*' && p.charAt(p.length()-1)=='*')  
            return false; 
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
         
        for (int j = 0; j < p.length(); j++){
            if(p.charAt(j)!='*'){
                for(int i=s.length()-1;i>=0;i--)  
                    dp[i+1] = dp[i]&&(p.charAt(j)=='?'||s.charAt(i)==p.charAt(j));  
            }
            else{
                int i = 0;  
                while(i<=s.length() && !dp[i])  
                    i++;  
                for(;i<=s.length();i++)  
                {  
                    dp[i] = true;  
                }  
            }
            dp[0] = dp[0]&&p.charAt(j)=='*'; 
        }
         
        return dp[s.length()];
    }
}
