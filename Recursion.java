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
}