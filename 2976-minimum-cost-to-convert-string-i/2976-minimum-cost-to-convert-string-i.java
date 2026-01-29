class Solution {
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        final long INF = (long) 4e18;
        long[][] dist = new long[26][26];

        for (int i = 0; i < 26; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        for (int i = 0; i < cost.length; i++) {
            int u = original[i] - 'a';
            int v = changed[i] - 'a';
            dist[u][v] = Math.min(dist[u][v], (long) cost[i]);
        }

        for (int k = 0; k < 26; k++) {
            for (int i = 0; i < 26; i++) {
                long dik = dist[i][k];
                if (dik == INF) continue;
                for (int j = 0; j < 26; j++) {
                    long nk = dik + dist[k][j];
                    if (nk < dist[i][j]) dist[i][j] = nk;
                }
            }
        }

        long ans = 0;
        for (int i = 0; i < source.length(); i++) {
            int s = source.charAt(i) - 'a';
            int t = target.charAt(i) - 'a';
            long d = dist[s][t];
            if (d >= INF / 2) return -1;
            ans += d;
        }
        return ans;    
    }
}