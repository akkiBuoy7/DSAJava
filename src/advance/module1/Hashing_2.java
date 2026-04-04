package advance.module1;

import java.util.HashMap;
import java.util.HashSet;

public class Hashing_2 {
    public static void main(String[] args) {
        String s = "bbaabb";

        System.out.println(canDivideIntoKSameStrings(s, 2)); // true  → "abb" + "abb"
        System.out.println(canDivideIntoKSameStrings(s, 3)); // false


        int[][] points = {
                {1, 1},
                {1, 3},
                {2, 1},
                {2, 3},
                {3, 1},
                {3, 3}
        };

        System.out.println(countRectangles(points));

        String A = "abc";
        String B = "abcbacabc";

        System.out.println(countPermutations(A, B)); // output: 5

        int[][] points1 = {
                {1, 3},
                {3, 1},
                {4, 3},
                {4, 1},
                {1, 1}
        };

        System.out.println(countRightTriangles(points1));
    }


    public static boolean canDivideIntoKSameStrings(String s, int k) {
        /*
        Given a string s and integer k, check if  can rearrange the characters of s so that
        it equals a concatenation of k identical strings.
        Since we can rearrange, only the frequency of each character matters.
        Every character's frequency must be divisible by k.
         */

        // Edge case: length must be divisible by k
        if (s.length() % k != 0) return false;

        // Count frequency of each character using HashMap
        HashMap<Character, Integer> freqMap = new HashMap<>();
        for (char ch : s.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }

        // Every character's frequency must be divisible by k
        for (int count : freqMap.values()) {
            if (count % k != 0) return false;
        }

        return true;
    }

        public static int countRectangles(int[][] points) {

            HashSet<String> set = new HashSet<>();

            // store all points in HashSet
            for (int i = 0; i < points.length; i++) {
                int x = points[i][0];
                int y = points[i][1];
                set.add(x + "#" + y);
            }

            int count = 0;

            // check all pairs of points (diagonal)
            for (int i = 0; i < points.length; i++) {
                int x1 = points[i][0];
                int y1 = points[i][1];

                for (int j = i + 1; j < points.length; j++) {
                    int x2 = points[j][0];
                    int y2 = points[j][1];

                    // must be diagonal points
                    if (x1 != x2 && y1 != y2) {

                        // other two corners
                        String p3 = x1 + "#" + y2;
                        String p4 = x2 + "#" + y1;

                        if (set.contains(p3) && set.contains(p4)) {
                            count++;
                        }
                    }
                }
            }

            // each rectangle counted twice
            return count / 2;

    }

    public static int countPermutations(String A, String B) {
        /*
        Sliding window — the key insight is that when you slide the window by 1,
         you only need to remove the outgoing character and add the incoming character.
        Comparing two frequency arrays of size 26 is O(26) = O(1). Final TC = O(M × 26) = O(M), SC = O(26) = O(1).
         */

        int n = A.length();
        int m = B.length();

        if (n > m) return 0;

        int[] freqA = new int[26];
        int[] freqB = new int[26];

        // freq of A
        for (int i = 0; i < n; i++) {
            freqA[A.charAt(i) - 'a']++;
        }

        // first window freq in B
        for (int i = 0; i < n; i++) {
            freqB[B.charAt(i) - 'a']++;
        }

        int count = 0;

        if (isSame(freqA, freqB)) count++;

        // sliding window
        for (int i = n; i < m; i++) {
            // add new char
            freqB[B.charAt(i) - 'a']++;

            // remove old char
            freqB[B.charAt(i - n) - 'a']--;

            if (isSame(freqA, freqB)) count++;
        }

        return count;
    }

    private static boolean isSame(int[] a, int[] b) {
        for (int i = 0; i < 26; i++) {
            if (a[i] != b[i]) return false;
        }
        return true;
    }


    public static long countRightTriangles(int[][] points) {

        /*
        For a point P(x, y) to be the right-angle vertex:
        choose one point with same x (vertical side)
        choose one point with same y (horizontal side)

        Example:
        If P = (x, y)

        Possible points:

        (x, y2) → vertical partner

        (x2, y) → horizontal partner

        Then triangle formed.

        So number of triangles with right angle at P:

        (countX[x]−1)×(countY[y]−1)

        Where:

        countX[x] = how many points have x coordinate = x

        countY[y] = how many points have y coordinate = y

        We subtract 1 because P itself is included.
         */

        HashMap<Integer, Integer> countX = new HashMap<>();
        HashMap<Integer, Integer> countY = new HashMap<>();

        int n = points.length;

        // count frequency of x and y
        for (int i = 0; i < n; i++) {
            int x = points[i][0];
            int y = points[i][1];

            countX.put(x, countX.getOrDefault(x, 0) + 1);
            countY.put(y, countY.getOrDefault(y, 0) + 1);
        }

        long ans = 0;

        // compute triangles
        for (int i = 0; i < n; i++) {
            int x = points[i][0];
            int y = points[i][1];

            long vertical = countX.get(x) - 1;
            long horizontal = countY.get(y) - 1;

            ans += vertical * horizontal;
        }

        return ans;
    }

}
