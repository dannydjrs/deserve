package com.dannydjrs.deserve.request;

import java.util.Map;
import java.util.HashMap;

public class Request {
    private String method;
    private String url;
    private String protocol;
    private Map<String, String> map;

    public Request() {
        this.map = new HashMap<String, String>();
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getMethod() {
        return this.method;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getProtocol() {
        return this.protocol;
    }

    public void set(String key, String value) {
        this.map.put(key, value);
    }

    public String get(String key) {
        return this.map.get(key);
    }
}
