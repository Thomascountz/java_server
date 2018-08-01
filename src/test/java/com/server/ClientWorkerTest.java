package com.server;

import com.server.mocks.MockSocket;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClientWorkerTest {

    @Test
    public void runSendsA200OKToClientSocket() {

        MockSocket clientSocket = new MockSocket();
        clientSocket.getOutputStream();

        ServerConfig serverConfig = new ServerConfig(5000);

        clientSocket.setRequestHeader("GET", "/");

        ClientWorker clientWorker = new ClientWorker(clientSocket, serverConfig);

        clientWorker.run();

        assertTrue(clientSocket.getOutgoingString().contains("200"));
    }

}