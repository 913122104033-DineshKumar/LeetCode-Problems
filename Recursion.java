public class Recursion {
    //Print First N Numbers
    public static void printNNumbers(int i, int n) {
        if (i == n) {
            return;
        }
        System.out.println(i);
        printNNumbers(i + 1, n);
    }
    //Print First N Numbers in Reverse Order
    public static void printNNumbersInReverse(int i, int n) {
        if (i == n) {
            return;
        }
        printNNumbersInReverse(i + 1, n);
        System.out.println(i);
    }
    //Fibanocci Series
    public static int fibanocci(int i) {
        if (i == 0 || i == 1) {
            return i;
        }
        return fibanocci(i - 1) + fibanocci(i - 2);
    }
    //Subsequence Pattern
    public static void subsequence(int idx, List<Integer> nums, int n, int[] arr) {
        if (idx == n) {
            for (int num : nums) {
                System.out.println(num + " ");
            }
            return;
        }
        nums.add(arr[idx]);
        subsequence(idx + 1, nums, n, arr);
        nums.remove(arr[idx]);
    }
    //Combination Sum Problem I
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        void recursion(int idx, int[] candidates, int x, List<List<Integer>> ans, List<Integer> list) {
            if (idx == candidates.length) {
                if (x == 0) {
                    ans.add(new ArrayList<>(list));
                }
                return;
            }       
            if (candidates[idx] <= x) {
                list.add(candidates[idx]);
                recursion(idx, candidates, x - candidates[idx], ans, list);
                list.remove(list.size() - 1);
            }
            recursion(idx + 1, candidates, x, ans, list);                          
        }
        List<List<Integer>> ans = new ArrayList<>();
        recursion(0, candidates, target, ans, new ArrayList<>());
        return ans;
    }
    //Combination Sum Problem II
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        //Optimal Solution
        void findCombinations(int idx, List<Integer> list, int target, List<List<Integer>> ans, int[] arr) {
            if (target == 0) {
                ans.add(new ArrayList<>(list));
                return;
            }
            for (int i = idx; i < arr.length; i++) {
                if (i > idx && arr[i] == arr[i - 1]) {
                    continue;
                }
                if (arr[i] > target) {
                    break;
                }
                list.add(arr[i]);
                findCombinations(i + 1, list, target - arr[i], ans, arr);
                list.remove(list.size() - 1);
            }
        }
        Arrays.sort(candidates);
        List<List<Integer>> ans = new ArrayList<>();
        findCombinations(0, new ArrayList<>(), target, ans, candidates);
        return ans;
    }
    //Combination Sum III
    public List<List<Integer>> combinationSum3(int k, int n) {
        void findCombinations(int cur, List<Integer> ds, int target, int k, List<List<Integer>> ans) {
            if (target == 0 && ds.size() == k) {
                ans.add(new ArrayList<>(ds));
                return;
            }
            if (cur > 9 || ds.size() > k || cur > target) {
                return;
            }
            ds.add(cur);
            findCombinations(cur + 1, ds, target - cur, k, ans);
            ds.remove(ds.size() - 1);
            findCombinations(cur + 1, ds, target, k, ans);
        }
        List<List<Integer>> ans = new ArrayList<>();
        findCombinations(1, new ArrayList<>(), n, k, ans);
        return ans;
    }
    //Subsets I
    public List<List<Integer>> subsets(int[] nums) {
        void findSubsets(int idx, List<Integer> ds, List<List<Integer>> ans, int[] nums) {
            if (idx == nums.length) {
                ans.add(new ArrayList<>(ds));
                return;
            }
            ds.add(nums[idx]);
            findSubsets(idx + 1, ds, ans, nums);
            ds.remove(ds.size() - 1);
            findSubsets(idx + 1, ds, ans, nums);
        }
        List<List<Integer>> ans = new ArrayList<>();
        findSubsets(0, new ArrayList<>(), ans, nums);
        return ans;
    }
    //Subsets II
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        void findSubsets(int idx, List<Integer> ds, List<List<Integer>> ans, int[] nums) {
            ans.add(new ArrayList<>(ds));
            for (int i = idx; i < nums.length; i++) {
                if (i > idx && nums[i] == nums[i - 1]) {
                    continue;
                }   
                ds.add(nums[i]);
                findSubsets(i + 1, ds, ans, nums);
                ds.remove(ds.size() - 1);
            }
        }
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        findSubsets(0, new ArrayList<>(), ans, nums);
        return ans;
    }
    //Permutation Brute Force Approach
    public List<List<Integer>> permute(int[] nums) {
        void permutate(int[] nums, List<Integer> ds, List<List<Integer>> ans, boolean[] marked) {
            if (ds.size() == nums.length) {
                ans.add(new ArrayList<>(ds));
                return;
            }   
            for (int i = 0; i < nums.length; i++) {
                if (!marked[i]) {
                    ds.add(nums[i]);
                    marked[i] = true;
                    permutate(nums, ds, ans, marked);
                    ds.remove(ds.size() - 1);
                    marked[i] = false;
                }
            }   
        }
        List<List<Integer>> ans = new ArrayList<>();
        boolean[] marked = new boolean[nums.length];
        permutate(nums, new ArrayList<>(), ans, marked);
        return ans;
    }
    //Permutations Optimal Solution
    public List<List<Integer>> permutation(int[] nums) {
        void permutate(int idx, int[] nums, List<List<Integer>> ans) {
            if (idx == nums.length) {
                List<Integer> ds = new ArrayList<>();
                for (int num : nums) {
                    ds.add(num);
                }
                ans.add(new ArrayList<>(ds));
                return;
            }
            for (int i = idx; i < nums.length; i++) {
                swap(i, idx, nums);
                permutate(idx + 1, nums, ans);
                swap(i, idx, nums);
            }
        }
        void swap(int cur, int next, int[] nums) {
            int temp = nums[cur];
            nums[cur] = nums[next];
            nums[next] = temp;
        }
        List<List<Integer>> ans = new ArrayList<>();
        permutate(0, nums, ans);
        return ans;
    }
    //Sudoku Problem
    public void solveSudoku(char[][] board) {
        boolean solve(char[][] board, int n) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == '.') {
                        for (char val = '1'; val <= '9'; val++) {
                            if (isValid(board, i, j, val)) {
                                board[i][j] = val;
                                if (solve(board, n)) {
                                    return true;
                                }
                                else {
                                    board[i][j] = '.';
                                }
                            }
                        }
                        return false;
                    }
                }
            }   
            return true;
        }
        boolean isValid(char[][] board, int row, int col, char c) {
            for (int i = 0; i < 9; i++) {
                if (board[row][i] == c) {
                    return false;
                }   
                if (board[i][col] == c) {
                    return false;
                }
                if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) {
                    return false;
                }
            }
            return true;
        }
        solve(board, 9);
    }
    //N Queens Problem
    public List<List<String>> solveNQueens(int n) {
        void solve(int col, List<List<String>> ans, int n, boolean[] leftSide, boolean[] lowerDiagonal, 
                        boolean[] upperDiagonal, char[][] board) {
            if (col == n) {
                List<String> ds = new ArrayList<>();
                for (char[] row : board) {
                    String s = new String(row);
                    ds.add(s);
                }
                ans.add(new ArrayList<>(ds));
                return;
            } 
            for (int row = 0; row < n; row++) {
                int upperIdx = (n - 1) + (col - row);
                if (!leftSide[row] && !lowerDiagonal[row + col] && !upperDiagonal[upperIdx]) {
                    board[row][col] = 'Q';
                    leftSide[row] = true;
                    lowerDiagonal[row + col] = true;
                    upperDiagonal[upperIdx] = true;
                    solve(col + 1, ans, n, leftSide, lowerDiagonal, upperDiagonal, board);
                    board[row][col] = '.';
                    leftSide[row] = false;
                    lowerDiagonal[row + col] = false;
                    upperDiagonal[upperIdx] = false;
                }
            }                   
        }
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }
        boolean[] ls = new boolean[n];
        boolean[] ld = new boolean[2 * n - 1];
        boolean[] ud = new boolean[2 * n - 1];
        List<List<String>> ans = new ArrayList<>();
        solve(0, ans, n, ls, ld, ud, board);
        return ans;
    }
    //M Coloring Graph
    boolean graphColoring(int n, List<int[]> edges, int m) {
        boolean coloring(int vertex, int colors, Map<Integer, List<Integer>> G, int n, int[] colored) {
            if (vertex == n) {
                return true;
            }
            for (int color = 1; color <= colors; color++) {
                if (canColor(G, color, vertex, colored)) {
                    colored[vertex] = color;
                    if (coloring(vertex + 1, colors, G, n, colored)) {
                        return true;
                    }
                    colored[vertex] = 0;
                }
            }
            return false;
        }
        boolean canColor(Map<Integer, List<Integer>> G, int color, int vertex, int[] colored) {
            for (int neighbour : G.get(vertex)) {
                if (colored[neighbour] == color) {
                    return false;
                }
            }
            return true;
        }
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] link : edges) {
            int u = link[0];
            int v = link[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        int[] colored = new int[n];
        Arrays.fill(colored, 0);
        return coloring(0, m, graph, n, colored);
    }
    //Palindrome Partioning
    public List<List<String>> partition(String s) {
        void partioning(int idx, String s, List<String> ds, List<List<String>> ans) {
            if (idx == s.length()) {
                ans.add(new ArrayList<>(ds));
                return;
            }
            for (int i = idx; i < s.length(); ++i) {
                if (isPalindrome(s, idx, i)) {
                    ds.add(s.substring(idx, i + 1));
                    partioning(i + 1, s, ds, ans);
                    ds.remove(ds.size() - 1);
                }
            }
        }
        boolean isPalindrome(String s, int left, int right) {
            while (left <= right) {
                if (s.charAt(left++) != s.charAt(right--)) {
                    return false;
                }
            }
            return true;
        }
        List<List<String>> ans = new ArrayList<>();
        partioning(0, s, new ArrayList<>(), ans);
        return ans;
    }
    //Rat in the Maze
    public ArrayList<String> findPath(ArrayList<ArrayList<Integer>> mat) {
        private void path(int row, int col, ArrayList<ArrayList<Integer>> maze, int n, 
                        String move, ArrayList<String> ans, boolean[][] visited) {
            if (row == n - 1 && col == n - 1) {
                ans.add(move);
                return;
            }
            if (row + 1 < n && !visited[row + 1][col] && maze.get(row + 1).get(col) == 1) {
                visited[row + 1][col] = true;
                path(row + 1, col, maze, n, move + 'D', ans, visited);
                visited[row + 1][col] = false;
            }
            if (col - 1 >= 0 && !visited[row][col - 1] && maze.get(row).get(col - 1) == 1) {
                visited[row][col - 1] = true;
                path(row, col - 1, maze, n, move + 'L', ans, visited);
                visited[row][col - 1] = false;
            }
            if (col + 1 < n && !visited[row][col + 1] && maze.get(row).get(col + 1) == 1) {
                visited[row][col + 1] = true;
                path(row, col + 1, maze, n, move + 'R', ans, visited);
                visited[row][col + 1] = false;
            }
            if (row - 1 >= 0 && !visited[row - 1][col] && maze.get(row - 1).get(col) == 1) {
                visited[row - 1][col] = true;
                path(row - 1, col, maze, n, move + 'U', ans, visited);
                visited[row - 1][col] = false;
            }
        }
        int n = mat.size();
        ArrayList<String> ans = new ArrayList<>();
        boolean[][] visited = new boolean[n][n];
        visited[0][0] = true;
        if (mat.get(0).get(0) == 1) {
            path(0, 0, mat, n, "", ans, visited);
        }
        return ans;
    }
    //Kth Permutation Sequence
    public String getPermutation(int n, int k) {
        StringBuilder sb = new StringBuilder();
        int fact = 1;
        for (int i = 1; i <= n; i++) {
            if (i < n) {
                fact *= i;
            }
            sb.append(i);
        }
        k--;
        String ans = "";
        while (true) {
            int block = k / fact;
            ans += sb.charAt(block);
            sb.delete(block, block + 1);
            if (sb.isEmpty()) {
                break;
            }
            k %= fact;
            fact /= sb.length();
        }
        return ans;
    }
}
