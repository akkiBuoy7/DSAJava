package advance.module1;

import java.util.Arrays;

public class Arrays_1 {
    public static void main(String[] args) {
        int ans1 = problem1();
        System.out.println("The maximum diff between any two element in the array is " + ans1);

        int ans2 = problem2();
        System.out.println("The max value of the function is " + ans2);

        int ans3 = problem3();
        System.out.println("The max sum of the prefix sub array is " + ans3);

        int ans4 = problem4();
        System.out.println("The max sum of the sub array is " + ans4);

        int ans4_1 = problem4_1();
        System.out.println("The max sum of the sub array using Kadane’s Algorithm is " + ans4_1);

        int N = 7;
        // add 3 at index 1
        int[][] queries = {
                {1, 3},
                {4, 2},
                {3, 1}
        };

        int[] result = problem5(N, queries);
        System.out.println("Final Array: " + Arrays.toString(result));
        // Expected output: [0, 2, 5, 6, 6, 6, 6]

        int N1 = 7;
        // add 2 at indices 1 and 3
        int[][] queries1 = {
                {1, 3, 2},
                {2, 5, 3},
                {5, 6, 5}
        };

        int[] result1 = problem5_1(N, queries1);
        System.out.println("Final Array: " + Arrays.toString(result1));

        int[] a = {1,0,0,1,0,0,1,1,1};
        int[] result2 = problem6(a);

        System.out.println(Arrays.toString(result2));

    }

