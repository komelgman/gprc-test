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
            return new AuthServerListener<>(next.startCall(method, call, requestHeaders), token);
        } finally {
            AuthToken.remove();
        }
    }

    private static class AuthServerListener<ReqT> extends ForwardingServerCallListener<ReqT> {
        private final ServerCall.Listener<ReqT> delegate;
        private final String token;

        public AuthServerListener(ServerCall.Listener<ReqT> delegate, String token) {
            this.delegate = delegate;
            this.token = token;
        }

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

        @Override
        public void onCancel() {
            //logger.info("trace");
            try {
                AuthToken.set(token);
                super.onCancel();
            } finally {
                AuthToken.remove();
            }
        }

        @Override
        public void onComplete() {
            //logger.info("trace");
            try {
                AuthToken.set(token);
                super.onComplete();
            } finally {
                AuthToken.remove();
            }
        }

        @Override
        public void onReady() {
            //logger.info("trace");
            try {
                AuthToken.set(token);
                super.onReady();
            } finally {
                AuthToken.remove();
            }
        }
    }
}
