package basics;

import kotlin.Pair;

public class MaxMin {
    public int[] getTop3Max(int[] arr) {
        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        int third = Integer.MIN_VALUE;
        int[] result = new int[3];

        for (int v : arr) {
            if (v > first) {
                third = second;
                second = first;
                first = v;
            } else if (v > second && v != first) {
                third = second;
                second = v;
            } else if (v > third && v != second && v != first) {
                third = v;
            }
        }
        result[0] = first;
        result[1] = second;
        result[2] = third;
        return result;
    }

    public int[] getTop3Min(int [] arr){
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;
        int third = Integer.MAX_VALUE;
        int[] result = new int[3];


        for (int v : arr) {
            if (v < first) {
                third = second;
                second = first;
                first = v;
            } else if (v < second && v != first) {
                third = second;
                second = v;
            } else if (v < third && v != second && v != first) {
                third = v;
            }
        }
        result[0] = first;
        result[1] = second;
        result[2] = third;
        return result;

    }

    public Pair<Integer, Integer> getMaxMin(int [] arr){
        int max= Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for(int d:arr){
            if (d<min){
                min = d;
            }else if (d>max){
                max = d;
            }
        }
        return new Pair<>(max,min);
    }
}
