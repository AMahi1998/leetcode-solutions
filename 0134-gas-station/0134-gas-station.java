class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalTank=0, tank=0, start=0;
        for(int i=0; i<gas.length; i++)
        {
            int diff=gas[i]-cost[i];
            totalTank=totalTank+diff;
            tank=tank+diff;

            if(tank<0)
            {
                start=i+1;
                tank=0;
            }

        }
        return totalTank>=0 ? start : -1;
        
    }
}