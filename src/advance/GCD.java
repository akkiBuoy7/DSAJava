package advance;

public class GCD {
    public static void main(String[] args) {
        try {
            System.out.println("bruteForce : gcd " + bruteForceGcd(5, 15));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println("recursion : gcd " + recursionGcd(35, 0));
        System.out.println("iterative : gcd " + iterativeGcd(15, 5));
        prefixSuffixGcd();
        int[] A = {10, 30, 6, 15};
        System.out.println(existsSubsequenceWithGCDOne(A));
        int[] H = {6, 4, 2};
        System.out.println(minLastSurvivorHealth(H));
    }

    private static int bruteForceGcd(int a, int b) throws Exception {
        /*
        GCD = greatest no that divides both a and b
        T(c) = O(Min(a,b))
        S(c) = O(1)
         */
        if (a == 0 && b == 0) {
            throw new Exception("GCD not possible");
        }
        if (a == 0 || b == 0) {
            return Math.max(a, b);
        }
        int ans = 1;
        for (int i = 2; i <= a && i <= b; i++) {
            if (a % i == 0 && b % i == 0) {
                ans = i;
            }
        }
        return ans;
    }

    private static int recursionGcd(int a, int b) {
        // T(c) < O(log(max(a,b)))
        if (a == 0 || b == 0) {
            return Math.max(a, b);
        }
        if (a == 0) {
            return b;
        }
        return recursionGcd(b % a, a);
    }

    private static int iterativeGcd(int a, int b) {
        // T(c) < O(log(max(a,b)))
        if (a == 0 || b == 0) {
            return Math.max(a, b);
        }
        while (a > 0) { // consider decreasing a or a is smaller num
            int temp = a;
            a = b % a; // decreasing a
            b = temp;
        }
        return b;
    }

    private static void prefixSuffixGcd() {
        /*
        T(c) = O(N log(A[i]))
        S(c) = O(N)
         */

        int[] a = {9, 18, 49, 12, 30};
        int N = a.length;
        int[] prefixGcd = new int[N];
        prefixGcd[0] = a[0];
        int[] suffixGcd = new int[N];
        suffixGcd[N - 1] = a[N - 1];

        for (int i = 1; i < N; i++) {
            prefixGcd[i] += recursionGcd(a[i], prefixGcd[i - 1]);
        }
        for (int j = N - 2; j >= 0; j--) {
            suffixGcd[j] += recursionGcd(a[j], suffixGcd[j + 1]);
        }

        /*
        to handle the boundary case
        if 0th element removed => remaining element (1 to N-1) => suffixGcd[1]
        if Nth element removed => remaining element (0 to N-2) => prefixGcd[N - 2]
         */
        int ans = Math.max(suffixGcd[1], prefixGcd[N - 2]);


        int ele = -1;
        for (int i = 1; i < N - 1; i++) {
            int a1 = prefixGcd[i - 1];
            int b1 = suffixGcd[i + 1];
            int gcd = recursionGcd(a1, b1);
            if (gcd > ans) {
                ans = gcd;
                ele = a[i];

            }

        }
        System.out.printf("Max sum is %d and deleted element is %d", ans, ele);
        System.out.println();
    }

    public static boolean existsSubsequenceWithGCDOne(int[] A) {
        /*
        If gcd(all elements) = 1, then that whole array itself is a subsequence with GCD = 1.
        If gcd(all elements) > 1, then every subsequence will also have GCD > 1.

        T(c) = O(N log(A[i]))
        S(c) = O(1)
         */
        int g = A[0];

        for (int i = 1; i < A.length; i++) {
            g = recursionGcd(g, A[i]);
            if (g == 1) return true; // early exit
        }

        return g == 1;
    }

    public static int minLastSurvivorHealth(int[] H) {
        /*
        T(c) = O(N log(A[i]))
        S(c) = O(1)
         */
        int g = H[0];

        for (int i = 1; i < H.length; i++) {
            g = iterativeGcd(g, H[i]);
        }

        return g;
    }
}
