import java.util.*;

class Solution {

    static class State {
        long d;
        int cell;
        int t;
        State(long d, int cell, int t) {
            this.d = d;
            this.cell = cell;
            this.t = t;
        }
    }

    static class NextDSU {
        int[] parent;
        NextDSU(int V) {
            parent = new int[V + 1];
            for (int i = 0; i <= V; i++) parent[i] = i;
        }
        int find(int x) {
            if (parent[x] == x) return x;
            parent[x] = find(parent[x]);
            return parent[x];
        }
        void erase(int x) { // mark processed
            parent[x] = find(x + 1);
        }
    }

    public int minCost(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int V = m * n;
        int target = (m - 1) * n + (n - 1);

        int[] value = new int[V];
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                value[r * n + c] = grid[r][c];
            }
        }

        // sort cells by value
        Integer[] ord = new Integer[V];
        for (int i = 0; i < V; i++) ord[i] = i;
        Arrays.sort(ord, Comparator.comparingInt(a -> value[a]));

        int[] cellAtRank = new int[V];
        int[] valAtRank = new int[V];
        for (int i = 0; i < V; i++) {
            int cell = ord[i];
            cellAtRank[i] = cell;
            valAtRank[i] = value[cell];
        }

        long INF = (long)4e18;
        long[][] dist = new long[k + 1][V];
        for (int t = 0; t <= k; t++) Arrays.fill(dist[t], INF);

        NextDSU[] dsu = new NextDSU[k + 1];
        for (int layer = 0; layer <= k; layer++) dsu[layer] = new NextDSU(V);

        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a.d));

        dist[0][0] = 0;
        pq.add(new State(0, 0, 0));

        while (!pq.isEmpty()) {
            State cur = pq.poll();
            long d = cur.d;
            int cell = cur.cell;
            int t = cur.t;

            if (d != dist[t][cell]) continue;
            if (cell == target) return (int) d;

            int r = cell / n, c = cell % n;

            // normal moves
            if (c + 1 < n) {
                int nxt = cell + 1;
                long nd = d + value[nxt];
                if (nd < dist[t][nxt]) {
                    dist[t][nxt] = nd;
                    pq.add(new State(nd, nxt, t));
                }
            }
            if (r + 1 < m) {
                int nxt = cell + n;
                long nd = d + value[nxt];
                if (nd < dist[t][nxt]) {
                    dist[t][nxt] = nd;
                    pq.add(new State(nd, nxt, t));
                }
            }

            // teleport
            if (t < k) {
                int layer = t + 1;
                int curVal = value[cell];

                // IMPORTANT: include ALL ranks with val <= curVal (ties too)
                int lastRank = upperBound(valAtRank, curVal) - 1;
                if (lastRank >= 0) {
                    int rk = dsu[layer].find(0);
                    while (rk <= lastRank) {
                        int destCell = cellAtRank[rk];
                        if (d < dist[layer][destCell]) {
                            dist[layer][destCell] = d;
                            pq.add(new State(d, destCell, layer));
                        }
                        dsu[layer].erase(rk);
                        rk = dsu[layer].find(rk);
                    }
                }
            }
        }

        return -1;
    }

    // first index with a[idx] > x
    private int upperBound(int[] a, int x) {
        int lo = 0, hi = a.length;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (a[mid] <= x) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }
}
