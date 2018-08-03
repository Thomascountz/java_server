package http.application;

import http.server.RequestParams;

public abstract class Application {
    public abstract byte[] apply(RequestParams requestParams);
}
