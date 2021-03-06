/*
Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB 
    
Math
 */
public class LT168_Excel_Sheet_Column_Title {
    public String convertToTitle(int n) {
	StringBuilder sb = new StringBuilder();
	while (n != 0) {
	    n--;
	    char cur = (char) ('A' + n % 26);
	    sb.append(cur);
	    n /= 26;
	}

	return sb.reverse().toString();
    }
}
