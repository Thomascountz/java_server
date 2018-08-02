package com.server;

import com.server.loggers.ILogger;
import com.server.loggers.Logger;

public class HTTPServer {

    public static void main(String[] args) throws Exception {

        CLIFlagParser cliFlagParser = new CLIFlagParser();

        ServerConfig serverConfig = cliFlagParser.parse(args);

        ILogger logger = new Logger("logs.txt");

        HTTPServerManager HTTPServerManager = new HTTPServerManager(serverConfig, logger);

        HTTPServerManager.runServer();
    }
}
