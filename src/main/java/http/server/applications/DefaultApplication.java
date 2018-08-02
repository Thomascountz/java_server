package http.server.applications;

import http.server.RequestParams;

public class DefaultApplication implements IApplication{

    public byte[] apply(RequestParams requestParams){
        return "HTTP/1.1 200 OK\r\n\r\nHello, World!".getBytes();
    }
}
