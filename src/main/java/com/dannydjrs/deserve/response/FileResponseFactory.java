package com.dannydjrs.deserve.response;

import com.dannydjrs.deserve.objectmanager.ObjectFactory;
import com.dannydjrs.deserve.objectmanager.ObjectManager;

/**
 * @author dannydjrs
 */
public class FileResponseFactory implements ObjectFactory<FileResponse> {
    /**
     * 
     */
    public FileResponse create() {
        return (FileResponse) ObjectManager.getInstance().get("com.dannydjrs.deserve.response.FileResponse");
    }
}
