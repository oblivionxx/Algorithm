/*
 Implement strStr().
 Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 */
/*
 * Two Pointer, String
 */
public class LT028_Implement_strStr {
    public int strStr(String haystack, String needle){
    	//1. KMP.
    	if(haystack ==null || needle == null) return 0;
    	int h = haystack.length();
    	int n = needle.length();

    	if(h<n) return -1;
    	if(n==0) return 0;

    	int[] next = getNext(needle);
    	int i=0;

    	while(i<=h-n){
    		int success=1;
    		for(int j =0;j<n;j++){
    			if(needle.charAt(0)!=haystack.charAt(i)){
    				success = 0;
    				i++;
    				break;
    			}
    			else if(needle.charAt(j)!=haystack.charAt(i+j)){
    				success = 0;
    				i = i+j-next[j-1];		//i jump to offset = j+next(j-1). not starting from 0.
    				break;
    			}

    		}
    		if(success==1) return i;
    	}
    	return -1;
    }

    private int[] getNext(String needle){
    	int[] next = new int[needle.length()];
    	next[0]=0;

    	for(int i=1;i<needle.length();i++){
    		int index = next[i-1];
    		while(index>0 && needle.charAt(index)!=needle.charAt(i))
    			index = next[index-1];
    	
	    	if(needle.charAt(index) == needle.charAt(i))
	    		next[i] = next[i-1]+1;
	    	else
	    		next[i] = 0;
    	}
   		return next;
    }
    
    
    public int strStrSol2(String haystack, String needle) {
        //Brute Force.
        int m = haystack.length();
        int n = needle.length();
        int i=0;
        int j=0;
        for(i=0;i<=(m-n);i++){
            for(j=0;j<n && haystack.charAt(i+j)==needle.charAt(j);j++);
            if(j==n) return i;
        }
        return -1;
    }
    
    //3.Boyer-Moore. http://www.ruanyifeng.com/blog/2013/05/boyer-moore_string_search_algorithm.html
    //Match from the end. O(n) in worst case. better KMP.
    
}
