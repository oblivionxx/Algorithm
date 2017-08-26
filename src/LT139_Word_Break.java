import java.util.Set;

/*
Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".

 */
public class LT139_Word_Break {
	//O(n^2) with DP. store past possible cut in memory dp[]
	public boolean wordBreak(String s, Set<String> wordDict) {
        boolean dp[] = new boolean[s.length()+1];
        dp[0] = true;
        for(int i=1; i <= s.length(); i++){
        	//string(0,i) cut at j. 
            for(int j=0; j < i; j++){
                if(dp[j] && wordDict.contains(s.substring(j, i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
	
	/* ETL. O(n!).
	public boolean wordBreak(String s, Set<String> wordDict) {
        if(wordDict.contains(s)) return true;
        for(int i=1;i<=s.length();i++){
            String s1 = s.substring(0,i);
            if(wordDict.contains(s1) && wordBreak(s.substring(i), wordDict)) 
                return true;
        }
        return false;
    }
	 */
}
