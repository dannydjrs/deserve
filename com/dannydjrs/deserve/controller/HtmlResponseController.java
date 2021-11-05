package com.dannydjrs.deserve.controller;

import com.dannydjrs.deserve.request.Request;
import com.dannydjrs.deserve.response.Response;
import com.dannydjrs.deserve.response.FourZeroFourResponse;
import com.dannydjrs.deserve.response.HtmlResponse;
import com.dannydjrs.deserve.response.NotImplementedResponse;
import com.dannydjrs.deserve.response.RedirectResponse;
import com.dannydjrs.deserve.config.Config;
import java.io.File;

public class HtmlResponseController implements Controller {
    public Response get(Request request) {
        if (request.getUrl().equals("/")) {
            return new RedirectResponse("/index.html");
        }

        File file = new File(Config.get("root") + request.getUrl());
        if (!file.exists()) {
            return new FourZeroFourResponse();
        }

        return new HtmlResponse(file);
    }
    
    public Response post(Request request) {
        return new NotImplementedResponse();
    }
}
