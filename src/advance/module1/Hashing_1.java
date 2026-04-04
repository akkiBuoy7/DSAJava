package advance.module1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Hashing_1 {
    public static void main(String[] args) {
        int[] arr = {2, 3, 5, 3, 5, 5, 1, 4, 4};
        frequencyMap(arr);
        distinctSet(arr);
        int[] A = {1, 2, 3, 4, 1, 3, 5, 1, 5, 2};

        int[] result = findMinDistancePair(A);

        if (result[0] != -1) {
            System.out.println("Ans = {" + result[0] + ", " + result[1] + "}");
        } else {
            System.out.println("No matching pairs found.");
        }

        int[] A1 = {1, -2, 2, 0, -2, -3, -1, 4, 3};
        System.out.println("Longest subarray length: " + findMaxDistanceSumZero(A1));
    }

    private static void frequencyMap(int[] arr) {
        // TC  = O(N)
        // SC = O(Range)
        HashMap<Integer, Integer> h = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            if (h.containsKey(arr[i])) {
                h.put(arr[i], h.get(arr[i]) + 1);
            } else {
                h.put(arr[i], 1);
            }
        }
        System.out.println(h);
    }

    private static void distinctSet(int[] arr) {
        // TC = O(N)
        // SC = O(N)
        Set<Integer> s = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            s.add(arr[i]);
        }
        System.out.println(s);
    }

    public static int[] findMinDistancePair(int[] arr) {

        /*
        Given an array find (i,j) such that A[i] = A[j] and
        |i-j| = min
         */


        // Map will store: Key = Array Element, Value = Last Seen Index
        HashMap<Integer, Integer> lastSeenMap = new HashMap<>();

        int minDistance = Integer.MAX_VALUE;
        int[] bestPair = new int[]{-1, -1};

        for (int i = 0; i < arr.length; i++) {
            int currentElement = arr[i];

            // If we have seen this number before, calculate the distance
            if (lastSeenMap.containsKey(currentElement)) {
                int prevIndex = lastSeenMap.get(currentElement);
                int currentDistance = i - prevIndex;

                // If this is the shortest distance we've found so far, update our answer
                if (currentDistance < minDistance) {
                    minDistance = currentDistance;
                    bestPair[0] = prevIndex;
                    bestPair[1] = i;
                }
            }

            // CRITICAL STEP: Always update the map with the current index!
            // This ensures the next time we see this number, we check the closest possible pair.
            lastSeenMap.put(currentElement, i);
        }

        return bestPair;
    }

    private static int findMaxDistanceSumZero(int[] arr){
        /*
        Given an array find the max length of the sub array whose sum is zero
         */

// Map stores: Key = Prefix Sum, Value = FIRST Index we saw this sum
        HashMap<Integer, Integer> firstSeenMap = new HashMap<>();
        int maxDistance = Integer.MIN_VALUE;
        int currentPrefixSum = 0;
        // CRITICAL: Handle the case where the valid subarray starts at index 0.
        // This represents the sum "before" we've looked at any elements.
        firstSeenMap.put(0, -1);
        for (int i = 0; i<arr.length;i++){
            currentPrefixSum+=arr[i];
            // If we have seen this prefix sum before, we found a zero-sum subarray!
            if (firstSeenMap.containsKey(currentPrefixSum)){
                int firstSeenIndex = firstSeenMap.get(currentPrefixSum);
                int currentLength = i - firstSeenIndex;
                // Update maxLength if this new subarray is longer
                maxDistance = Math.max(maxDistance,currentLength);
            }else {
                // ONLY put it in the map if we haven't seen it before.
                // This ensures we keep the earliest possible index to maximize length.
                firstSeenMap.put(currentPrefixSum,i);
            }
        }
        return maxDistance;
    }

}
