package http.server.mocks;

import http.application.Application;
import http.server.RequestParams;
import http.server.Response;

public class MockApplication extends Application {

    public Response apply(RequestParams requestParams){
        return new Response(200, "");
    }
}
