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
        if(n<27){
            n--;
            sb.insert(0, (char)(n%26+'A'));
        }else{
            while(n!=0){
                n--;									//first n--. important
                sb.insert(0,(char)(n%26+'A')); 			//insert before. reverse order!!
                										//here pay attention to int + char. force to change type as char. and add the bracket in the correct place ?
                n = n/26;
            }
        }
        
        return sb.toString();  
    }
}
