class Solution {
    private static final long MOD = 1_000_000_007L;

    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        int[] hs = addBordersAndSort(hFences, m);
        int[] vs = addBordersAndSort(vFences, n);

        HashSet<Integer> hDiffs = new HashSet<>();
        for (int i = 0; i < hs.length; i++) {
            for (int j = i + 1; j < hs.length; j++) {
                hDiffs.add(hs[j] - hs[i]);
            }
        }

        int bestSide = 0;

        for (int i = 0; i < vs.length; i++) {
            for (int j = i + 1; j < vs.length; j++) {
                int d = vs[j] - vs[i];
                if (d > bestSide && hDiffs.contains(d)) {
                    bestSide = d;
                }
            }
        }

        if (bestSide == 0) return -1;

        long side = bestSide;
        return (int) ((side * side) % MOD);
    }

    private int[] addBordersAndSort(int[] fences, int limit) {
        int[] arr = new int[fences.length + 2];
        arr[0] = 1;
        arr[1] = limit;
        for (int i = 0; i < fences.length; i++) {
            arr[i + 2] = fences[i];
        }
        Arrays.sort(arr);
        return arr;      
    }
}