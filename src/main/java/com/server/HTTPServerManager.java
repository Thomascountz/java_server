package com.server;

import java.net.*;
import java.io.*;

public class HTTPServerManager {

    private ServerConfig serverConfig;
    private RequestRouter requestRouter;

    HTTPServerManager(ServerConfig serverConfig, RequestRouter requestRouter){
        this.serverConfig = serverConfig;
        this.requestRouter = requestRouter;
    }

    public void runServer() throws IOException {

        while(running()) {

            ServerSocket serverSocket = createServerSocket();
            Socket clientSocket = openSocket(serverSocket);

            BufferedReader in = openInputStream(clientSocket);

            RequestReader requestHeaderReader = new RequestReader(in);
            RequestParser requestParser = new RequestParser(requestHeaderReader.getRequest(), serverConfig.getDirectory());

            RequestParams requestParams = requestParser.getRequestParams();

            ResponseBuilder responseBuilder = new ResponseBuilder(requestRouter);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            baos.write(responseBuilder.getResponse(requestParams));
            byte[] result = baos.toByteArray();
            BufferedOutputStream outputStream = new BufferedOutputStream(clientSocket.getOutputStream());
            outputStream.write(result);
            baos.flush();
            baos.close();
            outputStream.close();

            stopServer(serverSocket, clientSocket);
        }
    }

    protected Boolean running(){
        return true;
    }

    protected ServerSocket createServerSocket() throws IOException {
        return new ServerSocket(serverConfig.getPortNumber());
    }

    private Socket openSocket(ServerSocket serverSocket) throws IOException {
        return serverSocket.accept();
    }

    private BufferedReader openInputStream(Socket socket) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        return bufferedReader;
    }

    private void stopServer(ServerSocket serverSocket, Socket clientSocket) throws IOException {
        clientSocket.close();
        serverSocket.close();
    }
}
