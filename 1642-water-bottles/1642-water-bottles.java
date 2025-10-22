class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {
        int totalDrunk = numBottles;
        int empty = numBottles;

        while (empty >= numExchange) {
            int newFull = empty / numExchange;
            totalDrunk += newFull;
            empty = empty % numExchange + newFull;
        }

        return totalDrunk;
    }
        
}