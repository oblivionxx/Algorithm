import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/*
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.

Array, Union Find
 */
public class LT128_Longest_Consecutive_Sequence {
    public int longestConsecutive(int[] nums) {
	if (nums == null || nums.length == 0)
	    return 0;
	HashSet<Integer> set = new HashSet<>();

	// put all numbers into set.
	for (int i = 0; i < nums.length; i++) {
	    set.add(nums[i]);
	}

	int res = 1;

	for (int elm : nums) {
	    if (!set.contains(elm))
		continue;
	    int left = elm - 1;
	    int right = elm + 1;
	    int localMax = 1;

	    // this two steps will definitely find for one consecutive sequence
	    while (set.contains(left)) {
		set.remove(left);
		localMax++;
		left--;
	    }

	    while (set.contains(right)) {
		set.remove(right);
		localMax++;
		right++;
	    }

	    res = Math.max(res, localMax);
	}

	return res;
    }

    // UF.
    public int longestConsecutive2(int[] nums) {
	UF uf = new UF(nums.length);
	Map<Integer, Integer> map = new HashMap<Integer, Integer>(); // <value,index>
	for (int i = 0; i < nums.length; i++) {
	    if (map.containsKey(nums[i])) {
		continue;
	    }
	    map.put(nums[i], i);
	    if (map.containsKey(nums[i] + 1)) {
		uf.union(i, map.get(nums[i] + 1));
	    }
	    if (map.containsKey(nums[i] - 1)) {
		uf.union(i, map.get(nums[i] - 1));
	    }
	}
	return uf.maxUnion();
    }

    class UF {
	private int[] list;

	public UF(int n) {
	    list = new int[n];
	    for (int i = 0; i < n; i++) {
		list[i] = i;
	    }
	}

	private int root(int i) {
	    while (i != list[i]) {
		list[i] = list[list[i]];
		i = list[i];
	    }
	    return i;
	}

	public boolean connected(int i, int j) {
	    return root(i) == root(j);
	}

	public void union(int p, int q) {
	    int i = root(p);
	    int j = root(q);
	    list[i] = j;
	}

	// returns the maxium size of union
	public int maxUnion() { // O(n)
	    int[] count = new int[list.length];
	    int max = 0;
	    for (int i = 0; i < list.length; i++) {
		count[root(i)]++;
		max = Math.max(max, count[root(i)]);
	    }
	    return max;
	}
    }
}
