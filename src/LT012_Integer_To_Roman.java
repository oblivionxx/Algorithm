/*
 Given an integer, convert it to a roman numeral.
 Input is guaranteed to be within the range from 1 to 3999.
 */

/*
 * Math, String
 * 1-I, 5-V, 10-X, 50-L, 100-C, 500-D, 1000-M.
 */
public class LT012_Integer_To_Roman {
    public String intToRoman(int num) {
	// keep 1, 4, 5, 9
	String res = "";
	String[] str = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
	int[] arr = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };

	for (int i = 0; num != 0; i++) {
	    while (num >= arr[i]) {
		num -= arr[i];
		res += str[i];
	    }
	}

	return res;
    }

    public String intToRomanSol2(int num) {
	String M[] = { "", "M", "MM", "MMM" };
	String C[] = { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" };
	String X[] = { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" };
	String I[] = { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" };
	return M[num / 1000] + C[(num % 1000) / 100] + X[(num % 100) / 10] + I[num % 10];
    }
}
