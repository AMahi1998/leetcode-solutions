class Solution {
    public int partitionArray(int[] nums, int k) {
        Arrays.sort(nums);
        
        int count = 1;              
        int currentMin = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - currentMin > k) {
    
                count++;
                currentMin = nums[i];
            }
            
        }
        
        return count;
        
    }
}