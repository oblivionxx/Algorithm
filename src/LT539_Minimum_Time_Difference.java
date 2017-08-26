import java.util.*;

/*
 * Given a list of 24-hour clock time points in "Hour:Minutes" format, find the minimum minutes difference between any two time points in the list.

Example 1:
Input: ["23:59","00:00"]
Output: 1
Note:
The number of time points in the given list is at least 2 and won't exceed 20000.
The input time is legal and ranges from 00:00 to 23:59.

String
 */
public class LT539_Minimum_Time_Difference {
    public int findMinDifference(List<String> timePoints) {
	int mm = Integer.MAX_VALUE;
	List<Integer> time = new ArrayList<>();

	for (int i = 0; i < timePoints.size(); i++) {
	    Integer h = Integer.valueOf(timePoints.get(i).substring(0, 2));
	    time.add(60 * h + Integer.valueOf(timePoints.get(i).substring(3, 5)));
	}

	Collections.sort(time, (Integer a, Integer b) -> a - b);

	for (int i = 1; i < time.size(); i++) {
	    mm = Math.min(mm, time.get(i) - time.get(i - 1));
	}

	int corner = time.get(0) + (1440 - time.get(time.size() - 1)); // 1440 = 24h
	return Math.min(mm, corner);
    }
}
