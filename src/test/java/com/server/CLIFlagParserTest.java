package com.server;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class CLIFlagParserTest {

    @Test
    public void successful_parse() throws IOException {
        String[] args = {"-p", "5000"};

        final CLIFlagParser parser = new CLIFlagParser();
        final ServerConfig serverConfig = parser.parse(args);

        assertEquals(5000, serverConfig.getPortNumber());
    }

    @Test(expected = IOException.class)
    public void failed_parse() throws IOException {
        String[] args = {"-f 5000"};

        final CLIFlagParser parser = new CLIFlagParser();
        parser.parse(args);
    }

}