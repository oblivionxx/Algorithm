/*
 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

Given two integers x and y, calculate the Hamming distance.

Note:
0 ≤ x, y < 231.

Example:

Input: x = 1, y = 4

Output: 2

Explanation:
1   (0 0 0 1)
4   (0 1 0 0)
       ?   ?

The above arrows point to positions where the corresponding bits are different.

Bit Manipulation
 */
public class LT461_Hamming_Distance {
    public int hammingDistance(int x, int y) {
        int XOR =  x^y;      //XOR count 1s
        int count = 0;
        while(XOR!=0){
            count++;
            XOR = XOR&(XOR-1);      //remove right most bit 1
        }
        
        return count;
    }
}
