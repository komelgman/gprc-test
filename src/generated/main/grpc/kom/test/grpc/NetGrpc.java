package kom.test.grpc;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;

@javax.annotation.Generated("by gRPC proto compiler")
public class NetGrpc {

  private NetGrpc() {}

  public static final String SERVICE_NAME = "Net";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<kom.test.grpc.NetMessage,
      kom.test.grpc.NetMessage> METHOD_OPEN_STREAM =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING,
          generateFullMethodName(
              "Net", "OpenStream"),
          io.grpc.protobuf.ProtoUtils.marshaller(kom.test.grpc.NetMessage.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(kom.test.grpc.NetMessage.getDefaultInstance()));

  public static NetStub newStub(io.grpc.Channel channel) {
    return new NetStub(channel);
  }

  public static NetBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new NetBlockingStub(channel);
  }

  public static NetFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new NetFutureStub(channel);
  }

  public static interface Net {

    public io.grpc.stub.StreamObserver<kom.test.grpc.NetMessage> openStream(
        io.grpc.stub.StreamObserver<kom.test.grpc.NetMessage> responseObserver);
  }

  public static interface NetBlockingClient {
  }

  public static interface NetFutureClient {
  }

  public static class NetStub extends io.grpc.stub.AbstractStub<NetStub>
      implements Net {
    private NetStub(io.grpc.Channel channel) {
      super(channel);
    }

    private NetStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NetStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new NetStub(channel, callOptions);
    }

    @java.lang.Override
    public io.grpc.stub.StreamObserver<kom.test.grpc.NetMessage> openStream(
        io.grpc.stub.StreamObserver<kom.test.grpc.NetMessage> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(METHOD_OPEN_STREAM, getCallOptions()), responseObserver);
    }
  }

  public static class NetBlockingStub extends io.grpc.stub.AbstractStub<NetBlockingStub>
      implements NetBlockingClient {
    private NetBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private NetBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NetBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new NetBlockingStub(channel, callOptions);
    }
  }

  public static class NetFutureStub extends io.grpc.stub.AbstractStub<NetFutureStub>
      implements NetFutureClient {
    private NetFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private NetFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NetFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new NetFutureStub(channel, callOptions);
    }
  }

  public static io.grpc.ServerServiceDefinition bindService(
      final Net serviceImpl) {
    return io.grpc.ServerServiceDefinition.builder(SERVICE_NAME)
      .addMethod(
        METHOD_OPEN_STREAM,
        asyncBidiStreamingCall(
          new io.grpc.stub.ServerCalls.BidiStreamingMethod<
              kom.test.grpc.NetMessage,
              kom.test.grpc.NetMessage>() {
            @java.lang.Override
            public io.grpc.stub.StreamObserver<kom.test.grpc.NetMessage> invoke(
                io.grpc.stub.StreamObserver<kom.test.grpc.NetMessage> responseObserver) {
              return serviceImpl.openStream(responseObserver);
            }
          })).build();
  }
}
