package basics;

import java.util.ArrayList;

public class Reverse {

    public Integer reverseNumber(Integer num) {
        Integer result = 0;

        if (String.valueOf(num).length() == 1) {
            return num;
        }
        while (num > 0) {
            Integer rem = num % 10;
            result = result * 10 + rem;
            num = num / 10;
        }
        return result;
    }


    public boolean palindrome(String input) {
        StringBuilder reverse = new StringBuilder();
        for (int i = input.length() - 1; i >= 0; i--) {
            reverse.append(input.charAt(i));
        }
        return reverse.toString().equals(input);
    }

    public int[] reverseArrayUsingSwap(int[] arr) {
        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
        return arr;
    }

    public int[] reverseArrayUsingLoop(int[] arr) {
        int j = 0;
        int[] result = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            result[j] = arr[i];
            j++;
        }
        return result;
    }

    public String reverseSentence(String input) {
        ArrayList<String> result = new ArrayList<>();
        String[] arr = input.split(" ");

        for (int i = arr.length - 1; i >= 0; i--) {
            result.add(arr[i]);
        }

        String rev = "";
        for (String s : result) {
            rev += s + " ";
        }

        return rev;
    }
}
