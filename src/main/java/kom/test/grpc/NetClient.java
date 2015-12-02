package kom.test.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import kom.test.grpc.NetGrpc.NetStub;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;
import rx.subjects.ReplaySubject;
import rx.subjects.Subject;

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
        final ObserverProxy<NetMessage> clientObserver = new ObserverProxy<NetMessage>();
        final StreamObserver<NetMessage> serverObserver = asynkStub.openStream(clientObserver);

        ReplaySubject<NetMessage> subject = ReplaySubject.create();

        clientObserver.setObserver(subject);

        subject
                .filter(message -> { return message.getBodyCase().getNumber() == NetMessage.COMMAND_FIELD_NUMBER; })
                .subscribe(new Action1<NetMessage>() {
                    @Override
                    public void call(NetMessage message) {
                        log.info("body case:" + message.getBodyCase().getNumber());
                        serverObserver.onCompleted();
                    }
                });

        NetMessage.Connect msgBody = NetMessage.Connect.newBuilder().build();
        NetMessage netMessage = NetMessage.newBuilder().setMsgConnect(msgBody).build();
        serverObserver.onNext(netMessage);
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
