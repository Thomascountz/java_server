package http.application;

import http.server.RequestParams;

public class DefaultApplication extends Application {

    public byte[] apply(RequestParams requestParams){
        return "HTTP/1.1 200 OK\r\n\r\nHello, World!".getBytes();
    }
}