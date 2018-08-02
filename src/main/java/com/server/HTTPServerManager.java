package com.server;

import com.server.loggers.ILogger;
import com.server.loggers.NullLogger;

import java.net.*;
import java.io.*;

public class HTTPServerManager {

    private ServerConfig serverConfig;
    private ILogger logger;

    HTTPServerManager(ServerConfig serverConfig, ILogger logger){
        this.serverConfig = serverConfig;
        this.logger = logger;
    }

    HTTPServerManager(ServerConfig serverConfig){
        this.serverConfig = serverConfig;
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
