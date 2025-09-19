class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;
        if(n==0){ return 0;}

        int[] left= new int[n];//candies count for left neighbours 
        int[] right=new int[n];//candies count for right neighbours

        Arrays.fill(left,1);//evry child must hv atleast 1 candies
        Arrays.fill(right,1);

        //1. Calculate left[]
        for(int i=1; i<n; i++)
        {
            if(ratings[i]>ratings[i-1])
            {
                left[i]=left[i-1]+1;
            }
        }
        //2. Calculate right[]
        for(int i=n-2; i>=0; i--)
        {
            if(ratings[i]>ratings[i+1])
            {
                right[i]=right[i+1]+1;
            }
        }
        //3.total the max
        int total=0;
        for(int i=0; i<n; i++)
        {
            total=total + Math.max(left[i], right[i]);
        }
        return total;
    }
}