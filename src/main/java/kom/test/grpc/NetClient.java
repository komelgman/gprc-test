package kom.test.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import kom.test.grpc.NetGrpc.NetStub;
import rx.Observable;
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

        asynkStub = NetGrpc.newStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    private void test() {
        PublishSubject<NetMessage> subject = PublishSubject.create();
        final StreamObserver<NetMessage> serverObserver = asynkStub.openStream(new StreamObserver<NetMessage>() {
            @Override
            public void onNext(NetMessage netMessage) {
                subject.onNext(netMessage);
            }

            @Override
            public void onError(Throwable throwable) {
                subject.onError(throwable);
            }

            @Override
            public void onCompleted() {
                subject.onCompleted();
            }
        });

        subject
                .subscribe(netMessage -> {
                    switch (netMessage.getBodyCase().getNumber()) {
                        case NetMessage.COMMAND_FIELD_NUMBER:
                            log.info("command:" + netMessage.getBodyCase().getNumber());
                            // someCodeRun(netMessage.getCommand());
                            serverObserver.onCompleted();
                            break;
                        default:
                            log.info("unknown:" + netMessage.getBodyCase().getNumber());
                    }
                });

        subject
                .filter(message -> message.getBodyCase().getNumber() == NetMessage.COMMAND_FIELD_NUMBER)
                .map(netMessage -> netMessage.getCommand())
                .subscribe(command -> {
                    log.info("obj:" + command.getClass());
                });

        NetMessage.Connect msgBody = NetMessage.Connect.newBuilder().build();
        NetMessage netMessage = NetMessage.newBuilder().setMsgConnect(msgBody).build();
        serverObserver.onNext(netMessage);
    }

    public class NetConnection {
        private final StreamObserver<NetMessage> remoteObserver;
        private final PublishSubject<NetMessage> localSubject;

        public NetConnection(StreamObserver<NetMessage> remoteObserver, PublishSubject<NetMessage> localSubject) {
            this.remoteObserver = remoteObserver;
            this.localSubject = localSubject;
        }

        public void send(NetMessage message) {
            remoteObserver.onNext(message);
        }

        public Observable<NetMessage> asObservable() {
            return localSubject;
        }
    }

    public boolean testConnection() {
        return false;
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
