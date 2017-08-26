import java.util.HashMap;

/*
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
Write a function to determine if a number is strobogrammatic. The number is represented as a string.
For example, the numbers "69", "88", and "818" are all strobogrammatic.

HashTable, Math
 */
public class LT246_Strobogrammatic_Number {
    public boolean isStrobogrammatic(String num) {
	HashMap<Character, Character> map = new HashMap<>();
	map.put('8', '8');
	map.put('1', '1');
	map.put('6', '9');
	map.put('9', '6');
	map.put('0', '0');

	char[] arr = num.toCharArray();
	int left = 0;
	int right = arr.length - 1;

	while (left <= right) {
	    if (!map.containsKey(arr[left]) || map.get(arr[left]) != arr[right])
		return false;

	    left++;
	    right--;
	}
	return true;
    }
}
