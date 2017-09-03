/*
 * Given an integer, write an algorithm to convert it to hexadecimal. For negative integer, two’s complement method is used.

Note:

All letters in hexadecimal (a-f) must be in lowercase.
The hexadecimal string must not contain extra leading 0s. If the number is zero, it is represented by a single zero character '0'; otherwise, the first character in the hexadecimal string will not be the zero character.
The given number is guaranteed to fit within the range of a 32-bit signed integer.
You must not use any method provided by the library which converts/formats the number to hex directly.
Example 1:

Input:
26

Output:
"1a"
Example 2:

Input:
-1

Output:
"ffffffff"

Bit Manipulation
 */
public class LT405_Convert_a_Number_to_Hexadecimal {
    char[] map = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    public String toHex(int num) {
	if (num == 0)
	    return "0";
	String result = "";
	while (num != 0) {
	    result = map[(num & 15)] + result; // add before the calculated value. ~append(0,map[num%16])  ==> cannot use num%16 directly. as should use a not 10.
	    num = (num >>> 4);
	}
	return result;
    }

    // eg. 1a = 1*16+a*0 = 10000+1010(=a). 4 digit by 4 digit. O(lg_16^N)
}
