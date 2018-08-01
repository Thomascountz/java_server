package com.server;

import java.net.*;
import java.io.*;

public class HTTPServerManager {

    private ServerConfig serverConfig;
    private boolean logRequests;

    HTTPServerManager(ServerConfig serverConfig, boolean logRequests){
        this.serverConfig = serverConfig;
        this.logRequests = logRequests;
    }

    public void runServer() throws IOException {
        ServerSocket serverSocket = createServerSocket();
        while(running()) {
            Socket clientSocket = openSocket(serverSocket);
            ClientWorker clientWorker = new ClientWorker(
                    clientSocket,
                    serverConfig,
                    logRequests
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
