package kom.test.grpc;

/**
 * Created by syungman on 09.12.2015
 */

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import kom.test.grpc.auth.*;

import java.util.logging.Logger;

/**
 * Created by syungman on 02.12.2015
 */
public class AuthServer {
    private static final Logger log = Logger.getLogger(NetClient.class.getName());

    public static void main(String[] args) throws Exception {
        Server server = ServerBuilder.forPort(443)
                .addService(AuthServiceGrpc.bindService(new AuthService()))
                //.useTransportSecurity() // secure this connection
                .build();

        ServerLauncher.launch(server);
    }

    private static class AuthService implements AuthServiceGrpc.AuthService {
        @Override
        public void signUp(SignUpParams request, StreamObserver<SignUpResult> responseObserver) {
            SignUpResult.Builder result = SignUpResult.newBuilder();

            result.setStatus(Status.OK.getCode().value());
            result.setMessage("Ok");

            responseObserver.onNext(result.build());
            responseObserver.onCompleted();
        }

        @Override
        public void signIn(SignInParams request, StreamObserver<SignInResult> responseObserver) {
            SignInResult.Builder result = SignInResult.newBuilder();

            //result.s

            responseObserver.onNext(result.build());
            responseObserver.onCompleted();
        }

        @Override
        public void logout(LogoutParams request, StreamObserver<LogoutResult> responseObserver) {

        }

        @Override
        public void verifyToken(VerifyTokenParams request, StreamObserver<VerifyTokenResult> responseObserver) {

        }
    }
}