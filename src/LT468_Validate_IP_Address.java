/*
 * Write a function to check whether an input string is a valid IPv4 address or IPv6 address or neither.

IPv4 addresses are canonically represented in dot-decimal notation, which consists of four decimal numbers, each ranging from 0 to 255, separated by dots ("."), e.g.,172.16.254.1;

Besides, leading zeros in the IPv4 is invalid. For example, the address 172.16.254.01 is invalid.

IPv6 addresses are represented as eight groups of four hexadecimal digits, each group representing 16 bits. The groups are separated by colons (":"). For example, the address 2001:0db8:85a3:0000:0000:8a2e:0370:7334 is a valid one. Also, we could omit some leading zeros among four hexadecimal digits and some low-case characters in the address to upper-case ones, so 2001:db8:85a3:0:0:8A2E:0370:7334 is also a valid IPv6 address(Omit leading zeros and using upper cases).

However, we don't replace a consecutive group of zero value with a single empty group using two consecutive colons (::) to pursue simplicity. For example, 2001:0db8:85a3::8A2E:0370:7334 is an invalid IPv6 address.

Besides, extra leading zeros in the IPv6 is also invalid. For example, the address 02001:0db8:85a3:0000:0000:8a2e:0370:7334 is invalid.

Note: You may assume there is no extra space or special characters in the input string.

Example 1:
Input: "172.16.254.1"

Output: "IPv4"

Explanation: This is a valid IPv4 address, return "IPv4".
Example 2:
Input: "2001:0db8:85a3:0:0:8A2E:0370:7334"

Output: "IPv6"

Explanation: This is a valid IPv6 address, return "IPv6".
Example 3:
Input: "256.256.256.256"

Output: "Neither"

Explanation: This is neither a IPv4 address nor a IPv6 address.

String
 */
public class LT468_Validate_IP_Address {
    public String validIPAddress(String IP) {
	if (isValidIPv4(IP))
	    return "IPv4";
	else if (isValidIPv6(IP))
	    return "IPv6";
	else
	    return "Neither";
    }

    private boolean isValidIPv4(String IP) {
	String[] tokens = IP.split("\\.", -1);
	if (tokens.length != 4)
	    return false;
	for (String token : tokens) {
	    if (!isValidIPv4Token(token))
		return false;
	}
	return true;
    }

    private boolean isValidIPv4Token(String s) {
	if (s.length() == 0 || s.length() > 3)
	    return false;
	if (s.charAt(0) == '0')
	    return s.equals("0");
	int foo = 0;
	try {
	    foo = Integer.parseInt(s);
	} catch (NumberFormatException e) { // string consisting letter
	    return false;
	}

	return foo > 0 && foo <= 255;
    }

    private boolean isValidIPv6(String IP) {
	String[] tokens = IP.split("\\:", -1); // so that if first or last character is : , including an empty token
	if (tokens.length != 8)
	    return false;
	for (String token : tokens) {
	    if (!isValidIPv6Token(token))
		return false;
	}
	return true;
    }

    private boolean isValidIPv6Token(String s) {
	if (s.length() == 0 || s.length() > 4)
	    return false;
	char[] chars = s.toCharArray();
	for (char c : chars) {
	    boolean isUppercaseAF = c >= 65 && c <= 70; // only use abcdef
	    boolean isLowerCaseAF = c >= 97 && c <= 102;
	    if (!(Character.isDigit(c) || isUppercaseAF || isLowerCaseAF))
		return false;
	}
	return true;
    }
}
