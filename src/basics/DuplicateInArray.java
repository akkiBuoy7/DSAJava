package basics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class DuplicateInArray {
    public Integer[] usingHashSet(Integer[] input) {
        HashSet<Integer> seen = new HashSet<>();
        HashSet<Integer> duplicates = new HashSet<>();

        for (int num : input) {
            if (!seen.add(num)) {
                duplicates.add(num);
            }
        }
        Integer[] result = duplicates.toArray(new Integer[0]);
        if (duplicates.isEmpty()) {
            System.out.println("No duplicates found");
        }
        return result;
    }

    public ArrayList<Integer> usingNestedLoops(Integer[] input) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            for (int j = i + 1; j < input.length; j++) {
                if (input[i] == input[j]) {
                    result.add(input[i]);
                    break;
                }
            }
        }
        return result;
    }

    public ArrayList<Integer> usingSorting(Integer[] input) {
        ArrayList<Integer> result = new ArrayList<>();
        Arrays.sort(input);
        for (int i = 1; i < input.length; i++) {
            if (input[i] == input[i - 1]) {
                result.add(input[i]);
            }
        }
        return result;
    }
}
