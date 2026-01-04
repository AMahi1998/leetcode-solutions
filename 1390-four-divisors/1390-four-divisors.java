class Solution {
    public int sumFourDivisors(int[] nums) {
                int total = 0;
        for (int x : nums) {
            total += sumIfFourDivisors(x);
        }
        return total;
    }

    private int sumIfFourDivisors(int x) {
        int first = -1;
        int second = -1;
        int count = 0;

        int n = x;
        for (int p = 2; p * p <= n; p++) {
            while (n % p == 0) {
                count++;
                if (first == -1) first = p;
                else if (p != first && second == -1) second = p;
                n /= p;

                
                if (count > 3) return 0;
            }
        }
        if (n > 1) {
            count++;
            if (first == -1) first = n;
            else if (n != first && second == -1) second = n;
            if (count > 3) return 0;
        }

        if (count == 3 && second == -1) {
            int p = first;
            long p2 = (long) p * p;
            long p3 = p2 * p;
    
            return (int) (1 + p + p2 + p3);
        }

        if (count == 2 && second != -1) {
            int p = first, q = second;
            return 1 + p + q + p * q;
        }

        return 0;
    
    }
}