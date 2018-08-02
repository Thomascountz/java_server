package http.server;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Hashtable;

import static org.junit.Assert.*;

public class RequestParserTest {

    @Test
    public void getVerbReturnsGetHTTPVerb() throws UnsupportedEncodingException {

        String headerString = "GET / HTTP/1.1";

        RequestParser requestParser = new RequestParser(headerString);

        assertEquals("GET", requestParser.getRequestParams().getMethod());
    }

    @Test
    public void getVerbReturnsHTTPPostVerb() throws UnsupportedEncodingException {

        String headerString = "POST / HTTP/1.1";

        RequestParser requestParser = new RequestParser(headerString);

        assertEquals("POST", requestParser.getRequestParams().getMethod());
    }

    @Test
    public void getRouteReturnsHTTPRoute() throws UnsupportedEncodingException {

        String headerString = "POST / HTTP/1.1";

        RequestParser requestParser = new RequestParser(headerString);

        assertEquals("/", requestParser.getRequestParams().getPath());
    }

    @Test
    public void getRouteReturnsFormHTTPRoute() throws UnsupportedEncodingException {

        String headerString = "POST /form HTTP/1.1";

        RequestParser requestParser = new RequestParser(headerString);

        assertEquals("/form", requestParser.getRequestParams().getPath());
    }

    @Test
    public void getRouteReturnsPathWithoutQueryComponent() throws UnsupportedEncodingException {

        String headerString = "GET /cookie?type=chocolate HTTP/1.1";

        RequestParser requestParser = new RequestParser(headerString);

        assertEquals("/cookie", requestParser.getRequestParams().getPath());
    }

    @Test
    public void getQueryComponentReturnsAHashTableFromRequestURI() throws UnsupportedEncodingException {

        String headerString = "GET /cookie?type=chocolate&foo=bar HTTP/1.1";

        RequestParser requestParser = new RequestParser(headerString);

        Hashtable result = requestParser.getRequestParams().getQueryComponent();

        assertEquals("chocolate", result.get("type"));
        assertEquals("bar", result.get("foo"));
    }

    @Test
    public void getQueryComponentReturnsADecodedHashTableFromRequestURI() throws UnsupportedEncodingException {

        String headerString = "GET /cookie?foo=%3D%40%23%24%25%5E HTTP/1.1";

        RequestParser requestParser = new RequestParser(headerString);

        Hashtable result = requestParser.getRequestParams().getQueryComponent();

        assertEquals("=@#$%^", result.get("foo"));
    }

    @Test
    public void getQueryComponentReturnsAnEmptyHashTableIfNoQueriesAreInURI() throws UnsupportedEncodingException {

        String headerString = "GET / HTTP/1.1";

        RequestParser requestParser = new RequestParser(headerString);

        Hashtable result = requestParser.getRequestParams().getQueryComponent();

        assertEquals(new Hashtable(), result);
    }

    @Test
    public void getCookiesReturnsAHashTableFromRequestHeaders() throws UnsupportedEncodingException {

        String headerString = "GET /cookie HTTP/1.1\r\nCookie: type=chocolate\r\n\r\n";

        RequestParser requestParser = new RequestParser(headerString);

        Hashtable result = requestParser.getRequestParams().getCookies();

        assertEquals("chocolate", result.get("type"));
    }

    @Test
    public void getCookieReturnsAnEmptyHashTableIfNoCookiesInRequestHeaders() throws UnsupportedEncodingException {

        String headerString = "GET /cookie?type=chocolate&foo=bar HTTP/1.1";

        RequestParser requestParser = new RequestParser(headerString);

        Hashtable result = requestParser.getRequestParams().getCookies();

        assertEquals(new Hashtable(), result);
    }

    @Test
    public void requestParamsReturnsRequestParamsWithRangeTable() throws UnsupportedEncodingException {
        String headerString = "GET /foo HTTP/1.1\r\nRange: bytes=1-2\r\n";

        RequestParser requestParser = new RequestParser(headerString);

        Hashtable result = requestParser.getRequestParams().getRange();

        assertEquals(1, result.get("start"));
        assertEquals(2, result.get("stop"));

    }

    @Test
    public void requestParamsReturnsRequestParamsWithDefaultStartRangeTable() throws UnsupportedEncodingException {
        String headerString = "GET /foo HTTP/1.1\r\nRange: bytes=-2\r\n";

        RequestParser requestParser = new RequestParser(headerString);

        Hashtable result = requestParser.getRequestParams().getRange();

        assertEquals(-1, result.get("start"));
        assertEquals(2, result.get("stop"));

    }

    @Test
    public void requestParamsReturnsRequestParamsWithDefaultEndRangeTable() throws UnsupportedEncodingException {
        String headerString = "GET /foo HTTP/1.1\r\nRange: bytes=2-\r\n";

        RequestParser requestParser = new RequestParser(headerString);

        Hashtable result = requestParser.getRequestParams().getRange();

        assertEquals(2, result.get("start"));
        assertEquals(-1, result.get("stop"));

    }

    @Test
    public void requestParamsReturnsRequestParamsWithEmptyRangeTable() throws UnsupportedEncodingException {
        String headerString = "GET /foo HTTP/1.1\r\n\r\n";

        RequestParser requestParser = new RequestParser(headerString);

        Hashtable result = requestParser.getRequestParams().getRange();

        assertEquals(new Hashtable(), result);
    }

    @Test
    public void getContentLengthReturnsContentLengthOfBody() throws UnsupportedEncodingException{
        String headerString = "GET /foo HTTP/1.1\r\nContent-Length:4\r\n\r\nbody";

        RequestParser requestParser = new RequestParser(headerString);

        assertEquals(4, requestParser.getRequestParams().getContentLength());
    }

    @Test
    public void getBodyReturnsTheRequestBodyAsAString() throws UnsupportedEncodingException{
        String headerString = "GET /foo HTTP/1.1\r\nContent-Length:4\r\n\r\nbody";

        RequestParser requestParser = new RequestParser(headerString);

        assertEquals("body", requestParser.getRequestParams().getBody());
    }

    @Test
    public void getAuthorizationCredentialsReturnsExpectedString() throws UnsupportedEncodingException {
        String headerString = "GET /logs HTTP/1.1\r\nAuthorization: Basic AWDVyM==\r\n";
        RequestParser requestParser = new RequestParser(headerString);
        String result = requestParser.getRequestParams().getAuthorizationCredentials();

        assertEquals("AWDVyM==", result);
    }

    @Test
    public void requestParamsReturnsIfMatchFromHeader() throws UnsupportedEncodingException {
        String headerString = "GET /foo HTTP/1.1\r\nIf-Match: foobar12345";

        RequestParser requestParser = new RequestParser(headerString);

        String result = requestParser.getRequestParams().getIfMatch();

        assertEquals("foobar12345", result);
    }

    @Test
    public void requestParamsReturnsIfMatchAsEmptyString() throws UnsupportedEncodingException {
        String headerString = "GET /foo HTTP/1.1\r\n\r\n";

        RequestParser requestParser = new RequestParser(headerString);

        String result = requestParser.getRequestParams().getIfMatch();

        assertEquals("", result);
    }
}