package http.application;

import http.server.RequestParams;
import http.server.RequestParamsBuilder;
import org.junit.Test;

import static org.junit.Assert.*;

public class DefaultApplicationTest {

    @Test
    public void responseReturns404NotFoundByDefault() {
        Application application = new DefaultApplication();
        String result = application.response();
        assertTrue(result.contains("404"));
    }

    @Test
    public void responseReturns200ForRootRoute() {
        RequestParams requestParams = new RequestParamsBuilder().setPath("/").setMethod("GET").build();
        Application application = new DefaultApplication();
        application.apply(requestParams);
        String result = application.response();
        assertTrue(result.contains("200"));

    }

}