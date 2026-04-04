package advance.module1;

import kotlin.Pair;

import java.util.Arrays;

public class TwoPointers {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 10, 20, 23, 30};
        System.out.println(twoPointerSum(arr, 23));
        int[] arr1 = {-2, 0, 1, 3, 10, 20, 23};
        System.out.println(Arrays.toString(twoPointerDifference(arr1, 9)));
        int[] arr2 = {1, 3, 15, 10, 20, 23, 3};
        int K = 45;

        int[] result = subArraySumSlidingWindow(arr2, K);
        int[] result1 = findSubarrayPureTwoPointers(arr2, K);
        System.out.println(
                "Subarray using sliding window found from index " + result[0] + " to " + result[1]);
        System.out.println(
                "Subarray using two pointer found from index " + result1[0] + " to " + result1[1]);


        int[] heights = {3, 6, 7, 4, 6, 2};
        System.out.println("Max water trapped is " + maxWaterTrapped(heights));

    }

    private static Pair<Integer, Integer> twoPointerSum(int[] arr, int k) {
        int N = arr.length;
        int i = 0;
        int j = N - 1;
        Pair<Integer, Integer> ans = new Pair<>(0, 0);

        while (i <= j) {
            int sum = arr[i] + arr[j];
            if (sum == k) { // if condition satisfies mid is ans
                return new Pair<>(i, j);
            }
            if (sum > k) { // need to reduce the sum so reduce higher elements j
                j--;
            } else {// need to increase the sum so increase lower elements i
                i++;
            }
        }
        return new Pair<>(-1, -1);
    }

    public static int[] twoPointerDifference(int[] arr, int K) {
        int N = arr.length;
        int i = 0;
        int j = 1; // Start j one step ahead

        // Stop when the fast pointer falls off the edge
        while (j < N) {
            int diff = arr[j] - arr[i];

            if (diff == K) {
                return new int[]{i, j}; // Found the pair
            } else if (diff < K) {
                j++; // Gap too small, stretch it by moving right pointer
            } else {
                i++; // Gap too big, shrink it by moving left pointer

                // Safety check: Pointers should never represent the same element.
                // If the 'Chaser' catches up to the 'Explorer', push the Explorer forward.
                if (i == j) {
                    j++;
                }
            }
        }
        return new int[]{-1, -1}; // Not found
    }

    public static int[] subArraySumSlidingWindow(int[] arr, int K) {
        int i = 0; // Left pointer (shrinks the window)
        int sum = 0; // Running sum of the current window

        // j is the right pointer (expands the window)
        for (int j = 0; j < arr.length; j++) {
            sum += arr[j]; // Add the new element to the window

            // If the sum is too large, shrink the window from the left
            // We use a while loop because we might need to remove multiple
            // elements from the left to get the sum <= K
            while (sum > K && i <= j) {
                sum -= arr[i]; // Remove the leftmost element from sum
                i++;           // Move left pointer forward
            }

            // Check if we hit the exact target
            if (sum == K) {
                return new int[]{i, j};
            }
        }

        // If we search the whole array and find nothing
        return new int[]{-1, -1};
    }

    public static int[] findSubarrayPureTwoPointers(int[] arr, int K) {
        if (arr.length == 0) return new int[]{-1, -1};

        int N = arr.length;
        int i = 0;
        int j = 0;

        // Start with the first element's value
        int sum = arr[0];

        while (j < N) {
            // Condition 1: We found the exact sum
            if (sum == K) {
                return new int[]{i, j};
            }
            // Condition 2: Sum is too small -> Expand right
            else if (sum < K) {
                j++;
                if (j < N) {
                    sum += arr[j]; // Add the new element to the sum
                }
            }
            // Condition 3: Sum is too large -> Shrink left
            else {
                sum -= arr[i]; // Remove the leftmost element from the sum
                i++;           // Move the left pointer forward

                // Edge Case Safety: What if a single element in the array is larger than K?
                // The 'i' pointer would shrink past the 'j' pointer.
                // If that happens, we must snap 'j' back to 'i' and reset the sum.
                if (i > j && i < N) {
                    j = i;
                    sum = arr[i];
                }
            }
        }

        return new int[]{-1, -1};
    }

    private static int maxWaterTrapped(int[] heights) {
        int N = heights.length;
        int i = 0;
        int j = N - 1;
        int maxArea = 0;
        while (i < j) {
            int base = (j - i);
            int height = Math.min(heights[i], heights[j]);
            int currentArea = height * base;
            maxArea = Math.max(currentArea, maxArea);
            if (heights[j] < heights[i]) {
                j--;
            } else if (heights[i] < heights[j]) {
                i++;
            } else {
                i++;
                j++;
            }
        }
        return maxArea;
    }
}
