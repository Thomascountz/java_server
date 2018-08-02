package http.server;

import org.junit.Test;

import static org.junit.Assert.*;

public class ServerConfigTest {

    @Test
    public void port() {
        int portNumber = 5000;

        final ServerConfig serverConfig = new ServerConfig(portNumber);

        assertEquals(5000, serverConfig.getPortNumber());
    }

}