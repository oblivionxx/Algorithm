import java.util.*;

/*
Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

For example,
MovingAverage m = new MovingAverage(3);
m.next(1) = 1
m.next(10) = (1 + 10) / 2
m.next(3) = (1 + 10 + 3) / 3
m.next(5) = (10 + 3 + 5) / 3

Design, Queue
 */
public class LT346_Moving_Average_from_Data_Stream {
    LinkedList<Integer> queue;
    int maxSize = 0;
    double sum = 0;

    /** Initialize your data structure here. */
    public LT346_Moving_Average_from_Data_Stream(int size) {
	queue = new LinkedList<>();
	maxSize = size;

    }

    public double next(int val) {
	if (queue.size() == maxSize)
	    sum -= queue.poll(); // O(1). dont need to loop to calculate sum
	sum += val;
	queue.add(val);
	return sum / queue.size();
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such: MovingAverage obj = new MovingAverage(size); double param_1 = obj.next(val);
 */
