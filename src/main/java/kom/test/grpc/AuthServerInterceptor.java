package kom.test.grpc;

import io.grpc.*;
import java.util.logging.Logger;

/**
 * Created by Sergey on 06.12.2015
 */
public class AuthServerInterceptor implements ServerInterceptor {

    private static final Logger logger = Logger.getLogger(AuthServerInterceptor.class.getName());

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
            MethodDescriptor<ReqT, RespT> method,
            ServerCall<RespT> call,
            final Metadata requestHeaders,
            ServerCallHandler<ReqT, RespT> next) {

        logger.info("header received from client:" + requestHeaders.toString());


        try {
            final String token = requestHeaders.get(AuthConstants.USER_TOKEN_HEADER);
            AuthToken.set(token);

            final ServerCall.Listener<ReqT> delegate = next.startCall(method, call, requestHeaders);

            return new ForwardingServerCallListener<ReqT>() {
                @Override
                protected ServerCall.Listener<ReqT> delegate() {
                    return delegate;
                }

                @Override
                public void onMessage(ReqT message) {
                    //logger.info("trace");
                    try {
                        AuthToken.set(token);
                        super.onMessage(message);
                    } finally {
                        AuthToken.remove();
                    }
                }

                @Override
                public void onHalfClose() {
                    //logger.info("trace");
                    try {
                        AuthToken.set(token);
                        super.onHalfClose();
                    } finally {
                        AuthToken.remove();
                    }
                }
            };
        } finally {
            AuthToken.remove();
        }
    }
}
