import java.util.HashMap;

/*
Design and implement a TwoSum class. It should support the following operations: add and find.

add - Add the number to an internal data structure.
find - Find if there exists any pair of numbers which sum is equal to the value.

For example,
add(1); add(3); add(5);
find(4) -> true
find(7) -> false

HashTable, Design
 */
public class LT170_Two_Sum_III_Data_Structure_Design {
	//similar idea to Two Sum I. but init constructor with map.
	//store number and occurrence.
	HashMap<Integer, Integer> map = new HashMap<>();

    // Add the number to an internal data structure.
	public void add(int number) {
	    if(!map.containsKey(number))
	        map.put(number,1);
	    else
	        map.put(number, map.get(number)+1);
	}

    // Find if there exists any pair of numbers which sum is equal to the value.
	public boolean find(int value) {
	    for(int key:map.keySet()){
	        int another = value-key;
	        if(another==key && map.get(key)>1)					//important.
	            return true;
	        if(another!=key && map.containsKey(another))
	            return true;
	    }
	    return false;
	}
}
