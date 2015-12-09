package kom.test.grpc;

import io.grpc.ClientInterceptors;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import kom.test.grpc.NetGrpc.NetStub;
import kom.test.grpc.ServiceMessage.Msg1;
import kom.test.grpc.ServiceMessage.Msg2;
import kom.test.grpc.ServiceMessage.Msg3;
import kom.test.grpc.ServiceMessage.Msg4;
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

        AuthToken.set("asdasd");

        final StreamObserver<ServiceMessage> serverObserver = asynkStub.serviceBus(new ObserverProxy<>(subject));

        subject
                .subscribe(message -> {
                    boolean finish = false;
                    ServiceMessage serviceMessage = null;
                    switch (message.getBodyCase().getNumber()) {
                        case ServiceMessage.MSG1_FIELD_NUMBER:
                            //serviceMessage = ServiceMessage.newBuilder().setMsg2(Msg2.newBuilder().build()).build();
                            finish = true;
                            break;

                        case ServiceMessage.MSG2_FIELD_NUMBER:
                            serviceMessage = ServiceMessage.newBuilder().setMsg3(Msg3.newBuilder().build()).build();
                            break;

                        case ServiceMessage.MSG3_FIELD_NUMBER:
                            serviceMessage = ServiceMessage.newBuilder().setMsg4(Msg4.newBuilder().build()).build();
                            break;

                        case ServiceMessage.MSG4_FIELD_NUMBER:
                            serviceMessage = ServiceMessage.newBuilder().setMsg1(Msg1.newBuilder().build()).build();
                            break;

                        default:
                    }

                    if (serviceMessage != null)
                        serverObserver.onNext(serviceMessage);

                    if (finish)
                        serverObserver.onCompleted();
                });

//        subject
////                .filter(message -> message.getBodyCase().getNumber() == ServiceMessage.COMMAND_FIELD_NUMBER)
////                .map(netMessage -> netMessage.getCommand())
//                .subscribe(message -> {
//                    //log.info("rcvdFrmSrvr:" + message.getBodyCase().name());
//                });

        ServiceMessage message = ServiceMessage.newBuilder().setMsg2(Msg2.newBuilder().build()).build();
        serverObserver.onNext(message);
    }

    public static void main(String ... args) throws Exception {
        NetClient client = new NetClient("localhost", 2233);
        try {
            for (int i = 0; i < 1; i++)
                client.test();

        } finally {
            client.shutdown();
        }
    }
}
