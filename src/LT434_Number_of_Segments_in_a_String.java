/*
 * Count the number of segments in a string, where a segment is defined to be a contiguous sequence of non-space characters.

Please note that the string does not contain any non-printable characters.

Example:

Input: "Hello, my name is John"
Output: 5

String
 */
public class LT434_Number_of_Segments_in_a_String {
    public int countSegments(String s) {
	return (s == null || s.trim().length() == 0) ? 0 : s.trim().split("\\s+").length;
    }
}
