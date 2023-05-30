package org.canzuo.programming.pearls.chapter1;

import org.canzuo.programming.pearls.chapter12.ReadArrFromFile;

import java.util.Arrays;

public class SortFileByQuickSort {

    private final static int amount = 10000000;
    private static int[] context = new int[amount];

    public static void sortedArray(int[] context) {
        Arrays.sort(context);
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        ReadArrFromFile.readFile("E:\\Code\\pearls\\src\\main\\java\\org\\canzuo\\programming\\pearls\\chapter1\\test.in", context);

        sortedArray(context);

        long expandTime = System.currentTimeMillis() - start;
        System.out.println(" \n Cost Time: " +expandTime + " ms");
    }
}
