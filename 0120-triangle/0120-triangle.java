class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int rows = triangle.size();
        int[] dp = new int[rows + 1]; // initialized to 0

        for (int r = rows - 1; r >= 0; r--) {
            List<Integer> row = triangle.get(r);
            for (int j = 0; j < row.size(); j++) {
                dp[j] = row.get(j) + Math.min(dp[j], dp[j + 1]);
            }
        }
        return dp[0];
    }
}
