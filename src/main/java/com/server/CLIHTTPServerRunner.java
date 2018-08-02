package com.server;

import com.server.loggers.ILogger;
import com.server.loggers.Logger;

public class CLIHTTPServerRunner {

    public static void main(String[] args) throws Exception {

        CLIFlagParser cliFlagParser = new CLIFlagParser();

        int portNumber = cliFlagParser.parse(args);

        ILogger logger = new Logger("logs.txt");

        HTTPServer HTTPServer = new HTTPServer(logger, portNumber);

        HTTPServer.runServer();
    }
}
