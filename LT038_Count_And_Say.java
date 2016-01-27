/*
 The count-and-say sequence is the sequence of integers beginning as follows:
 1, 11, 21, 1211, 111221, ...
 1 is read off as "one 1" or 11.
 11 is read off as "two 1s" or 21.
 21 is read off as "one 2, then one 1" or 1211.
 Given an integer n, generate the nth sequence.
 Note: The sequence of integers will be represented as a string.
 */

/*
 * String
 */
public class LT038_Count_And_Say {
	public String countAndSay(int n) {
        //for loop. update last string
		if(n<=0) return null;
		
		String str = "1";
		int cnt = 1;
		
		for(int i=1;i<n;i++){
			StringBuilder sb = new StringBuilder();
			for(int j=0;j<str.length();j++){
				if(j<str.length()-1 && str.charAt(j+1)==str.charAt(j))		//if compare j and j-1, may miss the last letter
																			//and cannot reverse the two element aside of &&
					cnt++;
				else{
					sb.append(cnt).append(str.charAt(j));
					cnt=1;
				}
			}
			
			str = sb.toString();
		}
		
		return str;
    }
}
