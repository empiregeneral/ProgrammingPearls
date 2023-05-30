package org.canzuo.programming.pearls.chapter12;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadArrFromFile {
    public static void readFile(final String path, int[] context) {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader in = new BufferedReader(fileReader);
            String s;
            int index = 0;
            while((s = in.readLine()) != null) {
                context[index++] = Integer.parseInt(s);
            }

            in.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
