package advance;

import java.util.Arrays;

public class Arrays_3 {
    public static void main(String[] args) {

        maxDiffRight();
        waterTrap();
    }


    public static void maxDiffRight() {

        //T(C) = O(N)
        // S(C) = O(N)

        int[] a = {6, 3, 2, 4, 1, 3, 5, 3, 4};
        //int [] p = new int[a.length];
        int ans = Integer.MIN_VALUE;
        int N = a.length;
        int[] maxRight = new int[a.length];
        maxRight[N-1] = a[N-1];
        for (int i = N - 1; i >= 0; i--) {
            ans = Math.max(a[i],ans);
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

}
