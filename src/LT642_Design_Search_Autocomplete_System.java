import java.util.*;

/*
 * Design a search autocomplete system for a search engine. Users may input a sentence (at least one word and end with a special character '#'). For each character they type except '#', you need to return the top 3 historical hot sentences that have prefix the same as the part of sentence already typed. Here are the specific rules:

The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one). If several sentences have the same degree of hot, you need to use ASCII-code order (smaller one appears first).
If less than 3 hot sentences exist, then just return as many as you can.
When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.
Your job is to implement the following functions:

The constructor function:

AutocompleteSystem(String[] sentences, int[] times): This is the constructor. The input is historical data. Sentences is a string array consists of previously typed sentences. Times is the corresponding times a sentence has been typed. Your system should record these historical data.

Now, the user wants to input a new sentence. The following function will provide the next character the user types:

List<String> input(char c): The input c is the next character typed by the user. The character will only be lower-case letters ('a' to 'z'), blank space (' ') or a special character ('#'). Also, the previously typed sentence should be recorded in your system. The output will be the top 3 historical hot sentences that have prefix the same as the part of sentence already typed.


Example:
Operation: AutocompleteSystem(["i love you", "island","ironman", "i love leetcode"], [5,3,2,2]) 
The system have already tracked down the following sentences and their corresponding times: 
"i love you" : 5 times 
"island" : 3 times 
"ironman" : 2 times 
"i love leetcode" : 2 times 
Now, the user begins another search: 

Operation: input('i') 
Output: ["i love you", "island","i love leetcode"] 
Explanation: 
There are four sentences that have prefix "i". Among them, "ironman" and "i love leetcode" have same hot degree. Since ' ' has ASCII code 32 and 'r' has ASCII code 114, "i love leetcode" should be in front of "ironman". Also we only need to output top 3 hot sentences, so "ironman" will be ignored. 

Operation: input(' ') 
Output: ["i love you","i love leetcode"] 
Explanation: 
There are only two sentences that have prefix "i ". 

Operation: input('a') 
Output: [] 
Explanation: 
There are no sentences that have prefix "i a". 

Operation: input('#') 
Output: [] 
Explanation: 
The user finished the input, the sentence "i a" should be saved as a historical sentence in system. And the following input will be counted as a new search. 

Note:
The input sentence will always start with a letter and end with '#', and only one blank space will exist between two words.
The number of complete sentences that to be searched won't exceed 100. The length of each sentence including those in the historical data won't exceed 100.
Please use double-quote instead of single-quote when you write test cases even for a character input.
Please remember to RESET your class variables declared in class AutocompleteSystem, as static/class variables are persisted across multiple test cases. Please see here for more details.

Design, Trie
 */
public class LT642_Design_Search_Autocomplete_System {
    // 思路：trie树，先把字符和频次用Trie树存储，根据prefix找到当前结点，遍历该结点以下的所有可能的字符串，统计top3的频次
    // save curStr at each node.
    class TrieNode {
	TrieNode[] children = new TrieNode[32];
	String str;
	int times;
    }

    public TrieNode add(TrieNode root, String str, int times) {
	char[] cs = str.toCharArray();
	if (root == null)
	    root = new TrieNode();
	TrieNode cur = root;
	for (char c : cs) {
	    int pos = 0;
	    if (c == ' ')
		pos = 27;
	    else
		pos = c - 'a';
	    if (cur.children[pos] == null)
		cur.children[pos] = new TrieNode();
	    cur = cur.children[pos];
	}
	if (cur.str != null)
	    cur.times += times;
	else
	    cur.times = times;
	cur.str = str;
	return root;
    }

    public TrieNode search(TrieNode root, String prefix) {
	TrieNode cur = root;
	for (char c : prefix.toCharArray()) {
	    int pos = 0;
	    if (c == ' ')
		pos = 27;
	    else
		pos = c - 'a';
	    if (cur.children[pos] != null)
		cur = cur.children[pos];
	    else
		return null;
	}
	return cur;
    }

    TrieNode root;
    String prefix;

    public LT642_Design_Search_Autocomplete_System(String[] sentences, int[] times) {
	for (int i = 0; i < sentences.length; ++i) {
	    root = add(root, sentences[i], times[i]);
	}
	prefix = "";
    }

    public List<TrieNode> bfs(TrieNode cur) {
	List<TrieNode> ans = new ArrayList<>();
	if (cur == null)
	    return ans;
	Queue<TrieNode> queue = new LinkedList<>();
	queue.offer(cur);
	while (!queue.isEmpty()) {
	    TrieNode node = queue.poll();
	    if (node.str != null)
		ans.add(node);
	    for (int i = 0; i < 32; ++i) {
		if (node.children[i] != null)
		    queue.offer(node.children[i]);
	    }
	}
	return ans;
    }

    public List<String> input(char c) {
	if (c == '#') {
	    root = add(root, prefix, 1);
	    prefix = "";
	    return new ArrayList<>();
	}
	prefix += c;
	List<TrieNode> candicates = bfs(search(root, prefix));			//bfs all the children nodes that isWord
	Collections.sort(candicates, new Comparator<TrieNode>() {
	    @Override
	    public int compare(TrieNode o1, TrieNode o2) {
		return o2.times != o1.times ? o2.times - o1.times : o1.str.compareTo(o2.str);		//order by times.
	    }
	});
	List<String> ans = new ArrayList<>();
	for (int i = 0; i < Math.min(candicates.size(), 3); ++i) {
	    ans.add(candicates.get(i).str);
	}
	return ans;
    }
}
