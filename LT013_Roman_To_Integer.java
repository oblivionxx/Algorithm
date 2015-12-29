/*
 Given a roman numeral, convert it to an integer.
 Input is guaranteed to be within the range from 1 to 3999.
 */

/*
 * Math, String
 */
public class LT013_Roman_To_Integer {
    public int romanToInt(String s) {
        int res = 0;
        res+=charToInt(s.charAt(0));
        for(int i=1;i<s.length();i++){
        	int cur = charToInt(s.charAt(i));
        	int pre = charToInt(s.charAt(i-1));
        	if(cur>pre)
        		res +=cur-2*pre;
        	else
        		res +=cur;
        }
        
        return res;
    }
    
    public int charToInt(char c){
    	int res = 0;
    	switch(c){
    		case 'I': res=1;	break;
    		case 'V': res=5;	break;
    		case 'X': res=10;	break;
    		case 'L': res=50;	break;
    		case 'C': res=100;	break;
    		case 'D': res=500;	break;
    		case 'M': res=1000;	break;
    		default:  res =0; 	break;
    	}
    	
    	return res;
    }
}
