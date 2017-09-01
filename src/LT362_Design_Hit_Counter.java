import java.util.LinkedList;
import java.util.Queue;

/*
Design a hit counter which counts the number of hits received in the past 5 minutes.

Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are being made to the system in chronological order (ie, the timestamp is monotonically increasing). You may assume that the earliest timestamp starts at 1.

It is possible that several hits arrive roughly at the same time.

Example:
HitCounter counter = new HitCounter();

// hit at timestamp 1.
counter.hit(1);

// hit at timestamp 2.
counter.hit(2);

// hit at timestamp 3.
counter.hit(3);

// get hits at timestamp 4, should return 3.
counter.getHits(4);

// hit at timestamp 300.
counter.hit(300);

// get hits at timestamp 300, should return 4.
counter.getHits(300);

// get hits at timestamp 301, should return 3.
counter.getHits(301); 
Follow up:
What if the number of hits per second could be very large? Does your design scale?

Design
 */
public class LT362_Design_Hit_Counter {
    Queue<Integer> q;

    /** Initialize your data structure here. */
    public LT362_Design_Hit_Counter() {
	q = new LinkedList<>();
    }

    /**
     * Record a hit.
     * 
     * @param timestamp
     *            - The current timestamp (in seconds granularity).
     */
    public void hit(int timestamp) {
	q.offer(timestamp);
    }

    /**
     * Return the number of hits in the past 5 minutes.
     * 
     * @param timestamp
     *            - The current timestamp (in seconds granularity).
     */
    public int getHits(int timestamp) {
	while (!q.isEmpty() && timestamp - q.peek() >= 300) // get rid of old hits
	    q.poll();
	return q.size();
    }

    // Follow up about concurrency and scale. hit 30000 in one timestamp, need lots of space to save in list. and need to delete all of them.
    // what about save all the hit in one place. think about hashmap, linkedlist for a hash.
    // no need to have a lock. will block the service
    // 定义了两个大小为300的一维数组times和hits，分别用来保存时间戳和点击数，在点击函数中，将时间戳对300取余，然后看此位置中之前保存的时间戳和当前的时间戳是否一样,说明是同一个时间戳
    // 那么对应的点击数自增1，如果不一样，说明已经过了五分钟了，那么将对应的点击数重置为1。那么在返回点击数时，我们需要遍历times数组，找出所有在5分中内的位置，然后把hits中对应位置的点击数都加起来即可，参见代码如下：
    public class HitCounter {
	private int[] times;
	private int[] hits;

	/** Initialize your data structure here. */
	public HitCounter() {
	    times = new int[300];
	    hits = new int[300];
	}

	/**
	 * Record a hit.
	 * @param timestamp - The current timestamp (in seconds granularity).
	 */
	public void hit(int timestamp) {
	    int index = timestamp % 300;
	    if (times[index] != timestamp) {
		times[index] = timestamp;
		hits[index] = 1;
	    } else {
		hits[index]++;
	    }
	}

	/**
	 * Return the number of hits in the past 5 minutes.
	 * 
	 * @param timestamp - The current timestamp (in seconds granularity).
	 */
	public int getHits(int timestamp) {
	    int total = 0;
	    for (int i = 0; i < 300; i++) {
		if (timestamp - times[i] < 300) {
		    total += hits[i];
		}
	    }
	    return total;
	}
    }
}
