package com.dannydjrs.deserve.request;

import java.io.IOException;
import java.io.BufferedReader;

public class RequestBuilder {

    public static Request build(BufferedReader raw) throws IOException {
        Request request = new Request();

        String line;
        line = raw.readLine();
        request.setMethod(line.split(" ")[0]);
        request.setUrl(line.split(" ")[1]);
        request.setProtocol(line.split(" ")[2]);

        while ((line = raw.readLine()) != null && !line.isEmpty()) {
            request.set(line.split(": ")[0], line.split(": ")[1]);
        }

        return request;
    }
}
