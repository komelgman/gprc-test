package kom.test.grpc;

import io.grpc.*;
import io.grpc.ForwardingClientCall.SimpleForwardingClientCall;

/**
 * Created by Sergey on 06.12.2015
 */
public class AuthClientInterceptor implements ClientInterceptor {

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(
            MethodDescriptor<ReqT, RespT> methodDescriptor,
            CallOptions callOptions,
            Channel channel) {

        return new SimpleForwardingClientCall<ReqT, RespT>(channel.newCall(methodDescriptor, callOptions)) {
            @Override
            public void start(Listener<RespT> responseListener, Metadata headers) {
                headers.put(AuthConstants.USER_TOKEN_HEADER, "test-value");
                super.start(responseListener, headers);
            }
        };
    }
}
