package basics.algos;

public class BinarySearch {

/*
Time Complexity:
Best case: O(1) (when the target is at the middle).
Worst case: O(log n) (when the search space is halved each time).
*/

    public int binarySearch(int[] array, int target) {

        int left = 0;
        int right = array.length - 1;

        while (left <= right) {

            int middle = left + (right - left) / 2;

            if (array[middle] == target) {
                return middle;
            } else if (array[middle] < target) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }

        }

        return -1;
    }
}
