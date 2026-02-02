class Solution {
    private static void add(TreeMap<Long, Integer> mp, long x) {
        mp.put(x, mp.getOrDefault(x, 0) + 1);
    }
    private static void remove(TreeMap<Long, Integer> mp, long x) {
        int c = mp.get(x);
        if (c == 1) mp.remove(x);
        else mp.put(x, c - 1);
    }

    public long minimumCost(int[] nums, int k, int dist) {
        int n = nums.length;
        int need = k - 1;   
        long base = nums[0];

        int maxI1 = n - need;

        int L = 1;
        int R = Math.min(n - 1, L + dist);

        TreeMap<Long, Integer> small = new TreeMap<>(); // need smallest
        TreeMap<Long, Integer> large = new TreeMap<>();
        long sumSmall = 0;
        int szSmall = 0;

        for (int i = L; i <= R; i++) add(large, (long) nums[i]);

        while (szSmall < need) {
            long x = large.firstKey();
            remove(large, x);
            add(small, x);
            sumSmall += x;
            szSmall++;
        }

        long ans = base + sumSmall;

        for (int i1 = 2; i1 <= maxI1; i1++) {
            int outIdx = i1 - 1;
            long outVal = nums[outIdx];

            if (small.containsKey(outVal)) {
                remove(small, outVal);
                sumSmall -= outVal;
                szSmall--;
            } else {
                remove(large, outVal);
            }

            int newR = Math.min(n - 1, i1 + dist);
            while (R < newR) {
                R++;
                add(large, (long) nums[R]);
            }

            while (szSmall < need) {
                long x = large.firstKey();
                remove(large, x);
                add(small, x);
                sumSmall += x;
                szSmall++;
            }

            while (!small.isEmpty() && !large.isEmpty() && small.lastKey() > large.firstKey()) {
                long a = small.lastKey();
                long b = large.firstKey();
                remove(small, a);
                remove(large, b);
                add(small, b);
                add(large, a);
                sumSmall += (b - a);
            }

            ans = Math.min(ans, base + sumSmall);
        }

        return ans;
   }
}