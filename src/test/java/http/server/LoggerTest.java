package http.server;

import http.server.loggers.Logger;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;

import static org.junit.Assert.*;

public class LoggerTest {

    @Test
    public void LoggerCreatesNewLogsFileIfOneDoesntExist() throws IOException {
        String filePath = "src/test/resources/logger-create-newfile/logs.txt";
        File existingFile = new File(filePath);
        Files.deleteIfExists(existingFile.toPath());
        String dataToRecord = "test record";

        Logger logger = new Logger(filePath);
        logger.log(dataToRecord);

        File newFile = new File(filePath);
        assertTrue(newFile.exists());

        PrintWriter newWriter = new PrintWriter(newFile);
        newWriter.print("");
        newWriter.close();
    }

    @Test
    public void LoggerAppendsTextToExistingLogFile() throws IOException {
        String filePath = "src/test/resources/logger-append-data/logs.txt";
        File existingFile = new File(filePath);
        Files.deleteIfExists(existingFile.toPath());

        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
        writer.append("test record");
        writer.append("\r\n");
        writer.close();

        String dataToRecord = "new record";

        Logger logger = new Logger(filePath);
        logger.log(dataToRecord);

        File logFile = new File(filePath);
        String content = new String(Files.readAllBytes(logFile.toPath()));

        assertTrue(content.contains("test record"));
        assertTrue(content.contains("new record"));

        PrintWriter newWriter = new PrintWriter(logFile);
        newWriter.print("");
        newWriter.close();
    }

}
