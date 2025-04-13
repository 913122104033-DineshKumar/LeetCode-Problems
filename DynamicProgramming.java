public class DynamicProgramming {
    
    // Fibannoci Series in recursion format
    private int recursion (int n, int[] dp) {
        if (n <= 1) {
            return n;
        }
        if (dp[n] != -1) {
            return dp[n];
        }
        return recursion(n - 1, dp) + recursion(n - 2, dp);
    }

    // Tabulation Way of Fibannoci Series
    public int fib (int n) {
        if (n == 0) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // Frog Jump - Space Optimized solution
    private static int frogJump(int n, int heights[]) {
        int prev1 = 0;
        int prev2 = 0;
        int current = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            int left = prev1 + Math.abs(heights[i] - heights[i - 1]);
            int right  = Integer.MAX_VALUE;
            if (i > 1) {
                right = prev2 + Math.abs(heights[i] - heights[i - 2]);
            }
            current = Math.min(left, right);
            prev2 = prev1;
            prev1 = current;
        }
        return prev1;
    }

    // Maximum Sum of non-adjacent elements - Memoization
    private static int recursion (int n, int[] dp, ArrayList<Integer> nums) {
		if (n == 0) {
			return nums.get(n);
		}
		if (n < 0) {
			return 0;
		}
		if (dp[n] != -1) {
			return dp[n];
		}
		int left = nums.get(n) + recursion(n - 2, dp, nums);
		int right = recursion(n - 1, dp, nums);
		return dp[n] = Math.max(left, right);
	}

    
}