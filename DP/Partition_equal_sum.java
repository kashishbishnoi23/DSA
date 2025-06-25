
/* 
416. Partition Equal Subset Sum
Solved
Medium
Topics
premium lock icon
Companies
Given an integer array nums, return true if you can partition the array into two subsets such that the sum of the elements in both subsets is equal or false otherwise.

 

Example 1:

Input: nums = [1,5,11,5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].
Example 2:

Input: nums = [1,2,3,5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.

*/





public class Partition_equal_sum {

     private boolean recursion(int index, int[] nums, int target, int sum, int [][] dp){
        if (sum == target) return true;
        if (sum > target || index == nums.length) return false;

        if (dp[index][sum] != -1){
            if (dp[index][sum] == 0) return false;
        }
        


        // pick the element
        if (recursion(index+1, nums, target,sum+nums[index], dp) == true){
            dp[index][sum] = 1;
            return true;
        } else{
            dp[index][sum] = 0;
        }
        
        


        // do not pick the element
        if (recursion(index+1, nums, target, sum, dp) == true){
            dp[index][sum] = 1;
            return true;
        } else{
            dp[index][sum] = 0;
        }

        return false;

        
    }
    public boolean canPartition(int[] nums) {

        // intution -> check kro if sum/2 exists in the array or not -> agar krta hai -> to bache hue elements ka sum bhi sum/2 ho jayega
        
        int n = nums.length;

        int sum = 0;
        for (int num : nums){
            sum += num;
        }

        if (sum % 2 == 1) return false;

        int target = sum/2;

        // ArrayList<Integer> indices = new ArrayList<>();
        
        // int [][] dp = new int [n+1][target+1];

        // for (int i = 0; i <= n; i++){
        //     Arrays.fill(dp[i], -1);
        // }

        
        // return recursion(0, nums, target, 0, dp);

        
        
        

        // tabulation:
        // boolean [][] dp = new boolean [n+1][target+1];
        boolean [] prev = new boolean [target+1];


        // for (int i = 0; i <= n; i++){
        //     dp[i][0] = true;
        // }
        prev[0] = true;

        for (int i = 1; i <= n; i++){
            boolean [] curr = new boolean [target+1];
            for (int j = 1; j <= target; j++){

                // do not pick the element:
                // dp[i][j] = dp[i-1][j];
                curr[j] = prev[j];

                // pick the element:
                if (nums[i-1] <= j){
                    // dp[i][j] = dp[i][j] || dp[i-1][j-nums[i-1]];
                    curr[j] = curr[j] || prev[j- nums[i-1]];
                }

            }
            prev = curr;
        }

        // return dp[n][target];
        return prev[target];

       





    }
}
