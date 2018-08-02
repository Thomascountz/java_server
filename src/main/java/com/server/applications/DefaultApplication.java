package com.server;

public class Application implements IApplication{

    public byte[] apply(RequestParams requestParams){
        return "HTTP/1.1 200 OK\r\n\r\nHello, World!".getBytes();
    }
}
