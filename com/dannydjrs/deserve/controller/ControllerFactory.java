package com.dannydjrs.deserve.controller;

import com.dannydjrs.deserve.request.Request;
import com.dannydjrs.deserve.controller.Controller;
import com.dannydjrs.deserve.controller.FileResponseController;

public class ControllerFactory {
    public static Controller build(Request request) {
        return new FileResponseController();
    }
}
