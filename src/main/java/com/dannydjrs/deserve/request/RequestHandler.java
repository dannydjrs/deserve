package com.dannydjrs.deserve.request;

import com.dannydjrs.deserve.controller.Controller;
import com.dannydjrs.deserve.controller.ControllerFactory;
import com.dannydjrs.deserve.objectmanager.ObjectManager;
import com.dannydjrs.deserve.response.Response;
import com.dannydjrs.deserve.response.StatusResponse;
import com.dannydjrs.deserve.response.StatusResponseFactory;

import java.net.Socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * @author dannydjrs
 */
public class RequestHandler implements Runnable {
    private final Socket client;

    /**
     * 
     * @param client
     */
    public RequestHandler(final Socket client) {
        this.client = client;
    }

    /**
     * 
     */
    @Override
    public void run() {
        BufferedReader input = null;
		OutputStream output = null;

        try {
            input = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
            output = this.client.getOutputStream();

            RequestBuilder builder = new RequestBuilder();
            Request request = builder.build(input);
            if (request == null) {
                closeInputOutput(input, output);
                return;
            }
            
            System.out.println(request.toString());
            
            Controller controller = new ControllerFactory().create(request);

            Response response;            
            if (request.getMethod().equals("GET")) {
                response = controller.get(request);
            } else  {
                response = controller.post(request);
            }
            
            response.send(output);
        } catch (Exception ex) {
            System.err.println("AN ERROR OCCURRED");
            ex.printStackTrace();
        } finally {
            this.closeInputOutput(input, output);
        }
    }
    
    /**
     * 
     * @param input
     * @param output
     */
    private void closeInputOutput(BufferedReader input, OutputStream output) {
        try {
            if (input != null)
                input.close();

            if (output != null)
                output.close();

            this.client.close();
        } catch (IOException ex) {
            // Ignore
        }
    }
}
