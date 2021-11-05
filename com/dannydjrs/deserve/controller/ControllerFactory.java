package com.dannydjrs.deserve.controller;

import com.dannydjrs.deserve.request.Request;

public class ControllerFactory {

    public static Controller create(Request request) {
        return new HtmlResponseController();
    }
}
