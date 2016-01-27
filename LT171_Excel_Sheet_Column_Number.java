/*
Related to question Excel Sheet Column Title

Given a column title as appear in an Excel sheet, return its corresponding column number.

For example:

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28 

Math
 */
public class LT171_Excel_Sheet_Column_Number {
	public int titleToNumber(String s) {
        if(s==null)
            return 0;
        
        int result =0;
        char[] charArray = s.toCharArray();
        
        for(int i=0;i<charArray.length;i++){			//char_i-'A'+1. remember +1
            result += (charArray[i]-'A'+1)*Math.pow(26,charArray.length-i-1);
        }
        
        return result;
    }
}
