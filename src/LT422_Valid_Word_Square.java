import java.util.List;

/*
Given a sequence of words, check whether it forms a valid word square.

A sequence of words forms a valid word square if the kth row and column read the exact same string, where 0 ≤ k < max(numRows, numColumns).

Note:
The number of words given is at least 1 and does not exceed 500.
Word length will be at least 1 and does not exceed 500.
Each word contains only lowercase English alphabet a-z.
Example 1:

Input:
[
  "abcd",
  "bnrt",
  "crmy",
  "dtye"
]

Output:
true

Explanation:
The first row and first column both read "abcd".
The second row and second column both read "bnrt".
The third row and third column both read "crmy".
The fourth row and fourth column both read "dtye".

Therefore, it is a valid word square.
Example 2:

Input:
[
  "abcd",
  "bnrt",
  "crm",
  "dt"
]

Output:
true

Explanation:
The first row and first column both read "abcd".
The second row and second column both read "bnrt".
The third row and third column both read "crm".
The fourth row and fourth column both read "dt".

Therefore, it is a valid word square.
Example 3:

Input:
[
  "ball",
  "area",
  "read",
  "lady"
]

Output:
false

Explanation:
The third row reads "read" while the third column reads "lead".

Therefore, it is NOT a valid word square.

 */
public class LT422_Valid_Word_Square {
    public boolean validWordSquare(List<String> words) {
	if (words == null || words.size() < 1)
	    return true;
	// check size
	if (words.get(0).length() != words.size())
	    return false;

	for (int i = 0; i < words.size(); i++) { // ith row
	    for (int j = 0; j < words.get(i).length(); j++) { // jth col
		char c1 = words.get(i).charAt(j); // compare [i,j] and [j,i]

		if (words.size() < j + 1 || words.get(j).length() < i + 1)
		    return false;
		char c2 = words.get(j).charAt(i);
		if (c1 != c2) {
		    return false;
		}
	    }
	}

	return true;
    }
}
