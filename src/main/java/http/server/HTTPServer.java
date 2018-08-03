package http.server;

import http.application.Application;
import http.server.loggers.ILogger;
import http.server.loggers.NullLogger;

import java.net.*;
import java.io.*;

public class HTTPServer {

    private ServerConfig serverConfig;
    private ILogger logger;

    public HTTPServer(ILogger logger, int port){
        this.serverConfig = new ServerConfig(port);
        this.logger = logger;
    }

    public HTTPServer(int port){
        this.serverConfig = new ServerConfig(port);
        this.logger = new NullLogger();
    }

    public void runServer(Application application) throws IOException {
        ServerSocket serverSocket = new ServerSocket(serverConfig.getPortNumber());
        while(true) {
            Socket clientSocket = serverSocket.accept();
            ClientWorker clientWorker = new ClientWorker(application, clientSocket, logger);
            Thread thread = new Thread(clientWorker);
            thread.start();
        }
    }
}
