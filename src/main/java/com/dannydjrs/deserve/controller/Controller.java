package com.dannydjrs.deserve.controller;

import com.dannydjrs.deserve.request.Request;
import com.dannydjrs.deserve.response.Response;

/**
 * @author dannydjrs
 */
public interface Controller {
    /**
     * 
     * @param request
     * @return
     */
    public Response get(Request request);

    /**
     * 
     * @param request
     * @return
     */
    public Response post(Request request);
}
