/*
Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.
For example:
Given "aacecaaa", return "aaacecaaa".
Given "abcd", return "dcbabcd".

String, KMP
 */
public class LT214_Shortest_Palindrome {
	//1. init idea is to calculate longest palidrome prefix.
	/* It's O(n). Pretty easy to prove using Master Method.
	   Given T(n) <= aT(n/b) +  O(n^d),
			T(n)= O(n^d)  if a<b^d

			Here a=1, b is not a constant but 1<b<n, and d=1. Thus a<b^d holds, 
			thus T(n) = O(n). EOF
	*/
    public static String shortestPalindrome(String s) {
        int j = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == s.charAt(j)) { j += 1; }
        }
        if (j == s.length()) { return s; }
        String suffix = s.substring(j);
        return new StringBuffer(suffix).reverse().toString() + shortestPalindrome(s.substring(0, j)) + suffix;
    }
    
	//2. KMP based
    public String shortestPalindrome2(String s) {
        StringBuilder builder = new StringBuilder(s);
        return builder.reverse().substring(0, s.length() - getCommonLength(s)) + s;
    }

    private int getCommonLength(String str) {
        StringBuilder builder = new StringBuilder(str);
        String rev = new StringBuilder(str).reverse().toString();
        //We add "#" here to force the match in reverse(s) starts from its first index 
        //eg. c a t a c b # b c a t a c.    # = 0, letter after # =0.!!
        //    0 0 0 0 1 0 0 0 1 2 3 4 5
        builder.append("#").append(rev);
        int[] p = new int[builder.length()];
        for (int i = 1; i < p.length; i++) {
            int j = p[i - 1];
            while (j > 0 && builder.charAt(i) != builder.charAt(j)) j = p[j - 1];
            p[i] = j == 0 ? (builder.charAt(i) == builder.charAt(0) ? 1 : 0) : j + 1;
        }
        return p[p.length - 1];
    }
    
    
    
}	
