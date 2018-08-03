package http.application;

import http.server.RequestParams;

import java.util.Optional;

public class DefaultApplication extends Application {

    public String apply(RequestParams requestParams){
        reset(requestParams);

        onGet("/", () -> "HTTP/1.1 200 OK\r\n\r\n<h1 align=\"center\">Hello, World!</h1>");

        return response();
    }
}
