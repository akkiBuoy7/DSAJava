package advance.module1;

import java.util.HashSet;
import java.util.Set;

public class Strings_1 {
    public static void main(String[] args) {
        char[] charArray = "The sky is blue".toCharArray();
        reverseWords(charArray);
        System.out.println(new String(charArray));


        int[] A = {100, 4, 3, 6, 10, 20, 11, 5, 101};
        System.out.println(longestConsecutive(A)); // output: 4

        String text = "GEEKS FOR GEEKS";
        String pattern = "FOR";

        // A prime number used for modulo arithmetic to avoid overflow
        int q = 101;

        RabinKarp.search(pattern, text, q);
    }

    private static void reverseWords(char[] s) {
        /*
        Reverse the words of a sentence
        1. reverse teh whole sentence
        2. reverse each word
         */


        // step 1 : reverse the whole array
        reverse(s, 0, s.length - 1);

        // step 2 : reverse each word
        int start = 0;
        for (int end = 0; end <= s.length; end++) {
            /*
            If running pointer encounters space or end of string
             */
            if (end == s.length || s[end] == ' ') {
                // end - 1 because end is space or end of string
                reverse(s, start, end - 1);
                // start will be for next  word beyond the space so end + 1
                start = end + 1;
            }
        }
    }

    private static void reverse(char[] s, int start, int end) {
        while (start <= end) { // two pointer swap
            char temp = s[end];
            s[end] = s[start];
            s[start] = temp;
            start++;
            end--;
        }
    }

    private static int longestConsecutive(int[] a) {

        /*
        TC = O(N)
        SC = O(N)
         */
        Set<Integer> h = new HashSet<>();

        for (int i : a) { // store elements in set
            h.add(i);
        }
        int maxLen = 0;
        for (int x : a) {

            if (!h.contains(x - 1)) { // if x is 1st element of sequence
                int len = 1;
                int curr = x;
                while (h.contains(curr + 1)) { // if next element is in set
                    curr++;
                    len++; // increase the length
                }
                maxLen = Math.max(len, maxLen); // find the max
            }
        }

        return maxLen;
    }

    public static class RabinKarp {

        // d is the number of characters in the input alphabet (256 for ASCII)
        public static final int d = 256;

        static void search(String pattern, String text, int q) {
            int M = pattern.length();
            int N = text.length();

            // Edge case
            if (M > N || M == 0) return;

            int pHash = 0; // Hash value for the pattern
            int tHash = 0; // Hash value for the current window of text
            int h = 1;     // The multiplier for the most significant digit

            // 1. Calculate h = pow(d, M-1) % q
            for (int i = 0; i < M - 1; i++) {
                h = (h * d) % q;
            }

            // 2. Calculate the initial hash value of pattern and first window of text
            for (int i = 0; i < M; i++) {
                pHash = (d * pHash + pattern.charAt(i)) % q;
                tHash = (d * tHash + text.charAt(i)) % q;
            }

            // 3. Slide the pattern over the text one by one
            for (int i = 0; i <= N - M; i++) {

                // Check if the current hash values match
                if (pHash == tHash) {
                    // If hashes match, check characters one by one to avoid spurious hits
                    boolean match = true;
                    for (int j = 0; j < M; j++) {
                        if (text.charAt(i + j) != pattern.charAt(j)) {
                            match = false;
                            break;
                        }
                    }
                    if (match) {
                        System.out.println("Pattern found at index " + i);
                    }
                }

                // Calculate hash value for the next window of text
                // Remove the leading character, add the trailing character
                if (i < N - M) {
                    tHash = (d * (tHash - text.charAt(i) * h) + text.charAt(i + M)) % q;

                    // We might get a negative value of tHash, convert it to positive
                    if (tHash < 0) {
                        tHash = (tHash + q);
                    }
                }
            }
        }
    }
}

