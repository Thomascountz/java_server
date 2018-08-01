package com.server;

public class HTTPServer {

    public static void main(String[] args) throws Exception {

        CLIFlagParser cliFlagParser = new CLIFlagParser();

        ServerConfig serverConfig = cliFlagParser.parse(args);

        HTTPServerManager HTTPServerManager = new HTTPServerManager(serverConfig,true);

        HTTPServerManager.runServer();
    }
}
