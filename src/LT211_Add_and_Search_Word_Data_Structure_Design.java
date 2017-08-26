import java.util.HashMap;

/*
Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)
search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

For example:

addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true
Note:
You may assume that all words are consist of lowercase letters a-z.

click to show hint.

You should be familiar with how a Trie works. If not, please work on this problem: Implement Trie (Prefix Tree) first.

 */
public class LT211_Add_and_Search_Word_Data_Structure_Design {
    public class WordDictionary {
	public TrieNode root;

	public WordDictionary() {
	    root = new TrieNode();
	}

	// Adds a word into the data structure. Trie insert(word)
	public void addWord(String word) {
	    TrieNode node = root;
	    for (int i = 0; i < word.length(); i++) {
		if (!node.children.containsKey(word.charAt(i)))
		    node.children.put(word.charAt(i), new TrieNode());

		node = node.children.get(word.charAt(i));
	    }
	    node.terminable = true;
	}

	// Returns if the word is in the data structure. A word could
	// contain the dot character '.' to represent any one letter. DIFFERENCE.
	public boolean search(String word) {
	    return dfsSearch(root, word, 0);
	}

	public boolean dfsSearch(TrieNode node, String word, int start) {
	    if (start == word.length()) {
		return node.terminable;
	    }

	    char c = word.charAt(start);
	    if (c == '.') {
		for (TrieNode child : node.children.values()) {
		    if (dfsSearch(child, word, start + 1)) {
			return true;
		    }
		}
	    } else {
		TrieNode child = node.children.get(c);
		if (child != null) {
		    return dfsSearch(child, word, start + 1);
		}
	    }

	    return false;
	}

    }

    class TrieNode {
	public HashMap<Character, TrieNode> children = new HashMap<>();
	public boolean terminable;

	public TrieNode() {
	    children = new HashMap<Character, TrieNode>();
	    terminable = false;
	}
    }

    // Your WordDictionary object will be instantiated and called as such:
    // WordDictionary wordDictionary = new WordDictionary();
    // wordDictionary.addWord("word");
    // wordDictionary.search("pattern");
}
