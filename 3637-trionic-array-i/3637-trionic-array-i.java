class Solution {
    public boolean isTrionic(int[] nums) {
        int n = nums.length;
        if (n < 4) return false;

        int i = 0;

        while (i + 1 < n && nums[i] < nums[i + 1]) i++;
        int p = i;
        if (p == 0) return false;
        if (p == n - 1) return false;

        while (i + 1 < n && nums[i] > nums[i + 1]) i++;
        int q = i;
        if (q == p) return false;
        if (q == n - 1) return false;

        while (i + 1 < n && nums[i] < nums[i + 1]) i++;

        return i == n - 1; 
    }
}