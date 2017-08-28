import java.util.*;

/*
Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the word list
For example,

Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]
Return
  [
    ["hit","hot","dot","dog","cog"],
    ["hit","hot","lot","log","cog"]
  ]
Note:
All words have the same length.
All words contain only lowercase alphabetic characters.

Array, Backtracking, BFS, String
 */
public class LT126_Word_Ladder_II {
    // https://leetcode.com/discuss/9523/share-two-similar-java-solution-that-accpted-by-oj
    // https://leetcode.com/discuss/44110/super-fast-java-solution-two-end-bfs
    // https://leetcode.com/discuss/64808/my-concise-java-solution-based-on-bfs-and-dfs
    // use a hashmap to store words and their corresponding depths.
    // 1) BFS过程与Word Ladder类似，但是在BFS过程中利用一个HashMap(distance)记录每个单词距start的最短距离，另一个HashMap(map)记录每个单词是由哪些单词变换一步得到的。
    // 2)
    // DFS过程类似Permutation，从end出发，逐个寻找其上一节点直到start。其中可能的上一节点可以由1）中的map得到，但是只有距离比当前节点距离小1的上一节点才是合法的（保证其一定在end和start之间的最短路径上，即找到的下一个点一定比当前点离start更近，这样防止搜到的下一个点向外走），距离可以由1）中的distance得到。当到达start时即找到一种解，因为是从end出发，所以要reverse成正序后再加入res，然后reverse回逆序后删除刚加入元素，再回溯寻找下一个解。
    // 注意：在dfs获取next的list的时候，需要判断curt是否在map中，否则可能得到null而造成之后的运行时异常。比如start="hot"，end="dog"，dict={"hot", "dog"}，这里在bfs中就找不到hot和dog的next，因此没有元素会被加到map和distance中。在lintcode里可以过，但是在leetcode里过不了。
    HashMap<String, Integer> path = new HashMap<String, Integer>();

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
	List<List<String>> res = new ArrayList<>();
	List<String> tmp = new ArrayList<>();
	Set<String> set = new HashSet<>(wordList);
	// beginWord is not in the wordlist, but the endword is ! check the endword first
	if (beginWord == null || endWord == null || !set.contains(endWord))
	    return res;
	bfs(beginWord, endWord, set);
	dfs(endWord, beginWord, set, tmp, res);
	return res;
    }

    private void dfs(String beginWord, String endWord, Set<String> set, List<String> tmp_path, List<List<String>> res) {
	if (beginWord.equals(endWord)) {
	    tmp_path.add(beginWord);
	    Collections.reverse(tmp_path);
	    res.add(tmp_path);
	    return;
	}
	if (path.get(beginWord) == null)
	    return;
	tmp_path.add(beginWord);
	int nextDepth = (int) path.get(beginWord) - 1;
	for (int i = 0; i < beginWord.length(); i++) {
	    char[] ca = beginWord.toCharArray();
	    for (char c = 'a'; c <= 'z'; c++) {
		if (ca[i] == c)
		    continue;
		ca[i] = c;
		String newWord = new String(ca);
		if (path.get(newWord) != null && path.get(newWord) == nextDepth) {
		    List<String> new_tmp_path = new ArrayList<>(tmp_path);
		    dfs(newWord, endWord, set, new_tmp_path, res);
		}
	    }
	}
    }

    private void bfs(String start, String end, Set<String> set) {
	Queue<String> queue = new LinkedList<>();
	queue.add(start);
	path.put(start, 0);
	String current;
	while (!queue.isEmpty()) {
	    current = queue.poll();
	    if (current == end)
		continue;
	    for (int i = 0; i < current.length(); i++) {
		char[] ca = current.toCharArray();
		for (char c = 'a'; c <= 'z'; c++) {
		    if (ca[i] == c)
			continue;
		    ca[i] = c;
		    String newWord = new String(ca);
		    // if newWord is the same as the endword or wordlist contains newWord
		    if (newWord.equals(end) || set.contains(newWord)) {
			if (path.get(newWord) == null) {
			    int depth = path.get(current);
			    path.put(newWord, depth + 1);
			    queue.add(newWord);
			}
		    }
		}
	    }
	}
    }
}
