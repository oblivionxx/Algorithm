import java.util.*;

/*
 * Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), 
 * where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. 
 * Write an algorithm to reconstruct the queue.

Note:
The number of people is less than 1,100.

Example

Input:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

Output:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]

Greedy
 */
public class LT406_Queue_Reconstruction_by_Height {
    public static int[][] reconstructQueue(int[][] people) {
	// Pick out tallest group of people and sort them in a subarray (S). Since there's no other groups of people taller than them, therefore each guy's index will be just as same as his k
	// value.For 2nd tallest group (and the rest), insert each one of them into (S) by k value. So on and so forth.
	// E.g.
	// input: [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
	// subarray after step 1: [[7,0], [7,1]]
	// subarray after step 2: [[7,0], [6,1], [7,1]] short people 6 in front of 7 doesn't violate 7. so just put shorter people at kth position
	Arrays.sort(people, (a, b) -> a[0] != b[0] ? b[0] - a[0] : a[1] - b[1]);
	List<int[]> ans = new ArrayList<>();
	for (int[] p : people) {
	    ans.add(p[1], p); // add int[] p at position p[1]
	}
	return ans.toArray(new int[0][0]);

    }
    
    public static void main(String[] args){
	int[][] people = {{7,0},{7,1}, {6,1}};
	reconstructQueue(people);
    }
}
