import java.util.*;

import utils.Interval;

/*
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return 2.

Sort, Greedy, Heap
 */
public class LT253_Meeting_Rooms_II {
    public int minMeetingRooms(Interval[] intervals) {
	int room = 0;
	Arrays.sort(intervals, (i1, i2) -> (i1.start - i2.start));
	PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // peek is the
								// smallest end.
	for (int i = 0; i < intervals.length; i++) {
	    if (minHeap.size() == 0) {
		minHeap.add(intervals[i].end);
		room++;
		continue;
	    }
	    if (minHeap.peek() <= intervals[i].start) {
		// update heap to new interval end.
		minHeap.poll();
		minHeap.add(intervals[i].end);
	    } else { // has collision
		minHeap.add(intervals[i].end);
		room++;
	    }
	}

	return room;
    }

    public int minMeetingRooms2(Interval[] intervals) {
	if (intervals == null || intervals.length == 0) {
	    return 0;
	}

	int len = intervals.length;
	int[] startTime = new int[len];
	int[] endTime = new int[len];

	for (int i = 0; i < len; i++) {
	    Interval curr = intervals[i];
	    startTime[i] = curr.start;
	    endTime[i] = curr.end;
	}

	// Sort the start and end time
	Arrays.sort(startTime);
	Arrays.sort(endTime);

	int activeMeetings = 0;
	int numMeetingRooms = 0;

	int i = 0;
	int j = 0;

	while (i < len && j < len) {
	    if (startTime[i] < endTime[j]) {
		activeMeetings++;
		numMeetingRooms = Math.max(numMeetingRooms, activeMeetings);
		i++;
	    } else {
		activeMeetings--;
		j++;
	    }
	}

	return numMeetingRooms;
    }
}
