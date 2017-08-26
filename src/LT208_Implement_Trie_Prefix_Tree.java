
/*
Implement a trie with insert, search, and startsWith methods.
Note:
You may assume that all inputs are consist of lowercase letters a-z.

Trie, Design
 */
import java.util.*;

public class LT208_Implement_Trie_Prefix_Tree {
    // Initialize your data structure here.
    public class TrieNode {
	// freq
	public int freq;
	// word path
	public HashMap<Character, TrieNode> children = new HashMap<>();
	// end of word
	public boolean terminable;
	// constructor

	public TrieNode() {
	    children = new HashMap<Character, TrieNode>();
	    freq = 0;
	    terminable = false;
	}
    }

    public class Trie {
	private TrieNode root;

	public Trie() {
	    root = new TrieNode();
	}

	// Inserts a word into the trie.
	public void insert(String word) {
	    TrieNode node = root;

	    for (int i = 0; i < word.length(); i++) {
		// 求字符地址，方便将该字符放入到26叉树中的哪一叉中
		if (!node.children.containsKey(word.charAt(i)))
		    node.children.put(word.charAt(i), new TrieNode());

		node = node.children.get(word.charAt(i));
	    }

	    node.terminable = true;
	    // 说明是最后一个字符，统计该词出现的次数
	    node.freq++;

	}

	// Returns if the word is in the trie.
	public boolean search(String word) {
	    TrieNode node = root;
	    for (int i = 0; i < word.length(); i++) {
		if (!(node.children.containsKey(word.charAt(i))))
		    return false;
		else
		    node = node.children.get(word.charAt(i));
	    }

	    return node.terminable; // 即便该字符串在Trie路径中，也不能说明该单词已存在，因为它有可能是某个子串
	}

	// Returns if there is any word in the trie
	// that starts with the given prefix.
	public boolean startsWith(String prefix) {
	    TrieNode node = root;
	    for (int i = 0; i < prefix.length(); i++) {
		if (!(node.children.containsKey(prefix.charAt(i))))
		    return false;
		else
		    node = node.children.get(prefix.charAt(i));
	    }

	    return true;
	}
    }
}

// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");
