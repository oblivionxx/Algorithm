/*
Write a function that takes an unsigned integer and returns the number of ’1' bits it has (also known as the Hamming weight).
For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011, so the function should return 3.

Bit Manipulation
 */
public class LT191_Number_of_1_Bits {
	// count hamming weigth and how many 1s
	public int hammingWeight(int n) {
		int count = 0;
		while (n != 0) {
			n = n & (n - 1);
			count++;
			// if using n>>=1. then cannot pass time limit.
			// 上面的方法巧妙的使用了(n-1) & n，这个有什么用？我们来看下具体的例子：假设n＝ 1111000111000 那 n-1 =
			// 1111000110111, (n-1) & n = 1111000110000，刚好把最后一个1给干掉了。也就是说，
			// (n-1)&n 刚好会从最后一位开始，每次会干掉一个1.这样速度就比下面的快了。有几个1，执行几次
		}

		return count;
	}

	public int hammingWeight2(int n) {
		int count = 0;
		while (n != 0) {
			if ((n & 1) == 1)
				count++;
			n >>= 1;
		}

		return count;
	}

}
