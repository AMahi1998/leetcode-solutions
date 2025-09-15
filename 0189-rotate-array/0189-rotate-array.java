class Solution {
    public void rotate(int[] nums, int k) {
        int n=nums.length;
        k =k%n;

        //1.Reverse the entire array
        reverse(nums, 0, n-1);
        //2. reverse the first k elements
        reverse(nums, 0, k-1);
        //3.reverse the rest
        reverse(nums, k, n-1);
    }
    public void reverse(int[] nums, int left, int right)
    {
        while(left<right)
        {
            int temp=nums[left];
            nums[left]=nums[right];
            nums[right]=temp;
            left++;
            right--;
        }
    }
}