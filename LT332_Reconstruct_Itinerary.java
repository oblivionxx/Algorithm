import java.util.*;

/*
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

Note:
If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
All airports are represented by three capital letters (IATA code).
You may assume all tickets form at least one valid itinerary.
Example 1:
tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
Example 2:
tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.

DFS, Graph
 */
public class LT332_Reconstruct_Itinerary {
    //must iterate all the destinations. backtracking. 
    public List<String> findItinerary(String[][] tickets) {
        List<String> res = new ArrayList<>();
        HashMap<String, List<String>> destination = new HashMap<>();
        for(String[] ticket:tickets){
            if(destination.containsKey(ticket[0])){
                destination.get(ticket[0]).add(ticket[1]);
            }else{
                List<String> end = new ArrayList<String>();
                end.add(ticket[1]);
                destination.put(ticket[0], end);
            }
        }
        
        for(List<String> list:destination.values()){
            Collections.sort(list);
        }
        dfs("JFK", res, destination, tickets.length+1);
        return res;   
    }
    
    public boolean dfs(String cur, List<String> res, HashMap<String, List<String>> destination, int n){
        res.add(cur);
        if (res.size() == n) {
            return true;
        }
        if (!destination.containsKey(cur) || destination.get(cur).isEmpty()) {
            return false;
        }
        List<String> arrivals = destination.get(cur);
        for (int i = 0; i < arrivals.size(); i++) { // iterate each arrival point
            String arrival = destination.get(cur).remove(i);
            if (dfs(arrival, res, destination, n)) {
                return true;
            }
            res.remove(res.size() - 1); // backtrack
            arrivals.add(i,arrival);
        }
        return false;
    }
}
