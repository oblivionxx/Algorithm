/*
This is a follow up of Shortest Word Distance. The only difference is now word1 could be the same as word2.

Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

word1 and word2 may be the same and they represent two individual words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = “makes”, word2 = “coding”, return 1.
Given word1 = "makes", word2 = "makes", return 3.

Note:
You may assume word1 and word2 are both in the list.

Array
 */
public class LT245_Shortest_Word_Distance_III {
    public int shortestWordDistance(String[] words, String word1, String word2) {
	if (!word1.equals(word2))
	    return shortestDistanceDistinct(words, word1, word2);
	int pre = -1;
	int cur = -1;
	int res = Integer.MAX_VALUE;

	for (int i = 0; i < words.length; i++) {
	    if (words[i].equals(word1))
		cur = i;

	    if (pre != -1 && cur != -1 && pre != cur)
		res = Math.min(res, Math.abs(cur - pre));

	    pre = cur;
	}

	return res;
    }

    public int shortestDistanceDistinct(String[] words, String word1, String word2) {
	int res = words.length;
	int index1 = -1;
	int index2 = -1;
	for (int i = 0; i < words.length; i++) {
	    if (words[i].equals(word1))
		index1 = i;
	    else if (words[i].equals(word2))
		index2 = i;
	    if (index1 != -1 && index2 != -1) {
		res = Math.min(res, Math.abs(index1 - index2));
	    }
	}

	return res;
    }
}
