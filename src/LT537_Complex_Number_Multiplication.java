/*
 * Given two strings representing two complex numbers.

You need to return a string representing their multiplication. Note i2 = -1 according to the definition.

Example 1:
Input: "1+1i", "1+1i"
Output: "0+2i"
Explanation: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i, and you need convert it to the form of 0+2i.
Example 2:
Input: "1+-1i", "1+-1i"
Output: "0+-2i"
Explanation: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i, and you need convert it to the form of 0+-2i.
Note:

The input strings will not have extra blank.
The input strings will be given in the form of a+bi, where the integer a and b will both belong to the range of [-100, 100]. And the output should be also in this form.

Math, String
 */
public class LT537_Complex_Number_Multiplication {
    public String complexNumberMultiply(String a, String b) {
	// case: if a, b exist. check indexOf + and i
	int[] partA = new int[2];
	String[] aa = a.split("\\+");
	partA[0] = Integer.valueOf(aa[0]); // if no +, aa[0] would be ""
	partA[1] = Integer.valueOf(aa[1].substring(0, aa[1].length() - 1));

	int[] partB = new int[2];
	String[] bb = b.split("\\+");
	partB[0] = Integer.valueOf(bb[0]); // if no +, aa[0] would be ""
	partB[1] = Integer.valueOf(bb[1].substring(0, bb[1].length() - 1));

	return (partA[0] * partB[0] - partA[1] * partB[1]) + "+" + (partA[0] * partB[1] + partA[1] * partB[0]) + "i";
    }
}
