class Solution {
    static class Edge {
        int to;
        int w;
        Edge(int to, int w) { this.to = to; this.w = w; }
    }

    static class State {
        int node;
        long dist;
        State(int node, long dist) { this.node = node; this.dist = dist; }
    }
    public int minCost(int n, int[][] edges) {
                List<Edge>[] g = new ArrayList[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();

        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            g[u].add(new Edge(v, w));
            g[v].add(new Edge(u, 2 * w));
        }

        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;

        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a.dist));
        pq.add(new State(0, 0));

        while (!pq.isEmpty()) {
            State cur = pq.poll();
            int x = cur.node;
            long d = cur.dist;

            if (d != dist[x]) continue;
            if (x == n - 1) return (int) d;

            for (Edge ed : g[x]) {
                long nd = d + ed.w;
                if (nd < dist[ed.to]) {
                    dist[ed.to] = nd;
                    pq.add(new State(ed.to, nd));
                }
            }
        }
        return -1;  
    }
}