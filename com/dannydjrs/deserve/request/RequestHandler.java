package com.dannydjrs.deserve.request;

import com.dannydjrs.deserve.controller.Controller;
import com.dannydjrs.deserve.controller.ControllerFactory;
import com.dannydjrs.deserve.response.Response;
import com.dannydjrs.deserve.response.NotImplementedResponse;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RequestHandler implements Runnable {
    private Socket client;

    public RequestHandler(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            Request request = RequestBuilder.build(new BufferedReader(
                new InputStreamReader(client.getInputStream()
            )));
            
            System.out.println(String.format(
                "Request: %s %s %s", 
                request.getMethod(),
                request.getUrl(),
                request.getProtocol()
            ));
        
            Controller controller = ControllerFactory.create(request);

            Response response;
            if (request.getMethod().equals("GET")) {
                response = controller.get(request);
            } else if (request.getMethod().equals("POST")) {
                response = controller.post(request);
            } else {
                response = new NotImplementedResponse();
            }

            response.send(this.client);
        } catch (Exception ex) {
            System.out.println("AN ERROR OCCURRED");
            ex.printStackTrace();
        }
    }
}
