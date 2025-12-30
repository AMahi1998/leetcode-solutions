class Solution {
    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        int n = fruits.length;
        int[] pos = new int[n];
        long[] pref = new long[n + 1];

        for (int i = 0; i < n; i++) {
            pos[i] = fruits[i][0];
            pref[i + 1] = pref[i] + fruits[i][1];
        }

        long ans = 0;
        int l = 0;

        for (int r = 0; r < n; r++) {
            while (l <= r && stepsNeeded(pos[l], pos[r], startPos) > k) {
                l++;
            }
            long sum = pref[r + 1] - pref[l];
            if (sum > ans) ans = sum;
        }

        return (int) ans;
    }

    private int stepsNeeded(int left, int right, int startPos) {
        if (right <= startPos) {
            
            return startPos - left;
        }
        if (left >= startPos) {
            
            return right - startPos;
        }
        
        int goLeftFirst = 2 * (startPos - left) + (right - startPos);
        int goRightFirst = 2 * (right - startPos) + (startPos - left);
        return Math.min(goLeftFirst, goRightFirst);
    }
}