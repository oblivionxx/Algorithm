import java.util.*;

/*
 * Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LFUCache cache = new LFUCache( 2  /capacity/  );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.get(3);       // returns 3.
cache.put(4, 4);    // evicts key 1.
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4

Design
 */
public class LT460_LFU_Cache {
    //Two HashMaps are used, one to store <key, value> pair, another store the <key, node>.
    // I use double linked list to keep the frequent of each key. In each double linked list node, keys with the same count are saved using java built in LinkedHashSet. This can keep the order.
    // Every time, one key is referenced, first find the current node corresponding to the key, If the following node exist and the frequent is larger by one, add key to the keys of the following node, else create a new node and add it following the current node.
    // All operations are guaranteed to be O(1).
    //http://bookshadow.com/weblog/2016/11/22/leetcode-lfu-cache/
    private CacheElement head = null;
    private int capacity = 0;
    private HashMap<Integer, Integer> valueHash = null;
    private HashMap<Integer, CacheElement> cache = null;
    
    public LT460_LFU_Cache(int _capacity) {
        capacity = _capacity;
        valueHash = new HashMap<Integer, Integer>();
        cache = new HashMap<Integer, CacheElement>();
    }
    
    public int get(int key) {
        if (valueHash.containsKey(key)) {
            refreshUsage(key);
            return valueHash.get(key);
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if ( capacity == 0 ) return;
        if (valueHash.containsKey(key)) {
            valueHash.put(key, value);
        } else {
            if (valueHash.size() < capacity) {
                valueHash.put(key, value);
            } else {
                removeOne();
                valueHash.put(key, value);
            }
            addToHead(key);
        }
        refreshUsage(key);
    }
    
    private void addToHead(int key) {
        if (head == null) {
            head = new CacheElement(0);
            head.keys.add(key);
        } else if (head.count > 0) {
            CacheElement element = new CacheElement(0);
            element.keys.add(key);
            element.next = head;
            head.prev = element;
            head = element;
        } else {
            head.keys.add(key);
        }
        cache.put(key, head);      
    }
    
    private void refreshUsage(int key) {
        CacheElement element = cache.get(key);
        element.keys.remove(key);
        
        if (element.next == null) {
            element.next = new CacheElement(element.count+1);
            element.next.prev = element;
            element.next.keys.add(key);
        } else if (element.next.count == element.count+1) {
            element.next.keys.add(key);
        } else {
            CacheElement tmp = new CacheElement(element.count+1);
            tmp.keys.add(key);
            tmp.prev = element;
            tmp.next = element.next;
            element.next.prev = tmp;
            element.next = tmp;
        }
        cache.put(key, element.next);
        if (element.keys.size() == 0) remove(element);
    }
    
    private void removeOne() {
        if (head == null) return;
        int old = 0;
        for (int n: head.keys) {
            old = n;
            break;
        }
        head.keys.remove(old);
        if (head.keys.size() == 0) remove(head);
        cache.remove(old);
        valueHash.remove(old);
    }
    
    private void remove(CacheElement element) {
        if (element.prev == null) {
            head = element.next;
        } else {
            element.prev.next = element.next;
        } 
        if (element.next != null) {
            element.next.prev = element.prev;
        }
    }
    
    class CacheElement {
        public int count = 0;
        public LinkedHashSet<Integer> keys = null;
        public CacheElement prev = null, next = null;
        
        public CacheElement(int count) {
            this.count = count;
            keys = new LinkedHashSet<Integer>();
            prev = next = null;
        }
    }
}
