/*
 * 494. Target Sum
Solved
Medium
Topics
premium lock icon
Companies
You are given an integer array nums and an integer target.

You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and then concatenate all the integers.

For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".
Return the number of different expressions that you can build, which evaluates to target.

 

Example 1:

Input: nums = [1,1,1,1,1], target = 3
Output: 5
Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
-1 + 1 + 1 + 1 + 1 = 3
+1 - 1 + 1 + 1 + 1 = 3
+1 + 1 - 1 + 1 + 1 = 3
+1 + 1 + 1 - 1 + 1 = 3
+1 + 1 + 1 + 1 - 1 = 3
Example 2:

Input: nums = [1], target = 1
Output: 1
 

Constraints:

1 <= nums.length <= 20
0 <= nums[i] <= 1000
0 <= sum(nums[i]) <= 1000
-1000 <= target <= 1000
Seen this question in a real interview before?
1/5
 */


 /*
  * intution -> for example I have an array -> {1,1,1,1,1} target is 3 -> to ab mai agar ek combination banati hu 1+1-1+1-1 -> I can write it like -> (1+1+1)-(1+1) -> yani subsetsum1 - subsetsum2   ->now this should be equal to target

    sum1 + sum2 = totalsum
    sum1 - sum2 = target

    sum1 = (totalsum+target)/2;

    // asa ek hi valid pair hoga sum1 aur sum2 ka -> so find out how many subsets exist with sum1 -> now if target+totalsum is an odd number -> sum1 will not be an integer so simply return 0 and if target > totalsum or less than -totalsum -> return 0 

  */


 class targetSum {

    private int recursive(int index, int[] nums, int target, int sum){

        if (index == nums.length){
            if (sum == target){
                return 1;
            } else{
                return 0;
            }
        }

        // take plus 1 
        int left = recursive(index+1, nums, target, sum+nums[index]);


        // take minus 1
        int right = recursive(index+1, nums, target, sum-nums[index]);

        return left + right;
    }
    public int findTargetSumWays(int[] nums, int target) {
        // return recursive(0, nums, target, 0);

        int n = nums.length;
        int sum = 0;

        for (int num : nums) sum += num;

        /* sum1 + sum2 = totalsum 
        sum1 - sum2 = target

        solving these 2 equations, we get sum1 = (totalsum + target)/2

        // we simply have to find the number of subsets with sum equal to sum1 

        if totalsum + target is odd -> sum1 will not be an integer so we simply return 0 

        */

        if ((sum+target) % 2 == 1 || target > sum || target < (-sum)) return 0;

        int subset1 = (sum+target)/2;

        // ham store krwa rahe hain ki ek sum kitne subsets se ban raha hai 

        int [][] dp = new int [n+1][subset1+1];

        for (int i = 0; i <= n; i++) dp[i][0] = 1;

        for (int i = 1; i <= n; i++){
            for (int j = 0; j <= subset1; j++){
                // do not pick the element:
                dp[i][j] = dp[i-1][j];

                // pick the element:
                if (nums[i-1] <= j){
                    dp[i][j] += dp[i-1][j-nums[i-1]];
                }

            }
        }

        return dp[n][subset1];



       






    }
}