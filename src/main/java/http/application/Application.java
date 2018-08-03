package http.application;

import http.server.RequestParams;
import http.server.Response;

import java.util.Optional;
import java.util.function.Supplier;

public abstract class Application {
    public Optional<Response> response = Optional.empty();
    public RequestParams requestParams;

    public abstract Response apply(RequestParams requestParams);

    public void onGet(String path, Supplier<Response> block) {
        if (requestParams.getMethod().equals("GET")) {
            if (requestParams.getPath().equals(path)) {
                this.response = Optional.of(block.get());
            }
        }
    }

    public void reset(RequestParams requestParams) {
        this.response = Optional.empty();
        this.requestParams = requestParams;
    }

    public Response response() {
        Response notFound = new Response(404, "<h1 align=\"center\">404</h1><h2 align=\"center\">We could not find the page you were looking for.</h2>");
        return response.orElse(notFound);
    }
}
