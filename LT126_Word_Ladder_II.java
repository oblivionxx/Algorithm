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
//	https://leetcode.com/discuss/9523/share-two-similar-java-solution-that-accpted-by-oj
//	https://leetcode.com/discuss/44110/super-fast-java-solution-two-end-bfs
//	https://leetcode.com/discuss/64808/my-concise-java-solution-based-on-bfs-and-dfs
 // use a hashmap to store words and their corresponding depths.
    HashMap<String,Integer> path = new HashMap<String,Integer>();
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        List<String> tmp = new ArrayList<>();
        Set<String> set = new HashSet<>(wordList);
        // beginWord is not in the wordlist, but the endword is ! check the endword first
        if (beginWord == null || endWord == null || !set.contains(endWord)) return res;
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
        if (path.get(beginWord) == null) return;
        tmp_path.add(beginWord);
        int nextDepth = (int) path.get(beginWord) - 1;
        for (int i = 0; i < beginWord.length(); i++) {
            char[] ca = beginWord.toCharArray();
            for (char c = 'a'; c <= 'z'; c++) {
                if (ca[i] == c) continue;
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
            if (current == end) continue;
            for (int i = 0; i < current.length(); i++) {
                char[] ca = current.toCharArray();
                for (char c = 'a'; c <= 'z'; c++) {
                    if (ca[i] == c) continue;
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
