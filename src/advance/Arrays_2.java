package advance;

import java.util.Arrays;

public class Arrays_2 {
    public static void main(String[] args) {
        findMatrixSumBrute();
        findSum1D();
        findMatrixSumPref();
        findAllSubMatrixSum();
    }

    public static void findSum1D() {
        /*
        For 1D array the formula is
        p[ex] - p[sx-1]
         */
        int[] a1 = {1, 20, 5, 3, 6, 9, 8, 10};
        int sx = 2;
        int ex = 6;

        int[] prefix_a1 = new int[a1.length];
        int p = 0;
        for (int i = 0; i < a1.length; i++) {
            p += a1[i];
            prefix_a1[i] = p;
        }

        System.out.println("prefix array is " + Arrays.toString(prefix_a1));
        int sum = prefix_a1[ex] - prefix_a1[sx - 1];
        System.out.printf("sum of 1D array for index %d to %d is %d", 2, 6, sum);
    }

    public static void findMatrixSumBrute() {
        // T(c) = O(N*M*Q)
        // S(c) = O(1)
        int[][] array = {{1, 3, 5, 2, -1}, {4, 8, 5, 0, 6}, {10, 20, -1, 3, 5}, {1, 5, -5, 10, 6}};
        System.out.println("Input array is >");
        System.out.println(Arrays.deepToString(array));
        // (1,0) (3,2)
        int sx = 1;
        int ex = 3;
        int sy = 0;
        int ey = 2;
        int sum = 0;
        for (int i = sx; i <= ex; i++) {
            for (int j = sy; j <= ey; j++) {
                sum += array[i][j];
            }
        }
        System.out.println("sub matrix sum is " + sum);
    }

    public static void findMatrixSumPref() {

        /*
        sum =   P[ex][ey]
                - P[sx-1][ey]
                - P[ex][sy-1]
                + P[sx-1][sy-1]


          T(C) = O(N*M + Q)
          n*m = precondition i.e. prefix array
          Q = no of queries
         */

        int[][] array = {
                {1, 3, 5, 2, -1},
                {4, 8, 5, 0, 6},
                {10, 20, -1, 3, 5},
                {1, 5, -5, 10, 6}
        };

        int rows = array.length;
        int cols = array[0].length;

        int sx = 1, sy = 0, ex = 3, ey = 2;

        int[][] prefix_array = new int[rows][cols];

        // Build prefix sum matrix
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int up = (i > 0) ? prefix_array[i - 1][j] : 0; // value above ith/jth cell
                int left = (j > 0) ? prefix_array[i][j - 1] : 0; // value left of ith/jth cell
                // minus value considered two times
                int diag = (i > 0 && j > 0) ? prefix_array[i - 1][j - 1] : 0;

                prefix_array[i][j] = array[i][j] + up + left - diag;
            }
        }

        int sum = prefix_array[ex][ey];
        if (sx > 0) sum -= prefix_array[sx - 1][ey];
        if (sy > 0) sum -= prefix_array[ex][sy - 1];
        if (sx > 0 && sy > 0) sum += prefix_array[sx - 1][sy - 1];
        System.out.println();
        System.out.println("findMatrixSumPref >>>Sub matrix sum is " + sum);
    }


    public static void findAllSubMatrixSum() {
        /*
        Sum  =  contribution og i-jth element in the total sum
                E(i-jth element * all submatrix i-jth element is part of)
                a[i][j] * ( { [i+1] * [j+1] }* { [N-i] * [M-j] } )

         T(C) = O(N*M)
         */
        int[][] array = {
                {1, 3, 5, 2, -1},
                {4, 8, 5, 0, 6},
                {10, 20, -1, 3, 5},
                {1, 5, -5, 10, 6}
        };

        int rows = array.length;
        int cols = array[0].length;
        int ans = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                ans += array[i][j] * (i + 1) * (j + 1) * (rows - i) * (cols - j);
            }
        }
        System.out.println();
        System.out.println("findAllSubMatrixSum >>>All Sub matrix sum is " + ans);
    }
}
