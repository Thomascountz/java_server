package com.server;

import com.server.handlers.*;

public class ResponseHeaderBuilder {

    private String header = "";
    private RequestRouter requestRouter;

    ResponseHeaderBuilder(RequestRouter requestRouter){
        this.requestRouter = requestRouter;
    }

    public byte[] getHeader(RequestParams requestParams, ResponseParams responseParams) {
        StatusHandler statusHandler = new StatusHandler(requestRouter);
        String statusLine = statusHandler.createLine(requestParams, responseParams);
        appendLine(statusLine);

        BasicAuthorizationHandler basicAuthHandler = new BasicAuthorizationHandler(requestParams);
        String basicAuthLine = basicAuthHandler.createLine(requestParams, responseParams);
        appendLine(basicAuthLine);

        if (requestRouter.getResponseCode(requestParams) < 400 || responseParams.getResponseCode() != 0){
            AllowHandler allowHandler = new AllowHandler();
            String allowLine = allowHandler.createLine(requestParams, responseParams);
            appendLine(allowLine);

            LocationHandler locationHandler = new LocationHandler();
            String locationLine = locationHandler.createLine(requestParams, responseParams);
            appendLine(locationLine);

            ContentTypeHandler contentTypeHandler = new ContentTypeHandler();
            String contentTypeLine = contentTypeHandler.createLine(requestParams, responseParams);
            appendLine(contentTypeLine);

            ContentLengthHandler contentLengthHandler = new ContentLengthHandler(requestParams.getDirectory());

            String contentLengthLine = contentLengthHandler.createLine(requestParams, responseParams);
            appendLine(contentLengthLine);

            SetCookieHandler setCookieHandler = new SetCookieHandler();
            String setCookieHandlerLine = setCookieHandler.createLine(requestParams, responseParams);
            appendLine(setCookieHandlerLine);

            PartialContentHandler partialContentHandler = new PartialContentHandler();
            String partialContentLine = partialContentHandler.createLine(requestParams, responseParams);
            appendLine(partialContentLine);
        }
        String endOfHeader = "\r\n";

        return (this.header + endOfHeader).getBytes();
    }

    private void appendLine(String line) {
        if(!line.equals("")){
            this.header = this.header + line + "\r\n";
        }
    }
}
