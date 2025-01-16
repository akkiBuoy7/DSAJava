package basics;

import java.util.HashMap;
import java.util.Locale;

public class NoOfChars {

    public HashMap<Character, Integer> findCountOfCharsInString(String input) {
        input = input.toLowerCase(Locale.ROOT); // Convert input to lowercase
        HashMap<Character, Integer> hashMap = new HashMap<>();

        for (char c : input.toCharArray()) {  // Loop over the characters in the string
            if (Character.isLetter(c)) {  // Optionally, you can count only alphabetic characters
                hashMap.put(c, hashMap.getOrDefault(c, 0) + 1);
            }
        }

        return hashMap;
    }
}