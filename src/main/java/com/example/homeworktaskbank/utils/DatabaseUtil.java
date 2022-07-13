package com.example.homeworktaskbank.utils;

import java.io.IOException;

public final class DatabaseUtil {
    public static boolean backup(String dbUsername, String dbPassword, String dbName, String outputFile)
            throws IOException, InterruptedException {
        String command = String.format("mysqldump -u%s -p%s --add-drop-table --databases %s -r %s",
                dbUsername, dbPassword, dbName, outputFile);
        Process process = Runtime.getRuntime().exec(command);
        int processComplete = process.waitFor();
       // System.out.println("I might be a backUp...");
        return processComplete == 0;
    }
}
