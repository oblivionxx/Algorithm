/*
 * You have n super washing machines on a line. Initially, each washing machine has some dresses or is empty.

For each move, you could choose any m (1 ? m ? n) washing machines, and pass one dress of each washing machine to one of its adjacent washing machines at the same time .

Given an integer array representing the number of dresses in each washing machine from left to right on the line, you should find the minimum number of moves to make all the washing machines have the same number of dresses. If it is not possible to do it, return -1.

Example1

Input: [1,0,5]

Output: 3

Explanation: 
1st move:    1     0 <-- 5    =>    1     1     4
2nd move:    1 <-- 1 <-- 4    =>    2     1     3    
3rd move:    2     1 <-- 3    =>    2     2     2   
Example2

Input: [0,3,0]

Output: 2

Explanation: 
1st move:    0 <-- 3     0    =>    1     2     0    
2nd move:    1     2 --> 0    =>    1     1     1     
Example3

Input: [0,2,0]

Output: -1

Explanation: 
It's impossible to make all the three washing machines have the same number of dresses. 
Note:
The range of n is [1, 10000].
The range of dresses number in a super washing machine is [0, 1e5].

DP, Math
 */
public class LT517_Super_Washing_Machines {
    // your machines[] is [0,0,11,5]. So your total is 16 and the target value for each machine is 4. Convert the machines array to a kind of gain/lose array, we get: [-4,-4,7,1]
    // To make the 1st machines 0, you need to give all its "load" to the 2nd machines. So we get: [0,-8,7,1] then: [0,0,-1,1] lastly: [0,0,0,0], done.
    // compare. abs(min) and max. ==>8
    public int findMinMoves(int[] machines) {
	int total = 0;
	for (int i : machines) {
	    total += i;
	}
	if (total % machines.length != 0)
	    return -1;

	int avg = total / machines.length, cnt = 0, max = 0;
	for (int load : machines) {
	    cnt += load - avg; // load-avg forms the gain/loss array. cnt(is negative) maximum closes need to move for one machine.
	    max = Math.max(Math.max(max, Math.abs(cnt)), load - avg);
	}
	return max;
    }
    // https://discuss.leetcode.com/topic/79923/c-16ms-o-n-solution-with-trivial-proof/2 --> more easy to understand solution. redo this
}
