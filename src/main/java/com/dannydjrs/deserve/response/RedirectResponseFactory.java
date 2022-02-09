package com.dannydjrs.deserve.response;

import com.dannydjrs.deserve.objectmanager.ObjectFactory;
import com.dannydjrs.deserve.objectmanager.ObjectManager;

/**
 * @author dannydjrs
 */
public class RedirectResponseFactory implements ObjectFactory<RedirectResponse> {
    /**
     * 
     */
    public RedirectResponse create() {
        return (RedirectResponse) ObjectManager.getInstance().get("com.dannydjrs.deserve.response.RedirectResponse");
    }   
}
