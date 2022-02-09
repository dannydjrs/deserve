package com.dannydjrs.deserve.response;

import com.dannydjrs.deserve.config.ConfigManager;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

/**
 * @author dannydjrs
 */
public class StatusResponse implements Response {
    private ConfigManager configManager;
    private int status;

    private static final Map<Integer, String> statusTextMap = Map.ofEntries(
        Map.entry(404, "File Not Found"),
        Map.entry(501, "Not Implemented")
    );
    
    /**
     * 
     * @param configManager
     */
    public StatusResponse(
        ConfigManager configManager
    ) {
        this.configManager = configManager;
    }

    /**
     * 
     * @param status
     * @return
     */
    public StatusResponse setStatus(int status) {
        this.status = status;
        return this;
    }

    /**
     * 
     */
    @Override
    public void send(OutputStream output) throws IOException {
        PrintWriter out = new PrintWriter(output);
        out.println("HTTP/1.1 " + this.status + " " + StatusResponse.statusTextMap.get(this.status));
        out.println("Server: " + this.configManager.get("config", "server.name") + "/" + this.configManager.get("config", "server.version"));
        out.println("Date: " + new Date());
        out.println("Connection: close");
        out.println("");
        out.flush();
        
        System.out.println("Seeing if file configured");
        if (this.configManager.get("config", String.format("status.%s", this.status)) == null) {
            System.out.println("File not configured");
            out.close();
            return;
        }

        File file = new File(String.format("%s%s%s",
                this.configManager.get("config", "root"),
                System.getProperty("file.separator"),
                this.configManager.get("config", String.format("status.%s", this.status)
        )));
        if (!file.exists() || file.isDirectory()) {
            System.out.println("File not exists");
            out.close();
            return;
        }

        BufferedOutputStream body = new BufferedOutputStream(output);
        FileInputStream file_stream = new FileInputStream(file);
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
