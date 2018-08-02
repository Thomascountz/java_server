package http.server.loggers;

public class NullLogger implements ILogger {
    @Override
    public void log(String request) {
        // null
    }
}
