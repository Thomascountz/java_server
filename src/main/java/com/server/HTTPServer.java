package com.server;

import com.server.loggers.ILogger;
import com.server.loggers.NullLogger;

import java.net.*;
import java.io.*;

public class HTTPServer {

    private ServerConfig serverConfig;
    private ILogger logger;

    HTTPServer(ILogger logger, int port){
        this.serverConfig = new ServerConfig(port);
        this.logger = logger;
    }

    HTTPServer(int port){
        this.serverConfig = new ServerConfig(port);
        this.logger = new NullLogger();
    }

    public void runServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(serverConfig.getPortNumber());
        while(true) {
            Socket clientSocket = serverSocket.accept();
            ClientWorker clientWorker = new ClientWorker(clientSocket, logger);
            Thread thread = new Thread(clientWorker);
            thread.start();
        }
    }
}
