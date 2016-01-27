/*
Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

For example,
Given:
s1 = "aabcc",
s2 = "dbbca",

When s3 = "aadbbcbcac", return true.
When s3 = "aadbbbaccc", return false.

DP, String
 */
public class LT097_Interleaving_String {
	//2d boolean DP
	public boolean isInterleave(String s1, String s2, String s3) {
        if(s3.length()!=s1.length()+s2.length()) 
            return false;  
        int m = s1.length();
        int n = s2.length();
        
        //match[i][j] 意味着，S1的(0, i)和S2的(0,j)，匹配与S3的(i+j)
        //init. match only with s1.. 
        boolean match[][] = new boolean[m+1][n+1];
        match[0][0] = true;
        for(int i=1;i<=m;i++){
            if(s3.charAt(i-1)==s1.charAt(i-1))
                match[i][0] |= match[i-1][0]; 
            else
                match[i][0] = false;
        }
        
        //init. match only with s2.
        for(int j=1;j<=n;j++){
            if(s3.charAt(j-1)==s2.charAt(j-1))
                match[0][j] |= match[0][j-1];
            else
                match[0][j] = false;
        }
        
        
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                match[i][j] = ( (s3.charAt(i+j-1) == s1.charAt(i-1)) && match[i-1][j] ) || 
                              ( (s3.charAt(i+j-1) == s2.charAt(j-1)) && match[i][j-1] );
            }
        }
        
        return match[m][n];
    }
}
