/*
Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.
get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

Design
 */
import java.util.*;
public class LT146_LRU_Cache {
	//multi-thread please use lock on the hashmap and linkedlist
    private HashMap<Integer, DoubleLinkedListNode> map = new HashMap<>();
    private DoubleLinkedListNode head;
    private DoubleLinkedListNode end;
    private int capacity;
    private int len;
    
    public LT146_LRU_Cache(int capacity) {
        this.capacity = capacity;
        len =0;
    }
    
    public int get(int key) {
        if(map.containsKey(key)){
            DoubleLinkedListNode cur = map.get(key);
            removeNode(cur);
            setHead(cur);
            return cur.val;
        }
        else
            return -1;
    }
    
    public void set(int key, int value) {
        if(map.containsKey(key)){
            //update new value
            DoubleLinkedListNode cur = map.get(key);
            cur.val = value;
            removeNode(cur);
            setHead(cur);
        }else{
            //need to create new node
            DoubleLinkedListNode cur = new DoubleLinkedListNode(key, value);
            if(len<capacity){
                setHead(cur);
                map.put(key, cur);
                len++;
            }else
            {   //remove end
                map.remove(end.key);
                end = end.pre;
                if(end!=null)
                    end.next = null;
                    
                setHead(cur);
                map.put(key, cur);
            }
        }
    }
    
    
    
    public void setHead(DoubleLinkedListNode node){
         node.next = head;
         node.pre = null;
         if(head!=null)
             head.pre = node;
         
         //if head==null
         head = node;
         if(end==null)
            end = node;
            
    }
    
    
    public void removeNode(DoubleLinkedListNode node){
        DoubleLinkedListNode pre = node.pre;
        DoubleLinkedListNode next = node.next;
        //need to attention, cur is head so pre is null or cur is end so next is null 
        if(pre!=null)
            pre.next = next;
        else
            head =next;
        
        if(next!=null)
            next.pre = pre;
        else
            end = pre;
            
    }
    
    class DoubleLinkedListNode{
        public int key;
        public int val;
        public DoubleLinkedListNode next;
        public DoubleLinkedListNode pre;
        
        public DoubleLinkedListNode(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
}
