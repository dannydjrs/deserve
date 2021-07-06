package com.dannydjrs.deserve.response;

import com.dannydjrs.deserve.response.Response;
import com.dannydjrs.deserve.config.Config;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class FourZeroFourResponse implements Response {
    public void send(Socket client) throws IOException {
        PrintWriter out = new PrintWriter(client.getOutputStream());
        out.println("HTTP/1.1 404 File Not Found");
        out.println("Server: " + Config.SERVER_NAME + "/" + Config.SERVER_VERSION);
        out.println("Date: " + new Date());
        out.println("Connection: close");
        out.close();
    }
}
