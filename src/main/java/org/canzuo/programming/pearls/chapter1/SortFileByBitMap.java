package org.canzuo.programming.pearls.chapter1;

import java.io.*;
import java.util.BitSet;
import java.util.Scanner;

public class SortFileByBitMap {

    private final static int amount = 10000000;

    public static void main(String[] args) throws FileNotFoundException {
        long start = System.currentTimeMillis();
        BitSet bitSet = new BitSet(amount / 32 + 1);

        Scanner input = new Scanner(new BufferedInputStream(System.in));
        while(input.hasNext()) {
            bitSet.set(input.nextInt());
        }
        input.close();

        try {
            PrintWriter out = new PrintWriter(new FileOutputStream("E:\\Code\\pearls\\src\\main\\java\\org\\canzuo\\programming\\pearls\\chapter1\\test.out"));
            for (int i = 0; i < bitSet.size(); i++) {
                if (bitSet.get(i)) {
                    out.println(i);
                }
            }
            long expandTime = System.currentTimeMillis() - start;
            out.println(" \n Cost Time: " +expandTime + " ms");
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
