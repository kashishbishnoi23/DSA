import java.util.*;
import java.io.*;

/*
 * 
 * Problem statement
You are given an array 'arr' of size 'n' containing positive integers and a target sum 'k'.



Find the number of ways of selecting the elements from the array such that the sum of chosen elements is equal to the target 'k'.



Since the number of ways can be very large, print it modulo 10 ^ 9 + 7.



Example:
Input: 'arr' = [1, 1, 4, 5]

Output: 3

Explanation: The possible ways are:
[1, 4]
[1, 4]
[5]
Hence the output will be 3. Please note that both 1 present in 'arr' are treated differently.
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1 :
4 5
1 4 4 5


Sample Output 1 :
 3


Explanation For Sample Output 1:
The possible ways are:
[1, 4]
[1, 4]
[5]
Hence the output will be 3. Please note that both 1 present in 'arr' are treated differently.


Sample Input 2 :
3 2
1 1 1


Sample Output 2 :
3


Explanation For Sample Output 1:
There are three 1 present in the array. Answer is the number of ways to choose any two of them.


Sample Input 3 :
3 40
2 34 5


Sample Output 3 :
0


Expected time complexity :
The expected time complexity is O('n' * 'k').


Constraints:
1 <= 'n' <= 100
0 <= 'arr[i]' <= 1000
1 <= 'k' <= 1000

Time limit: 1 sec
 */

public class countSubsetWithSumK {

    private static int recursion(int index, int[] num, int target, int sum, int[][] dp){
         
         if (sum == target) return 1;
         if (sum > target || index == num.length){
            return 0;
         }

         if (dp[index][sum] != -1){
             return dp[index][sum];
         }


         int pick = (sum + num[index]) <= target ? recursion(index+1, num, target, sum+num[index], dp) : 0;

         int notpick = recursion(index+1, num, target, sum, dp);

         return  dp[index][sum] = pick+notpick;
    }
    public static int findWays(int num[], int tar) {
        // Write your code here.
        int n = num.length;
        long [][] dp = new long [n+1][tar+1];

        // for (int i = 0; i <= n; i++){
        //     Arrays.fill(dp[i], -1);
        // }

        // return recursion(0, num, tar, 0, dp);

        // base case:
        for (int i = 0; i <= n; i++){
            dp[i][0] = 1;
        }

        for (int i = 1; i <= n; i++){
            for (int j = 0; j <= tar; j++){
                // not pick:
                dp[i][j] = dp[i-1][j];

                // pick krke possible h to add krlo else purane wala rkho:
                if (j >= num[i-1]){
                    dp[i][j] += dp[i-1][j-num[i-1]];
                }

        }
    }

    return (int)(dp[n][tar] % (1e9 + 7));

        


    }
}