package com.dannydjrs.deserve.controller;

import com.dannydjrs.deserve.request.Request;
import com.dannydjrs.deserve.response.Response;

public interface Controller {
    public Response get(Request request);
    public Response post(Request request);
}
