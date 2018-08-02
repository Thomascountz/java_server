package http.server.loggers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;

public class Logger implements ILogger {
    private String logFilePath;

    public Logger(String filePath) {
        this.logFilePath = filePath;
    }

    @Override
    public void log(String request) {
        BufferedWriter writer = null;
        try {
            String timeStamp = Instant.now().toString();
            writer = new BufferedWriter(new FileWriter(this.logFilePath, true));
            writer.append(timeStamp);
            writer.append(" -- ");
            writer.append(request);
            writer.append("\r\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
