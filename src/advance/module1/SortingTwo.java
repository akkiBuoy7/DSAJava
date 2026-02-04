package advance.module1;

import java.util.Arrays;

public class SortingTwo {
    public static void main(String[] args) {
        int[] stream = {7, 2, 10, 8, 12};
        int[] a = new int[stream.length];
        int n = 0;

        for (int x : stream) {
            n = insert(a, n, x);
        }

        System.out.println(Arrays.toString(Arrays.copyOf(a, n)));

        int[] A = {7, 3, 2, 4, 7, 5, 6};

        quickSort(A, 0, A.length - 1);
        System.out.println(Arrays.toString(A));
    }

    // insert x into sorted a[0..n-1]
    static int insert(int[] a, int n, int x) {
        int i;

        for (i = n; i > 0; i--) {
            if (a[i - 1] > x) {
                a[i] = a[i - 1];   // shift right
            } else {
                break;
            }
        }

        a[i] = x; // here due to i-- the above loop will break and a[i--] = x
        return n + 1;
    }

    static void quickSort(int[] a, int start, int end) {
        if (start >= end) return; // base condition
        int pi = partition(a, start, end);
        quickSort(a, start, pi - 1);
        quickSort(a, pi + 1, end);
    }

    private static int partition(int[] a, int start, int end) {
        int pe = a[end];
        int i = start - 1;
        for (int j = start; j < end; j++) {
            if (a[j] < pe) {
                i++;
                swap1(a, i, j);
            }
        }
        swap1(a, i + 1, end);
        return i + 1;
    }

    private static void swap1(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
