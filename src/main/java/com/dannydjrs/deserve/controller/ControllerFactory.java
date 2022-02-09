package com.dannydjrs.deserve.controller;

import com.dannydjrs.deserve.objectmanager.ObjectManager;
import com.dannydjrs.deserve.request.Request;

/**
 * @author dannydjrs
 */
public class ControllerFactory {
    /**
     * 
     * @param request
     * @return
     */
    public Controller create(Request request) {
        return (FileResponseController) ObjectManager.getInstance().get("com.dannydjrs.deserve.controller.FileResponseController");
    }
}
