package com.dannydjrs.deserve.response;

import com.dannydjrs.deserve.response.Response;
import com.dannydjrs.deserve.config.Config;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.File;
import java.net.Socket;
import java.util.Date;

public class FileResponse implements Response {
    private File file;
    private String content_type;

    public FileResponse(File file, String content_type) {
        this.file = file;
        this.content_type = content_type;
    }

    public void send(Socket client) throws IOException {
        PrintWriter out = new PrintWriter(client.getOutputStream());
        BufferedOutputStream body = new BufferedOutputStream(
            client.getOutputStream()
        );

        out.println("HTTP/1.1 200 OK");
        out.println("Server: " + Config.SERVER_NAME + "/" + Config.SERVER_VERSION);
        out.println("Date: " + new Date());
        out.println("Content-type: " + this.content_type);
        out.println("Content-length: " + (int)this.file.length());
        out.println("Connection: close");
        out.println("");
        out.flush();
        
        FileInputStream file_stream = new FileInputStream(this.file);
        int count;
        byte[] buffer = new byte[4096];
        while ((count = file_stream.read(buffer)) > 0) {
            body.write(buffer, 0, count);
        }
        file_stream.close();
        body.close();
        out.close();
    }
}
