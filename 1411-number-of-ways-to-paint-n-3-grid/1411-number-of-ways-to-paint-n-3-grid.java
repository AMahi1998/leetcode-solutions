class Solution {
        private static final long MOD = 1_000_000_007L;

    public int numOfWays(int n) {
        long same = 6;
        long diff = 6;

        for (int i = 2; i <= n; i++) {
            long newSame = (3 * same + 2 * diff) % MOD;
            long newDiff = (2 * same + 2 * diff) % MOD;
            same = newSame;
            diff = newDiff;
        }

        return (int) ((same + diff) % MOD);
    }
}