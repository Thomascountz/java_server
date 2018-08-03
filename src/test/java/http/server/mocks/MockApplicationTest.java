package http.server.mocks;

import http.application.Application;
import http.server.RequestParams;
import http.server.RequestParamsBuilder;
import org.junit.Test;

import static org.junit.Assert.*;

public class MockApplicationTest {

    @Test
    public void applyReturns200OKStatusLineResponseHeader(){
        Application application = new MockApplication();
        RequestParams requestParams = new RequestParamsBuilder().setMethod("GET").setPath("/").build();
        String result = new String(application.apply(requestParams).getReponse());
        assertEquals("HTTP/1.1 200 OK", result);
    }

}