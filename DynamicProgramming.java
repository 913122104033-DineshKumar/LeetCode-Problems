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

    // Memoization
    private static int recursion (int day, int task, int[][] dp, int[][] points) {
        if (day == 0) {
            int maxPoints = 0;
            for (int ts = 0; ts < 3; ts++) {
                if (ts != task) {
                    maxPoints = Math.max(maxPoints, points[0][ts]);
                }
            }
            return maxPoints;
        }
        if (dp[day][task] != -1) {
            return dp[day][task];
        }
        int maxPoints = 0;
        for (int ts = 0; ts < 3; ts++) {
            if (ts != task) {
                int currentPoints = points[day][ts] + recursion(day - 1, ts, dp, points);
                maxPoints = Math.max(maxPoints, currentPoints);
            }
        }
        return dp[day][task] = maxPoints;
    }

    // Tabulation
    public static int ninjaTraining(int n, int points[][]) {
        int[][] dp = new int[n][4];
        dp[0][0] = Math.max(points[0][1], points[0][2]);
        dp[0][1] = Math.max(points[0][0], points[0][2]);
        dp[0][2] = Math.max(points[0][0], points[0][1]);
        dp[0][3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));
        for (int day = 1; day < n; day++) {
            for (int task = 0; task < 4; task++) {
                int maxPoints = 0;
                for (int ts = 0; ts < 3; ts++) {
                    if (ts != task) {
                        int currentPoints = points[day][ts] + dp[day - 1][ts];
                        maxPoints = Math.max(maxPoints, currentPoints);
                    }
                }
                dp[day][task] = maxPoints;
            }
        }
        return dp[n - 1][3];
    }

    // Space Optimization
    public static int ninjaTraining(int n, int points[][]) {
        int[] dp = new int[4];
        dp[0] = Math.max(points[0][1], points[0][2]);
        dp[1] = Math.max(points[0][0], points[0][2]);
        dp[2] = Math.max(points[0][0], points[0][1]);
        dp[3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));
        for (int day = 1; day < n; day++) {
            int[] temp = new int[4];
            for (int task = 0; task < 4; task++) {
                for (int ts = 0; ts < 3; ts++) {
                    if (ts != task) {
                        temp[task] = Math.max(points[day][ts] + dp[ts], temp[task]);
                    }
                }
            }
            dp = temp;
        }
        return dp[3];
    }

    // Memoization
    private int uniquePathsMemoization (int row, int col, int[][] dp) {
        if (row == 0 && col == 0) {
            return 1;
        }
        if (row < 0 || col < 0) {
            return 0;
        }
        if (dp[row][col] != -1) {
            return dp[row][col];
        }
        int up = uniquePathsMemoization(row - 1, col, dp);
        int left = uniquePathsMemoization(row, col - 1, dp);
        return dp[row][col] = up + left;
    }

    // Tabulation
    private int uniquePathsTabulation (int m, int n) {
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (row == 0 && col == 0) {
                    dp[row][col] = 1;
                } else {
                    int up = 0, left = 0;
                    if (row - 1 >= 0) {
                        up = dp[row - 1][col];
                    } 
                    if (col - 1 >= 0) {
                        left = dp[row][col - 1];
                    }
                    dp[row][col] = up + left;
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    // Space Optimization
    private int uniquePathsSpaceOptimization (int m, int n) {
        int[] dp = new int[n];
        for (int row = 0; row < m; row++) {
            int[] temp = new int[n];
            for (int col = 0; col < n; col++) {
                if (row == 0 && col == 0) {
                    temp[col] = 1;
                } else {
                    int up = 0, left = 0;
                    up = dp[col];
                    if (col > 0) {
                        left = temp[col - 1];
                    }
                    temp[col] = up + left;
                }
            }
            dp = temp;
        }
        return dp[n - 1];
    }

}