import basics.*;
import basics.algos.BinarySearch;
import basics.algos.Sorting;
import kotlin.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Anagram obj = new Anagram();
        boolean result = obj.isAnagram("mug", "gum");
        System.out.println("is anagram " + result);

        Armstrong obj1 = new Armstrong();
        boolean result1 = obj1.isArmstrong(153);
        System.out.println("is armstrong " + result1);

        PrimeNumber obj2 = new PrimeNumber();
        boolean result2 = obj2.isPrime(10);
        System.out.println("is Prime number " + result2);
        Fibonacci obj3 = new Fibonacci();
        obj3.getFibonacci(7);
        System.out.println();
        DuplicateInArray obj4 = new DuplicateInArray();
        Integer[] a = {1, 2, 10, 2, 10, 5, 7, 9, 5};
        Integer[] duplicates = obj4.usingHashSet(a);
        System.out.println("Duplicates are :");
        for (Integer i : duplicates) {
            System.out.print(i + " ");
        }
        System.out.println();
        ArrayList<Integer> duplicates1 = obj4.usingNestedLoops(a);
        System.out.println("Duplicates are :");
        for (Integer i : duplicates1) {
            System.out.print(i + " ");
        }
        System.out.println();
        ArrayList<Integer> duplicates2 = obj4.usingSorting(a);
        System.out.println("Duplicates are :");
        for (Integer i : duplicates2) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("Common elements in two arrays are :");
        Integer[] input1 = new Integer[]{1, 2, 3, 4, 5};
        Integer[] input2 = {1, 2, 4, 3};
        CommonInTwoArrays obj5 = new CommonInTwoArrays();
        ArrayList<Integer> commons = obj5.usingHashSet(input1, input2);
        for (Integer i : commons) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("Common elements in two arrays are :");
        ArrayList<Integer> commons1 = obj5.usingNestedLoops(input1, input2);
        for (Integer i : commons1) {
            System.out.print(i + " ");
        }

        System.out.println();
        System.out.println("Common elements in two arrays are :");
        ArrayList<Integer> commons2 = obj5.usingSorting(input1, input2);
        for (Integer i : commons2) {
            System.out.print(i + " ");
        }
        System.out.println();
        String str1 = "This is sentence1";
        String str2 = "This is sentence2";
        DuplicatesInSentences obj6 = new DuplicatesInSentences();
        System.out.println("Duplicate words are: ");
        ArrayList<String> duplicateWords = obj6.usingNestedLoops(str1, str2);
        for (String s : duplicateWords) {
            System.out.print(s + " ");
        }
        System.out.println();
        System.out.println("Duplicate words are: ");
        ArrayList<String> duplicateWords1 = obj6.usingHashSet(str1, str2);
        for (String s : duplicateWords1) {
            System.out.print(s + " ");
        }
        System.out.println();

        Reverse obj7 = new Reverse();
        Integer resultRev = obj7.reverseNumber(123);
        System.out.println("Reversed number is: " + resultRev);
        System.out.println();
        String input = "bub";
        System.out.println(input + " is palindrome :" + obj7.palindrome(input));

        int[] arrToReverse = {1, 2, 3, 4, 5};
        System.out.println("Reversed array is");
        int[] reversedArray1 = obj7.reverseArrayUsingLoop(arrToReverse);

        for (int i : reversedArray1) {
            System.out.print(i + " ");
        }
        int[] reversedArray2 = obj7.reverseArrayUsingSwap(arrToReverse);
        System.out.println();
        for (int i : reversedArray2) {
            System.out.print(i + " ");
        }

        System.out.println();

        String reversedSent = obj7.reverseSentence("Hi I am Akash");
        System.out.println(reversedSent);

        System.out.println();
        NoOfChars obj8 = new NoOfChars();
        HashMap<Character, Integer> hashMapWithNoOfChars = obj8.findCountOfCharsInString("Akash");
        hashMapWithNoOfChars.forEach((k, v) -> System.out.print(k + "=" + v + " "));

        System.out.println();
        System.out.println("Top 3 max values");
        MaxMin obj9 = new MaxMin();
        int[] arrToFindMaxMin = {500, 21, 900, 300, 55, 9};
        int[] top3MaxValue = obj9.getTop3Max(arrToFindMaxMin);
        for (int i : top3MaxValue) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("Top 3 min values");
        int[] top3MinValue = obj9.getTop3Min(arrToFindMaxMin);
        for (int i : top3MinValue) {
            System.out.print(i + " ");
        }

        System.out.println();
        System.out.println("Find max min in single iteration");
        Pair<Integer, Integer> p = obj9.getMaxMin(arrToFindMaxMin);
        System.out.print(p.getFirst() + " " + p.getSecond());

        System.out.println();
        System.out.println("Sort zero to end");
        Sort obj10 = new Sort();
        int[] arrToSort = {500, 21,0, 900, 300, 55, 9,0};
        int[] sortedArrayWithZero = obj10.sortZeros(arrToSort);
        for (int i:sortedArrayWithZero){
            System.out.print(i + " ");
        }

        System.out.println();
        System.out.println("Binary Search:");
        int[] binaryArray = {10,20,63,105,500};
        BinarySearch binarySearch = new BinarySearch();
        int  targetIndexBinary = binarySearch.binarySearch(binaryArray,15);
        if (targetIndexBinary==-1){
            System.out.println("Not found in binary search");
        }else {
            System.out.println("Index of the target in binary search is :"+targetIndexBinary);
        }

        System.out.println();
        System.out.println("Binary Search:");
        int[] bubbleArraySort = {103,200,63,105,50};
        Sorting sorting = new Sorting();
        int [] bubbleSortedArray = sorting.bubbleSort(bubbleArraySort);
        for (int i:bubbleSortedArray){
            System.out.print(i + " ");
        }

    }
}
