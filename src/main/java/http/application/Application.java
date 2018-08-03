package http.application;

import http.server.RequestParams;

import java.util.Optional;
import java.util.function.Supplier;

public abstract class Application {
    public Optional<String> response = Optional.empty();
    public RequestParams requestParams;

    public abstract String apply(RequestParams requestParams);

    public void onGet(String path, Supplier<String> block) {
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

    public String response() {
        String notFound = "HTTP/1.1 404 Not Found\r\n\r\n<h1 align=\"center\">404</h1><h2 align=\"center\">We could not find the page your were looking for.</h2>";
        return response.orElse(notFound);
    }
}
