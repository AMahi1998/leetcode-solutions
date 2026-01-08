class Solution {
    public int maxDotProduct(int[] nums1, int[] nums2) {
                int m = nums1.length, n = nums2.length;
        int NEG = Integer.MIN_VALUE / 4;

        int[] prev = new int[n + 1];
        Arrays.fill(prev, NEG);

        for (int i = 1; i <= m; i++) {
            int[] cur = new int[n + 1];
            Arrays.fill(cur, NEG);

            for (int j = 1; j <= n; j++) {
                int prod = nums1[i - 1] * nums2[j - 1];

                int best = prod; // start new subsequence with this pair

                // extend diagonal subsequence if it helps
                if (prev[j - 1] > 0) best = Math.max(best, prod + prev[j - 1]);
                else best = Math.max(best, prod); // (already)

                // skip one element from either array
                best = Math.max(best, prev[j]);
                best = Math.max(best, cur[j - 1]);

                cur[j] = best;
            }
            prev = cur;
        }

        return prev[n];
    }
}