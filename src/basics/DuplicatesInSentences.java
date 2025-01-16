package basics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class DuplicatesInSentences {

    public ArrayList<String> usingNestedLoops(String sent1, String sent2) {
        ArrayList<String> duplicates = new ArrayList<>();

        String[] words1 = sent1.split(" ");
        String[] words2 = sent2.split(" ");

        for (String word1 : words1) {
            for (String word2 : words2) {
                if (word1.equals(word2) && !duplicates.contains(word1)) {
                    duplicates.add(word1);
                }
            }
        }
        if (duplicates.isEmpty()){
            System.out.println("No duplicates");
        }
        return duplicates;
    }

    public ArrayList<String> usingHashSet(String sent1, String sent2) {
        ArrayList<String> duplicates = new ArrayList<>();
        String[] words1 = sent1.split(" ");
        String[] words2 = sent2.split(" ");

        HashSet<String> h1 = new HashSet<>();
        HashSet<String> h2 = new HashSet<>();

        h1.addAll(Arrays.asList(words1));

        h2.addAll(Arrays.asList(words2));

        h1.retainAll(h2);

        if (!h1.isEmpty()) {
            duplicates.addAll(h1);

        } else {
            System.out.println("No duplicates");
        }
        return duplicates;
    }
}
