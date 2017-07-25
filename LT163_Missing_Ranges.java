import java.util.*;

/*
Given a sorted integer array where the range of elements are [lower, upper] inclusive, return its missing ranges.
For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].

Array
 */
public class LT163_Missing_Ranges {
	// Super clean and simple code sample.
	// Attention1: compare current with the end
	// Attention2: from == to
	public List<String> findMissingRanges(int[] nums, int lower, int upper) {
		List<String> res = new ArrayList<>();
		for (int i : nums) {
			if (i > lower)
				res.add(lower + ((i - 1 > lower) ? "->" + (i - 1) : ""));
			if (i == upper)
				return res; // Avoid overflow
			lower = i + 1;
		}
		if (lower <= upper)
			res.add(lower + ((upper > lower) ? "->" + (upper) : ""));
		return res;

	}
}
