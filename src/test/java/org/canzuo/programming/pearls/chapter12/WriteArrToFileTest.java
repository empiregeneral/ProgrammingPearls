package org.canzuo.programming.pearls.chapter12;


import junitparams.JUnitParamsRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

@RunWith(JUnitParamsRunner.class)
public class WriteArrToFileTest {

    private final int amount = 10000000;
    private final int start = 10000000;

    private final int end = 99999999;

    private int[] context = Sampling.genKnuth(amount, start, end);

    @Test
    public void samplingTest() {
        Assert.assertEquals(amount, context.length);
    }

    @Test
    public void writeArrToFileTest() {
        Sampling.shuffle(context);
        System.out.println(Arrays.toString(context));
        WriteArrToFile.write("E:\\Code\\pearls\\src\\main\\java\\org\\canzuo\\programming\\pearls\\chapter1\\test.in", context);
    }
}
