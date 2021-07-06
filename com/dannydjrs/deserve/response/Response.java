package com.dannydjrs.deserve.response;

import java.io.IOException;
import java.net.Socket;

public interface Response {
    public void send(Socket client) throws IOException;
}
