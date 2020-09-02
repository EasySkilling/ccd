package com.lloydfinch.lib_ccd.lib_tools.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Name: FileTool
 * Author: lloydfinch
 * Function: FileTool
 * Date: 2020-09-02 11:32
 * Modify: lloydfinch 2020-09-02 11:32
 */
public class FileTool {
    public static String getDatafromInputStream(InputStream input) {
        String result = null;
        try {
            Scanner scanner = new Scanner(input, "UTF-8").useDelimiter("\\A");
            if (scanner.hasNext()) {
                result = scanner.next();
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
