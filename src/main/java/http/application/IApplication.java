package http.application;

import http.server.RequestParams;

public interface IApplication {
    public byte[] apply(RequestParams requestParams);
}
