import java.util.*;

/*
 * Given a non-empty array of integers, return the k most frequent elements.

For example,
Given [1,1,1,2,2,3] and k = 2, return [1,2].

Note: 
You may assume k is always valid, 1 ? k ? number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

Hash Table, Heap
 */
public class LT347_Top_K_Frequent_Elements {
    //O(n)
    public List<Integer> topKFrequent(int[] nums, int k) {
	List<Integer>[] bucket = new List[nums.length + 1];
	Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();

	for (int n : nums) {
	    frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
	}

	for (int key : frequencyMap.keySet()) {
	    int frequency = frequencyMap.get(key);
	    if (bucket[frequency] == null) {
		bucket[frequency] = new ArrayList<>();
	    }
	    bucket[frequency].add(key);
	}

	List<Integer> res = new ArrayList<>();

	for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
	    if (bucket[pos] != null) {
		res.addAll(bucket[pos]);
	    }
	}
	return res;
    }

    public List<Integer> topKFrequent2(int[] nums, int k) {
	HashMap<Integer, Integer> map = new HashMap<>(); // save int, freq
	for (int i : nums) {
	    map.put(i, map.getOrDefault(i, 0) + 1);
	}

	PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
	for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
	    pq.add(entry);
	}

	List<Integer> res = new ArrayList<>();
	while (res.size() < k) {
	    Map.Entry<Integer, Integer> entry = pq.poll();
	    res.add(entry.getKey());
	}

	return res;
    }
}
