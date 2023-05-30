package org.canzuo.programming.pearls.chapter1;

import java.util.Arrays;


public class Solution9 {

    private final static int amount = 1000;
    private static int[] data = new int[amount];
    private static int[] from = new int[amount];
    private static int[] to = new int[amount];

    public static void main(String[] args) {
        createData(data);
        int top = 0;

    }

    /**
     * @description: 1000个数组只有3个数已经初始化了
     * @param data
     */
    private static void createData(int[] data) {
        Arrays.fill(data, 0);
        data[1] = 3;
        data[3] = 2;
        data[5] = 8;
    }


}
