package com.server;

import java.util.Hashtable;

public class RequestParamsBuilder {
    private String path;
    private String method;
    private Hashtable<String, String> queryComponent;
    private Hashtable<String, String> cookies;
    private String directory;
    private Hashtable<String, Integer> range;

    public RequestParamsBuilder setPath(String path) {
        this.path = path;
        return this;
    }

    public RequestParamsBuilder setMethod(String method) {
        this.method = method;
        return this;
    }

    public RequestParamsBuilder setQueryComponent(Hashtable<String, String> queryComponent) {
        this.queryComponent = queryComponent;
        return this;
    }

    public RequestParamsBuilder setCookies(Hashtable<String, String> cookies) {
        this.cookies = cookies;
        return this;
    }

    public RequestParamsBuilder setDirectory(String s) {
        this.directory = s;
        return this;
    }

    public RequestParamsBuilder setRange(Hashtable<String, Integer> range) {
        this.range = range;
        return this;
    }

    public RequestParams build() {
        return new RequestParams(path, method, queryComponent, cookies, directory, range);
    }


}