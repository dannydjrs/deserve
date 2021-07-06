package server;

import com.dannydjrs.deserve.request.RequestHandler;
import com.dannydjrs.deserve.config.Config;
import java.io.*;
import java.net.ServerSocket;
import java.lang.Thread;
import java.util.Properties;

public class Server {
    public static void main(String[] args) {
        // get port number from args
        int port = Integer.parseInt(args[0]);

        // Initialise config settings
        Properties prop = new Properties();
        try {
            InputStream input = new FileInputStream("config/config.properties");
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("ERROR: Cannot read file config/config.properties");
            System.exit(1);
        }

        Config.DEBUG = Boolean.parseBoolean(prop.getProperty("debug"));
        Config.ROOT = prop.getProperty("root");
        Config.SERVER_NAME = prop.getProperty("server.name");
        Config.SERVER_VERSION = prop.getProperty("server.version");

        if (!Config.is_valid()) {
            System.out.println("Configuration properties not set correctly");
            System.exit(1);
        }
        
        // Start running server
        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("Server listening on port " + port + "...");

            while (true) {
                RequestHandler handler = new RequestHandler(server.accept());
                Thread thread = new Thread(handler);
                thread.start();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }
}
