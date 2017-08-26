import java.util.Arrays;

/*
Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.
According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."
For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively. Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, his h-index is 3.
Note: If there are several possible values for h, the maximum one is taken as the h-index.

Hint:
An easy approach is to sort the array first.
What are the possible values of h-index?
A faster approach is to use extra space.

HashTable, Sort
 */
public class LT274_H_Index {
    // 1. counting sort
    public int hIndex(int[] citations) {
	if (citations.length <= 0)
	    return 0;
	int res = 0;
	int len = citations.length;
	int[] countarray = new int[len + 1];
	for (int i = 0; i < len; i++) {
	    if (citations[i] >= len) {
		countarray[len]++;
	    } else {
		countarray[citations[i]]++;
	    }
	}
	if (countarray[len] >= len)
	    return len;
	for (int i = len - 1; i >= 0; i--) {
	    countarray[i] = countarray[i] + countarray[i + 1];
	    if (countarray[i] >= i)
		return i; // remember, here i is the h-index. which is some value of citation[]. not index.
	}
	return res;
    }

    // 2. Sort and check from bwd
    public int hIndex2(int[] citations) {
	Arrays.sort(citations);
	int hIndex = 0;
	for (int i = citations.length - 1; i >= 0; i--) {
	    if (citations[i] <= hIndex)
		return hIndex;
	    hIndex++;
	}

	return hIndex;
    }
}
