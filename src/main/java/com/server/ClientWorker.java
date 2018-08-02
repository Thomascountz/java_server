package com.server;

import com.server.loggers.ILogger;

import java.io.*;
import java.net.Socket;

public class ClientWorker implements Runnable {

    private Socket clientSocket;
    private ServerConfig serverConfig;
    private ILogger logger;

    ClientWorker(Socket clientSocket,
                 ServerConfig serverConfig,
                 ILogger logger){
        this.clientSocket = clientSocket;
        this.serverConfig = serverConfig;
        this.logger = logger;
    }

    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            RequestReader requestReader = new RequestReader(bufferedReader);
            String requestString = requestReader.getRequest();

            logger.log(getFirstLine(requestString));

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
