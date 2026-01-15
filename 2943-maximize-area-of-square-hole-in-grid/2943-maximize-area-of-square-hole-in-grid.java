class Solution {
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
         int h = longestConsecutiveRun(hBars);
        int v = longestConsecutiveRun(vBars);

        long side = Math.min((long) h + 1, (long) v + 1);
        return (int) (side * side);
    }

    private int longestConsecutiveRun(int[] bars) {
        Arrays.sort(bars);
        int best = 1;
        int cur = 1;

        for (int i = 1; i < bars.length; i++) {
            if (bars[i] == bars[i - 1] + 1) {
                cur++;
            } else {
                cur = 1;
            }
            best = Math.max(best, cur);
        }
        return best;       
    }
}