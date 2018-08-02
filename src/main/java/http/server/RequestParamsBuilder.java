package http.server;

import java.util.Hashtable;

public class RequestParamsBuilder {
    private String path;
    private String method;
    private Hashtable<String, String> queryComponent;
    private Hashtable<String, String> cookies;
    private Hashtable<String, Integer> range;
    private String bodyString;
    private int contentLength;
    private String authorizationCredentials;
    private String ifMatch;

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

    public RequestParamsBuilder setRange(Hashtable<String, Integer> range) {
        this.range = range;
        return this;
    }

    public RequestParamsBuilder setContentLength(int contentLength){
        this.contentLength = contentLength;
        return this;
    }

    public RequestParamsBuilder setBody(String bodyString){
        this.bodyString = bodyString;
        return this;
    }

    public RequestParamsBuilder setAuthorizationCredentials(String s) {
        this.authorizationCredentials = s;
        return this;
    }

    public RequestParamsBuilder setIfMatch(String ifMatch) {
        this.ifMatch = ifMatch;
        return this;
    }

    public RequestParams build() {
        return new RequestParams(path, method, queryComponent, cookies, range, bodyString,
                                    contentLength, authorizationCredentials, ifMatch);
    }
}