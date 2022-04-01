package com.shpakovskiy.imagestream.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

public class WeirdLogger {
    public static void info(String message) {
        String logMessage = "[INFO][" + new Date() + "] " + message + "\n";
        System.out.print(logMessage);
        writeToFile(logMessage);
    }

    public static void error(String message) {
        String logMessage = "[ERROR][" + new Date() + "] " + message + "\n";
        System.err.print(logMessage);
        writeToFile(logMessage);
    }

    private static void writeToFile(String message) {
        String logFileName = "logs.log";
        File logFile = new File(logFileName);

        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            Files.write(
                    Paths.get(logFileName),
                    message.getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}