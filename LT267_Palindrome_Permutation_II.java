
/*
Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.
For example:
Given s = "aabb", return ["abba", "baab"].
Given s = "abc", return [].

Hint:

If a palindromic permutation exists, we just need to generate the first half of the string.
To generate all distinct permutations of a (half of) string, use a similar approach from: Permutations II or Next Permutation.

Backtracking
 */
import java.util.*;

public class LT267_Palindrome_Permutation_II {
	
	private List<String> list = new ArrayList<>();

	public List<String> generatePalindromes(String s) {
		int numOdds = 0; 
		int[] map = new int[256]; // 1. Map from character to its frequency. little bit complicate with hashmap
		for (char c : s.toCharArray()) {
			map[c]++;
			numOdds = (map[c] & 1) == 1 ? numOdds + 1 : numOdds - 1;
		}
		if (numOdds > 1)			//2. check if can generate palindromes
			return list;

		String mid = "";			//3. change freq to half. get len of half string
		int length = 0;
		for (int i = 0; i < 256; i++) {
			if (map[i] > 0) {
				if ((map[i] & 1) == 1) { // Char with odd count will be in the
											// middle
					mid = "" + (char) i;
					map[i]--;
				}
				map[i] /= 2; // Cut in half since we only generate half string
				length += map[i]; // The length of half string
			}
		}
		generatePalindromesHelper(map, length, "", mid);		//4. backtracking
		return list;
	}

	private void generatePalindromesHelper(int[] map, int length, String s, String mid) {
		if (s.length() == length) {
			StringBuilder reverse = new StringBuilder(s).reverse(); 
			list.add(s + mid + reverse);
			return;
		}
		for (int i = 0; i < 256; i++) { // backtracking just like permutation
			if (map[i] > 0) {
				map[i]--;
				generatePalindromesHelper(map, length, s + (char) i, mid);
				map[i]++;
			}
		}
	}
}
