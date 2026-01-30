class Solution { 
    static final long INF = (long) 4e18;

    public long minimumCost(String source, String target, String[] original, String[] changed, int[] cost) {
        int n = source.length();
        if (target.length() != n) return -1;

        Map<Integer, List<Integer>> byLen = new HashMap<>();
        for (int i = 0; i < original.length; i++) {
            int L = original[i].length();
            byLen.computeIfAbsent(L, k -> new ArrayList<>()).add(i);
        }

        Map<Integer, Map<String, Integer>> idMapByLen = new HashMap<>();
        Map<Integer, long[][]> distByLen = new HashMap<>();
        List<Integer> lengths = new ArrayList<>(byLen.keySet());
        Collections.sort(lengths);

        for (int L : lengths) {
            List<Integer> idxs = byLen.get(L);

            HashMap<String, Integer> id = new HashMap<>();
            ArrayList<String> nodes = new ArrayList<>();
            for (int idx : idxs) {
                String a = original[idx], b = changed[idx];
                if (!id.containsKey(a)) { id.put(a, nodes.size()); nodes.add(a); }
                if (!id.containsKey(b)) { id.put(b, nodes.size()); nodes.add(b); }
            }

            int M = nodes.size();
            long[][] dist = new long[M][M];
            for (int i = 0; i < M; i++) {
                Arrays.fill(dist[i], INF);
                dist[i][i] = 0;
            }

            for (int idx : idxs) {
                int u = id.get(original[idx]);
                int v = id.get(changed[idx]);
                dist[u][v] = Math.min(dist[u][v], (long) cost[idx]);
            }

            for (int kMid = 0; kMid < M; kMid++) {
                for (int i = 0; i < M; i++) {
                    long dik = dist[i][kMid];
                    if (dik == INF) continue;
                    for (int j = 0; j < M; j++) {
                        long nd = dik + dist[kMid][j];
                        if (nd < dist[i][j]) dist[i][j] = nd;
                    }
                }
            }

            idMapByLen.put(L, id);
            distByLen.put(L, dist);
        }

        long[] dp = new long[n + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            if (dp[i] == INF) continue;

            if (source.charAt(i) == target.charAt(i)) {
                dp[i + 1] = Math.min(dp[i + 1], dp[i]);
            }

            for (int L : lengths) {
                int j = i + L;
                if (j > n) break;

                String sSub = source.substring(i, j);
                String tSub = target.substring(i, j);

                if (sSub.equals(tSub)) {
                    dp[j] = Math.min(dp[j], dp[i]);
                    continue;
                }

                Map<String, Integer> id = idMapByLen.get(L);
                Integer u = id.get(sSub);
                Integer v = id.get(tSub);
                if (u == null || v == null) continue;

                long d = distByLen.get(L)[u][v];
                if (d >= INF / 2) continue;

                dp[j] = Math.min(dp[j], dp[i] + d);
            }
        }

        return dp[n] >= INF / 2 ? -1 : dp[n];
    }
}