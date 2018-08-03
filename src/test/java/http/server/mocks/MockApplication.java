package http.server.mocks;

import http.application.Application;
import http.server.RequestParams;

public class MockApplication extends Application {

    public String apply(RequestParams requestParams){
        return "HTTP/1.1 200 OK";
    }
}
