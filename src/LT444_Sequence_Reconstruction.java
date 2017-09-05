import java.util.*;

/*
 * Check whether the original sequence org can be uniquely reconstructed from the sequences in seqs. 
 * The org sequence is a permutation of the integers from 1 to n, with 1 ≤ n ≤ 104. 
 * Reconstruction means building a shortest common supersequence of the sequences in seqs 
 * (i.e., a shortest sequence so that all sequences in seqs are subsequences of it). 
 * Determine whether there is only one sequence that can be reconstructed from seqs and it is the org sequence.

Example 1:

Input:
org: [1,2,3], seqs: [[1,2],[1,3]]

Output:
false

Explanation:
[1,2,3] is not the only one sequence that can be reconstructed, because [1,3,2] is also a valid sequence that can be reconstructed.
Example 2:

Input:
org: [1,2,3], seqs: [[1,2]]

Output:
false

Explanation:
The reconstructed sequence can only be [1,2].
Example 3:

Input:
org: [1,2,3], seqs: [[1,2],[1,3],[2,3]]

Output:
true

Explanation:
The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].
Example 4:

Input:
org: [4,1,5,2,6,3], seqs: [[5,2,6,3],[4,1,5,2]]

Output:
true
UPDATE (2017/1/8):
The seqs parameter had been changed to a list of list of strings (instead of a 2d array of strings). Please reload the code definition to get the latest changes.

Graph, Topological Sort
 */
public class LT444_Sequence_Reconstruction {
    // The basic idea is to count how many numbers are smaller(self include) than the current number. We then compare this count to the org.
    public boolean sequenceReconstruction(int[] org, int[][] seqs) {
	int len = org.length;
	int[] map = new int[len + 1];// map number to its index
	Arrays.fill(map, -1);
	int[] memo = new int[org.length];// count how many numbers are smaller(on the right)
	for (int i = 0; i < len; i++) {
	    map[org[i]] = i;
	}
	for (int[] seq : seqs) {
	    if (seq.length == 0)
		continue;
	    int prev = seq[0];
	    if (prev <= 0 || prev > len || map[prev] == -1)
		return false;
	    for (int i = 1; i < seq.length; i++) {
		int curr = seq[i];
		if (curr <= 0 || curr > len || map[curr] == -1)
		    return false;
		memo[map[prev]] = Math.max(memo[map[prev]], len - map[curr] + 1);
		prev = curr;
	    }
	    memo[map[prev]] = Math.max(memo[map[prev]], 1);
	}
	for (int i = 0; i < memo.length; i++) {
	    if (memo[i] != len - i)
		return false;
	}
	return true;
    }

    public boolean sequenceReconstruction2(int[] org, List<List<Integer>> seqs) {
	Map<Integer, Set<Integer>> map = new HashMap<>();
	Map<Integer, Integer> indegree = new HashMap<>();
	for (List<Integer> seq : seqs) {
	    for (int i : seq) {
		map.putIfAbsent(i, new HashSet<>());
		indegree.putIfAbsent(i, 0);
	    }
	}

	for (List<Integer> seq : seqs) {
	    if (seq.size() == 1)
		continue;
	    for (int i = 1; i < seq.size(); i++)
		if (map.get(seq.get(i - 1)).add(seq.get(i)))
		    indegree.put(seq.get(i), indegree.get(seq.get(i)) + 1);
	}

	if (org.length != indegree.size())
	    return false;

	Queue<Integer> q = new ArrayDeque<>();
	for (int key : indegree.keySet())
	    if (indegree.get(key) == 0)
		q.add(key);

	int cnt = 0;
	while (q.size() == 1) {
	    for (int next : map.get(q.poll())) {
		indegree.put(next, indegree.get(next) - 1);
		if (indegree.get(next) == 0)
		    q.add(next);
	    }
	    cnt++;
	}

	return cnt == indegree.size();
    }
}
