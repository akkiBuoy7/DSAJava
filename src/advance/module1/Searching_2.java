package advance.module1;

public class Searching_2 {
    public static void main(String[] args) {
        int[] arr = {2, 2, 5, 5, 8, 8, 10, 13, 13, 18, 18};
        int uniqueInSortedArray = findUniqueInSortedArray(arr);
        System.out.println("Unique element is >> " + uniqueInSortedArray);

        int[] arr1 = {2, 2, 5, 5, 5, 8, 10, 10, 13, 13};
        int target = 5;
        int firstOccurrence = findFirstOccurrence(arr1, target);
        System.out.println("First occurrence of target is at " + firstOccurrence);

        int lastOccurrence = findLastOccurrence(arr1, target);
        System.out.println("Last occurrence of target is at " + lastOccurrence);

        int N = 30;
        System.out.println("Floor of sqrt(" + N + ") is: " + findFloorSqrt(N));

    }

    private static int findUniqueInSortedArray(int[] arr) {
        /*
        before unique pairs are (even , odd) => before zone
        after unique pairs are (odd , even) => after zone
        find mid and check
        if mid is 1st of the pair (mid!=mid-1) => (mid , mid+1) pairs
           now check
           if mid is even => (even , odd) pairs
               so we are in before zone => move the left (L + 2)
           else => (odd , even) pairs
               so we are in after zone => move the right (R -1)
         */
        int N = arr.length;
        int L = 0;
        int R = N - 1;

        while (L <= R) {
            int mid = (L + R) / 2;
            if (
                    ((mid - 1 < 0) || arr[mid] != arr[mid - 1])
                            &&
                            ((mid + 1 == N) || arr[mid] != arr[mid + 1])
            ) {
                return arr[mid];
            }
            if (mid - 1 < 0 || arr[mid] != arr[mid - 1]) { // [mid , mid+1]
                if (mid % 2 == 0) { // [even , odd]
                    L = mid + 2;
                } else { // [odd, even]
                    R = mid - 1;
                }
            } else { // [mid-1 , mid]
                if (mid % 2 == 0) { // [odd , even]
                    R = mid - 2;
                } else { // [even , odd]
                    L = mid + 1;
                }
            }
        }
        return -1;
    }

    private static int findFirstOccurrence(int[] arr, int T) {
        /*
        Find first occurrence of an element in an array that has duplicate elements
        mid check is important
        if mid == target and if mid is first occurrence (mid!=mid-1 || mid-1<0)
         */
        int L = 0;
        int N = arr.length;
        int R = N - 1;
        while (L <= R) {
            int mid = (L + R) / 2;

            if (arr[mid] == T
                    &&
                    (mid - 1 < 0 || arr[mid] != arr[mid - 1]) // if mid is the first occurrence
            ) {
                return mid;
            }
            if (arr[mid] < T) {
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return -1;
    }

    private static int findLastOccurrence(int[] arr, int T) {
        /*
        Find first occurrence of an element in an array that has duplicate elements
        mid check is important
        if mid == target and if mid is first occurrence (mid!=mid-1 || mid-1<0)
         */
        int L = 0;
        int N = arr.length;
        int R = N - 1;
        while (L <= R) {
            int mid = (L + R) / 2;

            if (arr[mid] == T
                    &&
                    (mid + 1 == N || arr[mid] != arr[mid + 1]) // if mid is the last occurrence
            ) {
                return mid;
            }
            if (arr[mid] <= T) { // need = as mid can be in any duplicate element before the last
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return -1;
    }

        public static int findFloorSqrt(int n) {
            if (n == 0 || n == 1) return n;

            long L = 1;
            long R = n;
            long ans = 0;

            while (L <= R) {
                long mid = L + (R - L) / 2;

                // Check if mid*mid is exactly N or less than N
                if (mid * mid == n) {
                    return (int) mid;
                }

                if (mid * mid < n) {
                    // mid is a candidate, but there might be a larger one
                    ans = mid;
                    L = mid + 1;
                } else {
                    // mid*mid > n, so mid is too large
                    R = mid - 1;
                }
            }
            return (int) ans;
        }

}
