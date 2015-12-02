package kom.test.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by syungman on 02.12.2015
 */
public class NetServer {
    private static final Logger log = Logger.getLogger(NetClient.class.getName());

    private final int port;
    private Server server;

    public NetServer(int port) {
        this.port = port;
    }

    /** Start serving requests. */
    public void start() throws IOException {
        server = ServerBuilder.forPort(port)
                .addService(NetGrpc.bindService(new NetService()))
                .build()
                .start();
        log.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may has been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                NetServer.this.stop();
                System.err.println("*** server shut down");
            }
        });
    }

    /** Stop serving requests and shutdown resources. */
    public void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws Exception {
        NetServer server = new NetServer(2233);
        server.start();
        server.blockUntilShutdown();
    }

    private class NetService implements NetGrpc.Net {
        @Override
        public StreamObserver<NetMessage> openStream(final StreamObserver<NetMessage> clientObserver) {
            return new StreamObserver<NetMessage>() {
                @Override
                public void onNext(NetMessage message) {
                    log.info("body case:" + message.getBodyCase().getNumber());

                    NetMessage.Command msgBody = NetMessage.Command.newBuilder().build();
                    NetMessage netMessage = NetMessage.newBuilder().setCommand(msgBody).build();
                    clientObserver.onNext(netMessage);
                }

                @Override
                public void onError(Throwable t) {
                    log.log(Level.WARNING, "Encountered error in openStream", t);
                }

                @Override
                public void onCompleted() {
                    clientObserver.onCompleted();
                }
            };
        }
    }
}
