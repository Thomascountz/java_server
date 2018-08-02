package http.server;

import http.server.applications.DefaultApplication;
import http.server.loggers.ILogger;
import http.server.loggers.Logger;

public class HTTPServerRunner {

    public static void main(String[] args) throws Exception {

        CLIFlagParser cliFlagParser = new CLIFlagParser();

        int portNumber = cliFlagParser.parse(args);

        ILogger logger = new Logger("logs.txt");

        HTTPServer HTTPServer = new HTTPServer(logger, portNumber);

        DefaultApplication defaultApplication = new DefaultApplication();

        HTTPServer.runServer(defaultApplication);
    }
}
