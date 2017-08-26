import java.util.*;

/*
 * Given a non-empty string s and an integer k, rearrange the string such that the same characters are at least distance k from each other.

All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".

Example 1:
s = "aabbcc", k = 3

Result: "abcabc"

The same letters are at least distance 3 from each other.
Example 2:
s = "aaabc", k = 3 

Answer: ""

It is not possible to rearrange the string.
Example 3:
s = "aaadbbcc", k = 2

Answer: "abacabcd"

Another possible answer is: "abcabcda"

The same letters are at least distance 2 from each other.

Hash Table, Heap, Greedy
 */
public class LT358_Rearrange_String_k_Distance_Apart {
    public String rearrangeString(String s, int k) {
	HashMap<Character, Integer> map = new HashMap<>(); // store the freq in the map
	for (char c : s.toCharArray())
	    map.put(c, map.getOrDefault(c, 0) + 1);

	PriorityQueue<Map.Entry<Character, Integer>> queue = new PriorityQueue<>(
		(a, b) -> a.getValue() != b.getValue() ? b.getValue() - a.getValue() : a.getKey() - b.getKey());

	queue.addAll(map.entrySet());

	StringBuilder sb = new StringBuilder();
	Queue<Map.Entry<Character, Integer>> tmp = new LinkedList<>(); // wait list. using 621 will have TLE
	while (!queue.isEmpty()) {
	    Map.Entry<Character, Integer> topTask = queue.poll();
	    sb.append(topTask.getKey());
	    topTask.setValue(topTask.getValue() - 1);
	    tmp.add(topTask);

	    if (tmp.size() < k)
		continue; // keep adding letters.

	    Map.Entry<Character, Integer> front = tmp.poll();
	    if (front.getValue() > 0)
		queue.offer(front);

	}

	return sb.length() == s.length() ? sb.toString() : "";
    }
}
