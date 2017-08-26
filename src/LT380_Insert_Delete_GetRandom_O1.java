import java.util.*;

/*
 * Design a data structure that supports all following operations in average O(1) time.

insert(val): Inserts an item val to the set if not already present.
remove(val): Removes an item val from the set if present.
getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
Example:

// Init an empty set.
RandomizedSet randomSet = new RandomizedSet();

// Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomSet.insert(1);

// Returns false as 2 does not exist in the set.
randomSet.remove(2);

// Inserts 2 to the set, returns true. Set now contains [1,2].
randomSet.insert(2);

// getRandom should return either 1 or 2 randomly.
randomSet.getRandom();

// Removes 1 from the set, returns true. Set now contains [2].
randomSet.remove(1);

// 2 was already in the set, so return false.
randomSet.insert(2);

// Since 2 is the only number in the set, getRandom always return 2.
randomSet.getRandom();

Array, Hash Table, Design
 */
public class LT380_Insert_Delete_GetRandom_O1 {
    ArrayList<Integer> nums; // just use arraylist. problem is remove(val) takes O(n)
    HashMap<Integer, Integer> map;
    Random rand = new Random();

    /** Initialize your data structure here. */
    public LT380_Insert_Delete_GetRandom_O1() {
	nums = new ArrayList<Integer>();
	map = new HashMap<Integer, Integer>(); // val, index
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
	if (map.containsKey(val))
	    return false;
	map.put(val, nums.size());
	nums.add(val);
	return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
	// remove(m) swap num and last value. always remove last one. ->O(1)
	if (!map.containsKey(val))
	    return false;
	int loc = map.get(val);
	if (loc < nums.size() - 1) { // not the last one than swap the last one with this val
	    int lastone = nums.get(nums.size() - 1);
	    nums.set(loc, lastone); // update in nums
	    map.put(lastone, loc); // update in hashmap
	}
	map.remove(val);
	nums.remove(nums.size() - 1);
	return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
	return nums.get(rand.nextInt(nums.size()));
    }
}
