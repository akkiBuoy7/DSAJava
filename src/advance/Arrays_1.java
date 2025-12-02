package advance;

public class Arrays_1 {
    public static void main(String[] args) {
        int ans1 = problem1();
        System.out.println("The maximum diff between any two element in the array is " + ans1);

        int ans2 = problem2();
        System.out.println("The max value of the function is " + ans2);

        int ans3 = problem3();
        System.out.println("The max sum of the sub array is " + ans3);
    }

    private static int problem1() {
        /*
        Given an array find the max difference between two elements of array
        f(i,j) = A(i) - A(j)
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

    private static int[] getSubArray(int end, int[] a) {
        int[] result = new int[end + 1];
        for (int i = 0; i <= end; i++) {
            result[i] = a[i];
        }
        return result;
    }
}

