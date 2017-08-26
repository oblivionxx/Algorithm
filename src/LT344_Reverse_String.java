/*
 * Write a function that takes a string as input and returns the string reversed.

Example:
Given s = "hello", return "olleh".

Two Pointers, String
 */
public class LT344_Reverse_String {
    public String reverseString(String s) {
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString();
    }
}
