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
	// 2d boolean DP
	public static boolean isInterleave(String s1, String s2, String s3) {
        //dp[i][j] = s3(0,i+j) can be formed by s1(0,i) and s2(0,j)
        if ((s1.length()+s2.length())!=s3.length()) return false;
        int m = s1.length();
		int n = s2.length();
        
        boolean[][] matrix = new boolean[m+1][n+1];
    
        matrix[0][0] = true;
    
        for (int i = 1; i <= m; i++){
            matrix[i][0] = matrix[i-1][0]&&(s1.charAt(i-1)==s3.charAt(i-1));
        }
        
        for (int i = 1; i <= n; i++){
        	matrix[0][i] = matrix[0][i-1]&&(s2.charAt(i-1)==s3.charAt(i-1));
        }
    
        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++){
                matrix[i][j] = (matrix[i-1][j]&&(s1.charAt(i-1)==s3.charAt(i+j-1))) || (matrix[i][j-1]&&(s2.charAt(j-1)==s3.charAt(i+j-1)));
            }
        }
    
        return matrix[m][n];
    }
	
	public static void main(String[] args){
		System.out.println(isInterleave("","abc","abc"));
	}
}
