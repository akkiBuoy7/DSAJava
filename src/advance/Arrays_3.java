package advance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Arrays_3 {
    public static void main(String[] args) {

        maxDiffRight();
        waterTrap();
        interval();
        interval_1();
        firstMissingPositive();
        firstMissingPositive_1();
    }


    public static void maxDiffRight() {

        //T(C) = O(N)
        // S(C) = O(N)

        int[] a = {6, 3, 2, 4, 1, 3, 5, 3, 4};
        //int [] p = new int[a.length];
        int ans = Integer.MIN_VALUE;
        int N = a.length;
        int[] maxRight = new int[a.length];
        maxRight[N - 1] = a[N - 1];
        for (int i = N - 1; i >= 0; i--) {
            ans = Math.max(a[i], ans);
            // p[i] = ans;

            maxRight[i] = ans - a[i];
        }
        System.out.println("height max diff array >> " + Arrays.toString(maxRight));

    }

    public static void waterTrap() {

        /*
        Prefix/Suffix array approach

        water[i] = min( max (maxLeft[i]) max(maxRight[i]) ) - h[i]
        max (maxLeft[i]) => maximum height from 0 to i
                      => max( Left[i-1] , h[i] )
        max (maxRight[i]) => maximum height from i to n-1
                       => max( Right[i+1] , h[i] )
         */
        int[] height = {6, 3, 2, 4, 1, 3, 5, 3, 4};

        int N = height.length;
        int[] maxLeft = new int[N];
        int[] maxRight = new int[N];

        // Prefix array
        maxLeft[0] = height[0];
        for (int i = 1; i <= N - 1; i++) {
            maxLeft[i] = Math.max(height[i], maxLeft[i - 1]);
        }

        // Suffix array
        maxRight[N - 1] = height[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            maxRight[i] = Math.max(height[i], maxRight[i + 1]);
        }

        int water = 0;
        for (int i = 0; i < N; i++) {
            water += Math.min(maxLeft[i], maxRight[i]) - height[i];
        }

        System.out.println("Total water is " + water);
    }

    public static void interval() {

        /*
        Given sorted list of intervals
        FInd all overlapping intervals and merge them
         */

        int[][] intervals = {
                {0, 2}, {1, 4}, {5, 6}, {6, 8}, {7, 10}, {13, 15}
        };

        List<int[]> result = new ArrayList<>();

        int S = intervals[0][0];
        int E = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            int start = intervals[i][0];
            int end = intervals[i][1];

            if (start <= E) {
                // overlapping
                E = Math.max(end, E);
            } else {
                // not overlapping
                result.add(new int[]{S, E});
                S = start;
                E = end;
            }
        }
        result.add(new int[]{S, E});

        for (int[] a : result) {
            System.out.println(Arrays.toString(a));
        }
    }

    public static void interval_1() {
        /*
        Given set of non overlapping intervals sorted with start time
        insert a new nterval so that the final list is sorted
         */

        int[][] intervals = {
                {0, 2}, {4, 7}, {8, 9}, {10, 11}, {12, 14}
        };

        int[] Q = {5, 10};

        int L = Q[0];
        int R = Q[1];

        List<int[]> result = new ArrayList<>();

        for (int i = 0; i < intervals.length; i++) {
            int start = intervals[i][0];
            int end = intervals[i][1];

            if (start <= R && L <= end) {
                // overlapping
                L = Math.min(L, start);
                R = Math.max(R, end);
            } else {
                // not overlapping
                result.add(new int[]{start, end});
            }
        }
        // insert merged interval in sorted order
        int idx = 0;
        while (idx < result.size() && result.get(idx)[0] < L) {
            idx++;
        }
        result.add(idx, new int[]{L, R});

        for (int[] a : result) {
            System.out.print(Arrays.toString(a) + " ");
        }
        System.out.println();

    }


    public static void firstMissingPositive() {

        /*
        Find the fist missing positive integer in the array
        T(C) = O(N)
        S(C) = O(N)
         */

        int[] A = {10, 3, 1, 2, 5, -8, -3, 4};

        int N = A.length;
        int ans = -1;

        int[] visited = new int[N + 1];

        // mark presence
        for (int i = 0; i < N; i++) {
            if (A[i] > 0 && A[i] <= N) {
                visited[A[i]] = 1;
            }
        }

        // find first missing positive
        for (int i = 1; i <= N; i++) {
            if (visited[i] == 0) {
                ans = i;
                break;
            }
        }

        if (ans != -1) {
            System.out.println("Missing no is " + ans);
        } else {
            System.out.println("Missing no is " + (N + 1));
        }
    }

    public static void firstMissingPositive_1() {

        int[] A = {10, 3, 1, 2, 5, -8, -3, 4};
        int N = A.length;
        int ans = -1;
        int i = 0;
        while (i < N) {
            int correctIndex = A[i] - 1;

            if (A[i] > 0 && A[i] <= N && A[i] != A[correctIndex]) {
                // swap A[i] with A[correctIndex]
                int temp = A[i];
                A[i] = A[correctIndex];
                A[correctIndex] = temp;
            } else {
                i++;
            }
        }

        // find first missing
        for (i = 0; i < N; i++) {
            if (A[i] != i + 1) {
                ans = i+1;
                break;
            }
        }

        if (ans != -1) {
            System.out.println("Missing no is " + ans);
        } else {
            System.out.println("Missing no is " + (N + 1));
        }

    }


}
