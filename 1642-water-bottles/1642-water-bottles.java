class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {
        int drank = numBottles; 
        int empties = numBottles;
        
        while (empties >= numExchange) {
            int newBottles = empties / numExchange;
            drank += newBottles;
            empties = empties % numExchange + newBottles;        }
        
        return drank;
        
    }
}