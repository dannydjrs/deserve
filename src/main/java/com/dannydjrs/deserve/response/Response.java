package com.dannydjrs.deserve.response;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 
 * @author dannydjrs
 */
public interface Response {
    /**
     * 
     * @param output
     * @throws IOException
     */
    public void send(OutputStream output) throws IOException;
}
