/*
Given a non-empty string s and an abbreviation abbr, return whether the string matches with the given abbreviation.

A string such as "word" contains only the following valid abbreviations:

["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
Notice that only the above abbreviations are valid abbreviations of the string "word". Any other string is not a valid abbreviation of "word".

Note:
Assume s contains only lowercase letters and abbr contains only lowercase letters and digits.

Example 1:
Given s = "internationalization", abbr = "i12iz4n":

Return true.
Example 2:
Given s = "apple", abbr = "a2e":

Return false.

String
 */
public class LT408_Valid_Word_Abbreviation {
    public boolean validWordAbbreviation(String word, String abbr) {
	int i = 0, j = 0;
	int num = 0; // track the num in abbr
	while (i < word.length() && j < abbr.length()) {
	    if (Character.isDigit(abbr.charAt(j))) { // if num
		num = num * 10 + (abbr.charAt(j) - '0');
		if (num == 0)
		    return false; // not abbr.
		j++;
	    } else { // if abbr(j) = letter. compare with word
		i += num; // word need to jump num letter
		num = 0;
		if (i >= word.length() || word.charAt(i) != abbr.charAt(j))
		    return false;
		i++;
		j++;
	    }
	}
	i += num; // case: abbr end with number. if not, num=0
	return i == word.length() && j == abbr.length();
    }

}
