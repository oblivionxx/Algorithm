import java.util.*;

/*
 * Given a set of words (without duplicates), find all word squares you can build from them.

A sequence of words forms a valid word square if the kth row and column read the exact same string, where 0 ≤ k < max(numRows, numColumns).

For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same both horizontally and vertically.

b a l l
a r e a
l e a d
l a d y
Note:
There are at least 1 and at most 1000 words.
All words will have the exact same length.
Word length is at least 1 and at most 5.
Each word contains only lowercase English alphabet a-z.
Example 1:

Input:
["area","lead","wall","lady","ball"]

Output:
[
  [ "wall",
    "area",
    "lead",
    "lady"
  ],
  [ "ball",
    "area",
    "lead",
    "lady"
  ]
]

Explanation:
The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
Example 2:

Input:
["abat","baba","atan","atal"]

Output:
[
  [ "baba",
    "abat",
    "baba",
    "atan"
  ],
  [ "baba",
    "abat",
    "baba",
    "atal"
  ]
]

Explanation:
The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).

Backtracking, Trie
 */
public class LT425_Word_Squares {
    TrieNode root = new TrieNode();

    public List<List<String>> wordSquares(String[] words) {
	List<List<String>> res = new ArrayList<>();
	if (words.length == 0)
	    return res;
	buildTrie(words);
	int length = words[0].length();
	findSquare(res, length, new ArrayList<>());
	return res;
    }

    private void findSquare(List<List<String>> res, int length, List<String> elm) {
	if (elm.size() == length) {
	    res.add(new ArrayList<>(elm));
	    return;
	}
	int index = elm.size();
	StringBuilder sb = new StringBuilder();
	for (String s : elm) {
	    sb.append(s.charAt(index)); // generate the prefix of new string
	}
	String s = sb.toString();
	TrieNode node = root;
	for (int i = 0; i < s.length(); i++) {
	    if (node.children[s.charAt(i) - 'a'] != null) {
		node = node.children[s.charAt(i) - 'a'];
	    } else {
		node = null;
		break;
	    }
	}
	if (node != null) {
	    for (String next : node.words) {
		elm.add(next);
		findSquare(res, length, elm);
		elm.remove(elm.size() - 1);
	    }
	}
    }

    private void buildTrie(String[] words) {
	for (String word : words) {
	    TrieNode node = root;
	    char[] array = word.toCharArray();
	    for (char c : array) {
		node.words.add(word);
		if (node.children[c - 'a'] == null) {
		    node.children[c - 'a'] = new TrieNode();
		}
		node = node.children[c - 'a'];
	    }
	    node.words.add(word);
	}
    }

    class TrieNode {
	TrieNode[] children = new TrieNode[26];
	List<String> words = new ArrayList<>(); // replace startWith
    }
}
