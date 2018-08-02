package http.server;

public class ServerConfig {

    private int portNumber;

    ServerConfig(int portNumber){
        this.portNumber = portNumber;
    }

    public int getPortNumber(){
        return portNumber;
    }
}
