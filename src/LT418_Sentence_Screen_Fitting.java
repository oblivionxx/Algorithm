/*
 * Given a rows x cols screen and a sentence represented by a list of non-empty words, find how many times the given sentence can be fitted on the screen.

Note:

A word cannot be split into two lines.
The order of words in the sentence must remain unchanged.
Two consecutive words in a line must be separated by a single space.
Total words in the sentence won't exceed 100.
Length of each word is greater than 0 and won't exceed 10.
1 <= rows, cols <= 20,000.
Example 1:

Input:
rows = 2, cols = 8, sentence = ["hello", "world"]

Output: 
1

Explanation:
hello---
world---

The character '-' signifies an empty space on the screen.
Example 2:

Input:
rows = 3, cols = 6, sentence = ["a", "bcd", "e"]

Output: 
2

Explanation:
a-bcd- 
e-a---
bcd-e-

The character '-' signifies an empty space on the screen.
Example 3:

Input:
rows = 4, cols = 5, sentence = ["I", "had", "apple", "pie"]

Output: 
1

Explanation:
I-had
apple
pie-I
had--

The character '-' signifies an empty space on the screen.

DP
 */
public class LT418_Sentence_Screen_Fitting {
    // BF, TLE. eg: ["a","b"], 20000, 20000
    public int wordsTyping(String[] sentence, int rows, int cols) {
	if (sentence == null || sentence.length == 0 || sentence.length > rows * cols || rows <= 0 || cols <= 0)
	    return 0;
	int res = 0;
	int j = 0; // indicate the index of string in sentence that is currently trying to be inserted to current row
	int row = 0; // current row
	int col = 0; // current col

	while (row < rows) {
	    while (col + sentence[j].length() <= cols) {
		col = col + sentence[j].length() + 1;
		j++;
		if (j == sentence.length) {
		    res++;
		    j = 0;
		}
	    }
	    row++;
	    col = 0;
	}
	return res;
    }

    public int wordsTyping2(String[] sentence, int rows, int cols) {
	if (sentence == null || sentence.length == 0)
	    return rows * cols;

	int row = 1, col = cols, idx = 0, cnt = 0;
	int total_len = 0;
	for (int i = 0; i < sentence.length; i++) {
	    total_len += sentence[i].length() + 1;
	}
	while (row <= rows) {
	    if (col >= sentence[idx].length()) {
		col = col - sentence[idx].length();
		if (col > 0)
		    col--;
		if (++idx == sentence.length) {
		    cnt += (1 + col / total_len); // see the rest of col can fit how many sentence. calculate multiples
		    col = col % total_len;
		    idx = 0;
		}
	    } else {
		row++;
		col = cols;
	    }
	}
	return cnt;
    }

}
