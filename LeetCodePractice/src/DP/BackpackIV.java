package DP;
//Given n items with size nums[i] which an integer array and all positive numbers, no duplicates. 
//An integer target denotes the size of a backpack. Find the number of possible fill the backpack.
// each item can be used unlimited times
public class BackpackIV {
	/**
     * @param nums an integer array and all positive numbers, no duplicates
     * @param target an integer
     * @return an integer
     */
    public int backPackIV2D(int[] nums, int target) {
        int len = nums.length;
        int dp[][] = new int[len + 1][target + 1];
        
        dp[0][0] = 1;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j <= target; j++) {
                int k = 0; 
                while(k * nums[i - 1] <= j) {
                    dp[i][j] += dp[i - 1][j - nums[i - 1] * k];
                    k+=1;
                }
            }
        }  
        return dp[len][target];
    }
    
    public int backPackIV(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; ++i)
            for (int  j = nums[i]; j <= target; ++j)
                dp[j] += dp[j - nums[i]];

        return dp[target];
    }
}