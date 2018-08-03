package http.application;

import http.server.RequestParams;

public class DefaultApplication extends Application {

    public byte[] apply(RequestParams requestParams){
        if (requestParams.getPath().equals("/") && requestParams.getMethod().equals("GET")) {
            return "HTTP/1.1 200 OK\r\n\r\n<h1 align=\"center\">Hello, World!</h1>".getBytes();
        } else {
            return ("HTTP/1.1 404 Not Found\r\n\r\n<h1 align=\"center\">404</h1><h2 align=\"center\">We could not find the page your were looking for.</h2>").getBytes();
        }
    }
}
