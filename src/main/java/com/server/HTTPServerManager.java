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
        ServerSocket serverSocket = createServerSocket();
        while(running()) {
            Socket clientSocket = openSocket(serverSocket);
            ClientWorker clientWorker = new ClientWorker(
                    clientSocket,
                    serverConfig,
                    logger
            );
            Thread thread = new Thread(clientWorker);
            thread.start();
        }
    }

    private Boolean running(){
        return true;
    }

    private ServerSocket createServerSocket() throws IOException {
        return new ServerSocket(serverConfig.getPortNumber());
    }

    private Socket openSocket(ServerSocket serverSocket) throws IOException {
        return serverSocket.accept();
    }
}
