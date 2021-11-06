package com.dannydjrs.deserve.response;

import java.io.File;

public class HtmlResponse extends FileResponse {
    public HtmlResponse(File file) {
        super(file, "text/html");
    }
}
