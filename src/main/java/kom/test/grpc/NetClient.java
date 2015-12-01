package kom.test.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import kom.test.grpc.NetGrpc.NetStub;

import java.util.concurrent.TimeUnit;

/**
 * Created by syungman on 01.12.2015
 */
public class NetClient {
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

    private StreamObserver<NetMessage> openStream() {
        return null; //asynkStub.openStream()
    }

    public boolean testConnection() {
        return false;
    }

    public static void main(String ... args) throws Exception {
        NetClient client = new NetClient("localhost", 2233);
        try {
            client.openStream();
        } finally {
            client.shutdown();
        }
    }
}
