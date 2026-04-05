package advance.module1;

import java.util.ArrayList;
import java.util.List;

public class Strings_2 {
    public static void main(String[] args) {
        char[] s = "xxyaxxyaz".toCharArray();
        int k = 4;
        System.out.println("Longest substring with k is " + longestSubStringEqualToPrefix(s, k));

        String s1 = "abcabcaa";
        int[] z = ZAlgorithm.calculateZArray(s1);

        System.out.print("Z-Array: ");
        for (int val : z) {
            System.out.print(val + " ");
        }
        // Expected for "abcabcaa": 0 0 0 4 0 0 1 1
        // (Note: Z[0] is generally left as 0 or the length of the string depending on
        // implementation)

        System.out.println();
        String text = "repeatareyouready";
        String pattern = "read";

        List<Integer> matchIndices = searchPattern(text, pattern);

        if (matchIndices.isEmpty()) {
            System.out.println("Pattern not found.");
        } else {
            for (int index : matchIndices) {
                System.out.println("Pattern found at original index: " + index);
            }
        }

        String s_1 = "abcaabcaab";
        System.out.println("Smallest period of '" + s_1 + "': " + findSmallestPeriod(s_1));
        // Expected: 4
        String s_2 = "abaabaabaabaaba";
        System.out.println("Smallest period of '" + s_2 + "': " + findSmallestPeriod(s_2));
        // Expected: 3
    }

    private static int longestSubStringEqualToPrefix(char[] s, int k) {
        /*
        find z(k) of a string such that
        z(k) -> find length of longest sub-string
        k -> starting index of the sub-string
        the sub-string starting from k should be same as prefix of the string

        j -> the length travelled from k
        j-k -> the length travelled from index 0
        if [j]th = [j-k]th then we have the prefix
         */
        int N = s.length;
        int j = k;

        while (j < N && s[j] == s[j - k]) {
            j++;
        }

        return j - k;
    }

    public static List<Integer> searchPattern(String text, String pattern) {
        /*
        Using Z array find the pattern.
         */
        List<Integer> result = new ArrayList<>();
        int M = pattern.length();

        // 1. Create the concatenated string with a sentinel character '$'
        String concat = pattern + "$" + text;

        // 2. Calculate the Z-array for the concatenated string
        int[] Z = ZAlgorithm.calculateZArray(concat);

        // 3. Scan the Z-array for matches
        for (int i = 0; i < Z.length; i++) {
            // If the Z-value equals the pattern length, we found a match!
            if (Z[i] == M) {
                // 4. Calculate the index in the original text
                int originalIndex = i - (M + 1);
                result.add(originalIndex);
            }
        }

        return result;
    }

    public static int findSmallestPeriod(String s) {
        /*
        find the smallest period k such that
        forall i S[i] == S[i % k]
         */
        int N = s.length();
        int[] Z = ZAlgorithm.calculateZArray(s);

        // Iterate through the Z-array to find the first valid period
        for (int i = 1; i < N; i++) {
            // If the match extends exactly to the end of the string
            if (i + Z[i] == N) {
                return i; // This 'i' is our smallest period (K)
            }
        }

        // If no smaller period is found, the string is its own smallest period
        return N;
    }

    public static class ZAlgorithm {

        static int[] calculateZArray(String s) {
            int N = s.length();
            int[] Z = new int[N];

            int L = 0;
            int R = 0;

            // Iterate from index 1 to N-1
            for (int i = 1; i < N; i++) {

                // Case 1: Outside the Z-box (No info)
                if (i > R) {
                    L = i;
                    R = i;

                    // Brute force from 'i'
                    while (R < N && s.charAt(R) == s.charAt(R - L)) {
                        R++;
                    }
                    Z[i] = R - L;
                    R--; // R is the index of the last matching character
                }
                // Inside the Z-box
                else {
                    int k = i - L; // The corresponding index in the prefix

                    // Case 2: Past information fits strictly inside the box
                    if (i + Z[k] <= R) {
                        Z[i] = Z[k];
                    }
                    // Case 3: Past information overflows past R
                    else {
                        L = i;
                        // R remains where it is, we resume brute force from R
                        while (R < N && s.charAt(R) == s.charAt(R - L)) {
                            R++;
                        }
                        Z[i] = R - L;
                        R--;
                    }
                }
            }
            return Z;
        }
    }
}
