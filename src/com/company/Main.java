package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println(reverseParentheses("abc(de(123)fgh)ijk"));
    }
//fghfgh
    static String reverseParentheses(String s) {
        String[] originalString = s.split("");
        String finalString = "";
        List<String> StringAsArrayList = new ArrayList<>(Arrays.asList(originalString));
        int leftMarkOfParenthes = -1;
        int rightMarkOfParenthes = -1;
        boolean iNeedToStop = false;
//jljiu
        do {
            leftMarkOfParenthes = getLeftMarkOfParenthes(StringAsArrayList, leftMarkOfParenthes);
            iNeedToStop = isiNeedToStop(leftMarkOfParenthes, iNeedToStop);
            rightMarkOfParenthes = getRightMarkParenthes(StringAsArrayList, leftMarkOfParenthes, rightMarkOfParenthes);
            StringAsArrayList = changingLettersInWord(StringAsArrayList, leftMarkOfParenthes, rightMarkOfParenthes);
            leftMarkOfParenthes = -1;
            rightMarkOfParenthes = -1;
        }
        while (!iNeedToStop);

        finalString = changingListToString(finalString, StringAsArrayList);
        return finalString;
    }

    private static String changingListToString(String finalString, List<String> stringAsArrayList) {
        for (int i = 0; i < stringAsArrayList.size(); i++) {
            finalString += stringAsArrayList.get(i);
        }
        return finalString;
    }

    private static List<String> changingLettersInWord(List<String> originalStringAsArrayList, int leftMarkOfParenthes, int rightMarkParenthes) {
        String helper = "";
        if (leftMarkOfParenthes != -1) {
            for (int m = 1; m < (rightMarkParenthes - leftMarkOfParenthes) / 2 + 1; m++) {
                helper = originalStringAsArrayList.get(leftMarkOfParenthes + m);
                originalStringAsArrayList.set(leftMarkOfParenthes + m, originalStringAsArrayList.get(rightMarkParenthes - m));
                originalStringAsArrayList.set(rightMarkParenthes - m, helper);
            }
            originalStringAsArrayList.remove(leftMarkOfParenthes);
            originalStringAsArrayList.remove(rightMarkParenthes - 1);
        }
        return originalStringAsArrayList;
    }

    private static int getRightMarkParenthes(List<String> originalStringAsArrayList, int leftMarkOfParenthes, int rightMarkParenthes) {
        for (int i = originalStringAsArrayList.size() - 1; i > leftMarkOfParenthes + 1; i--) {
            if (originalStringAsArrayList.get(i).equals(")")) {
                rightMarkParenthes = i;
            }
        }
        return rightMarkParenthes;
    }

    private static boolean isiNeedToStop(int leftMarkOfParenthes, boolean iNeedToStop) {
        if (leftMarkOfParenthes == -1) {
            iNeedToStop = true;
        }
        return iNeedToStop;
    }

    private static int getLeftMarkOfParenthes(List<String> originalStringAsArrayList, int leftMarkOfParenthes) {
        for (int x = 0; x < originalStringAsArrayList.size(); x++) {
            if (originalStringAsArrayList.get(x).equals("(")) {
                leftMarkOfParenthes = x;
            }
        }
        return leftMarkOfParenthes;
    }
}
