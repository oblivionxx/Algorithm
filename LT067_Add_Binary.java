/*
 Given two binary strings, return their sum (also a binary string).
	For example,
	a = "11"
	b = "1"
	Return "100".
 */
public class LT067_Add_Binary {
	public String addBinary(String a, String b) {
        //starting from length-1. bwd
		if(a==null || a.length()==0)  
            return b;  
        if(b==null || b.length()==0)  
            return a;  
		
		int i = a.length()-1;
		int j = b.length()-1;
		int carry = 0;
		StringBuilder sb = new StringBuilder();
		
		while(i>=0 && j>=0){
			int digit = (int)(a.charAt(i)-'0'+b.charAt(j)-'0')+carry;
			carry = digit/2;
			digit  = digit%2;
			sb.append(digit);
			i--;
			j--;
		}
		
		while(i>=0){
			int digit = (int)(a.charAt(i)-'0')+carry;
			carry  = digit/2;
			digit = digit%2;
			sb.append(digit);
			i--;
		}
		
		while(j>=0){
			int digit = (int)(b.charAt(j)-'0')+carry;
			carry  = digit/2;
			digit = digit%2;
			sb.append(digit);
			j--;
		}
		
		if(carry>0)
			sb.append(carry);
		
		return sb.reverse().toString();
    }
	
	//shorter version
	public String addBinary2(String a, String b) {
		if(a==null || a.length()==0)  
	        return b;  
	    if(b==null || b.length()==0)  
	        return a;  
		
		int i = a.length()-1;
		int j = b.length()-1;
		int carry = 0;
		StringBuilder sb = new StringBuilder();
		
		while(i>=0 || j>=0){
			int digit = carry;
			if (j >= 0) digit += b.charAt(j--) - '0';
            if (i >= 0) digit += a.charAt(i--) - '0';
			sb.append(digit%2);
			carry = digit/2;
		}
		
		if(carry>0)
			sb.append(carry);
		
		return sb.reverse().toString();
	}
}
