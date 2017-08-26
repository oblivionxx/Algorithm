/*
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.

Note:
Have you consider that the string might be empty? This is a good question to ask during an interview.
For the purpose of this problem, we define empty string as valid palindrome.

String, Two Pointer.
 */
public class LT125_Valid_Palindrome {
    // case: 1 with non-alphanumeric including the punctuation
    // 2. uppercase and lowercase
    public boolean isPalindrome(String s) {
	if (s.length() == 0)
	    return true;

	String str = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase(); // remove non-alphanumeric
	int m = 0;
	int n = str.length() - 1;

	while (m <= n) {
	    if (str.charAt(m) != str.charAt(n))
		return false;
	    else {
		m++;
		n--;
	    }
	}

	return true;
    }
}
