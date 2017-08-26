import java.util.*;

/*
 * Design a data structure that supports all following operations in average O(1) time.

Note: Duplicate elements are allowed.
insert(val): Inserts an item val to the collection.
remove(val): Removes an item val from the collection if present.
getRandom: Returns a random element from current collection of elements. The probability of each element being returned is linearly related to the number of same value the collection contains.
Example:

// Init an empty collection.
RandomizedCollection collection = new RandomizedCollection();

// Inserts 1 to the collection. Returns true as the collection did not contain 1.
collection.insert(1);

// Inserts another 1 to the collection. Returns false as the collection contained 1. Collection now contains [1,1].
collection.insert(1);

// Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
collection.insert(2);

// getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
collection.getRandom();

// Removes 1 from the collection, returns true. Collection now contains [1,2].
collection.remove(1);

// getRandom should return 1 and 2 both equally likely.
collection.getRandom();

Array, Hash Table, Design
 */
public class LT381_Insert_Delete_GetRandom_O1_Duplicates_allowed {
    ArrayList<Integer> nums;
    HashMap<Integer, Set<Integer>> locs;
    Random rand = new Random();

    /** Initialize your data structure here. */
    public LT381_Insert_Delete_GetRandom_O1_Duplicates_allowed() {
	nums = new ArrayList<Integer>();
	locs = new HashMap<Integer, Set<Integer>>();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
	boolean contain = locs.containsKey(val);
	if (!contain)
	    locs.put(val, new HashSet<Integer>());
	locs.get(val).add(nums.size());
	nums.add(val);
	return !contain;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    // ie. After insert(1), insert(1), insert(2), getRandom() should have 2/3 chance return 1 and 1/3 chance return 2.Then, remove(1), 1 and 2 should have an equal chance of being selected by
    // getRandom().
    public boolean remove(int val) {
	boolean contain = locs.containsKey(val);
	if (!contain)
	    return false;
	int loc = locs.get(val).iterator().next();
	locs.get(val).remove(loc);
	if (loc < nums.size() - 1) {
	    int lastone = nums.get(nums.size() - 1); // if loc is not last elm, swap with last elm in nums. used in line35.
	    nums.set(loc, lastone);
	    locs.get(lastone).remove(nums.size() - 1); // update location in the hashmap
	    locs.get(lastone).add(loc);
	}
	nums.remove(nums.size() - 1);
	if (locs.get(val).isEmpty())
	    locs.remove(val);
	return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
	return nums.get(rand.nextInt(nums.size()));
    }
}
