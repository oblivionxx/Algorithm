import java.util.*;

/*
 * Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks.Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.

However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.

You need to return the least number of intervals the CPU will take to finish all the given tasks.

Example 1:
Input: tasks = ['A','A','A','B','B','B'], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
Note:
The number of tasks is in the range [1, 10000].
The integer n is in the range [0, 100].

Array, Greedy, Queue
 */
public class LT621_Task_Scheduler {
    //Add tasks to a priority Q and sort based on the highest frequency. And pick the task in each round of 'n' with highest frequency. As you pick the task, decrease the frequency, and put them back after the round.
    //eg. AAABBC, n=2 -> ABCAB-A
    public int leastInterval(char[] tasks, int n) {
        HashMap<Character, Integer> map = new HashMap<>();          //store the freq in the map
        for(char c: tasks){
            map.put(c, map.getOrDefault(c, 0)+1);
        }
        
        PriorityQueue<Map.Entry<Character, Integer>> queue = new PriorityQueue<>(
            (a,b) -> a.getValue() != b.getValue() ? b.getValue() - a.getValue() : a.getKey() - b.getKey());
        
        queue.addAll(map.entrySet());
        
        int count = 0;
        while(!queue.isEmpty()){
            int k=n+1;
            List<Map.Entry<Character, Integer>> tmp = new ArrayList<>();
            while(k>0 && !queue.isEmpty()){
                Map.Entry<Character, Integer> topTask = queue.poll();   
                topTask.setValue(topTask.getValue()-1);             //one task got executed. 
                tmp.add(topTask);
                k--;                //decrese interval
                count++;            //counting executed tasks
            }   
            
            
            for(Map.Entry<Character, Integer> e: tmp){
                if(e.getValue()>0) queue.add(e);                //add tasks with new freq to the priority queue. trick here. consume priority list element
            }
            
            if(queue.isEmpty()) break;
            count +=k;                          //if k>0. need to be idle
        }
        
        return count;
        
    }
}
