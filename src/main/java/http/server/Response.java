package http.server;

import java.util.Hashtable;

public class Response {

    private int responseCode;
    private String body;
    private Hashtable<Integer, String> statusCodes;
    private final String HTTP_VERSION = "HTTP/1.1";

    public Response(int responseCode, String body) {
        this.responseCode = responseCode;
        this.body = body;
        this.statusCodes = new Hashtable<>();
        statusCodes.put(200, "200 OK");
        statusCodes.put(404, "404 Not Found");
    }

    public byte[] getReponse() {
        String response = HTTP_VERSION;
        response += " ";
        response += statusCodes.get(this.responseCode);
        if (!body.isEmpty()) {
            response += "\r\n\r\n";
            response += body;
        }
        return response.getBytes();
    }
}
