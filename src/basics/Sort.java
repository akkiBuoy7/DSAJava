package basics;

public class Sort {

    public int[] sortZeros(int[] input) {
        /*
        j keeps track of where non zero element shd be put
        swap jth element with ith element as i holds non zero
         */
        int j = 0;
        for (int i = 0; i < input.length; i++) {
            if (input[i] != 0) {
                int temp = input[j];
                input[j] = input[i];
                input[i] = temp;
                j++;
            }
        }

        return input;
    }
}
