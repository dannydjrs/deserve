package com.dannydjrs.deserve.request;

import java.util.Map;
import java.util.HashMap;
import java.io.IOException;
import java.io.BufferedReader;

public class Request {
    private Map<String, String> map;

    public Request() {
        this.map = new HashMap<String, String>();
    }

    public void set(String key, String value) {
        this.map.put(key, value);
    }

    public String get(String key) {
        return this.map.get(key);
    }

    public static Request build(BufferedReader raw) throws IOException {
        Request request = new Request();

        String line;
        line = raw.readLine();
        request.set("METHOD", line.split(" ")[0]);
        request.set("URL", line.split(" ")[1]);
        request.set("PROTOCOL", line.split(" ")[2]);

        while ((line = raw.readLine()) != null && !line.isEmpty()) {
            request.set(line.split(": ")[0], line.split(": ")[1]);
        }

        return request;
    }
}
