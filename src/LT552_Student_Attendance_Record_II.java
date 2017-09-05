/*
 * Given a positive integer n, return the number of all possible attendance records with length n, which will be regarded as rewardable. 
 * The answer may be very large, return it after mod 109 + 7.

A student attendance record is a string that only contains the following three characters:

'A' : Absent.
'L' : Late.
'P' : Present.
A record is regarded as rewardable if it doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).

Example 1:
Input: n = 2
Output: 8 
Explanation:
There are 8 records with length 2 will be regarded as rewardable:
"PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
Only "AA" won't be regarded as rewardable owing to more than one absent times. 
Note: The value of n won't exceed 100,000.

DP
 */
public class LT552_Student_Attendance_Record_II {
    // Total = ended with P + ended with L + ended with A. --> subProblem. end with 'A', 'P', 'L'
    // P can be added to (n-1) string end with A, P, L. P(n) = A(n - 1) + P(n - 1) + L(n - 1). n ≥ 2. A(1) = P(1) = L(1) = 1.
    // L can be added to (n-1) string end with A, P.
    // to (n-1) string end with L && (n-2) string end with A and P. (cannot have LLL)
    // L(n) = A(n - 1) + P(n - 1) + A(n - 2) + P(n - 2). n ≥ 3
    // A(1) = P(1) = 1. A(2) = 2, P(2) = 3.L(1) = 1, L(2) = 3.
    // A can be added to (n-1) string end with P, L and no A appeared before
    // A(n) = noAP(n - 1) + noAL(n - 1), n ≥ 2.
    // A(1) = 1. noAP(1) = noAL(1) = 1.
    // noAP(n) = noAP(n - 1) + noAL(n - 1), n ≥ 2. noAP(1) = noAL(1) = 1.
    // noAL(n) = noAP(n - 1) + noAP(n - 2), n ≥ 3. noAP(1) = noAL(1) = 1.noAL(2) = 2.
    // ==>A(n) = A(n - 1) + A(n - 2) + A(n - 3), n ≥ 4
    // P(1) = 1. L(1) = 1, L(2) = 3. A(1) = 1, A(2) = 2, A(3) = 4.
    public int checkRecord(int n) {
	int m = 1000000007;
	int[] A = new int[n];
	int[] P = new int[n];
	int[] L = new int[n];
	if (n == 1)
	    return 3;
	if (n == 2)
	    return 8;

	A[0] = 1;
	A[1] = 2;
	A[2] = 4;
	P[0] = 1;
	L[0] = 1;
	L[1] = 3;

	for (int i = 1; i < n; i++) {
	    A[i - 1] %= m;
	    P[i - 1] %= m;
	    L[i - 1] %= m;

	    P[i] = ((A[i - 1] + P[i - 1]) % m + L[i - 1]) % m;
	    if (i > 1) {
		L[i] = ((A[i - 1] + P[i - 1]) % m + (A[i - 2] + P[i - 2]) % m) % m;
	    }

	    if (i > 2) {
		A[i] = ((A[i - 1] + A[i - 2]) % m + A[i - 3]) % m;
	    }
	}

	return ((A[n - 1] % m + P[n - 1] % m) % m + L[n - 1] % m) % m;
    }
}
