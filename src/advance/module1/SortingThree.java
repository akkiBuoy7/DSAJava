package advance.module1;

import java.util.Arrays;

public class SortingThree {
    public static void main(String[] args) {
        int[] a = {5, 6, 1, 2, 3, 5, 2, 6};
        countSort(a);
        System.out.println();
        System.out.println("==========");
        String s = "56123526";
        String result = countSortS(s);
        System.out.println(result);
        System.out.println("==========");
        int[] a1 = {361, 435, 12, 78, 500, 123};

        // sort by 10's digit (i = 1)
        sortByIthDigit(a1, 1);

        System.out.println(Arrays.toString(a1));
        radixSort(a1);
        System.out.println(Arrays.toString(a1));

        int[] A = {3, 2, 5};

        long result1 = sumOfMaxMinusMin(A);
        System.out.println(result1);
    }

    // using integer array
    private static void countSort(int[] a) {
        int N = a.length;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int x : a) {
            max = Math.max(x, max);
            min = Math.min(x, min);
        }

        // range of input array
        int range = (max - min) + 1;

        int[] freq = new int[range];
        // count the frq of i and store at ith index - min value
        for (int i : a) {
            freq[i - min]++;
        }

        for (int i = 0; i < range; i++) {
            for (int j = 1; j <= freq[i]; j++) {
                System.out.print(i + min + " ");
            }
        }

    }

    // using string
    private static String countSortS(String s) {
        int N = s.length();

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        char[] charArray = s.toCharArray();
        for (char c : charArray) {
            int d = c - '0';
            min = Math.min(d, min);
            max = Math.max(d, max);
        }

        int range = (max - min) + 1;
        int[] freq = new int[range];
        for (char c : charArray) {
            int d = c - '0';
            freq[d - min]++;
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < range; i++) {
            for (int j = 1; j <= freq[i]; j++) {
                result.append(i + min).append(" ");
            }
        }
        return result.toString();
    }

    static void sortByIthDigit(int[] a, int i) {
        int n = a.length;

        // Buckets for digits 0-9
        int[][] buckets = new int[10][n];
        int[] size = new int[10];   // how many elements in each bucket

        // Step 1: Put elements into buckets
        for (int x : a) {
            int digit = (x / (int) Math.pow(10, i)) % 10;
            buckets[digit][size[digit]] = x;
            size[digit]++;
        }

        // Step 2: Collect back in order (stable)
        int idx = 0;
        for (int d = 0; d < 10; d++) {
            for (int j = 0; j < size[d]; j++) {
                a[idx++] = buckets[d][j];
            }
        }
    }

    static void radixSort(int[] a) {

        // Step 1: Find maximum element
        int max = a[0];
        for (int x : a) {
            max = Math.max(max, x);
        }

        // Step 2: Number of digits in max
        int digits = (int) Math.log10(max) + 1;

        // Step 3: Do counting/bucket sort for each digit
        for (int i = 0; i < digits; i++) {
            sortByIthDigit(a, i);
        }
    }

    public static long sumOfMaxMinusMin(int[] A) {
        int n = A.length;
        Arrays.sort(A);

        long[] pow2 = new long[n];
        pow2[0] = 1;

        for (int i = 1; i < n; i++) {
            pow2[i] = pow2[i - 1] * 2;
        }

        long ans = 0;

        for (int i = 0; i < n; i++) {
            long countMax = pow2[i];
            long countMin = pow2[n - 1 - i];

            ans += A[i] * (countMax - countMin);
        }

        return ans;
    }
}
