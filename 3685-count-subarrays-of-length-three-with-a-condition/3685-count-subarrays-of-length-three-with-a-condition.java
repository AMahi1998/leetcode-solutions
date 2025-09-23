class Solution {
    public int countSubarrays(int[] nums) {
        int cnt = 0;
        for (int i = 0; i + 2 < nums.length; i++) {
            int a = nums[i], b = nums[i + 1], c = nums[i + 2];
            if (2 * (a + c) == b) cnt++;
        }
        return cnt;
    }
}
