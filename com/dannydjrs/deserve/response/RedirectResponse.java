package com.dannydjrs.deserve.response;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class RedirectResponse implements Response {
    private String location;

    public RedirectResponse(String location) {
        this.location = location;
    }

    public void send(Socket client) throws IOException {
        PrintWriter out = new PrintWriter(client.getOutputStream());
        out.println("HTTP/1.1 301 Moved Permanently");
        out.println("Location: " + this.location);
        out.close();
    }
}
