class Solution {
   static class Event{
        long y;
        long x1, x2;
        int delta;
        Event(long y, long x1, long x2, int delta) {
            this.y = y; this.x1 = x1; this.x2 = x2; this.delta = delta;
        }
    }

    static class Slab {
        long y1, y2;
        long coveredX;
        Slab(long y1, long y2, long coveredX) {
            this.y1 = y1; this.y2 = y2; this.coveredX = coveredX;
        }
        double area() {
            return (double) coveredX * (double) (y2 - y1);
        }
    }

    static class SegTree {
        long[] xs;
        int n;              
        int[] coverCount;   
        long[] coverLen;    

        SegTree(long[] xs) {
            this.xs = xs;
            this.n = xs.length - 1;
            this.coverCount = new int[4 * n];
            this.coverLen = new long[4 * n];
        }

        void update(int ql, int qr, int delta) { 
            if (ql > qr) return;
            update(1, 0, n - 1, ql, qr, delta);
        }

        long coveredLength() {
            return (n <= 0) ? 0 : coverLen[1];
        }

        private void update(int idx, int l, int r, int ql, int qr, int delta) {
            if (qr < l || r < ql) return;
            if (ql <= l && r <= qr) {
                coverCount[idx] += delta;
                pushUp(idx, l, r);
                return;
            }
            int mid = (l + r) >>> 1;
            update(idx << 1, l, mid, ql, qr, delta);
            update(idx << 1 | 1, mid + 1, r, ql, qr, delta);
            pushUp(idx, l, r);
        }

        private void pushUp(int idx, int l, int r) {
            if (coverCount[idx] > 0) {
                // fully covered
                coverLen[idx] = xs[r + 1] - xs[l];
            } else {
                if (l == r) coverLen[idx] = 0;
                else coverLen[idx] = coverLen[idx << 1] + coverLen[idx << 1 | 1];
            }
        }
    }

    public double separateSquares(int[][] squares) {
        int n = squares.length;
        List<Event> events = new ArrayList<>(2 * n);
        long[] xVals = new long[2 * n];

        for (int i = 0; i < n; i++) {
            long x = squares[i][0], y = squares[i][1], l = squares[i][2];
            long x1 = x, x2 = x + l;
            long y1 = y, y2 = y + l;

            events.add(new Event(y1, x1, x2, +1));
            events.add(new Event(y2, x1, x2, -1));

            xVals[2 * i] = x1;
            xVals[2 * i + 1] = x2;
        }

        Arrays.sort(xVals);
        long[] xs = unique(xVals);
        if (xs.length <= 1) return (double) events.get(0).y;

        events.sort(Comparator.comparingLong(e -> e.y));

        SegTree st = new SegTree(xs);

        List<Slab> slabs = new ArrayList<>();
        int i = 0;
        long curY = events.get(0).y;

        while (i < events.size() && events.get(i).y == curY) {
            Event e = events.get(i++);
            int l = lowerBound(xs, e.x1);
            int r = lowerBound(xs, e.x2) - 1;
            st.update(l, r, e.delta);
        }

        while (i < events.size()) {
            long nextY = events.get(i).y;
            long coveredX = st.coveredLength();

            if (nextY > curY && coveredX > 0) {
                slabs.add(new Slab(curY, nextY, coveredX));
            }

            curY = nextY;
            while (i < events.size() && events.get(i).y == curY) {
                Event e = events.get(i++);
                int l = lowerBound(xs, e.x1);
                int r = lowerBound(xs, e.x2) - 1;
                st.update(l, r, e.delta);
            }
        }

        double total = 0.0;
        for (Slab s : slabs) total += s.area();
        double half = total / 2.0;

        double pref = 0.0;
        for (Slab s : slabs) {
            if (pref >= half) {
                return (double) s.y1;
            }
            double a = s.area();
            if (pref + a >= half) {
                double need = half - pref;
                return (double) s.y1 + need / (double) s.coveredX;
            }
            pref += a;
        }

        return (slabs.isEmpty() ? 0.0 : (double) slabs.get(slabs.size() - 1).y2);
    }

    private static long[] unique(long[] arr) {
        int m = arr.length;
        long[] tmp = new long[m];
        int k = 0;
        for (int i = 0; i < m; i++) {
            if (i == 0 || arr[i] != arr[i - 1]) tmp[k++] = arr[i];
        }
        return Arrays.copyOf(tmp, k);
    }

    private static int lowerBound(long[] a, long x) {
        int lo = 0, hi = a.length; // [lo, hi)
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (a[mid] < x) lo = mid + 1;
            else hi = mid;
        }
        return lo;
   }
}