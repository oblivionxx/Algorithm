
/*
Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the word list
For example,

Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.

 */
import java.util.*;

public class LT127_Word_Ladder {
    // BFS. curWord -> modify a letter * 26 --> if in the dict add to queue.
    // Complexity explanation http://blog.csdn.net/linhuanmars/article/details/23029973
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
	Set<String> dic = new HashSet<>(wordList);
	Queue<String> queue = new LinkedList<String>();
	queue.add(beginWord);
	int level = 0;

	while (!queue.isEmpty()) {
	    int size = queue.size();
	    for (int i = 0; i < size; i++) {
		String cur = queue.remove();
		if (cur.equals(endWord)) {
		    return level + 1;
		}
		for (int j = 0; j < cur.length(); j++) {
		    char[] word = cur.toCharArray();
		    for (char ch = 'a'; ch < 'z'; ch++) { // change a letter in the curWord.
			word[j] = ch;
			String check = new String(word);
			if (!check.equals(cur) && dic.contains(check)) { // if new curWord is in the list
			    queue.add(check);
			    dic.remove(check); // only used once.
			}
		    }
		}
	    }
	    level++;
	}
	return 0;
    }
}
