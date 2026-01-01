class Solution {
    private int R, C;
    private int[][] cells;
    private final int[] dr = {1, -1, 0, 0};
    private final int[] dc = {0, 0, 1, -1};

    public int latestDayToCross(int row, int col, int[][] cells) {
        this.R = row;
        this.C = col;
        this.cells = cells;

        int lo = 0, hi = cells.length;
        while (lo < hi) {
            int mid = lo + (hi - lo + 1) / 2;
            if (canCross(mid)) lo = mid;
            else hi = mid - 1;
        }
        return lo;
    }

    private boolean canCross(int day) {
        boolean[][] flooded = new boolean[R][C];
        for (int i = 0; i < day; i++) {
            int r = cells[i][0] - 1;
            int c = cells[i][1] - 1;
            flooded[r][c] = true;
        }

        ArrayDeque<int[]> q = new ArrayDeque<>();
        boolean[][] vis = new boolean[R][C];

       for (int c = 0; c < C; c++) {
            if (!flooded[0][c]) {
                q.offer(new int[]{0, c});
                vis[0][c] = true;
            }
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1];

            if (r == R - 1) return true;
            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];
                if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                if (vis[nr][nc] || flooded[nr][nc]) continue;
                vis[nr][nc] = true;
                q.offer(new int[]{nr, nc});
            }
        }
        return false;
    }
}