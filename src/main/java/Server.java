import com.dannydjrs.deserve.request.RequestHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutorService;

/**
 * A server to run a multithreaded request handler.
 * 
 * @author dannydjrs
 */
public class Server {
    private final ExecutorService executor;
    private final ServerSocket serverSocket;
    private volatile boolean running = true;

    /**
     * Server constructor to initialise a socket on the given port.
     * Additionally sets up the threads that will listen on the socket for requests.
     * 
     * @param port
     * @param threads
     * @throws IOException
     */
    public Server(final int port, final int threads) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.executor = Executors.newFixedThreadPool(threads);
    }

    /**
     * 
     */
    public void start() {
        while (this.running) {
            try {
                this.executor.execute(new RequestHandler(this.serverSocket.accept()));
            } catch (IOException ex) {
                Thread.yield();
            }
        }
    }

    /**
     * 
     * @throws IOException
     */
    public void shutdownAndAwaitTermination() throws IOException {
        this.running = false;
        this.executor.shutdown();
        try {
            if (!this.executor.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
                if (!this.executor.awaitTermination(1000, TimeUnit.MILLISECONDS))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException ex) {
            this.executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
        this.serverSocket.close();
    }

    /**
     * 
     */
    public static void main(String[] args) throws IOException {
        // get port number from args
        int port = Integer.parseInt(args[0]);
        int threads = Integer.parseInt(args[1]);

        // Start running server
        Server server = new Server(port, threads);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Server shutting down...");
            try {
                server.shutdownAndAwaitTermination();
            } catch (IOException ex) {
                // TODO: LOG EXCEPTION
                ex.printStackTrace();
            }
		}));
        
        System.out.println("Server listening on port " + port + "...");
        server.start();
    }
}
