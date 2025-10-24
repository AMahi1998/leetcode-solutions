class Solution {
    public int maxBottlesDrunk(int numBottles, int numExchange) {

        int totalDrunk = numBottles;
        int empty = numBottles;

        while (empty >= numExchange) {
            empty -= numExchange;
            numBottles = 1;
            totalDrunk += numBottles;
            empty += numBottles;
            numExchange++;
        }

        return totalDrunk;

        
    }
}