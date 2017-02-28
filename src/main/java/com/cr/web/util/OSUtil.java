package com.cr.web.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class OSUtil {

    public static String exeCmd(String cmdStr) throws Exception {
        StringBuilder sb = new StringBuilder();
        Process pr = Runtime.getRuntime().exec(cmdStr);
        BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream(), "GBK"));
        String line = null;
        while ((line = input.readLine()) != null) {
            sb.append(line);
        }
        int exitVal = pr.waitFor();
        if (exitVal != 0) {
            sb.append("Exited with error code " + exitVal);
        }
        return sb.toString();
    }

    public static boolean isWindows() {
        if (System.getProperties().getProperty("os.name").contains("Windows")) {
            return true;
        }
        return false;
    }

}
