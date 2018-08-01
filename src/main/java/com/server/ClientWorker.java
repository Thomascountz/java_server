package com.server;

import java.io.*;
import java.net.Socket;

public class ClientWorker implements Runnable {

    private Socket clientSocket;
    private ServerConfig serverConfig;
    private boolean logsRequests;

    ClientWorker(Socket clientSocket,
                      ServerConfig serverConfig,
                      boolean logsRequests){
        this.clientSocket = clientSocket;
        this.serverConfig = serverConfig;
        this.logsRequests = logsRequests;
    }

    ClientWorker(Socket clientSocket,
                 ServerConfig serverConfig){
        this.clientSocket = clientSocket;
        this.serverConfig = serverConfig;
        this.logsRequests = false;
    }


    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            RequestReader requestReader = new RequestReader(bufferedReader);
            String requestString = requestReader.getRequest();

            if (this.logsRequests) {
                Logger logger = new Logger("logs.txt");
                logger.log(getFirstLine(requestString));
            }

            RequestParser requestParser = new RequestParser(requestString);
            RequestParams requestParams = requestParser.getRequestParams();

            byte[] response = "HTTP/1.1 200 OK\r\n\r\nHello, World!".getBytes();

            OutputStream outputStream = clientSocket.getOutputStream();
            outputStream.write(response);
            outputStream.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String getFirstLine(String request) {
        return request.split("\r\n")[0].trim();
    }

}
