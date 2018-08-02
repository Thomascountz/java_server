package http.server;

import http.server.applications.IApplication;
import http.server.loggers.ILogger;

import java.io.*;
import java.net.Socket;

public class ClientWorker implements Runnable {

    private Socket clientSocket;
    private ILogger logger;
    private IApplication application;

    ClientWorker(IApplication application,
                 Socket clientSocket,
                 ILogger logger){
        this.clientSocket = clientSocket;
        this.logger = logger;
        this.application = application;
    }

    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            RequestReader requestReader = new RequestReader(bufferedReader);
            String requestString = requestReader.getRequest();

            logger.log(getFirstLine(requestString));

            RequestParser requestParser = new RequestParser(requestString);
            RequestParams requestParams = requestParser.getRequestParams();

            byte[] response = application.apply(requestParams);

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
