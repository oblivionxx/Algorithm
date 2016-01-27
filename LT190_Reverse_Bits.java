/*
Reverse bits of a given 32 bits unsigned integer.
For example, given input 43261596 (represented in binary as 00000010100101000001111010011100), return 964176192 (represented in binary as 00111001011110000010100101000000).

Follow up:
If this function is called many times, how would you optimize it?

Bit Manipulation
 */
public class LT190_Reverse_Bits {
	// you need treat n as an unsigned value. loop 32 bits.
	// res | (n&1). then res<<1. n>>1
    public int reverseBits(int n) {
        int res = n&1;
        for(int i=1;i<=31;i++){
            n = n>>1;
            res = res<<1;
            res = res | (n&1);
        }
        
        return res;
    }
}
