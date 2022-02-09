package com.dannydjrs.deserve.response;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * @author dannydjrs
 */
public class RedirectResponse implements Response {
    private String location;

    /**
     * 
     */
    public RedirectResponse() {}

    /**
     * 
     */
    @Override
    public void send(OutputStream output) throws IOException {
        PrintWriter out = new PrintWriter(output);
        out.println("HTTP/1.1 301 Moved Permanently");
        out.println("Location: " + this.location);
        out.close();
    }

    /**
     * 
     */
    public void setLocation(String location) {
        this.location = location;
    }
}
