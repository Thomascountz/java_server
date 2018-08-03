package http.server;

import org.junit.Test;

import static org.junit.Assert.*;

public class ResponseTest {

    @Test
    public void getResponseReturns200OKHeader() {
        Response response = new Response(200, "");
        assertEquals("HTTP/1.1 200 OK", new String(response.getReponse()));
    }

    @Test
    public void getResponseReturns404NotFoundHeader() {
        Response response = new Response(404, "");
        assertEquals("HTTP/1.1 404 Not Found", new String(response.getReponse()));
    }

    @Test
    public void getResponseReturnsBody() {
        Response response = new Response(200, "foo");
        assertTrue(new String(response.getReponse()).contains("foo"));
    }

}