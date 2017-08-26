/*
 * You are given two jugs with capacities x and y litres. There is an infinite amount of water supply available. You need to determine whether it is possible to measure exactly z litres using these two jugs.

If z liters of water is measurable, you must have z liters of water contained within one or both buckets by the end.

Operations allowed:

Fill any of the jugs completely with water.
Empty any of the jugs.
Pour water from one jug into another till the other jug is completely full or the first jug itself is empty.
Example 1: (From the famous "Die Hard" example)

Input: x = 3, y = 5, z = 4
Output: True
Example 2:

Input: x = 2, y = 6, z = 5
Output: False

Math
 */
public class LT365_Water_and_Jug_Problem {
    public boolean canMeasureWater(int x, int y, int z) {
	// limit brought by the statement that water is finallly in one or both buckets
	if (x + y < z)
	    return false;
	// case x or y is zero
	if (x == z || y == z || x + y == z)
	    return true;

	/// get GCD, then we can use the property of Bézout's identity. ax+by=z 有整数解时当且仅当z是gcd的倍数
	return z % GCD(x, y) == 0;
    }

    public int GCD(int a, int b) {
	while (b != 0) {
	    int temp = b;
	    b = a % b;
	    a = temp;
	}
	return a;
    }

    // https://discuss.leetcode.com/topic/49238/math-solution-java-solution/2
}
