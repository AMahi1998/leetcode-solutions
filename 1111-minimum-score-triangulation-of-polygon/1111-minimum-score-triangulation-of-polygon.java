class Solution {
    public int minScoreTriangulation(int[] values) {
            int n = values.length;
    int[][] dp = new int[n][n];

    for (int len = 2; len < n; len++) {
        for (int i = 0; i + len < n; i++) {
            int j = i + len;
            dp[i][j] = Integer.MAX_VALUE;
            for (int k = i + 1; k < j; k++) {
                int cost = dp[i][k] + dp[k][j] + values[i] * values[k] * values[j];
                if (cost < dp[i][j]) dp[i][j] = cost;
            }
        }
    }
    return dp[0][n - 1];
        
    }
}