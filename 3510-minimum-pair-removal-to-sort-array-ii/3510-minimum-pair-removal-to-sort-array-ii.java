class Solution {
    static class Entry {
        long sum;
        int left;
        int right;
        int verL;
        int verR;

        Entry(long sum, int left, int right, int verL, int verR) {
            this.sum = sum;
            this.left = left;
            this.right = right;
            this.verL = verL;
            this.verR = verR;
        }
    }
    public int minimumPairRemoval(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;

        long[] val = new long[n];
        for (int i = 0; i < n; i++) val[i] = nums[i];

        int[] prev = new int[n];
        int[] next = new int[n];
        boolean[] removed = new boolean[n];
        int[] ver = new int[n];

        for (int i = 0; i < n; i++) {
            prev[i] = i - 1;
            next[i] = (i == n - 1) ? -1 : i + 1;
        }

        int bad = 0;
        for (int i = 1; i < n; i++) {
            if (val[i] < val[i - 1]) bad++;
        }
        if (bad == 0) return 0;

        PriorityQueue<Entry> pq = new PriorityQueue<>((a, b) -> {
            if (a.sum != b.sum) return Long.compare(a.sum, b.sum);
            return Integer.compare(a.left, b.left);
        });

        for (int i = 0; i < n - 1; i++) {
            pq.add(new Entry(val[i] + val[i + 1], i, i + 1, ver[i], ver[i + 1]));
        }

        int ops = 0;

        while (bad > 0) {
            Entry e = pq.poll();
            while (!isValid(e, next, removed, ver)) {
                e = pq.poll();
            }

            int b = e.left;
            int c = e.right;
            int a = prev[b];
            int d = next[c];

            bad -= edgeBad(a, b, val, removed);
            bad -= edgeBad(b, c, val, removed);
            bad -= edgeBad(c, d, val, removed);

            val[b] = val[b] + val[c];
            ver[b]++;
            removed[c] = true;

            next[b] = d;
            if (d != -1) prev[d] = b;

            prev[c] = next[c] = -1;

            bad += edgeBad(a, b, val, removed);
            bad += edgeBad(b, d, val, removed);

            if (a != -1 && !removed[a]) {
                pq.add(new Entry(val[a] + val[b], a, b, ver[a], ver[b]));
            }
            if (d != -1 && !removed[d]) {
                pq.add(new Entry(val[b] + val[d], b, d, ver[b], ver[d]));
            }

            ops++;
        }

        return ops;
    }

    private boolean isValid(Entry e, int[] next, boolean[] removed, int[] ver) {
        if (e == null) return false;
        int l = e.left, r = e.right;
        if (l < 0 || r < 0) return false;
        if (removed[l] || removed[r]) return false;
        if (next[l] != r) return false;
        if (ver[l] != e.verL || ver[r] != e.verR) return false;
        return true;
    }

    private int edgeBad(int u, int v, long[] val, boolean[] removed) {
        if (u == -1 || v == -1) return 0;
        if (removed[u] || removed[v]) return 0;
        return (val[v] < val[u]) ? 1 : 0;
    }
}