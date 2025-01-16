package basics;

import java.util.Arrays;

public class Anagram {
    public boolean isAnagram(String s1,String s2){
        if(s1.length()!=s2.length()){
            return false;
        }
        char[] arrayS1 = s1.toCharArray();
        char[] arrayS2 = s2.toCharArray();

        Arrays.sort(arrayS1);
        Arrays.sort(arrayS2);
        return Arrays.equals(arrayS1, arrayS2);
    }
}
