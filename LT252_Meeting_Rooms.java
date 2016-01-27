/*
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return false.

Sort. check for overlap
 */
import java.util.*;	
public class LT252_Meeting_Rooms {
	public boolean canAttendMeetings(Interval[] intervals) {
		Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                int r = o1.start - o2.start;
                return r==0? o1.end - o2.end : r;
            }
        });
        
        for(int i=1;i<intervals.length;i++) {
            Interval t1 = intervals[i-1];
            Interval t2 = intervals[i];
            if(t1.end>t2.start) return false;
        }
        return true;
    }
}
