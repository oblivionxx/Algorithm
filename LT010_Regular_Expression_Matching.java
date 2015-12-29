/*
 '.' Matches any single character.
 '*' Matches zero or more of the preceding element.

 The matching should cover the entire input string (not partial).
 The function prototype should be:
 bool isMatch(const char *s, const char *p)
	Some examples:
	isMatch("aa","a") → false
	isMatch("aa","aa") → true
	isMatch("aaa","aa") → false
	isMatch("aa", "a*") → true
	isMatch("aa", ".*") → true
	isMatch("ab", ".*") → true
	isMatch("aab", "c*a*b") → true
 */

/*
 * DP, Backtracking, String
 * Trick part is '*'
 */
public class LT010_Regular_Expression_Matching {
	public boolean isMatch(String s, String p) {
		//dp O(n^2)
		/* boolean dp[i][j]: if s[0..i-1] matches p[0..j-1]
		 * if p[j-1] != '*'
		 * dp[i][j] = (dp[i-1][j-1] && (s[i-1] == p[j-1]||p[j-1]=='.'))
		 * else p[j-1] = '*', p[j-2]=x
		 * then dp[i][j] is true iff any of the following is true
         * 1) "x*" repeats 0 time and matches empty: dp[i][j -2]
         * 2) "x*" repeats >= 1 times and matches "x*x": s[i-1] == x && dp[i-1][j]. 
         */
		
		boolean dp[][] = new boolean[s.length()+1][p.length()+1];
		//p,s="".
		dp[0][0] = true;
		
		for(int i=1;i<=s.length();i++)
			dp[i][0] = false;
		
		for(int j=1;j<=p.length();j++)
			//p(0) cannot be '*'. so check j>1
			dp[0][j] = j>1 && p.charAt(j-1)=='*' && dp[0][j-2];
		
		for(int i=1;i<=s.length();i++){
			for(int j=1;j<=p.length();j++){
				if(p.charAt(j-1)!='*')
					dp[i][j] = dp[i-1][j-1] && (s.charAt(i-1)==p.charAt(j-1)||p.charAt(j-1)=='.');
				else
					dp[i][j] = dp[i][j-2] || ( s.charAt(i-1)==p.charAt(j-2) ||p.charAt(j-2)=='.') && dp[i-1][j];
			}
		}
		
		return dp[s.length()][p.length()];
		
	}
	
	public boolean isMatchSol2(String s, String p) {
		//recursion. compare two digits in p. check second digit is '*' or not
		//every time p is subtract by 2
		
		//empty string
		if(p.isEmpty()) return s.isEmpty();
		
		//p.charAt(1) is letter or '.'
		//check s(0)==p(0). then recurse s.sub(1) and p.sub(1)
		
		//easy to forget p.length==1
	    if (p.length() == 1)
	            return (s.length() == 1) && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.');

        if (p.charAt(1) != '*') {
            if (s.length() == 0)
                return false;
            else
                return (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')
                        && isMatch(s.substring(1), p.substring(1));
        }
        
		//p.charAt(1) is '*'. p.length>=2. 
		//if s(0)=p(0), try every possible occurrence of s(0). s->s.substr(1)
		while(!s.isEmpty() && (s.charAt(0)==p.charAt(0)||p.charAt(0)=='.')){
			if(isMatch(s, p.substring(2)))
				return true;
			s = s.substring(1);
		}
		//if s(0)!=p(0). means *=zero repeat.
		return isMatch(s,p.substring(2));
		
	}
}
