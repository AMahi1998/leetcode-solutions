class Solution {
    public int maxCollectedFruits(int[][] fruits) {
        int n = fruits.length;
        final long NEG = Long.MIN_VALUE / 4;

        long diagSum = 0;
        for (int i = 0; i < n; i++) {
            diagSum += fruits[i][i];
        }

        long[][] dp2 = new long[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(dp2[i], NEG);

        dp2[0][n - 1] = (0 == n - 1) ? 0 : fruits[0][n - 1];

        for (int r = 1; r < n; r++) {
            for (int c = 0; c < n; c++) {
                long best = NEG;
                if (c > 0) best = Math.max(best, dp2[r - 1][c - 1]);
                best = Math.max(best, dp2[r - 1][c]);
                if (c + 1 < n) best = Math.max(best, dp2[r - 1][c + 1]);

                if (best != NEG) {
                    dp2[r][c] = best + ((r == c) ? 0 : fruits[r][c]);
                }
            }
        }

        long best2 = dp2[n - 1][n - 1];

        long[][] dp3 = new long[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(dp3[i], NEG);

        dp3[n - 1][0] = ((n - 1) == 0) ? 0 : fruits[n - 1][0];

        for (int c = 1; c < n; c++) {
            for (int r = 0; r < n; r++) {
                long best = NEG;
                if (r > 0) best = Math.max(best, dp3[r - 1][c - 1]);
                best = Math.max(best, dp3[r][c - 1]);
                if (r + 1 < n) best = Math.max(best, dp3[r + 1][c - 1]);

                if (best != NEG) {
                    dp3[r][c] = best + ((r == c) ? 0 : fruits[r][c]);
                }
            }
        }

        long best3 = dp3[n - 1][n - 1];

        return (int) (diagSum + best2 + best3);

        
    }
}