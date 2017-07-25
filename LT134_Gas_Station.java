/*
There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.
Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.

Note:
The solution is guaranteed to be unique.

Greedy
 */
public class LT134_Gas_Station {
	public int canCompleteCircuit(int[] gas, int[] cost) {
        int sum = 0;
        int total = 0;
        int index= 0;

        for(int i=0;i<gas.length;i++){
            sum  += gas[i]-cost[i];		//sum is whether current total oil can support current travel. when update new start, sum->0
            total += gas[i]-cost[i];	//always add up to check if can travel around the circuit once.
            if(sum<0){
                 index = i+1;    //cannot be start.
                 sum = 0;    	//from the new start to calculate possibility of sum
            }
            
        }
        
        if(total<0)
            return -1;
        else
            return index;
    }
}
