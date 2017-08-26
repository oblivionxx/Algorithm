/*
 * Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:
Given s = "hello", return "holle".

Example 2:
Given s = "leetcode", return "leotcede".

Note:
The vowels does not include the letter "y".

Two Pointers, String
 */
public class LT345_Reverse_Vowels_of_a_String {
    public String reverseVowels(String s) {
	char[] charSet = s.toCharArray();
	String vowels = "aeiouAEIOU";
	int left = 0, right = charSet.length - 1;

	while (left < right) {
	    while (left < right && vowels.indexOf(charSet[left]) == -1)
		left++;
	    while (left < right && vowels.indexOf(charSet[right]) == -1)
		right--;
	    char tmp = charSet[left];
	    charSet[left] = charSet[right];
	    charSet[right] = tmp;
	    left++;
	    right--;
	}

	return new String(charSet);
    }
}
