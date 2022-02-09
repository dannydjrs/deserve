package com.dannydjrs.deserve.response;

import com.dannydjrs.deserve.config.ConfigManager;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.File;
import java.util.Date;

/**
 * @author dannydjrs
 */
public class FileResponse implements Response {
    private ConfigManager configManager;
    private File file;
    private String contentType;

    /**
     * 
     * @param configManager
     */
    public FileResponse(
        ConfigManager configManager
    ) {
        this.configManager = configManager;
    }

    /**
     * 
     * @param file
     * @return
     */
    public FileResponse setFile(File file) {
        this.file = file;
        return this;
    }
    
    /**
     * 
     * @param contentType
     * @return
     */
    public FileResponse setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    /**
     * 
     */
    @Override
    public void send(OutputStream output) throws IOException {
        PrintWriter out = new PrintWriter(output);
        out.println("HTTP/1.1 200 OK");
        out.println("Server: " + this.configManager.get("config", "server.name") + "/" + this.configManager.get("config", "server.version"));
        out.println("Date: " + new Date());
        out.println("Content-type: " + this.contentType);
        out.println("Content-length: " + (int)this.file.length());
        out.println("Connection: close");
        out.println("");
        out.flush();
        
        BufferedOutputStream body = new BufferedOutputStream(output);
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
