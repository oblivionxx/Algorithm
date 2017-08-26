import java.util.*;

/*
Write a function to generate the generalized abbreviations of a word.

Example:
Given word = "word", return the following list (order does not matter):
["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]

Backtracking, Bit Manipulation
 */
public class LT320_Generalized_Abbreviation {
    public List<String> generateAbbreviations(String word) {
	List<String> res = new ArrayList<String>();
	backtrack(res, word, 0, "", 0);
	return res;
    }

    private void backtrack(List<String> res, String word, int pos, String cur, int count) {
	if (pos == word.length()) {
	    if (count > 0)
		cur += count;
	    res.add(cur);
	    return;
	}

	backtrack(res, word, pos + 1, cur, count + 1); // no abbr, keep letter at pos. continue
	backtrack(res, word, pos + 1, cur + (count > 0 ? count : "") + word.charAt(pos), 0); // record count + abbr, continue

    }
}
