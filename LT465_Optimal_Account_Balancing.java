import java.util.*;

/*
 * A group of friends went on holiday and sometimes lent each other money. For example, Alice paid for Bill's lunch for $10. Then later Chris gave Alice $5 for a taxi ride. We can model each transaction as a tuple (x, y, z) which means person x gave person y $z. Assuming Alice, Bill, and Chris are person 0, 1, and 2 respectively (0, 1, 2 are the person's ID), the transactions can be represented as [[0, 1, 10], [2, 0, 5]].

Given a list of transactions between a group of people, return the minimum number of transactions required to settle the debt.

Note:

A transaction will be given as a tuple (x, y, z). Note that x ? y and z > 0.
Person's IDs may not be linear, e.g. we could have the persons 0, 1, 2 or we could also have the persons 0, 2, 6.
Example 1:

Input:
[[0,1,10], [2,0,5]]

Output:
2

Explanation:
Person #0 gave person #1 $10.
Person #2 gave person #0 $5.

Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.
Example 2:

Input:
[[0,1,10], [1,0,1], [1,2,5], [2,0,5]]

Output:
1

Explanation:
Person #0 gave person #1 $10.
Person #1 gave person #0 $1.
Person #1 gave person #2 $5.
Person #2 gave person #0 $5.

Therefore, person #1 only need to give person #0 $4, and all debt is settled.
 */
public class LT465_Optimal_Account_Balancing {
    public int minTransfers(int[][] transactions) {
        if (transactions == null || transactions.length == 0 || transactions[0].length == 0)
        return 0;
        Map<Integer, Integer> accountToDelta = new HashMap<Integer, Integer>();
        for (int[] transaction : transactions) {                //how much debt of each person
            int from = transaction[0];
            int to = transaction[1];
            int val = transaction[2];
            if (!accountToDelta.containsKey(from)) {
                accountToDelta.put(from, 0);
            }
            if (!accountToDelta.containsKey(to)) {
                accountToDelta.put(to, 0);
            }
            accountToDelta.put(from, accountToDelta.get(from) - val);
            accountToDelta.put(to, accountToDelta.get(to) + val);
        }
        List<Integer> deltas = new ArrayList<Integer>();
        for (int delta : accountToDelta.values()) {
            if (delta != 0)
                deltas.add(delta);
        }
        int matchCount = removeMatchDeltas(deltas);             //if have +5, -5 then can get 1. find all possible situation and remove
        return matchCount + minTransStartsFrom(deltas, 0);
    }

    private int removeMatchDeltas(List<Integer> deltas) {
        Collections.sort(deltas);
        int left = 0;
        int right = deltas.size() - 1;
        int matchCount = 0;
        while (left < right) {
            if (-1 * deltas.get(left) == deltas.get(right)) {
                deltas.remove(left);
                deltas.remove(right - 1);
                right -= 2;
                matchCount++;
            } else if (-1 * deltas.get(left) > deltas.get(right)) {
                left++;
            } else {
                right--;
            }
        }
        return matchCount;
    }

    //try out all possibilities to get minimum number of transactions??
    private int minTransStartsFrom(List<Integer> deltas, int start) {
        int result = Integer.MAX_VALUE;
        int n = deltas.size();
        while (start < n && deltas.get(start) == 0)             //some people didn't own any people. so deltas = 0
            start++;
        if (start == n)
            return 0;
        for (int i = start + 1; i < n; i++) {
            if ((long) deltas.get(i) * deltas.get(start) < 0) {
                deltas.set(i, deltas.get(i) + deltas.get(start));
                result = Math.min(result, 1 + minTransStartsFrom(deltas, start + 1));
                deltas.set(i, deltas.get(i) - deltas.get(start));
            }
        }
        return result;
    }
}
