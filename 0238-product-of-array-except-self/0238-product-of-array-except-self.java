class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n =nums.length;
        int[] result= new int[n];

        //1.Product of Prefix and store in result arr
        result[0]=1;
        for(int i=1; i<n; i++)
        {
            result[i]=result[i-1]*nums[i-1];
        }
        //2.Product of Suffix by traversing reversee
        int suffix=1;
        for(int i=n-1; i >=0; i--)
        {
            result[i]=result[i]*suffix;
            suffix=suffix*nums[i];
        }
        return result;
    }
}