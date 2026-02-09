package advance.module1;

public class Searching_1 {
    public static void main(String[] args) {
        int[] A = {9, 8, 7, 3, 6, 4, 1, 5};
        int result = findLocalMinimum(A);
        System.out.println("A Local Minimum is: " + result);

        int[] A1 = {1, 3, 8, 10, 15, 4, 2};
        System.out.println("The peak element is: " + findPeak(A1));

        int[] A2 = {50, 60, 100, 3, 9, 10, 25, 30, 35};
        int target = 25;
        System.out.println("Index of target: " + rotatedSearch(A2, target));
    }

    public static int findLocalMinimum(int[] arr) {
        int n = arr.length;

        // Handle edge cases for arrays of size 1 or 2
        if (n == 1) return arr[0];
        if (arr[0] < arr[1]) return arr[0];
        if (arr[n - 1] < arr[n - 2]) return arr[n - 1];

        int left = 1;
        int right = n - 2;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Case 1: Found the local minimum
            if (arr[mid] < arr[mid - 1] && arr[mid] < arr[mid + 1]) {
                return arr[mid];
            }

            // Case 2 & 4: If mid is greater than the right neighbor,
            // there's a min to the right.
            if (arr[mid] > arr[mid + 1]) {
                left = mid + 1;
            }
            // Case 3: If mid is greater than the left neighbor,
            // there's a min to the left.
            else {
                right = mid - 1;
            }
        }
        return -1; // Should not reach here given distinct elements
    }

    public static int findPeak(int[] A) {
        int N = A.length;
        int L = 0;
        int R = N - 1;

        while (L <= R) {
            int Mid = L + (R - L) / 2;

            // Check if Mid is the peak
            // Boundary checks (Mid-1 < 0 and Mid+1 >= N) handle array ends
            boolean isGreaterLeft = (Mid - 1 < 0 || A[Mid - 1] < A[Mid]);
            boolean isGreaterRight = (Mid + 1 >= N || A[Mid + 1] < A[Mid]);

            if (isGreaterLeft && isGreaterRight) {
                return A[Mid]; // Peak found
            }

            // If we are on an increasing slope, move right
            if (isGreaterLeft) {
                L = Mid + 1;
            }
            // If we are on a decreasing slope, move left
            else {
                R = Mid - 1;
            }
        }
        return -1; // Should not occur in a bitonic array
    }

    public static int rotatedSearch(int[] A, int target) {
        int n = A.length;
        int L = 0, R = n - 1;

        while (L <= R) {
            int mid = L + (R - L) / 2;

            if (A[mid] == target) return mid; // Found target

            // Check if mid is in Set 1
            if (A[mid] >= A[0]) {
                // Target is in Set 2 OR target is larger than A[mid]
                if (target < A[0] || target > A[mid]) {
                    L = mid + 1;
                } else {
                    R = mid - 1;
                }
            }
            // Mid is in Set 2
            else {
                // Target is in Set 1 OR target is smaller than A[mid]
                if (target >= A[0] || target < A[mid]) {
                    R = mid - 1;
                } else {
                    L = mid + 1;
                }
            }
        }
        return -1; // Target not found
    }
}
