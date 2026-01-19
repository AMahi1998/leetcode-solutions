class Solution {
    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length, n = mat[0].length;

        long[][] ps = new long[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            long rowSum = 0;
            for (int j = 0; j < n; j++) {
                rowSum += mat[i][j];
                ps[i + 1][j + 1] = ps[i][j + 1] + rowSum;
            }
        }

        int lo = 0, hi = Math.min(m, n);
        while (lo < hi) {
            int mid = lo + (hi - lo + 1) / 2;
            if (existsSquare(ps, m, n, mid, threshold)) {
                lo = mid;
            } else {
                hi = mid - 1;
            }
        }
        return lo;
    }

    private boolean existsSquare(long[][] ps, int m, int n, int k, int threshold) {
        if (k == 0) return true;
        for (int i = 0; i + k <= m; i++) {
            for (int j = 0; j + k <= n; j++) {
                long sum = ps[i + k][j + k]
                         - ps[i][j + k]
                         - ps[i + k][j]
                         + ps[i][j];
                if (sum <= threshold) return true;
            }
        }
        return false;
        
    }
}