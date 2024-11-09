package telran.appl.net;

import telran.net.*;

public class ReverseLengthProtocol implements Protocol {

    @Override
    public Response getResponse(Request request) {
        String type = request.requestType();
        String data = request.requestData();
        Response response = null;
        try {
            response = switch (type) {
                case "reverse" -> reverse(data);
                case "length" -> length(data);
                default -> new Response(ResponseCode.WRONG_TYPE, type + " is wrong type");
            };
        } catch (Exception e) {
            response = new Response(ResponseCode.WRONG_DATA, e.getMessage());
        }
        return response;
    }

    private Response length(String data) {
        String length = data.length() + "";
        return getResponseOk(length);
    }

    private Response reverse(String data) {
        if(data.length() < 2) {
            throw new IllegalArgumentException("The string is too short, must be longer than 1");
        }
        String result = new StringBuilder(data).reverse().toString();
        return getResponseOk(result);
    }

    private Response getResponseOk(String length) {
        return new Response(ResponseCode.OK, length);
    }
}
