package org.canzuo.programming.pearls.chapter12;

import java.util.Arrays;
import java.util.Random;

public class Sampling {

    public static int[] genKnuth(final int amount, int start, int end) {
        if (start > end) {
            int t = start;
            start = end;
            end = t;
        }

        int select = amount;
        int remaining = end - start;
        int[] result = new int[amount];
        Random rand = new Random();
        for (int i = end; i >= start; i--) {
            int randInt = rand.nextInt(remaining);
            if ( randInt < select ) {
                result[--select] = i;
            }
            remaining--;
            if (remaining == 0) {
                break;
            }
        }

        return result;
    }

    public static int[] shuffle(final int[] context) {
        Random rand = new Random();
        for ( int i = 0; i < context.length / 2 + 1; i++) {
            int j = rand.nextInt(context.length - i - 1) + i;
            int t = context[i];
            context[i] = context[j];
            context[j] = t;
        }
        return context;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(shuffle(genKnuth(10000000, 10000000, 99999999))));
    }
}
