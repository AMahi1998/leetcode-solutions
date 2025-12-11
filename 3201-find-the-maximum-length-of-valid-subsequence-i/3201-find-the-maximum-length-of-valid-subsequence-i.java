class Solution {
    public int maximumLength(int[] nums) {
        int countEven = 0, countOdd = 0;

        for (int num : nums) {
            if ((num & 1) == 0) countEven++;
            else countOdd++;
        }

        // Case 1: same parity subsequence
        int sameParity = Math.max(countEven, countOdd);

        // Case 2: alternating parity subsequence
        int altStartEven = 0;
        int expected = 0;
        for (int num : nums) {
            if ((num & 1) == expected) {
                altStartEven++;
                expected ^= 1; 
            }
        }

        int altStartOdd = 0;
        expected = 1;
        for (int num : nums) {
            if ((num & 1) == expected) {
                altStartOdd++;
                expected ^= 1;
            }
        }

        int alternating = Math.max(altStartEven, altStartOdd);

        return Math.max(sameParity, alternating);

        
    }
}