package com.dannydjrs.deserve.request;

import java.util.Map;
import java.util.HashMap;

/**
 * @author dannydjrs
 */
public class Request {
    private String method;
    private String url;
    private String protocol;
    private Map<String, String> map;

    /**
     * 
     */
    public Request() {
        this.map = new HashMap<String, String>();
    }

    /**
     * 
     * @param method
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * 
     * @return
     */
    public String getMethod() {
        return this.method;
    }

    /**
     * 
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 
     * @return
     */
    public String getUrl() {
        return this.url;
    }
    
    /**
     * 
     * @param protocol
     */
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
    
    /**
     * 
     * @return
     */
    public String getProtocol() {
        return this.protocol;
    }

    /**
     * 
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        this.map.put(key, value);
    }

    /**
     * 
     * @param key
     * @return
     */
    public String get(String key) {
        return this.map.get(key);
    }

    /**
     * 
     */
    public String toString() {
        return String.format("Request: %s %s %s", 
            this.getMethod(),
            this.getUrl(),
            this.getProtocol()
        );
    }
}
