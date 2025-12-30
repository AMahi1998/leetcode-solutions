class Solution {
    public int numMagicSquaresInside(int[][] grid) {
        int r = grid.length, c = grid[0].length;
        if (r < 3 || c < 3) return 0;

        int count = 0;
        for (int i = 0; i <= r - 3; i++) {
            for (int j = 0; j <= c - 3; j++) {
                if (isMagic3x3(grid, i, j)) count++;
            }
        }
        return count;
    }

    private boolean isMagic3x3(int[][] g, int i, int j) {
    
        if (g[i + 1][j + 1] != 5) return false;

        boolean[] seen = new boolean[10];
        for (int x = i; x < i + 3; x++) {
            for (int y = j; y < j + 3; y++) {
                int v = g[x][y];
                if (v < 1 || v > 9 || seen[v]) return false;
                seen[v] = true;
            }
        }

        int s1 = g[i][j] + g[i][j + 1] + g[i][j + 2];
        int s2 = g[i + 1][j] + g[i + 1][j + 1] + g[i + 1][j + 2];
        int s3 = g[i + 2][j] + g[i + 2][j + 1] + g[i + 2][j + 2];
        if (s1 != 15 || s2 != 15 || s3 != 15) return false;

        int c1 = g[i][j] + g[i + 1][j] + g[i + 2][j];
        int c2 = g[i][j + 1] + g[i + 1][j + 1] + g[i + 2][j + 1];
        int c3 = g[i][j + 2] + g[i + 1][j + 2] + g[i + 2][j + 2];
        if (c1 != 15 || c2 != 15 || c3 != 15) return false;

        int d1 = g[i][j] + g[i + 1][j + 1] + g[i + 2][j + 2];
        int d2 = g[i][j + 2] + g[i + 1][j + 1] + g[i + 2][j];
        return d1 == 15 && d2 == 15;

        
    }
}