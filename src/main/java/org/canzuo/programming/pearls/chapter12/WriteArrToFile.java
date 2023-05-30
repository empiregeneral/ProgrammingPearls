package org.canzuo.programming.pearls.chapter12;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteArrToFile {

    public static void write(final String path, final int[] context) {
        File file = new File(path);
        if ( !createFile(file)) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int e : context) {
            sb.append(e + "\n");
        }

        try {
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(sb.toString());
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean createFile(File file) {
        if ( !file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        if ( !file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }
}
