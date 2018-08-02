package http.server.mocks;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.Socket;

public class MockSocket extends Socket{

    private ByteArrayOutputStream outgoingStream;
    private String header = "GET / HTTP/1.1\r\n\r\n";

    public ByteArrayOutputStream getOutputStream(){
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        outgoingStream = os;
        return os;
    }

    public ByteArrayInputStream getInputStream(){
        ByteArrayInputStream is = new ByteArrayInputStream(this.header.getBytes());
        return is;
    }

    public String getOutgoingString() {
        return new String(outgoingStream.toByteArray());
    }

    public void setRequestHeader(String method, String path) {
        this.header = method + " " + path + " " + "HTTP/1.1" + "\r\n\r\n";
    }
}
