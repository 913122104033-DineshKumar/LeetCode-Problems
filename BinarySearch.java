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
}