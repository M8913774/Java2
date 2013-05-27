package com.suhorukov.gubin.server;

import java.io.*;

public class FileSystemReader {

    public static String readTextFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        Reader r = new InputStreamReader(new BufferedInputStream(fileInputStream));
        int symbol;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            while ((symbol = r.read()) != -1) {
                stringBuilder.append((char) symbol);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally { try {
            r.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        }
        return stringBuilder.toString();
    }


}
