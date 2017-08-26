import java.util.HashMap;

/*
Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

Examples:
pattern = "abba", str = "dog cat cat dog" should return true.
pattern = "abba", str = "dog cat cat fish" should return false.
pattern = "aaaa", str = "dog cat cat dog" should return false.
pattern = "abba", str = "dog dog dog dog" should return false.
Notes:
You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.

HashTable
 */
public class LT290_Word_Pattern {
    public boolean wordPattern(String pattern, String str) {
	HashMap<String, Character> map1 = new HashMap<String, Character>();
	HashMap<Character, String> map2 = new HashMap<Character, String>();

	char[] charArray = pattern.toCharArray();
	String[] array = str.split(" ");
	if (charArray.length != array.length)
	    return false;

	for (int i = 0; i < array.length; i++) {
	    if (map1.containsKey(array[i])) {
		if (!map1.get(array[i]).equals(charArray[i]))
		    return false;
	    }

	    if (map2.containsKey(charArray[i])) {
		if (!map2.get(charArray[i]).equals(array[i]))
		    return false;
	    }

	    map1.put(array[i], charArray[i]);
	    map2.put(charArray[i], array[i]);
	}

	return true;
    }
}
