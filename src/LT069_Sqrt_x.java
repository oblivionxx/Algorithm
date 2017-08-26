/*
 Implement int sqrt(int x).
 Compute and return the square root of x.
 */
/*
 * Math, Binary Search
 */
public class LT069_Sqrt_x {
    // 1. Newton method.
    public int mySqrt1(int x) {
	if (x == 0)
	    return 0;
	double lastY = 0;
	double y = 1;
	while (y != lastY) {
	    lastY = y;
	    y = (y + x / y) / 2;
	}
	return (int) (y);
    }

    public double mySqrt2(int x) {
	if (x == 0)
	    return 0;
	double epsilon = 1e-15; // relative error tolerance
	double y = x; // estimate of the square root of c

	while (Math.abs(y - x / y) > epsilon * y) {
	    y = (x / y + y) / 2.0;
	}

	return y;
    }

    // 2. Binary Search. O(lgn), O(1)
    public int mySqrt3(int x) {
	if (x < 0)
	    return -1;
	if (x == 0)
	    return 0;
	int left = 1, right = x / 2 + 1;
	while (left <= right) {
	    int mid = left + (right - left) / 2;
	    if (x / mid == mid)
		return mid;
	    else if (x / mid < mid)
		right = mid - 1;
	    else
		left = mid + 1;
	}

	return right;
    }
}
