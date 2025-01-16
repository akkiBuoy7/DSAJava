package basics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class CommonInTwoArrays {

    public ArrayList<Integer> usingHashSet(Integer[] input1, Integer[] input2) {
        ArrayList<Integer> result = new ArrayList<>();
        HashSet<Integer> hashSet = new HashSet<>(Arrays.asList(input1));

        for (Integer integer : input2) {
            if (hashSet.contains(integer)) {
                result.add(integer);
            }
        }
        return result;
    }

    public ArrayList<Integer> usingNestedLoops(Integer[] input1, Integer[] input2) {
        ArrayList<Integer> result = new ArrayList<>();
        for (Integer integer : input1) {
            for (Integer value : input2) {
                if (integer == value) {
                    result.add(integer);
                }
            }
        }
        return result;
    }

    public ArrayList<Integer> usingSorting(Integer[] input1, Integer[] input2) {
        ArrayList<Integer> result = new ArrayList<>();
        Arrays.sort(input1);
        Arrays.sort(input2);
        int i = 0;
        int j = 0;

        while (i < input1.length && j < input2.length) {
            if (input1[i] == input2[j]) {
                result.add(input1[i]);
                i++;
                j++;
            } else if (input1[i] < input2[j]) {
                i++;
            } else {
                j++;
            }
        }
        return result;

    }
}
