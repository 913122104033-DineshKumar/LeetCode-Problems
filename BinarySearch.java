public class BinarySearch {
    //33. Search in Rotated Sorted Array
    public int search(int[] nums, int x) {
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
}