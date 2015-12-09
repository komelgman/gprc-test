package kom.test.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import kom.test.grpc.ServiceMessage.Msg1;
import kom.test.grpc.ServiceMessage.Msg2;
import kom.test.grpc.ServiceMessage.Msg3;
import kom.test.grpc.ServiceMessage.Msg4;

import java.util.logging.Level;
import java.util.logging.Logger;

import static io.grpc.ServerInterceptors.intercept;

/**
 * Created by syungman on 02.12.2015
 */
public class NetServer {
    private static final Logger log = Logger.getLogger(NetClient.class.getName());

    public static void main(String[] args) throws Exception {
        Server server = ServerBuilder.forPort(2233)
                .addService(intercept(NetGrpc.bindService(new NetService()), new AuthServerInterceptor()))
                .build();

        ServerLauncher.launch(server);
    }

    private static class NetService implements NetGrpc.Net {
        @Override
        public StreamObserver<ServiceMessage> serviceBus(final StreamObserver<ServiceMessage> clientObserver) {
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