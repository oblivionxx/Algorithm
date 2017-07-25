/*
Given a string, find the length of the longest substring T that contains at most 2 distinct characters.
For example, Given s = “eceba”,
T is "ece" which its length is 3.

HashTable, Two Pointer, String
 */
public class LT159_Longest_Substring_with_At_Most_Two_Distinct_Characters {
	public int lengthOfLongestSubstringTwoDistinct(String s) {
        int[] count = new int[256];
        int num = 0, i = 0, res = 0;
        for (int j = 0; j < s.length(); j++) {
            if (count[s.charAt(j)]++ == 0) num++;
            if (num > 2) {
                while (--count[s.charAt(i++)] > 0);         //count[left_letter]-- until =0. move left until there's a letter not exist in count[]
                num--;
            }
            res = Math.max(res, j - i + 1);
        }
        return res;
    }
}