    private static int problem1() {
        /*
        Given an array find the max difference between two elements of array
        f(i,j) = A(i) - A(j)
        max difference in an array is the difference between max and min element of the array

        T(c) = O(N)
        S(c) = O(1)
         */

        int[] a = {1, 3, -2};
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i : a) {
            max = Math.max(i, max);
            min = Math.min(i, min);
        }
        return max - min;
    }

    private static int problem2() {
        /*
        Given an array find the max value of the below function
        f(i,j) = |A(i)-A(j)| + |i-j|

        1st part ->
        |i - j| will be max when i>j
        so we can write |i - j| ~= (i - j)

        2nd part ->

        case1: when A(i) >= A(j)
        [A(i) - A(j)] + (i - j)
        [A(i) + i] - [A(j) + j]
        Xk = [ A(k) + k ]

        case2: when A(i) < A(j)
        [A(j) - A(i)] + (i - j)
        [A(j) - j] - [A(i) - i]
        Yk = [ A(k) - k ]

        So Max(f(i,j)) = Max(case1 , case2)
                       = Max(f(Xi - Xj) , f(Yi - Yj))

         T(c) = O(N)
         S(c) = O(1)
         */
        int[] a = {1, 3, -2};
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int X = 0;
        int Y = 0;
        int case1 = 0;
        int case2 = 0;

        for (int i = 0; i < a.length; i++) {
            X = a[i] + i;
            Y = a[i] - i;

            maxX = Math.max(X, maxX);
            minX = Math.min(X, minX);

            maxY = Math.max(Y, maxY);
            minY = Math.min(Y, minY);


        }
        case1 = maxX - minX;
        case2 = maxY - minY;
        return Math.max(case1, case2);
    }

    private static int problem3() {
        /*
        Given an array , find the prefix sub array that has the max sum
        prefix => should start for 0 index
        sub array => should be continuous of the parent array


        sol => Loop through the array
        find the sum of all the elements from index 0
        up to an index i and get the max bw current sum(ith) and previous sum (i-1)th

        T(c) = O(N)
        S(c) = O(1)
         */

        int[] a = {10, -5, 7, 8, -1, 2, -20, 5};
        int pref = 0;
        int ans = Integer.MIN_VALUE;
        int end = 0;
        for (int i = 0; i < a.length; i++) {
            pref += a[i];
            if (pref > ans) {
                ans = pref;
                end = i;
            }
        }
        int[] subArray = getSubArray(end, a);
        for (int k : subArray) {
            System.out.print(k + "  ");
        }
        System.out.println();
        return ans;
    }

    private static int problem4() {
        /*
        Given an array , find the sub array that has the max sum
        sub array => should be continuous of the parent array
        there is no constraint that it should start from 0


        sol => find the sum of all the index from every element in the array
         */

        // TC -> O(N2)

        int[] a = {10, -5, 7, 8, -1, 2, -20, 5};
        int pref;
        int ans = Integer.MIN_VALUE;
        int end = 0;
        int start = 0;
        for (int i = 0; i < a.length; i++) { // start of sub array
            pref = 0;
            for (int j = i; j < a.length; j++) { // end of sub array
                pref += a[j];
                if (pref > ans) { // if current subarray pref > global max of all sub array
                    ans = pref;
                    start = i;
                    end = j;
                }
            }
        }
        int[] subArray = getSubArray(start, end, a);
        for (int k : subArray) {
            System.out.print(k + "  ");
        }
        System.out.println();
        return ans;
    }

    private static int problem4_1() {
        //Kadane’s Algorithm
        int[] a = {10, -5, 7, 8, -20, -10, 3, 6, -10, 5};
        //int [] a = {-3,-5,-2,-8};
        int pref = 0;
        int ans = Integer.MIN_VALUE;
        int end = 0;
        int start = 0;
        int tempStart = 0;  // temp start when pref resets

        // TC -> O(N)

        for (int i = 0; i < a.length; i++) {
            pref += a[i];
            if (pref > ans) {
                ans = pref;
                start = tempStart;
                end = i;
            }
            if (pref < 0) {
                pref = 0;
                tempStart = i + 1;
            }
        }
        int[] subArray = getSubArray(start, end, a);
        for (int k : subArray) {
            System.out.print(k + "  ");
        }
        System.out.println();
        return ans;
    }

    // Apply queries of the form (i, x) -> add x to A[i..N-1]
    public static int[] problem5(int N, int[][] queries) {
        int[] diff = new int[N];  // difference array of size N

        // Apply all queries
        for (int[] q : queries) {
            int i = q[0];
            int x = q[1];
            diff[i] = x; // just update the index with the value
        }
        System.out.println("Diff array is " + Arrays.toString(diff));
        // Build final array using prefix sum
        int[] result = new int[N];
        int pref = 0;
        for (int i = 0; i < N; i++) {
            pref += diff[i]; //prefix sum
            result[i] = pref;
        }

        return result;
    }


    public static int[] problem5_1(int N, int[][] queries) {
        int[] diff = new int[N];  // difference array of size N

        // Apply all queries
        for (int[] q : queries) {
            int i = q[0];
            int j = q[1];
            int x = q[2];


            diff[i] = x;
            if (j + 1 < N) {
                diff[j + 1] = -x;
            }

        }
        System.out.println("Diff array is " + Arrays.toString(diff));
        // Build final array using prefix sum
        int[] result = new int[N];
        int pref = 0;
        for (int i = 0; i < N; i++) {
            pref += diff[i]; //prefix sum
            result[i] = pref;
        }

        return result;
    }

    private static int[] problem6(int[] a) {

        /*
        Given a binary array flip the max no of 0 to 1 of any sub array in one attempt
        so that max 1 remains in the final sub array

        T(c) = O(N)
         */

        int n = a.length;

        int currSum = 0, maxSum = 0;
        int start = 0, end = -1;
        int tempStart = 0;

        // Kadane with index tracking
        for (int i = 0; i < n; i++) {
            // if we flip a 0 then we are adding 1
           // if we flip a one then we are adding -1
            int val = (a[i] == 0) ? 1 : -1;

            if (currSum + val < val) {
                currSum = val;
                tempStart = i;
            } else {
                currSum += val;
            }

            if (currSum > maxSum) {
                maxSum = currSum;
                start = tempStart;
                end = i;
            }
        }

        // If no beneficial flip, return original array
        if (end == -1) return a;

        // Flip the subarray [start..end]
        for (int i = start; i <= end; i++) {
            a[i] = (a[i] == 0) ? 1 : 0;
        }

        return a;
    }

    private static int[] getSubArray(int end, int[] a) {
        int[] result = new int[end + 1];
        for (int i = 0; i <= end; i++) {
            result[i] = a[i];
        }
        return result;
    }

    private static int[] getSubArray(int start, int end, int[] a) {
        int[] result = new int[end + 1];
        for (int i = start; i <= end; i++) {
            result[i] = a[i];
        }
        return result;
    }
}

