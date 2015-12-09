package kom.test.grpc;

import io.grpc.Server;

import java.io.IOException;

/**
 * Created by syungman on 09.12.2015
 */
public class ServerLauncher {

    private final Server server;

    public ServerLauncher(Server server) {
        this.server = server;
    }

    public void start() throws IOException {
        if (server != null)
            server.start();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                ServerLauncher.this.stop();
            }
        });
    }

    /**
     * Stop serving requests and shutdown resources.
     */
    public void stop() {
        if (server != null)
            server.shutdown();
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null)
            server.awaitTermination();
    }

    public static void launch(Server server) throws Exception {
        ServerLauncher launcher = new ServerLauncher(server);
        launcher.start();
        launcher.blockUntilShutdown();
    }
}