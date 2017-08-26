/*
 * Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. You may assume that each word will contain only lower case letters. If no such two words exist, return 0.

Example 1:
Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
Return 16
The two words can be "abcw", "xtfn".

Example 2:
Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
Return 4
The two words can be "ab", "cd".

Example 3:
Given ["a", "aa", "aaa", "aaaa"]
Return 0
No such pair of words.

Bit Manipulation
 */
public class LT318_Maximum_Product_of_Word_Lengths {
    public int maxProduct(String[] words) {
	// bit manipulation
	if (words == null || words.length == 0)
	    return 0;

	int len = words.length;
	int[] value = new int[len];
	for (int i = 0; i < len; i++) {
	    String tmp = words[i];
	    value[i] = 0;
	    for (int j = 0; j < tmp.length(); j++) {
		value[i] |= 1 << (tmp.charAt(j) - 'a'); // save the mask into value[i]. eg: word = abc, mask = 000...0111
	    } // 32bit int can fit 26 letters
	}
	int maxProduct = 0;
	for (int i = 0; i < len; i++)
	    for (int j = i + 1; j < len; j++) {
		if ((value[i] & value[j]) == 0 && (words[i].length() * words[j].length() > maxProduct)) // no common letter and find maxProduct
		    maxProduct = words[i].length() * words[j].length();
	    }
	return maxProduct;
    }
}
