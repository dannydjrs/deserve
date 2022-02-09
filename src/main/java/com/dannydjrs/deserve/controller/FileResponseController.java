package com.dannydjrs.deserve.controller;

import com.dannydjrs.deserve.request.Request;
import com.dannydjrs.deserve.response.Response;
import com.dannydjrs.deserve.response.StatusResponseFactory;
import com.dannydjrs.deserve.response.FileResponseFactory;
import com.dannydjrs.deserve.config.ConfigManager;

import java.io.File;

/**
 * @author dannydjrs
 */
public class FileResponseController implements Controller {
    private FileResponseFactory fileResponseFactory;
    private StatusResponseFactory statusResponseFactory;
    private ConfigManager configManager;

    /**
     * 
     * @param fileResponseFactory
     * @param statusResponseFactory
     * @param configManager
     */
    public FileResponseController(
        FileResponseFactory fileResponseFactory,
        StatusResponseFactory statusResponseFactory,
        ConfigManager configManager
    ) {
        this.fileResponseFactory = fileResponseFactory;
        this.statusResponseFactory = statusResponseFactory;
        this.configManager = configManager;
    }

    /**
     * 
     */
    public Response get(Request request) {
        String contentType = "text/html";

        if (request.getUrl().matches(".+.html")) {
            contentType = "text/html";
        }

        if (request.getUrl().matches(".+.json")) {
            contentType = "text/json";
        }
        
        File file = new File(this.configManager.get("config", "root") + request.getUrl());
        if (!file.exists() || file.isDirectory()) {
            return statusResponseFactory.create().setStatus(404);
        }

        return this.fileResponseFactory.create()
            .setContentType(contentType)
            .setFile(file);
    }
    
    /**
     * 
     */
    public Response post(Request request) {
        return statusResponseFactory.create().setStatus(501);
    }
}
