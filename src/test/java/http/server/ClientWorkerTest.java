package http.server;

import http.server.applications.DefaultApplication;
import http.server.applications.IApplication;
import http.server.loggers.NullLogger;
import http.server.mocks.MockSocket;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClientWorkerTest {

    @Test
    public void runSendsA200OKToClientSocket() {

        MockSocket clientSocket = new MockSocket();
        clientSocket.getOutputStream();

        clientSocket.setRequestHeader("GET", "/");

        IApplication application = new DefaultApplication();

        ClientWorker clientWorker = new ClientWorker(application, clientSocket, new NullLogger());

        clientWorker.run();

        assertTrue(clientSocket.getOutgoingString().contains("200"));
    }

}