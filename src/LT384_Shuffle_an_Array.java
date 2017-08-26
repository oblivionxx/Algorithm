import java.util.*;

/*
 * Shuffle a set of numbers without duplicates.

Example:

// Init an array with set 1, 2, and 3.
int[] nums = {1,2,3};
Solution solution = new Solution(nums);

// Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
solution.shuffle();

// Resets the array back to its original configuration [1,2,3].
solution.reset();

// Returns the random shuffling of array [1,2,3].
solution.shuffle();

 */
public class LT384_Shuffle_an_Array {
    private int[] nums;
    private Random random = null;

    public LT384_Shuffle_an_Array(int[] nums) {
	this.nums = nums;
	random = new Random(System.currentTimeMillis()); // avoid same seeds
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
	return nums;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
	int[] ans = Arrays.copyOf(nums, nums.length); // create a copy
	for (int i = 1; i < nums.length; i++) {
	    int swapIndex = random.nextInt(i + 1); // generate a random number within visited elements including current index.
	    // nextInt(j + 1) returns a random num between [0, j]. By nextInt(j), you never get a chance to return the original order array.
	    swap(ans, i, swapIndex); // swap the index
	}
	return ans;
    }

    private void swap(int[] a, int i, int j) {
	int t = a[i];
	a[i] = a[j];
	a[j] = t;
    }
}
