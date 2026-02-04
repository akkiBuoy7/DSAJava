package advance.module1;

import java.util.ArrayList;
import java.util.Arrays;

public class SortingOne {

    public static void main(String[] args) {
        int[] a = {10, 5, 8, 20, 6, 3, 2};
        int[] selection_sort = selectionSort(a);
        for (int i : selection_sort) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("==============");
        int[] bubble_sort = selectionSort(a);
        for (int i : bubble_sort) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("==============");
        int[] a1 = {3, 9, 2, 4, 15, 10, 19};
        int[] sort_merged = sortOddEvenMerged(a1);
        for (int i : sort_merged) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("==============");
        mergeSort(a1, 0, a1.length - 1);

        for (int x : a1) {
            System.out.print(x + " ");
        }

        System.out.println();
        System.out.println("==============");

        int[] arr = {4, 5, 1, 2, 6, 3};

        long inversions = mergeSortInv(0, arr.length - 1, arr);

        System.out.println("Sorted Array:");
        for (int x : arr) {
            System.out.print(x + " ");
        }

        System.out.println("\nInversion Count = " + inversions);

        int[] stream = {7, 2, 10, 8, 12};
        int[] a5 = new int[stream.length];
        int n = 0;

        for (int x : stream) {
            n = insertSort(a5, n, x);
            System.out.println("After inserting " + x + " -> "
                    + Arrays.toString(Arrays.copyOf(a5, n)));
        }
    }

    private static int[] selectionSort(int[] a) {

        int N = a.length;

        for (int last = N - 1; last > 0; last--) {
            int maxIndex = 0;
            for (int i = 0; i <= last; i++) {
                if (a[i] > a[maxIndex]) {
                    maxIndex = i;
                }
            }

            int temp = a[maxIndex];
            a[maxIndex] = a[last];
            a[last] = temp;
        }
        return a;
    }

    private static int[] bubbleSort(int[] a) {
        int N = a.length;
        for (int pass = 0; pass < N - 1; pass++) {
            for (int i = 0; i < N - 1 - pass; i++) {
                if (a[i] < a[i + 1]) {
                    int temp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = temp;
                }
            }
        }
        return a;
    }

    private static int[] sortOddEvenMerged(int[] A) {

        int n = A.length;

        // store odds and evens
        ArrayList<Integer> odds = new ArrayList<>();
        ArrayList<Integer> evens = new ArrayList<>();

        // Step 1: separate
        for (int x : A) {
            if (x % 2 == 0) {
                evens.add(x);
            } else {
                odds.add(x);
            }
        }

        // Step 2: merge two sorted lists
        int[] result = new int[n];
        int i = 0, o = 0, e = 0;

        while (o < odds.size() && e < evens.size()) {
            if (odds.get(o) < evens.get(e)) {
                result[i++] = odds.get(o++);
            } else {
                result[i++] = evens.get(e++);
            }
        }

        // remaining odds
        while (o < odds.size()) {
            result[i++] = odds.get(o++);
        }

        // remaining evens
        while (e < evens.size()) {
            result[i++] = evens.get(e++);
        }

        return result;
    }

    // Main function that sorts array[st...end] using merge()
    private static void mergeSort(int[] arr, int st, int end) {
        if (st < end) {
            // Find the middle point
            int mid = st + (end - st) / 2;

            // Sort first and second halves
            mergeSort(arr, st, mid);
            mergeSort(arr, mid + 1, end);

            // Merge the sorted halves
            merge(arr, st, mid, end);
        }
    }

    // Merges two subarrays of arr[].
    private static void merge(int[] arr, int st, int mid, int end) {
        // Find sizes of two subarrays to be merged
        int n1 = mid - st + 1;
        int n2 = end - mid;

        // Create temporary arrays
        int[] L = new int[n1];
        int[] R = new int[n2];

        // Copy data to temp arrays
        for (int i = 0; i < n1; ++i) {
            L[i] = arr[st + i];
        }
        for (int j = 0; j < n2; ++j) {
            R[j] = arr[mid + 1 + j];
        }
        // Merge the temp arrays
        int i = 0, j = 0, k = st;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
            // k++;
        }

//         Copy remaining elements if any
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }


    /*
    given an array find the no of inversions .
    inversions =>
    i<j & A[i] > A[j]
    if jth(right) element is smaller and picked while merging or sorting
    then all the remaining elements from i to middle on the left forms inversion with the jth
    element
     */
    static long mergeSortInv(int st, int end, int[] a) {
        long invCount = 0;
        if (st < end) {
            int mid = st + (end - st) / 2;
            invCount += mergeSortInv(st, mid, a);
            invCount += mergeSortInv(mid + 1, end, a);
            invCount += mergeInv(st, mid, end, a);
        }

        return invCount;
    }

    static long mergeInv(int st, int mid, int end, int[] a) {
        long invCount = 0;
        int n1 = (mid - st) + 1;
        int n2 = (end - mid);
        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) {
            L[i] = a[st + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = a[mid + 1 + j];
        }

        int i = 0, j = 0, k = st;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                a[k++] = L[i++];
            } else {
                a[k++] = R[j++];
                invCount += (n1 - i);
            }
        }
        while (i < n1) {
            a[k++] = L[i++];
        }

        while (j < n2) {
            a[k++] = R[j++];
        }

        return invCount;
    }

    /*
    Given a stream of integers place them in the array such that
    they are sorted in the final array
     */

    static int insertSort(int[] a, int n, int x) {
        int i = n;
        for (i = n; i > 0; i--) {
            if (a[i-1]>x){
                a[i] = a[i-1];
            }else {
                break;
            }
        }
        a[i] = x;
        return n++;
    }

}