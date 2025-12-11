class Solution {
    public int maximumLength(int[] nums, int k) {
        int ans = 1;

        for (int c = 0; c < k; c++) {
            int[] dp = new int[k];

            for (int num : nums) {
                int r = num % k;
                int prev = (c - r + k) % k;

                dp[r] = Math.max(dp[r], dp[prev] + 1);
                ans = Math.max(ans, dp[r]);
            }
        }

        return ans;

    }
}