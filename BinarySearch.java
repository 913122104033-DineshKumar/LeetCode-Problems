public class BinarySearch {
    //33. Search in Rotated Sorted Array
    public int searchI(int[] nums, int x) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == x) {
                return mid;
            }
            if (nums[low] <= nums[mid]) {
                if (nums[low] <= x && nums[mid] >= x) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (nums[mid] <= x && nums[high] >= x) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }
    //Coding Ninja Find Floor and Ceil for a given integer.
    public static int[] getFloorAndCeil(int[] a, int n, int x) {
        int getCeil(int[] a, int n, int x) {
            int low = 0, high = n - 1;
            int ans = -1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (a[mid] >= x) {
                ans = a[mid];
                high = mid - 1;
                } else {
                low = mid + 1;
                }
            }
            return ans;
        }
        int getFloor(int[] a, int n, int x) {
            int low = 0, high = n - 1;
            int ans = -1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (a[mid] <= x) {
                    ans = a[mid];
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            return ans;
        }
        int floor = getFloor(a, n, x);
        int ceil = getCeil(a, n, x);
        return new int[]{ floor, ceil };
    }
    //81. Search in Rotated Sorted Array II
    public boolean searchII(int[] nums, int x) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == x) {
                return true;
            }
            if (nums[low] == nums[mid] && nums[mid] == nums[high]) {
                low = low + 1;
                high = high - 1;
            } else if (nums[low] <= nums[mid]) {
                if (nums[low] <= x && x <= nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (nums[mid] <= x && x <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return false;
    }
    //153. Find Minimum in Rotated Sorted Array
    public int findMin(int[] nums) {
        int low = 0, high = nums.length - 1;
        int ans = Integer.MAX_VALUE;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[low] <= nums[mid]) {
                ans = Math.min(ans, nums[low]);
                low = mid + 1;
            } else {
                ans = Math.min(ans, nums[mid]);
                high = mid - 1;
            }
        }
        return ans;
    }
    //Find K Rotations
    public static int findKRotation(int []nums){
        int low = 0, high = nums.length - 1;
        int ans = Integer.MAX_VALUE;
        int idx = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[low] <= nums[mid]) {
                if (ans > nums[low]) {
                    ans = nums[low];
                    idx = low;
                }
                low = mid + 1;
            } else {
                if (ans > nums[mid]) {
                    ans = nums[mid];
                    idx = mid;
                }
                high = mid - 1;
            }
        }
        return idx;
    }
    //540. Single Element in a Sorted Array
    public int singleNonDuplicate(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        if (nums[0] != nums[1]) {
            return nums[0];
        } 
        if (nums[n - 1] != nums[n - 2]) {
            return nums[n - 1];
        }
        int low = 1, high = n - 2;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] != nums[mid - 1] && nums[mid] != nums[mid + 1]) {
                return nums[mid];
            } else if ((mid % 2 != 0 && nums[mid] == nums[mid - 1]) || 
                        (mid % 2 == 0 && nums[mid] == nums[mid + 1])) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
    //69. Sqrt(x)
    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        long low = 1, high = x;
        long ans = 1;
        while (low <= high) {
            long mid = (low + high) / 2;
            if (mid * mid <= x) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return (int) ans;
    }
    //162. Find Peak Element
    public int findPeakElement(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }
        if (nums[0] > nums[1]) {
            return 0;
        }
        if (nums[n - 1] > nums[n - 2]) {
            return n - 1;
        }
        int low = 1, high = n - 2;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                return mid;
            } else if (nums[mid] > nums[mid - 1]) {
                low = mid + 1; 
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
    //Find Nth Root of M
    public static int NthRoot(int n, int m) {
        int getExponential(int mid, int n, int m) {
            long ans = 1;
            for (int i = 1; i <= n; i++) {
                ans *= mid;
                if (ans > m) {
                    return 2;
                } 
            }
            if (ans < m) {
                return 1;
            }
            return 0;
        }
        int low = 1, high = m;
        while (low <= high) {
            int mid = (low + high) / 2;
            int ans = getExponential(mid, n, m);
            if (ans == 0) {
                return mid;
            } else if (ans == 2) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }
    //875. Koko Eating Bananas
    public static int minEatingSpeed(int[] piles, int h) {
        int low = 0, high = 0;
        boolean isFinished(int[] piles, int mid, int h) {
            long totalHours = 0;
            for (int pile : piles) {
                totalHours += Math.ceil(pile + mid - 1) / mid;
                if (totalHours > h) {
                    return false;
            }
            }
            return totalHours <= h;
        }
        for (int pile : piles) {
            high = Math.max(high, pile);
        }        
        while(low <= high) {
            int mid = (low + high) / 2;
            if (isFinished(piles, mid, h)) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
    //Mininum Days to make M Bouquets
    public int minDays(int[] days, int m, int k) {
        int n = days.length;
        if ((long) (m * k) > n) {
            return -1;
        }
        boolean isReady(int[] days, int m, int k, int mid) {
            int bouque = 0, cnt = 0;
            for (int i = 0; i < days.length; i++) {
                if (days[i] <= mid) {
                    cnt++;
                } else {
                    bouque += (cnt / k);
                    cnt = 0;
                }
                if (bouque >= m) {
                    return true;
                }
            }
            bouque += (cnt / k);
            return bouque >= m;
        }
        int low = 0, high = 0;
        for (int day : days) {
            high = Math.max(high, day);
        }
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isReady(days, m, k, mid)) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
    //Capacity To Ship Packages Within D Days
    public static int leastWeightCapacity(int[] weights, int d) {
        boolean noOfDays(int[] weight, int cap, int d) {
            int days = 1, load = 0;
            for (int w : weight) {
                if (load + w > cap) {
                    ++days;
                    load = w;
                } else {
                    load += w;
                }
            } 
            return days <= d;
        }   
        int low = Integer.MIN_VALUE, high = 0;
        for(int w : weights) {
            low = Math.max(low, w);
            high += w;
        }
        while (low <= high) {
            int mid = (low + high) / 2;
            if (noOfDays(weights, mid, d)) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
    //Kth Missing Positive Number
    public static int missingK(int[] vec, int n, int k) {
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int missing = vec[mid] - (mid + 1);
            if (missing < k) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low + k; // high + 1 + k || arr[high] + k - (high - arr[high] + 1)
    }
    //2554. Maximum Number of Integers to Choose From a Range I
    public int maxCount(int[] banned, int high, int maxSum) {
        boolean isInLimit(Set<Integer> banned, int limit, int x) {
            int sum = 0;
            for (int i = 1; i <= limit; i++) {
                if (!banned.contains(i)) {
                sum += i;
                } 
                if (sum > x) {
                    return false;
                }
            }   
            return true;
        }
        int noOfElements(Set<Integer> banned, int limit) {
            int cnt = 0;
            for (int i = 1; i <= limit; i++) {
                if (!banned.contains(i)) {
                    cnt++;
                }
            }
            return cnt;
        }
        Set<Integer> bans = new HashSet<>();
        for (int ban : banned) {
            bans.add(ban);
        }
        int low = 1;
        int rangeLimit = 0;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (isInLimit(bans, mid, maxSum)) {
                low = mid + 1;
            } else {
                high = mid -1;
            }
        }
        return noOfElements(bans, high);
    }
    // Aggressive Cows
    public static int aggressiveCows(int []stalls, int k) {
        boolean canBePlaced(int[] stalls, int dist, int k) {
            int cowCnt = 1;
            int last = stalls[0];
            for (int i = 1; i < stalls.length; i++) {
                if (stalls[i] - last >= dist) {
                    ++cowCnt;
                    last = stalls[i];
                }
            }
            return cowCnt >= k;
        }
        Arrays.sort(stalls);
        int low = 1,high = stalls[stalls.length - 1] - stalls[0];
        while (low <= high) {
            int mid = (low + high) / 2;
            if (canBePlaced(stalls, mid, k)) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return high;
    }
    //Allocate Books
    public static int findPages(ArrayList<Integer> arr, int n, int m) {
        if (m > n) {
            return -1;
        }
        boolean canGiveBooks(ArrayList<Integer> books, int pageLimit, int m) {
            int studentCnt = 0, pageCnt = 0;
            for (int pages : books) {
                if (pageCnt + pages <= pageLimit) {
                    pageCnt += pages;
                } else {
                    studentCnt++;
                    pageCnt = pages;
                }
            }
            return studentCnt < m;
        }
        int low = 1, high = 0;
        for (int pages : arr) {
            low = Math.max(low, pages);
            high += pages;
        }
        while (low <= high) {
            int mid = (low + high) / 2;
            if (!canGiveBooks(arr, mid, m)) {
                low = mid + 1;
            } else {
                high = mid -1;
            }
        }
        return low;
    }
    //1760. Minimum Limit of Balls in a Bag
    public int minimumSize(int[] nums, int maxOperations) {
        boolean canDistribute(int[] nums, int penalty, int maxOperations) {
            int operations = 0;
            for (int num : nums) {
                operations += (num - 1) / penalty;
            }
            return operations <= maxOperations;
        }
        int low = 1, high = 0;
        for (int num : nums) {
            high = Math.max(high, num);
        }
        int res = high;
        while (low <= high) {
            int mid = (low + high) / 2;
            int rem = high - mid;
            if (canDistribute(nums, mid, maxOperations)) {
                res = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return res;
    }
}