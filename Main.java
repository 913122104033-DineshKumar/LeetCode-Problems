public class Main{
    //Day 1 - 1652. Defuse the Bomb
    public static int[] decrypt(int[] code, int k) {
        int n = code.length;
        int[] ans = new int[n];
        if (k == 0) {
            return ans;
        }
        boolean isRight = true;
        if (k < 0) {
            k *= -1;
            isRight = false;
        }
        for (int i = 0; i < n; i++) {
            int sum = 0;
            if (isRight) {
                int temp = k;
                int j = i + 1;
                while (k > 0) {
                    if (j == n) {
                        j = 0;
                    }
                    sum += code[j];
                    k--;
                    j++;
                }
                k = temp;
            } else {
                int temp = k;
                int j = i - 1;
                while (k > 0) {
                    if (j < 0) {
                        j = n - 1;
                    }
                    sum += code[j];
                    k--;
                    j--;
                }
                k = temp;
            }
            ans[i] = sum;
        }
        return ans;
    }
    //Day 2 - 2461. Maximum Sum of Distinct Subarrays With Length K
    public static long maximumSubarraySum(int[] nums, int k) {
        long ans = 0;
        long sum = 0;
        Set<Integer> set = new HashSet<>();
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            while (set.contains(nums[i])) {
                set.remove(nums[j]);
                sum -= nums[j];
                j++;
            }
            set.add(nums[i]);
            sum += nums[i];
            if (i - j + 1 == k) {
                ans = Math.max(ans, sum);
                sum -= nums[j];
                set.remove(nums[j]);
                j++;
            }
        }
        return ans;
    }
    //Day 4 - 2257. Count Unguarded Cells in the Grid
    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int[][] grid = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(grid[i], 2);
        }
        for (int i = 0; i < guards.length; i++) {
            grid[guards[i][0]][guards[i][1]] = -1;
        }
        for (int i = 0; i < walls.length; i++) {
            grid[walls[i][0]][walls[i][1]] = 0;
        }
        for (int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if (grid[i][j] == -1) {
                    int k = j - 1;
                    while (k >= 0 && grid[i][k] != 0 && grid[i][k] != -1) {
                        grid[i][k] = 1;
                        k--;
                    }
                    k = j + 1;
                    while (k < n && grid[i][k] != 0 && grid[i][k] != -1) {
                        grid[i][k] = 1;
                        k++;
                    }
                    k = i - 1;
                    while (k >= 0 && grid[k][j] != 0 && grid[k][j] != -1) {
                        grid[k][j] = 1;
                        k--;
                    }
                    k = i + 1;
                    while (k < m && grid[k][j] != 0 && grid[k][j] != -1) {
                        grid[k][j] = 1;
                        k++;
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    ans++;
                }
            }
        }
        return ans;
    }
    //Day 6 - 1861. Rotating the Box
    public char[][] rotateTheBox(char[][] box) {
        int m = box.length;
        int n = box[0].length;
        char[][] ans = new char[n][m];
        for (int i = 0, k = m -1; i < m && k >= 0; i++, k--) {
            int empties = 0;
            int idx = n - 1;
            for (int j = n - 1; j >= 0; j--) {
                if (box[i][j] == '.') {
                    empties++;
                } else if (box[i][j] == '#') {
                    ans[idx--][k] = '#';
                } else if (box[i][j] == '*') {
                    while (empties > 0 && idx >= 0) {
                        ans[idx--][k] = '.';
                        empties--;
                    }
                    ans[idx--][k] = '*';
                }
            }
            while (empties > 0 && idx >= 0) {
                ans[idx--][k] = '.';
            }
        }
        return ans;
    }
    //2070. Most Beautiful Item for Each Query
    public int[] maximumBeauty(int[][] items, int[] queries) {
        int findBeauty(int[][] items, int target) {
            int l = 0, r = items.length - 1;
            int res = -1;
            while (l <= r) {
                int mid = (r - l) / 2 + l;
                if (items[mid][0] > target) {
                    r = mid - 1;
                } else {
                    res = mid;
                    l = mid + 1;
                }
            }
            return (res == -1) ? 0 : items[res][1];
        }
        int n = items.length;
        int m = queries.length;
        Arrays.sort(items, (x, y) -> Integer.compare(x[0], y[0]));
        int max = items[0][1];
        for (int i = 1; i < n; i++) {
            max = Math.max(items[i][1], max);
            items[i][1] = max;
        }
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            ans[i] = findBeauty(items, queries[i]);
        }
        return ans;
    }
}