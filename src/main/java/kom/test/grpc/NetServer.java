package kom.test.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptors;
import io.grpc.stub.StreamObserver;
import kom.test.grpc.ServiceMessage.Msg1;
import kom.test.grpc.ServiceMessage.Msg2;
import kom.test.grpc.ServiceMessage.Msg3;
import kom.test.grpc.ServiceMessage.Msg4;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static io.grpc.ServerInterceptors.intercept;
import static kom.test.grpc.ServiceMessage.CHATMESSAGE_FIELD_NUMBER;

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

    public void start() throws IOException {
        server = ServerBuilder.forPort(port)
                //.addService(NetGrpc.bindService(new NetService()))
                .addService(intercept(NetGrpc.bindService(new NetService()), new AuthServerInterceptor()))
                .build()
                .start();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                NetServer.this.stop();
            }
        });
    }

    /**
     * Stop serving requests and shutdown resources.
     */
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
        public StreamObserver<ServiceMessage> serviceBus(final StreamObserver<ServiceMessage> clientObserver) {
            log.info("authToken: " + AuthToken.get());

            return new StreamObserver<ServiceMessage>() {
                @Override
                public void onNext(ServiceMessage message) {
                    //log.info("trace");
                    //log.info("authToken: " + AuthToken.get());

                    ServiceMessage serviceMessage = null;
                    switch (message.getBodyCase().getNumber()) {
                        case ServiceMessage.MSG1_FIELD_NUMBER:
                            serviceMessage = ServiceMessage.newBuilder().setMsg1(Msg1.newBuilder().build()).build();
                            break;

                        case ServiceMessage.MSG2_FIELD_NUMBER:
                            serviceMessage = ServiceMessage.newBuilder().setMsg2(Msg2.newBuilder().build()).build();
                            break;

                        case ServiceMessage.MSG3_FIELD_NUMBER:
                            serviceMessage = ServiceMessage.newBuilder().setMsg3(Msg3.newBuilder().build()).build();
                            break;

                        case ServiceMessage.MSG4_FIELD_NUMBER:
                            serviceMessage = ServiceMessage.newBuilder().setMsg4(Msg4.newBuilder().build()).build();
                            break;

                        default:
                    }

                    if (serviceMessage != null)
                        clientObserver.onNext(serviceMessage);
                }

                @Override
                public void onError(Throwable t) {
                    log.log(Level.WARNING, "Encountered error in serviceBus", t);
                }

                @Override
                public void onCompleted() {
                    clientObserver.onCompleted();
                }
            };
        }
    }
}