package com.dannydjrs.deserve.controller;

import com.dannydjrs.deserve.request.Request;
import com.dannydjrs.deserve.response.Response;
import com.dannydjrs.deserve.controller.Controller;
import com.dannydjrs.deserve.response.FourZeroFourResponse;
import com.dannydjrs.deserve.response.FileResponse;
import com.dannydjrs.deserve.response.NotImplementedResponse;
import com.dannydjrs.deserve.config.Config;
import java.io.File;

public class FileResponseController implements Controller {
    public Response get(Request request) {
        File file = new File(Config.ROOT + request.get("URL"));
        if (!file.exists()) {
            return new FourZeroFourResponse();
        }

        return new FileResponse(file, "text/plain");
    }
    
    public Response post(Request request) {
        return new NotImplementedResponse();
    }
}
