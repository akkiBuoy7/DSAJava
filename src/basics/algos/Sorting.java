package basics.algos;

public class Sorting {
/*
Best Case Time Complexity: O(n) (with optimized algorithm to detect no swaps)
Average Case Time Complexity: O(n²)
Worst Case Time Complexity: O(n²)
Space Complexity: O(1)
Thus, Bubble Sort is efficient in the best case (if the array is already sorted)
but inefficient in the average and worst cases due to its O(n²) time complexity.
It is not the best algorithm for large datasets because of this quadratic growth.
*/
    public int[] bubbleSort(int[] input) {

        for (int i = 0; i < input.length - 1; i++) {
            for (int j = 0; j < input.length - i - 1; j++) {
                if (input[j] > input[j + 1]) {
                    int temp = input[j];
                    input[j] = input[j + 1];
                    input[j + 1] = temp;
                }
            }
        }
        return input;
    }
}
