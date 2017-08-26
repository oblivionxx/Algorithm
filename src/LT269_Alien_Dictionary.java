
/*
There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

For example,
Given the following words in dictionary,

[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]
The correct order is: "wertf".

Note:
You may assume all letters are in lowercase.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.

Graph, Topological Sort
 */
import java.util.*;

public class LT269_Alien_Dictionary {
    // https://leetcode.com/discuss/77078/easiest-java-bfs-solution
    // https://leetcode.com/discuss/54549/java-toposort-solution-clean
    // https://leetcode.com/discuss/78602/3ms-clean-java-solution-dfs
    public String alienOrder(String[] words) {
	// adjacent list
	HashMap<Character, HashSet<Character>> adj = new HashMap<>();
	// calculate indegree
	HashMap<Character, Integer> freq = new HashMap<>();
	for (String s : words) {
	    for (char c : s.toCharArray()) {
		freq.put(c, 0);
	    }
	}

	for (int i = 0; i < words.length - 1; i++) {
	    String cur = words[i];
	    String next = words[i + 1];
	    int length = Math.min(cur.length(), next.length());
	    for (int j = 0; j < length; j++) {
		char c1 = cur.charAt(j); // c1-->c2
		char c2 = next.charAt(j);
		if (c1 != c2) {
		    HashSet<Character> set = new HashSet<Character>(); // update c2 to c1's adjcent list
		    if (adj.containsKey(c1))
			set = adj.get(c1);
		    if (!set.contains(c2)) {
			set.add(c2);
			adj.put(c1, set);
			freq.put(c2, freq.get(c2) + 1);
		    }
		    break;
		}
	    }
	}

	String result = "";
	Queue<Character> q = new LinkedList<Character>();
	for (char c : freq.keySet()) {
	    if (freq.get(c) == 0)
		q.add(c);
	}
	while (!q.isEmpty()) {
	    char c = q.poll();
	    result += c;
	    if (adj.containsKey(c)) {
		for (char c2 : adj.get(c)) {
		    freq.put(c2, freq.get(c2) - 1);
		    if (freq.get(c2) == 0)
			q.add(c2);
		}
	    }
	}

	if (result.length() != freq.size())
	    return "";
	return result;

    }
}
