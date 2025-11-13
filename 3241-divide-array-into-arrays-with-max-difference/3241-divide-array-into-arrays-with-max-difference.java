class Solution {
    public int[][] divideArray(int[] nums, int k) {
        int n = nums.length;
        
        if (n % 3 != 0) {
            return new int[0][];
        }
        
        Arrays.sort(nums);
        
        int[][] res = new int[n / 3][3];
        int idx = 0;         
        for (int i = 0; i < n; i += 3) {
            
            if (nums[i + 2] - nums[i] > k) {
                return new int[0][];
            }
            
            res[idx][0] = nums[i];
            res[idx][1] = nums[i + 1];
            res[idx][2] = nums[i + 2];
            idx++;
        }
        
        return res;
        
    }
}