package http.server;

import java.util.Hashtable;

public class RequestParams {

    private String path;
    private String method;
    private Hashtable<String, String> queryComponent;
    private Hashtable<String, String> cookies;
    private Hashtable<String, Integer> range;
    private String ifMatch;
    private String bodyString;
    private int contentLength;
    private String authorizationCredentials;

    public RequestParams(String path, String method, Hashtable<String, String> queryComponent, Hashtable<String, String> cookies, Hashtable<String, Integer> range, String bodyString, int contentLength, String authorizationCredentials, String ifMatch) throws NullPointerException {
        if ((path == null) || (method == null)) {
            throw new NullPointerException();
        }
        this.path = path;
        this.method = method;
        this.queryComponent = queryComponent;
        this.cookies = cookies;
        this.range = range;
        this.bodyString = bodyString;
        this.contentLength = contentLength;
        this.authorizationCredentials = authorizationCredentials;
        this.ifMatch = ifMatch;
    }

    public String getPath() { return this.path; }

    public String getMethod() { return this.method; }

    public Hashtable getQueryComponent() { return this.queryComponent; }

    public Hashtable getCookies() { return this.cookies; }

    public Hashtable<String, Integer> getRange() { return this.range; }

    public String getBody() { return this.bodyString; }

    public int getContentLength() { return this.contentLength; }

    public String getAuthorizationCredentials() { return this.authorizationCredentials; }

    public String getIfMatch() {
        return this.ifMatch;
    }
}
