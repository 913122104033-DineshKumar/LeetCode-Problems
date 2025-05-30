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

    // Memoization
    private int uniquePathsWithObstaclesMemoization (int row, int col, int[][] dp, int[][] grid) {
        if (row == 0 && col == 0 && grid[row][col] != 1) {
            return 1;
        }
        if (row < 0 || col < 0 || grid[row][col] == 1) {
            return 0;
        }
        if (dp[row][col] != -1) {
            return dp[row][col];
        }
        int up = uniquePathsWithObstaclesMemoization(row - 1, col, dp, grid);
        int left = uniquePathsWithObstaclesMemoization(row, col - 1, dp, grid);
        return dp[row][col] = up + left;
    }

    // Tabulation
    private int uniquePathsWithObstaclesTabulation (int m, int n, int[][] grid) {
        int[][] dp = new int[m][n];
        if (grid[0][0] != 1) {
            dp[0][0] = 1;
        }
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (row == 0 && col == 0 && grid[row][col] != 1) {
                    dp[row][col] = 1;
                } else {
                    int up = 0, left = 0;
                    if (row > 0 && grid[row][col] != 1) {
                        up = dp[row - 1][col];
                    }
                    if (col > 0 && grid[row][col] != 1) {
                        left = dp[row][col - 1];
                    }
                    dp[row][col] = up + left;
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    // Memoization
    private int minPathSumMemoization (int row, int col, int[][] dp, int[][] grid) {
        if (row == 0 && col == 0) {
            return grid[0][0];
        }
        if (row < 0 || col < 0) {
            return (int) 1e9;
        }
        if (dp[row][col] != Integer.MAX_VALUE) {
            return dp[row][col];
        }
        int up = grid[row][col] + minPathSumMemoization(row - 1, col, dp, grid);
        int left = grid[row][col] + minPathSumMemoization(row, col - 1, dp, grid);
        return dp[row][col] = Math.min(up, left);
    }

    // Tabulation
    private int minPathSumTabulation (int m, int n, int[][] grid) {
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int row = 0; row < m; row++)  {
            for (int col = 0; col < n; col++) {
                if (row == 0 && col == 0) {
                    dp[row][col] = grid[row][col];
                } else {
                    int up = grid[row][col];
                    int left = grid[row][col];
                    if (row > 0) {
                        up += dp[row - 1][col];
                    } else {
                        up += (int) 1e9;
                    }
                    if (col > 0) {
                        left += dp[row][col - 1];
                    } else {
                        left += (int) 1e9;
                    }
                    dp[row][col] = Math.min(up, left);
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    // Space Optimization
    private int minPathSumSpaceOptimization (int m, int n, int[][] grid) {
        int[] dp = new int[n];
        Arrays.fill(dp, (int) 1e9);
        for (int row = 0; row < m; row++) {
            int[] temp = new int[n];
            for (int col = 0; col < n; col++) {
                if (row == 0 && col == 0) {
                    temp[0] = grid[0][0];
                } else {
                    int up = grid[row][col] + dp[col];
                    int left = grid[row][col];
                    if (col > 0) {
                        left += temp[col - 1];
                    } else {
                        left += (int) 1e9;
                    }
                    temp[col] = Math.min(up, left);
                }
            }
            dp = temp;
        }
        return dp[n - 1];
    }

    // Memoization
    private int triangleMemoization (int row, int col, List<List<Integer>> triangle, List<List<Integer>> dp) {
        if (row == triangle.size() - 1) {
            return triangle.get(row).get(col);
        }
        if (dp.get(row).get(col) != (int) 1e9) {
            return dp.get(row).get(col);
        }
        int down = triangle.get(row).get(col) + triangleMemoization(row + 1, col, triangle, dp);
        int diagonal = triangle.get(row).get(col) + triangleMemoization(row + 1, col + 1, triangle, dp);
        int min = Math.min(down, diagonal);
        dp.get(row).set(col, min);
        return min;
    }

    // Space Optimization
    private int triangleSpaceOptimization (List<List<Integer>> triangle) {
        int n = triangle.size();
        if (n == 1) {
            return triangle.get(0).get(0);
        }
        List<Integer> dp = triangle.get(n - 1);
        for (int row = n - 2; row > 0; row--) {
            List<Integer> temp = new ArrayList<>();
            for (int col = 0; col < row + 1; col++) {
                if (row == 0 && col == 0) {
                    dp.add(triangle.get(row).get(col));
                    continue;
                } else {
                    int diagonal = triangle.get(row).get(col) + dp.get(col);
                    int up = triangle.get(row).get(col) + dp.get(col + 1);
                    temp.add(Math.min(up, diagonal));
                }
            }
            dp = temp;
        }
        int min = (int) 1e9;
        for (int val : dp) {
            min = Math.min(min, val);
        }
        return min + triangle.get(0).get(0);
    }

    // Memoization
	private static int maximumChocolatesMemoization (int row, int col1, int col2, int m, int n, int[][] grid, 
	int[][][] dp) {
		if (col1 < 0 || col1 >= n || col2 < 0 || col2 >= n) {
			return -(int) 1e9;
		}
		if (row == m - 1) {
			if (col1 == col2) {
				return grid[row][col1];
			}
			return grid[row][col1] + grid[row][col2];
		}
		if (dp[row][col1][col2] != -1) {
			return dp[row][col1][col2];
		}
		int max = 0;
		for (int alice = -1; alice <= 1; alice++) {
			for (int bob = -1; bob <= 1; bob++) {
				if (col1 == col2) {
					max = Math.max(
						max, 
						grid[row][col1] + 
						maximumChocolatesMemoization(row + 1, col1 + alice, col2 + bob, 
						m, n, grid, dp)
					);
				} else {
					max = Math.max(
						max, 
						grid[row][col1] + grid[row][col2] +
						maximumChocolatesMemoization(row + 1, col1 + alice, col2 + bob, 
						m, n, grid, dp)
					);
				}
			}
		}
		return dp[row][col1][col2] = max;
	}

	// Tabulation
	private static int maximumChocolatesTabulation (int r, int c, int[][] grid) {
		int[][][] dp = new int[r][c][c];
		for (int col1 = 0; col1 < c; col1++) {
			for (int col2 = 0; col2 < c; col2++) {
				if (col1 == col2) {
					dp[r - 1][col1][col2] = grid[r - 1][col1];
				} else {
					dp[r - 1][col1][col2] = grid[r - 1][col1] + grid[r - 1][col2];
				}
			}
		}
		for (int row = r - 2; row >= 0; row--) {
			for (int col1 = 0; col1 < c; col1++) {
				for (int col2 = 0; col2 < c; col2++) {
					int max = 0;
					for (int alice = -1; alice <= 1; alice++) {
						for (int bob = -1; bob <= 1; bob++) {
							int val = -(int) 1e9;
							if (col1 == col2) {
								if (col1 + alice >= 0 && col1 + alice < c &&
									col2 + bob >= 0 && col2 + bob < c) {
									val = grid[row][col1] + 
									dp[row + 1][col1 + alice][col2 + bob];
								}
								max = Math.max(max, val);
							} else {
								if (col1 + alice >= 0 && col1 + alice < c &&
									col2 + bob >= 0 && col2 + bob < c) {
									val = grid[row][col1] + grid[row][col2] +
									dp[row + 1][col1 + alice][col2 + bob];
								}
								max = Math.max(max, val);
							}
						}
					}
					dp[row][col1][col2] = max;
				}
			}
		}
		return dp[0][0][c - 1];
	}

    // Memoization
    private static boolean subsetSumToKMemoization (int idx, int target, int[] arr, int[][] dp) {
        if (target == 0) {
            return true;
        }
        if (idx == 0) {
            return arr[idx] == target;
        }
        if (dp[idx][target] != -1) {
            return dp[idx][target] == 0 ? false : true;
        }
        boolean notTake = subsetSumToKMemoization(idx - 1, target, arr, dp);
        boolean take = false;
        if (target >= arr[idx]) {
            take = subsetSumToKMemoization(idx - 1, target - arr[idx], arr, dp);
        }
        dp[idx][target] = (notTake || take) ? 1 : 0;
        return notTake || take;
    }

    // Tabulation
    private static boolean subsetSumToKTabulation (int n, int k, int[] arr) {
        boolean[][] dp = new boolean[n][k + 1];
        for (int row = 0; row < n; row++) {
            dp[row][0] = true;
        }
        if (arr[0] <= k) {
            dp[0][arr[0]] = true;
        }
        for (int row = 1; row < n; row++) {
            for (int target = 1; target <= k; target++) {
                boolean notTake = dp[row - 1][target];
                boolean take = false;
                if (target >= arr[row]) {
                    take = dp[row - 1][target - arr[row]];
                }
                dp[row][target] = notTake || take;
            }
        }
        return dp[n - 1][k];
    }

    // Memoization
    private boolean canPartitionMemoization (int idx, int target, int[] nums, int[][] dp) {
        if (target == 0) {
            return true;
        }
        if (idx == 0) {
            return nums[idx] == target;
        }
        if (dp[idx][target] != -1) {
            return dp[idx][target] == 1 ? true : false;
        }
        boolean notTake = canPartitionMemoization(idx - 1, target, nums, dp);
        boolean take = false;
        if (nums[idx] <= target) {
            take = canPartitionMemoization(idx - 1, target - nums[idx], nums, dp);
        }
        dp[idx][target] = (notTake || take) ? 1 : 0;
        return notTake || take;
    }

    // Space Optimization
    private boolean canPartitionSpaceOptimization (int n, int k, int[] nums) {
        boolean[] dp = new boolean[k + 1];
        dp[0] = true;
        if (nums[0] <= k) {
            dp[nums[0]] = true;
        }
        for (int row = 1; row < n; row++) {
            boolean[] cur = new boolean[k + 1];
            cur[0] = true;
            for (int target = 1; target  <= k; target++) {
                boolean notTake = dp[target];
                boolean take = false;
                if (nums[row] <= target) {
                    take = dp[target - nums[row]];
                }
                cur[target] = notTake || take;
            }
            dp = cur;
        }
        return dp[k];
    }

     private static int findWaysMemoization (int idx, int target, int[] nums, int[][] dp) {
        if (idx == 0) {
            if (target == 0 && nums[idx] == 0) {
                return 2;
            }
            if (target == 0 || target == nums[idx]) {
                return 1;
            }
            return 0;
        }
        if (dp[idx][target] != -1) {
            return dp[idx][target];
        }
        int notTake = findWaysMemoization(idx - 1, target, nums, dp);
        int take = 0;
        if (nums[idx] <= target) {
            take = findWaysMemoization(idx - 1, target - nums[idx], nums, dp);
        }
        return dp[idx][target] = (notTake + take) % (int) (1e9 + 7);
    }

    private static int findWaysTabulation (int n, int[] nums, int k) {
        int[][] dp = new int[n][k + 1];
        for (int row = 0; row < n; row++) {
            dp[row][0] = 1;
        }
        if (nums[0] <= k) {
            dp[0][nums[0]] = 1;
        }
        for (int row = 1; row < n; row++) {
            for (int target = 1; target <= k; target++) {
                int notTake = dp[row - 1][target];
                int take = 0;
                if (nums[row] <= target) {
                    take = dp[row - 1][target - nums[row]];
                }
                dp[row][target] = notTake + take;
            }
        }
        return dp[n - 1][k];
    }


    static int knapsackMemoization (int idx, int w, int[] wt, int[] val, int[][] dp) {
        if (idx == 0) {
            if (wt[0] <= w) {
                return val[0];
            }
            return 0;
        }
        if (dp[idx][w] != -1) {
            return dp[idx][w];
        }
        int notTake = knapsackMemoization(idx - 1, w, wt, val, dp);
        int take = -(int) 1e9;
        if (wt[idx] <= w) {
            take = val[idx] + knapsackMemoization(idx - 1, w - wt[idx], wt, val, dp);
        }
        return dp[idx][w] = Math.max(notTake, take);
    } 

    static int knapsackTabulation (int n, int[] wt, int[] val, int w) {
        int[][] dp = new int[n][w + 1];
        for (int i = wt[0]; i <= w; i++) {
            dp[0][i] = val[0];
        }
        for (int row = 1; row < n; row++) {
            for (int weight = 0; weight <= w; weight++) {
                int notTake = dp[row - 1][weight];
                int take = -(int) 1e9;
                if (wt[row] <= weight) {
                    take = val[row] + dp[row - 1][weight - wt[row]];
                }
                dp[row][weight] = Math.max(notTake, take);
            }
        }
        return dp[n - 1][w];
    }

    static int knapsackSpaceOptimization (int n, int[] wt, int[] val, int w) {
        int[] dp = new int[w + 1];
        for (int i = wt[0]; i <= w; i++) {
            dp[i] = val[0];
        }
        for (int row = 1; row < n; row++) {
            for (int weight = w; weight >= 0; weight--) {
                int notTake = dp[weight];
                int take = -(int) 1e9;
                if (wt[row] <= weight) {
                    take = val[row] + dp[weight - wt[row]];
                }
                dp[weight] = Math.max(notTake, take);
            }
        }
        return dp[w];
    }

    private int coinChangeMemoization (int idx, int target, int[] coins, int[][] dp) {
        if (idx == 0) {
            if (target % coins[idx] == 0) {
                return  target / coins[idx];
            } 
            return (int) 1e9;
        }
        if (dp[idx][target] != (int) 1e9) {
            return dp[idx][target];
        }
        int notTake = coinChangeMemoization(idx - 1, target, coins, dp);
        int take = (int) 1e9;
        if (coins[idx] <= target) {
            take = 1 + coinChangeMemoization(idx, target - coins[idx], coins, dp);
        }
        return dp[idx][target] = Math.min(notTake, take);
    }

    private int coinChangeTabulation (int n, int[] coins, int target) {
        int[][] dp = new int[n][target + 1];
        for (int T = 0; T <= target; T++) {
            if (T % coins[0] == 0) {
                dp[0][T] = T / coins[0];
            } else {
                dp[0][T] = (int) 1e9;
            }
        }
        for (int row = 1; row < n; row++) {
            for (int T = 0; T <= target; T++) {
                int notTake = dp[row - 1][T];
                int take = (int) 1e9;
                if (coins[row] <= T) {
                    take = 1 + dp[row][T - coins[row]];
                } 
                dp[row][T] = Math.min(notTake, take);
            }
        }
        return dp[n - 1][target];
    }

    public static int targetSum(int n, int target, int[] arr) {
        int totSum = 0;
        for (int num : arr) {
            totSum += num;
        }
        if ((totSum - target) % 2 == 1) {
            return 0;
        }
    	Map<Integer, Integer> dp = new HashMap<>();
        int s2 = (totSum - target) / 2;
        dp.put(0, 1);
        Map<Integer, Integer> cur = new HashMap<>();
        for (int row = 0; row < n; row++) {
            cur.clear();
            for (int T = 0; T <= s2; T++) {
                int notTake = dp.getOrDefault(T, 0);
                int take = 0;
                if (arr[row] <= T) {
                    take = dp.getOrDefault(T - arr[row], 0);
                }
                cur.put(T, notTake + take);
            }
            dp = new HashMap<>(cur);
        }
        return dp.getOrDefault(s2, 0);
    }

    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[] dp = new int[amount + 1];
        for (int am = 0; am <= amount; am++) {
            if (am % coins[0] == 0) {
                dp[am] = 1;
            }
        }
        for (int row = 1; row < n; row++) {
            int[] cur = new int[amount + 1];
            for (int am = 0; am <= amount; am++) {
                int notTake = dp[am];
                int take = 0;
                if (coins[row] <= am) {
                    take = cur[am - coins[row]];
                }
                cur[am] = notTake + take;
            }
            dp = cur;
        }
        return dp[amount];
    }

    private static int unboundedKnapsackMemoization (int idx, int w, 
    int[] profit, int[] weight, int[][] dp) {
        if (idx == 0) {
            return (w / weight[idx]) * profit[idx];
        }
        if (dp[idx][w] != -(int) 1e9) {
            return dp[idx][w];
        }
        int notTake = unboundedKnapsackMemoization(idx - 1, w, profit, weight, dp);
        int take = -(int) 1e9;
        if (weight[idx] <= w) {
            take = profit[idx] + 
            unboundedKnapsackMemoization(idx, w - weight[idx], profit, weight, dp);
        }
        return dp[idx][w] = Math.max(notTake, take);
    }

    public static int unboundedKnapsack(int n, int w, int[] profit, int[] weight) {
        int[] dp = new int[w + 1];
        for (int wt = weight[0]; wt <= w; wt++) {
            dp[wt] = wt / weight[0] * profit[0];
        }
        for (int row = 1; row < n; row++) {
            int[] cur = new int[w + 1];
            for (int wt = 0; wt <= w; wt++) {
                int notTake = dp[wt];
                int take = -(int) 1e9;
                if (weight[row] <= wt) {
                    take = profit[row] + cur[wt - weight[row]];
                }
                cur[wt] = Math.max(notTake, take);
            }
            dp = cur;
        }
        return dp[w];
    }

    private static int cutRodMemoization (int idx, int target, int[] price, int[][] dp) {
		if (idx == 0) {
			return target * price[idx];
		}
		if (dp[idx][target] != -(int) 1e9) {
			return dp[idx][target];
		}
		int notTake = cutRodMemoization(idx - 1, target, price, dp);
		int take = -(int) 1e9;
		if (idx + 1 <= target) {
			take = price[idx] + 
			cutRodMemoization(idx, target - (idx + 1), price, dp);
		}
		return dp[idx][target] = Math.max(notTake, take);
	}

	private static int cutRodTabulation (int n, int[] price) {
		int[][] dp = new int[n][n + 1];
		for (int len = 0; len <= n; len++) {
			dp[0][len] = len * price[0];
		}
		for (int row = 1; row < n; row++) {
			for (int target = 0; target <= n; target++) {
				int notTake = dp[row - 1][target];
				int take = -(int) 1e9;
				if (row + 1 <= target) {
					take = price[row] + dp[row][target - (row + 1)];
				}
				dp[row][target] = Math.max(notTake, take);
			}
		}
		return dp[n - 1][n];
	}

    private int lcsMemoization (int idx1, int idx2, String s1, String s2, int[][] dp) {
        if (idx1 < 0 || idx2 < 0) {
            return 0;
        }
        if (dp[idx1][idx2] != -1) {
            return dp[idx1][idx2];
        }
        if (s1.charAt(idx1) == s2.charAt(idx2)) {
            return dp[idx1][idx2] = 1 + lcsMemoization(idx1 - 1, idx2 - 1, s1, s2, dp);
        }
        return dp[idx1][idx2] = Math.max(lcsMemoization(idx1 - 1, idx2, s1, s2, dp), lcsMemoization(idx1, idx2 - 1, s1, s2, dp));
    }

    private int lcsTabulation (int n, int m, String s1, String s2) {
        int[][] dp = new int[n + 1][m + 1];
        for (int idx1 = 1; idx1 <= n; idx1++) {
            for (int idx2 = 1; idx2 <= m; idx2++) {
                if (s1.charAt(idx1 - 1) == s2.charAt(idx2 - 1)) {
                    dp[idx1][idx2] = 1 + dp[idx1 - 1][idx2 - 1];
                } else {
                    dp[idx1][idx2] = Math.max(dp[idx1 - 1][idx2], dp[idx1][idx2 - 1]);
                }
            }
        }
        return dp[n][m];
    }

    private int lcsSpaceOptimization (int n, int m, String s1, String s2) {
        int[] dp = new int[m + 1];
        for (int idx1 = 1; idx1 <= n; idx1++) {
            int[] cur = new int[m + 1];
            for (int idx2 = 1; idx2 <= m; idx2++) {
                if (s1.charAt(idx1 - 1) == s2.charAt(idx2 - 1)) {
                    cur[idx2] = 1 + dp[idx2 - 1];
                } else {
                    cur[idx2] = Math.max(dp[idx2], cur[idx2 - 1]);
                }
            }
            dp = cur;
        }
        return dp[m];
    }

    public static int longestCommonSubstring(String s1, String s2){
        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n + 1][m + 1];
        int ans = 0;
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= m; col++) {
                if (s1.charAt(row -1) == s2.charAt(col - 1)) {
                    dp[row][col] = 1 + dp[row - 1][col - 1];
                } else {
                    dp[row][col] = 0;
                }
                ans = Math.max(ans, dp[row][col]);
            }
        }
        return ans;
    }

    private int makeStringPalindromeSpaceOptimization (String s1, String s2, int n) {
        int[] dp = new int[n + 1];
        for (int idx1 = 1; idx1 <= n; idx1++) {
            int[] cur = new int[n + 1];
            for (int idx2 = 1; idx2 <= n; idx2++) {
                if (s1.charAt(idx1 - 1) == s2.charAt(idx2 - 1)) {
                    cur[idx2] = 1 + dp[idx2 - 1];
                } else {
                    cur[idx2] = Math.max(dp[idx2], cur[idx2 - 1]);
                }
            }
            dp = cur;
        }
        return n - dp[n];
    }

    public int minInsertionsToMakeStringPalindrome(String s1) {
        int n = s1.length();
        StringBuilder sb = new StringBuilder(s1);
        String s2 = new String(sb.reverse());
        return makeStringPalindromeSpaceOptimization(s1, s2, n);
        int[][] dp = new int[n + 1][n + 1];
        for (int idx1 = 1; idx1 <= n; idx1++) {
            for (int idx2 = 1; idx2 <= n; idx2++) {
                if (s1.charAt(idx1 - 1) == s2.charAt(idx2 - 1)) {
                    dp[idx1][idx2] = 1 + dp[idx1 - 1][idx2 - 1];
                } else {
                    dp[idx1][idx2] = Math.max(dp[idx1 - 1][idx2], dp[idx1][idx2 - 1]);
                }
            }
        }
        return n - dp[n][n];
    }

    public int minDistanceToMakeStringsEqual(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int[] dp = new int[m + 1];
        for (int ind1 = 1; ind1 <= n; ind1++) {
            int[] cur = new int[m + 1];
            for (int ind2 = 1; ind2 <= m; ind2++) {
                if (s1.charAt(ind1 - 1) == s2.charAt(ind2 - 1)) {
                    cur[ind2] = 1 + dp[ind2 - 1];
                } else {
                    cur[ind2] = Math.max(dp[ind2], cur[ind2 - 1]);
                }
            }
            dp = cur;
        }
        return n + m - (2 * dp[m]);
    }

    public String shortestCommonSupersequence(String s, String t) {
        int n = s.length();
        int m = t.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int ind1 = 1; ind1 <= n; ind1++) {
            for (int ind2 = 1; ind2 <= m; ind2++) {
                if (s.charAt(ind1 - 1) == t.charAt(ind2 - 1)) {
                    dp[ind1][ind2] = 1 + dp[ind1 - 1][ind2 - 1];
                } else {
                    dp[ind1][ind2] = Math.max(dp[ind1 - 1][ind2], dp[ind1][ind2 - 1]);
                }
            }
        }
        int ind1 = n, ind2 = m;
        StringBuilder sb = new StringBuilder();
        while (ind1 > 0 && ind2 > 0) {
            if (s.charAt(ind1 - 1) == t.charAt(ind2 - 1)) {
                sb.append(s.charAt(ind1 - 1));
                ind1--;
                ind2--;
            } else if (dp[ind1 - 1][ind2] > dp[ind1][ind2 - 1]) {
                sb.append(s.charAt(ind1 - 1));
                ind1--;
            } else {
                sb.append(t.charAt(ind2 - 1));
                ind2--;
            }
        }
        while (ind1-- > 0) {
            sb.append(s.charAt(ind1));
        } 
        while (ind2-- > 0) {
            sb.append(t.charAt(ind2));
        }
        return sb.reverse().toString();
    }

    private int numDistinctMemoization (int ind1, int ind2, String s, String t, int[][] dp) {
        if (ind2 < 0) {
            return ind1 + 1;
        }
        if (ind1 < 0) {
            return ind2 + 1;
        }
        if (dp[ind1][ind2] != -1) {
            return dp[ind1][ind2];
        }
        int match = (int) 1e9;
        int notMatch = (int) 1e9;
        if (s.charAt(ind1) == t.charAt(ind2)) {
            match = numDistinctMemoization(ind1 - 1, ind2 - 1, s, t, dp);
        } else {
            int insert = numDistinctMemoization(ind1, ind2 - 1, s, t, dp);
            int delete = numDistinctMemoization(ind1 - 1, ind2, s, t, dp);
            int replace = numDistinctMemoization(ind1 - 1, ind2 - 1, s, t, dp);
            notMatch = 1 + Math.min(insert, Math.min(delete, replace));
        }
        return dp[ind1][ind2] = Math.min(match, notMatch);
    }

    private boolean canMatch (int ind1, int ind2, String s, String p, int[][] dp) {
        if (ind1 < 0 && ind2 < 0) {
            return true;
        }
        if (ind1 < 0 && ind2 >= 0) {
            return false;
        }
        if (ind2 < 0 && ind1 >= 0) {
            for (int ind = 0; ind <= ind1; ind++) {
                if (s.charAt(ind) != '*') {
                    return false;
                }
            }
            return true;
        }
        if (dp[ind1][ind2] != -1) {
            return dp[ind1][ind2] == 1 ? true : false;
        }
        boolean match = false;
        boolean notMatch = false;
        if ((s.charAt(ind1) == p.charAt(ind2)) || s.charAt(ind1) == '?') {
            match = canMatch(ind1 - 1, ind2 - 1, s, p, dp);
        }
        if (s.charAt(ind1) == '*') {
            notMatch = canMatch(ind1 - 1, ind2, s, p, dp) || canMatch(ind1, ind2 - 1, s, p, dp);
        }
        dp[ind1][ind2] = (match || notMatch) ? 1 : 0;
        return match || notMatch;
    }

    private boolean canMatchTabulation (int n, int m, String s, String p) {
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        for (int ind = 1; ind <= n; ind++) {
            boolean flag = true;
            for (int i = 1; i <= ind; i++)  {
                if (s.charAt(i - 1) != '*') {
                    flag = false;
                    break;
                }
            }
            dp[ind][0] = flag;
        }
        for (int ind1 = 1; ind1 <= n; ind1++) {
            for (int ind2 = 1; ind2 <= m; ind2++) {
                if (s.charAt(ind1 - 1) == p.charAt(ind2 - 1) || s.charAt(ind1 - 1) == '?') {
                    dp[ind1][ind2] = dp[ind1 - 1][ind2 - 1];
                } else if (s.charAt(ind1 - 1) == '*') {
                    dp[ind1][ind2] = dp[ind1 - 1][ind2] || dp[ind1][ind2 - 1];
                }
            }
        }
        return dp[n][m];
    }

    private int maxProfitMemoization(int ind, int buy, int n, int[] prices, int[][] dp) {
        if (ind == n) {
            return 0;
        }
        if (dp[ind][buy] != -1) {
            return dp[ind][buy];
        }
        int profit;
        if (buy == 1) {
            int take = -prices[ind] + maxProfitMemoization(ind + 1, 0, n, prices, dp);
            int notTake = maxProfitMemoization(ind + 1, 1, n, prices, dp);
            profit = Math.max(take, notTake);
        } else {
            int take = prices[ind] + maxProfitMemoization(ind + 1, 1, n, prices, dp);
            int notTake = maxProfitMemoization(ind + 1, 0, n, prices, dp);
            profit = Math.max(take, notTake);
        }
        return dp[ind][buy] = profit;
    }

    private int maxProfitTabulation (int n, int[] prices) {
        int[][] dp = new int[n + 1][2];
        for (int ind = n - 1; ind >= 0; ind--) {
            for (int buy = 0; buy <= 1; buy++) {
                int profit;
                if (buy == 0) {
                    int take = prices[ind] + dp[ind + 1][1];
                    int notTake = dp[ind + 1][0];
                    profit = Math.max(take, notTake);
                } else {    
                    int take = -prices[ind] + dp[ind + 1][0];
                    int notTake = dp[ind + 1][1];
                    profit = Math.max(take, notTake);
                }
                dp[ind][buy] = profit;
            }
        }
        return dp[0][1];
    }

    private int maxProfitMemoizationIII(int ind, int buy, int limit, int n, int[] prices, int[][][] dp) {
        if (limit == 0) {
            return 0;
        }
        if (ind == n) {
            return 0;
        }
        if (dp[ind][buy][limit] != -1) {
            return dp[ind][buy][limit];
        }
        int profit;
        if (buy == 1) {
            int take = -prices[ind] + maxProfitMemoizationIII(ind + 1, 0, limit, n, prices, dp);
            int notTake = maxProfitMemoizationIII(ind + 1, 1, limit, n, prices, dp);
            profit = Math.max(take, notTake);
        } else {
            int take = prices[ind] + maxProfitMemoizationIII(ind + 1, 1, limit - 1, n, prices, dp);
            int notTake = maxProfitMemoizationIII(ind + 1, 0, limit, n, prices, dp);
            profit = Math.max(take, notTake);
        }
        return dp[ind][buy][limit] = profit;
    }

    private int maxProfitTabulationIII(int n, int[] prices) {
        int[][][] dp = new int[n + 1][2][3];
        for (int ind = n - 1; ind >= 0; ind--) {
            for (int buy = 0; buy <= 1; buy++) {
                for (int limit = 1; limit <= 2; limit++) {
                    int profit;
                    if (buy == 0) {
                        int take = -prices[ind] + dp[ind + 1][1][limit];
                        int notTake = dp[ind + 1][0][limit];
                        profit = Math.max(take, notTake);
                    } else {
                        int take = prices[ind] + dp[ind + 1][0][limit - 1];
                        int notTake = dp[ind + 1][1][limit];
                        profit = Math.max(take, notTake);
                    }
                    dp[ind][buy][limit] = profit;
                }
            }
        }
        return dp[0][0][2];
    }

    private int maxProfitTabulationIV(int n, int k, int[] prices) {
        int[][] dp = new int[2][k + 1];
        for (int ind = n - 1; ind >= 0; ind--) {
            int[][] cur = new int[2][k + 1];
            for (int buy = 0; buy <= 1; buy++) {
                for (int limit = 1; limit <= k; limit++) {
                    int profit;
                    if (buy == 0) {
                        int take = -prices[ind] + dp[1][limit];
                        int notTake = dp[0][limit];
                        profit = Math.max(take, notTake);
                    } else {
                        int take = prices[ind] + dp[0][limit - 1];
                        int notTake = dp[1][limit];
                        profit = Math.max(take, notTake);
                    }
                    cur[buy][limit] = profit;
                }
            }
            dp = cur;
        }
        return dp[0][k];
    }

    private int LISMemoization(int ind, int prevInd, int n, int[] nums, int[][] dp) {
        if (ind == n) {
            return 0;
        }
        if (dp[ind][prevInd + 1] != -1) {
            return dp[ind][prevInd + 1];
        }
        int notTake = LISMemoization(ind + 1, prevInd, n, nums, dp);
        int take = 0;
        if (prevInd == -1 || nums[prevInd] < nums[ind]) {
            take = 1 + LISMemoization(ind + 1, ind, n, nums, dp);
        }
        return dp[ind][prevInd + 1] = Math.max(notTake, take);
    }

    private int LISTabulation(int n, int[] nums) {
        int[][] dp = new int[n + 1][n + 1];
        for (int ind = n - 1; ind >= 0; ind--) {
            for (int prevInd = ind - 1; prevInd >= -1; prevInd--) {
                int notTake = dp[ind + 1][prevInd + 1];
                int take = 0;
                if (prevInd == -1 || nums[prevInd] < nums[ind]) {
                    take = 1 + dp[ind + 1][ind + 1];
                }
                dp[ind][prevInd + 1] = Math.max(notTake, take);
            }
        }
        return dp[0][0];
    }

    private int LISSpaceOptimized (int n, int[] nums) {
        int[] dp = new int[n];
        int[] hash = new int[n];
        for (int ind = 0; ind < n; ind++) {
            dp[ind] = 1;
            hash[ind] = ind;
        }
        for (int ind = 1; ind < n; ind++) {
            for (int idx = 0; idx < ind; idx++) {
                if (nums[idx] < nums[ind]) {
                    if (dp[ind] < 1 + dp[idx]) {
                        hash[ind] = idx;
                        dp[ind] = 1 + dp[idx];
                    }
                }
            }
        }
        int ans = 1;
        int idx = 0;
        for (int ind = 0; ind < n; ind++) {
            if (ans < dp[ind]) {
                ans = dp[ind];
                idx = ind;
            }
        }
        while (hash[idx] != idx) {
            System.out.println(nums[idx]);
            idx = hash[idx];
        }
        return ans;
    }

    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] dp = new int[n];
        int[] hash = new int[n];
        for (int ind = 0; ind < n; ind++) {
            dp[ind] = 1;
            hash[ind] = ind;
        }
        int ans = 1;
        int lastInd = -1;
        for (int ind = 1; ind < n; ind++) {
            for (int prev = 0; prev < ind; prev++) {
                if ((nums[prev] % nums[ind] == 0) || (nums[ind] % nums[prev] == 0) && dp[ind] < 1 + dp[prev]) {
                    dp[ind] = 1 + dp[prev];
                    hash[ind] = prev;
                }
            }
            if (dp[ind] > ans) {
                ans = dp[ind];
                lastInd = ind;
            }
        }
        List<Integer> subset = new ArrayList<>();
        if (lastInd == -1) {
            return new ArrayList<>(Arrays.asList(nums[0]));
        }
        while (hash[lastInd] != lastInd) {
            subset.add(nums[lastInd]);
            lastInd = hash[lastInd];
        }
        subset.add(nums[lastInd]);
        Collections.reverse(subset);
        return subset;
    }

    public int longestStrChain(String[] words) {
        private boolean isSubsequence (String s1, String s2) {
            int ind1 = 0, ind2 = 0;
            int n = s1.length(), m = s2.length();
            if (n > m || m - n != 1) {
                return false;
            }
            while (ind1 < n && ind2 < m) {
                if (s1.charAt(ind1) == s2.charAt(ind2)) {
                    ind1++;
                    ind2++;
                } else {
                    ind2++;
                }
            }
            return ind1 == n;
        }
        int n = words.length;
        int[] dp = new int[n];
        Arrays.sort(words, Comparator.comparingInt(String :: length));
        Arrays.fill(dp, 1);
        int ans = 1;
        for (int ind = 1; ind < n; ind++) {
            for (int prev = 0; prev < ind; prev++) {
                if (isSubsequence(words[prev], words[ind])) {
                    dp[ind] = Math.max(dp[ind], 1 + dp[prev]);
                }
            }
            ans = Math.max(ans, dp[ind]);
        }
        return ans;
    }

     public static int longestBitonicSequence(int[] arr, int n) {
        int[] dp1 = new int[n];
        dp1[0] = 1;
        for (int ind = 1; ind < n; ind++) {
            dp1[ind] = 1;
            for (int prev = 0; prev < ind; prev++) {
                if (arr[prev] < arr[ind]) {
                    dp1[ind] = Math.max(dp1[ind], 1 + dp1[prev]);
                }
            }
        }
        int[] dp2 = new int[n];
        dp2[n - 1] = 1;
        for (int ind = n - 2; ind >= 0; ind--) {
            dp2[ind] = 1;
            for (int prev = n - 1; prev > ind; prev--) {
                if (arr[prev] < arr[ind]) {
                    dp2[ind] = Math.max(dp2[ind], 1 + dp2[prev]);
                }
            }
        }
        int ans = 1;
        for (int ind = 0; ind < n; ind++) {
            ans = Math.max(ans, dp2[ind] + dp1[ind] - 1);
        }
        return ans;
    }

    private int findMinSplit(int ind, String s, int[] dp) {
        if (ind == s.length()) {
            return 0;
        }
        int min = (int) 1e9;
        for (int i = ind; i < s.length(); i++) {
            if (isPalindrome(ind, i, s)) {
                int cost = 1 + findMinSplit(i + 1, s, dp);
                min = Math.min(min, cost);
            }
        }
        return dp[ind] = min;
    }

    private static int noOfWays (int i, int j, int isTrue, String exp, int[][][] dp) {
        if (i > j) {
            return 0;
        }
        if (i == j) {
            if (isTrue == 1) {
                return exp.charAt(i) == 'T' ? 1: 0;
            } 
            return exp.charAt(i) == 'F' ? 1 : 0;
        }
        if (dp[i][j][isTrue] != -1) {
            return dp[i][j][isTrue];
        }
        long ways = 0;
        for (int ind = i + 1; ind <= j - 1; ind += 2) {
            long lt = noOfWays(i, ind -1, 1, exp, dp);
            long lf = noOfWays(i, ind -1, 0, exp, dp);
            long rt = noOfWays(ind + 1, j, 1, exp, dp);
            long rf = noOfWays(ind + 1, j, 0, exp, dp);
            if (exp.charAt(ind) == '&') {
                if (isTrue == 1) {
                    ways = (ways + (lt * rt) % mod) % mod;
                } else {
                    ways = (ways + (lf * rt) % mod + 
                    (lf * rf) % mod +
                    (lt * rf) % mod) % mod;
                }
            } else if (exp.charAt(ind) == '^') {
                if (isTrue == 1) {
                    ways = (ways + (lt * rf) % mod + 
                    (rt * lf) % mod) % mod;
                } else {
                    ways = (ways + (lt * rt) % mod + 
                    (rf * lf) % mod) % mod;
                }
            } else {
                if (isTrue == 1) {
                    ways = (ways + (lt * rf) % mod + 
                    (rt * lf) % mod + 
                    (lt * rt) % mod) % mod;
                } else {
                    ways = (ways + (lf * rf) % mod) % mod;
                }
            }
        }
        return dp[i][j][isTrue] = (int) ways;
    }

    private int largestRectangleStack (int[] hist) {
        Stack<Integer> st = new Stack<>();
        int maxArea = 0;
        for (int ind = 0; ind < hist.length; ind++) {
            while (!st.isEmpty() && hist[st.peek()] > hist[ind]) {
                int area = hist[st.pop()] * (ind - (st.isEmpty() ? -1 : st.peek()) - 1);
                maxArea = Math.max(maxArea, area);
            }
            st.push(ind);
        }
        while (!st.isEmpty()) {
            int area = hist[st.pop()] * (hist.length - (st.isEmpty() ? -1 : st.peek()) - 1);
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }

    public int maximalRectangle(char[][] matrix) {
        int[] hist = new int[matrix[0].length];
        int ans = 0;
        for (char[] mat : matrix) {
            for (int ind = 0; ind < matrix[0].length; ind++) {
                if (mat[ind] == '0') {
                    hist[ind] = 0;
                } else {
                    hist[ind] += 1;
                }
            }
            ans = Math.max(ans, largestRectangle(hist));
        }
        return ans;
    }

    public int countSquares(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m]; 
        for (int ind = 0; ind < n; ind++) {
            dp[ind][0] = matrix[ind][0];
        }
        for (int ind = 0; ind < m; ind++) {
            dp[0][ind] = matrix[0][ind];
        }
        for (int row = 1; row < n; row++) {
            for (int col = 1; col < m; col++) {
                if (matrix[row][col] == 1) {
                    int up = (row - 1 >= 0) ? dp[row - 1][col] : (int) 1e9;
                    int left = (col - 1 >= 0) ? dp[row][col - 1] : (int) 1e9;
                    int diagonal = (row - 1 >= 0 && col - 1 >= 0) ? dp[row - 1][col - 1] : (int) 1e9;
                    dp[row][col] = Math.min(up, Math.min(left, diagonal)) + 1; 
                } else {
                    dp[row][col] = 0;
                }
            }
        }
        int ans = 0;
        for (int[] row : dp) {
            for (int c : row) {
                ans += c;
            }
        }
        return ans;
    }

}