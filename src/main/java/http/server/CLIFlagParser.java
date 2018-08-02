package http.server;

import java.io.IOException;

public class CLIFlagParser {

    public int parse(String[] args) throws IOException {
        int portNumber;

        try{
            if(args[0].equals("-p")){
                portNumber = Integer.parseInt(args[1]);
            } else {
                throw new IOException();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IOException();
        }
        return  portNumber;
    }
}
