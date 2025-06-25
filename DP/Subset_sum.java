 /*
  * Subset Sum Problem
Difficulty: MediumAccuracy: 32.0%Submissions: 363K+Points: 4
Given an array of positive integers arr[] and a value sum, determine if there is a subset of arr[] with sum equal to given sum. 

Examples:

Input: arr[] = [3, 34, 4, 12, 5, 2], sum = 9
Output: true 
Explanation: Here there exists a subset with target sum = 9, 4+3+2 = 9.
Input: arr[] = [3, 34, 4, 12, 5, 2], sum = 30
Output: false
Explanation: There is no subset with target sum 30.
Input: arr[] = [1, 2, 3], sum = 6
Output: true
Explanation: The entire array can be taken as a subset, giving 1 + 2 + 3 = 6.
Constraints:
1 <= arr.size() <= 200
1<= arr[i] <= 200
1<= sum <= 104
  */
 
 
 
 
 class Solution {
    
    static Boolean recursion(int index, int arr[], int sum, int temp, int[][]dp){
        
        if (temp == sum) return true;
        if (index == arr.length || temp > sum)return false;
        
        if (dp[index][temp] != -1){
            if (dp[index][temp] == 1) return true;
            return false;
        }
        
        // pick:
         boolean ans = recursion(index+1, arr, sum, temp+arr[index], dp) || recursion(index
        +1, arr, sum, temp, dp);
        
        if (ans){
            dp[index][temp] = 1;
        } else{
            dp[index][temp] = 0;
        }
        
        return ans;
        
        
    }

    static Boolean isSubsetSum(int arr[], int sum) {
        // code here
        int n = arr.length;
        //   int[][] dp = new int [n][sum];
           
        //   for (int i = 0; i < n; i++){
        //       Arrays.fill(dp[i], -1);
        //   }
          
        //   return recursion(0, arr, sum, 0, dp);
        
        // tabulation:
        
        // ab dekho -> we'll create a 2D dp array (boolean data type) -> jisme row number batayega 
        // number of elements and col batayega sum 
        
        // we have an array -> {1,2,3} target is 6
        
        // so the dp array will look like -> dp[n+1][sum];
        
        // ab 0 elements pe 1 sum possible h ya nhi -> dp[0][1] se pata chalega
        
        
        // 0 elements pe 2 sum possible ha ya nhi -> dp[0][2] se pata chalega and so on
        
        /* ab 0,1,2,3 -> sabhi number of elements pe 0 sum possible hai -> aap koi bhi element
        pick na kro to  -> isliye base case me ham dp[i][0] -> true kr denge
        
        baki sab false rahenge -> cuz 0 element pe 1 sum possible nhi h , 2 sum possible 
        nhi hai so dp[0][1], dp[0][2], dp[0][3], dp[0][4] ...dp[0][6] will be false 
        */
        
        boolean [][] dp = new boolean[n+1][sum+1];
        
        for (int i = 0; i <= n; i++){
            dp[i][0] = true;
        }
        
        /* ab ham loop chalenge => i = 1 to i = n tak -> yani pehle 1 element pe 
        kon konse sum possible hai vo check krenge -> fir 2 elements pe kon-konse sum
        possible hai vo check krenge and so on  */
        
        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= sum; j++){
                
                // dp[1][1] -> kya 1 element pe 1 sum possible hai??
                
                // do not pick the element -> kya 0 elements pe 1 sum possible tha?? no
                dp[i][j] = dp[i-1][j];
                
                
                // pick the element, if sum >= arr[i-1] 
                
                if (j >= arr[i-1]){
                    dp[i][j] = dp[i][j] || dp[i-1][j-arr[i-1]];
                }
                
                // ab is line ka kya matlab hai ??
                
                // manlo muje dp[2][2] nikalna hai -> yani 2 elements pe sum 
                // 2 mile -> jisme maai current elememt include kr rhi hu 
                
                // agar current element ko include krke sum 2 ho -> to iska matlab 
                
                // uske piche muje sum 0 (j - arr[i-1]) chahiye hoga -> tabhi current ko add krke sum 2 banega cuz
                // arr[i-1] is 2 -> yani ham check krenge dp[1][0] true hai ya nhi -> get by
                // dp[i-1][j- arr[i-1]];
                
                
                
            }
        }
        
        /* 0 1 2 3 4 5 6 
        0  T F F F F F F
        1  T T F F F F F 
        2  T T T T F F F 
        3  T T T T T T T
        */
        
        
        // last me ham check krenge  dp[3][6] -> 3 elements pe sum 6 ban raha ya nhi
        
        // return dp[n][sum];
        
        
        
        // Space optimisation :
        
        boolean [] prev = new boolean [sum+1];
        
        prev[0] = true;
        
        for (int i = 1; i <= n; i++){
            boolean [] curr = new boolean[sum+1];
            curr[0] = true;
            for (int j = 1; j <= sum; j++){
                
                // do not pick the element:
                curr[j] = prev[j];
                
                if (j >= arr[i-1]){
                    curr[j] = prev[j] || prev[j-arr[i-1]];
                }
                
            }
            
            prev = curr;
        }
        
        return prev[sum];
        
        
        
        
        
        
        
        
        
        
        
        
        
    }
}