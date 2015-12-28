/*
 The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
 P   A   H   N
 A P L S I I G
 Y   I   R
 And then read line by line: "PAHNAPLSIIGYIR"
 Write the code that will take a string and make this conversion given a number of rows:

 string convert(string text, int nRows);
 convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 */

/*
 * String
 */
public class LT006_Zigzag_Conversion {
	public String convert(String s, int nRows) {
        //just trick to calculate zigzaging letter position.
		if(nRows==1) return s;
		if(s==null || s.length()==0 || nRows<1) return "";
		
		StringBuilder sb = new StringBuilder();
		int zigzag = 2*nRows-2;		
		for(int i=0;i<nRows;i++){
			for(int j=i;j<s.length();j+=zigzag){
				//non-zigzaging letter. those in colums
				sb.append(s.charAt(j));
				
				//add zigzaging letter in row 1~lastRow-1
				if(i>0 && i<nRows-1){
					int idx = j+zigzag-2*i;		//trick here
					if(idx<s.length()) sb.append(s.charAt(idx));
				}
			}
		}
		
		return sb.toString();
		    
    }
}
