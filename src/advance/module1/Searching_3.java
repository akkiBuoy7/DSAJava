package advance.module1;

public class Searching_3 {
    public static void main(String[] args) {
        int[] arr = {7, 2, 3, 8, 4, 5};
        int k = 3;
        int maxSubarraySum = maxSubarrayK(arr, k);
        System.out.println("Max sub array sum of size k is " + maxSubarraySum);
        int s = 16;
        int subArrayNGTS = subArrayNGTS(arr, s);
        System.out.println("Value of k for sum not exceeding s is " + subArrayNGTS);

        int[] L = {5, 3, 6, 1, 9};
        int T = 2;
        int X = 20;

        int minPainters = findMinPainters(L, T, X);
        System.out.println("Minimum number of painters required: " + minPainters);

        int P = 3;

        long minTime = findMinTime(L, T, P);
        System.out.println("Minimum time to paint all boards: " + minTime);

        int[] A1 = {1, 3, 4, 8, 10, 12, 15};
        int C1 = 5;
        System.out.println("Max possible min distance : " + findMaxMinDistance(A1, C1));
    }

    private static int maxSubarrayK(int[] arr, int k) {
        /*
        Given an array find the max sub array of size equal to given k
        Sliding window we can use to find all the sum and take max
        S = 1st sum of size k
        for(i = k,i<n;i++) i starts from k as previous S already included sum up to size k
          Si = S + arr[i] - arr[i-k]
          find max Si
          TC = O(N)
          SC = O(1)
         */

        int ans = 0;
        int sum1 = 0;
        int N = arr.length;
        for (int i = 0; i < k; i++) {
            sum1 += arr[i];
        }
        for (int j = k; j < N; j++) {
            sum1 = sum1 + arr[j] - arr[j - k];
            ans = Math.max(ans, sum1);
        }
        return ans;
    }

    private static int subArrayNGTS(int[] arr, int s) {
        /*
        Given an array where all i >0 , find max k such that no sub array sum of size k is greater
        than given S
        k has a range from 0 to N
        if k increases than sum(k) also increases , so sorted and monotonic increasing
        So we can use BS on sum(k) to satisfy if it is less than given S and return k
         */
        int N = arr.length;
        int L = 0;
        int R = N - 1;
        int sum = 0;
        while (L <= R) {
            int mid = (L + R) / 2;
            sum = maxSubarrayK(arr, mid);
            if (sum <= s && (mid == N || maxSubarrayK(arr, mid + 1) > s)) {
                return mid;
            }
            if (sum > s) {
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return -1;
    }


    public static int findMinPainters(int[] L, int T, int X) {
        int N = L.length;

        // p represents the number of painters currently used
        int p = 1;

        // time represents the current time consumed by the current painter
        int time = 0;

        for (int i = 0; i < N; i++) {
            int boardTime = L[i] * T;

            // Base condition: If a single board takes more time than the max allowed time X,
            // it's impossible to paint it, even with a dedicated painter.
            if (boardTime > X) {
                return -1;
            }

            // Can the current painter take on this board without exceeding X?
            if (time + boardTime <= X) {
                time += boardTime;
            } else {
                // The current painter cannot take this board.
                // We need a new painter.
                p++;
                time = boardTime; // The new painter starts with this board's time
            }
        }

        return p;
    }


    public static long findMinTime(int[] L, int T, int P) {
        long left = 0;
        long right = 0;

        // 1. Calculate the search space boundaries
        for (int board : L) {
            long timeForBoard = (long) board * T;
            left = Math.max(left, timeForBoard); // L = max(L[i] * T)
            right += timeForBoard;               // R = sum(L[i] * T)
        }

        // 2. Binary Search
        while (left <= right) {
            int mid = Math.toIntExact(left + (right - left) / 2);

            // Count painters needed for 'mid' time and 'mid - 1' time
            int cnt = findMinPainters(L, T, mid);
            int cnt1 = findMinPainters(L, T, mid - 1);

            // If current mid is valid (<= P painters) AND
            // the time just below it is invalid (needs > P painters or is impossible)
            if (cnt != -1 && cnt <= P && (cnt1 == -1 || cnt1 > P)) {
                return mid;

            }

            // Adjust search space
            if (cnt == -1 || cnt > P) {
                // 'mid' is too little time, we need more time
                left = mid + 1;
            } else {
                // 'mid' is valid, but we might find a smaller valid time
                right = mid - 1;
            }
        }
        return -1;
    }

    private static int findMaxMinDistance(int[] arr, int C) {
        int N = arr.length;
        int L = 0;// if multiple cows in same stall
        int R = arr[N - 1] - arr[0]; // if cows are at extreme ends

        while (L <= R) {
            int mid = L + (R - L) / 2;
            // Check how many cows we can place with 'mid' distance and 'mid+1' distance
            int count = countCows(arr, mid);
            int count1 = countCows(arr, mid + 1);
            // The exact boundary condition from your notes:
            // 'mid' is valid (we can place C or more cows) AND
            // 'mid+1' is invalid (we can't place C cows)
            if (count >= C && count1 < C) {
                return mid;
            }

            if (count < C) {
                // 'mid' distance is too large, we couldn't place all cows. We need a smaller
                // distance.
                R = mid - 1;
            } else {
                // 'mid' distance is valid, but since we want to MAXIMIZE distance, we search Right.
                L = mid + 1;
            }
        }
        return -1;
    }

    private static int countCows(int[] arr, int minDist) {
        int lastPos = arr[0]; // Keep track of where we placed the last cow
        int cowsPlaced = 1; // Always place the first cow in the very first stall

        for (int i = 1; i < arr.length; i++) { // first cow is already at 0th pos
            // If the distance between the current stall and the last placed cow is >= minDist
            if (arr[i] - lastPos >= minDist) {
                cowsPlaced++; // Place a cow here
                lastPos = arr[i]; // Update the last position
            }
        }
        return cowsPlaced;
    }
}

