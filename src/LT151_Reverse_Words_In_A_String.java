/*
Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".

Update (2015-02-12):
For C programmers: Try to solve it in-place in O(1) space.

click to show clarification.

Clarification:
What constitutes a word?
A sequence of non-space characters constitutes a word.
Could the input string contain leading or trailing spaces?
Yes. However, your reversed string should not contain leading or trailing spaces.
How about multiple spaces between two words?
Reduce them to a single space in the reversed string.

String
 */
public class LT151_Reverse_Words_In_A_String {
    // 1. using trim. separate by space.
    public String reverseWords(String s) {
	s = s.trim();
	String[] list = s.split("\\s+");
	StringBuilder sb = new StringBuilder();
	for (int i = list.length - 1; i >= 0; i--) {
	    if (i != 0)
		sb.append(list[i]).append(" ");
	    else
		sb.append(list[i]);
	}

	return sb.toString();
    }

    // 2. iterate string. similar LT176 no trailing and simple space in between.
    // step 1. reverse the whole string
    // step 2. reverse each word

}
