package com.server;

import org.junit.Ignore;
import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.Assert.*;

public class SimpleGetTest {

    @Test
    public void runServerSendsHTTPOKHeader () throws IOException {
        int portNumber = 5000;
        String directoryPath = "/path/to/dir";
        ServerConfig serverConfig = new ServerConfig(portNumber, directoryPath);

        final MockServerSocket serverSocket = new MockServerSocket();
//        MockRequestRouter mockRequestRouter = new MockRequestRouter();
//        mockRequestRouter.setResponseCode(200);
        RequestRouter requestRouter = new RequestRouter();

        SimpleGet simpleGet = new SimpleGet(serverConfig, requestRouter) {
            int runCount = 1;

            @Override
            protected Boolean running(){
                if(runCount > 0){
                    runCount -= 1;
                    return true;
                } else {
                    return false;
                }
            }

            @Override
            protected ServerSocket createServerSocket() throws IOException {
                return serverSocket;
            }
        };

        simpleGet.runServer();

        assertTrue(serverSocket.getMockSocket().getOutgoingString().contains("200"));
    }

    @Test
    public void runServerSendsHTTPNotFound () throws IOException {
        int portNumber = 5000;
        String directoryPath = "/path/to/dir";

        ServerConfig serverConfig = new ServerConfig(portNumber, directoryPath);

        final MockServerSocket serverSocket = new MockServerSocket();
        MockRequestRouter mockRequestRouter = new MockRequestRouter();
        mockRequestRouter.setResponseCode(404);

        SimpleGet simpleGet = new SimpleGet(serverConfig, mockRequestRouter) {
            int runCount = 1;

            @Override
            protected Boolean running(){
                if(runCount > 0){
                    runCount -= 1;
                    return true;
                } else {
                    return false;
                }
            }

            @Override
            protected ServerSocket createServerSocket() throws IOException {
                return serverSocket;
            }
        };

        simpleGet.runServer();

        assertTrue(serverSocket.getMockSocket().getOutgoingString().contains("404"));
    }

    @Test
    public void runServerSendsHTTPMethodNotAllowed () throws IOException {
        int portNumber = 5000;
        String directoryPath = "/path/to/dir";

        ServerConfig serverConfig = new ServerConfig(portNumber, directoryPath);

        final MockServerSocket serverSocket = new MockServerSocket();
        MockRequestRouter mockRequestRouter = new MockRequestRouter();
        mockRequestRouter.setResponseCode(405);

        SimpleGet simpleGet = new SimpleGet(serverConfig, mockRequestRouter) {
            int runCount = 1;

            @Override
            protected Boolean running(){
                if(runCount > 0){
                    runCount -= 1;
                    return true;
                } else {
                    return false;
                }
            }

            @Override
            protected ServerSocket createServerSocket() throws IOException {
                return serverSocket;
            }
        };

        simpleGet.runServer();

        assertTrue(serverSocket.getMockSocket().getOutgoingString().contains("405"));
    }

    @Test
    public void createServerSocketReturnsAServerSocketWithConfigPortNumber() throws IOException {
        int portNumber = 5000;
        String directoryPath = "/path/to/dir";
        ServerConfig serverConfig = new ServerConfig(portNumber, directoryPath);
        final RequestRouter requestRouter = new RequestRouter();
        SimpleGet simpleGet = new SimpleGet(serverConfig, requestRouter);

        ServerSocket serverSocket = simpleGet.createServerSocket();

        assertEquals(serverSocket.getLocalPort(), portNumber);
    }

    @Test
    public void runningReturnsTrue(){

        int portNumber = 5000;
        String directoryPath = "/path/to/dir";
        ServerConfig serverConfig = new ServerConfig(portNumber, directoryPath);
        final RequestRouter requestRouter = new RequestRouter();
        SimpleGet simpleGet = new SimpleGet(serverConfig, requestRouter);

        assertTrue(simpleGet.running());
    }

    @Test
    public void runServerSends200OKForOptionsMethod () throws IOException {
        int portNumber = 5000;
        String directoryPath = "/path/to/dir";

        ServerConfig serverConfig = new ServerConfig(portNumber, directoryPath);
        MockSocket mockSocket = new MockSocket();
        mockSocket.setRequestHeader("OPTIONS", "/method_options");
        final MockServerSocket serverSocket = new MockServerSocket(mockSocket);
        RequestRouter requestRouter = new RequestRouter();

        SimpleGet simpleGet = new SimpleGet(serverConfig, requestRouter) {
            int runCount = 1;

            @Override
            protected Boolean running(){
                if(runCount > 0){
                    runCount -= 1;
                    return true;
                } else {
                    return false;
                }
            }

            @Override
            protected ServerSocket createServerSocket() throws IOException {
                return serverSocket;
            }
        };

        simpleGet.runServer();

        MockSocket m = serverSocket.getMockSocket();
        String outgoingString = m.getOutgoingString();
        assertEquals(true, outgoingString.contains("200"));
    }
}