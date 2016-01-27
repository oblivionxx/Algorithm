/*
Given two strings S and T, determine if they are both one edit distance apart.
String
 */
public class LT161_One_Edit_Distance {
	public boolean isOneEditDistance(String s, String t) {
        //replace, delete, insert
        //compare length for delete and insert distance
        if(Math.abs(s.length()-t.length())>1) return false;
        if(s.length()==t.length()) return isSameLength(s,t);
        else if(s.length()<t.length()) return isOneLength(s,t);
        else return isOneLength(t,s);
    }
    
    public boolean isSameLength(String s, String t){
        //length same. replace. only one letter is different.
        int count = 0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)!=t.charAt(i))
                count++;
        }
        
        return count==1;
    }
        
    public boolean isOneLength(String s, String t){
    	//length s<t
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == t.charAt(i))
                continue;
            return s.substring(i).equals(t.substring(i+1));
        }
        return true; // 说明前面的都相等只有最后一个不匹配
    }
}
