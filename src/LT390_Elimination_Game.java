/*
 * There is a list of sorted integers from 1 to n. Starting from left to right, remove the first number and every other number afterward until you reach the end of the list.

Repeat the previous step again, but this time from right to left, remove the right most number and every other number from the remaining numbers.

We keep repeating the steps again, alternating left to right and right to left, until a single number remains.

Find the last number that remains starting with a list of length n.

Example:

Input:
n = 9,
1 2 3 4 5 6 7 8 9
2 4 6 8
2 6
6

Output:
6

 */
public class LT390_Elimination_Game {
    public int lastRemaining(int n) {
	boolean left = true; // count from left. remove index = 0,2,4...from left. else remove index=0,2,4...from right
	int countRemain = n;
	int head = 1; // track head element
	int step = 1;
	while (countRemain > 1) {
	    if (left || countRemain % 2 == 1) {
		// When will head be updated? if we move from left or we move from right and the total remaining number % 2 == 1
		// like 2 4 6 8 10, we move from 10, we will take out 10, 6 and 2, head is deleted and move to 4
		// like 2 4 6 8 10 12, we move from 12, we will take out 12, 8, 4, head is still remaining 2
		head += step;
	    }
	    countRemain /= 2;
	    step *= 2; // double step
	    left = !left;
	}

	return head;
    }
}
