import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

Examples: 
[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.
For example:

add(1)
add(2)
findMedian() -> 1.5
add(3) 
findMedian() -> 2

Heap, Design
 */
public class LT295_Find_Median_from_Data_Stream {
    /*
     * 维护MaxHeap + MinHeap 大顶堆中存储的元素 均不大于 小顶堆中的元素 MaxHeap.size() ==
     * MinHeap.size()，或者 MaxHeap.size() == MinHeap.size() + 1
     * 
     * 当MaxHeap.size() == MinHeap.size() + 1时，中位数就是MaxHeap的堆顶元素 当MaxHeap.size()
     * == MinHeap.size()时，中位数就是MaxHeap堆顶元素与MinHeap堆顶元素的均值
     * 
     * PriorityQueue is minHeap, maxHeap可以通过对元素的值 * -1实现
     */

    PriorityQueue<Integer> minheap = new PriorityQueue<>(); // store bigger
							    // elements
    PriorityQueue<Integer> maxheap = new PriorityQueue<>(new Comparator<Integer>() {
	public int compare(Integer i1, Integer i2) {
	    return i2 - i1;
	}
    }); // store smaller elements

    // Adds a number into the data structure. O(lgn)
    public void addNum(int num) {
	if (maxheap.size() == 0 || num <= maxheap.peek()) { // maxheap store
							    // smaller elements
	    // 如果最大堆大小超过最小堆，则要平衡一下
	    if (maxheap.size() > minheap.size()) {
		minheap.offer(maxheap.poll());
	    }
	    maxheap.offer(num);
	} else if (minheap.size() == 0 || num > minheap.peek()) {
	    if (minheap.size() > maxheap.size()) {
		maxheap.offer(minheap.poll());
	    }
	    minheap.offer(num);
	    // 数字在两个堆顶之间的情况. maxheap.peek() <= num < minheap.peek()
	} else {
	    if (maxheap.size() <= minheap.size()) {
		maxheap.offer(num);
	    } else {
		minheap.offer(num);
	    }
	}
    }

    // Returns the median of current data stream. O(1)
    public double findMedian() {
	if (maxheap.size() > minheap.size()) {
	    return maxheap.peek();
	} else if (maxheap.size() < minheap.size()) {
	    return minheap.peek();
	} else if (maxheap.isEmpty() && minheap.isEmpty()) {
	    return 0;
	} else {
	    return (maxheap.peek() + minheap.peek()) / 2.0;
	}
    }

    // Your MedianFinder object will be instantiated and called as such:
    // MedianFinder mf = new MedianFinder();
    // mf.addNum(1);
    // mf.findMedian();

    // simplify version
    // max queue is always larger or equal to min queue
    PriorityQueue<Integer> min = new PriorityQueue<>();
    PriorityQueue<Integer> max = new PriorityQueue<>(1000, Collections.reverseOrder());

    // Adds a number into the data structure.
    public void addNum1(int num) {
	max.offer(num);
	min.offer(max.poll());
	if (max.size() < min.size()) {
	    max.offer(min.poll());
	}
    }

    // Returns the median of current data stream
    public double findMedian1() {
	if (max.size() == min.size())
	    return (max.peek() + min.peek()) / 2.0;
	else
	    return max.peek();
    }
}
