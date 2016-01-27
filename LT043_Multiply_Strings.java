/*
 Given two numbers represented as strings, return multiplication of the numbers as a string.
 Note: The numbers can be arbitrarily large and are non-negative.
 */
/*
 * Math, String
 */
public class LT043_Multiply_Strings {
	public String multiply(String num1, String num2) {
        //reverse the string. calculate multiply of every pair of digit num1_i, num2_j.
		String n1 = new StringBuilder(num1).reverse().toString();
		String n2 = new StringBuilder(num2).reverse().toString();
		
		int[] res = new int[n1.length()+n2.length()];
		for(int i=0;i<n1.length();i++){
			for(int j=0;j<n2.length();j++){
				res[i+j] += (n1.charAt(i)-'0')*(n2.charAt(j)-'0');
			}
		}
		
		//change to number. res[l] may be larger than 9.
		int carry = 0;
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<res.length;i++){
			int digit = res[i]+carry;
			carry = digit/10;
			digit = digit%10;
			sb.append(digit);
		}
		
		if(carry>0)
			sb.append(carry);
		sb = sb.reverse();
		while(sb.charAt(0)=='0' && sb.length()>1)
			sb.deleteCharAt(0);
		
		return sb.toString();
		
	
    }
}
