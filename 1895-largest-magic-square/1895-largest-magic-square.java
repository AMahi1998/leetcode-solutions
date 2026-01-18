class Solution {
    public int largestMagicSquare(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        long[][] rowPref = new long[m][n + 1];
        long[][] colPref = new long[n][m + 1];
        long[][] diag1 = new long[m + 1][n + 1];     // TL->BR
        long[][] diag2 = new long[m + 1][n + 1];     // TR->BL (shifted)

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                rowPref[r][c + 1] = rowPref[r][c] + grid[r][c];
                colPref[c][r + 1] = colPref[c][r] + grid[r][c];
                diag1[r + 1][c + 1] = diag1[r][c] + grid[r][c];
                diag2[r + 1][c] = diag2[r][c + 1] + grid[r][c];
            }
        }

        int maxK = Math.min(m, n);
        for (int k = maxK; k >= 2; k--) {
            for (int r = 0; r + k <= m; r++) {
                for (int c = 0; c + k <= n; c++) {
                    if (isMagic(grid, r, c, k, rowPref, colPref, diag1, diag2)) {
                        return k;
                    }
                }
            }
        }
        return 1; // every 1x1 is magic
    }

    private boolean isMagic(int[][] grid, int r, int c, int k,
                            long[][] rowPref, long[][] colPref,
                            long[][] diag1, long[][] diag2) {

        long target = rowSum(rowPref, r, c, c + k);

        // rows
        for (int i = 0; i < k; i++) {
            if (rowSum(rowPref, r + i, c, c + k) != target) return false;
        }

        // cols
        for (int j = 0; j < k; j++) {
            if (colSum(colPref, c + j, r, r + k) != target) return false;
        }

        // diagonals
        long d1 = diag1Sum(diag1, r, c, k);
        if (d1 != target) return false;

        long d2 = diag2Sum(diag2, r, c, k);
        return d2 == target;
    }

    private long rowSum(long[][] rowPref, int r, int c1, int c2) {
        return rowPref[r][c2] - rowPref[r][c1];
    }

    private long colSum(long[][] colPref, int c, int r1, int r2) {
        return colPref[c][r2] - colPref[c][r1];
    }

    private long diag1Sum(long[][] diag1, int r, int c, int k) {
        return diag1[r + k][c + k] - diag1[r][c];
    }

    private long diag2Sum(long[][] diag2, int r, int c, int k) {
        return diag2[r + k][c] - diag2[r][c + k];

        
    }
}