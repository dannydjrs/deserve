import com.dannydjrs.deserve.request.RequestHandler;
import com.dannydjrs.deserve.config.Config;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class Server {
    private ExecutorService executor;
    private ServerSocket serverSocket;

    public Server(int threads) {
        this.executor = Executors.newFixedThreadPool(threads);
    }

    public void start(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);

        while (true) {
            this.executor.submit(new RequestHandler(this.serverSocket.accept()));
        }
    }

    public void stop() throws IOException {
        this.executor.shutdownNow();
        this.serverSocket.close();
    }

    public static void main(String[] args) throws IOException {
        // get port number from args
        int port = Integer.parseInt(args[0]);

        // Initialise config settings
        Config.setup(new FileInputStream("config/config.properties"));

        // Start running server
        Server server = new Server(Config.getInt("threads"));
        try {
            System.out.println("Server listening on port " + port + "...");
            server.start(port);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            server.stop();
            System.exit(1);
        }
    }
}
