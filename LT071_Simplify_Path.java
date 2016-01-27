/*
 Given an absolute path for a file (Unix-style), simplify it.

 For example,
 path = "/home/", => "/home"
 path = "/a/./b/../../c/", => "/c"

	Corner Cases:
	Did you consider the case where path = "/../"?
	In this case, you should return "/".
	Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
	In this case, you should ignore redundant slashes and return "/home/foo".
 */
/*
 * Stack, String
 */
import java.util.*;
public class LT071_Simplify_Path {
	// 当遇到“/../"则需要返回上级目录，需检查上级目录是否为空。
    //当遇到"/./"则表示是本级目录，无需做任何特殊操作。 
    //当遇到"//"则表示是本级目录，无需做任何操作。
    //当遇到其他字符则表示是文件夹名，无需简化。
    //当字符串是空或者遇到”/../”，则需要返回一个"/"。
    // 当遇见"/a//b"，则需要简化为"/a/b"。
	
	//idea: split path. then check 
	//	if . or / or empty string. then do nothing. 
	//	if .., pop stk.
	//  else, push to stk.
	public String simplifyPath(String path) {
        if(path==null || path.length()==0) return "";
		Stack<String> stk = new Stack<>();
        String[] content = path.split("/");
        
        for(int i=0;i<content.length;i++){
        	//要注意split函数是可以split出空字符的，例如：//b/ 会被split结果为["","b"]。
        	if(content[i].equals(".")||content[i].equals("/") || content[i].length()==0) 
        		continue;
        	if(content[i].equals("..")){
        		if(!stk.isEmpty()){
        			stk.pop();
        		}
        		continue;		//if stk is empty, just continue.
        	}
        	stk.push(content[i]);	//be careful the position of clause.
        }
        
        if(stk.isEmpty()) return "/";
        StringBuilder stb = new StringBuilder();

        Stack<String> reverse = new Stack<String>();
        
        while(!stk.isEmpty())   //cannot use reverse otherwise the directory name would be reversed
           reverse.push('/'+stk.pop());
           
        while(!reverse.isEmpty())
            stb.append(reverse.pop());
        
        return stb.toString();
    }
}
