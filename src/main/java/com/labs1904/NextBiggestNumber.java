package com.labs1904;

// import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NextBiggestNumber {

    public static void main(String[] args) {
        Integer input = Integer.parseInt(args[0]);
        int nextBiggestNumber = getNextBiggestNumber(input);
        System.out.println("Input: " + input);
        System.out.println("Next biggest number: " + nextBiggestNumber);
    }

    private static List<Integer> getIntListFromInt(Integer i) {
        List<Integer> intList = new ArrayList<Integer>();

        do {
            intList.add(0, i % 10);
            i /= 10;
        } while (i > 0);

        return intList;
    }

    private static int getIntFromIntList(List<Integer> intList) {
        int total = 0;

        for (int i : intList) {
            total = 10 * total + i;
        }

        return total;
    }

    private static int getNextDigitSmallerIx(List<Integer> intList, int size) {
        int i;

        for (i = size - 1; i> 0; i--) {
            if (intList.get(i) > intList.get(i - 1)) {
                break;
            }
        }

        return i;
    }

    private static int getMinIndex(List<Integer> intList, int nextDigitSmallerIx, int size) {
        int firstDigitSmaller = intList.get(nextDigitSmallerIx - 1);

        int minIx = nextDigitSmallerIx;

        int i;

        for (i = nextDigitSmallerIx + 1; i < size; i++) {
            if (intList.get(i) > firstDigitSmaller && intList.get(i) < intList.get(minIx)) {
                minIx = i;
            }
        }

        return minIx;
    }

    private static List<Integer> getSortedIntList(List<Integer> intList, int minIx, int nextDigitSmallerIx, int size) {
        Collections.swap(intList, minIx, nextDigitSmallerIx - 1);

        Collections.sort(intList.subList(nextDigitSmallerIx, size));

        return intList;
    }

    public static int getNextBiggestNumber(Integer i) {
        List<Integer> intList = getIntListFromInt(i);

        int size = intList.size();

        int nextDigitSmallerIx = getNextDigitSmallerIx(intList, size);

        if (nextDigitSmallerIx == 0) return -1;

        int minIx = getMinIndex(intList, nextDigitSmallerIx, size);

        List<Integer> sortedIntList = getSortedIntList(intList, minIx, nextDigitSmallerIx, size);

        return getIntFromIntList(sortedIntList);
    }
}
