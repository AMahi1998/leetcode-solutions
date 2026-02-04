class Solution {
    public long maxSumTrionic(int[] nums) {
        int n = nums.length;
        long NEG = (long) -4e18;

        long incAnyPrev = nums[0];
        long inc2Prev = NEG;
        long incDecPrev = NEG;
        long incDecIncPrev = NEG;

        long ans = NEG;

        for (int i = 1; i < n; i++) {
            long x = nums[i];

            long incAnyCur = x;
            long inc2Cur = NEG;
            if (nums[i - 1] < nums[i]) {
                incAnyCur = Math.max(x, incAnyPrev + x);
                inc2Cur = incAnyPrev + x;
            }

            long incDecCur = NEG;
            if (nums[i - 1] > nums[i]) {
                long fromPeak = (inc2Prev == NEG) ? NEG : inc2Prev + x;
                long extend   = (incDecPrev == NEG) ? NEG : incDecPrev + x;
                incDecCur = Math.max(fromPeak, extend);
            }

            long incDecIncCur = NEG;
            if (nums[i - 1] < nums[i]) {
                long startLast = (incDecPrev == NEG) ? NEG : incDecPrev + x;
                long extend    = (incDecIncPrev == NEG) ? NEG : incDecIncPrev + x;
                incDecIncCur = Math.max(startLast, extend);
            }

            ans = Math.max(ans, incDecIncCur);

            incAnyPrev = incAnyCur;
            inc2Prev = inc2Cur;
            incDecPrev = incDecCur;
            incDecIncPrev = incDecIncCur;
        }

        return ans;
    }
}