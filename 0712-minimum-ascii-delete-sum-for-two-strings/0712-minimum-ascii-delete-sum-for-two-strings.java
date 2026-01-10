class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length(), n = s2.length();

        int[] dp = new int[n + 1];

        for (int j = n - 1; j >= 0; j--) {
            dp[j] = dp[j + 1] + s2.charAt(j);
        }

        for (int i = m - 1; i >= 0; i--) {
            int prevDiag = dp[n];
            dp[n] = dp[n] + s1.charAt(i);

            for (int j = n - 1; j >= 0; j--) {
                int temp = dp[j];

                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[j] = prevDiag;
                } else {
                    int deleteS1 = s1.charAt(i) + dp[j];
                    int deleteS2 = s2.charAt(j) + dp[j + 1];
                    dp[j] = Math.min(deleteS1, deleteS2);
                }

                prevDiag = temp;
            }
        }

        return dp[0];
        
    }
}