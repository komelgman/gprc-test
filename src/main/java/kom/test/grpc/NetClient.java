package kom.test.grpc;

import io.grpc.ClientInterceptors;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import kom.test.grpc.NetGrpc.NetStub;
import rx.subjects.PublishSubject;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created by syungman on 01.12.2015
 */
public class NetClient {
    private static final Logger log = Logger.getLogger(NetClient.class.getName());

    private final ManagedChannel channel;
    private final NetStub asynkStub;

    public NetClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext(true) // debug
                .build();

        asynkStub = NetGrpc.newStub(ClientInterceptors.intercept(channel, new AuthClientInterceptor()));
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    private void test() {
        PublishSubject<ServiceMessage> subject = PublishSubject.create();
        final StreamObserver<ServiceMessage> serverObserver = asynkStub.serviceBus(new ObserverProxy<>(subject));

        subject
                .subscribe(netMessage -> {
                    switch (netMessage.getBodyCase().getNumber()) {
                        case ServiceMessage.COMMAND_FIELD_NUMBER:
                            log.info("command:" + netMessage.getBodyCase().getNumber());
                            // someCodeRun(netMessage.getCommand());
                            serverObserver.onCompleted();
                            break;
                        default:
                            log.info("unknown:" + netMessage.getBodyCase().getNumber());
                    }
                });

        subject
                .filter(message -> message.getBodyCase().getNumber() == ServiceMessage.COMMAND_FIELD_NUMBER)
                .map(netMessage -> netMessage.getCommand())
                .subscribe(command -> {
                    log.info("obj:" + command.getClass());
                });

        ServiceMessage.Command command = ServiceMessage.Command.newBuilder().build();
        ServiceMessage message = ServiceMessage.newBuilder().setCommand(command).build();
        serverObserver.onNext(message);
    }

    public static void main(String ... args) throws Exception {
        NetClient client = new NetClient("localhost", 2233);
        try {
            client.test();
        } finally {
            client.shutdown();
        }
    }
}
