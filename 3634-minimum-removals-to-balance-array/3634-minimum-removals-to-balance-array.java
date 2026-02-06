class Solution {
    public int minRemoval(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int best = 1;
        int l = 0;

        for (int r = 0; r < n; r++) {
            while (l <= r && (long) nums[r] > (long) nums[l] * (long) k) {
                l++;
            }
            best = Math.max(best, r - l + 1);
        }
        return n - best;
    }
}