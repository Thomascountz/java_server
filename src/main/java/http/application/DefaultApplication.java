package http.application;

import http.server.RequestParams;
import http.server.Response;

import java.util.Optional;

public class DefaultApplication extends Application {

    public Response apply(RequestParams requestParams){
        reset(requestParams);

        onGet("/", () -> {
            return new Response(200, "<h1 align=\"center\">Hello, World!</h1>");
        });

        return response();
    }
}
