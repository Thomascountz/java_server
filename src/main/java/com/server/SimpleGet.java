package com.server;

import java.net.*;
import java.io.*;

public class SimpleGet {

    private ServerConfig serverConfig;
    private RequestRouter requestRouter;

    SimpleGet(ServerConfig serverConfig, RequestRouter requestRouter){
        this.serverConfig = serverConfig;
        this.requestRouter = requestRouter;
    }

    public void runServer() throws IOException {

        while(running()) {

            ServerSocket serverSocket = createServerSocket();
            Socket clientSocket = openSocket(serverSocket);

            BufferedReader in = openInputStream(clientSocket);

            RequestHeaderReader requestHeaderReader = new RequestHeaderReader(in);
            RequestHeaderParser requestHeaderParser = new RequestHeaderParser(requestHeaderReader.getHeader());

            PrintWriter out = openOutputStream(clientSocket);

            int responseCode = requestRouter.getResponseCode(requestHeaderParser.getPath(), requestHeaderParser.getMethod());

            if(responseCode == 200) {
                sendHTTPOkHeader(out);
            } else if(responseCode == 405) {
                sendHTTPNotAllowedHeader(out);
            } else {
                sendHTTPNotFoundHeader(out);
            }

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

    private PrintWriter openOutputStream(Socket socket) throws IOException {
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
        return printWriter;
    }

    private void sendHTTPOkHeader(PrintWriter out){
        out.println("HTTP/1.1 200 OK");
    }

    private void sendHTTPNotFoundHeader(PrintWriter out){
        out.println("HTTP/1.1 404 Not Found");
    }

    private void sendHTTPNotAllowedHeader(PrintWriter out){
        out.println("HTTP/1.1 405 Method Not Allowed");
    }

    private void stopServer(ServerSocket serverSocket, Socket clientSocket) throws IOException {
        clientSocket.close();
        serverSocket.close();
    }
}
