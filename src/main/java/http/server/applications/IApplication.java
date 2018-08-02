package http.server.applications;

import http.server.RequestParams;

public interface IApplication {
    public byte[] apply(RequestParams requestParams);
}
