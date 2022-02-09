package com.dannydjrs.deserve.response;

import com.dannydjrs.deserve.objectmanager.ObjectFactory;
import com.dannydjrs.deserve.objectmanager.ObjectManager;

/**
 * @author dannydjrs
 */
public class StatusResponseFactory implements ObjectFactory<StatusResponse> {
    /**
     * 
     */
    public StatusResponse create() {
        return (StatusResponse) ObjectManager.getInstance().get("com.dannydjrs.deserve.response.StatusResponse");
    }   
}
