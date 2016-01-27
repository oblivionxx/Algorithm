/*
Given a string, find the length of the longest substring T that contains at most 2 distinct characters.
For example, Given s = “eceba”,
T is "ece" which its length is 3.

HashTable, Two Pointer, String
 */
public class LT159_Longest_Substring_with_At_Most_Two_Distinct_Characters {
	public int lengthOfLongestSubstringTwoDistinct(String s) {
        //brute force的解法就是构造出来所有substring然后线性扫描一遍，复杂度为O(n^3)。可以使用Set来判断是不是有重复的字符。
        /*
        指针变量i指向sliding window的起始位置，nextleft指向另个一个字符在sliding window的最后一个，用于定位i的下一个跳转位置。内部逻辑就是
        1）如果当前字符跟前一个字符是一样的，直接继续。
        2）如果不一样，则需要判断当前字符跟j是不是一样的
        a）一样的话sliding window左边不变，右边继续增加，但是nextleft的位置需要调整到right-1。
        b）不一样的话，sliding window的左侧变为j的下一个字符（也就是去掉包含nextLeft指向的字符的区间），nextleft的位置也需要调整到right-1。
        
        在对i进行调整的时候（1.a），需要更新maxLen。
        */
        int i = 0, nextleft = -1, maxLen = 0;
        for (int right = 1; right < s.length(); right++) {
            if (s.charAt(right) == s.charAt(right-1)) continue;
            if (nextleft >= 0 && s.charAt(nextleft) != s.charAt(right)) {
                maxLen = Math.max(right - i, maxLen);
                i = nextleft + 1; 
            }
            nextleft = right - 1;  
        }
        return Math.max(s.length() - i, maxLen);
    }
}
