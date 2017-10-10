
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
    public String simplifyPath(String path) {
	// using stack. list all the corner case
	// 当遇到“/../"则需要返回上级目录，需检查上级目录是否为空。
	// 当遇到"/./"则表示是本级目录，无需做任何特殊操作。
	// 当遇到"//"则表示是本级目录，无需做任何操作。
	// 当遇到其他字符则表示是文件夹名，无需简化。
	// 当字符串是空或者遇到”/../”，则需要返回一个"/"。
	// 当遇见"/a//b"，则需要简化为"/a/b"。
	if (path == null || path.length() == 0)
	    return "";
	Stack<String> stk = new Stack<>();
	String[] content = path.split("/+");

	for (int i = 0; i < content.length; i++) {
	    // 要注意split函数是可以split出空字符的，例如：//b/ 会被split结果为["","b"]。
	    if (content[i].equals(".") || content[i].length() == 0)		//must check if split with space.
		continue;
	    if (content[i].equals("..")) {
		if (!stk.isEmpty()) {
		    stk.pop();
		}
		continue; // if stk is empty, just continue.
	    }
	    stk.push(content[i]); // be careful the position of clause.
	}

	String res = "";
	while (!stk.empty()) {// we build string from sub directory to root directory,so res it's added at the end of string
	    res = "/" + stk.pop() + res;
	}
	if (res.length() == 0) {// if it's empty (eg. /../, or /home/../), we need to return "/"
	    return "/";
	}
	return res;

    }
    
    public String simplifyPath2(String path) {
        String[] tokens = path.split("/+");
        Stack<String> stk = new Stack<>();
        for(String s: tokens){
            if(s.equals(".") || s.length()==0) continue;
            if(s.equals("..")){
                if(!stk.isEmpty()) stk.pop();
            }else
                stk.push(s);
        }
        
        if(stk.isEmpty()) return "/";
        Stack<String> rev = new Stack<>();
        while(!stk.isEmpty()){
            rev.push("/"+stk.pop());
        }
        
        StringBuilder sb = new StringBuilder();
        while(!rev.isEmpty()){
            sb.append(rev.pop());
        }
        
        return sb.toString();
    }
}
