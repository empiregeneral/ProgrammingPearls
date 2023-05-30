package org.canzuo.programming.pearls.chapter1;

import junitparams.JUnitParamsRunner;
import org.canzuo.programming.pearls.chapter12.ReadArrFromFile;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

import static org.canzuo.programming.pearls.chapter1.SortFileByQuickSort.sortedArray;

@RunWith(JUnitParamsRunner.class)
public class SortFileByQuickSortTest {

    private final int amount = 10000000;

    private int[] context = new int[amount];

    @Test
    public void sortTest() {
        ReadArrFromFile.readFile("E:\\Code\\pearls\\src\\main\\java\\org\\canzuo\\programming\\pearls\\chapter1\\test.in", context);
        long start = System.currentTimeMillis();
        sortedArray(context);
        long expandTime = System.currentTimeMillis() - start;
        System.out.println(Arrays.toString(context) + " \n Cost Time: " +expandTime + " ms");
    }
}
