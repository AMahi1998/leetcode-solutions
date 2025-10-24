class Solution {
    private static class Cell {
        int r, c, h;
        Cell(int r, int c, int h) { this.r = r; this.c = c; this.h = h; }
    }
    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length;
        if (m == 0) return 0;
        int n = heightMap[0].length;
        if (n == 0) return 0;

        boolean[][] vis = new boolean[m][n];
        PriorityQueue<Cell> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.h));

        for (int r = 0; r < m; r++) {
            pq.offer(new Cell(r, 0, heightMap[r][0]));
            pq.offer(new Cell(r, n - 1, heightMap[r][n - 1]));
            vis[r][0] = true;
            vis[r][n - 1] = true;
        }
        for (int c = 1; c < n - 1; c++) {
            pq.offer(new Cell(0, c, heightMap[0][c]));
            pq.offer(new Cell(m - 1, c, heightMap[m - 1][c]));
            vis[0][c] = true;
            vis[m - 1][c] = true;
        }

        int water = 0;
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        while (!pq.isEmpty()) {
            Cell cur = pq.poll();
            for (int[] d : dirs) {
                int nr = cur.r + d[0], nc = cur.c + d[1];
                if (nr < 0 || nr >= m || nc < 0 || nc >= n || vis[nr][nc]) continue;
                vis[nr][nc] = true;

                int nh = heightMap[nr][nc];
                
                if (nh < cur.h) water += cur.h - nh;
                pq.offer(new Cell(nr, nc, Math.max(nh, cur.h)));
            }
        }
        return water;
        
    }
}