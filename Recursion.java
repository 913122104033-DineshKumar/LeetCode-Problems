public class Recursion {
    public static void printNNumbers(int i, int n) {
        if (i == n) {
            return;
        }
        System.out.println(i);
        printNNumbers(i + 1, n);
    }
    public static void printNNumbersInReverse(int i, int n) {
        if (i == n) {
            return;
        }
        printNNumbersInReverse(i + 1, n);
        System.out.println(i);
    }
    public static int fibanocci(int i) {
        if (i == 0 || i == 1) {
            return i;
        }
        return fibanocci(i - 1) + fibanocci(i - 2);
    }
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
}