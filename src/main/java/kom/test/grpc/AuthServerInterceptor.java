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

        String token = requestHeaders.get(AuthConstants.USER_TOKEN_HEADER);
        try {
            Test.set(token);
            return next.startCall(method, call, requestHeaders);
        } finally {
            Test.remove();
        }
    }
}
